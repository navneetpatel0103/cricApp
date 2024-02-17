package com.players.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class TeamDTO implements Serializable {
	private int teamId;
	private String teamName;
	private String email;
	private String contact;
	private LocalDate teamFormationDate;
	private String teamLocation;
	private String teamOwner;
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public LocalDate getTeamFormationDate() {
		return teamFormationDate;
	}

	public void setTeamFormationDate(LocalDate teamFormationDate) {
		this.teamFormationDate = teamFormationDate;
	}

	public String getTeamLocation() {
		return teamLocation;
	}

	public void setTeamLocation(String teamLocation) {
		this.teamLocation = teamLocation;
	}

	public String getTeamOwner() {
		return teamOwner;
	}

	public void setTeamOwner(String teamOwner) {
		this.teamOwner = teamOwner;
	}

	private Date createdAt;
	private Date updatedAt;

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "Team ID is " + teamId + " and team name is " + teamName;
	}
}
