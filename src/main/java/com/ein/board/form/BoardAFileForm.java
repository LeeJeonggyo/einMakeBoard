package com.ein.board.form;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BoardAFileForm extends CommonForm {
	
	List<MultipartFile> files;
	int filesNo;
	int filesNoAll;
	int bb_A_Seq;
	String bb_A_Writer; 
	String bb_A_Subject; 
	String bb_A_Content; 
	int bb_A_Hits; 
	Date bb_A_Ins_Date;
	int fileCnt;
	
	String search_type;
	String board_file;
	
	public int getFilesNo() {
		return filesNo;
	}

	public void setFilesNo(int filesNo) {
		this.filesNo = filesNo;
	}

	public int getFilesNoAll() {
		return filesNoAll;
	}

	public void setFilesNoAll(int filesNoAll) {
		this.filesNoAll = filesNoAll;
	}

	public List<MultipartFile> getFiles() {
        return files;
    }
 
    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
	
	
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

	public Date getBb_A_Ins_Date() {
		return bb_A_Ins_Date;
	}

	public void setBb_A_Ins_Date(Date bb_A_Ins_Date) {
		this.bb_A_Ins_Date = bb_A_Ins_Date;
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
    
}
