package com.ls.service;

import com.ls.domain.User;
import com.ls.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BootstrapService implements InitializingBean {
    private final Logger LOG = LoggerFactory.getLogger(BootstrapService.class);

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional()
    public void afterPropertiesSet() throws Exception {
        LOG.info("----------------------LINKSHARING BOOTSTRAP STARTED----------------------");
//        createSystemUser();
        LOG.info("----------------------LINKSHARING BOOTSTRAP COMPLETE---------------------");
    }

    private void createSystemUser() {
        User user = new User();
        user.setFirstName("NITIN");
        user.setLastName("KUMAR SINGH");
        user.setPassword("NOTSOSAFEPASSWORD");
        user.setAdmin(true);
        user.setUsername("username@dummyuser.com");
//        user.setProfilePhoto("CLOUDINARY SAMPLE URL");
        userRepository.save(user);
    }

}
