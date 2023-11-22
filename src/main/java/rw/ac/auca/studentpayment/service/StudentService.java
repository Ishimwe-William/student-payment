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
        return studentRepo.getMatchingStudentIds(query);
    }
    public Optional<Student> findMatchingStudentInfo(String query) {
        return studentRepo.getMatchingStudentInfo(query);
    }
}
