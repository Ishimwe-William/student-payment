package rw.ac.auca.studentpayment.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rw.ac.auca.studentpayment.repository.Model.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, String> {
    @Modifying
    @Query(value = "SELECT s.reg_no FROM student s WHERE s.reg_no LIKE %:query%", nativeQuery = true)
    List<String> getMatchingStudentIds(@Param("query") String query);

    @Query(value = "SELECT * FROM student s WHERE s.reg_no LIKE :query", nativeQuery = true)
    Optional<Student> getMatchingStudentInfo(@Param("query") String query) throws DataAccessException;

}

