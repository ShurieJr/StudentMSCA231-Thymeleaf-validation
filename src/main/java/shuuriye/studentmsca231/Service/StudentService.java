package shuuriye.studentmsca231.Service;

import org.springframework.stereotype.Service;
import shuuriye.studentmsca231.Model.Address;
import shuuriye.studentmsca231.Model.Student;
import shuuriye.studentmsca231.dto.StudentResponse;
import shuuriye.studentmsca231.dto.StudentRequest;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StudentService {
    private Map<Long , Student> students = new ConcurrentHashMap<>();

    private AtomicLong idGenerator = new AtomicLong(000);


    //operations
    public StudentResponse getStudentById(Long id) {
        return StudentResponse.from(students.get(id));
    }

    public Collection<StudentResponse> getAllStudents() {
        return StudentResponse.from(students.values());
    }

    public StudentResponse  createStudent(StudentRequest student) {
      //dto -> entity


        Address newAddress = new Address();
        newAddress.setStreet(student.getAddress().getStreet());
        newAddress.setCity(student.getAddress().getCity());

         Long newId = idGenerator.incrementAndGet();
        Student newStudent = new Student();
        newStudent.setId(newId);
        newStudent.setAddress(newAddress);
        newStudent.setName(student.getName());
        newStudent.setEmail(student.getEmail());
        newStudent.setPasscode(newId + "just");
        newStudent.setCourses(student.getCourses());

        students.put(newId, newStudent);

        return StudentResponse.from(newStudent);
    }

    public void deleteStudent(Long id) {
         students.remove(id);
    }

    public StudentResponse updateStudent( StudentRequest request){
        Student std = students.get(request.getId());

        Address newAddress = new Address();
        newAddress.setStreet(request.getAddress().getStreet());
        newAddress.setCity(request.getAddress().getCity());




        std.setAddress(newAddress);
        std.setName(request.getName());
        std.setEmail(request.getEmail());

        std.setCourses(request.getCourses());

        students.put(request.getId(), std);

        return StudentResponse.from(std);
    }
}
