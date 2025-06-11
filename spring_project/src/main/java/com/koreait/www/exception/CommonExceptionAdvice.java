package com.koreait.www.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CommonExceptionAdvice {
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handler404(NoHandlerFoundException ex, Model m) {
		log.info(">>>> exception >> {}", ex.getMessage());
		m.addAttribute("exception", ex.getMessage());
		return "custom404";
	}
	
	// 404 외 다른 에러 통틀어 사용
//	@ExceptionHandler(Exception.class)
//	public String exception(Exception ex, Model m) {
//		return "새로 페이지 만들어 연결";
//	}
}
