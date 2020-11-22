<%@ page language="java"  import="by.epam.lobanok.entity.User" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../../../jsp/pageElement/header.jsp"%>

<fmt:message bundle="${loc}" key="local.students" var="students" />
<fmt:message bundle="${loc}" key="local.name" var="name" />
<fmt:message bundle="${loc}" key="local.surname" var="surname" />
<fmt:message bundle="${loc}" key="local.age" var="age" />
<fmt:message bundle="${loc}" key="local.sex" var="sex" />
<fmt:message bundle="${loc}" key="local.email" var="email" />


<body>
	<c:out value = "${students}" />
	<table border = "1" width = "80%" align="center">
         <tr>
            <th><c:out value="${name} ${surname}" /></th>
           	<th><c:out value="${age}" /></th>
           	<th><c:out value="${sex}" /></th>
           	<th><c:out value="${email}" /></th>
         </tr>
		<c:forEach items="${requestScope.students}" var="student">
		<tr>
			<td><a href="Controller?command=go_to_guest_user_page&userID=${student.id}"><c:out value="${student.name} ${student.surname}" /></a></td>
			<td><c:out value="${student.age}" /></td>
			<td><c:out value="${student.sex}" /></td>
			<td><c:out value="${student.email}" /></td>
		</tr>
		</c:forEach>
	</table>
	
</body>

<%@include file="../../../jsp/pageElement/footer.jsp"%>