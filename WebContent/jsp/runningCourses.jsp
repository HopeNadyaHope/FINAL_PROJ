<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="pageElement/header.jsp"%>

<fmt:message bundle="${loc}" key="local.courseName" var="courseName" />
<fmt:message bundle="${loc}" key="local.courseDescription" var="courseDescription" />
<fmt:message bundle="${loc}" key="local.teacher" var="teacher" />
<fmt:message bundle="${loc}" key="local.passing" var="passing" />
<fmt:message bundle="${loc}" key="local.start" var="start" />
<fmt:message bundle="${loc}" key="local.end" var="end" />

<fmt:message bundle="${loc}" key="local.follow" var="follow" />
<fmt:message bundle="${loc}" key="local.addCourse" var="addCourse" />
<fmt:message bundle="${loc}" key="local.edit" var="edit" />


<body>
	<c:if test="${user.role eq 'администратор'}">	
		<a href="Controller?command=go_to_add_running_course_page&courseID=${courseID}"><c:out value="${addCourse}" /></a>
	</c:if>	

	<table border = "1" width = "80%" align="center">
         <tr>
            <th><c:out value="${courseName}" /></th>
           	<th><c:out value="${courseDescription}" /></th>
           	<th><c:out value="${teacher}" />
           	<th><c:out value="${start}" />
           	<th><c:out value="${end}" />
           	<th><c:out value="${passing}" />
           	<c:if test="${sessionScope.user.role eq 'студент'}">
           		<th><c:out value="${follow}" /></th>           	
           	</c:if>
           	
         </tr>
    
	<c:forEach items="${requestScope.runningCourses}" var="runningCourse">  
		<tr>
			<td><c:out value="${runningCourse.course.courseName}" /></td>
			<td><c:out value="${runningCourse.course.description}" /></td>
			<td><a href="Controller?command=go_to_guest_user_page&userID=${runningCourse.teacher.id}"><c:out value="${runningCourse.teacher.name}  ${runningCourse.teacher.surname}" /></a></td>
			<td><c:out value="${runningCourse.start}" /></td>
			<td><c:out value="${runningCourse.end}" /></td>
			<td><c:out value="${runningCourse.passing}" /></td>
			
			<c:if test="${sessionScope.user.role eq 'студент'}">
	           	<td>
	           		<form action="Controller" method="post">
						<input type="hidden" name="command" value="add_course_participant" />	
						<input type="hidden" name="runningCourseID" value="${runningCourse.id}" />	
						<input type="submit" name="runningCourses" value="${follow}" /> 
					</form>	 
				</td>        	
           	</c:if>
           	
           	<c:if test="${user.role eq 'администратор'}">
           		<td>	
					<a href="Controller?command=go_to_edit_running_course_page&runningCourseID=${runningCourse.id}"><c:out value="${edit}" /></a>
				</td>
			</c:if>	
  		</tr>  
	</c:forEach>
	</table>
</body>

<%@include file="pageElement/footer.jsp"%>