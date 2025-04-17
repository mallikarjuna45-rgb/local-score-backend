package com.cricket.local_score.Service.playerEntity;

import com.cricket.local_score.Entity.PlayerEntity;
import com.cricket.local_score.Entity.TeamEntity;

import java.util.List;
import java.util.Set;

public interface IplayerService {
    // Create player (must be linked to a user)
    PlayerEntity createPlayer(PlayerEntity player, Integer userId);

    // Update specific attributes with automatic dependencies
    PlayerEntity updateRuns(Long playerId, Integer runs); // also updates strike rate
    PlayerEntity updateBoundaries(Long playerId, Integer boundaries); // also updates runs
    PlayerEntity updateBallsFaced(Long playerId, Integer ballsFaced); // may update strike rate
    PlayerEntity updateBallsBowled(Long playerId, Integer ballsBowled); // may update bowling strike rate
    PlayerEntity updateWickets(Long playerId, Integer wickets); // update bowling SR too
    PlayerEntity updateMatches(Long playerId, Integer matches);
    PlayerEntity updateCatches(Long playerId, Integer catches);

    // Update all fields (for complete modification)
    PlayerEntity updateAllAttributes(Long playerId, PlayerEntity updatedPlayer);
    Set<TeamEntity> getTeamsByPlayerId(Long playerId);

    // Get details
    PlayerEntity getPlayerById(Long playerId);
    List<PlayerEntity> getAllPlayers();
    List<PlayerEntity> getPlayersByPlayerName(String name);
}
