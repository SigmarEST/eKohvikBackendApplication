package com.coffemachine.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.coffemachine.module.Station;
import com.coffemachine.repository.RoleRepository;
import com.coffemachine.repository.StationRepository;
import com.coffemachine.services.StationService;

@Service
public class StationServiceImpl implements StationService {
	
	@Autowired
	StationRepository stationRepository;
	@Autowired
    private RoleRepository roleRepository;
   // @Autowired
   // private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public List<Station> getAllStations(){
		return (List<Station>) stationRepository.findAll();
	}
	
	
	public Station getStation(Long id){
		return stationRepository.findOne(id);
	}
	
	
	public void addStation(Station station){
			//station.setPassword(bCryptPasswordEncoder.encode(station.getPassword()));
	       // Role userRole = roleRepository.findByRole("ADMIN");
	       // station.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
	        stationRepository.save(station);
	}
	
	public void deleteStation(Long id){
		stationRepository.delete(id);
	}
	
	public void updateStation(Station station){
		//station.setPassword(bCryptPasswordEncoder.encode(station.getPassword()));
       // Role userRole = roleRepository.findByRole("ADMIN");
       // station.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        stationRepository.save(station);
	}

	public boolean isStationExist(Station station) {
		return (stationRepository.findByAddress(station.getAddress()))!=null;
	}
	
	public Station findOneByUsername(String username){
		return stationRepository.findOneByUsername(username);
	}

}
