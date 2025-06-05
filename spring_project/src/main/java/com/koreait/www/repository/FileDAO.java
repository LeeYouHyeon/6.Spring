package com.koreait.www.repository;

import java.util.List;

import com.koreait.www.domain.FileVO;

public interface FileDAO {

	int insert(FileVO fvo);

	List<FileVO> getList(long bno);

}
