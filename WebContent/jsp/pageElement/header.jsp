<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<title>Education</title>
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet" href="css/form-style.css">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.locbutton.en" var="en_button" />
<fmt:message bundle="${loc}" key="local.locbutton.ru" var="ru_button" />

<fmt:message bundle="${loc}" key="local.exit" var="exit" />

<fmt:message bundle="${loc}" key="local.mainPage" var="mainPage" />
<fmt:message bundle="${loc}" key="local.about" var="about" />
<fmt:message bundle="${loc}" key="local.courses" var="courses" />
<fmt:message bundle="${loc}" key="local.myPage" var="myPage" />
<fmt:message bundle="${loc}" key="local.myCourses" var="myCourses" />
<fmt:message bundle="${loc}" key="local.myResults" var="myResults" />

<fmt:message bundle="${loc}" key="local.teachers" var="teachers" />
<fmt:message bundle="${loc}" key="local.students" var="students" />

</head>

<header>
	<div class="header">
		<div class="section-inner">
			<div class="logo">	<c:out value="${message}" /></div>
			<div class = "localization">
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="localization" />	
					<input type="hidden" name="local" value="ru" />	
					<input type="submit" value="${ru_button}" />
				</form>
	
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="localization" />	
					<input type="hidden" name="local" value="en" />	
					<input type="submit" value="${en_button}" />
				</form>
				
				<c:if test="${sessionScope.user ne null}">
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="exit" />
					<input type="submit" value="${exit}" />
				</form>
				</c:if>
			</div>			
		</div>	
	</div>

	<!-- nav -->
	<nav class="menu">
		<div class="section-inner">
			<ul>
				<li><a href="Controller?command=go_to_main_page"><c:out value="${mainPage}" /></a></li>
				<li><a href="Controller?command=go_to_courses_page"><c:out value="${courses}" /></a></li>
				<li><a href="Controller?command=go_to_about_page"><c:out value="${about}" /></a></li>
				
				<c:if test="${sessionScope.user ne null}">
					<li><a href="Controller?command=go_to_user_page"><c:out value="${myPage}" /></a></li>
					<c:if test="${user.role eq 'преподаватель'}">
						<li><a href="Controller?command=go_to_user_courses_page"><c:out value="${myCourses}" /></a></li>
					</c:if>
					
					<c:if test="${user.role eq 'студент'}">	
						<li><a href="Controller?command=go_to_user_courses_page"><c:out value="${myCourses}" /></a></li>
						<li><a href="Controller?command=go_to_student_courses_results_page"><c:out value="${myResults}" /></a></li>
					</c:if>	
					<c:if test="${user.role eq 'администратор'}">	
						<li><a href="Controller?command=go_to_all_teachers_page"><c:out value="${teachers}" /></a></li>
						<li><a href="Controller?command=go_to_all_students_page"><c:out value="${students}" /></a></li>
					</c:if>	
				</c:if>
			</ul>
		</div>
	</nav><!--/nav-->
</header><!--/header-->