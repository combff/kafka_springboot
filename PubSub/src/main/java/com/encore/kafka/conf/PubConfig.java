package com.encore.kafka.conf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.scheduling.annotation.Scheduled;

import com.encore.kafka.service.PubService;
import com.encore.kafka.service.PubSubServiceImpl;
import com.encore.kafka.vo.PubSubVo;

@EnableKafka
@Configuration
public class PubConfig {
	
	@Value(value = "${spring.kafka.bootstrap-servers}")
	private String bootstrap;

	@Autowired
	PubSubServiceImpl serviceImpl;
	
    @Autowired
    PubService pingService;
    
    @Bean
	public ProducerFactory<String, List<PubSubVo>> pingProducerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<>(configProps);
	}

	@Bean
	public KafkaTemplate<String, List<PubSubVo>> pingKafkaTemplate() {
		return new KafkaTemplate<>(pingProducerFactory());
	}
	
    @Scheduled(fixedRate = 5000)
    public void sendMessage() {

    	List<PubSubVo> listOracle = serviceImpl.selectAllMembers();  	
    	
    	try {
    		if (listOracle.size() > 0)
    			pingService.pingAndPong(listOracle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
