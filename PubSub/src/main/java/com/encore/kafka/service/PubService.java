package com.encore.kafka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import com.encore.kafka.vo.PubSubVo;


@Component
public class PubService {
	@Autowired
    private KafkaTemplate<String, List<PubSubVo>> pingKafkaTemplate;

    @Value(value = "${spring.kafka.template.default-topic}")
    private String pingTopicName;
    
    public List<PubSubVo> pingAndPong(List<PubSubVo> listOracle) throws Exception {
    	ListenableFuture<SendResult<String, List<PubSubVo>>> future = pingKafkaTemplate.send(pingTopicName, listOracle);
        future.addCallback(new ListenableFutureCallback<SendResult<String, List<PubSubVo>>>() {
            @Override
            public void onSuccess(SendResult<String, List<PubSubVo> > result) {
            	List<PubSubVo> g = result.getProducerRecord().value();
                System.out.println("Sent message=" + g.toString() + " with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println( "Unable to send message=[" + listOracle + "] due to : " + ex.getMessage());
            }
        });
        return listOracle;
    }
}
