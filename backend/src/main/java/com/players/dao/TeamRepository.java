package com.players.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.players.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
	
	
	List<Team> findByTeamName(String name);
	List<Team> findByEmail(String name);
	List<Team> findByContact(String name);
	
	// Operator query
	@Query(value = "select t from Team t where t.team_id > :id1")
	List<Team> findTeamById(@Param("id1") int id);

	// customized data through join
	@Query("select t.team_id as teamId, t.teamName as teamName, p.playerName as playerName"
			+ ", p.playerCountry as playerCountry, p.playerRole as playerRole "
			+ "from Team t join Player p on t.team_id=p.Team_ID")
	List<TeamWithPlayerCustomize> findTeamWithPlayerCustomize();

	// named query (aggregate function)
	int countTotalTeams();
}
