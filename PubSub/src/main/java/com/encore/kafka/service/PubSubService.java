package com.encore.kafka.service;

import java.util.List;

import com.encore.kafka.vo.PubSubVo;

public interface PubSubService {

	List<PubSubVo> selectAllMembers();
	void insertRecievedAll(List<PubSubVo> ping);
}
