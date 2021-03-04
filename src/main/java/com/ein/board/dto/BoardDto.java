package com.ein.board.dto;
 
public class BoardDto extends CommonDto {
	
	int bb_Seq;
	String bb_Writer; 
	String bb_Subject; 
	String bb_Content; 
	int bb_Hits; 
	String bb_Ins_Date;
	
	String result;
 
	
	public int getBb_Seq() {
		return bb_Seq;
	}
	public void setBb_Seq(int bb_Seq) {
		this.bb_Seq = bb_Seq;
	}
	public String getBb_Writer() {
		return bb_Writer;
	}
	public void setBb_Writer(String bb_Writer) {
		
		this.bb_Writer = bb_Writer;
	}
	public String getBb_Subject() {
		return bb_Subject;
	}
	public void setBb_Subject(String bb_Subject) {
		this.bb_Subject = bb_Subject;
	}
	public String getBb_Content() {
		return bb_Content;
	}
	public void setBb_Content(String bb_Content) {
		this.bb_Content = bb_Content;
	}
	public int getBb_Hits() {
		return bb_Hits;
	}
	public void setBb_Hits(int bb_Hits) {
		this.bb_Hits = bb_Hits;
	}
	public String getBb_Ins_Date() {
		return bb_Ins_Date;
	}
	public void setBb_InsDate(String bb_Ins_Date) {
		this.bb_Ins_Date = bb_Ins_Date;
	}
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
}

