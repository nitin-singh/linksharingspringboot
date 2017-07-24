package com.ls.service;

import com.ls.domain.User;
import com.ls.dto.ResponseDTO;
import com.ls.dto.user.UserDTO;
import com.ls.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final Logger LOG = LoggerFactory.getLogger(UserService.class);

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

    public ResponseDTO<User> updateUser(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.username);
        ResponseDTO<User> responseDTO = null;
        if (userDTO != null && user != null) {
            user.setFirstName(userDTO.firstName);
            user.setLastName(userDTO.lastName);
            user.setImage(userDTO.profilePhoto);
            user = userRepository.saveAndFlush(user);
            if (user != null) {
                responseDTO = new ResponseDTO<User>(true, "User profile Updated Successfully", user);
            }
        } else {
            responseDTO = new ResponseDTO<User>(true, "Unable to edit profile. No Data found", null);
        }
        return responseDTO;
    }

    public ResponseDTO<Long> deleteUser(Long id) {
        ResponseDTO<Long> responseDTO = null;
        User user = userRepository.findById(id);
        if (user != null) {
            if (user.getAdmin()) {
                responseDTO = new ResponseDTO<Long>(false, "Operation Aborted! Can not delete Admin User.", id);
            } else {
                userRepository.delete(id);
                responseDTO = new ResponseDTO<Long>(true, "User Deleted Successfully.", id);
            }
        } else {
            responseDTO = new ResponseDTO<Long>(false, "No user data found.", id);
        }
        return responseDTO;
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
