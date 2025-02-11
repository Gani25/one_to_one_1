package com.sprk.one_to_one.controller;

import com.sprk.one_to_one.entity.Instructor;
import com.sprk.one_to_one.entity.InstructorDetail;
import com.sprk.one_to_one.repository.InstructorDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InstructorDetailController {

    @Autowired
    private InstructorDetailRepository instructorDetailRepository;

    @GetMapping("/instructor_detail/{instructorDetailId}")
    public Instructor getInstructorDetail(@PathVariable int instructorDetailId) {

        InstructorDetail instructorDetail = instructorDetailRepository
                .findById(instructorDetailId)
                .orElseThrow(() -> new RuntimeException("Instructor Detail Not Found"));

        Instructor instructor = instructorDetail.getInstructor();

        return instructor;
    }

    @GetMapping("/instructor_detail")
    public List<Instructor> getInstructorDetailLists() {

        List<InstructorDetail> instructorDetails = instructorDetailRepository
                .findAll();

        List<Instructor> instructors = instructorDetails
                .stream()
//                .map((tempInstructorDetail) -> tempInstructorDetail.getInstructor())
                .map(InstructorDetail::getInstructor)
                .toList();


        return instructors;
    }
}
