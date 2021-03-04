package com.ein.board.form;

import java.util.Date;

public class BoardFilesForm extends CommonForm {
 
    int bb_File_Seq;
    int file_no;
    String file_name_key;
    String file_name;
    String file_path;
    String file_size;
    Date ins_date;
    String file_yn;
 
    public int getBb_File_Seq() {
		return bb_File_Seq;
	}

	public void setBb_File_Seq(int bb_File_Seq) {
		this.bb_File_Seq = bb_File_Seq;
	}

	public int getFile_no() {
        return file_no;
    }
 
    public void setFile_no(int file_no) {
        this.file_no = file_no;
    }
 
    public String getFile_name_key() {
        return file_name_key;
    }
 
    public void setFile_name_key(String file_name_key) {
        this.file_name_key = file_name_key;
    }
 
    public String getFile_name() {
        return file_name;
    }
 
    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
 
    public String getFile_path() {
        return file_path;
    }
 
    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
 
    public String getFile_size() {
        return file_size;
    }
 
    public void setFile_size(String file_size) {
        this.file_size = file_size;
    }
 
    public Date getIns_date() {
        return ins_date;
    }
 
    public void setIns_date(Date ins_date) {
        this.ins_date = ins_date;
    }

	public String getFile_yn() {
		return file_yn;
	}

	public void setFile_yn(String file_yn) {
		this.file_yn = file_yn;
	}
 
}