<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html ng-app="myApp">
<head>
<title>Coffee Machine</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="<c:url value='css/app.css' />" rel="stylesheet"></link>
<base href="/" />
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"> </a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a href="/home">Home</a></li>
					<li><a href="/items">Items</a></li>
					<li><a href="/users">Users</a></li>
					<li><a href="/cards">Cards</a></li>
					<li><a href="/stations">Stations</a></li>
					<li><a href="/purchases">Purchases</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
							Login</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div>
	<ng-view></ng-view>
	</div>
	
	<script src="<c:url value='js/angular.js' />"></script>
	<script src="<c:url value='js/angular-route.js' />"></script>
	<script src="<c:url value='js/app.js' />"></script>
	<script src="<c:url value='js/controller/home_controller.js' />"></script>
	<script src="<c:url value='js/service/item_service.js' />"></script>
	<script src="<c:url value='js/controller/item_controller.js' />"></script>
	<script src="<c:url value='js/service/station_service.js' />"></script>
	<script src="<c:url value='js/controller/station_controller.js' />"></script>
	<script src="<c:url value='js/service/user_service.js' />"></script>
	<script src="<c:url value='js/controller/user_controller.js' />"></script>
	<script src="<c:url value='js/service/card_service.js' />"></script>
	<script src="<c:url value='js/controller/card_controller.js' />"></script>
	
</body>
</html>