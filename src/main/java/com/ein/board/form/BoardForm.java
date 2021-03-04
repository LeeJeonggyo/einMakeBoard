package com.ein.board.form;
 
import java.util.Date;
 
public class BoardForm extends CommonForm  {
 
	int bb_Seq;
	String bb_Writer; 
	String bb_Subject; 
	String bb_Content; 
	int bb_Hits; 
	Date bb_Ins_Date;
	
	String search_type;
	
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
	
	public Date getBb_Ins_Date() {
		return bb_Ins_Date;
	}
	
	public void setBb_Ins_Date(Date bb_Ins_Date) {
		this.bb_Ins_Date = bb_Ins_Date;
	}
	
	public String getSearch_type() {
        return search_type;
    }
 
    public void setSearch_type(String search_type) {
        this.search_type = search_type;
    }
 
}