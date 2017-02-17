package com.coffemachine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coffemachine.module.Station;
import com.coffemachine.services.StationService;


@RestController
//@RequestMapping("/coffemachine")
public class StationController {
	
	@Autowired
	StationService stationService;
	
	@RequestMapping("/stations")
	public List<Station> getAllStations(){
		return stationService.getAllStations();
	}
	
	@RequestMapping("/stations/{id}")
	public Station getStation(@PathVariable Long id){
		return stationService.getStation(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="stations/add")
	public void addStation(@RequestBody Station station){
		stationService.addStation(station);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "stations/delete")
	public void deleteStation(@PathVariable Long id){
		stationService.deleteStation(id);
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "stations/update")
	public void updateStation(@RequestBody Station station){
		stationService.updateStation(station);
	}

}
