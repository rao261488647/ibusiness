package com.ibusiness.codegenerate.common;
/**
 * 选择带出用Bean对象
 * @author jiangbo
 *
 */
public class SelectInputBean {
	// 选择输入的控件的值
	private String inputKey;
	// 选择带出的控件的值
	private String inputValue;
	// 控件的标题
	private String inputTitle;
	
	public String getInputKey() {
		return inputKey;
	}
	public void setInputKey(String inputKey) {
		this.inputKey = inputKey;
	}
	public String getInputValue() {
		return inputValue;
	}
	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}
	public String getInputTitle() {
		return inputTitle;
	}
	public void setInputTitle(String inputTitle) {
		this.inputTitle = inputTitle;
	}
}
