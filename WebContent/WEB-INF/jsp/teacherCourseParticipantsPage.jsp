<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../../jsp/pageElement/header.jsp"%>

<fmt:message bundle="${loc}" key="local.courseName" var="courseName" />
<fmt:message bundle="${loc}" key="local.courseDescription" var="courseDescription" />
<fmt:message bundle="${loc}" key="local.student" var="student" />
<fmt:message bundle="${loc}" key="local.passing" var="passing" />

<fmt:message bundle="${loc}" key="local.rating" var="rating" />
<fmt:message bundle="${loc}" key="local.review" var="review" />
<fmt:message bundle="${loc}" key="local.rate" var="rate" />

<body>

<jsp:useBean id="runningCourse" class="by.epam.lobanok.entity.RunningCourse" scope="request" />
	<c:out value="${runningCourse.course.courseName}" /></br>
	<c:out value="${runningCourse.course.description}" /></br>
	<c:out value="${runningCourse.teacher.name} ${runningCourse.teacher.surname}" /></br>
	<c:out value="${runningCourse.passing}" /></br>
		
	<table border = "1" width = "80%" align="center">
         <tr>
           	<th><c:out value="${student}" /></th>
           	<th><c:out value="${rating}" />
           	<th><c:out value="${review}" />
           	<th><c:out value="${rate}" />
         </tr>

	<c:forEach items="${requestScope.courseParticipants}" var="courseParticipants">  
		<tr>
			<td><a href="Controller?command=go_to_guest_user_page&userID=${courseParticipants.student.id}"><c:out value="${courseParticipants.student.name} ${courseParticipants.student.surname}" /></a></td>

			<c:choose>
				<c:when test="${courseParticipants.result.rating ne '0'}">
					<td><c:out value="${courseParticipants.result.rating}" /></td>
					<td><c:out value="${courseParticipants.result.review}" /></td>
					<td></td>
				</c:when>
				
				<c:when test="${courseParticipants.result.rating eq '0'}">
					<form action="Controller" method="get">
						<input type="hidden" name="command" value="add_course_result" />	
						<input type="hidden" name="runningCourseID" value="${runningCourse.id}" />
						<input type="hidden" name="courseParticipantID" value="${courseParticipants.id}" />
					
					
					<td><select name="rating">
  							<option value=1>1</option>
  							<option value=2>2</option>
  							<option value=3>3</option>
  							<option value=4>4</option>
  							<option value=5>5</option>
  							<option value=6>6</option>
  							<option value=7>7</option>
  							<option value=8>8</option>
  							<option value=9>9</option>
  							<option value=10>10</option>  						
						</select>
					</td>				
					
					<td><textarea name="review">Добавьте сюда свой комментарий</textarea></td>
					
					<td><input type="submit" name="runningCourses" value="${rate}" /></td>
					</form>
				</c:when>
			</c:choose>
			
  		</tr>  
	</c:forEach>
	</table>
</body>

<%@include file="../../jsp/pageElement/footer.jsp"%>