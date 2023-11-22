package rw.ac.auca.studentpayment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rw.ac.auca.studentpayment.repository.Model.Student;
import rw.ac.auca.studentpayment.service.StudentService;

import java.util.Optional;

@Controller
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping (path = "/students/add")
    public String createStudent(Model model){
        model.addAttribute("student",new Student());
        return "student/addStudent";
    }

    @PostMapping(path = "/student")
    public String saveStudent(Student student){
        studentService.createStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students")
    public String getAllStudents(Model model){
        model.addAttribute("students",studentService.findAll());
        return "student/students";
    }

    @GetMapping (path = "/students/edit/{id}")
    public String editStudent(Model model, @PathVariable(value = "id")String id){
        model.addAttribute("student", studentService.findById(id));
        return "student/addStudent";
    }

    @GetMapping(path = "/students/delete/{id}")
    public String deleteStudent(@PathVariable("id") String id) {
        Optional<Student> studentOptional = studentService.findById(id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            studentService.deleteStudent(student);
        }
        return "redirect:/students";
    }
}
