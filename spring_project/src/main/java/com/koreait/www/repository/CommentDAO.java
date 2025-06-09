package com.koreait.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.koreait.www.domain.CommentVO;
import com.koreait.www.domain.PagingVO;

public interface CommentDAO {

	int post(CommentVO cvo);

	List<CommentVO> getList(@Param("bno") long bno, @Param("pgvo") PagingVO pgvo);

	int getTotalCount(long bno);

	int delete(long cno);

	int update(CommentVO cvo);

	long getBno(long cno);

}
