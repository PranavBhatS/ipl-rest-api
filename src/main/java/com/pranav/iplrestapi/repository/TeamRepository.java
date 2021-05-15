package com.pranav.iplrestapi.repository;

import com.pranav.iplrestapi.model.Team;

import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long>  {

   Team findByTeamName(String teamName);
   
}