package me.douglashdezt.simanmarvelpediaws.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
