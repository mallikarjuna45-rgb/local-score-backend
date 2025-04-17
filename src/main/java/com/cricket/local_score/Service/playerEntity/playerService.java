package com.cricket.local_score.Service.playerEntity;

import com.cricket.local_score.Entity.PlayerEntity;
import com.cricket.local_score.Entity.TeamEntity;
import com.cricket.local_score.Entity.UserEntity;
import com.cricket.local_score.Repository.PlayerRepository;
import com.cricket.local_score.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class playerService implements IplayerService{

        @Autowired
        private PlayerRepository playerRepository;

        @Autowired
        private UserRepository userRepository;

        @Override
        public PlayerEntity createPlayer(PlayerEntity player, Integer userId) {
            UserEntity user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            player.setUser(user);
            return playerRepository.save(player);
        }

        @Override
        public PlayerEntity updateRuns(Integer playerId, Integer runs) {
            PlayerEntity player = getPlayerById(playerId);
            player.setRuns(player.getRuns()+runs);
            updateBattingStrikeRate(player);
            return playerRepository.save(player);
        }

        @Override
        public PlayerEntity updateBoundaries(Integer playerId, Integer boundaries) {
            PlayerEntity player = getPlayerById(playerId);
            player.setBoundaries(player.getBoundaries()+boundaries);
            player.setRuns(player.getRuns() + (boundaries * 4)); // assuming each boundary is 4
            updateBattingStrikeRate(player);
            return playerRepository.save(player);
        }

        @Override
        public PlayerEntity updateBallsFaced(Integer playerId, Integer ballsFaced) {
            PlayerEntity player = getPlayerById(playerId);
            player.setBallsFaced(player.getBallsFaced()+ballsFaced);
            updateBattingStrikeRate(player);
            return playerRepository.save(player);
        }

        @Override
        public PlayerEntity updateBallsBowled(Integer playerId, Integer ballsBowled) {
            PlayerEntity player = getPlayerById(playerId);
            player.setBallsBowled(player.getBallsBowled()+ballsBowled);
            updateBowlingStrikeRate(player);
            return playerRepository.save(player);
        }

        @Override
        public PlayerEntity updateWickets(Integer playerId, Integer wickets) {
            PlayerEntity player = getPlayerById(playerId);
            player.setWickets(wickets);
            updateBowlingStrikeRate(player);
            return playerRepository.save(player);
        }

        @Override
        public PlayerEntity updateMatches(Integer playerId, Integer matches) {
            PlayerEntity player = getPlayerById(playerId);
            player.setMatches(matches);
            return playerRepository.save(player);
        }

        @Override
        public PlayerEntity updateCatches(Integer playerId, Integer catches) {
            PlayerEntity player = getPlayerById(playerId);
            player.setCatches(catches);
            return playerRepository.save(player);
        }

        @Override
        public PlayerEntity updateAllAttributes(Integer playerId, PlayerEntity updatedPlayer) {
            PlayerEntity player = getPlayerById(playerId);

            player.setName(updatedPlayer.getName());
            player.setRuns(updatedPlayer.getRuns());
            player.setBoundaries(updatedPlayer.getBoundaries());
            player.setBallsFaced(updatedPlayer.getBallsFaced());
            player.setBallsBowled(updatedPlayer.getBallsBowled());
            player.setWickets(updatedPlayer.getWickets());
            player.setMatches(updatedPlayer.getMatches());
            player.setCatches(updatedPlayer.getCatches());

            updateBattingStrikeRate(player);
            updateBowlingStrikeRate(player);

            return playerRepository.save(player);
        }

        @Override
        public PlayerEntity getPlayerById(Integer playerId) {
            return playerRepository.findById(playerId)
                    .orElseThrow(() -> new RuntimeException("Player not found"));
        }

        @Override
        public List<PlayerEntity> getAllPlayers() {
            return playerRepository.findAll();
        }

    @Override
    public List<PlayerEntity> getPlayersByPlayerName(String name) {
        List<PlayerEntity> players = playerRepository.findByName(name);
        if (players.isEmpty()) {
            throw new RuntimeException("No players found matching name: " + name);
        }
        return players;
    }


    private void updateBattingStrikeRate(PlayerEntity player) {
            if (player.getBallsFaced() != null && player.getBallsFaced() > 0) {
                float rate = (player.getRuns() * 100f) / player.getBallsFaced();
                player.setBattingStrikeRate(rate);
            }
        }

        private void updateBowlingStrikeRate(PlayerEntity player) {
            if (player.getWickets() != null && player.getWickets() > 0) {
                float rate = (player.getBallsBowled() * 1f) / player.getWickets();
                player.setBowlingStrikeRate(rate);
            }
        }
    @Override
    public Set<TeamEntity> getTeamsByPlayerId(Integer playerId) {
        PlayerEntity player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));
        return player.getTeamEntities();
    }

}
