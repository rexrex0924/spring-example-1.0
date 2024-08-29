package com.test1.example.school;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/schools")
    public SchoolDTO create(@RequestBody SchoolDTO dto) {
        return schoolService.storeSchool(dto);
    }

    @GetMapping("/schools")
    public List<SchoolDTO> findAll() {
        return schoolService.findAllSchool();
    }

    @DeleteMapping("/schools/{schoolID}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable(name = "schoolID") Integer id) {
        schoolService.deleteSchool(id);
    }
}
