package com.encore.kafka.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encore.kafka.dao.PubSubDao;
import com.encore.kafka.vo.PubSubVo;

@Service
public class PubSubServiceImpl implements PubSubService {

	@Autowired
	PubSubDao pubsubDao;
	
	@Override
	public List<PubSubVo> selectAllMembers() {
		return pubsubDao.selectAll_pub();
	}
	
	@Override
	public void insertRecievedAll(List<PubSubVo> ping) {
		pubsubDao.insert_Sub(ping);
	}
}
