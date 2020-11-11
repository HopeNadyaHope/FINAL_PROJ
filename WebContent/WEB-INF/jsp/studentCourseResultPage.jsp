<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../../jsp/pageElement/header.jsp"%>

<fmt:message bundle="${loc}" key="local.courseName" var="courseName" />
<fmt:message bundle="${loc}" key="local.courseDescription" var="courseDescription" />
<fmt:message bundle="${loc}" key="local.teacher" var="teacher" />
<fmt:message bundle="${loc}" key="local.passing" var="passing" />

<fmt:message bundle="${loc}" key="local.rating" var="rating" />
<fmt:message bundle="${loc}" key="local.review" var="review" />


<fmt:message bundle="${loc}" key="local.already_on_course" var="already_on_course" />
<fmt:message bundle="${loc}" key="local.added_to_course" var="added_to_course" />

<fmt:message bundle="${loc}" key="local.unfollow" var="unfollow" />

<body>
<c:choose>
	<c:when test="${param.message eq 'already'}">
		<c:out value="${already_on_course}" />
	</c:when>
	<c:when test="${param.message eq 'added'}">
		<c:out value="${added_to_course}" />
	</c:when>
</c:choose></b>

<jsp:useBean id="runningCourse" class="by.epam.lobanok.entity.RunningCourse" scope="request" />
	<c:out value="${runningCourse.course.courseName}" /></br>
	<c:out value="${runningCourse.course.description}" /></br>
	<c:out value="${runningCourse.teacher.name} ${runningCourse.teacher.surname}" /></br>
	<c:out value="${runningCourse.passing}" /></br>

	<table border = "1" width = "80%" align="center">
         <tr>
             <th><c:out value="${rating}" /></th>
           	<th><c:out value="${review}" /></th>
         </tr>
       
    
	<jsp:useBean id="courseResult" class="by.epam.lobanok.entity.Result" scope="request" />
	<c:if test="${courseResult eq null}">
	 	<td></td>
	 	<td></td>
	</c:if>
	
	<c:if test="${courseResult ne null}">
		<tr>
			<td><c:out value="${courseResult.rating}" /></td>
			<td><c:out value="${courseResult.review}" /></td>
  		</tr> 
  	</c:if> 
  	</table> 
  	
  	<form action="Controller" method="post">
		<input type="hidden" name="command" value="delete_course_participant" />	
		<input type="hidden" name="runningCourseID" value="${runningCourse.id}" />	
		<input type="submit" name="runningCourses" value="${unfollow}" /> 
	</form>

</body>

<%@include file="../../jsp/pageElement/footer.jsp"%>