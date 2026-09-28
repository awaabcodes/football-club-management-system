package com.projects.Football.Club.Management.System.test;

import com.projects.Football.Club.Management.System.entity.Coach;
import com.projects.Football.Club.Management.System.exception.ResourceNotFound;
import com.projects.Football.Club.Management.System.repository.CoachRepo;
import com.projects.Football.Club.Management.System.service.CoachService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


public class CoachServiceTest {
    @Mock
    private CoachRepo coachRepo;

    @InjectMocks
    private CoachService coachService;

    @Test
    void getCoachById_whenCoachExists_returnCoach(){

        Coach coach = new Coach();
        coach.setName("Pep");
        when(coachRepo.findById(1)).thenReturn(Optional.of(coach));

        Coach result = coachService.getCoachById(1);

        assertEquals("Pep",result.getName());
    }
    @Test
    void getCoachById_whenCoachDoesNotExist_throwResourceNotFound(){

        when(coachRepo.findById(99)).thenReturn(Optional.empty());

        ResourceNotFound exception = assertThrows(ResourceNotFound.class, ()->coachService.getCoachById(99));
        assertEquals("Coach not found with this id: 99", exception.getMessage());
    }
}
