package com.ein.board.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ein.board.common.PagingUtil;
import com.ein.board.common.ResultUtil;
import com.ein.board.dao.BoardDao;
import com.ein.board.dto.BoardAFileDto;
import com.ein.board.dto.BoardCommentComDto;
import com.ein.board.dto.BoardCommentDto;
import com.ein.board.dto.BoardDto;
import com.ein.board.dto.BoardThreeFilesDto;
import com.ein.board.dto.CommonDto;
import com.ein.board.form.BoardAFileForm;
import com.ein.board.form.BoardCommentComForm;
import com.ein.board.form.BoardCommentFileForm;
import com.ein.board.form.BoardCommentForm;
import com.ein.board.form.BoardFileForm;
import com.ein.board.form.BoardFilesForm;
import com.ein.board.form.BoardForm;
import com.ein.board.form.BoardThreeFilesForm;
import com.ein.board.form.CommonForm;
 
@Service
public class BoardService {
	
	protected final Logger logger = LoggerFactory.getLogger(BoardService.class);
 
    @Autowired
    private BoardDao boardDao;
    
    /** 문자에서 HTML 부분 변경 */
    public String ChangeToHTMLB(String chString) {
	
		chString = chString.replace("&quot;", "\"");
		chString = chString.replace("&gt;", ">");
		chString = chString.replace("&lt;", "<");
		chString = chString.replace("&amp;", "&");
		
		return chString;
    }
 
    /** 占쌉쏙옙占쏙옙 - 占쏙옙占� 占쏙옙회 */
	public ResultUtil getBoardList(BoardForm boardForm) throws Exception {

		ResultUtil resultUtil = new ResultUtil();

		CommonDto commonDto = new CommonDto();

		int totalCount = boardDao.getBoardCnt(boardForm);
		if (totalCount != 0) {
			CommonForm commonForm = new CommonForm();
			commonForm.setFunction_name(boardForm.getFunction_name());
			commonForm.setCurrent_page_no(boardForm.getCurrent_page_no());
			commonForm.setCount_per_page(10);
			commonForm.setCount_per_list(4);
			commonForm.setTatal_list_count(totalCount);
			commonDto = PagingUtil.setPageUtil(commonForm);
			
			logger.debug("==================== getBoardDetail START ====================");
			logger.debug("commonForm.getCount_per_page()"+commonForm.getCount_per_page()+"=======");
			logger.debug("commonForm.getCount_per_list()"+commonForm.getCount_per_list()+"=======");
			logger.debug("commonForm.getCurrent_page_no()"+commonForm.getCurrent_page_no()+"=======");
		}
		
		logger.debug("==================== getBoardDetail START ====================");
		boardForm.setLimit(commonDto.getLimit());
		boardForm.setOffset(commonDto.getOffset());
		
		logger.debug("commonDto.getLimit()"+boardForm.getLimit()+"=======");
		logger.debug("commonDto.getOffset()"+boardForm.getOffset()+"=======");
		
		
		logger.debug("==================== getBoardDetail FIN ====================");
		
		List<BoardDto> list = new ArrayList<BoardDto>();
		try {
			list = boardDao.getBoardList(boardForm);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.debug("==================== getBoardDetail FIN ====================");
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", list);
		resultMap.put("totalCount", totalCount);
		resultMap.put("pagination", commonDto.getPagination());

		resultUtil.setData(resultMap);
		resultUtil.setState("SUCCESS");

		return resultUtil;
	}
    
    /** 占쌉쏙옙占쏙옙 - 占쏙옙 占쏙옙회 */
	public BoardDto getBoardDetail(BoardForm boardForm) throws Exception {

		BoardDto boardDto = new BoardDto();

		String searchType = boardForm.getSearch_type();
		

		if ("S".equals(searchType)) {

			int updateCnt = boardDao.updateBoardHits(boardForm);
	        
            if (updateCnt > 0) {
                boardDto = boardDao.getBoardDetail(boardForm);
                boardDto.setBb_Subject(ChangeToHTMLB(boardDto.getBb_Subject()));
                boardDto.setBb_Writer(ChangeToHTMLB(boardDto.getBb_Writer()));
                boardDto.setBb_Content(ChangeToHTMLB(boardDto.getBb_Content()));
            }
		} else {
            
            boardDto = boardDao.getBoardDetail(boardForm);
            boardDto.setBb_Subject(ChangeToHTMLB(boardDto.getBb_Subject()));
            boardDto.setBb_Writer(ChangeToHTMLB(boardDto.getBb_Writer()));
            boardDto.setBb_Content(ChangeToHTMLB(boardDto.getBb_Content()));
        }

		return boardDto;
	}
	
	
	
	/** 문자에서 HTML 부분 변경 
    public BoardDto ChangeToHTMLB(BoardDto boardDto) {
    	
    	String bbSubjectH = boardDto.getBb_Subject();
    	String bbWriterH = boardDto.getBb_Writer();
    	String bbContentH = boardDto.getBb_Content();
    	
    	logger.debug("==================== Are You Come In? ====================");
    	
    	if(bbSubjectH != null) {
			bbSubjectH = bbSubjectH.replace("&quot;", "\"");
			bbSubjectH = bbSubjectH.replace("&gt;", ">");
			bbSubjectH = bbSubjectH.replace("&lt;", "<");
			bbSubjectH = bbSubjectH.replace("&amp;", "&");
        	boardDto.setBb_Subject(bbSubjectH);
    	}
    	
    	if(bbWriterH != null) {
			bbWriterH = bbWriterH.replace("&quot;", "\"");
			bbWriterH = bbWriterH.replace("&gt;", ">");
			bbWriterH = bbWriterH.replace("&lt;", "<");
			bbWriterH = bbWriterH.replace("&amp;", "&");
        	boardDto.setBb_Writer(bbWriterH);
    	}
    	
    	if(bbContentH != null) {
			logger.debug("==================== Are You Come In? :"+bbContentH);
			bbContentH = bbContentH.replace("&quot;", "\"");
			logger.debug("==================== Are You Come In? :"+bbContentH);
			bbContentH = bbContentH.replace("&gt;", ">");
			logger.debug("==================== Are You Come In? :"+bbContentH);
			bbContentH = bbContentH.replace("&lt;", "<");
			logger.debug("==================== Are You Come In? :"+bbContentH);
			bbContentH = bbContentH.replace("&amp;", "&");
			logger.debug("==================== Are You Come In? :"+bbContentH);
			bbContentH = bbContentH.replace("&#59;", ";");
			logger.debug("==================== Are You Come In? :"+bbContentH);
        	boardDto.setBb_Content(bbContentH);
    	}
    	
    	
    	return boardDto;
    	
    } */
	
	/** 占쌉쏙옙占쏙옙 - 占쏙옙占� */
	public BoardDto insertBoard(BoardForm boardForm) throws Exception {

		BoardDto boardDto = new BoardDto();
		 
        int insertCnt = boardDao.insertBoard(boardForm);
 
        if (insertCnt > 0) {
            boardDto.setResult("SUCCESS");
        } else {
            boardDto.setResult("FAIL");
        }
 
        return boardDto;
	}
	
	/** 占쌉쏙옙占쏙옙 - 占쏙옙占쏙옙 */
	public BoardDto deleteBoard(BoardForm boardForm) throws Exception {
		 
        BoardDto boardDto = new BoardDto();
 
        int deleteCnt = boardDao.deleteBoard(boardForm);
        
 
        if (deleteCnt > 0) {
            boardDto.setResult("SUCCESS");
        } else {
            boardDto.setResult("FAIL");
        }
 
        return boardDto;
    }
	
	/** 占쌉쏙옙占쏙옙 - 占쏙옙占쏙옙 */
	public BoardDto updateBoard(BoardForm boardForm) throws Exception {
		 
        BoardDto boardDto = new BoardDto();
 
        int updateCnt = boardDao.updateBoard(boardForm);
 
        if (updateCnt > 0) {
            boardDto.setResult("SUCCESS");
        } else {
            boardDto.setResult("FAIL");
        }
 
        return boardDto;
    }
	
	/* ========================================================================================================
	 * 泥⑤��뙆�씪 愿��젴
	 * ========================================================================================================*/
	/** 게시판 - 목록 조회 */
	public ResultUtil getBoardListAFile(BoardAFileForm boardAFileForm) throws Exception {

		ResultUtil resultUtil = new ResultUtil();

		CommonDto commonDto = new CommonDto();

		int totalCount = boardDao.getBoardCntAFile(boardAFileForm);
		if (totalCount != 0) {
			CommonForm commonForm = new CommonForm();
			commonForm.setFunction_name(boardAFileForm.getFunction_name());
			commonForm.setCurrent_page_no(boardAFileForm.getCurrent_page_no());
			commonForm.setCount_per_page(10);
			commonForm.setCount_per_list(4);
			commonForm.setTatal_list_count(totalCount);
			commonDto = PagingUtil.setPageUtil(commonForm);
			
			logger.debug("==================== getBoardDetail START ====================");
			logger.debug("commonForm.getCount_per_page()"+commonForm.getCount_per_page()+"=======");
			logger.debug("commonForm.getCount_per_list()"+commonForm.getCount_per_list()+"=======");
			logger.debug("commonForm.getCurrent_page_no()"+commonForm.getCurrent_page_no()+"=======");
		}
		
		boardAFileForm.setLimit(commonDto.getLimit());
		boardAFileForm.setOffset(commonDto.getOffset());
		
		logger.debug("commonDto.getLimit()"+boardAFileForm.getLimit()+"=======");
		logger.debug("commonDto.getOffset()"+boardAFileForm.getOffset()+"=======");
		
		
		logger.debug("==================== getBoardDetailAFile FIN ====================");
		
		List<BoardAFileDto> list = new ArrayList<BoardAFileDto>();
		try {
			list = boardDao.getBoardListAFile(boardAFileForm);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.debug("==================== getBoardDetailAFile FIN ====================");
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", list);
		resultMap.put("totalCount", totalCount);
		resultMap.put("pagination", commonDto.getPagination());

		resultUtil.setData(resultMap);
		resultUtil.setState("SUCCESS");

		return resultUtil;
	}
    
	/** 게시판 - 상세 조회 */
	public BoardAFileDto getBoardDetailAFile(BoardAFileForm boardAFileForm) throws Exception {

		BoardAFileDto boardAFileDto = new BoardAFileDto();

		String searchType = boardAFileForm.getSearch_type();

		if ("S".equals(searchType)) {

			boardDao.updateBoardHitsAFile(boardAFileForm);
	        
		} 
		
		boardAFileDto = boardDao.getBoardDetailAFile(boardAFileForm);
		
		boardAFileDto.setBb_A_Subject(ChangeToHTMLB(boardAFileDto.getBb_A_Subject()));
		boardAFileDto.setBb_A_Writer(ChangeToHTMLB(boardAFileDto.getBb_A_Writer()));
		boardAFileDto.setBb_A_Content(ChangeToHTMLB(boardAFileDto.getBb_A_Content()));
		
		 
        BoardFileForm boardFileForm = new BoardFileForm();
        boardFileForm.setBb_A_Seq(boardAFileForm.getBb_A_Seq());
 
        boardAFileDto.setFiles(boardDao.getBoardFileList(boardFileForm));

		return boardAFileDto;
	}
	
	/** 게시판 - 등록 */
	public BoardAFileDto insertBoardAFile(BoardAFileForm boardAFileForm) throws Exception {

		BoardAFileDto boardAFileDto = new BoardAFileDto();
		
		logger.debug("=========================== boardFileList0 =================================");
		 
        int insertCnt = boardDao.insertBoardAFile(boardAFileForm);
        
        List<BoardFileForm> boardFileList = getBoardFileInfo(boardAFileForm);
        
        for (BoardFileForm boardFileForm : boardFileList) {
        	
            boardDao.insertBoardFile(boardFileForm);
            
        }
 
        if (insertCnt > 0) {
        	boardAFileDto.setResult("SUCCESS");
        } else {
        	boardAFileDto.setResult("FAIL");
        }
 
        return boardAFileDto;
	}
	
	/** 게시판 - 첨부파일 정보 조회 */
    public List<BoardFileForm> getBoardFileInfo(BoardAFileForm boardAFileForm) throws Exception {
 
        List<MultipartFile> files = boardAFileForm.getFiles();
 
        List<BoardFileForm> boardFileList = new ArrayList<BoardFileForm>();
 
        BoardFileForm boardFileForm = new BoardFileForm();
 
        int boardSeq = boardAFileForm.getBb_A_Seq();
        String fileName = null;
        String fileExt = null;
        String fileNameKey = null;
        String fileSize = null;
        // 파일이 저장될 Path 설정
        String filePath = "C:\\board\\file";
        
        if (files != null && files.size() > 0) {
 
            File file = new File(filePath);
            
            // 디렉토리가 없으면 생성
            if (file.exists() == false) {
                file.mkdirs();
            }
 
            for (MultipartFile multipartFile : files) {
 
                fileName = multipartFile.getOriginalFilename();
                fileExt = fileName.substring(fileName.lastIndexOf("."));
                // 파일명 변경(uuid로 암호화) + 확장자
                fileNameKey = getRandomString() + fileExt;
                fileSize = String.valueOf(multipartFile.getSize());
 
                // 설정한 Path에 파일 저장
                file = new File(filePath + "/" + fileNameKey);
 
                multipartFile.transferTo(file);
 
                boardFileForm = new BoardFileForm();
                boardFileForm.setBb_A_Seq(boardSeq);
                boardFileForm.setFile_name(fileName);
                boardFileForm.setFile_name_key(fileNameKey);
                boardFileForm.setFile_path(filePath);
                boardFileForm.setFile_size(fileSize);
                boardFileList.add(boardFileForm);
            }
        }
 
        return boardFileList;
    }
	
    /** 게시판 - 삭제 */
	public BoardAFileDto deleteBoardAFile(BoardAFileForm boardAFileForm) throws Exception {
		 
		BoardAFileDto boardAFileDto = new BoardAFileDto();
 
        int deleteCnt = boardDao.deleteBoardAFile(boardAFileForm);
        
 
        if (deleteCnt > 0) {
        	boardAFileDto.setResult("SUCCESS");
        } else {
        	boardAFileDto.setResult("FAIL");
        }
 
        return boardAFileDto;
    }
	
	/** 게시판 - 수정 */
	public BoardAFileDto updateBoardAFile(BoardAFileForm boardAFileForm) throws Exception {
		 
		BoardAFileDto boardAFileDto = new BoardAFileDto();
 
        int updateCnt = boardDao.updateBoardAFile(boardAFileForm);
        
        List<BoardFileForm> boardFileList = getBoardFileInfo(boardAFileForm);
        
        logger.debug("=========================== boardFileList0 =================================");
        
        logger.debug("boardAFileForm.getBoard_seq():" + boardAFileForm.getBb_A_Seq()+"=====");
        logger.debug("boardAFileForm.getBoard_seq():" + boardAFileForm.getFilesNoAll()+"=====");
        logger.debug("boardAFileForm.getBoard_seq():" + boardAFileForm.getFilesNo()+"=====");
        
        logger.debug("=========================== boardFileList0 =================================");
        
        int fileNumber = boardAFileForm.getFilesNo();
        int fileNumberAll = boardAFileForm.getFilesNoAll();        
		
		logger.debug("=========================== WHATTHE... =================================");
    	
		if (fileNumber != fileNumberAll) {
			BoardFileForm boardFileFormForDel = new BoardFileForm();
	    	boardFileFormForDel.setBb_A_Seq(boardAFileForm.getBb_A_Seq());
			boardFileFormForDel.setFile_no(fileNumberAll);
			boardDao.deleteBoardFile(boardFileFormForDel);
		}
        
        for (BoardFileForm boardFileForm : boardFileList) {
        	
            boardDao.insertBoardFile(boardFileForm);
            
        }
 
        if (updateCnt > 0) {
        	boardAFileDto.setResult("SUCCESS");
        } else {
        	boardAFileDto.setResult("FAIL");
        }
 
        return boardAFileDto;
    }
	
	/** 첨부파일 - 삭제 */
    public BoardAFileDto deleteBoardFile(BoardFileForm boardFileForm) throws Exception {
 
    	BoardAFileDto boardAFileDto = new BoardAFileDto();
    	logger.debug("=========================== boardFileList0 =================================");
    	
    	logger.debug("boardFileForm.getBoard_seq():" + boardFileForm.getBb_A_Seq()+"=====");
    	
    	logger.debug("=========================== boardFileList0 =================================");
 
        int deleteCnt = boardDao.deleteBoardFile(boardFileForm);
        
        logger.debug("deleteCnt:" + deleteCnt+"=====");
        logger.debug("=========================== boardFileList0 =================================");
        
        if (deleteCnt > 0) {
        	boardAFileDto.setResult("SUCCESS");
        } else {
        	boardAFileDto.setResult("FAIL");
        }
 
        return boardAFileDto;
    }
	
	/** 32글자의 랜덤한 문자열(숫자포함) 생성 */
    public static String getRandomString() {
 
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
	
    /* ========================================================================================================
	 * 다중 첨부파일 관련
	 * ========================================================================================================*/
	/** 게시판 - 목록 조회 */
	public ResultUtil getBoardListThreeFiles(BoardThreeFilesForm boardThreeFilesForm) throws Exception {

		ResultUtil resultUtil = new ResultUtil();

		CommonDto commonDto = new CommonDto();

		int totalCount = boardDao.getBoardCntThreeFiles(boardThreeFilesForm);
		if (totalCount != 0) {
			CommonForm commonForm = new CommonForm();
			commonForm.setFunction_name(boardThreeFilesForm.getFunction_name());
			commonForm.setCurrent_page_no(boardThreeFilesForm.getCurrent_page_no());
			commonForm.setCount_per_page(10);
			commonForm.setCount_per_list(4);
			commonForm.setTatal_list_count(totalCount);
			commonDto = PagingUtil.setPageUtil(commonForm);
			
			logger.debug("==================== getBoardDetail START ====================");
			logger.debug("commonForm.getCount_per_page()"+commonForm.getCount_per_page()+"=======");
			logger.debug("commonForm.getCount_per_list()"+commonForm.getCount_per_list()+"=======");
			logger.debug("commonForm.getCurrent_page_no()"+commonForm.getCurrent_page_no()+"=======");
		}
		
		boardThreeFilesForm.setLimit(commonDto.getLimit());
		boardThreeFilesForm.setOffset(commonDto.getOffset());
		
		logger.debug("commonDto.getLimit()"+boardThreeFilesForm.getLimit()+"=======");
		logger.debug("commonDto.getOffset()"+boardThreeFilesForm.getOffset()+"=======");
		
		logger.debug("==================== getBoardDetailThreeFiles FIN ====================");
		
		List<BoardThreeFilesDto> list = new ArrayList<BoardThreeFilesDto>();
		try {
			list = boardDao.getBoardListThreeFiles(boardThreeFilesForm);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.debug("commonDto.getOffset()"+boardThreeFilesForm.getBb_File_Seq()+"=======");
		
		logger.debug("==================== getBoardDetailThreeFiles FIN ====================");
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", list);
		resultMap.put("totalCount", totalCount);
		resultMap.put("pagination", commonDto.getPagination());

		resultUtil.setData(resultMap);
		resultUtil.setState("SUCCESS");

		return resultUtil;
	}
    
	/** 게시판 - 상세 조회 */
	public BoardThreeFilesDto getBoardDetailThreeFiles(BoardThreeFilesForm boardThreeFilesForm) throws Exception {

		BoardThreeFilesDto boardThreeFilesDto = new BoardThreeFilesDto();

		String searchType = boardThreeFilesForm.getSearch_type();

		if ("S".equals(searchType)) {

			boardDao.updateBoardHitsThreeFiles(boardThreeFilesForm);
	        
		} 
		
		boardThreeFilesDto = boardDao.getBoardDetailThreeFiles(boardThreeFilesForm);
		
		boardThreeFilesDto.setBb_File_Subject(ChangeToHTMLB(boardThreeFilesDto.getBb_File_Subject()));
		boardThreeFilesDto.setBb_File_Writer(ChangeToHTMLB(boardThreeFilesDto.getBb_File_Writer()));
		boardThreeFilesDto.setBb_File_Content(ChangeToHTMLB(boardThreeFilesDto.getBb_File_Content()));
		 
        BoardFilesForm boardFilesForm = new BoardFilesForm();
        boardFilesForm.setBb_File_Seq(boardThreeFilesForm.getBb_File_Seq());
 
        boardThreeFilesDto.setFiles(boardDao.getBoardFilesList(boardFilesForm));

		return boardThreeFilesDto;
	}
	
	/** 게시판 - 등록 */
	public BoardThreeFilesDto insertBoardThreeFiles(BoardThreeFilesForm boardThreeFilesForm) throws Exception {

		BoardThreeFilesDto boardThreeFilesDto = new BoardThreeFilesDto();
		
		logger.debug("=========================== boardFileList0 =================================");
		 
        int insertCnt = boardDao.insertBoardThreeFiles(boardThreeFilesForm);
        
        List<BoardFilesForm> boardFileList = getBoardFilesInfo(boardThreeFilesForm);
        
        for (BoardFilesForm boardFilesForm : boardFileList) {
        	
            boardDao.insertBoardFiles(boardFilesForm);
            
        }
 
        if (insertCnt > 0) {
        	boardThreeFilesDto.setResult("SUCCESS");
        } else {
        	boardThreeFilesDto.setResult("FAIL");
        }
 
        return boardThreeFilesDto;
	}
	
	/** 게시판 - 첨부파일 정보 조회 */
    public List<BoardFilesForm> getBoardFilesInfo(BoardThreeFilesForm boardThreeFilesForm) throws Exception {
 

    	List<MultipartFile> files = boardThreeFilesForm.getFiles();
 
        List<BoardFilesForm> boardFileList = new ArrayList<BoardFilesForm>();
 
        BoardFilesForm boardFilesForm = new BoardFilesForm();
 
        int boardSeq = boardThreeFilesForm.getBb_File_Seq();
        String fileName = null;
        String fileExt = null;
        String fileNameKey = null;
        String fileSize = null;
        // 파일이 저장될 Path 설정
        String filePath = "C:\\board\\file";
        
        if (files != null && files.size() > 0) {
 
            File file = new File(filePath);
            
            // 디렉토리가 없으면 생성
            if (file.exists() == false) {
                file.mkdirs();
            }
 
            for (MultipartFile multipartFile : files) {
            	if (multipartFile == null) {
            		continue;
            	}
                fileName = multipartFile.getOriginalFilename();
                fileExt = fileName.substring(fileName.lastIndexOf("."));
                // 파일명 변경(uuid로 암호화) + 확장자
                fileNameKey = getRandomString() + fileExt;
                fileSize = String.valueOf(multipartFile.getSize());
 
                // 설정한 Path에 파일 저장
                file = new File(filePath + "/" + fileNameKey);
 
                multipartFile.transferTo(file);
 
                boardFilesForm = new BoardFilesForm();
                boardFilesForm.setBb_File_Seq(boardSeq);
                boardFilesForm.setFile_name(fileName);
                boardFilesForm.setFile_name_key(fileNameKey);
                boardFilesForm.setFile_path(filePath);
                boardFilesForm.setFile_size(fileSize);
                boardFileList.add(boardFilesForm);
            }
        }
 
        return boardFileList;
    }
	
    /** 게시판 - 삭제 */
	public BoardThreeFilesDto deleteBoardThreeFiles(BoardThreeFilesForm boardThreeFilesForm) throws Exception {
		 
		BoardThreeFilesDto boardThreeFilesDto = new BoardThreeFilesDto();
 
        int deleteCnt = boardDao.deleteBoardThreeFiles(boardThreeFilesForm);
        
 
        if (deleteCnt > 0) {
        	boardThreeFilesDto.setResult("SUCCESS");
        } else {
        	boardThreeFilesDto.setResult("FAIL");
        }
 
        return boardThreeFilesDto;
    }
	
	/** 게시판 - 수정 */
	public BoardThreeFilesDto updateBoardThreeFiles(BoardThreeFilesForm boardThreeFilesForm) throws Exception {
		 
		BoardThreeFilesDto boardThreeFilesDto = new BoardThreeFilesDto();
 
        int updateCnt = boardDao.updateBoardThreeFiles(boardThreeFilesForm);
        
        List<BoardFilesForm> boardFileList = getBoardFilesInfo(boardThreeFilesForm);
        
        logger.debug("=========================== boardFileList0 =================================");
        
        logger.debug("boardFilesForm.getBoard_seq():" + boardThreeFilesForm.getBb_File_Seq()+"=====");
        logger.debug("boardFilesForm.getBoard_seq():" + boardThreeFilesForm.getFilesNoAll()+"=====");
        logger.debug("boardFilesForm.getBoard_seq():" + boardThreeFilesForm.getFilesNo()+"=====");
        
        logger.debug("=========================== boardFileList0 =================================");
        
        if (boardThreeFilesForm.getFilesNoAll() != null ) {
        	List<String> fileUnuse = boardThreeFilesForm.getFilesNo();
            List<String> fileNumberAll = boardThreeFilesForm.getFilesNoAll();
            int checkingNum = fileNumberAll.size();
            
            for(int a = 0; a < checkingNum; a++) {
            	String unUse = fileUnuse.get(a);
            	
            	logger.debug("=========================== boardFileList0 =================================");
                
            	logger.debug("boardFilesForm.unUse:" + fileUnuse.get(a) +"=====");
            	logger.debug("boardFilesForm.numberAll():" + fileNumberAll.get(a) +"=====");
                
                logger.debug("=========================== boardFileList0 =================================");
            	
                if(unUse.length() <= 0) {
                	logger.debug("=========================== WHATTHE... =================================");
                	int numberAll = Integer.parseInt(fileNumberAll.get(a));
                	
                	BoardFilesForm boardFilesFormForDel = new BoardFilesForm();
            		boardFilesFormForDel.setBb_File_Seq(boardThreeFilesForm.getBb_File_Seq());
            		boardFilesFormForDel.setFile_no(numberAll);
            		boardDao.deleteBoardFiles(boardFilesFormForDel);            	
                }
        	
            }
        }
        //파일 사용 유무 체크하기
                
        
        	
        	/*for(int a = 0; a < checkingNum; a++) {
            	String unUse = fileUnuse.get(a);
            	
            	logger.debug("=========================== boardFileList0 =================================");
                
            	logger.debug("boardFilesForm.unUse:" + fileUnuse.get(a) +"=====");
            	logger.debug("boardFilesForm.numberAll():" + fileNumberAll.get(a) +"=====");
                
                logger.debug("=========================== boardFileList0 =================================");
            	
                if(unUse.length() <= 0) {
                	logger.debug("=========================== WHATTHE... =================================");
                	int numberAll = Integer.parseInt(fileNumberAll.get(a));
                	
                	BoardFilesForm boardFilesFormForDel = new BoardFilesForm();
            		boardFilesFormForDel.setBb_File_Seq(boardThreeFilesForm.getBb_File_Seq());
            		boardFilesFormForDel.setFile_no(numberAll);
            		boardDao.deleteBoardFiles(boardFilesFormForDel);            	
                }
                
                
                if(unUse.length() > 0) {
                	int numberAll = Integer.parseInt(fileNumberAll.get(a));
                	
                	BoardFilesForm boardFilesFormForDel = new BoardFilesForm();
            		boardFilesFormForDel.setBb_File_Seq(boardThreeFilesForm.getBb_File_Seq());
            		boardFilesFormForDel.setFile_no(numberAll);
            		boardDao.deleteBoardFiles(boardFilesFormForDel);
                }
                
                
                if (unUse == "undefined" || unUse == null || unUse == "") {
            		
            		int numberAll = Integer.parseInt(fileNumberAll.get(a));
                	
                	BoardFilesForm boardFilesFormForDel = new BoardFilesForm();
            		boardFilesFormForDel.setBb_File_Seq(boardThreeFilesForm.getBb_File_Seq());
            		boardFilesFormForDel.setFile_no(numberAll);
            		boardDao.deleteBoardFiles(boardFilesFormForDel);
                	
            	}
            }
        }*/

        for (BoardFilesForm boardFilesForm : boardFileList) {
        	
            boardDao.insertBoardFiles(boardFilesForm);
        }
 
        if (updateCnt > 0) {
        	boardThreeFilesDto.setResult("SUCCESS");
        } else {
        	boardThreeFilesDto.setResult("FAIL");
        }
 
        return boardThreeFilesDto;
    }
	
	/** 첨부파일 - 삭제 */
    public BoardThreeFilesDto deleteBoardFiles(BoardFilesForm boardFilesForm) throws Exception {
 
    	BoardThreeFilesDto boardThreeFilesDto = new BoardThreeFilesDto();
    	logger.debug("=========================== boardFileList0 =================================");
    	
    	logger.debug("boardFilesForm.getBoard_seq():" + boardFilesForm.getBb_File_Seq()+"=====");
    	
    	logger.debug("=========================== boardFileList0 =================================");
    	
    	
 
        int deleteCnt = boardDao.deleteBoardFiles(boardFilesForm);
        
        logger.debug("deleteCnt:" + deleteCnt+"=====");
        logger.debug("=========================== boardFileList0 =================================");
        
        if (deleteCnt > 0) {
        	boardThreeFilesDto.setResult("SUCCESS");
        } else {
        	boardThreeFilesDto.setResult("FAIL");
        }
 
        return boardThreeFilesDto;
    }
    
    
    
    /* ========================================================================================================
	 * 다중 첨부파일 관련
	 * ========================================================================================================*/
	
    /** 게시판 - 목록 조회 */
	public ResultUtil getBoardListComment(BoardCommentForm boardCommentForm) throws Exception {

		ResultUtil resultUtil = new ResultUtil();

		CommonDto commonDto = new CommonDto();

		int totalCount = boardDao.getBoardCntComment(boardCommentForm);
		if (totalCount != 0) {
			CommonForm commonForm = new CommonForm();
			commonForm.setFunction_name(boardCommentForm.getFunction_name());
			commonForm.setCurrent_page_no(boardCommentForm.getCurrent_page_no());
			commonForm.setCount_per_page(10);
			commonForm.setCount_per_list(4);
			commonForm.setTatal_list_count(totalCount);
			commonDto = PagingUtil.setPageUtil(commonForm);
			
			logger.debug("==================== getBoardDetail START ====================");
			logger.debug("commonForm.getCount_per_page()"+commonForm.getCount_per_page()+"=======");
			logger.debug("commonForm.getCount_per_list()"+commonForm.getCount_per_list()+"=======");
			logger.debug("commonForm.getCurrent_page_no()"+commonForm.getCurrent_page_no()+"=======");
		}
		
		boardCommentForm.setLimit(commonDto.getLimit());
		boardCommentForm.setOffset(commonDto.getOffset());
		
		logger.debug("commonDto.getLimit()"+boardCommentForm.getLimit()+"=======");
		logger.debug("commonDto.getOffset()"+boardCommentForm.getOffset()+"=======");
		
		logger.debug("==================== getBoardDetailComment FIN ====================");
		
		List<BoardCommentDto> list = new ArrayList<BoardCommentDto>();
		try {
			list = boardDao.getBoardListComment(boardCommentForm);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.debug("commonDto.getOffset()"+boardCommentForm.getBb_Comment_Seq()+"=======");
		
		logger.debug("==================== getBoardDetailComment FIN ====================");
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", list);
		resultMap.put("totalCount", totalCount);
		resultMap.put("pagination", commonDto.getPagination());

		resultUtil.setData(resultMap);
		resultUtil.setState("SUCCESS");

		return resultUtil;
	}
	
	/** 게시판 - 상세 조회 */
	public BoardCommentDto getBoardDetailComment(BoardCommentForm boardCommentForm) throws Exception {

		BoardCommentDto boardCommentDto = new BoardCommentDto();

		String searchType = boardCommentForm.getSearch_type();

		if ("S".equals(searchType)) {

			boardDao.updateBoardHitsComment(boardCommentForm);
	        
		} 
		
		boardCommentDto = boardDao.getBoardDetailComment(boardCommentForm);
		
		boardCommentDto.setBb_Comment_Subject(ChangeToHTMLB(boardCommentDto.getBb_Comment_Subject()));
		boardCommentDto.setBb_Comment_Writer(ChangeToHTMLB(boardCommentDto.getBb_Comment_Writer()));
		boardCommentDto.setBb_Comment_Content(ChangeToHTMLB(boardCommentDto.getBb_Comment_Content()));
		 
        BoardCommentFileForm boardCommentFileForm = new BoardCommentFileForm();
        boardCommentFileForm.setBb_Comment_Seq(boardCommentForm.getBb_Comment_Seq());
 
        boardCommentDto.setFiles(boardDao.getBoardCommentFileList(boardCommentFileForm));
        
        
        
        

		return boardCommentDto;
	}
	
	/** 게시판 - 등록 */
	public BoardCommentDto insertBoardComment(BoardCommentForm boardCommentForm) throws Exception {

		BoardCommentDto boardCommentDto = new BoardCommentDto();
		
		logger.debug("=========================== boardFileList0 =================================");
		 
        int insertCnt = boardDao.insertBoardComment(boardCommentForm);
        
        List<BoardCommentFileForm> boardFileList = getBoardCommentFileInfo(boardCommentForm);
        
        for (BoardCommentFileForm boardCommentFileForm : boardFileList) {
        	
            boardDao.insertBoardCommentFile(boardCommentFileForm);
            
        }
 
        if (insertCnt > 0) {
        	boardCommentDto.setResult("SUCCESS");
        } else {
        	boardCommentDto.setResult("FAIL");
        }
 
        return boardCommentDto;
	}
	
	/** 게시판 - 첨부파일 정보 조회 */
    public List<BoardCommentFileForm> getBoardCommentFileInfo(BoardCommentForm boardCommentForm) throws Exception {
 

    	List<MultipartFile> files = boardCommentForm.getFiles();
 
        List<BoardCommentFileForm> boardFileList = new ArrayList<BoardCommentFileForm>();
 
        BoardCommentFileForm boardCommentFileForm = new BoardCommentFileForm();
 
        int boardSeq = boardCommentForm.getBb_Comment_Seq();
        String fileName = null;
        String fileExt = null;
        String fileNameKey = null;
        String fileSize = null;
        // 파일이 저장될 Path 설정
        String filePath = "C:\\board\\file";
        
        if (files != null && files.size() > 0) {
 
            File file = new File(filePath);
            
            // 디렉토리가 없으면 생성
            if (file.exists() == false) {
                file.mkdirs();
            }
 
            for (MultipartFile multipartFile : files) {
            	if (multipartFile == null) {
            		continue;
            	}
                fileName = multipartFile.getOriginalFilename();
                fileExt = fileName.substring(fileName.lastIndexOf("."));
                // 파일명 변경(uuid로 암호화) + 확장자
                fileNameKey = getRandomString() + fileExt;
                fileSize = String.valueOf(multipartFile.getSize());
 
                // 설정한 Path에 파일 저장
                file = new File(filePath + "/" + fileNameKey);
 
                multipartFile.transferTo(file);
 
                boardCommentFileForm = new BoardCommentFileForm();
                boardCommentFileForm.setBb_Comment_Seq(boardSeq);
                boardCommentFileForm.setFile_name(fileName);
                boardCommentFileForm.setFile_name_key(fileNameKey);
                boardCommentFileForm.setFile_path(filePath);
                boardCommentFileForm.setFile_size(fileSize);
                boardFileList.add(boardCommentFileForm);
            }
        }
 
        return boardFileList;
    }
    
    /** 게시판 - 삭제 */
	public BoardCommentDto deleteBoardComment(BoardCommentForm boardCommentForm) throws Exception {
		 
		BoardCommentDto boardCommentDto = new BoardCommentDto();
 
        int deleteCnt = boardDao.deleteBoardComment(boardCommentForm);
        
 
        if (deleteCnt > 0) {
        	boardCommentDto.setResult("SUCCESS");
        } else {
        	boardCommentDto.setResult("FAIL");
        }
 
        return boardCommentDto;
    }
	
	/** 게시판 - 수정 */
	public BoardCommentDto updateBoardComment(BoardCommentForm boardCommentForm) throws Exception {
		 
		BoardCommentDto boardCommentDto = new BoardCommentDto();
 
        int updateCnt = boardDao.updateBoardComment(boardCommentForm);
        
        List<BoardCommentFileForm> boardFileList = getBoardCommentFileInfo(boardCommentForm);
        
        logger.debug("=========================== boardFileList0 =================================");
        
        logger.debug("boardCommentFileForm.getBb_Comment_Seq()():" + boardCommentForm.getBb_Comment_Seq()+"=====");
        logger.debug("boardCommentFileForm.getBb_Comment_Seq()():" + boardCommentForm.getFilesNoAll()+"=====");
        logger.debug("boardCommentFileForm.getBb_Comment_Seq()():" + boardCommentForm.getFilesNo()+"=====");
        
        logger.debug("=========================== boardFileList0 =================================");
        
        if (boardCommentForm.getFilesNoAll() != null ) {
        	List<String> fileUnuse = boardCommentForm.getFilesNo();
            List<String> fileNumberAll = boardCommentForm.getFilesNoAll();
            int checkingNum = fileNumberAll.size();
            
            for(int a = 0; a < checkingNum; a++) {
            	String unUse = fileUnuse.get(a);
            	
            	logger.debug("=========================== boardFileList0 =================================");
                
            	logger.debug("boardCommentFileForm.unUse:" + fileUnuse.get(a) +"=====");
            	logger.debug("boardCommentFileForm.numberAll():" + fileNumberAll.get(a) +"=====");
                
                logger.debug("=========================== boardFileList0 =================================");
            	
                if(unUse.length() <= 0) {
                	logger.debug("=========================== WHATTHE... =================================");
                	int numberAll = Integer.parseInt(fileNumberAll.get(a));
                	
                	BoardCommentFileForm boardFilesFormForDel = new BoardCommentFileForm();
            		boardFilesFormForDel.setBb_Comment_Seq(boardCommentForm.getBb_Comment_Seq());
            		boardFilesFormForDel.setFile_no(numberAll);
            		boardDao.deleteBoardCommentFile(boardFilesFormForDel);            	
                }
            }
        }

        for (BoardCommentFileForm boardCommentFileForm : boardFileList) {
        	
            boardDao.insertBoardCommentFile(boardCommentFileForm);
        }
 
        if (updateCnt > 0) {
        	boardCommentDto.setResult("SUCCESS");
        } else {
        	boardCommentDto.setResult("FAIL");
        }
 
        return boardCommentDto;
    }
	
	/** 첨부파일 - 삭제 */
    public BoardCommentDto deleteBoardCommentFile(BoardCommentFileForm boardCommentFileForm) throws Exception {
 
    	BoardCommentDto boardCommentDto = new BoardCommentDto();
    	logger.debug("=========================== boardFileList0 =================================");
    	
    	logger.debug("boardCommentFileForm.getBb_Comment_Seq():" + boardCommentFileForm.getBb_Comment_Seq()+"=====");
    	
    	logger.debug("=========================== boardFileList0 =================================");
    	
    	
 
        int deleteCnt = boardDao.deleteBoardCommentFile(boardCommentFileForm);
        
        logger.debug("deleteCnt:" + deleteCnt+"=====");
        logger.debug("=========================== boardFileList0 =================================");
        
        if (deleteCnt > 0) {
        	boardCommentDto.setResult("SUCCESS");
        } else {
        	boardCommentDto.setResult("FAIL");
        }
 
        return boardCommentDto;
    }    
    
    /** 게시판 - 댓글조회 */
	public List<BoardCommentComDto> getBoardListCommentCom(BoardCommentComForm boardCommentComForm) throws Exception{
		return boardDao.getBoardListCommentCom(boardCommentComForm);
	}
	
	/** 게시판 - 댓글등록 */ 
	public BoardCommentComDto insertBoardCommentCom(BoardCommentComForm boardCommentComForm) throws Exception {

		BoardCommentComDto boardCommentComDto = new BoardCommentComDto();
		
		logger.debug("=========================== boardFileList0 =================================");
		logger.debug("=========================== boardFileList0" + boardCommentComForm.getBb_Comment_Seq());
		logger.debug("=========================== boardFileList0" + boardCommentComForm.getCom_Content());
		logger.debug("=========================== boardFileList0 =================================");
		 
        int insertCnt = boardDao.insertBoardCommentCom(boardCommentComForm);
 
        if (insertCnt > 0) {
        	boardCommentComDto.setResult("SUCCESS");
        } else {
        	boardCommentComDto.setResult("FAIL");
        }
 
        return boardCommentComDto;
	}
	
	/** 게시판 - 댓글삭제 */
    public BoardCommentComDto deleteBoardCommentCom(BoardCommentForm boardCommentForm) throws Exception {
 
    	BoardCommentComDto boardCommentComDto = new BoardCommentComDto();
    	logger.debug("=========================== boardFileList0 =================================");
    	
    	logger.debug("boardCommentComForm.getBb_Comment_Seq():" + boardCommentForm.getBb_Comment_Seq()+"=====");
    	logger.debug("boardCommentComForm.getBb_Com_No():" + boardCommentForm.getComNoZip()+"=====");
    	logger.debug("boardCommentComForm.getBb_Com_No_All():" + boardCommentForm.getComNoAllZip()+"=====");
    	
    	logger.debug("=========================== boardFileList0 =================================");
    	
    	int deleteCnt = 0;
    	BoardCommentComForm boardCommentComForm = new BoardCommentComForm();
    	
    	
    	if (boardCommentForm.getComNoAllZip() != null ) {
        	List<String> comUnuse = boardCommentForm.getComNoZip();
            List<String> comNumberAll = boardCommentForm.getComNoAllZip();
            int checkingNum = comNumberAll.size();
            
            for(int a = 0; a < checkingNum; a++) {
            	int unUse = Integer.parseInt(comUnuse.get(a));
            	            	
                if(unUse == 0) {
                	logger.debug("=========================== WHATTHE... =================================");
                	int numberAll = Integer.parseInt(comNumberAll.get(a));
                	
                	//boardCommentComForm.setBb_Comment_Seq(boardCommentForm.getBb_Comment_Seq());
                	boardCommentComForm.setCom_No(numberAll);
                	deleteCnt = boardDao.deleteBoardCommentCom(boardCommentComForm); 
                	
                	break;
                }
            }
        }
    	
        logger.debug("deleteCnt:" + deleteCnt +"=====");
        logger.debug("=========================== boardFileList0 =================================");
        
        if (deleteCnt > 0) {
        	boardCommentComDto.setResult("SUCCESS");
        } else {
        	boardCommentComDto.setResult("FAIL");
        }
 
        return boardCommentComDto;
    }
}

