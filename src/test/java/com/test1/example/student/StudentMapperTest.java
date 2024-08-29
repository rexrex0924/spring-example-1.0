package com.test1.example.student;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDTOtoStudent(){
        StudentDTO dto = new StudentDTO(
                "John",
                "Doe",
                "john@gmail.com",
                1);

        Student student = mapper.toStudent(dto);

        assertEquals(dto.firstname(), student.getFirstname());
        assertEquals(dto.lastname(), student.getLastname());
        assertEquals(dto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(dto.schoolID(), student.getSchool().getId());

    }

    @Test
    public void should_throw_NullPointerException_when_studentDTO_is_null(){
        var message = assertThrows(NullPointerException.class, () -> mapper.toStudent(null));
        assertEquals("The StudentDTO should not be null", message.getMessage());
    }

    @Test
    public void shouldMapStudentToStudentResponseDTO(){
        Student student = new Student("John", "Doe", "john@gmail.com", 1);
        StudentResponseDTO dto = mapper.toStudentResponseDTO(student);

        assertEquals(dto.firstname(), student.getFirstname());
        assertEquals(dto.lastname(), student.getLastname());
        assertEquals(dto.email(), student.getEmail());

    }



}