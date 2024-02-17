package com.players.bo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.players.dao.TeamRepository;
import com.players.dao.TeamWithPlayerCustomize;
import com.players.entity.Team;
import com.players.exception.TeamException;

@Component
public class TeamBO {

	@Autowired
	TeamRepository t;

	public Team insertTeam(Team teamName) throws TeamException {
		Pattern emailPattern = Pattern.compile("[a-zA-Z0-9[!#$%&'()*+,/\\-_\\.\"]]+"
				+ "@[a-zA-Z0-9[!#$%&'()*+,/\\-_\"]]+\\.[a-zA-Z0-9[!#$%&'()*+,/\\-_\"\\.]]+");
		
		Pattern ptrn = Pattern.compile("[6-9][0-9]{9}");  
		
//		List<Team> list = t.findAll();
		if (teamName.getTeam_id() < 0) {
			throw new TeamException("Team ID can not be negative");
		} else if (teamName.getTeamName().isEmpty() || teamName.getTeamName().isBlank()) {
			throw new TeamException("Please provide a valid team name.");
		}else if(teamName.getContact().isEmpty() || teamName.getContact().isBlank()) {
			throw new TeamException("Please provide a valid team contact.");
		}else if(teamName.getEmail().isEmpty() || teamName.getEmail().isBlank()) {
			throw new TeamException("Please provide a valid team email.");
		}else if(teamName.getTeamFormationDate()==null) {
			throw new TeamException("Please provide a valid team formation date.");
		}else if(teamName.getTeamLocation().isEmpty() || teamName.getTeamLocation().isBlank()) {
			throw new TeamException("Please provide a valid team location.");
		}else if(teamName.getTeamOwner().isEmpty() || teamName.getTeamOwner().isBlank()) {
			throw new TeamException("Please provide a valid team owner.");
		}
//		for (Team it : list) {
//			if (it.getTeamName().equalsIgnoreCase(str)) {
//				throw new TeamException("Team Name already exists");
//			}
//		}
		
		
		String name = teamName.getTeamName();
		name=name.trim();
		List<Team> nameList=t.findByTeamName(name);
		if(nameList.size()!=0) {
			throw new TeamException("Team Name already exists");
		}
		teamName.setTeamName(name);
		String email=teamName.getEmail();
		email=email.trim();
		if(!emailPattern.matcher(email).matches()) {
			throw new TeamException("Invalid email address");
		}
		String contact=teamName.getContact();
		contact=contact.trim();
		if(contact.length()!=10 || !ptrn.matcher(contact).matches()) {
			throw new TeamException("Invalid contact number");
		}
		LocalDate formationDate=teamName.getTeamFormationDate();
		if(formationDate.isAfter(LocalDate.now())) {
			throw new TeamException("Invalid formation date");
		}
		List<Team> emailList=t.findByEmail(email);
		if(emailList.size()!=0) {
			throw new TeamException("Email already exists");
		}
		teamName.setEmail(email);
		
		List<Team> contactList=t.findByContact(contact);
		if(contactList.size()!=0) {
			throw new TeamException("Contact number already exists");
		}
		teamName.setContact(contact);
		
		
		String teamLocation=teamName.getTeamLocation();
		teamLocation=teamLocation.trim();
		teamName.setTeamLocation(teamLocation);
		
		String teamOwner=teamName.getTeamOwner();
		teamOwner=teamOwner.trim();
		teamName.setTeamOwner(teamOwner);
		return t.save(teamName);
	}

	public Team updateTeam(Team teamName) throws TeamException {
		Pattern emailPattern = Pattern.compile("[a-zA-Z0-9[!#$%&'()*+,/\\-_\\.\"]]+"
				+ "@[a-zA-Z0-9[!#$%&'()*+,/\\-_\"]]+\\.[a-zA-Z0-9[!#$%&'()*+,/\\-_\"\\.]]+");
		
		Pattern ptrn = Pattern.compile("[6-9][0-9]{9}");  
		
//		List<Team> list = t.findAll();
		if (teamName.getTeam_id() < 0) {
			throw new TeamException("Team ID can not be negative");
		} else if (teamName.getTeamName().isEmpty() || teamName.getTeamName().isBlank()) {
			throw new TeamException("Please provide a valid team name.");
		}else if(teamName.getContact().isEmpty() || teamName.getContact().isBlank()) {
			throw new TeamException("Please provide a valid team contact.");
		}else if(teamName.getEmail().isEmpty() || teamName.getEmail().isBlank()) {
			throw new TeamException("Please provide a valid team email.");
		}else if(teamName.getTeamFormationDate()==null) {
			throw new TeamException("Please provide a valid team formation date.");
		}else if(teamName.getTeamLocation().isEmpty() || teamName.getTeamLocation().isBlank()) {
			throw new TeamException("Please provide a valid team location.");
		}else if(teamName.getTeamOwner().isEmpty() || teamName.getTeamOwner().isBlank()) {
			throw new TeamException("Please provide a valid team owner.");
		}
		
		String email=teamName.getEmail();
		email=email.trim();
		if(!emailPattern.matcher(email).matches()) {
			throw new TeamException("Invalid email address");
		}
		String contact=teamName.getContact();
		contact=contact.trim();
		if(contact.length()!=10 || !ptrn.matcher(contact).matches()) {
			throw new TeamException("Invalid contact number");
		}
		LocalDate formationDate=teamName.getTeamFormationDate();
		if(formationDate.isAfter(LocalDate.now())) {
			throw new TeamException("Invalid formation date");
		}
		
		String name = teamName.getTeamName();
		name=name.trim();
		System.out.println("name: " + name);
		List<Team> nameList=t.findByTeamName(name);
		if(nameList.size()!=0) {
			Team temp=nameList.get(0);
			System.out.println(temp.getTeamName());
			if(temp.getTeam_id()!=teamName.getTeam_id()) {
				throw new TeamException("Team Name already existsSSSSS");
			}	
		}
		teamName.setTeamName(name);
		List<Team> emailList=t.findByEmail(email);
		
		if(emailList.size()!=0) {
			Team temp=emailList.get(0);
			System.out.println("Curr team :" + temp);
			if(temp.getTeam_id()!=teamName.getTeam_id()) {
				throw new TeamException("Email already exists");
			}
		}
		teamName.setEmail(email);
		
		List<Team> contactList=t.findByContact(contact);
		if(contactList.size()!=0) {
			Team temp=contactList.get(0);
			if(temp.getTeam_id()!=teamName.getTeam_id()) {
				throw new TeamException("Contact number already exists");
			}
		}
		teamName.setContact(contact);
		
		
		String teamLocation=teamName.getTeamLocation();
		teamLocation=teamLocation.trim();
		teamName.setTeamLocation(teamLocation);
		
		String teamOwner=teamName.getTeamOwner();
		teamOwner=teamOwner.trim();
		teamName.setTeamOwner(teamOwner);
		return t.save(teamName);
		
//		String str = teamName.getTeamName();
//		str=str.trim();
//		teamName.setTeamName(str);
//		List<Team> list = t.findAll();
//		if (teamName.getTeam_id() < 0) {
//			throw new TeamException("Team ID can not be negative");
//		} else if (teamName.getTeamName().isEmpty() || teamName.getTeamName().isBlank()) {
//			throw new TeamException("Please provide a valid team name.");
//		}
//		for (Team it : list) {
//			if (it.getTeamName().equalsIgnoreCase(str)) {
//				throw new TeamException("Team Name already exists");
//			}
//		}
//		return t.save(teamName);
	}

	public Team findById(int id) throws TeamException {
		if (id <=0) {
			throw new TeamException("Enter positive team id.");
		}
		Optional<Team> team = t.findById(id);
		if (!team.isPresent()) {
			throw new TeamException("Team is not present with id:" + id);
		}
		return team.get();
	}

	public List<Team> findAllTeams() throws TeamException {
		List<Team> list = t.findAll();
		if (list.isEmpty()) {
			throw new TeamException("No teams are found.");
		}
		return list;
	}

	// query 1
	public List<Team> findTeamsById(Integer id) throws TeamException {
		if(id==null) {
			throw new TeamException("Enter the valid id");
		}
		List<Team> l = t.findTeamById(id);
		if (l.isEmpty()) {
			throw new TeamException("No data is present");
		}
		return l;
	}



	// query 2

	public List<TeamWithPlayerCustomize> findTeamWithPlayerCustomize() throws TeamException {
		List<TeamWithPlayerCustomize> l = t.findTeamWithPlayerCustomize();
		if (l.isEmpty()) {
			throw new TeamException("No data is present");
		}
		return l;
	}

	// query 3
	public int countTotalTeams() {
		return t.countTotalTeams();
	}

	

}
