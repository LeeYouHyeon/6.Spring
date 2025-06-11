package com.koreait.www.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
