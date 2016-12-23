package com.ibusiness.core.ext.message;

import javax.jms.JMSException;
import javax.jms.Topic;
/**
 * Topic是对应Pub/Sub消息的目标地址。
 * 
 * @author JiangBo
 *
 */
public class ProxyTopic implements Topic {
    private String name;

    public ProxyTopic(String name) {
        this.name = name;
    }

    public String getTopicName() throws JMSException {
        return name;
    }

    public String toString() {
        return name;
    }
}
