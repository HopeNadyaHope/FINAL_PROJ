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
<fmt:message bundle="${loc}" key="local.uploadPhoto" var="uploadPhoto" />


<fmt:message bundle="${loc}" key="local.welcome" var="welcome" />

<body>
	<c:out value="${welcome}" /><br />	
	<jsp:useBean id="user" class="by.epam.lobanok.entity.User" scope="session" />

	<div class="container">
	
	
		
		<c:if test="${user.photoURL ne null}">
			<img src="${user.photoURL}"> 
		</c:if>
		<c:if test="${user.photoURL eq null}">		
			<c:if test="${user.sex eq 'жен'}"> 
	          <img src="images/userPhoto/female.jpg"> 
			</c:if> 
			   	
	    	<c:if test="${user.sex eq 'муж'}">	
	           <img src="images/userPhoto/men.jpg">   
	    	</c:if>
    	</c:if>

    
	<br />	
	
	<form action="Controller" method="post" enctype="multipart/form-data">
		<input type="hidden" name="command" value="upload_photo" />
		<input type="file" name="file"  multiple accept = "image/*" />
		<input type="submit" name="upload_photo" value="${uploadPhoto}" />
	</form>
	
	
	
	<c:out value="${name} : " /><c:out value="${user.name}" /><br />
	<c:out value="${surname} : " /><c:out value="${user.surname}" /><br />
	<c:out value="${age} : " /><c:out value="${user.age}" /><br />
	<c:out value="${sex} : " /><c:out value="${user.sex}" /><br />
	<c:out value="${role} : " /><c:out value="${user.role}" /><br />
	<c:out value="${email} : " />	<c:out value="${user.email}" /><br />
	
	<a href="Controller?command=go_to_edit_profile"><c:out value="${edit}" /></a>
</div>
</body>

<%@include file="../../jsp/pageElement/footer.jsp"%>