package com.players.bo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.players.dao.CustomPlayer;
import com.players.dao.PlayerRepository;
import com.players.dao.TeamRepository;
import com.players.entity.Player;
import com.players.entity.Team;
import com.players.exception.PlayerException;

@Component
public class PlayerBO {
	@Autowired
	TeamRepository t;
	@Autowired
	PlayerRepository playerRepo;

	public Player insertPlayer(Player p) throws PlayerException {

		if (p.getPlayer_id() < 0) {
			throw new PlayerException("Player ID can not be negative");
		} else if ((p.getPlayerName().isEmpty()) || (p.getPlayerName().isBlank())) {
			throw new PlayerException("Enter the valid player name.");
		} else if (p.getPlayerCountry().isBlank() || p.getPlayerCountry().isEmpty()) {
			throw new PlayerException("Enter the valid player country");
		} else if (p.getPlayerRole().isBlank() || p.getPlayerRole().isEmpty()) {
			throw new PlayerException("Enter the valid player role");
		} else if (p.getTeam() == null) {
			throw new PlayerException("Enter the valid team");
		}else if(p.getGender().isBlank() || p.getGender().isEmpty()) {
			throw new PlayerException("Enter the valid gender");
		}else if(p.getJerseyNumber()<0) {
			throw new PlayerException("Jersey number can not be negative");
		}else if(p.getJerseyNumber()==0) {
			throw new PlayerException("Enter the valid jersey number");
		}else if(p.getMatches()<0) {
			throw new PlayerException("Total matches can not be negative");
		}else if(p.getMatches()==0) {
			if(p.getRuns()!=0 && p.getWickets()!=0) {
				throw new PlayerException("Can't enter runs and wickets without matches");
			}else if(p.getRuns()!=0) {
				throw new PlayerException("Can't enter runs without matches");
			}else if(p.getWickets()!=0) {
				throw new PlayerException("Can't enter wickets without matches");
			}
		}else if(p.getRuns()<0) {
			throw new PlayerException("Total runs can not be negative");
		}else if(p.getWickets()<0) {
			throw new PlayerException("Total wickets can not be negative");
		}
		
		
		int teamId = p.getTeam().getTeam_id();
		if (teamId < 0) {
			throw new PlayerException("Team ID should be positive");
		}
		Optional<Team> pl = t.findById(teamId);
		if (!pl.isPresent()) {
			throw new PlayerException("Team is not present with id: " + p.getTeam().getTeam_id());
		}
		
		
		p.setPlayerName(p.getPlayerName().trim());
		p.setPlayerCountry(p.getPlayerCountry().trim());
		p.setPlayerRole(p.getPlayerRole().trim());
		p.setGender(p.getGender().trim());
		
		
		return playerRepo.save(p);
	}

	public Player updatePlayer(Player p) throws PlayerException {

		if (p.getPlayer_id() < 0) {
			throw new PlayerException("Player ID can not be negative");
		} else if ((p.getPlayerName().isEmpty()) || (p.getPlayerName().isBlank())) {
			throw new PlayerException("Enter the valid player name.");
		} else if (p.getPlayerCountry().isBlank() || p.getPlayerCountry().isEmpty()) {
			throw new PlayerException("Enter the valid player country");
		} else if (p.getPlayerRole().isBlank() || p.getPlayerRole().isEmpty()) {
			throw new PlayerException("Enter the valid player role");
		} else if (p.getTeam() == null) {
			throw new PlayerException("Enter the valid team");
		}else if(p.getGender().isBlank() || p.getGender().isEmpty()) {
			throw new PlayerException("Enter the valid gender");
		}else if(p.getJerseyNumber()<0) {
			throw new PlayerException("Jersey number can not be negative");
		}else if(p.getJerseyNumber()==0) {
			throw new PlayerException("Enter the valid jersey number");
		}else if(p.getMatches()<0) {
			throw new PlayerException("Total matches can not be negative");
		}else if(p.getMatches()==0) {
			if(p.getRuns()!=0 && p.getWickets()!=0) {
				throw new PlayerException("Can't enter runs and wickets without matches");
			}else if(p.getRuns()!=0) {
				throw new PlayerException("Can't enter runs without matches");
			}else if(p.getWickets()!=0) {
				throw new PlayerException("Can't enter wickets without matches");
			}
		}else if(p.getRuns()<0) {
			throw new PlayerException("Total runs can not be negative");
		}else if(p.getWickets()<0) {
			throw new PlayerException("Total wickets can not be negative");
		}
		
		
		int teamId = p.getTeam().getTeam_id();
		if (teamId < 0) {
			throw new PlayerException("Team ID should be positive");
		}
		Optional<Team> pl = t.findById(teamId);
		if (!pl.isPresent()) {
			throw new PlayerException("Team is not present with id: " + p.getTeam().getTeam_id());
		}
		
		
		p.setPlayerName(p.getPlayerName().trim());
		p.setPlayerCountry(p.getPlayerCountry().trim());
		p.setPlayerRole(p.getPlayerRole().trim());
		p.setGender(p.getGender().trim());
		
		
		return playerRepo.save(p);
	}

	public Player findById(Integer id) throws PlayerException {
		if (id == null) {
			throw new PlayerException("Enter the valid id");
		} else if (id <= 0) {
			throw new PlayerException("Enter the positive id");
		}
		Optional<Player> player = playerRepo.findById(id);
		if (!player.isPresent()) {
			throw new PlayerException("Player is not present with id:" + id);
		}
		return player.get();
	}

	public List<Player> findAll() throws PlayerException {
		List<Player> list = playerRepo.findAll();
		if (list.isEmpty()) {
			throw new PlayerException("No players are found.");
		}
		return list;
	}

	public List<CustomPlayer> findPlayerById(Integer id) throws PlayerException {
		if (id == null) {
			throw new PlayerException("Enter the valid id");
		} else if (id < 0) {
			throw new PlayerException("Enter the positive id");
		}
		List<CustomPlayer> player = playerRepo.findCustomInfo(id);
		if (player.isEmpty()) {
			throw new PlayerException("No data found.");
		}
		return player;
	}

	public List<Player> findByName(String s) throws PlayerException {
		if (s.isEmpty()) {
			throw new PlayerException("Enter the valid name.");
		}
		List<Player> list = playerRepo.findByName(s);
		if (list.isEmpty()) {
			throw new PlayerException("No player having string " + s + " in their name.");
		}
		return list;
	}

	public List<String> convertNameToUpperCase() throws PlayerException {
		List<String> list = playerRepo.convertNameToUpperCase();
		if (list.isEmpty()) {
			throw new PlayerException("No data found.");
		}
		return list;
	}
}
