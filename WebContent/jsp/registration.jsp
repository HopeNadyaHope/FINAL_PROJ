<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="pageElement/header.jsp"%>

<fmt:message bundle="${loc}" key="local.login" var="login" />
<fmt:message bundle="${loc}" key="local.password" var="password" />
<fmt:message bundle="${loc}" key="local.name" var="name" />
<fmt:message bundle="${loc}" key="local.surname" var="surname" />
<fmt:message bundle="${loc}" key="local.age" var="age" />

<fmt:message bundle="${loc}" key="local.sex" var="sex" />
<fmt:message bundle="${loc}" key="local.male" var="male" />
<fmt:message bundle="${loc}" key="local.female" var="female" />

<fmt:message bundle="${loc}" key="local.email" var="email" />

<fmt:message bundle="${loc}" key="local.role" var="role" />
<fmt:message bundle="${loc}" key="local.student" var="student" />
<fmt:message bundle="${loc}" key="local.teacher" var="teacher" />

<fmt:message bundle="${loc}" key="local.registration" var="registration" />

<body>
	
	<div class="container">
	<div class="form-data">
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="registration" />
		
		<c:if test="${requestScope.exceptionMessage ne null}">
			<c:out value="${requestScope.exceptionMessage}" />
		</c:if>
		
		<div class="row">
      		<div class="col-25"><c:out value="${login}" /></div>
			<div class="col-75"><input type="text" name="login" value="" required/></div>
		</div>
				
		<div class="row">
			<div class="col-25"><c:out value="${password}" /></div>
			<div class="col-75"><input type="password" name="password" value="" required/></div>
		</div>
		
		<div class="row">
			<div class="col-25"><c:out value="${name}" /></div>
			<div class="col-75"><input type="text" name="name" value="" required/></div>
		</div>
		
		<div class="row">
			<div class="col-25"><c:out value="${surname}" /></div>
			<div class="col-75"><input type="text" name="surname" value="" required/></div>
		</div>
		
		<div class="row">
			<div class="col-25"><c:out value="${age}" /></div>
			<div class="col-75"><input type="number" min="14" max="100" step="1" 
			name="age" value="" placeholder="14-100" required/></div>	
		</div>
		
		<div class="row">
			<div class="col-25"><c:out value="${sex}" /></div>
			<input type="radio" name="sex" value="муж"><c:out value="${male}" />
			<input type="radio" name="sex" value="жен"><c:out value="${female}" /><br />		
		</div>
		
		<div class="row">
			<div class="col-25"><c:out value="${email}" /></div>
			<div class="col-75"><input type="text" name="email" value="" 
			required pattern="^[.a-z0-9_-]+@[a-z0-9-]+\.[a-zA-Z]{2,6}$" placeholder="example@email.com"/></div>	
		</div>
		
		<div class="row">
			<div class="col-25"><c:out value="${role}" /></div>
			<input type="radio" name="role" value="студент"><c:out value="${student}" />
			<input type="radio" name="role" value="преподаватель"><c:out value="${teacher}" />			
		</div>
			
		<div class="row">
			<input type="submit" name="redistration" value="${registration}" />
		</div>
	</form>
	</div>		
	</div>
</body>

<%@include file="pageElement/footer.jsp"%>