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
<fmt:message bundle="${loc}" key="local.addCourse" var="addCourse" />

<body>
	<jsp:useBean id="course" class="by.epam.lobanok.entity.Course" scope="request" />
	
	<div class="container">
		<div class="form-data">
			
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="add_running_course" />
				<input type="hidden" name="courseID" value="${course.id}"/>
				
				<div class="row">
					<div class="col-25"><c:out value="${courseName}" /></div>
					<div class="col-75"><input type="text" name="courseName" value="${course.courseName}" readonly/></div>
				</div>
				
				<div class="row">
					<div class="col-25"><c:out value="${courseDescription}" /></div>
					<div class="col-75"><input type="text" name="courseDescription" value="${course.description}" readonly/></div>
				</div>
				
				<div class="row">
					<div class="col-25"><c:out value="${teacher}" /></div>
					<select name="teacherID">
						<c:forEach items="${requestScope.teachers}" var="teacher">  
  							<option value="${teacher.id}">${teacher.name} ${teacher.surname} </option>  
						</c:forEach>						
					</select>
				</div>
				
				<div class="row">
					<div class="col-25"><c:out value="${start}" /></div>
					<div class="col-75"><input type="date" name="start" value="" required/></div>
				</div>
				
				<div class="row">
					<div class="col-25"><c:out value="${end}" /></div>
					<div class="col-75"><input type="date" name="end" required/></div>
				</div>
				
				<div class="row">
					<div class="col-25"><c:out value="${passing}" /></div>
					<select name="passing">
  						<option value="контрольная">контрольная</option> 
						<option value="экзамен">экзамен</option> 			
					</select>
				</div>	
				
				<div class="row">
					<input type="submit" name="add course" value="${addCourse}" />
				</div>
			</form>
		</div>
	</div>
	
</body>

<%@include file="../../../jsp/pageElement/footer.jsp"%>