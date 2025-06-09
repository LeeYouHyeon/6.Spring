package com.koreait.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koreait.www.domain.BoardDTO;
import com.koreait.www.domain.BoardVO;
import com.koreait.www.domain.FileVO;
import com.koreait.www.domain.PagingVO;
import com.koreait.www.repository.BoardDAO;
import com.koreait.www.repository.FileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
	
	private final BoardDAO bdao;
	private final FileDAO fdao;

	@Transactional
	@Override
	public int insert(BoardDTO bdto) {
		// BoardDTO => BoardVO + flist
		int isOk = bdao.insert(bdto.getBvo());
		if (bdto.getFlist() == null) return isOk;
		if (isOk == 0) return 0;
		
		// fileDAO 생성 => fileMapper를 생성하여 fvo 값을 DB로 등록
		long lastBno = bdao.getBno();
		for (FileVO fvo : bdto.getFlist()) {
			fvo.setBno(lastBno);
			// file에 저장
			isOk *= fdao.insert(fvo);
		}
		return isOk; 
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		return bdao.getList(pgvo);
	}
	
	@Transactional
	@Override
	public BoardDTO getDetail(long bno, boolean isIncrease) {
		if (isIncrease) bdao.addCount(bno);
		BoardVO bvo = bdao.getDetail(bno);
		
		List<FileVO> flist = fdao.getList(bno);
		return new BoardDTO(bvo, flist);
	}

	@Transactional
	@Override
	public int update(BoardDTO bdto) {
		int isOk = bdao.update(bdto.getBvo());
		if (bdto.getFlist() == null) return isOk;
		if (isOk == 0) return 0;
		
		for (FileVO fvo : bdto.getFlist()) {
			fvo.setBno(bdto.getBvo().getBno());
			isOk *= fdao.insert(fvo);
		}
		return isOk;
	}

	@Override
	public int delete(long bno) {
		return bdao.delete(bno);
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		return bdao.getTotalCount(pgvo);
	}
	
	@Override
	public FileVO getOne(String uuid) {
		// TODO Auto-generated method stub
		return fdao.getOne(uuid);
	}

	@Transactional
	@Override
	public int deleteFile(String uuid) {
		// TODO Auto-generated method stub
		long bno = fdao.getBno(uuid);
		if (fdao.delete(uuid) == 0) return 0;
		
		return bdao.decreaseFileQty(bno);
	}

}
