package shuuriye.studentmsca231.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import shuuriye.studentmsca231.Service.StudentService;
import shuuriye.studentmsca231.Validation.ValidationGroups;
import shuuriye.studentmsca231.dto.StudentRequest;

@Controller
@RequestMapping("/student")
public class StudentWebController {
    private final StudentService studentService;

    public StudentWebController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/home")
    public String getStudent(Model model){
        model.addAttribute("Title" , "Student MS");
        model.addAttribute("message" , "<i>Welcome to Student MS</i>");

        model.addAttribute("students" , studentService.getAllStudents() );
        return "home";
    }

    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return "redirect:/student/home";
    }

    //showForm
    @GetMapping("/form")
    public String showForm(Model model){
        model.addAttribute("student" , new StudentRequest());  //dto
        return "form";
    }

    //register new student
    @PostMapping("/register")
    public String registerStudent(@Validated(ValidationGroups.Create.class)
            @ModelAttribute("student") StudentRequest form,
                                  BindingResult binding){
        if(binding.hasErrors()){
            return "form";
        }
        studentService.createStudent(form);
        return "redirect:/student/home";
    }
}
