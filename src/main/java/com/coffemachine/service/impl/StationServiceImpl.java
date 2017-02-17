package com.coffemachine.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coffemachine.module.Station;
import com.coffemachine.repository.StationRepository;
import com.coffemachine.services.StationService;

@Service
public class StationServiceImpl implements StationService {
	
	@Autowired
	StationRepository stationRepository;
	
	public List<Station> getAllStations(){
		return (List<Station>) stationRepository.findAll();
	}
	
	
	public Station getStation(Long id){
		return stationRepository.findOne(id);
	}
	
	
	public void addStation(Station station){
		stationRepository.save(station);
	}
	
	public void deleteStation(Long id){
		stationRepository.delete(id);
	}
	
	public void updateStation(Station station){
		stationRepository.save(station);
	}

	public boolean isStationExist(Station station) {
		return (stationRepository.findByAddress(station.getAddress()))!=null;
	}

}
