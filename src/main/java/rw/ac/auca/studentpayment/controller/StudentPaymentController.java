package rw.ac.auca.studentpayment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.studentpayment.repository.Model.Student;
import rw.ac.auca.studentpayment.repository.Model.StudentPayment;
import rw.ac.auca.studentpayment.service.StudentPaymentService;
import rw.ac.auca.studentpayment.service.StudentService;

import java.util.List;
import java.util.Optional;


@Controller
public class StudentPaymentController {
    private StudentPaymentService studentPaymentService;

    private StudentService studentService;

    @Autowired
    public StudentPaymentController(StudentPaymentService studentPaymentService, StudentService studentService) {
        this.studentPaymentService = studentPaymentService;
        this.studentService = studentService;
    }

    @GetMapping("/payments")
    public String showPaymentPage() {
        return "studentPayment/payments";
    }

    @RequestMapping(value = "/search")
    @ResponseBody
    public List<String> getStudentId(@RequestParam(value = "query", required = false, defaultValue = "") String query) {
        List<String> ids = studentService.findMatchingStudentIds(query);
        return ids;
    }

    @GetMapping("/getStudentInfo")
    @ResponseBody
    public ResponseEntity<?> getStudentInfo(@RequestParam String regNo) {
        try {
            Optional<Student> student = studentService.findMatchingStudentInfo(regNo);
            if (student != null) {
                return new ResponseEntity<>(student, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/student-payments")
    public String getStudentPayments(@RequestParam String id, Model model) {
        List<StudentPayment> payments = studentPaymentService.findPaymentsByRegNo(id);
        model.addAttribute("payments", payments);
        return "studentPayment/payments";
    }

    @GetMapping("/payments/recent")
    public String getRecentPayments(Model model) {
        List<StudentPayment> recentPayments = studentPaymentService.findAllPayments();
        model.addAttribute("payments", recentPayments);
        return "fragments/left-sidebar :: left-sidebar-content";
    }

}
