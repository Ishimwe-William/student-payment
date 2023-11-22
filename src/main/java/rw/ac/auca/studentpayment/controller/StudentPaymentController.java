package rw.ac.auca.studentpayment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.studentpayment.repository.Model.StudentPayment;
import rw.ac.auca.studentpayment.service.StudentPaymentService;
import rw.ac.auca.studentpayment.service.StudentService;

import java.util.List;


@Controller
public class StudentPaymentController {
    private StudentPaymentService studentPaymentService;

    private StudentService studentService;

    public StudentPaymentController(StudentPaymentService studentPaymentService, StudentService studentService) {
        this.studentPaymentService = studentPaymentService;
        this.studentService = studentService;
    }

    @GetMapping("/payments")
    public String showPaymentPage(){
        return "studentPayment/payments";
    }

    @GetMapping("/search")
    @ResponseBody
    public List<String> autocompleteStudentIds(@RequestParam("query") String query) {
        List<String> matchingStudentIds = studentService.findMatchingStudentIds(query);
        return matchingStudentIds;
    }

    @GetMapping("/student-payments/{id}")
    public String getAllStudentPayments(Model model, @PathVariable String id){
        model.addAttribute("payments",studentPaymentService.findPaymentsById(id));
        return "studentPayment/student-payments";
    }

    @GetMapping(path = "/student-payments/add/{id}")
    public String addPayment(Model model, @PathVariable String id){
        model.addAttribute("payment",new StudentPayment());
        return "studentPayment/new-payment";
    }

    @PostMapping(path = "/payment")
    public String savePayment(StudentPayment payment){
        studentPaymentService.createStudentPayment(payment);
        return "redirect:/student-payments/{id}";
    }
}
