package shuuriye.studentmsca231.Controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import shuuriye.studentmsca231.Service.StudentService;
import shuuriye.studentmsca231.dto.StudentResponse;
import shuuriye.studentmsca231.Validation.ValidationGroups;
import shuuriye.studentmsca231.dto.StudentRequest;

import java.util.Collection;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //requests
    @GetMapping
    public Collection<StudentResponse> getStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("{id}")
    public StudentResponse getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public StudentResponse createStudent(@Validated(ValidationGroups.Create.class)
                                             @RequestBody StudentRequest studentRequest) {
        return studentService.createStudent(studentRequest);
    }
}
