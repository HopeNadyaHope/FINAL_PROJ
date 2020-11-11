<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="pageElement/header.jsp"%>
<fmt:message bundle="${loc}" key="local.text_about" var="text_about" />

<body>
	<p><c:out value="${text_about}" /></p>
	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2351.2388371174507!2d27.564995015305996!3d53.89195874140385!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x46dbcfc55560d89f%3A0xeff5cd213cedc06c!2svulica%20Kastry%C4%8Dnickaja%2010%2C%20Minsk!5e0!3m2!1sen!2sby!4v1604934743386!5m2!1sen!2sby" align="center" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
</body>

<%@include file="pageElement/footer.jsp"%>