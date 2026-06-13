package shuuriye.studentmsca231.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {
    private Long id;
    private String name;
    private String email;
    private String passcode;

    private Address address = new Address();
    private List<String> courses;
}
