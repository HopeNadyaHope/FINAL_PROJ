<%@ page language="java"  import="by.epam.lobanok.entity.User" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../../../jsp/pageElement/header.jsp"%>

<fmt:message bundle="${loc}" key="local.courseName" var="courseName" />
<fmt:message bundle="${loc}" key="local.courseDescription" var="courseDescription" />
<fmt:message bundle="${loc}" key="local.teacher" var="teacher" />
<fmt:message bundle="${loc}" key="local.passing" var="passing" />
<fmt:message bundle="${loc}" key="local.start" var="start" />
<fmt:message bundle="${loc}" key="local.end" var="end" />
<fmt:message bundle="${loc}" key="local.edit" var="edit" />

<body>
	<jsp:useBean id="runningCourse" class="by.epam.lobanok.entity.RunningCourse" scope="request" />
	<div class="container">
		<div class="form-data">
			
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="edit_running_course" />
				<input type="hidden" name="runningCourseID" value="${runningCourse.id}"/>	
				<c:out value="${runningCourse.id}" />			
				<input type="hidden" name="courseID" value="${runningCourse.course.id}"/>
				<c:out value="${runningCourse.course.id}" />
				
				<div class="row">
					<div class="col-25"><c:out value="${courseName}" /></div>
					<div class="col-75"><input type="text" name="courseName" value="${runningCourse.course.courseName}" readonly/></div>
				</div>
				
				<div class="row">
					<div class="col-25"><c:out value="${courseDescription}" /></div>
					<div class="col-75"><input type="text" name="courseDescription" value="${runningCourse.course.description}" readonly/></div>
				</div>
				
				<div class="row">
					<div class="col-25"><c:out value="${teacher}" /></div>
					<select name="teacherID">
						<c:forEach items="${requestScope.teachers}" var="teacher">
							<c:if test="${teacher.id eq runningCourse.teacher.id}">  
  								<option value="${teacher.id}" selected>${teacher.name} ${teacher.surname} </option> 
  							</c:if> 
  							
  							<c:if test="${teacher.id ne runningCourse.teacher.id}">  
  								<option value="${teacher.id}">${teacher.name} ${teacher.surname} </option> 
  							</c:if> 							
  							
						</c:forEach>						
					</select>
				</div>
				
				<div class="row">
					<div class="col-25"><c:out value="${start}" /></div>
					<div class="col-75"><input type="date" name="start" value="${runningCourse.start}" required/></div>
				</div>
				
				<div class="row">
					<div class="col-25"><c:out value="${end}" /></div>
					<div class="col-75"><input type="date" name="end" value="${runningCourse.end}" required/></div>
				</div>
				
				<div class="row">
					<div class="col-25"><c:out value="${passing}" /></div>
					<select name="passing">
  						<option value="контрольная">контрольная</option> 
						<option value="экзамен">экзамен</option> 			
					</select>
				</div>	
				
				<div class="row">
					<input type="submit" name="edit" value="${edit}" />
				</div>
			</form>
		</div>
	</div>
	
</body>

<%@include file="../../../jsp/pageElement/footer.jsp"%>