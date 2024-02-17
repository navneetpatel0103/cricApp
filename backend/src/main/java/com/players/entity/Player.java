package com.players.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Players")
@EntityListeners(AuditingEntityListener.class)
@NamedQueries({ @NamedQuery(name = "Player.convertNameToUpperCase", 
				query = "select upper(playerName) from Player p") })
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Player_ID")
	private int player_id;

	@NotBlank
	@Column(name = "Player_Name")
	private String playerName;

	@NotNull
	@Column(insertable = false, updatable = false)
	private int Team_ID;

	@JsonBackReference
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Team_ID", nullable = false, referencedColumnName = "Team_id")
	Team team;

	@NotBlank
	@Column(name = "Country")
	private String playerCountry;

	@NotBlank
	@Column(name = "Role")
	private String playerRole;
	
	@NotBlank
	@Column(name="Gender")
	private String gender;
	
	@NotNull
	@Column(name="Jersey_Number")
	private int jerseyNumber;
	
	@NotNull
	@Column(name="Matches", columnDefinition="INT DEFAULT 0")
	private int matches;
	
	@NotNull
	@Column(name="Runs", columnDefinition="INT DEFAULT 0")
	private int runs;
	
	@NotNull
	@Column(name="Wickets", columnDefinition="INT DEFAULT 0")
	private int wickets;
	
//	@NotNull
//	@Column(name="Avg_Runs", columnDefinition="BIGDECIMAL DEFAULT 0", precision = 5, scale = 2)
//	private BigDecimal avgRuns;
//	
//	@NotNull
//	@Column(name="Avg_Wickets", columnDefinition="BIGDECIMAL DEFAULT 0", precision = 5, scale = 2)
//	private BigDecimal avgWickets;
	

	@Column(nullable = false, updatable = false, name = "Created_At")
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(nullable = false, name = "Updated_At")
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	public int getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getTeam_ID() {
		return Team_ID;
	}

	public void setTeam_ID(int team_ID) {
		Team_ID = team_ID;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public String getPlayerCountry() {
		return playerCountry;
	}

	public void setPlayerCountry(String playerCountry) {
		this.playerCountry = playerCountry;
	}

	public String getPlayerRole() {
		return playerRole;
	}

	public void setPlayerRole(String playerRole) {
		this.playerRole = playerRole;
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
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getJerseyNumber() {
		return jerseyNumber;
	}

	public void setJerseyNumber(int jerseyNumber) {
		this.jerseyNumber = jerseyNumber;
	}

	public int getMatches() {
		return matches;
	}

	public void setMatches(int matches) {
		this.matches = matches;
	}

	public int getRuns() {
		return runs;
	}

	public void setRuns(int runs) {
		this.runs = runs;
	}

	public int getWickets() {
		return wickets;
	}

	public void setWickets(int wickets) {
		this.wickets = wickets;
	}

//	public BigDecimal getAvgRuns() {
//		return avgRuns;
//	}
//
//	public void setAvgRuns(BigDecimal avgRuns) {
//		this.avgRuns = avgRuns;
//	}
//
//	public BigDecimal getAvgWickets() {
//		return avgWickets;
//	}
//
//	public void setAvgWickets(BigDecimal avgWickets) {
//		this.avgWickets = avgWickets;
//	}

	@Override
	public String toString() {
		return "Player id is " + player_id + " , player name is " + playerName + " and its IPL team is "
				+ team.getTeamName() + " and its country is " + playerCountry;
	}

}
