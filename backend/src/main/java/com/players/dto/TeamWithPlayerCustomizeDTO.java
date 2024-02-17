package com.players.dto;

import java.io.Serializable;

public class TeamWithPlayerCustomizeDTO implements Serializable {
	private int teamIdDTO;
	private String teamNameDTO;
	private String playerNameDTO;
	private String playerCountryDTO;
	private String playerRoleDTO;

	public int getTeamIdDTO() {
		return teamIdDTO;
	}

	public void setTeamIdDTO(int teamIdDTO) {
		this.teamIdDTO = teamIdDTO;
	}

	public String getTeamNameDTO() {
		return teamNameDTO;
	}

	public void setTeamNameDTO(String teamNameDTO) {
		this.teamNameDTO = teamNameDTO;
	}

	public String getPlayerNameDTO() {
		return playerNameDTO;
	}

	public void setPlayerNameDTO(String playerNameDTO) {
		this.playerNameDTO = playerNameDTO;
	}

	public String getPlayerCountryDTO() {
		return playerCountryDTO;
	}

	public void setPlayerCountryDTO(String playerCountryDTO) {
		this.playerCountryDTO = playerCountryDTO;
	}

	public String getPlayerRoleDTO() {
		return playerRoleDTO;
	}

	public void setPlayerRoleDTO(String playerRoleDTO) {
		this.playerRoleDTO = playerRoleDTO;
	}

}
