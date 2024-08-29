package com.test1.example.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    //which service we want to test
    @InjectMocks
    private StudentService studentService;

    //declare the dependencies
    @Mock
    private StudentRepository repository;

    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_a_student() {
        //Given
        StudentDTO dto = new StudentDTO(
                "John",
                "Doe",
                "john@gmail.com",
                1);
        Student student  = new Student(
                "John",
                "Doe",
                "john@gmail.com",
                20);
        Student savedstudent  = new Student(
                "John",
                "Doe",
                "john@gmail.com",
                20);
        savedstudent.setId(1);

        //Mock the calls
        when(studentMapper.toStudent(dto)).thenReturn(student);
        when(repository.save(student)).thenReturn(savedstudent);
        when(studentMapper.toStudentResponseDTO(savedstudent))
                .thenReturn(new StudentResponseDTO(
                        "John",
                        "Doe",
                        "john@gmail.com"));

        //When
        StudentResponseDTO responseDTO = studentService.saveStudent(dto);

        //Then
        assertEquals(dto.firstname(), responseDTO.firstname());
        assertEquals(dto.lastname(), responseDTO.lastname());
        assertEquals(dto.email(), responseDTO.email());

        verify(studentMapper, times(1)).toStudent(dto);
        verify(repository, times(1)).save(student);
        verify(studentMapper, times(1)).toStudentResponseDTO(savedstudent);
    }

    @Test
    public void should_return_all_students() {

        //Given
        List<Student> students = new ArrayList<>();
        students.add(new Student("John", "Doe", "john@gmail.com", 1));

        //When
        when(repository.findAll()).thenReturn(students);
        when(studentMapper.toStudentResponseDTO(any(Student.class)))
                .thenReturn(new StudentResponseDTO(
                        "John",
                        "Doe",
                        "john@gmail.com"));

        List<StudentResponseDTO> responseDTOs = studentService.findAll();

        //Then
        assertEquals(students.size(), responseDTOs.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    public void should_return_correct_students_according_to_id() {

        //Given
        Student student = new Student("John", "Doe", "john@gmail.com", 1);

        //When
        when(repository.findById(1)).thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponseDTO(student))
                .thenReturn(new StudentResponseDTO(
                        "John",
                        "Doe",
                        "john@gmail.com"));

        StudentResponseDTO responseDTO = studentService.findById(1);

        assertEquals(student.getFirstname(), responseDTO.firstname());
        assertEquals(student.getLastname(), responseDTO.lastname());
        assertEquals(student.getEmail(), responseDTO.email());

        verify(repository, times(1)).findById(1);
        verify(studentMapper, times(1)).toStudentResponseDTO(student);

    }

    @Test
    public void should_return_correct_students_according_to_name() {

        //Given
        List<StudentResponseDTO> students = new ArrayList<>();
        String name = "Jo";
        students.add(new StudentResponseDTO("John", "Doe", "john@gmail.com"));

        //When
        when(repository.findAllByFirstnameContaining(name))
                .thenReturn(students);
        when(studentMapper.toStudentResponseDTO(any(Student.class)))
                .thenReturn(new StudentResponseDTO(
                        "John",
                        "Doe",
                        "john@gmail.com"));

        var responseDTO = studentService.findStudentByFirstnameContaining(name);

        assertEquals(students.size(), responseDTO.size());
        verify(repository, times(1)).findAllByFirstnameContaining(name);

    }
}