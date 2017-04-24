package com.coffemachine.module;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "station")
public class Station implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2441620538608413747L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long stationId;

	@NotNull
	private String address;
	
	@NotNull
	private String stationName;
	
	@NotNull
	private String stationSecret;
	

	public Station() {
		super();
	}

	public Station(Long stationId, String address, String stationName, String stationSecret) {
		super();
		this.stationId = stationId;
		this.address = address;
		this.stationName = stationName;
		this.stationSecret = stationSecret;
	}

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getStationSecret() {
		return stationSecret;
	}

	public void setStationSecret(String stationSecret) {
		this.stationSecret = stationSecret;
	}
	
	

}
