package com.cyj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyj.dao.DyNamicMessagesDao;
import com.cyj.entity.DyNamicMessages;
import com.cyj.service.DyNamicMessagesService;

@Service
public class DyNamicMessagesServiceImpl implements DyNamicMessagesService {
	
	@Autowired
	private DyNamicMessagesDao ddao;


	
	public List<DyNamicMessages> getDyNamicMessages(DyNamicMessages d) {
		return ddao.getDyNamicMessages(d);
	}

	
	public Integer getDyNamicMessagesCount(DyNamicMessages d) {
		return ddao.getDyNamicMessagesCount(d);
	}

	
	public boolean addDyNamicMessages(DyNamicMessages d) {
		return ddao.addDyNamicMessages(d) > 0 ? true : false;
	}

	
	public boolean updDyNamicMessages(DyNamicMessages d) {
		return ddao.updDyNamicMessages(d) > 0 ? true : false;
	}

	
	public boolean delDyNamicMessages(List<String> ids) {
		return ddao.delDyNamicMessages(ids) > 0 ? true : false;
	}

	
	public List<String> selDyNamicMessages(String ziXunName) {
		return ddao.selDyNamicMessages(ziXunName);
	}
	
}
