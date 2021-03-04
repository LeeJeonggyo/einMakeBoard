package com.ein.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import com.ein.board.common.ResultUtil;
import com.ein.board.dto.BoardAFileDto;
import com.ein.board.dto.BoardCommentComDto;
import com.ein.board.dto.BoardCommentDto;
import com.ein.board.dto.BoardDto;
import com.ein.board.dto.BoardThreeFilesDto;
import com.ein.board.form.BoardAFileForm;
import com.ein.board.form.BoardCommentComForm;
import com.ein.board.form.BoardCommentFileForm;
import com.ein.board.form.BoardCommentForm;
import com.ein.board.form.BoardFileForm;
import com.ein.board.form.BoardFilesForm;
import com.ein.board.form.BoardForm;
import com.ein.board.form.BoardThreeFilesForm;
import com.ein.board.service.BoardService;



@Controller
@RequestMapping(value = "/board")
public class BoardController {
	
    @Autowired
    private BoardService boardService;
    
 
    /** 占쌉쏙옙占쏙옙 - 占쏙옙占� 占쏙옙占쏙옙占쏙옙 占싱듸옙 */
    @RequestMapping( value = "/boardList")
    public String boardList(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
        return "board/boardList";
    }
 
    /** 占쌉쏙옙占쏙옙 - 占쏙옙占� 占쏙옙회 */
    @RequestMapping(value = "/getBoardList")
    @ResponseBody
    public ResultUtil getBoardList(HttpServletRequest request, HttpServletResponse response, BoardForm boardForm) throws Exception {

		ResultUtil resultUtils = boardService.getBoardList(boardForm);

		return resultUtils;
	}
    
    /** 占쌉쏙옙占쏙옙 - 占쏙옙 占쏙옙占쏙옙占쏙옙 占싱듸옙 */
	@RequestMapping(value = "/boardDetail")
	public String boardDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return "board/boardDetail";
	}
	
	/** 占쌉쏙옙占쏙옙 - 占쏙옙 占쏙옙회 */
	@RequestMapping(value = "/getBoardDetail")
	@ResponseBody
	public BoardDto getBoardDetail(HttpServletRequest request, HttpServletResponse response, BoardForm boardForm) throws Exception {

		BoardDto boardDto = boardService.getBoardDetail(boardForm);

		return boardDto;
	}
	
	/** 占쌉쏙옙占쏙옙 - 占쌜쇽옙 占쏙옙占쏙옙占쏙옙 占싱듸옙 & 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占싱듸옙 */
	@RequestMapping(value = "/boardWrite")
	public String boardWrite(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "board/boardWrite";
	}
	
	/** 문자에서 HTML 부분 변경 */
    public BoardForm ChangeToTextB(BoardForm boardForm) {
    	
    	String bbSubjectH = boardForm.getBb_Subject();
    	String bbWriterH = boardForm.getBb_Writer();
    	String bbContentH = boardForm.getBb_Content();
    	
    	if(bbSubjectH != null) {
    		bbSubjectH = bbSubjectH.replace("&", "&amp;");
    		bbSubjectH = bbSubjectH.replace("<", "&lt;");
    		bbSubjectH = bbSubjectH.replace(">", "&gt;");
			bbSubjectH = bbSubjectH.replace("\"", "&quot;");
			boardForm.setBb_Subject(bbSubjectH);
    	}
    	
    	if(bbWriterH != null) {
    		bbWriterH = bbWriterH.replace("&", "&amp;");
    		bbWriterH = bbWriterH.replace("<", "&lt;");
    		bbWriterH = bbWriterH.replace(">", "&gt;");
			bbWriterH = bbWriterH.replace("\"", "&quot;");
			boardForm.setBb_Writer(bbWriterH);
    	}
    	
    	if(bbContentH != null) {
    		bbContentH = bbContentH.replace("&", "&amp;");
    		bbContentH = bbContentH.replace("<", "&lt;");
    		bbContentH = bbContentH.replace(">", "&gt;");
			bbContentH = bbContentH.replace("\"", "&quot;");
			boardForm.setBb_Content(bbContentH); 
    	}
    	
    	
    	return boardForm;
    	
    }
	
	/** 占쌉쏙옙占쏙옙 - 占쏙옙占� */
	@RequestMapping(value = "/insertBoard")
	@ResponseBody
	public BoardDto insertBoard(HttpServletRequest request, HttpServletResponse response, BoardForm boardForm) throws Exception {

		ChangeToTextB(boardForm);
		BoardDto boardDto = boardService.insertBoard(boardForm);

		return boardDto;
	}
	
	/** 占쌉쏙옙占쏙옙 - 占쏙옙占쏙옙 */
	@RequestMapping(value = "/deleteBoard")
	@ResponseBody
	public BoardDto deleteBoard(HttpServletRequest request, HttpServletResponse response, BoardForm boardForm) throws Exception {

		BoardDto boardDto = boardService.deleteBoard(boardForm);

		return boardDto;
	}
	
	/** 占쌉쏙옙占쏙옙 - 占쌜쇽옙 占쏙옙占쏙옙占쏙옙 占싱듸옙 & 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占싱듸옙 */
	@RequestMapping(value = "/boardUpdate")
	public String boardUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "board/boardUpdate";
	}

	/** 占쌉쏙옙占쏙옙 - 占쏙옙占쏙옙 */
	@RequestMapping( value = "/updateBoard")
    @ResponseBody
    public BoardDto updateBoard(HttpServletRequest request, HttpServletResponse response, BoardForm boardForm) throws Exception{
        
		ChangeToTextB(boardForm);
        BoardDto boardDto = boardService.updateBoard(boardForm);
        
        return boardDto;
    }
	
	/* ========================================================================================================
	 * 泥⑤��뙆�씪 愿��젴
	 * ========================================================================================================*/
	/** 게시판 - 목록 페이지 이동 */
    @RequestMapping( value = "/boardListAFile")
    public String boardListAFile(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
        return "board/boardListAFile";
    }
 
    /** 게시판 - 목록 조회 */
    @RequestMapping(value = "/getBoardListAFile")
    @ResponseBody
    public ResultUtil getBoardListAFile(HttpServletRequest request, HttpServletResponse response, BoardAFileForm boardAFileForm) throws Exception {
		ResultUtil resultUtils = boardService.getBoardListAFile(boardAFileForm);

		return resultUtils;
	}
    
    /** 게시판 - 상세 페이지 이동 */
	@RequestMapping(value = "/boardDetailAFile")
	public String boardDetailAFile(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return "board/boardDetailAFile";
	}
	
	/** 게시판 - 상세 조회 */
	@RequestMapping(value = "/getBoardDetailAFile")
	@ResponseBody
	public BoardAFileDto getBoardDetailAFile(HttpServletRequest request, HttpServletResponse response, BoardAFileForm boardAFileForm) throws Exception {

		BoardAFileDto boardAFileDto = boardService.getBoardDetailAFile(boardAFileForm);

		return boardAFileDto;
	}
	
	/** 게시판 - 작성 페이지 이동 */
	@RequestMapping(value = "/boardWriteAFile")
	public String boardWriteAFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "board/boardWriteAFile";
	}
	
	/** 문자에서 HTML 부분 변경 */
    public BoardAFileForm ChangeToTextC(BoardAFileForm boardAFileForm){
    	
    	String bbSubjectH = boardAFileForm.getBb_A_Subject();
    	String bbWriterH = boardAFileForm.getBb_A_Writer();
    	String bbContentH = boardAFileForm.getBb_A_Content();
    	
    	if(bbSubjectH != null) {
    		bbSubjectH = bbSubjectH.replace("&", "&amp;");
    		bbSubjectH = bbSubjectH.replace("<", "&lt;");
    		bbSubjectH = bbSubjectH.replace(">", "&gt;");
			bbSubjectH = bbSubjectH.replace("\"", "&quot;");
			boardAFileForm.setBb_A_Subject(bbSubjectH);
    	}
    	
    	if(bbWriterH != null) {
    		bbWriterH = bbWriterH.replace("&", "&amp;");
    		bbWriterH = bbWriterH.replace("<", "&lt;");
    		bbWriterH = bbWriterH.replace(">", "&gt;");
			bbWriterH = bbWriterH.replace("\"", "&quot;");
			boardAFileForm.setBb_A_Writer(bbWriterH);
    	}
    	
    	if(bbContentH != null) {
    		bbContentH = bbContentH.replace("&", "&amp;");
    		bbContentH = bbContentH.replace("<", "&lt;");
    		bbContentH = bbContentH.replace(">", "&gt;");
			bbContentH = bbContentH.replace("\"", "&quot;");
			boardAFileForm.setBb_A_Content(bbContentH); 
    	} 
    	
    	return boardAFileForm;
    	
    }
	
	/** 게시판 - 등록 */
	@RequestMapping(value = "/insertBoardAFile")
	@ResponseBody
	public BoardAFileDto insertBoardAFile(HttpServletRequest request, HttpServletResponse response, BoardAFileForm boardAFileForm) throws Exception {
		
		ChangeToTextC(boardAFileForm);
		BoardAFileDto boardAFileDto = boardService.insertBoardAFile(boardAFileForm);

		return boardAFileDto;
	}
	
	/** 게시판 - 삭제 */
	@RequestMapping(value = "/deleteBoardAFile")
	@ResponseBody
	public BoardAFileDto deleteBoardAFile(HttpServletRequest request, HttpServletResponse response, BoardAFileForm boardAFileForm) throws Exception {

		BoardAFileDto boardAFileDto = boardService.deleteBoardAFile(boardAFileForm);

		return boardAFileDto;
	}
	
	/** 게시판 - 수정 페이지 이동 */
	@RequestMapping(value = "/boardUpdateAFile")
	public String boardUpdateAFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "board/boardUpdateAFile";
	}

	/** 게시판 - 수정 */
	@RequestMapping( value = "/updateBoardAFile")
    @ResponseBody
    public BoardAFileDto updateBoardAFile(HttpServletRequest request, HttpServletResponse response, BoardAFileForm boardAFileForm) throws Exception{
        
		ChangeToTextC(boardAFileForm);
		BoardAFileDto boardAFileDto = boardService.updateBoardAFile(boardAFileForm);
        
        return boardAFileDto;
    }
	
	/** 게시판 - 첨부파일 삭제 */
    @RequestMapping( value = "/deleteBoardFile")
    @ResponseBody
    public BoardAFileDto deleteBoardFile(HttpServletRequest request, HttpServletResponse response, BoardFileForm boardFileForm) throws Exception{
        
    	BoardAFileDto boardAFileDto = boardService.deleteBoardFile(boardFileForm);
        
        return boardAFileDto;
    }
	
	/** 게시판 - 첨부파일 다운로드 */
    @RequestMapping("/fileDownload")                      
    public ModelAndView fileDownload(@RequestParam("fileNameKey") String fileNameKey
                                    ,@RequestParam("fileName") String fileName
                                    ,@RequestParam("filePath") String filePath) throws Exception {
          
        /** 첨부파일 정보 조회 */
        Map<String, Object> fileInfo = new HashMap<String, Object>();
        fileInfo.put("fileNameKey", fileNameKey);
        fileInfo.put("fileName", fileName);
        fileInfo.put("filePath", filePath);
     
        return new ModelAndView("fileDownloadUtil", "fileInfo", fileInfo);
    }
    
    /* ========================================================================================================
	 * 다중 첨부파일 관련
	 * ========================================================================================================*/
	/** 게시판 - 목록 페이지 이동 */
    @RequestMapping( value = "/boardListThreeFiles")
    public String boardListThreeFiles(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
        return "board/boardListThreeFiles";
    }
 
    /** 게시판 - 목록 조회 */
    @RequestMapping(value = "/getBoardListThreeFiles")
    @ResponseBody
    public ResultUtil getBoardListThreeFiles(HttpServletRequest request, HttpServletResponse response, BoardThreeFilesForm boardThreeFilesForm) throws Exception {
		ResultUtil resultUtils = boardService.getBoardListThreeFiles(boardThreeFilesForm);

		return resultUtils;
	}
    
    /** 게시판 - 상세 페이지 이동 */
	@RequestMapping(value = "/boardDetailThreeFiles")
	public String boardDetailThreeFiles(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return "board/boardDetailThreeFiles";
	}
	
	/** 게시판 - 상세 조회 */
	@RequestMapping(value = "/getBoardDetailThreeFiles")
	@ResponseBody
	public BoardThreeFilesDto getBoardDetailThreeFiles(HttpServletRequest request, HttpServletResponse response, BoardThreeFilesForm boardThreeFilesForm) throws Exception {

		BoardThreeFilesDto boardThreeFilesDto = boardService.getBoardDetailThreeFiles(boardThreeFilesForm);

		return boardThreeFilesDto;
	}
	
	/** 게시판 - 작성 페이지 이동 */
	@RequestMapping(value = "/boardWriteThreeFiles")
	public String boardWriteThreeFiles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "board/boardWriteThreeFiles";
	}
	
	/** 문자에서 HTML 부분 변경 */
    public BoardThreeFilesForm ChangeToTextD(BoardThreeFilesForm boardThreeFilesForm){
    	
    	String bbSubjectH = boardThreeFilesForm.getBb_File_Subject();
    	String bbWriterH = boardThreeFilesForm.getBb_File_Writer();
    	String bbContentH = boardThreeFilesForm.getBb_File_Content();
    	
    	if(bbSubjectH != null) {
    		bbSubjectH = bbSubjectH.replace("&", "&amp;");
    		bbSubjectH = bbSubjectH.replace("<", "&lt;");
    		bbSubjectH = bbSubjectH.replace(">", "&gt;");
			bbSubjectH = bbSubjectH.replace("\"", "&quot;");
			boardThreeFilesForm.setBb_File_Subject(bbSubjectH);
    	}
    	
    	if(bbWriterH != null) {
    		bbWriterH = bbWriterH.replace("&", "&amp;");
    		bbWriterH = bbWriterH.replace("<", "&lt;");
    		bbWriterH = bbWriterH.replace(">", "&gt;");
			bbWriterH = bbWriterH.replace("\"", "&quot;");
			boardThreeFilesForm.setBb_File_Writer(bbWriterH);
    	}
    	
    	if(bbContentH != null) {
    		bbContentH = bbContentH.replace("&", "&amp;");
    		bbContentH = bbContentH.replace("<", "&lt;");
    		bbContentH = bbContentH.replace(">", "&gt;");
			bbContentH = bbContentH.replace("\"", "&quot;");
			boardThreeFilesForm.setBb_File_Content(bbContentH);
    	} 
    	
    	return boardThreeFilesForm;
    	
    }
	
	/** 게시판 - 등록 */
	@RequestMapping(value = "/insertBoardThreeFiles")
	@ResponseBody
	public BoardThreeFilesDto insertBoardThreeFiles(HttpServletRequest request, HttpServletResponse response, BoardThreeFilesForm boardThreeFilesForm) throws Exception {

		ChangeToTextD(boardThreeFilesForm);
		BoardThreeFilesDto boardThreeFilesDto = boardService.insertBoardThreeFiles(boardThreeFilesForm);

		return boardThreeFilesDto;
	}
	
	/** 게시판 - 삭제 */
	@RequestMapping(value = "/deleteBoardThreeFiles")
	@ResponseBody
	public BoardThreeFilesDto deleteBoardThreeFiles(HttpServletRequest request, HttpServletResponse response, BoardThreeFilesForm boardThreeFilesForm) throws Exception {

		BoardThreeFilesDto boardThreeFilesDto = boardService.deleteBoardThreeFiles(boardThreeFilesForm);

		return boardThreeFilesDto;
	}
	
	/** 게시판 - 수정 페이지 이동 */
	@RequestMapping(value = "/boardUpdateThreeFiles")
	public String boardUpdateThreeFiles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "board/boardUpdateThreeFiles";
	}

	/** 게시판 - 수정 */
	@RequestMapping( value = "/updateBoardThreeFiles")
    @ResponseBody
    public BoardThreeFilesDto updateBoardThreeFiles(HttpServletRequest request, HttpServletResponse response, BoardThreeFilesForm boardThreeFilesForm) throws Exception{
        
		ChangeToTextD(boardThreeFilesForm);
		BoardThreeFilesDto boardThreeFilesDto = boardService.updateBoardThreeFiles(boardThreeFilesForm);
        
        return boardThreeFilesDto;
    }
	
	/** 게시판 - 첨부파일 삭제 */
    @RequestMapping( value = "/deleteBoardFiles")
    @ResponseBody
    public BoardThreeFilesDto deleteBoardFiles(HttpServletRequest request, HttpServletResponse response, BoardFilesForm boardFilesForm) throws Exception{
        
    	BoardThreeFilesDto boardThreeFilesDto = boardService.deleteBoardFiles(boardFilesForm);
        
        return boardThreeFilesDto;
    }
    
    
    /* ========================================================================================================
	 * 다중 첨부파일 관련
	 * ========================================================================================================*/
	
	/** 게시판 - 목록 페이지 이동 */

    @RequestMapping( value = "/boardListComment")
    public String boardListComment(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
        return "board/boardListComment";
    }
 
    /** 게시판 - 목록 조회 */
    @RequestMapping(value = "/getBoardListComment")
    @ResponseBody
    public ResultUtil getBoardListComment(HttpServletRequest request, HttpServletResponse response, BoardCommentForm boardCommentForm) throws Exception {
		ResultUtil resultUtils = boardService.getBoardListComment(boardCommentForm);

		return resultUtils;
	}
    
    /** 게시판 - 상세 페이지 이동 */
	@RequestMapping(value = "/boardDetailComment")
	public String boardDetailComment(HttpServletRequest request, HttpServletResponse response) throws Exception {

		return "board/boardDetailComment";
	}
	
	/** 게시판 - 상세 조회 */
	@RequestMapping(value = "/getBoardDetailComment")
	@ResponseBody
	public BoardCommentDto getBoardDetailComment(HttpServletRequest request, HttpServletResponse response, BoardCommentForm boardCommentForm) throws Exception {

		BoardCommentDto boardCommentDto = boardService.getBoardDetailComment(boardCommentForm);

		return boardCommentDto;
	}
	
	/** 게시판 - 작성 페이지 이동 */
	@RequestMapping(value = "/boardWriteComment")
	public String boardWriteComment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "board/boardWriteComment";
	}
	
	/** 문자에서 HTML 부분 변경 */
    public BoardCommentForm ChangeToTextE(BoardCommentForm boardCommentForm){
    	
    	String bbSubjectH = boardCommentForm.getBb_Comment_Subject();
    	String bbWriterH = boardCommentForm.getBb_Comment_Writer();
    	String bbContentH = boardCommentForm.getBb_Comment_Content();
    	
    	if(bbSubjectH != null) {
    		bbSubjectH = bbSubjectH.replace("&", "&amp;");
    		bbSubjectH = bbSubjectH.replace("<", "&lt;");
    		bbSubjectH = bbSubjectH.replace(">", "&gt;");
			bbSubjectH = bbSubjectH.replace("\"", "&quot;");
			boardCommentForm.setBb_Comment_Subject(bbSubjectH);
    	}
    	
    	if(bbWriterH != null) {
    		bbWriterH = bbWriterH.replace("&", "&amp;");
    		bbWriterH = bbWriterH.replace("<", "&lt;");
    		bbWriterH = bbWriterH.replace(">", "&gt;");
			bbWriterH = bbWriterH.replace("\"", "&quot;");
			boardCommentForm.setBb_Comment_Writer(bbWriterH);
    	}
    	
    	if(bbContentH != null) {
    		bbContentH = bbContentH.replace("&", "&amp;");
    		bbContentH = bbContentH.replace("<", "&lt;");
    		bbContentH = bbContentH.replace(">", "&gt;");
			bbContentH = bbContentH.replace("\"", "&quot;");
			boardCommentForm.setBb_Comment_Content(bbContentH);
    	} 
    	
    	return boardCommentForm;
    	
    }
    
    /** 게시판 - 등록 */
	@RequestMapping(value = "/insertBoardComment")
	@ResponseBody
	public BoardCommentDto insertBoardComment(HttpServletRequest request, HttpServletResponse response, BoardCommentForm boardCommentForm) throws Exception {

		ChangeToTextE(boardCommentForm);
		BoardCommentDto boardCommentDto = boardService.insertBoardComment(boardCommentForm);

		return boardCommentDto;
	}
	
	/** 게시판 - 삭제 */
	@RequestMapping(value = "/deleteBoardComment")
	@ResponseBody
	public BoardCommentDto deleteBoardComment(HttpServletRequest request, HttpServletResponse response, BoardCommentForm boardCommentForm) throws Exception {

		BoardCommentDto boardCommentDto = boardService.deleteBoardComment(boardCommentForm);

		return boardCommentDto;
	}
	
	/** 게시판 - 수정 페이지 이동 */
	@RequestMapping(value = "/boardUpdateComment")
	public String boardUpdateComment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "board/boardUpdateComment";
	}

	/** 게시판 - 수정 */
	@RequestMapping( value = "/updateBoardComment")
    @ResponseBody
    public BoardCommentDto updateBoardComment(HttpServletRequest request, HttpServletResponse response, BoardCommentForm boardCommentForm) throws Exception{
        
		ChangeToTextE(boardCommentForm);
		BoardCommentDto boardCommentDto = boardService.updateBoardComment(boardCommentForm);
        
        return boardCommentDto;
    }
	
	/** 게시판 - 첨부파일 삭제 */
    @RequestMapping( value = "/deleteBoardCommentFile")
    @ResponseBody
    public BoardCommentDto deleteBoardCommentFile(HttpServletRequest request, HttpServletResponse response, BoardCommentFileForm boardCommentFileForm) throws Exception{
        
    	BoardCommentDto boardCommentDto = boardService.deleteBoardCommentFile(boardCommentFileForm);
        
        return boardCommentDto;
    }
    
    /** 게시판 - 목록 조회 */
    @RequestMapping(value = "/getBoardListCommentCom")
    @ResponseBody
    public List<BoardCommentComDto> getBoardListCommentCom(HttpServletRequest request, HttpServletResponse response, BoardCommentComForm boardCommentComForm) throws Exception {
    	
    	List<BoardCommentComDto> commentList = boardService.getBoardListCommentCom(boardCommentComForm);

		return commentList;
	}  
    
    /** 문자에서 HTML 부분 변경 */
    public BoardCommentComForm ChangeToTextF(BoardCommentComForm boardCommentComForm){
    	
    	String bbContentH = boardCommentComForm.getCom_Content();
    	
    	bbContentH = bbContentH.replace("&", "&amp;");
    	bbContentH = bbContentH.replace("<", "&lt;");
    	bbContentH = bbContentH.replace(">", "&gt;");
    	bbContentH = bbContentH.replace("\"", "&quot;");
    	boardCommentComForm.setCom_Content(bbContentH); 
    	
    	return boardCommentComForm;
    	
    }
    
    /** 게시판 - 댓글등록 */
	@RequestMapping(value = "/insertBoardCommentCom")
	@ResponseBody
	public BoardCommentComDto insertBoardCommentCom(HttpServletRequest request, HttpServletResponse response, BoardCommentComForm boardCommentComForm) throws Exception {

		ChangeToTextF(boardCommentComForm);
		BoardCommentComDto boardCommentComDto = boardService.insertBoardCommentCom(boardCommentComForm);

		return boardCommentComDto;
	}
    
    /** 게시판 - 댓글삭제 */
	@RequestMapping(value = "/deleteBoardCommentCom")
	@ResponseBody
	public BoardCommentComDto deleteBoardCommentCom(HttpServletRequest request, HttpServletResponse response, BoardCommentForm boardCommentForm) throws Exception {

		BoardCommentComDto boardCommentComDto = boardService.deleteBoardCommentCom(boardCommentForm);

		return boardCommentComDto;
	}
    
}
