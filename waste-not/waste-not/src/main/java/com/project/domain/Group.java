package com.project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "groups")
@EqualsAndHashCode(exclude = "id")
@ToString(exclude = { "members", "admin" })

public class Group implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@JsonView(JsonViews.Public.class)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "group_name", nullable = false, length = 50)
	@JsonView(JsonViews.Public.class)
	private String groupName;

	@Column(name = "description", nullable = false, length = 100)
	@JsonView(JsonViews.Public.class)
	private String description;

	@Column(name = "group_image", nullable = true, length = 150)
	@JsonView(JsonViews.Public.class)
	private String groupImage;

	@ManyToOne(optional = true)
	@JsonView(JsonViews.Public.class)
	@JsonIgnoreProperties({ "productsPosted","productsShared","productsRecieved",
		"productsRequestedByUser","productsRequestedByOthers","friends","location","groups","wishList",
		"friendsRequests", "groupRequests"}) 
	private User admin;

	@ManyToMany
	@JsonView(JsonViews.Public.class)
	@JsonIgnoreProperties({ "productsPosted","productsShared","productsRecieved",
		"productsRequestedByUser","productsRequestedByOthers","friends","location","groups",
		"friendsRequests", "groupRequests"}) 
	private List<User> members = new ArrayList<>();
	
	@ManyToMany
	@JsonView(JsonViews.Public.class)

	private List<Product> productsSharedToGroup = new ArrayList<>();


	public Group() {
	}


	public Group(String groupName, String description, String groupImage, User admin, List<User> members,
			List<Product> productsSharedToGroup) {
		super();
		this.groupName = groupName;
		this.description = description;
		this.groupImage = groupImage;
		this.admin = admin;
		this.members = members;
		this.productsSharedToGroup = productsSharedToGroup;
	}



}
