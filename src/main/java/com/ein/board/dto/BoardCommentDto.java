package com.ein.board.dto;

import java.util.List;

public class BoardCommentDto extends CommonDto {
	
	int bb_Comment_Seq;
	String bb_Comment_Writer; 
	String bb_Comment_Subject; 
	String bb_Comment_Content; 
	int bb_Comment_Hits; 
	String bb_Comment_Ins_Date;
	int fileCnt;
	
	String result;
	
	List<BoardCommentFileDto> files;
	List<BoardCommentComDto> commentZip;
	
	public int getBb_Comment_Seq() {
		return bb_Comment_Seq;
	}

	public void setBb_Comment_Seq(int bb_Comment_Seq) {
		this.bb_Comment_Seq = bb_Comment_Seq;
	}

	public String getBb_Comment_Writer() {
		return bb_Comment_Writer;
	}

	public void setBb_Comment_Writer(String bb_Comment_Writer) {
		this.bb_Comment_Writer = bb_Comment_Writer;
	}

	public String getBb_Comment_Subject() {
		return bb_Comment_Subject;
	}

	public void setBb_Comment_Subject(String bb_Comment_Subject) {
		this.bb_Comment_Subject = bb_Comment_Subject;
	}

	public String getBb_Comment_Content() {
		return bb_Comment_Content;
	}

	public void setBb_Comment_Content(String bb_Comment_Content) {
		this.bb_Comment_Content = bb_Comment_Content;
	}

	public int getBb_Comment_Hits() {
		return bb_Comment_Hits;
	}

	public void setBb_Comment_Hits(int bb_Comment_Hits) {
		this.bb_Comment_Hits = bb_Comment_Hits;
	}

	public String getBb_Comment_Ins_Date() {
		return bb_Comment_Ins_Date;
	}

	public void setBb_Comment_Ins_Date(String bb_Comment_Ins_Date) {
		this.bb_Comment_Ins_Date = bb_Comment_Ins_Date;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<BoardCommentFileDto> getFiles() {
		return files;
	}

	public void setFiles(List<BoardCommentFileDto> files) {
		this.files = files;
	}

	public int getFileCnt() {
		return fileCnt;
	}

	public void setFileCnt(int fileCnt) {
		this.fileCnt = fileCnt;
	}
	
}