package com.cyj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyj.dao.ComponentDao;
import com.cyj.entity.Component;
import com.cyj.service.ComponentService;

@Service
public class ComponentServiceImpl implements ComponentService {

	@Autowired
	private ComponentDao cDao;
	
	public Component selComponent() {
		return cDao.selComponent();
	}

	public boolean updCom(Component c) {
		return cDao.updCom(c) > 0 ? true :false;
	}

}
