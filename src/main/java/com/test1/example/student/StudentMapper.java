package com.test1.example.student;

import com.test1.example.school.School;
import jakarta.validation.constraints.Null;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public Student toStudent(StudentDTO dto) {
        if(dto == null){
            throw new NullPointerException("The StudentDTO should not be null");
        }
        Student student = new Student();
        student.setFirstname(dto.firstname());
        student.setLastname(dto.lastname());
        student.setEmail(dto.email());

        School school = new School();
        school.setId(dto.schoolID());
        student.setSchool(school);

        return student;
    }

    //Changes the student object to a record without the schoolID stored here for returning to the client
    public StudentResponseDTO toStudentResponseDTO(Student student) {
        return new StudentResponseDTO(student.getFirstname(),
                student.getLastname(),
                student.getEmail());
    }
}
