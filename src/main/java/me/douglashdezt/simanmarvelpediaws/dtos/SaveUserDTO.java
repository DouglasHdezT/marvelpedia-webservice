package me.douglashdezt.simanmarvelpediaws.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaveUserDTO {
    @Email
    private String email;
    @NotBlank
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[#%$^&!])[A-Za-z\\d#%$^&!]{8,32}$",
            message = "The password must be at least one Capital Letter, one normal letter, a number and a symbol of !#$%^&"
    )
    private String password;
}
