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
	<div ui-view="nav"></div>	
	<div>
	<ng-view></ng-view>
	</div>
	
	<script src="app/angular.js' />"></script>
	<script src="app/angular-route.js' />"></script>
	<script src="app/app.js' />"></script>
	<script type="text/javascript" src="app/route.js"></script>
	
	
	<script src="app/service/auth-service.js"></script>
	<script src="app/service/item_service.js' />"></script>
	<script src="app/service/station_service.js' />"></script>
	<script src="app/service/user_service.js' />"></script>
	<script src="app/service/card_service.js' />"></script>
	<script src="app/service/purchase_service.js' />"></script>

	<!-- Including controllers -->
	<script  src="app/controller/nav.js"></script>
	<script  src="app/controller/login.js"></script>
	<script  src="app/controller/page-not-found.js"></script>
	<script src="app/controller/access-denied.js"></script>
	<script src="app/controller/home_controller.js' />"></script>
	<script src="app/controller/item_controller.js' />"></script>
	<script src="app/controller/station_controller.js' />"></script>
	<script src="app/controller/user_controller.js' />"></script>
	<script src="app/controller/card_controller.js' />"></script>
	<script src="app/controller/purchase_controller.js' />"></script>
	
</body>
</html>