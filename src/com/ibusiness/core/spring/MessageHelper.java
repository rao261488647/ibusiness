package com.ibusiness.core.spring;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;

import org.springframework.ui.Model;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 进行国际化的消息提示时
 * 
 * @author JiangBo
 * 
 */
public class MessageHelper {
    private MessageSourceAccessor messages;

	/**
	 * 重定向传参
	 */
    public void addFlashMessage(RedirectAttributes redirectAttributes, String code, String text) {
        addFlashMessage(redirectAttributes, messages.getMessage(code, text));
    }

    public void addFlashMessage(RedirectAttributes redirectAttributes, String text) {
        List<String> flashMessages = (List<String>) redirectAttributes.getFlashAttributes().get("flashMessages");

        if (flashMessages == null) {
            flashMessages = new ArrayList<String>();
            redirectAttributes.addFlashAttribute("flashMessages", flashMessages);
        }

        flashMessages.add(text);
    }

    public void addMessage(Model model, String text) {
        List<String> flashMessages = (List<String>) model.asMap().get("flashMessages");

        if (flashMessages == null) {
            flashMessages = new ArrayList<String>();
            model.addAttribute("flashMessages", flashMessages);
        }

        flashMessages.add(text);
    }

    @Resource
    public void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }
}
