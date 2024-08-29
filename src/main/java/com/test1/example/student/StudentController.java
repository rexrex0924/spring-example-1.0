package com.test1.example.student;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


/*
DTO Logic:
1. HTTP Client sends the information to the dto record
(Only firstname, lastname, email and schoolID are required as defined)

2. Since repository can only save Student object, toStudent method will
inject all the required information into a student object and return it to the caller

3. In the post method, repository.save returns a Student Object that contains the schoolID
which is considered as a sensitive data that needs to be hidden from the Client. Therefore,
toStudentResponseDTO method will encapsulate the schoolID and return a record with only 3 fields:
firstname, lastname and email.

Advantages: Do not require all the information to be passed into a student object,
REST API can decide which information to be requested from the HTTP Client
--> Data is more manageable and secure.
*/

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // This is a read option in the CRUD Operations
    @GetMapping("/students")
    public List<StudentResponseDTO> findAllStudent() {
        return studentService.findAll();
    }

    // This is a create option in the CRUD Operations
    //Receives a record -> changes to a student object -> back to a record
    //Body of the request is "message"
    @PostMapping("/students")
    public StudentResponseDTO saveStudent(@Valid @RequestBody StudentDTO dto) {
        return studentService.saveStudent(dto);
    }

    // This is a read option in the CRUD Operations
    @GetMapping("/students/{student-id}")
    public StudentResponseDTO findStudentById(@PathVariable("student-id") int id) {
        return studentService.findById(id);
    }

    //This is a custom function defined in the interface StudentRepository
    @GetMapping("/students/search/{student-name}")
    public List<StudentResponseDTO> findStudentByName(@PathVariable("student-name") String name) {
        return studentService.findStudentByFirstnameContaining(name);
    }

    @DeleteMapping("/students/{students-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void DeleteStudentById(@PathVariable("students-id") int id) {
        studentService.deleteById(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        var errors = new HashMap<String, String>();
        e.getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
