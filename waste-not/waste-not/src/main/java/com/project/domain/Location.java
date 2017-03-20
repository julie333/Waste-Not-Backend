package com.project.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode(exclude = "id")
@Entity
@Table(name = "location")

public class Location  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@JsonView(JsonViews.Public.class)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="street", nullable = false, length = 50)
	@JsonView(JsonViews.Public.class)
	private String street;
	
	@Column(name="streetno",nullable = false, length = 50)
	@JsonView(JsonViews.Public.class)
	private String streetno;
	
	@Column(name="postalcode",nullable = false, length = 50)
	@JsonView(JsonViews.Public.class)
	private String postalCode;
	
	@Column(name="city",nullable = false, length = 50)
	@JsonView(JsonViews.Public.class)
	private String city;
	
	@Column(name="country",nullable = false, length = 50)
	@JsonView(JsonViews.Public.class)
	private String country;
	
	@Column(name="latitude",nullable = true, length = 50)
	@JsonView(JsonViews.Public.class)
	private String latitude;
	
	@Column(name="longitude",nullable = true, length = 50)
	@JsonView(JsonViews.Public.class)
	private String longitude;

	public Location() {
	}
	
	public Location(String street, String streetno, String postalCode, String city, String country, String latitude,
			String longitude) {
		super();
		this.street = street;
		this.streetno = streetno;
		this.postalCode = postalCode;
		this.city = city;
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
}
	