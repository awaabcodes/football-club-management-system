package com.projects.Football.Club.Management.System;

import com.projects.Football.Club.Management.System.entity.Coach;
import com.projects.Football.Club.Management.System.exception.ResourceNotFound;
import com.projects.Football.Club.Management.System.repository.CoachRepo;
import com.projects.Football.Club.Management.System.service.CoachService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CoachServiceTest {

    @Mock
    private CoachRepo coachRepo;

    @InjectMocks
    private CoachService coachService;

    @Test
    void getCoachById_whenCoachExists_returnsCoach() {
        // Arrange
        Coach coach = new Coach();
        coach.setName("Pep");
        when(coachRepo.findById(1)).thenReturn(Optional.of(coach));

        // Act
        Coach result = coachService.getCoachById(1);

        // Assert
        assertEquals("Pep", result.getName());
    }

    @Test
    void getCoachById_whenCoachDoesNotExist_throwsResourceNotFound() {
        // Arrange
        when(coachRepo.findById(99)).thenReturn(Optional.empty());

        // Act + Assert
        ResourceNotFound ex = assertThrows(ResourceNotFound.class, () -> coachService.getCoachById(99));
        assertEquals("Coach not found with this id: 99", ex.getMessage());
    }
}