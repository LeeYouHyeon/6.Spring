package com.koreait.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koreait.www.domain.PagingVO;
import com.koreait.www.domain.UserVO;
import com.koreait.www.repository.UserDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
	
	private final UserDAO udao;

	@Transactional
	@Override
	public int insert(UserVO uvo) {
		// TODO Auto-generated method stub
		int isOk = udao.insert(uvo);
		if (isOk == 0) return 0;
		return udao.insertAuthInit(uvo.getEmail());
	}

	@Override
	public boolean isRegistered(String email) {
		// TODO Auto-generated method stub
		return udao.getUser(email) != null;
	}

	@Override
	public List<UserVO> getList(PagingVO pgvo) {
		// TODO Auto-generated method stub
		List<UserVO> list = udao.getList(pgvo);
		for (UserVO uvo : list)
			uvo.setAuthList(udao.getAuthList(uvo.getEmail()));
		return list;
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return udao.getTotalCount(pgvo);
	}

	@Override
	public int update(UserVO uvo) {
		// TODO Auto-generated method stub
		return udao.update(uvo);
	}
	
	@Transactional
	@Override
	public int delete(String email) {
		// TODO Auto-generated method stub
		int isOk = udao.removeAuth(email);
		if (isOk == 0) return 0;
		return udao.remove(email);
	}
}
