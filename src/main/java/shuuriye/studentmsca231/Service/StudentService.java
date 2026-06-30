package shuuriye.studentmsca231.Service;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
//    private Map<Long , Student> students = new ConcurrentHashMap<>();

    private AtomicLong idGenerator = new AtomicLong(000);

    private final JdbcTemplate jdbc;  // ?
    private final NamedParameterJdbcTemplate namedjdbc; //:name

    public StudentService(JdbcTemplate jdbc, NamedParameterJdbcTemplate namedjdbc) {
        this.jdbc = jdbc;
        this.namedjdbc = namedjdbc;
    }

    //operations
    public StudentResponse getStudentById(Long id) {
        String sqlQuery = "select * from students where id = ?";
       Student result = jdbc.queryForObject(sqlQuery ,
                new Object[]{id},
                new BeanPropertyRowMapper<>(Student.class));
        return StudentResponse.from(result);
    }

    public Collection<StudentResponse> getAllStudents() {
        String sqlQuery="select * from students";

      Collection<Student> result=  jdbc.query(sqlQuery ,
                new BeanPropertyRowMapper<>(Student.class));

        return StudentResponse.from(result);
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

       String sqlQuery = """
               insert into students values(
               :id,
               :name,
               :email ,
               :passcode,
               :city,
               :street,
               :courses
               )
               """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id" , newId )
                .addValue("email" , student.getEmail())
                .addValue("name" , student.getName())
                .addValue("passcode" , newId + "just")
                .addValue("city" , student.getAddress().getCity())
                .addValue("street" , student.getAddress().getStreet())
                .addValue("courses" , student.getCourses());

        namedjdbc.update(sqlQuery , params);
        return StudentResponse.from(newStudent);
    }

    public void deleteStudent(Long id) {
        String sqlQuery = "delete from students where id= :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id" , id);
        namedjdbc.update(sqlQuery , params);
    }

    public void updateStudent( StudentRequest request){


        String sqlQuery= """
                update students set 
                name = :name,
                email= :email,
                city= :city,
                street= :street,
                courses= :courses
                where id = :id
                """;
        StudentResponse std = getStudentById(request.getId());
MapSqlParameterSource params = new MapSqlParameterSource()
        .addValue("id" , request.getId() )
        .addValue("email" , request.getEmail())
        .addValue("name" , request.getName())
        .addValue("city" , request.getAddress().getCity())
        .addValue("street" , request.getAddress().getStreet())
        .addValue("courses" , request.getCourses());

namedjdbc.update(sqlQuery , params);

    }
}
