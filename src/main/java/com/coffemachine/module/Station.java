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

	public Station() {
		super();
	}

	public Station(Long stationId, String address) {
		super();
		this.stationId = stationId;
		this.address = address;
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

}
