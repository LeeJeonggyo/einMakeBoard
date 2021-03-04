package com.ein.board.form;

import java.util.Date;

public class BoardCommentComForm {
	
	int com_No;
	int bb_Comment_Seq;
	String com_Content;
	Date ins_Date;
	
	
	public int getCom_No() {
		return com_No;
	}
	public void setCom_No(int com_No) {
		this.com_No = com_No;
	}
	public int getBb_Comment_Seq() {
		return bb_Comment_Seq;
	}
	public void setBb_Comment_Seq(int bb_Comment_Seq) {
		this.bb_Comment_Seq = bb_Comment_Seq;
	}
	public String getCom_Content() {
		return com_Content;
	}
	public void setCom_Content(String com_Content) {
		this.com_Content = com_Content;
	}
	public Date getIns_Date() {
		return ins_Date;
	}
	public void setIns_Date(Date ins_Date) {
		this.ins_Date = ins_Date;
	}
	
}
