package com.ein.board.form;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BoardCommentForm extends CommonForm {
	
	List<MultipartFile> files;
	List<BoardCommentComForm> commentZip;
	List<String> filesNo;
	List<String> filesNoAll;
	int bb_Comment_Seq;
	String bb_Comment_Writer; 
	String bb_Comment_Subject; 
	String bb_Comment_Content; 
	int bb_Comment_Hits; 
	Date bb_Comment_Ins_Date;
	int fileCnt;
	
	String search_type;
	String board_file;
	
	List<String> comNoZip;
	List<String> comNoAllZip;
	
	public List<String> getComNoZip() {
		return comNoZip;
	}
	public void setComNoZip(List<String> comNoZip) {
		this.comNoZip = comNoZip;
	}
	public List<String> getComNoAllZip() {
		return comNoAllZip;
	}
	public void setComNoAllZip(List<String> comNoAllZip) {
		this.comNoAllZip = comNoAllZip;
	}
	public List<BoardCommentComForm> getCommentZip() {
		return commentZip;
	}

	public void setCommentZip(List<BoardCommentComForm> commentZip) {
		this.commentZip = commentZip;
	}

	public List<String> getFilesNoAll() {
		return filesNoAll;
	}

	public void setFilesNoAll(List<String> filesNoAll) {
		this.filesNoAll = filesNoAll;
	}

	public List<MultipartFile> getFiles() {
        return files;
    }
 
    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
	
	
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

	public Date getBb_Comment_Ins_Date() {
		return bb_Comment_Ins_Date;
	}

	public void setBb_Comment_Ins_Date(Date bb_Comment_Ins_Date) {
		this.bb_Comment_Ins_Date = bb_Comment_Ins_Date;
	}

	public String getSearch_type() {
		return search_type;
	}

	public void setSearch_type(String search_type) {
		this.search_type = search_type;
	}
	
	public String getBoard_file() {
        return board_file;
    }
 
    public void setBoard_file(String board_file) {
        this.board_file = board_file;
    }

	public int getFileCnt() {
		return fileCnt;
	}

	public void setFileCnt(int fileCnt) {
		this.fileCnt = fileCnt;
	}

	public List<String> getFilesNo() {
		return filesNo;
	}

	public void setFilesNo(List<String> filesNo) {
		this.filesNo = filesNo;
	}
	
	
    
}
