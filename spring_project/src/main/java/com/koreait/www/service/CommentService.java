package com.koreait.www.service;

import java.util.List;

import com.koreait.www.domain.CommentVO;
import com.koreait.www.domain.PagingVO;

public interface CommentService {

	int post(CommentVO cvo);

	List<CommentVO> getList(long bno, PagingVO pgvo);

	int getTotalCount(long bno);

	int delete(long cno);

	int update(CommentVO cvo);

}
