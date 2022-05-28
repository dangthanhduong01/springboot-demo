package com.springboot.service;

import com.springboot.model.History;

public interface HistoryService {

	History getHistoryByUser_id(int id);
}
