package com.players.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.MappingApplication;
import com.players.bo.PlayerBO;
import com.players.dao.CustomPlayer;
import com.players.entity.Player;
import com.players.exception.PlayerException;
import com.players.response.ResponseObject;

@Component
public class playerService {
	final static Logger LOG = Logger.getLogger(MappingApplication.class);
	@Autowired
	PlayerBO objBO;

	public ResponseObject insert(Player p) {
		LOG.info("Insert function for player is called from service for id: " + p.getPlayer_id());
		ResponseObject ros = new ResponseObject();
		try {
			Player pl = objBO.insertPlayer(p);
			ros.setP(pl);
			ros.setSuccessMessage("Player is inserted successfully with id: " + pl.getPlayer_id());
			LOG.info("Player is inserted successfully with id:" + pl.getPlayer_id());
		} catch (PlayerException e) {
			ros.setFailureMessage(e.getMessage());
			LOG.error(e.getMessage());
		} catch (Exception e) {
			ros.setFailureMessage(e.getMessage());
			LOG.error(e.getMessage());
		}
		return ros;
	}

	public ResponseObject updatePlayer(Player p) {
		LOG.info("Update function for player is called from service for id: " + p.getPlayer_id());
		ResponseObject ros = new ResponseObject();
		try {
			Player pl = objBO.updatePlayer(p);
			ros.setP(pl);
			ros.setSuccessMessage("Player is updated successfully with id: " + pl.getPlayer_id());
			LOG.info("Player is updated successfully with id:" + pl.getPlayer_id());
		} catch (PlayerException e) {
			ros.setFailureMessage(e.getMessage());
			LOG.error(e.getMessage());
		} catch (Exception e) {
			ros.setFailureMessage(e.getMessage());
			LOG.error(e.getMessage());
		}
		return ros;
	}

	public ResponseObject findById(Integer id) {
		LOG.info("findById function for player is called from service for ID:" + id);
		ResponseObject ros = new ResponseObject();
		try {
			Player pl = objBO.findById(id);
			ros.setP(pl);
			ros.setSuccessMessage("Record for player id: " + pl.getPlayer_id() + " is fetched");
			LOG.info("Successfully printed the player details for id: " + id);
		} catch (PlayerException e) {
			ros.setFailureMessage(e.getMessage());
			LOG.error(e.getMessage());
		} catch (Exception e) {
			ros.setFailureMessage(e.getMessage());
			LOG.error(e.getMessage());
		}
		return ros;
	}

	public ResponseObject findAll() {
		LOG.info("findAll function for player is called from service to fetch all players");
		ResponseObject ros = new ResponseObject();
		try {
			List<Player> list = objBO.findAll();
			ros.setListP(list);
			ros.setSuccessMessage("Records for all players is fetched.");
			LOG.info("Successfully printed the player details for all the players");
		} catch (PlayerException e) {
			ros.setFailureMessage(e.getMessage());
			LOG.error(e.getMessage());
		} catch (Exception e) {
			ros.setFailureMessage(e.getMessage());
			LOG.error(e.getMessage());
		}
		return ros;
	}

	public ResponseObject findPlayerById(Integer id) {
		LOG.info("Find player is called to fetch custom fields of player with id: " + id);
		ResponseObject ros = new ResponseObject();
		try {
			List<CustomPlayer> pl = objBO.findPlayerById(id);
			ros.setListCustomizedPlayer(pl);
			ros.setSuccessMessage("Printed the custom info of player with id: " + id);
			LOG.info("Successfully printed the custom info of player with id: " + id);
		} catch (PlayerException e) {
			ros.setFailureMessage(e.getMessage());
			LOG.error(e.getMessage());
		} catch (Exception e) {
			ros.setFailureMessage(e.getMessage());
			LOG.error(e.getMessage());
		}
		return ros;
	}

	public ResponseObject findByName(String s) {
		LOG.info("find by name function is called to find players having string: " + s + " in players name");
		ResponseObject ros = new ResponseObject();
		try {
			List<Player> list = objBO.findByName(s);
			ros.setListP(list);
			ros.setSuccessMessage("Player having string " + s + " is fetched");
			LOG.info("Successfully fetched player having string " + s + " in players name");
		} catch (PlayerException e) {
			ros.setFailureMessage(e.getMessage());
			LOG.error(e.getMessage());
		} catch (Exception e) {
			ros.setFailureMessage(e.getMessage());
			LOG.error(e.getMessage());
		}
		return ros;
	}

	public ResponseObject convertNameToUpperCase() {
		LOG.info("convertNameToUpperCase function is called to fetch players name in upper case");
		ResponseObject ros = new ResponseObject();
		try {
			List<String> list = objBO.convertNameToUpperCase();
			ros.setListWithUpperCase(list);
			ros.setSuccessMessage("Player name in upper case is fetched");
			LOG.info("Successfully fetched the player name in upper case");
		} catch (PlayerException e) {
			ros.setFailureMessage(e.getMessage());
			LOG.error(e.getMessage());
		} catch (Exception e) {
			ros.setFailureMessage(e.getMessage());
			LOG.error(e.getMessage());
		}
		return ros;
	}

}
