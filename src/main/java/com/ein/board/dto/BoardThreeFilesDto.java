package com.ein.board.dto;

import java.util.List;

public class BoardThreeFilesDto extends CommonDto {
	
	int bb_File_Seq;
	String bb_File_Writer; 
	String bb_File_Subject; 
	String bb_File_Content; 
	int bb_File_Hits; 
	String bb_File_Ins_Date;
	int fileCnt;
	
	String result;
	
	List<BoardFilesDto> files;
	
	public int getBb_File_Seq() {
		return bb_File_Seq;
	}

	public void setBb_File_Seq(int bb_File_Seq) {
		this.bb_File_Seq = bb_File_Seq;
	}

	public String getBb_File_Writer() {
		return bb_File_Writer;
	}

	public void setBb_File_Writer(String bb_File_Writer) {
		this.bb_File_Writer = bb_File_Writer;
	}

	public String getBb_File_Subject() {
		return bb_File_Subject;
	}

	public void setBb_File_Subject(String bb_File_Subject) {
		this.bb_File_Subject = bb_File_Subject;
	}

	public String getBb_File_Content() {
		return bb_File_Content;
	}

	public void setBb_File_Content(String bb_File_Content) {
		this.bb_File_Content = bb_File_Content;
	}

	public int getBb_File_Hits() {
		return bb_File_Hits;
	}

	public void setBb_File_Hits(int bb_File_Hits) {
		this.bb_File_Hits = bb_File_Hits;
	}

	public String getBb_File_Ins_Date() {
		return bb_File_Ins_Date;
	}

	public void setBb_File_Ins_Date(String bb_File_Ins_Date) {
		this.bb_File_Ins_Date = bb_File_Ins_Date;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<BoardFilesDto> getFiles() {
		return files;
	}

	public void setFiles(List<BoardFilesDto> files) {
		this.files = files;
	}

	public int getFileCnt() {
		return fileCnt;
	}

	public void setFileCnt(int fileCnt) {
		this.fileCnt = fileCnt;
	}
	
	
	
}