package com.sprk.one_to_one.controller;

import com.sprk.one_to_one.entity.Instructor;
import com.sprk.one_to_one.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InstructorController {

    @Autowired
    private InstructorRepository instructorRepository;

    @PostMapping("/instructor")
    public Instructor instructor(@RequestBody Instructor instructor) {
        System.out.println(instructor);
        Instructor savedInstructor = instructorRepository.save(instructor);
        return savedInstructor;
    }
}
