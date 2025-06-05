package com.koreait.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FileVO {
	private String uuid;
	private String saveDir;
	private String fileName;
	private int fileType;
	private long bno;
	private long fileSize;
	private String regDate;
}
