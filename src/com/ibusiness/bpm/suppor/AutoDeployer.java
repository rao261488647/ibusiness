package com.ibusiness.bpm.suppor;

import java.io.IOException;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.annotation.PostConstruct;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ContextResource;
import org.springframework.core.io.Resource;

import com.ibusiness.bpm.cmd.SyncProcessCmd;

/**
 * 自动部署，并把每个XML都发布成一个Deployment.
 * Spring集成专门用于对资源部署的特性。
 * 在流程引擎的配置中，可以指定一组资源。
 * 当流程引擎被创建的时候， 所有在这里的资源都将会被自动扫描与部署。
 * 在这里有过滤以防止资源重新部署，只有当这个资源真正发生改变的时候，它才会向Activiti使用的数据库创建新的部署。
 */
public class AutoDeployer {
    private Logger logger = LoggerFactory.getLogger(AutoDeployer.class);
    // 流程引擎
    private ProcessEngine processEngine;
    // 部署资源
    private Resource[] deploymentResources = new Resource[0];
    private boolean enable = true;

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        if (!enable) {
            return;
        }
        // 如果没有需要部署的资源 直接返回
        if ((deploymentResources == null) || (deploymentResources.length == 0)) {
            return;
        }
        // 取得静态资源相关service
        RepositoryService repositoryService = processEngine.getRepositoryService();
        for (Resource resource : deploymentResources) {
            // 取得部署资源名
            String resourceName = null;
            if (resource instanceof ContextResource) {
                resourceName = ((ContextResource) resource).getPathWithinContext();
            } else if (resource instanceof ByteArrayResource) {
                resourceName = resource.getDescription();
            } else {
                try {
                    resourceName = resource.getFile().getAbsolutePath();
                } catch (IOException ex) {
                    logger.debug(ex.getMessage(), ex);
                    resourceName = resource.getFilename();
                }
            }

            try {
                // 取得需要上传发布的对象
                DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().enableDuplicateFiltering().name(resourceName);

                if (resourceName.endsWith(".bar") || resourceName.endsWith(".zip") || resourceName.endsWith(".jar")) {
                    deploymentBuilder.addZipInputStream(new ZipInputStream(resource.getInputStream()));
                } else {
                    deploymentBuilder.addInputStream(resourceName, resource.getInputStream());
                }

                // 上传并安装发布
                Deployment deployment = deploymentBuilder.deploy();
                logger.info("auto deploy : {}", resourceName);

                // 根据deployment取得
                for (ProcessDefinition processDefinition : repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).list()) {
                    // 执行命令
                    this.syncProcessDefinition(processDefinition.getId());
                }
            } catch (IOException ex) {
                throw new ActivitiException("couldn't auto deploy resource '" + resource + "': " + ex.getMessage(), ex);
            }
        }
    }

    /**
     * 检查是否有新资源需要发布
     * 
     * @param resourceName
     * @param lastModified
     * @return
     */
    public boolean checkDeploymentUpToDate(String resourceName, long lastModified) {
        List<Deployment> deployments = processEngine.getRepositoryService().createDeploymentQuery()
                .deploymentName(resourceName).orderByDeploymenTime().desc().list();

        if (deployments.isEmpty()) {
            return false;
        }

        Deployment deployment = deployments.get(0);

        return deployment.getDeploymentTime().getTime() > lastModified;
    }

    /**
     * 执行一个命令--创建流程用数据
     * @param processDefinitionId
     */
    public void syncProcessDefinition(String processDefinitionId) {
        processEngine.getManagementService().executeCommand(new SyncProcessCmd(processDefinitionId));
    }

    /**
     * 流程引擎的抽象.提供了获取所有服务的方法.
     * @param processEngine
     */
    public void setProcessEngine(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }

    /**
     * 资源的自动部署对象
     * @param deploymentResources
     */
    public void setDeploymentResources(Resource[] deploymentResources) {
        this.deploymentResources = deploymentResources;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
