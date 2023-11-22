package rw.ac.auca.studentpayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rw.ac.auca.studentpayment.repository.Model.StudentPayment;

import java.util.List;

@Repository
public interface StudentPaymentRepo extends JpaRepository<StudentPayment,String> {
    @Query(value = "SELECT * FROM Student_Payment sp WHERE sp.student_id = (SELECT s.id FROM student s WHERE s.reg_no LIKE :reg)", nativeQuery = true)
    List<StudentPayment> findAllByStudent_RegNo(@Param("reg") String reg);
}
