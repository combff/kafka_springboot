package com.encore.kafka.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.encore.kafka.vo.PubSubVo;

@Mapper
public interface PubSubDao {
	
	List<PubSubVo> selectAll_pub();
	void insert_Sub(List<PubSubVo> ping);
	
}
