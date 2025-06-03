package com.koreait.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.koreait.www.domain.BoardVO;
import com.koreait.www.domain.PagingVO;
import com.koreait.www.repository.BoardDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
	
	private final BoardDAO bdao;

	@Override
	public int insert(BoardVO bvo) {
		// TODO Auto-generated method stub
		log.info(">>>> service insert {}", bvo);
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		// TODO Auto-generated method stub
		log.info(">>>> service list");
		return bdao.getList(pgvo);
	}

	@Override
	public BoardVO getDetail(long bno, boolean isIncrease) {
		log.info(">>>> service detail {}, {}" + bno, isIncrease);
		if (isIncrease) bdao.addCount(bno);
		return bdao.getDetail(bno);
	}

	@Override
	public int update(BoardVO bvo) {
		// TODO Auto-generated method stub
		log.info(">>>> service update " + bvo);
		return bdao.update(bvo);
	}

	@Override
	public int delete(long bno) {
		// TODO Auto-generated method stub
		return bdao.delete(bno);
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.getTotalCount(pgvo);
	}
}
