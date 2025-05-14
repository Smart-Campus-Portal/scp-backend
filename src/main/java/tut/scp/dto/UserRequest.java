package tut.scp.dto;

import lombok.Getter;
import lombok.Setter;
import tut.scp.enums.Role;

@Getter
@Setter
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
}
