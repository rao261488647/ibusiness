package com.ibusiness.bpm.listener;

import java.util.Collections;
import java.util.List;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * 自定义监听器-任务. 在记录history之后执行的TaskListener
 * @author JiangBo
 * 
 */
public class ProxyTaskListener implements TaskListener {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private List<TaskListener> taskListeners = Collections.emptyList();

    public void notify(DelegateTask delegateTask) {
        for (TaskListener taskListener : taskListeners) {
            taskListener.notify(delegateTask);
        }
    }

    public void setTaskListeners(List<TaskListener> taskListeners) {
        this.taskListeners = taskListeners;
    }
}
