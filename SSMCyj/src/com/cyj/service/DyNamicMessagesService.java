package com.cyj.service;

import java.util.List;

import com.cyj.entity.DyNamicMessages;

public interface DyNamicMessagesService {
	
	public List<DyNamicMessages> getDyNamicMessages(DyNamicMessages d);// 查询所有签到信息
	
	public Integer getDyNamicMessagesCount(DyNamicMessages d);// 带条件分页查询结果集总条数
	
	public List<String> selDyNamicMessages(String ziXunName);

	public boolean addDyNamicMessages(DyNamicMessages d);
	
	public boolean updDyNamicMessages(DyNamicMessages d);
	
	public boolean delDyNamicMessages(List<String> ids);

}
