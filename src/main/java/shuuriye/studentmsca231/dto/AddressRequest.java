package shuuriye.studentmsca231.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shuuriye.studentmsca231.Validation.ValidationGroups;

@AllArgsConstructor

@NoArgsConstructor
@Getter
@Setter
public class AddressRequest {
    @NotBlank(message="street is required" ,
    groups= {ValidationGroups.Create.class , ValidationGroups.Update.class}
    )
    private String street;

    @NotBlank(message="city is required" ,
            groups= {ValidationGroups.Create.class , ValidationGroups.Update.class}
    )
    private String city;
}
