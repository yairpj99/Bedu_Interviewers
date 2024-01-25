package org.bedu.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.bedu.controller.InterviewerController;
import org.bedu.dto.CreateInterviewerDTO;
import org.bedu.dto.InterviewerDTO;
import org.bedu.service.InterviewerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class InterviewerControllerTest {

    @MockBean
    private InterviewerService service;
    
    @Autowired
    private InterviewerController controller;

    @Test
    @DisplayName("Controller should be injected")
    public void smokeTest(){
        assertNotNull(controller);
    }

    @Test
    @DisplayName("Controller should return an interviewer's list")
    public void findAllTest(){
        List<InterviewerDTO> fakeList = new LinkedList<>();

        //Act
        List<InterviewerDTO> result = controller.findAll();

        //Assert
        assertEquals(fakeList, result);
    }

    @Test
    @DisplayName("controller should save an interviewer")
    public void saveTest(){
        CreateInterviewerDTO data = new CreateInterviewerDTO();
        data.setName("Ejemplo del nombre");
        data.setEmail("ejemplo@correo.com");

        InterviewerDTO saved = new InterviewerDTO();

        saved.setName("Ejemplo del nombre");
        saved.setEmail("ejemplo@correo.com");

        when(service.save(any(CreateInterviewerDTO.class))).thenReturn(saved);

        InterviewerDTO result = controller.save(data);

        assertNotNull(result);
        assertEquals(saved, result);
        assertEquals(data.getName(),result.getName());
        assertEquals(data.getEmail(),result.getEmail());
    }

}
