package com.ein.board.form;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BoardThreeFilesForm extends CommonForm {
	
	List<MultipartFile> files;
	List<String> filesNo;
	List<String> filesNoAll;
	int bb_File_Seq;
	String bb_File_Writer; 
	String bb_File_Subject; 
	String bb_File_Content; 
	int bb_File_Hits; 
	Date bb_File_Ins_Date;
	int fileCnt;
	
	String search_type;
	String board_file;
	
	
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

	public Date getBb_File_Ins_Date() {
		return bb_File_Ins_Date;
	}

	public void setBb_File_Ins_Date(Date bb_File_Ins_Date) {
		this.bb_File_Ins_Date = bb_File_Ins_Date;
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
