package com.koreait.www.repos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.koreait.www.domain.BoardVO;
import com.koreait.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 객체 생성
@RunWith(SpringJUnit4ClassRunner.class) // 실제 컨트롤러 대신 JUnit이 실행 
@ContextConfiguration(classes = {com.koreait.www.config.RootConfig.class}) // DB 연결
public class BoardTest {
	
	// JUnit : pom.xml에 기본 등록된 라이브러리
	// 프로젝트의 Properties > Java Build Path > Libraries > Classpath > Add Libraries로 추가
	@Autowired // import만으론 안 되고 직접 injection을 받아와야 함
	private BoardDAO bdao;
	
	@Test // 테스트 케이스임을 알리는 annotation
	public void insertBoardDummies() {
		for (int i = 0; i < 3000; i++) {
			BoardVO bvo = new BoardVO();
			bvo.setTitle("Test Title " + (int)(Math.random()*300));
			bvo.setWriter("Tester " + (int)(Math.random()*300));
			bvo.setContent("Test Content" + i);
			bdao.insert(bvo);
		}
	}
	
}
