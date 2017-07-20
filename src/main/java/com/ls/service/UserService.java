package com.ls.service;

import com.ls.domain.User;
import com.ls.dto.user.UserDTO;
import com.ls.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser(UserDTO userDTO) {
        //TODO: Implement bind data here
        User user = bindUserFieldFromDTO(userDTO);
        user = userRepository.saveAndFlush(user);
        if (user == null) {
            System.out.print("user not created");
        } else {
            System.out.print("UserCreated successfully");
        }
        return user;
    }

    User bindUserFieldFromDTO(UserDTO userDTO) {
        User user;
        if (userDTO != null) {
            user = new User();
            user.setFirstName(userDTO.firstName);
            user.setLastName(userDTO.lastName);
            user.setUsername(userDTO.username);
            user.setEmail(userDTO.email);
            user.setPassword(userDTO.password);
            user.setAdmin(false);
            user.setActive(true);
            user.setImage(null);
        } else {
            user = new User();
        }
        return user;
    }
}
