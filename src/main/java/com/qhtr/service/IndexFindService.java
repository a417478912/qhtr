package com.qhtr.service;

import java.util.List;

import com.qhtr.model.IndexFind;

public interface IndexFindService {

	List<IndexFind> findAll();

	IndexFind getById(int id);

}
