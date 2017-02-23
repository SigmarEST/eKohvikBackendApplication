package com.coffemachine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.coffemachine.module.Station;
import com.coffemachine.services.StationService;

@RestController
// @RequestMapping("/coffemachine")
public class StationController {

	@Autowired
	StationService stationService;

	@RequestMapping("/stations")
	public ResponseEntity<List<Station>> getAllStations() {
		List<Station> stations = stationService.getAllStations();
		if (stations.isEmpty()) {
			return new ResponseEntity<List<Station>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Station>>(stations, HttpStatus.OK);
	}

	@RequestMapping("/stations/{id}")
	public ResponseEntity<Station> getStation(@PathVariable Long id) {
		System.out.println("Fetching station with id " + id);
		Station station = stationService.getStation(id);
		if (station == null) {
			System.out.println("station with id " + id + " not found");
			return new ResponseEntity<Station>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Station>(station, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/stations/add")
	public ResponseEntity<Void> addStation(@RequestBody Station station, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Station " + station.getAddress());

		if (stationService.isStationExist(station)) {
			System.out.println("A Station with name " + station.getAddress() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {

			stationService.addStation(station);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/stations/{id}").buildAndExpand(station.getStationId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
	}

	@RequestMapping(value = "/stations/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Station> updateStation(@PathVariable("id") Long id, @RequestBody Station station) {
		System.out.println("Updating Station " + id);

		Station currentStation = stationService.getStation(id);

		if (currentStation == null) {
			System.out.println("Station with id " + id + " not found");
			return new ResponseEntity<Station>(HttpStatus.NOT_FOUND);
		}

		currentStation.setAddress(station.getAddress());

		stationService.updateStation(currentStation);
		return new ResponseEntity<Station>(currentStation, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/stations/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Station> deleteStation(@PathVariable("id") Long id) {
		System.out.println("Fetching & Deleting station with id " + id);

		Station station = stationService.getStation(id);
		if (station == null) {
			System.out.println("Unable to delete. Station with id " + id + " not found");
			return new ResponseEntity<Station>(HttpStatus.NOT_FOUND);
		}

		stationService.deleteStation(id);
		return new ResponseEntity<Station>(HttpStatus.NO_CONTENT);
	}

}
