package com.aws.ses.dto;

/**
 * @author Sumanth
 *
 */
public class EmailTemplate {
	
	private String templateName;
	
	private String subjectPart;
	
	private String textPart;
	
	private String htmlPart;

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getSubjectPart() {
		return subjectPart;
	}

	public void setSubjectPart(String subjectPart) {
		this.subjectPart = subjectPart;
	}

	public String getTextPart() {
		return textPart;
	}

	public void setTextPart(String textPart) {
		this.textPart = textPart;
	}

	public String getHtmlPart() {
		return htmlPart;
	}

	public void setHtmlPart(String htmlPart) {
		this.htmlPart = htmlPart;
	}
	
}
