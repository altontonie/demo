package backyard.programmer.demo.model.request;

import lombok.Data;

@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
