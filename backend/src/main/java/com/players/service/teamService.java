package com.players.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.MappingApplication;
import com.players.bo.PlayerBO;
import com.players.bo.TeamBO;
import com.players.dao.TeamWithPlayerCustomize;
import com.players.entity.Player;
import com.players.entity.Team;
import com.players.exception.PlayerException;
import com.players.exception.TeamException;
import com.players.response.ResponseObject;

@Component
public class teamService {
	final static Logger LOG = Logger.getLogger(MappingApplication.class);
	@Autowired
	TeamBO t;

	@Autowired
	TeamBO objT;

	@Autowired
	PlayerBO objPl;

	public ResponseObject insert(Team team) {
		LOG.info("Insert function for team is called from service for id: " + team.getTeam_id());
		ResponseObject ros = new ResponseObject();
		Team t1;
		try {
			t1 = t.insertTeam(team);
			ros.setT(t1);
			ros.setSuccessMessage("Team is inserted successfully with id:" + t1.getTeam_id());
			LOG.info("Team is inserted successfully with id:" + t1.getTeam_id());
		} catch (TeamException e) {
			ros.setFailureMessage(e.getMessage());
			LOG.error(e.getMessage());
		} catch (Exception e) {
			ros.setFailureMessage(e.getMessage());
			LOG.error(e.getMessage());
		}
		return ros;
	}

	public ResponseObject updateTeam(Team team) {
		LOG.info("Update function for team is called from service for id: " + team.getTeam_id());
		ResponseObject ros = new ResponseObject();
		Team t1;
		try {
			t1 = t.updateTeam(team);
			ros.setT(t1);
			ros.setSuccessMessage("Team is updated successfully with id:" + t1.getTeam_id());
			LOG.info("Team is updated successfully with id:" + t1.getTeam_id());
		} catch (TeamException e) {
			ros.setFailureMessage(e.getMessage());
			LOG.error(e.getMessage());
		} catch (Exception e) {
			ros.setFailureMessage(e.getMessage());
			LOG.error(e.getMessage());
		}
		return ros;
	}

	public ResponseObject findById(int id) {
		LOG.info("findById function for team is called from service for ID:" + id);
		ResponseObject ros = new ResponseObject();
		Team t1;
		try {
			t1 = t.findById(id);
			ros.setT(t1);
			ros.setSuccessMessage("Record for team id: " + t1.getTeam_id() + " is fetched");
			LOG.info("Successfully printed the team details for id: " + id);
		} catch (TeamException e) {
			ros.setFailureMessage(e.getMessage());
			LOG.error(e.getMessage());
		} catch (Exception e) {
			ros.setFailureMessage(e.getMessage());
			LOG.error(e.getMessage());
		}
		return ros;
	}

	public ResponseObject findAll() {
		LOG.info("findAll function is called for team from service to fetch all teams");
		ResponseObject ros = new ResponseObject();
		try {
			List<Team> list = t.findAllTeams();
			ros.setListT(list);
			ros.setSuccessMessage("Records for all teams is fetched.");
			LOG.info("Successfully printed the team details for all the teams");
		} catch (TeamException e) {
			ros.setFailureMessage(e.getMessage());
			LOG.error(e.getMessage());
		} catch (Exception e) {
			ros.setFailureMessage(e.getMessage());
			LOG.error(e.getMessage());
		}
		return ros;
	}

	public ResponseObject findTeamById(Integer id) {
		LOG.info("findTeamById function is called from service to fetch all teams greater than id: " + id);
		ResponseObject ros = new ResponseObject();
		try {
			List<Team> list = t.findTeamsById(id);
			ros.setListT(list);
			ros.setSuccessMessage("Printed the result of team with id greater than " + id);
			LOG.info("Successfully printed the team details for id greater than " + id);
		} catch (TeamException e) {
			ros.setFailureMessage(e.getMessage());
			LOG.error(e.getMessage());
		} catch (Exception e) {
			ros.setFailureMessage(e.getMessage());
			LOG.error(e.getMessage());
		}
		return ros;
	}


	public ResponseObject findTeamWithPlayerCustomize() {
		LOG.info("findTeamWithPlayerCustomize function is called from service to show the custom fields of join query");
		ResponseObject ros = new ResponseObject();
		try {
			List<TeamWithPlayerCustomize> list = t.findTeamWithPlayerCustomize();
			ros.setListCustomized(list);
			ros.setSuccessMessage("Successfully printed the all teams with players to show the custom fields of join query");
			LOG.info("Successfully printed the all teams with players to show the custom fields of join query");
		} catch (TeamException e) {
			ros.setFailureMessage(e.getMessage());
			LOG.error(e.getMessage());
		} catch (Exception e) {
			ros.setFailureMessage(e.getMessage());
			LOG.error(e.getMessage());
		}
		return ros;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void transactionDemo(Team t1, Player p1) throws TeamException, PlayerException {
		LOG.info("Transactional functional called from service");
		System.out.println("Team before");
		objT.insertTeam(t1);
		System.out.println("Team after");
		objPl.insertPlayer(p1);
		System.out.println("Player After");
	}

	public int countTotalTeams() {
		LOG.info("countTotalTeams function is called from service");
		LOG.info("Successfully printed the total number of teams");
		return t.countTotalTeams();
	}

	
}
