package com.ein.board.dto;

import java.util.List;

public class BoardAFileDto extends CommonDto {
	
	int bb_A_Seq;
	String bb_A_Writer; 
	String bb_A_Subject; 
	String bb_A_Content; 
	int bb_A_Hits; 
	String bb_A_Ins_Date;
	int fileCnt;
	
	String result;
	
	List<BoardFileDto> files;
	
	public int getBb_A_Seq() {
		return bb_A_Seq;
	}

	public void setBb_A_Seq(int bb_A_Seq) {
		this.bb_A_Seq = bb_A_Seq;
	}

	public String getBb_A_Writer() {
		return bb_A_Writer;
	}

	public void setBb_A_Writer(String bb_A_Writer) {
		this.bb_A_Writer = bb_A_Writer;
	}

	public String getBb_A_Subject() {
		return bb_A_Subject;
	}

	public void setBb_A_Subject(String bb_A_Subject) {
		this.bb_A_Subject = bb_A_Subject;
	}

	public String getBb_A_Content() {
		return bb_A_Content;
	}

	public void setBb_A_Content(String bb_A_Content) {
		this.bb_A_Content = bb_A_Content;
	}

	public int getBb_A_Hits() {
		return bb_A_Hits;
	}

	public void setBb_A_Hits(int bb_A_Hits) {
		this.bb_A_Hits = bb_A_Hits;
	}

	public String getBb_A_Ins_Date() {
		return bb_A_Ins_Date;
	}

	public void setBb_A_Ins_Date(String bb_A_Ins_Date) {
		this.bb_A_Ins_Date = bb_A_Ins_Date;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<BoardFileDto> getFiles() {
		return files;
	}

	public void setFiles(List<BoardFileDto> files) {
		this.files = files;
	}

	public int getFileCnt() {
		return fileCnt;
	}

	public void setFileCnt(int fileCnt) {
		this.fileCnt = fileCnt;
	}
	
	
	
}
