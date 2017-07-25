package com.ls.service;

import com.ls.domain.Resource;
import com.ls.domain.Subscription;
import com.ls.domain.Topic;
import com.ls.domain.User;
import com.ls.enums.Visibility;
import com.ls.repository.ResourceRepository;
import com.ls.repository.SubscriptionRepository;
import com.ls.repository.TopicRepository;
import com.ls.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.omg.CORBA.INV_FLAG;
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
    private final Logger LOG = LoggerFactory.getLogger(BootstrapService.class);

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
        LOG.info("----------------------LINKSHARING BOOTSTRAP STARTED----------------------");
        createUserData();
        createUserTopics();
        createResourcesForTopics();
        createSubscriptionData();
        subscribeToAllTopics();
        listAllTopicNamesOfCreatedTopics();
        listAllSubscribersOfEachTopic();
        LOG.info("----------------------LINKSHARING BOOTSTRAP COMPLETE---------------------");
    }

    private void createAdminUser() {
    }

    private void createUserData() {
        List<User> persistedUsers = userRepository.findAll();
        if (persistedUsers.isEmpty()) {
            for (int i = 1; i <= 10; i++) {
                userRepository.saveAndFlush(new User("Nitin", "nitin" + i, "Kumar Singh", "nitinkoemail@gmail.com", "dummypassword", true, false));
            }
        }
    }

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
        LOG.info("***********************CREATING RESOURCES FOR TOPICS***********************");
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
        LOG.info("***********************COMPLETED CREATING RESOURCES FOR TOPICS***********************");
    }

    private void createSubscriptionData() {
        LOG.info("*********************CREATING SUBSCRIPTION DATA**************************");
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
        LOG.info("*********************COMPLETED CREATING SUBSCRIPTION DATA**************************");
    }

    private void subscribeToAllTopics() {
        LOG.info("*********************Starting subscription to all topics****************************");
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
        LOG.info("*********************Completing subscription to all topics****************************");
    }

    private void listAllTopicNamesOfCreatedTopics() {
        LOG.info("***************************DISPLAYING ALL TOPICS CREATED BY ALL USERS************************");
        List<User> users = userRepository.findAll();
        for (User user : users) {
            LOG.info("******************************************************************");
            LOG.info("***************************" + user.getUsername() + "*************************");
            LOG.info("******************************************************************");
            for (Topic topic : user.getTopics()) {
                LOG.info("USER: " + user.getUsername() + "-----Topic Name: " + topic.getName());
            }
        }
    }

    private void listAllSubscribersOfEachTopic() {
        LOG.info("***************************DISPLAYING ALL TOPICS CREATED BY ALL USERS************************");
        List<Topic> topics = topicRepository.findAll();
        for (Topic topic : topics) {
            LOG.info("******************************************************************");
            LOG.info("***************************" + topic.getName() + "*************************");
            LOG.info("******************************************************************");
            for (Subscription subscription : topic.getSubscriptions()) {
                LOG.info("TOPIC: " + topic.getName() + "----Subscriber Name: " + subscription.getUser().getUsername());
            }
        }
    }
}
