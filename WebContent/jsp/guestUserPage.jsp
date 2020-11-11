<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="pageElement/header.jsp"%>

<fmt:message bundle="${loc}" key="local.name" var="name" />
<fmt:message bundle="${loc}" key="local.surname" var="surname" />
<fmt:message bundle="${loc}" key="local.age" var="age" />
<fmt:message bundle="${loc}" key="local.sex" var="sex" />
<fmt:message bundle="${loc}" key="local.role" var="role" />
<fmt:message bundle="${loc}" key="local.email" var="email" />

<body>
	<jsp:useBean id="user" class="by.epam.lobanok.entity.User" scope="request" />
	
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
	
	<c:out value="${name} : " /><c:out value="${user.name}" /><br />
	<c:out value="${surname} : " /><c:out value="${user.surname}" /><br />
	<c:out value="${age} : " /><c:out value="${user.age}" /><br />
	<c:out value="${sex} : " /><c:out value="${user.sex}" /><br />
	<c:out value="${role} : " /><c:out value="${user.role}" /><br />
	<c:out value="${email} : " />	<c:out value="${user.email}" /><br />

</div>
	
</body>

<%@include file="pageElement/footer.jsp"%>