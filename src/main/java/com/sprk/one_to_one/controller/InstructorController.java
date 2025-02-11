package com.sprk.one_to_one.controller;

import com.sprk.one_to_one.entity.Instructor;
import com.sprk.one_to_one.entity.InstructorDetail;
import com.sprk.one_to_one.repository.InstructorDetailRepository;
import com.sprk.one_to_one.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InstructorController {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private InstructorDetailRepository instructorDetailRepository;

    @PostMapping("/instructor")
    public Instructor instructor(@RequestBody Instructor instructor) {
        System.out.println(instructor);
        Instructor savedInstructor = instructorRepository.save(instructor);
        return savedInstructor;
    }

    @GetMapping("/instructor")
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    @GetMapping("/instructor/{instructorId}")
    public Instructor getInstructorById(@PathVariable int instructorId) {
        return instructorRepository.findById(instructorId).orElseThrow(() -> new RuntimeException("Instrcutor Not Found"));
    }

    // Insert Only Instructor Detail or if instructor detail is present then update with new instructor detail
    @PutMapping("/instructor/instructor_detail/{instructorId}")
    public Instructor updateInstructorDetail(@PathVariable int instructorId, @RequestBody InstructorDetail instructorDetail) {

        Instructor existingInstructor = getInstructorById(instructorId);
        InstructorDetail existingInstructorDetail = existingInstructor.getInstructorDetail();
        if(existingInstructorDetail != null){
            instructorDetail.setInstructorDetailId(existingInstructorDetail.getInstructorDetailId()); // update

        }

        if(instructorDetail.getHobby() == null){
            instructorDetail.setHobby(existingInstructorDetail.getHobby());
        }
        if(instructorDetail.getQualification() == null){
            instructorDetail.setQualification(existingInstructorDetail.getQualification());
        }
        instructorDetail.setInstructorDetailId(existingInstructorDetail.getInstructorDetailId());

        existingInstructor.setInstructorDetail(instructorDetail);

        return instructorRepository.save(existingInstructor);
    }

    // Only update instructor object
    @PutMapping("/instructor/{instructorId}")
    public Instructor updateInstructor(@PathVariable int instructorId, @RequestBody Instructor instructor) {

        Instructor existingInstructor = getInstructorById(instructorId);
        InstructorDetail existingInstructorDetail = existingInstructor.getInstructorDetail();

        if(instructor.getEmail() == null){
            instructor.setEmail(existingInstructor.getEmail());
        }
        if(instructor.getFirstName() == null){
            instructor.setFirstName(existingInstructor.getFirstName());
        }
        if(instructor.getLastName() == null){
            instructor.setLastName(existingInstructor.getLastName());
        }
        if(instructor.getInstructorDetail() == null){
            instructor.setInstructorDetail(existingInstructorDetail);
        }

        instructor.setId(existingInstructor.getId());

        return instructorRepository.save(instructor);
    }

    // Delete only instructor detail
    @DeleteMapping("/instructor/instructor_detail/{instructorId}")
    public Instructor deleteInstructorDetail(@PathVariable int instructorId) {


        Instructor existingInstructor = getInstructorById(instructorId);
        InstructorDetail existingInstructorDetail = existingInstructor.getInstructorDetail();
        existingInstructor.setInstructorDetail(null);
        instructorDetailRepository.delete(existingInstructorDetail);
        return instructorRepository.save(existingInstructor);

    }

    @DeleteMapping("/instructor/{instructorId}")
    public Instructor deleteInstructor(@PathVariable int instructorId) {
        Instructor existingInstructor = getInstructorById(instructorId);
        instructorRepository.delete(existingInstructor);
        return existingInstructor;
    }


}


