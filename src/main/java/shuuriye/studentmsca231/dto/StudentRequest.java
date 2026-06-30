package shuuriye.studentmsca231.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shuuriye.studentmsca231.Model.Address;
import shuuriye.studentmsca231.Validation.StudentEmail;
import shuuriye.studentmsca231.Validation.ValidationGroups;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentRequest {
    @NotNull(message="id is required when updating student" ,
            groups= ValidationGroups.Update.class)
    @Null(groups= ValidationGroups.Create.class , message="id is not required")
    private Long id;

    @NotBlank(message="name is required" ,
            groups={ ValidationGroups.Create.class ,  ValidationGroups.Update.class })
    private String name;

    @NotBlank(message="email is required" ,
            groups={ ValidationGroups.Create.class ,  ValidationGroups.Update.class })
    @Email(message="invalid email format" ,
            groups={ ValidationGroups.Create.class ,  ValidationGroups.Update.class })
    @StudentEmail(groups={ValidationGroups.Create.class ,   ValidationGroups.Update.class} ,
    message="email must be an educational email , ends with(just.edu.so)")
    private String email;


@NotNull(message="address is required" ,
        groups={ ValidationGroups.Create.class ,  ValidationGroups.Update.class })
@Valid
    private AddressRequest address = new AddressRequest();   //nested object

    @NotBlank(message="invalid course name" ,
            groups={ ValidationGroups.Create.class ,  ValidationGroups.Update.class })
    private String courses;

}
