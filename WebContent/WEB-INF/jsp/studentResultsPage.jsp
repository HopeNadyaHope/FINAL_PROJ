<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../../jsp/pageElement/header.jsp"%>

<fmt:message bundle="${loc}" key="local.no_results" var="no_results" />

<fmt:message bundle="${loc}" key="local.courseName" var="courseName" />
<fmt:message bundle="${loc}" key="local.courseDescription" var="courseDescription" />

<fmt:message bundle="${loc}" key="local.teacher" var="teacher" />
<fmt:message bundle="${loc}" key="local.passing" var="passing" />
<fmt:message bundle="${loc}" key="local.start" var="start" />
<fmt:message bundle="${loc}" key="local.end" var="end" />

<fmt:message bundle="${loc}" key="local.rating" var="rating" />
<fmt:message bundle="${loc}" key="local.review" var="review" />

<body>
	<c:if test="${requestScope.results eq null}">
    	<c:out value="${no_results}" />
    </c:if>
    
    <c:if test="${requestScope.results ne null}">
		<table border = "1" width = "80%" align="center">
	         <tr>
	           	<th><c:out value="${courseName}" /></th>
	           	<th><c:out value="${courseDescription}" /></th>
	           	<th><c:out value="${start}-${end}" /></th>
	           	<th><c:out value="${passing}" /></th>
	           	
	           	<th><c:out value="${teacher}" />
	           	
	           	<th><c:out value="${rating}" />
	           	<th><c:out value="${review}" />
	         </tr>
	
		<c:forEach items="${requestScope.results}" var="result">  
			<tr>
				<td><c:out value="${result.runningCourse.course.courseName}" /></td>
				<td><c:out value="${result.runningCourse.course.description}" /></td>
				<td><c:out value="${result.runningCourse.start} - ${result.runningCourse.end} " /></td>
				<td><c:out value="${result.runningCourse.passing}" /></td>
							
				<td><c:out value="${result.runningCourse.teacher.name} ${result.runningCourse.teacher.surname}" /></td>
				
				<td><c:out value="${result.result.rating}" /></td>
				<td><c:out value="${result.result.review}" /></td>
	  		</tr>  
		</c:forEach>
		</table>
	</c:if>
</body>

<%@include file="../../jsp/pageElement/footer.jsp"%>