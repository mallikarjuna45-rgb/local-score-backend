package com.cricket.local_score.Service.playerEntity;

import com.cricket.local_score.Entity.PlayerEntity;
import com.cricket.local_score.Entity.TeamEntity;

import java.util.List;
import java.util.Set;

public interface IplayerService {
    // Create player (must be linked to a user)
    PlayerEntity createPlayer(PlayerEntity player, Integer userId);

    // Update specific attributes with automatic dependencies
    PlayerEntity updateRuns(Integer playerId, Integer runs); // also updates strike rate
    PlayerEntity updateBoundaries(Integer playerId, Integer boundaries); // also updates runs
    PlayerEntity updateBallsFaced(Integer playerId, Integer ballsFaced); // may update strike rate
    PlayerEntity updateBallsBowled(Integer playerId, Integer ballsBowled); // may update bowling strike rate
    PlayerEntity updateWickets(Integer playerId, Integer wickets); // update bowling SR too
    PlayerEntity updateMatches(Integer playerId, Integer matches);
    PlayerEntity updateCatches(Integer playerId, Integer catches);

    // Update all fields (for complete modification)
    PlayerEntity updateAllAttributes(Integer playerId, PlayerEntity updatedPlayer);
    Set<TeamEntity> getTeamsByPlayerId(Integer playerId);

    // Get details
    PlayerEntity getPlayerById(Integer playerId);
    List<PlayerEntity> getAllPlayers();
    List<PlayerEntity> getPlayersByPlayerName(String name);
}
