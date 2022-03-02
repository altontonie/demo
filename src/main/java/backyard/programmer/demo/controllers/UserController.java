package backyard.programmer.demo.controllers;


import backyard.programmer.demo.dto.UserDto;
import backyard.programmer.demo.model.request.UserRequest;
import backyard.programmer.demo.model.response.UserResponse;
import backyard.programmer.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")  // http://localhost:8080/users
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/{id}",
    produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserResponse getUser(@PathVariable String id){
        UserResponse userResponse = new UserResponse();
        UserDto userDto = userService.getUserById(id);
        BeanUtils.copyProperties(userDto,userResponse);

        return userResponse;
    }

    @PostMapping(
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public UserResponse createUser(@RequestBody UserRequest userDetails){
        UserResponse userResponse = new UserResponse();
        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userDetails,userDto);
        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser,userResponse);

        return userResponse;
    }

    @PutMapping
    public String updateUser(){
        return "update user called";
    }

    @DeleteMapping
    public String deleteUser(){
        return "delete user called";
    }
}
