package com.ibusiness.bpm.suppor;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.persistence.entity.TaskEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 任务监听器 来实现自定义的分配逻辑
 * 
 * @author JiangBo
 *
 */
public class DefaultTaskListener implements TaskListener {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private static Logger logger = LoggerFactory.getLogger(DefaultTaskListener.class);

    /**
     * delegateTask：任务代理类
     */
    public void notify(DelegateTask delegateTask) {
        // DelegateTask对象和事件名称(delegateTask.eventName)
        String eventName = delegateTask.getEventName();
        logger.debug("{}", this);
        logger.debug("{} : {}", eventName, delegateTask);

        // create：任务创建并设置所有属性后触发
        if ("create".equals(eventName)) {
            try {
                this.onCreate(delegateTask);
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        // assignment：任务分配给一些人时触发。 
        // 当流程到达userTask， assignment事件 会在create事件之前发生。 
        // 这样的顺序似乎不自然，但是原因很简单：当获得create时间时， 我们想获得任务的所有属性，包括执行人.
        if ("assignment".equals(eventName)) {
            try {
                this.onAssignment(delegateTask);
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        // complete：当任务完成，并尚未从运行数据中删除时触发。
        if ("complete".equals(eventName)) {
            try {
                this.onComplete(delegateTask);
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        // delete：只在任务删除之前发生。 注意在通过completeTask正常完成时，也会执行。
        if ("delete".equals(eventName)) {
            try {
                this.onDelete(delegateTask);
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        // 作为参数传递给调用的对象
        ((TaskEntity) delegateTask).setEventName(eventName);
    }

    public void onCreate(DelegateTask delegateTask) throws Exception {
    }

    public void onAssignment(DelegateTask delegateTask) throws Exception {
    }

    public void onComplete(DelegateTask delegateTask) throws Exception {
    }

    public void onDelete(DelegateTask delegateTask) throws Exception {
    }
}
