package com.qhtr.utils.weixinMessage.model;

import java.util.Map;

/**
 * @author Harry
 * @Description 微信消息模版
 * @date  2017年7月10日
 */
public class WxTemplate {

	/**
	 * 消息模版id
	 */
	private String template_id;
	
	/**
	 * 用户openid
	 */
	private String touser;
	
	/**
	 * 点击进入的url
	 */
	private String url;
	
	/**
	 * 标题颜色
	 */
	private String topColor;
	
	/**
	 * 详细内容
	 */
	private Map<String,TemplateData> data;
	
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTopColor() {
		return topColor;
	}
	public void setTopColor(String topColor) {
		this.topColor = topColor;
	}
	public Map<String, TemplateData> getData() {
		return data;
	}
	public void setData(Map<String, TemplateData> data) {
		this.data = data;
	}
	
	
}
