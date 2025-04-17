package com.cricket.local_score.Service.tournmentEntity;

import com.cricket.local_score.Entity.*;
import com.cricket.local_score.Repository.*;
import com.cricket.local_score.dto.playerDto;
import com.cricket.local_score.dto.teamDto;
import com.cricket.local_score.dto.tournamentDto;
import com.cricket.local_score.request.CreateTournamentRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class tournmentService implements ItournmentService {

    private final TournamentRepository tournamentRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;  // Injecting ModelMapper

    @Override
    public List<tournamentDto> getAllTournaments() {
        return tournamentRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, tournamentDto.class))  // Using injected ModelMapper
                .collect(Collectors.toList());
    }

    @Override
    public List<tournamentDto> getOngoingTournaments() {
        return tournamentRepository.findAll().stream()
                .filter(t -> "ONGOING".equalsIgnoreCase(t.getStatus()))
                .map(entity -> modelMapper.map(entity, tournamentDto.class))  // Using injected ModelMapper
                .collect(Collectors.toList());
    }

    @Override
    public List<tournamentDto> getScheduledTournaments() {
        return tournamentRepository.findAll().stream()
                .filter(t -> "SCHEDULED".equalsIgnoreCase(t.getStatus()))
                .map(entity -> modelMapper.map(entity, tournamentDto.class))  // Using injected ModelMapper
                .collect(Collectors.toList());
    }

    @Override
    public List<tournamentDto> getPastTournaments() {
        return tournamentRepository.findAll().stream()
                .filter(t -> "PAST".equalsIgnoreCase(t.getStatus()))
                .map(entity -> modelMapper.map(entity, tournamentDto.class))  // Using injected ModelMapper
                .collect(Collectors.toList());
    }

    @Override
    public List<tournamentDto> getTournamentsByLocation(String location) {
        return tournamentRepository.findByAddressEntity_Address2ContainingIgnoreCase(location)
                .stream()
                .map(this::convertToTournamentDto)  // Assuming you have a method to convert to tournamentDto
                .collect(Collectors.toList());
    }

    public tournamentDto convertToTournamentDto(TournamentEntity entity) {
        return modelMapper.map(entity, tournamentDto.class);
    }

    @Override
    public tournamentDto getTournamentById(Integer tournamentId) {
        TournamentEntity entity = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new NoSuchElementException("Tournament not found"));
        return modelMapper.map(entity, tournamentDto.class);  // Using injected ModelMapper
    }

    @Override
    public tournamentDto createTournament(CreateTournamentRequest request) {
        if (request.getName() == null || request.getStartDate() == null || request.getEndDate() == null ||
                request.getStatus() == null || request.getOwnerId() == null || request.getAddressId() == null) {
            throw new IllegalArgumentException("Missing required fields for tournament creation");
        }

        TournamentEntity entity = new TournamentEntity();
        entity.setName(request.getName());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setStatus(request.getStatus());

        UserEntity owner = userRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new NoSuchElementException("Owner not found"));
        entity.setOwner(owner);

        AddressEntity address = addressRepository.findById(request.getAddressId())
                .orElseThrow(() -> new NoSuchElementException("Address not found"));
        entity.setAddressEntity(address);

        TournamentEntity saved = tournamentRepository.save(entity);
        return modelMapper.map(saved, tournamentDto.class);
    }


    @Override
    public tournamentDto updateTournament(Integer tournamentId, CreateTournamentRequest request) {
        TournamentEntity entity = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new NoSuchElementException("Tournament not found"));

        entity.setName(request.getName());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setStatus(request.getStatus());

        UserEntity owner = userRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new NoSuchElementException("Owner not found"));
        entity.setOwner(owner);

        AddressEntity address = addressRepository.findById(request.getAddressId())
                .orElseThrow(() -> new NoSuchElementException("Address not found"));
        entity.setAddressEntity(address);

        TournamentEntity saved = tournamentRepository.save(entity);
        return modelMapper.map(saved, tournamentDto.class);
    }


    @Override
    public void deleteTournament(Integer tournamentId) {
        tournamentRepository.deleteById(tournamentId);
    }

    @Override
    public List<teamDto> getTeamsInTournament(Integer tournamentId) {
        TournamentEntity entity = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new NoSuchElementException("Tournament not found"));
        return entity.getTeamEntities().stream()
                .map(teamEntity -> modelMapper.map(teamEntity, teamDto.class))  // Using injected ModelMapper
                .collect(Collectors.toList());
    }

    @Override
    public List<playerDto> getPlayersInTournament(Integer tournamentId) {
        TournamentEntity entity = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new NoSuchElementException("Tournament not found"));
        return entity.getTeamEntities().stream()
                .flatMap(team -> team.getPlayerEntities().stream())
                .map(playerEntity -> modelMapper.map(playerEntity, playerDto.class))  // Using injected ModelMapper
                .collect(Collectors.toList());
    }

//    @Override
//    public void addTeamsToTournament(Integer tournamentId, List<Integer> teamIds) {
//        TournamentEntity tournament = tournamentRepository.findById(tournamentId)
//                .orElseThrow(() -> new NoSuchElementException("Tournament not found"));
//
//        Set<TeamEntity> teamsToAdd = teamIds.stream()
//                .map(id -> teamRepository.findById(id)
//                        .orElseThrow(() -> new NoSuchElementException("Team not found with id: " + id)))
//                .collect(Collectors.toSet());
//
//        // Initialize if null
//        if (tournament.getTeamEntities() == null) {
//            tournament.setTeamEntities(new HashSet<>());
//        }
//
//        tournament.getTeamEntities().addAll(teamsToAdd);
//        tournament.setTotalTeams(tournament.getTeamEntities().size()); // optional update
//        tournamentRepository.save(tournament);
//    }
@Override
public void addTeamToTournament(Integer tournamentId, String teamName) {
    TournamentEntity tournament = tournamentRepository.findById(tournamentId)
            .orElseThrow(() -> new NoSuchElementException("Tournament not found"));

    TeamEntity team = teamRepository.findByName(teamName)
            .orElseThrow(() -> new NoSuchElementException("Team not found with name: " + teamName));

    Set<TeamEntity> teamEntities = tournament.getTeamEntities();
    if (teamEntities == null) {
        teamEntities = new HashSet<>();
    }

    teamEntities.add(team);
    tournament.setTeamEntities(teamEntities);
    tournament.setTotalTeams(teamEntities.size()); // update totalTeams if needed
    tournamentRepository.save(tournament);
}




    @Override
    public void removeTeamFromTournament(Integer tournamentId, Integer teamId) {
        TournamentEntity tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new NoSuchElementException("Tournament not found"));
        tournament.getTeamEntities().removeIf(team -> team.getId().equals(teamId));
        tournamentRepository.save(tournament);
    }
}
