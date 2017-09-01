package com.ls.service;

import com.ls.enums.Visibility;
import com.ls.model.*;
import com.ls.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BootstrapService implements InitializingBean {
    private final Logger log = LoggerFactory.getLogger(BootstrapService.class);

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    SubscriptionRepository subscriptionRepository;

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

    /*private void createUserData() {
        List<User> persistedUsers = userRepository.findAll();
        if (persistedUsers.isEmpty()) {
            for (int i = 1; i <= 10; i++) {
                userRepository.saveAndFlush(new User("Nitin", "nitin" + i, "Kumar Singh", "nitinkoemail@gmail.com", "dummypassword", true, false));
            }
        }
    }*/

    private List<Topic> createUserTopics() {
        List<User> users = userRepository.findAll();
        List<Topic> savedTopics = new ArrayList<Topic>();
        if (!users.isEmpty()) {
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                Topic topic = new Topic("Topic-> " + (i + 1) + " of User with id-> " + user.getId(), user, Visibility.PUBLIC, new Date(), new Date());
                user.getTopics().add(topic);
                userRepository.save(user);
                savedTopics.add(topic);
            }
        }
        return savedTopics;
    }

    private void createResourcesForTopics() {
        log.info("***********************CREATING RESOURCES FOR TOPICS***********************");
        List<Topic> topics = topicRepository.findAll();
        for (Topic topic : topics) {
            Resource linkResource = new Resource("Link Resource Description for TopicId: " + topic.getId(), "www.resource.com", null, topic.getCreatedBy(), topic, new Date(), new Date());
            Resource documentResource = new Resource("Doc Resource Description for TopicId: " + topic.getId(), null, "dummy file path", topic.getCreatedBy(), topic, new Date(), new Date());
            User user = topic.getCreatedBy();
            resourceRepository.save(linkResource);
            resourceRepository.save(documentResource);
//            user.getResources().add(linkResource);
//            user.getResources().add(documentResource);
//            userRepository.save(user);
        }
        log.info("***********************COMPLETED CREATING RESOURCES FOR TOPICS***********************");
    }

    private void createSubscriptionData() {
        log.info("*********************CREATING SUBSCRIPTION DATA**************************");
        List<Topic> topics = topicRepository.findAll();
        for (Topic topic : topics) {
            Subscription subscription = new Subscription(topic, topic.getCreatedBy(), new Date(), new Date());
            User user = topic.getCreatedBy();
            user.getSubscriptions().add(subscription);
            topic.getSubscriptions().add(subscription);
            subscriptionRepository.save(subscription);
            userRepository.save(user);
            topicRepository.save(topic);

        }
        log.info("*********************COMPLETED CREATING SUBSCRIPTION DATA**************************");
    }

    private void subscribeToAllTopics() {
        log.info("*********************Starting subscription to all topics****************************");
        List<Topic> topics = topicRepository.findAll();
        List<User> users = userRepository.findAll();
        for (Topic topic : topics) {
            for (User user : users) {
                if ((topic.getCreatedBy().getId()) != (user.getId())) {
                    Subscription subscription = new Subscription(topic, user, new Date(), new Date());
                    user.getSubscriptions().add(subscription);
                    topic.getSubscriptions().add(subscription);
                    subscriptionRepository.save(subscription);
                    userRepository.save(user);
                    topicRepository.save(topic);
                }

            }
        }
        log.info("*********************Completing subscription to all topics****************************");
    }

    private void listAllTopicNamesOfCreatedTopics() {
        log.info("***************************DISPLAYING ALL TOPICS CREATED BY ALL USERS************************");
        List<User> users = userRepository.findAll();
        for (User user : users) {
            log.info("******************************************************************");
            log.info("***************************" + user.getEmail()                         + "*************************");
            log.info("******************************************************************");
            for (Topic topic : user.getTopics()) {
                log.info("USER: " + user.getEmail() + "-----Topic Name: " + topic.getName());
            }
        }
    }

    private void listAllSubscribersOfEachTopic() {
        log.info("***************************DISPLAYING ALL TOPICS CREATED BY ALL USERS************************");
        List<Topic> topics = topicRepository.findAll();
        for (Topic topic : topics) {
            log.info("******************************************************************");
            log.info("***************************" + topic.getName() + "*************************");
            log.info("******************************************************************");
            for (Subscription subscription : topic.getSubscriptions()) {
                log.info("TOPIC: " + topic.getName() + "----Subscriber Name: " + subscription.getUser().getEmail());
            }
        }
    }


}
