package com.pranav.iplrestapi.controller;

import java.time.LocalDate;
import java.util.List;

import com.pranav.iplrestapi.model.Match;
import com.pranav.iplrestapi.model.Team;
import com.pranav.iplrestapi.repository.MatchRepository;
import com.pranav.iplrestapi.repository.TeamRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class TeamController {

   private final TeamRepository teamRepository;
   private final MatchRepository matchRepository;

   public TeamController(final TeamRepository teamRepository, final MatchRepository matchRepository) {
      this.teamRepository = teamRepository;
      this.matchRepository = matchRepository;
   }

   @GetMapping("/team")
   public Iterable<Team> getAllTeam() {
      return this.teamRepository.findAll();
   }

   @GetMapping("/team/{teamName}")
   public Team getTeam(@PathVariable final String teamName) {
      final Team team = this.teamRepository.findByTeamName(teamName);
      team.setMatches(matchRepository.findLatestMatchesbyTeam(teamName, 4));

      return team;
   }

   @GetMapping("/team/{teamName}/matches")
   public List<Match> getMatchesForTeam(@PathVariable final String teamName, @RequestParam final int year) {
      final LocalDate startDate = LocalDate.of(year, 1, 1);
      final LocalDate endDate = LocalDate.of(year + 1, 1, 1);
      return this.matchRepository.getMatchesByTeamBetweenDates(teamName, startDate, endDate);
   }

}