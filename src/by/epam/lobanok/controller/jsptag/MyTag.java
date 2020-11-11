package by.epam.lobanok.controller.jsptag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class MyTag extends TagSupport{
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Все права защищены";
	
	public int doStartTag() throws JspException {
       JspWriter writer = pageContext.getOut();
       try { 
    	  writer.println(MESSAGE);
       } catch (IOException e) {
    	   throw new JspException(e);
       }
       return SKIP_BODY;
	}
} 