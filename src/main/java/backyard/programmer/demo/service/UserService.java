package backyard.programmer.demo.service;

import backyard.programmer.demo.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto user);
    UserDto getUser(String email);

    UserDto getUserById(String id);
}
