package com.finsurge.task51.service;

import com.finsurge.task51.entity.Student;
import com.finsurge.task51.repository.StudentRepository;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceClass {
    @Autowired
    private StudentRepository studentRepository;

    // Save operation
    public Student saveStudent(Student student)
    {
        return studentRepository.save(student);
    }

    // Read operation
    public List<Student> fetchStudentList()
    {
        return (List<Student>)
                studentRepository.findAll(Sort.by("studentId").ascending());
    }

    // Update operation
    public Student
    updateStudent(Student student, Long studentId)
    {
        Student depDB
                = studentRepository.findById(studentId)
                .get();

        if (Objects.nonNull(student.getStudentName())
                && !"".equalsIgnoreCase(
                student.getStudentName())) {
            depDB.setStudentName(
                    student.getStudentName());
        }

        if (Objects.nonNull(student.getStudentDept())
                && !"".equalsIgnoreCase(
                student.getStudentDept())) {
            depDB.setStudentDept(
                    student.getStudentDept());
        }

        if (Objects.nonNull(student.getStudentClg())
                && !"".equalsIgnoreCase(
                student.getStudentClg())) {
            depDB.setStudentClg(
                    student.getStudentClg());
        }

        return studentRepository.save(depDB);
    }

    // Delete operation
    public void deleteStudentById(Long studentId)
    {
        studentRepository.deleteById(studentId);
    }
}


