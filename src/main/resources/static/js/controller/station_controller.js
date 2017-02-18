'use strict';

App.controller('StationController', ['$scope', 'StationService', function($scope, StationService) {
          var self = this;
          self.station={stationId:null, address:''};
          self.stations=[];
              
          self.fetchAllStations = function(){
              StationService.fetchAllStations()
                  .then(
      					       function(d) {
      						        self.stations = d;
      					       },
            					function(errResponse){
            						console.error('Error while fetching Stations');
            					}
      			       );
          };
           
          self.createStation = function(station){
              StationService.createStation(station)
		              .then(
                          self.fetchAllStations, 
                          function(errResponse){
					               console.error('Error while creating Station.');
				              }	
                  );
          };
          
          self.fetchAllStations();

         self.updateStation = function(station, stationId){
              StationService.updateStation(station, stationId)
		              .then(
				              self.fetchAllStations, 
				              function(errResponse){
					               console.error('Error while updating station.');
				              }	
                  );
          };
          
          self.fetchAllStations();

         self.deleteStation = function(stationId){
              StationService.deleteStation(stationId)
		              .then(
				              self.fetchAllStations, 
				              function(errResponse){
					               console.error('Error while deleting Station.');
				              }	
                  );
          };

          self.fetchAllStations();

          self.submit = function() {
              if(self.station.stationId==null){
                  console.log('Saving New Station', self.station);    
                  self.createStation(self.station);
              }else{
                  self.updateStation(self.station, self.station.stationId);
                  console.log('Station updated with id ', self.station.stationId);
              }
              self.reset();
          };
              
          self.edit = function(stationId){
              console.log('id to be edited', stationId);
              for(var i = 0; i < self.stations.length; i++){
                  if(self.stations[i].stationId == stationId) {
                     self.station = angular.copy(self.stations[i]);
                     break;
                  }
              }
          };
              
          self.remove = function(stationId){
              console.log('id to be deleted', stationId);
              if(self.station.stationId === stationId) {//clean form if the item to be deleted is shown there.
                 self.reset();
              }
              self.deleteStation(stationId);
          };

          
          self.reset = function(){
              self.station={stationId:null, address:''};
              $scope.myForm.$setPristine(); //reset Form
          };

      }]);
