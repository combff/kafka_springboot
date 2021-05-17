package com.encore.kafka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.encore.kafka.vo.PubSubVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SubService {
	
	@Autowired
	PubSubServiceImpl serviceImpl;
	
    @KafkaListener(topics = "${spring.kafka.template.default-topic}", containerFactory = "pingKafkaListenerContainerFactory")
    public void pingListener(List<PubSubVo> ping, Acknowledgment ack) {
        try {
            System.out.println("Recieved ping message: " + ping);
            
            ObjectMapper mapper = new ObjectMapper();
            List<PubSubVo> data =  mapper.convertValue(ping, new TypeReference<List<PubSubVo>>() {});
            
        	serviceImpl.insertRecievedAll(data);
            ack.acknowledge();
        } catch (Exception e) {
        	String msg = "시스템에 예상치 못한 문제가 발생했습니다";
        	System.out.println("Recieved ping message: " + msg + e);
        }
    }
}
