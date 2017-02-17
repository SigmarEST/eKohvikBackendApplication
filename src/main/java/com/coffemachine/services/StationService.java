package com.coffemachine.services;

import java.util.List;

import com.coffemachine.module.Station;

public interface StationService {
	
	public List<Station> getAllStations();
	
	
	public Station getStation(Long id);
	
	
	public void addStation(Station station);
	
	public void deleteStation(Long id);
	
	public void updateStation(Station station);


	public boolean isStationExist(Station station);

}
