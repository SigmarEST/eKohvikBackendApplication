package com.coffemachine.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.coffemachine.model.Station;
import com.coffemachine.services.StationService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api/station")
public class StationController {

	@Autowired
	StationService stationService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/")
	public ResponseEntity<List<Station>> getAllStations() {
		List<Station> stations = stationService.getAllStations();
		if (stations.isEmpty()) {
			return new ResponseEntity<List<Station>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Station>>(stations, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/{id}")
	public ResponseEntity<Station> getStation(@PathVariable Long id) {
		System.out.println("Fetching station with id " + id);
		Station station = stationService.getStation(id);
		if (station == null) {
			System.out.println("station with id " + id + " not found");
			return new ResponseEntity<Station>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Station>(station, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST, value = "/")
	public ResponseEntity<Void> addStation(@RequestBody Station station, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Station " + station.getUsername());

		if (stationService.findOneByUsername(station.getUsername()) != null) {
			System.out.println("A Station with name " + station.getUsername() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			String role = "USER";
			List<String> roles = new ArrayList<>();
			roles.add(role);
			station.setRoles(roles);
			System.out.println(station);
			stationService.addStation(station);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<Station> updateStation(@RequestBody Station station) {
		System.out.println("Updating Station " + station.getId());

		Station currentStation = stationService.getStation(station.getId());

		if (currentStation == null) {
			System.out.println("Station with id " + station.getId() + " not found");
			return new ResponseEntity<Station>(HttpStatus.NOT_FOUND);
		}

		currentStation.setAddress(station.getAddress());
		currentStation.setItems(station.getItems());

		stationService.updateStation(currentStation);
		return new ResponseEntity<Station>(currentStation, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
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
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> login(@RequestParam String username, @RequestParam String password,
			HttpServletResponse response) throws IOException {
		String token = null;
		Station station = stationService.findOneByUsername(username);
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		if (station != null && station.getPassword().equals(password)) {
			token = Jwts.builder().setSubject(username).claim("roles", station.getRoles()).setIssuedAt(new Date())
					.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
			tokenMap.put("token", token);
			tokenMap.put("user", station);
			return new ResponseEntity<Map<String, Object>>(tokenMap, HttpStatus.OK);
		} else {
			tokenMap.put("token", null);
			return new ResponseEntity<Map<String, Object>>(tokenMap, HttpStatus.UNAUTHORIZED);
		}

	}

}
