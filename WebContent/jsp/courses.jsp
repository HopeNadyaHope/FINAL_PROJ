<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="pageElement/header.jsp"%>

<fmt:message bundle="${loc}" key="local.courseName" var="courseName" />
<fmt:message bundle="${loc}" key="local.courseDescription" var="courseDescription" />
<fmt:message bundle="${loc}" key="local.go_to" var="go_to" />
<fmt:message bundle="${loc}" key="local.edit" var="edit" />
<fmt:message bundle="${loc}" key="local.addCourse" var="addCourse" />

<body>
	<c:out value="${courses}" />
	<c:if test="${user.role eq 'администратор'}">	
		<a href="Controller?command=go_to_add_course_page"><c:out value="${addCourse}" /></a>
	</c:if>	
	
	<c:forEach items="${requestScope.courses}" var="course">  
		<div class="container">
		
		<form action="Controller" method="get">	
			<c:out value="${course.courseName}" /><br/>
			<c:out value="${course.description}" /><br/>			
			<a href="Controller?command=go_to_running_courses_page&courseID=${course.id}"><c:out value="${go_to}" /></a>
			
			<c:if test="${user.role eq 'администратор'}">	
				<a href="Controller?command=go_to_edit_course_page&courseID=${course.id}"><c:out value="${edit}" /></a>
			</c:if>			
		</form>			
		</div>
	</c:forEach>
	
	
</body>

<%@include file="pageElement/footer.jsp"%>