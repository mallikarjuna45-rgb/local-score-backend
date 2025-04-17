package com.cricket.local_score.Service.matchEntity;

import com.cricket.local_score.Common.Enums.MatchStatus;
import com.cricket.local_score.Common.Enums.TossDecision;
import com.cricket.local_score.Entity.MatchEntity;
import com.cricket.local_score.Entity.TeamEntity;
import com.cricket.local_score.Repository.*;
import com.cricket.local_score.dto.matchDto;
import com.cricket.local_score.dto.teamDto;
import com.cricket.local_score.request.createMatchRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class matchService implements ImatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    private matchDto convertToDto(MatchEntity matchEntity) {
        return modelMapper.map(matchEntity, matchDto.class);
    }

    @Override
    public matchDto createMatch(createMatchRequest request) {
        MatchEntity match = new MatchEntity();

        match.setName(request.getName());
        match.setMatchDate(request.getMatchDate());
        match.setTotalOvers(request.getTotalOvers());
        match.setTargetRuns(request.getTargetRuns());
        match.setTargetOvers(request.getTargetOvers());
        match.setSuperOver(request.getSuperOver());
        match.setTossDecision(request.getTossDecision());
        match.setMatchStatus(request.getMatchStatus());

        // ✅ Check if Tournament ID is not null before calling findById
        if (request.getTournamentId() != null) {
            match.setTournamentEntity(tournamentRepository.findById(request.getTournamentId()).orElse(null));
        }

        if (request.getPlayerOfMatchId() != null) {
            match.setPlayerEntityOfMatch(playerRepository.findById(request.getPlayerOfMatchId()).orElse(null));
        }

        if (request.getTossWinnerId() != null) {
            match.setTossWinner(teamRepository.findById(request.getTossWinnerId()).orElse(null));
        }

        if (request.getMatchWinnerId() != null) {
            match.setMatchWinner(teamRepository.findById(request.getMatchWinnerId()).orElse(null));
        }

       // if (request.getTeam1Id() != null) {
            match.setTeamEntity1(teamRepository.findById(request.getTeam1Id()).orElse(null));
        //}

        //if (request.getTeam2Id() != null) {
            match.setTeamEntity2(teamRepository.findById(request.getTeam2Id()).orElse(null));
        //}

       // if (request.getOwnerId() != null) {
            match.setOwner(userRepository.findById(request.getOwnerId()).orElse(null));
       // }

       // if (request.getAddressId() != null) {
            match.setAddressEntity(addressRepository.findById(request.getAddressId()).orElse(null));
        //}

        MatchEntity saved = matchRepository.save(match);
        return convertToDto(saved);
    }



    @Override
    public void deleteMatch(Integer matchId) {
        matchRepository.deleteById(matchId);
    }

    @Override
    public matchDto updateMatch(Integer matchId, createMatchRequest updatedDetails) {
        MatchEntity existing = matchRepository.findById(matchId).orElseThrow(() -> new EntityNotFoundException("Match not found"));

        existing.setName(updatedDetails.getName());
        existing.setMatchDate(updatedDetails.getMatchDate());
        existing.setTotalOvers(updatedDetails.getTotalOvers());
        existing.setSuperOver(updatedDetails.getSuperOver());

        return convertToDto(matchRepository.save(existing));
    }

    @Override
    public matchDto getMatchById(Integer matchId) {
        MatchEntity match = matchRepository.findById(matchId).orElseThrow(() -> new EntityNotFoundException("Match not found"));
        return convertToDto(match);
    }

    @Override
    public List<matchDto> findMatchesByName(String name) {
        return matchRepository.findByNameContainingIgnoreCase(name)
                .stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<matchDto> findMatchesByLocation(String location) {
        return matchRepository.findByAddressEntity_Address2ContainingIgnoreCase(location)
                .stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<matchDto> findMatchesByTeamName(String teamName) {
        return matchRepository.findByTeamEntity1_NameIgnoreCaseOrTeamEntity2_NameIgnoreCase(teamName, teamName)
                .stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<matchDto> findMatchesByTournamentName(String tournamentName) {
        return matchRepository.findByTournamentEntity_NameContainingIgnoreCase(tournamentName)
                .stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<matchDto> findAllMatches() {
        return matchRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<matchDto> findAllOngoingMatches() {
        return matchRepository.findByMatchStatus(MatchStatus.ONGOING)
                .stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<matchDto> findAllScheduledMatches() {
        return matchRepository.findByMatchStatus(MatchStatus.SCHEDULED)
                .stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<matchDto> findAllAbandonedMatches() {
        return matchRepository.findByMatchStatus(MatchStatus.ABANDONED)
                .stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<matchDto> findAllUpcomingMatches() {
        return matchRepository.findByMatchDateAfter(new Date())
                .stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public matchDto setTargetDetails(Integer matchId, Integer targetRuns, Integer targetOvers) {
        MatchEntity match = matchRepository.findById(matchId).orElseThrow(() -> new EntityNotFoundException("Match not found"));
        match.setTargetRuns(targetRuns);
        match.setTargetOvers(targetOvers);
        return convertToDto(matchRepository.save(match));
    }

    @Override
    public matchDto setTossWinnerAndStatus(Integer matchId, Integer tossWinnerTeamId, String tossDecisionStr) {
        MatchEntity match = matchRepository.findById(matchId).orElseThrow(() -> new EntityNotFoundException("Match not found"));
        TossDecision tossDecision = TossDecision.valueOf(tossDecisionStr.toUpperCase());
        TeamEntity tossWinner = teamRepository.findById(tossWinnerTeamId).orElseThrow(() -> new EntityNotFoundException("Team not found"));

        match.setTossWinner(tossWinner);
        match.setTossDecision(tossDecision);
        match.setMatchStatus(MatchStatus.ONGOING);

        return convertToDto(matchRepository.save(match));
    }
    @Override
    public List<teamDto> getTeamsByMatchId(Integer matchId) {
        MatchEntity match = matchRepository.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Match not found with ID: " + matchId));

        List<teamDto> teamDtos = new ArrayList<>();

        if (match.getTeamEntity1() != null) {
            teamDtos.add(convertToTeamDto(match.getTeamEntity1()));
        }

        if (match.getTeamEntity2() != null) {
            teamDtos.add(convertToTeamDto(match.getTeamEntity2()));
        }

        return teamDtos;
    }

    private teamDto convertToTeamDto(TeamEntity team) {
        teamDto dto = new teamDto();
        dto.setId(team.getId());
        dto.setName(team.getName());
        // Set other fields if needed
        return dto;
    }

}
