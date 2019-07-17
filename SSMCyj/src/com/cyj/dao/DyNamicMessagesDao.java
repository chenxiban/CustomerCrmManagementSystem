package com.cyj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cyj.entity.DyNamicMessages;

public interface DyNamicMessagesDao {
	
	public List<DyNamicMessages> getDyNamicMessages(DyNamicMessages d);// 查询所有签到信息
	
	public Integer getDyNamicMessagesCount(DyNamicMessages d);// 带条件分页查询结果集总条数
	
	public int addDyNamicMessages(DyNamicMessages d);
	
	public int updDyNamicMessages(DyNamicMessages d);
	
	public List<String> selDyNamicMessages(@Param("ziXunName") String ziXunName);
	
	public int delDyNamicMessages(@Param("id") List<String> ids);

}
