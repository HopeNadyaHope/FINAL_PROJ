<%@ page language="java"  import="by.epam.lobanok.entity.User" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../../../jsp/pageElement/header.jsp"%>

<fmt:message bundle="${loc}" key="local.courseName" var="courseName" />
<fmt:message bundle="${loc}" key="local.courseDescription" var="courseDescription" />
<fmt:message bundle="${loc}" key="local.addCourse" var="addCourse" />

<body>
	<jsp:useBean id="course" class="by.epam.lobanok.entity.Course" scope="request" />
	
	<div class="container">
		<div class="form-data">
			
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="add_course" />
				<input type="hidden" name="courseID" value="${course.id}"/>
				
				<div class="row">
					<div class="col-25"><c:out value="${courseName}" /></div>
					<div class="col-75"><input type="text" name="courseName" value="" required/></div>
				</div>
				
				<div class="row">
					<div class="col-25"><c:out value="${courseDescription}" /></div>
					<div class="col-75"><input type="text" name="courseDescription" value="" required/></div>
				</div>
				
				<div class="row">
					<input type="submit" name="add course" value="${addCourse}" />
				</div>
			</form>
		</div>
	</div>
	
</body>

<%@include file="../../../jsp/pageElement/footer.jsp"%>