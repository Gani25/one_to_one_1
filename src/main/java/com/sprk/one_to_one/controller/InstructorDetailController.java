package com.sprk.one_to_one.controller;

import com.sprk.one_to_one.entity.InstructorDetail;
import com.sprk.one_to_one.repository.InstructorDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InstructorDetailController {

    @Autowired
    private InstructorDetailRepository instructorDetailRepository;

    @GetMapping("/instructor_detail/{instructorDetailId}")
    public InstructorDetail getInstructorDetail(@PathVariable int instructorDetailId) {

        return instructorDetailRepository
                .findById(instructorDetailId)
                .orElseThrow(() -> new RuntimeException("Instructor Detail Not Found"));
    }
}
