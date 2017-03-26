package com.project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
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
@Table(name = "users")
@EqualsAndHashCode(exclude = "id")
@ToString(exclude = { "password", "productsPosted", "productsShared", "productsRecieved", "productsRequestedByUser",
		"productsRequestedByOthers", "friends", "location", "groups" })

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@JsonView(JsonViews.Public.class)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "first_name", nullable = false, length = 50)
	@JsonView(JsonViews.Public.class)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 50)
	@JsonView(JsonViews.Public.class)
	private String lastName;

	@Column(name = "email", nullable = false, length = 50)
	@JsonView(JsonViews.Public.class)
	private String email;

	@JsonView(JsonViews.Public.class)
	@Column(name = "username", nullable = false, length = 50)
	private String username;

	@Column(name = "password", nullable = false, length = 76)
	private String password;

	@JsonView(JsonViews.Public.class)
	@Column(name = "avatar", nullable = true, length = 250)
	private String avatar;

	@JsonView(JsonViews.Public.class)
	@OneToOne
	private Location location;

	// Lists
	@JsonView(JsonViews.Public.class)
	@ElementCollection
	@Column(name = "wish_list")
	private List<String> wishList;

	@OneToMany(mappedBy = "productOwner",orphanRemoval=true)
	@OrderBy("datePosted")
	@JsonView(JsonViews.Public.class)
	private List<Product> productsPosted = new ArrayList<>();

//	When Products are successfully given away - Add To Products Shared
	@OneToMany
	@OrderBy("datePosted")
	@JsonView(JsonViews.Public.class)
	private List<Product> productsShared = new ArrayList<>();

//	When Products are successfully accepted - Add To Products Received
	@OneToMany
	@OrderBy("datePosted")
	@JsonView(JsonViews.Public.class)
	private List<Product> productsRecieved = new ArrayList<>();

	@ManyToMany
	@OrderBy("datePosted")
	@JsonView(JsonViews.Public.class)
	private List<Product> productsRequestedByUser = new ArrayList<>();

	@ManyToMany
	@OrderBy("datePosted")
	@JsonView(JsonViews.Public.class)
	private List<Product> productsRequestedByOthers = new ArrayList<>();

	@ManyToMany
	@JsonView(JsonViews.Public.class)
	@JsonIgnoreProperties({ "email", "productsPosted", "productsShared", "productsRecieved", "productsRequestedByUser",
			"productsRequestedByOthers", "friends", "location","groups"})
	private List<User> friends = new ArrayList<>();

	@ManyToMany
	@JsonView(JsonViews.Public.class)
	@JsonIgnoreProperties({ "members" })
	private List<Group> groups = new ArrayList<>();

	public User() {
	}

	public User(String firstName, String lastName, String email, String username, String password, String avatar,
			Location location, List<Product> productsPosted, List<Product> productsShared,
			List<Product> productsRecieved, List<Product> productsRequestedByUser,
			List<Product> productsRequestedByOthers, List<User> friends, List<Group> groups) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.avatar = avatar;
		this.location = location;
		this.productsPosted = productsPosted;
		this.productsShared = productsShared;
		this.productsRecieved = productsRecieved;
		this.productsRequestedByUser = productsRequestedByUser;
		this.productsRequestedByOthers = productsRequestedByOthers;
		this.friends = friends;
		this.groups = groups;
	}

}
