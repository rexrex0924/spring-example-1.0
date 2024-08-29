package com.test1.example.school;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }

    public SchoolDTO storeSchool(SchoolDTO dto) {
        schoolRepository.save(schoolMapper.toSchool(dto));
        return dto;
    }

    public List<SchoolDTO> findAllSchool() {
        return schoolRepository.findAll()
                .stream()
                .map(schoolMapper::toSchoolDTO).toList();
    }

    public void deleteSchool(Integer id) {
        schoolRepository.deleteById(id);
    }
}
