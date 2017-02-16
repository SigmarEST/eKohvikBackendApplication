<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Coffee Machine</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="<c:url value='css/app.css' />" rel="stylesheet"></link>

<style>
/* Remove the navbar's default margin-bottom and rounded borders */
.navbar {
	height: 15%;
	margin-bottom: 30px;
	border-radius: 0;
}

/* Set height of the grid so .sidenav can be 100% (adjust as needed) */
.row.content {
	height: 450px
}

.navbar-collapse {
	margin-top: 30px;
	font-size: 25px;
	margin-bottom: 30px;
	padding-bottom: 30px;
}

.navbar-nav {
	margin-left: 10%;
}

.navbar-brand {
	margin-left: 10% !important;
	display: inline !important;
}

.navbar-header {
	margin-top: 30px;
	font-size: 25px;
	margin-bottom: 30px;
	padding-bottom: 30px;
}

/* Set gray background color and 100% height */
.sidenav {
	padding-top: 20px;
	background-color: #f1f1f1;
	height: 100%;
}

.grey {
	height: 10%;
	background: light-grey;
}

/* Set black background color, white text and some padding */
.footer {
	background-color: #555;
	color: white;
	padding: 15px;
}

.formcontainer{
text-align: left;
font-size:15px;
}

.form-actions{
	margin-left:20%;
}

/* On small screens, set height to 'auto' for sidenav and grid */
@media screen and (max-width: 767px) {
	.sidenav {
		height: auto;
		padding: 15px;
	}
	.row.content {
		height: auto;
	}
}
</style>

</head>
<body ng-app="myApp">
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
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#">About</a></li>
					<li><a href="#">Projects</a></li>
					<li><a href="#">Contact</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
							Login</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container-fluid text-center">
		<div ng-controller="ItemController as ctrl">
			<div>
				<h1>Item Registration Form</h1>
				<br>
			</div>
			<div class="formcontainer">
				<form ng-submit="ctrl.submit()" name="myForm"
					class="form-horizontal">
					<input type="hidden" ng-model="ctrl.item.itemId" />

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Name</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.item.name" name="iname"
									placeholder="Enter item name" />
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Price</label>
							<div class="col-md-7">
								<input type="number" ng-model="ctrl.item.price" name="price"
									placeholder="Enter item price" />
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Available</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.item.available" />
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-actions">
							<input type="submit"
								value="{{!ctrl.item.itemId ? 'Add' : 'Update'}}"
								class="btn btn-primary btn-sm" />
							<button type="button" ng-click="ctrl.reset()"
								class="btn btn-warning btn-sm">ResetForm</button>
						</div>
					</div>
				</form>
			</div>
			<div class="grey">
				<h1>List of Items</h1>
			</div>
			<div class="panel panel-default">

				<div class="tablecontainer">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>ID.</th>
								<th>Name</th>
								<th>Price</th>
								<th>Available</th>
								<th width="20%"></th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="u in ctrl.items">
								<td><span ng-bind="u.itemId"></span></td>
								<td><span ng-bind="u.name"></span></td>
								<td><span ng-bind="u.price"></span></td>
								<td><span ng-bind="u.available"></span></td>
								<td>
									<button type="button" ng-click="ctrl.edit(u.itemId)"
										class="btn btn-success custom-width">Edit</button>
									<button type="button" ng-click="ctrl.remove(u.itemId)"
										class="btn btn-danger custom-width">Remove</button>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
	<script src="<c:url value='js/app.js' />"></script>
	<script src="<c:url value='js/service/item_service.js' />"></script>
	<script src="<c:url value='js/controller/item_controller.js' />"></script>
</body>
</html>