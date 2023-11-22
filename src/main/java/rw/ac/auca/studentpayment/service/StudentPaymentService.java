package rw.ac.auca.studentpayment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.studentpayment.repository.Model.Student;
import rw.ac.auca.studentpayment.repository.Model.StudentPayment;
import rw.ac.auca.studentpayment.repository.StudentPaymentRepo;

import java.util.List;
import java.util.Optional;

@Service
public class StudentPaymentService {
    private StudentPaymentRepo studentPaymentRepo;

    @Autowired
    public StudentPaymentService(StudentPaymentRepo studentPaymentRepo) {
        this.studentPaymentRepo = studentPaymentRepo;
    }
    
    public List<StudentPayment> findAllPayments() {
        return studentPaymentRepo.findAll();

    }
    public void createStudentPayment(StudentPayment payment) {
        studentPaymentRepo.save(payment);
    }

    public void deleteStudentPayment(String id) {
        Optional<StudentPayment> payment=findPaymentsById(id);
        if(payment.isPresent()){
            studentPaymentRepo.delete(payment.get());
        }
    }

    public Optional<StudentPayment> findPaymentsById(String id) {
        return studentPaymentRepo.findById(id);
    }

}
