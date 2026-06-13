package shuuriye.studentmsca231.dto;

import lombok.*;
import shuuriye.studentmsca231.Model.Address;
import shuuriye.studentmsca231.Model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentResponse {
    private Long id;
    private String name;
    private String email;
    private Address address = new Address();
    private List<String> courses;


    //convertion  from entity to dto
    public static StudentResponse from (Student student) {
        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getAddress(),
                student.getCourses());
    }

    public static Collection<StudentResponse> from (Collection<Student> students) {
        List<StudentResponse> studentResponses = new ArrayList<>();

        for(Student student : students) {
            studentResponses.add(from(student));
        }

        return studentResponses;
    }
}
