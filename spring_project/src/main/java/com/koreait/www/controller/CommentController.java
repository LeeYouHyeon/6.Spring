package com.koreait.www.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.www.domain.CommentVO;
import com.koreait.www.domain.PagingVO;
import com.koreait.www.handler.PagingHandler;
import com.koreait.www.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RequestMapping("/comment/*")
@Slf4j
@RestController // REST API를 처리하는 컨트롤러
public class CommentController {

	private final CommentService csv;
	
	// ResponseEntity<T> : T => response 객체의 body에 보낼 값의 타입
	@PostMapping("/post")
	public ResponseEntity<String> post(@RequestBody CommentVO cvo) {
		log.info(">>>> cvo >> {}", cvo);
		
		// cvo를 DB로 전달
		int isOk = csv.post(cvo);
		return new ResponseEntity<String>(String.valueOf(isOk), 
				isOk > 0 ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 경로 없이 parameter만 준 경우 "/{}/{}/{}/...."
	/*
	 * @GetMapping(value = "/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
	 * public ResponseEntity<List<CommentVO>> list(@PathVariable("bno") long bno) {
	 * List<CommentVO> list = csv.getList(bno);
	 * 
	 * return new ResponseEntity<>(list, HttpStatus.OK); }
	 */
	
	@GetMapping(value = "/{bno}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PagingHandler> list(@PathVariable("bno") long bno, @PathVariable("page") int page) {
		int totalCount = csv.getTotalCount(bno);
		
		PagingVO pgvo = new PagingVO(page, 5);
		List<CommentVO> list = csv.getList(bno, pgvo);
		
		PagingHandler ph = new PagingHandler(totalCount, pgvo, list);
		return new ResponseEntity<>(ph, HttpStatus.OK);
	}
	
	@DeleteMapping("/{cno}")
	@ResponseBody // 위의 ResponseEntity를 축약
	public String delete(@PathVariable("cno") long cno) {
		log.info(">>>> delete cno >> " + cno);
		
		return csv.delete(cno) > 0 ? "1" : "0";
	}
	
	@PutMapping("/update")
	@ResponseBody
	public String update(@RequestBody CommentVO cvo) {
		log.info(">>>> cvo >> {}", cvo);
		int isOk = csv.update(cvo);
		return isOk > 0 ? "1" : "0";
	}
}
