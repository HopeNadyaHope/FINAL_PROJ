<%@ page language="java"  import="by.epam.lobanok.entity.User" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../../jsp/pageElement/header.jsp"%>

<fmt:message bundle="${loc}" key="local.name" var="name" />
<fmt:message bundle="${loc}" key="local.surname" var="surname" />
<fmt:message bundle="${loc}" key="local.age" var="age" />
<fmt:message bundle="${loc}" key="local.sex" var="sex" />
<fmt:message bundle="${loc}" key="local.role" var="role" />
<fmt:message bundle="${loc}" key="local.email" var="email" />

<fmt:message bundle="${loc}" key="local.edit" var="edit" />

<body>
	<jsp:useBean id="user" class="by.epam.lobanok.entity.User" scope="session" />
	<div class="container">
	<div class="form-data">
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="edit_profile" />
				
		<div class="row">
			<div class="col-25"><c:out value="${name}" /></div>
			<div class="col-75"><input type="text" name="name" value="${user.name}" required/></div>
		</div>
		
		<div class="row">
			<div class="col-25"><c:out value="${surname}" /></div>
			<div class="col-75"><input type="text" name="surname" value="${user.surname}" required/></div>
		</div>
		
		<div class="row">
			<div class="col-25"><c:out value="${age}" /></div>
			<div class="col-75"><input type="number" name="age" value="${user.age}" readonly/></div>	
		</div>
		
		<div class="row">
			<div class="col-25"><c:out value="${sex}" /></div>
			<div class="col-75"><input type="text" name="sex" value="${user.sex}" readonly/></div>		
		</div>
		
		<div class="row">
			<div class="col-25"><c:out value="${email}" /></div>
			<div class="col-75"><input type="text" name="email" value="${user.email}" 
			required pattern="^[.a-z0-9_-]+@[a-z0-9-]+\.[a-zA-Z]{2,6}$" placeholder="example@email.com"/></div>	
		</div>
		
		<div class="row">
			<div class="col-25"><c:out value="${role}" /></div>
			<div class="col-75"><input type="text" name="role" value="${user.role}" readonly/></div>	
		</div>
			
		<div class="row">
			<input type="submit" name="edit" value="${edit}" />
		</div>
	</form>
	</div>
	</div>
	
</body>

<%@include file="../../jsp/pageElement/footer.jsp"%>