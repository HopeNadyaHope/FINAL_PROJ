<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="../WEB-INF/tld/MyTagDescriptor.tld" prefix="tag"%>

<%@include file="pageElement/header.jsp"%>

<link rel="stylesheet" href="css/font-awesome.css" type="text/css">    

<fmt:message bundle="${loc}" key="local.message" var="message" />
<fmt:message bundle="${loc}" key="local.login" var="login" />
<fmt:message bundle="${loc}" key="local.password" var="password" />
<fmt:message bundle="${loc}" key="local.entrance" var="entrance" />
<fmt:message bundle="${loc}" key="local.registration" var="registration" />

<fmt:message bundle="${loc}" key="local.no_such_user" var="no_such_user" />
<fmt:message bundle="${loc}" key="local.server_exception" var="server_exception" />
<fmt:message bundle="${loc}" key="local.uncorrect_data" var="uncorrect_data" />

<body>
<c:if test="${sessionScope.user eq null}">
	<div class="container">
		<c:choose>
		    <c:when test="${param.exceptionMessage eq 'noSuchUser'}">
		        <c:out value="${no_such_user}" />
		    </c:when>
		     <c:when test="${param.exceptionMessage eq 'serverException'}">
		        <c:out value="${server_exception}" />
		    </c:when>
		     <c:when test="${param.exceptionMessage eq 'uncorrectData'}">
		        <c:out value="${uncorrect_data}" />
		    </c:when>
	    </c:choose>

		<form action="Controller" method="post">
			<input type="hidden" name="command" value="entrance" />
			<h1><c:out value="${login}" /></h1>
			<div class="dws-input">
			<input type="text" name="login" value="" required/>
			</div>
		
			<c:out value="${password}" /><br/>
			<div class="dws-input">
				<input type="password" name="password" value="" required />
			</div>	
			<div class="button"><input type="submit" name="entrance" value="${entrance}" />	</div>
		</form>


		<a href="Controller?command=go_to_registration_page"><c:out value="${registration}" /></a>
	</div>
</c:if>
	
	<div class="services-photo">
		<img src="images/education.jpg">    
	</div>	
	

	<tag:MyTag/>
</body>

<%@include file="pageElement/footer.jsp"%>