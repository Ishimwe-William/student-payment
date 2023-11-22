package rw.ac.auca.studentpayment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ac.auca.studentpayment.repository.Model.StudentPayment;

import java.util.List;

@Repository
public interface StudentPaymentRepo extends JpaRepository<StudentPayment,String> {
}
