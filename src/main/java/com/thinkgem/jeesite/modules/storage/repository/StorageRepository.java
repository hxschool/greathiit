package com.thinkgem.jeesite.modules.storage.repository;

import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Repository;

import com.mongodb.gridfs.GridFSDBFile;

@Repository
public class StorageRepository extends AbstractStorageRepositoryImpl {
	@Resource
	private GridFsOperations gridFsOperations;

	public String save(InputStream inputStream, String contentType, String filename) {
		return save(gridFsOperations, inputStream, contentType, filename);
	}

	public void delete(String filename) {
		delete(gridFsOperations, filename);
	}

	public GridFSDBFile getByFilename(String filename) {
		return getByFilename(gridFsOperations, filename);
	}

	public List<GridFSDBFile> listFiles() {

		return listFiles(gridFsOperations);
	}

	public GridFSDBFile get(String id) {
		return get(gridFsOperations, id);
	}

	public boolean exists(String md5) {
		return exists(gridFsOperations, md5);
	}
}
