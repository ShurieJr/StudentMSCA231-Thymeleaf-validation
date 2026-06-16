package shuuriye.studentmsca231.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import shuuriye.studentmsca231.Model.Address;
import shuuriye.studentmsca231.Service.StudentService;
import shuuriye.studentmsca231.Validation.ValidationGroups;
import shuuriye.studentmsca231.dto.AddressRequest;
import shuuriye.studentmsca231.dto.StudentRequest;
import shuuriye.studentmsca231.dto.StudentResponse;

@Controller
@RequestMapping("/student")
public class StudentWebController {
    private final StudentService studentService;

    public StudentWebController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/home")
    public String getStudent(Model model) {
        model.addAttribute("Title", "Student MS");
        model.addAttribute("message", "<i>Welcome to Student MS</i>");

        model.addAttribute("students", studentService.getAllStudents());
        return "home";
    }

    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/student/home";
    }

    //showForm
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("student", new StudentRequest());  //dto
        return "form";
    }

    //register new student
    @PostMapping("/register")
    public String registerStudent(@Validated(ValidationGroups.Create.class)
                                  @ModelAttribute("student") StudentRequest form,
                                  BindingResult binding) {
        if (binding.hasErrors()) {
            return "form";
        }
        studentService.createStudent(form);
        return "redirect:/student/home";
    }

    //show update page
    @GetMapping("/edit/{id}")
    public String showUpdatePage(@PathVariable Long id , Model model){
        StudentResponse std = studentService.getStudentById(id);

        StudentRequest req = new StudentRequest();

        AddressRequest address = new AddressRequest();
        address.setCity(std.getAddress().getCity());
        address.setStreet(std.getAddress().getStreet());

        req.setId(id);
        req.setName(std.getName());
        req.setEmail(std.getEmail());
        req.setAddress(address);
        req.setCourses(std.getCourses());

        model.addAttribute("student" , req);
        return "update";
    }

    //update operation
    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id,
                                @Validated(ValidationGroups.Update.class)
                                @ModelAttribute("student") StudentRequest updateRequest,
                                BindingResult result){
        if(result.hasErrors()){
            return "update";
        }

        //set id
        updateRequest.setId(id);

        studentService.updateStudent(updateRequest);

        return "redirect:/student/home";

    }

}
