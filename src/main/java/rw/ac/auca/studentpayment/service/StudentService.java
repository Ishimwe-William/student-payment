package rw.ac.auca.studentpayment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.studentpayment.repository.Model.Student;
import rw.ac.auca.studentpayment.repository.StudentRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> findAll() {
      return studentRepo.findAll();

    }
    public void createStudent(Student student) {
       studentRepo.save(student);
    }

    public void deleteStudent(Student student) {
            studentRepo.delete(student);
    }

    public Optional<Student> findById(String id) {
      return studentRepo.findById(id);
    }

    public List<String> findMatchingStudentIds(String query) {
        List<Student> matchingStudents = studentRepo.findByRegNoContainingIgnoreCase(query);

        // Extract student IDs from matching students and return as a List<String>
        return matchingStudents.stream()
                .map(Student::getRegNo)
                .collect(Collectors.toList());
    }
}
