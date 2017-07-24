package com.ls.controller;

import com.ls.domain.Topic;
import com.ls.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TopicController {

    @Autowired
    TopicRepository topicRepository;

    @RequestMapping(value = "/topic", method = RequestMethod.POST)
    public String createTopic(Topic topic) {

        return "Success"; //Need to show
    }


}
