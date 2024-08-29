package com.test1.example.student;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {
    private final StudentRepository repository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository repository, StudentMapper studentMapper) {
        this.repository = repository;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDTO saveStudent(StudentDTO dto) {
        var savedStudent = repository.save(studentMapper.toStudent(dto));     //repository.save returns a student object
        return studentMapper.toStudentResponseDTO(savedStudent);      //changes student object to a record
    }

    public List<StudentResponseDTO> findAll(){
        return repository.findAll()
                .stream()
                .map(studentMapper::toStudentResponseDTO).toList();
    }

    public StudentResponseDTO findById(Integer id) {
        return studentMapper.toStudentResponseDTO(repository.findById(id).orElse(new Student()));
    }

    public List<StudentResponseDTO> findStudentByFirstnameContaining(String name) {
        return repository.findAllByFirstnameContaining(name);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
