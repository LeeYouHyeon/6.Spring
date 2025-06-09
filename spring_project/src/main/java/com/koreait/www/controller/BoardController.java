package com.koreait.www.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.www.domain.BoardDTO;
import com.koreait.www.domain.BoardVO;
import com.koreait.www.domain.FileVO;
import com.koreait.www.domain.PagingVO;
import com.koreait.www.handler.FileHandler;
import com.koreait.www.handler.FileRemoveHandler;
import com.koreait.www.handler.PagingHandler;
import com.koreait.www.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/board/*")
@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {
	// 생성자 주입 시 @RequiredArgsConstructor 사용, 멤버 변수는 반드시 final
	private final BoardService bsv;
	private final FileHandler fh;

	// servlet path case를 method 형식으로 사용
	// return값이 가야 하는 주소

	// /board/register => jsp에서 controller로 오는 경로
	// return 주소(jsp 파일)와 request의 주소가 같을 경우 생략 가능
	@GetMapping("/register")
	public void register() {
	}
//	@GetMapping("/register")
//	public String register() {
//		return "/board/register";
//	}

	private void printLog(String logTitle, int isOk) {
		log.info(">>>> {} {}", logTitle, isOk > 0 ? "성공" : "실패");
	}

	// 첨부파일 추가 => multipart/form-data => multiple => Multipart[]
	@PostMapping("/insert")
	public String insert(BoardVO bvo, MultipartFile[] files) {
		log.info(">>>> bvo >> {}", bvo);

		List<FileVO> flist = null;
		if (files[0].getSize() > 0) { // 파일이 있다면
			// MultipartFile[] => DB에 저장할 값으로 생성 => List<FileVO> 형태로 생성
			// 실제 파일을 저장 => FileHandler
			flist = fh.uploadFile(files);
			bvo.setFileQty(flist.size());
			log.info(">>>> flist >> {}", flist);
		}

		BoardDTO bdto = new BoardDTO(bvo, flist);

		printLog("insert", bsv.insert(bdto));
		return "redirect:/board/list";
	}

	@GetMapping("/list")
	public void list(Model m, PagingVO pgvo) {
		// PagingVO pgvo = new PagingVO(); // 1, 10 => 0, 10
		List<BoardVO> list = bsv.getList(pgvo);

		// totalCount 구해오기
		int totalCount = bsv.getTotalCount(pgvo);
		PagingHandler ph = new PagingHandler(totalCount, pgvo);
		log.info(">>>> ph >> {}", ph);

		m.addAttribute("ph", ph);
		m.addAttribute("list", list);
	}

	// isUpdate를 wrapper class로 받아 null을 받을 수 있게 함
	@GetMapping({ "/detail", "/modify" })
	public void detail(@RequestParam("bno") long bno, Boolean isUpdate, Model m, HttpServletRequest request) {
		boolean isIncrease = isUpdate == null && request.getRequestURI().equals("/board/detail");

		BoardDTO bdto = bsv.getDetail(bno, isIncrease);

		m.addAttribute("bdto", bdto);
	}

	// RedirectAttributes
	@PostMapping("/update")
	public String update(BoardVO bvo, RedirectAttributes ra,
			@RequestParam(value = "files", required = false) MultipartFile[] files) {
		List<FileVO> flist = null;
		if (files[0].getSize() > 0) {
			flist = fh.uploadFile(files);
			bvo.setFileQty(flist.size());
		}
		
		log.info(">>>> update bvo >> {}", bvo);
		log.info(">>>> update {}", bsv.update(new BoardDTO(bvo, flist)) > 0 ? "성공" : "실패");

		// 파라미터를 붙여서 리턴하는 방법 2가지
		// 1. 파라미터를 직접 다는 방법
		// return "redirect:/board/detail?bno=" + bvo.getBno();
		// 2. 객체에 저장하는 방법 : RedirectAttribute
		ra.addAttribute("bno", bvo.getBno());
		ra.addAttribute("isUpdate", true); // isUpdate에 null이 아닌 무언가를 담기 위한 작업. 값 자체엔 의미가 없다.
		return "redirect:/board/detail";
	}

	@GetMapping("/delete")
	public String delete(long bno) {
		log.info(">>>> delete bno >> " + bno);
		printLog("delete", bsv.delete(bno));
		return "redirect:/board/list";
	}
	
	@DeleteMapping("/deleteFile/{uuid}")
	@ResponseBody
	public String deleteFile(@PathVariable("uuid") String uuid) {
		log.info(">>>> delete file uuid >> {}", uuid);
//		FileVO fvo = bsv.getOne(uuid);
//		if (fvo == null) return "0";
//		
//		if (bsv.deleteFile(uuid) == 0) return "0";
//		
//		return new FileRemoveHandler().deleteRealFile(fvo);
		return String.valueOf(bsv.deleteFile(uuid));
	}
}
