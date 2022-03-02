package backyard.programmer.demo.model.request;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String email;
    private String password;
}
