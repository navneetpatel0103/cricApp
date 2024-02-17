package com.players.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.players.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

	// customized player data(fetching some attributes from table)
	@Query(value = "select p.playerName as playerName, p.playerCountry as playerCountry,p.playerRole as "
			+ "playerRole, p.team as iplTeam from Player p where p.player_id= :id1")
	List<CustomPlayer> findCustomInfo(@Param("id1") int playerId);

	// Operator query
	@Query("select p from Player p where p.playerName LIKE %:name1%")
	List<Player> findByName(@Param("name1") String playerName);

	// named query(converting name to upper case)
	List<String> convertNameToUpperCase();
}
