package com.ibusiness.core.ext.message;

import javax.jms.JMSException;
import javax.jms.TextMessage;
/**
 * 字符消息是
 * 
 * @author JiangBo
 *
 */
public class ProxyTextMessage extends ProxyMessage implements TextMessage {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
