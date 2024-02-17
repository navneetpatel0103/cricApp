package com.players.response;

import java.util.List;

import org.springframework.stereotype.Component;

import com.players.dao.CustomPlayer;
import com.players.dao.TeamWithPlayerCustomize;
import com.players.dto.PlayersDTO;
import com.players.dto.TeamDTO;
import com.players.entity.Player;
import com.players.entity.Team;

@Component
public class ResponseObject {
	String successMessage;
	String failureMessage;

	Team t;
	Player p;
	TeamDTO tDo;
	PlayersDTO pDo;
	List<Team> listT;
	List<Player> ListP;
	List<TeamWithPlayerCustomize> ListCustomized;
	List<CustomPlayer> ListCustomizedPlayer;
	List<String> ListWithUpperCase;

	public List<String> getListWithUpperCase() {
		return ListWithUpperCase;
	}

	public void setListWithUpperCase(List<String> listWithUpperCase) {
		ListWithUpperCase = listWithUpperCase;
	}

	public List<CustomPlayer> getListCustomizedPlayer() {
		return ListCustomizedPlayer;
	}

	public void setListCustomizedPlayer(List<CustomPlayer> listCustomizedPlayer) {
		ListCustomizedPlayer = listCustomizedPlayer;
	}

	public List<TeamWithPlayerCustomize> getListCustomized() {
		return ListCustomized;
	}

	public void setListCustomized(List<TeamWithPlayerCustomize> listCustomized) {
		ListCustomized = listCustomized;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getFailureMessage() {
		return failureMessage;
	}

	public void setFailureMessage(String failureMessage) {
		this.failureMessage = failureMessage;
	}

	public Team getT() {
		return t;
	}

	public void setT(Team t) {
		this.t = t;
	}

	public Player getP() {
		return p;
	}

	public void setP(Player p) {
		this.p = p;
	}

	public TeamDTO gettDo() {
		return tDo;
	}

	public void settDo(TeamDTO tDo) {
		this.tDo = tDo;
	}

	public PlayersDTO getpDo() {
		return pDo;
	}

	public void setpDo(PlayersDTO pDo) {
		this.pDo = pDo;
	}

	public List<Team> getListT() {
		return listT;
	}

	public void setListT(List<Team> listT) {
		this.listT = listT;
	}

	public List<Player> getListP() {
		return ListP;
	}

	public void setListP(List<Player> listP) {
		ListP = listP;
	}

}
