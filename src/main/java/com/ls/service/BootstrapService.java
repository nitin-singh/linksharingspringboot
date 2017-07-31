package com.ls.service;

import com.ls.model.Role;
import com.ls.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BootstrapService implements InitializingBean {
    private final Logger log = LoggerFactory.getLogger(BootstrapService.class);

    @Autowired
    RoleRepository roleRepository;

    @Override
    @Transactional()
    public void afterPropertiesSet() throws Exception {
        log.info("----------------------Bootstrap Started----------------------");
        try {
            createRoles();
        } catch (Exception exception) {
            log.error("Exception Occurred in BOOTSTRAP: " + exception.getMessage());
        }
        log.info("----------------------Bootstrap Complete---------------------");
    }

    void createRoles() {
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()) {
            Role userRole = new Role();
            userRole.setRole("ROLE_USER");
            Role adminRole = new Role();
            adminRole.setRole("ROLE_ADMIN");
            roleRepository.save(userRole);
            roleRepository.save(adminRole);
        } else {
            log.info("Roles already created.... Skipping bootstrapping");
        }
    }


}
