package com.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.model.History;
import com.springboot.repository.HistoryRepository;

@Service
public class HistoryServiceImpl implements HistoryService{

	@Autowired
	HistoryRepository historyRepository;
	
	@Override
	public History getHistoryByUser_id(int id) {
		// TODO Auto-generated method stub
		return historyRepository.findByUser_id(id);
	}

}
