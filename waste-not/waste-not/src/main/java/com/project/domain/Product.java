package com.project.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@Entity
@Table(name = "products")
@EqualsAndHashCode(exclude = "id")
@ToString
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@JsonView(JsonViews.Public.class)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "product_name", nullable = false, length = 50)
	@JsonView(JsonViews.Public.class)
	private String productName;

	@Column(name = "product_category", nullable = true, length = 50)
	@JsonView(JsonViews.Public.class)
	@Enumerated(EnumType.STRING)
	private ProductCategory productCategory;

	@Column(name = "date_posted", nullable = true)
	@JsonView(JsonViews.Public.class)
	private LocalDate datePosted;

	@Column(name = "description", nullable = true, length = 100)
	@JsonView(JsonViews.Public.class)
	private String description;

	@Column(name = "product_image_url", nullable = true, length = 250)
	@JsonView(JsonViews.Public.class)
	private String productImageUrl;

	@Column(name = "pickup_location", nullable = true, length = 250)
	@JsonView(JsonViews.Public.class)
	private String pickupLocation;

	@Column(name = "pickup_time", nullable = true, length = 250)
	@JsonView(JsonViews.Public.class)
	private String pickupTime;

	@JsonView(JsonViews.Public.class)
	@OneToOne(optional = true)
	private Location location;

	@ManyToOne(optional = true)
	@JsonView(JsonViews.Public.class)
	@JsonIgnoreProperties({ "productsPosted", "productsShared", "productsRecieved", "productsRequestedByUser",
			"productsRequestedByOthers", "friends", "location", "wishList", "groups", "friendsRequests",
			"groupRequests" })
	private User productOwner;

	@Column(name = "available", nullable = true)
	@JsonView(JsonViews.Public.class)
	private boolean available;

	// List of Users who have Requested for this product
	@ManyToMany
	@JsonView(JsonViews.Public.class)
	@Column(name = "request_list")
	@JsonIgnoreProperties({ "productsPosted", "productsShared", "productsRecieved", "productsRequestedByUser",
			"productsRequestedByOthers", "friends", "location", "wishList", "groups", "friendsRequests",
			"groupRequests" })
	private List<User> requestList = new ArrayList<>();

	public Product() {

	}

	public Product(String productName, ProductCategory productCategory, LocalDate datePosted, String description,
			String productImageUrl, String pickupLocation, String pickupTime, Location location, boolean available,
			List<User> requestList) {
		super();
		this.productName = productName;
		this.productCategory = productCategory;
		this.datePosted = datePosted;
		this.description = description;
		this.productImageUrl = productImageUrl;
		this.pickupLocation = pickupLocation;
		this.pickupTime = pickupTime;
		this.location = location;
		this.available = available;
		this.requestList = requestList;
	}

}
