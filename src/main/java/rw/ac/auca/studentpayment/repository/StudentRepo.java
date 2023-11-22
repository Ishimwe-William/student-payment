package rw.ac.auca.studentpayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ac.auca.studentpayment.repository.Model.Student;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, String> {
    List<Student> findByRegNoContainingIgnoreCase(String query);
}
