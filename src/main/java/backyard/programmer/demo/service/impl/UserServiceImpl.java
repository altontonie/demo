package backyard.programmer.demo.service.impl;

import backyard.programmer.demo.dto.UserDto;
import backyard.programmer.demo.entity.UserEntity;
import backyard.programmer.demo.repository.UserRepository;
import backyard.programmer.demo.service.UserService;
import backyard.programmer.demo.util.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto user) {

        if(userRepository.findByEmail(user.getEmail()) != null){
            throw new RuntimeException("Email already exist");
        }

        String publicUserId = utils.GenerateUserId(30);

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setUserId(publicUserId);

        UserEntity storedUserDetails = userRepository.save(userEntity);
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails,returnValue);

        return returnValue;
    }

    @Override
    public UserDto getUser(String email) {
        UserEntity user = userRepository.findByEmail(email);
        if(user == null) throw new UsernameNotFoundException(email);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(user,returnValue);

        return returnValue;
    }

    @Override
    public UserDto getUserById(String id) {
        UserDto returnValue = new UserDto();
        UserEntity user = userRepository.findByUserId(id);
        if(user==null) throw new UsernameNotFoundException(id);

        BeanUtils.copyProperties(user,returnValue);
        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email);

        if(user == null) throw new UsernameNotFoundException(email);

        return new User(user.getEmail(),user.getEncryptedPassword(),new ArrayList<>());
    }
}