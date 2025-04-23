package com.imentor.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(Long id) {
        // For now, just return the first student as placeholder
        return studentRepository.findAll().stream().findFirst().orElse(null);
    }

    public Student getStudentById(String id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }
}
