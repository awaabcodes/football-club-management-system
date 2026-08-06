package com.projects.Football.Club.Management.System.controller;

import com.projects.Football.Club.Management.System.entity.Coach;
import com.projects.Football.Club.Management.System.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CoachController {

    @Autowired
    CoachService coachService;

    @GetMapping("/coach")
    public List<Coach> getAllCoach(){
        return coachService.getAllCoach();
    }

    @GetMapping("/coach/{coachId}")
    public Coach getCoachById(@PathVariable int coachId){
        return coachService.getCoachById(coachId);
    }

    @PostMapping("/coach")
    public void addCoach(@RequestBody Coach coach){
        coachService.addCoach(coach);
    }

    @PutMapping("/coach")
    public void updateCoach(@RequestBody Coach coach){
        coachService.updateCoach(coach);
    }

    @DeleteMapping("/coach/{coachId}")
    public void deleteCoach(@PathVariable int coachId){
        coachService.deleteCoach(coachId);
    }

    @PutMapping("/coach/assignCoach/{teamId}/{coachId}")
    public void assignCoach(int teamId,int coachId){
        coachService.assignCoach(teamId,coachId);
    }

    @PutMapping("/coach/removeCoach/{teamId}/{coachId}")
    public void removeCoach(@PathVariable int teamId,@PathVariable int coachId){
        coachService.removeCoach(teamId,coachId);
    }

    @PutMapping("/coach/transferCoach/{teamId1}/{teamId2}/{coachId}")
    public void transferCoach(@PathVariable int teamId1,@PathVariable int teamId2,@PathVariable int coachId){
        coachService.transferCoach(teamId1,teamId2,coachId);
    }
}
