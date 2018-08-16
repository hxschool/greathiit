package com.thinkgem.jeesite.modules.storage.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.gridfs.GridFSDBFile;
import com.thinkgem.jeesite.modules.storage.repository.StorageRepository;

@Service
public class StorageService {
	@Autowired
	private StorageRepository storageRepository;

	public String save(InputStream inputStream, String contentType, String filename) {
		return storageRepository.save(inputStream, contentType, filename);
	}

	public void delete(String filename) {
		storageRepository.delete(filename);
	}

	public GridFSDBFile getByFilename(String filename) {
		return storageRepository.getByFilename(filename);
	}

	public List<GridFSDBFile> listFiles() {
		return storageRepository.listFiles();
	}

	public GridFSDBFile get(String id) {
		return storageRepository.get(id);
	}

	public boolean exists(String md5) {
		return storageRepository.exists(md5);
	}
}
