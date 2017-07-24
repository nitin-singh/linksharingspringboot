package com.ls.service;

import com.ls.dto.ResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {
    ResponseDTO subscribeTopic() {
        return new ResponseDTO();
    }

    ResponseDTO unsubscribeTopic() {
        return new ResponseDTO();
    }

    ResponseDTO listAllSubscribedTopicsOfUser() {
        return new ResponseDTO();
    }
}
