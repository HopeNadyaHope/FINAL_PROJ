<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../../jsp/pageElement/header.jsp"%>

<fmt:message bundle="${loc}" key="local.courseName" var="courseName" />
<fmt:message bundle="${loc}" key="local.courseDescription" var="courseDescription" />
<fmt:message bundle="${loc}" key="local.teacher" var="teacher" />
<fmt:message bundle="${loc}" key="local.passing" var="passing" />
<fmt:message bundle="${loc}" key="local.no_courses" var="no_courses" />
<fmt:message bundle="${loc}" key="local.go_to" var="go_to" />


<body>
    <jsp:useBean id="user" class="by.epam.lobanok.entity.User" scope="session" />
    <c:if test="${requestScope.runningCourses eq null}">
    	<c:out value="${no_courses}" />
    </c:if>
    
   	<c:if test="${requestScope.runningCourses ne null}">
	   	<table border = "1" width = "80%" align="center">
	         <tr>
	            <th><c:out value="${courseName}" /></th>
	           	<th><c:out value="${courseDescription}" /></th>
	           	<th><c:out value="${teacher}" />
	           	<th><c:out value="${passing}" />
	         </tr>
			<c:forEach items="${requestScope.runningCourses}" var="runningCourse">  
				<tr>
					<td><c:out value="${runningCourse.course.courseName}" /></td>
					<td><c:out value="${runningCourse.course.description}" /></td>
					<td><a href="Controller?command=go_to_guest_user_page&userID=${runningCourse.teacher.id}"><c:out value="${runningCourse.teacher.name}  ${runningCourse.teacher.surname}" /></a></td>
					<td><c:out value="${runningCourse.passing}" /></td>
					<td>
						
						<c:if test="${user.role eq 'студент'}">	 
						<form action="Controller" method="get">
							<input type="hidden" name="command" value="go_to_student_course_result_page" />	
							<input type="hidden" name="runningCourseID" value="${runningCourse.id}" />					
							<input type="submit" name="runningCourses" value="${go_to}" /> 
						</form>	
						</c:if>
						
						<c:if test="${user.role eq 'преподаватель'}">	 
						<form action="Controller" method="get">
							<input type="hidden" name="command" value="go_to_teacher_course_participants_page" />	
							<input type="hidden" name="runningCourseID" value="${runningCourse.id}" />
							<input type="submit" name="runningCourses" value="${go_to}" /> 
						</form>	
						</c:if>
						
						
					</td>
		  		</tr>  
			</c:forEach>
		</table>
	</c:if>

</body>

<%@include file="../../jsp/pageElement/footer.jsp"%>