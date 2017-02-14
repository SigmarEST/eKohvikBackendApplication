<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>Coffemachine</title>  
    <style>
      .name.ng-valid {
          background-color: lightgreen;
      }
      .name.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .name.ng-dirty.ng-invalid-minlength {
          background-color: yellow;
      }

      .price.ng-valid {
          background-color: lightgreen;
      }
      .price.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .price.ng-dirty.ng-invalid-price {
          background-color: yellow;
      }

    </style>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link href="<c:url value='css/app.css' />" rel="stylesheet"></link>
  </head>
  <body ng-app="myApp" class="ng-cloak">
  <header>
			<nav class="navbar navbar-default">
				<div class = "container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">Coffe Machine</a>
				</div>
				<p class="navbar-text navbar-right" th:text="'Signed in as ' + ${username}" style="margin-right : 10px"> Signed as Anonimous</p>
				</div>
			</nav>
		</header>
      <div class="generic-container" ng-controller="ItemController as ctrl">
              <div class="panel-heading"><span class="lead">Item Registration Form </span></div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                      <input type="hidden" ng-model="ctrl.item.itemId" />
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Name</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.item.name" name="iname" class="name form-control input-sm" placeholder="Enter item name" required ng-minlength="3"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.iname.$error.required">This is a required field</span>
                                      <span ng-show="myForm.iname.$error.minlength">Minimum length required is 3</span>
                                      <span ng-show="myForm.iname.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Price</label>
                              <div class="col-md-7">
                                  <input type="number" ng-model="ctrl.item.price" name="price" class="price form-control input-sm" placeholder="Enter item price" required/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.price.$error.required">This is a required field</span>
                                      <span ng-show="myForm.price.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
                      
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Available</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.item.available" class="form-control input-sm" placeholder="Enter item avialability. [This field is validation free]"/>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.item.itemId ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                          </div>
                      </div>
                  </form>
              </div>
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of Items </span></div>
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
                              <button type="button" ng-click="ctrl.edit(u.itemId)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl.remove(u.itemId)" class="btn btn-danger custom-width">Remove</button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
          </div>
      <footer class="footer" style="position : absolute; bottom:0; background-color: #f5f5f5">
			<div class = "container">
				<h2>This is footer</h2>
			</div>
		</footer>
      
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="<c:url value='js/app.js' />"></script>
      <script src="<c:url value='js/service/item_service.js' />"></script>
      <script src="<c:url value='js/controller/item_controller.js' />"></script>
  </body>
</html>