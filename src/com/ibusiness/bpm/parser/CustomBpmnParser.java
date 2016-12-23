package com.ibusiness.bpm.parser;

import org.activiti.engine.impl.bpmn.parser.BpmnParser;
import org.activiti.engine.impl.bpmn.parser.factory.ActivityBehaviorFactory;
import org.activiti.engine.impl.bpmn.parser.factory.DefaultActivityBehaviorFactory;
/**
 * 解析器会保存一个BPMN 2.0元素与BpmnParseHandler实例的映射
 * 
 * @author JiangBo
 *
 */
public class CustomBpmnParser extends BpmnParser {
    // 当使用自定义activityBehaviorFactory 时，需要重写该方法
    public void setActivityBehaviorFactory(ActivityBehaviorFactory activityBehaviorFactory) {
        ((DefaultActivityBehaviorFactory) activityBehaviorFactory).setExpressionManager(expressionManager);
        super.setActivityBehaviorFactory(activityBehaviorFactory);
    }
}
