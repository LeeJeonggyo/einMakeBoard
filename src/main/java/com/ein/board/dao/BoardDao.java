package com.ein.board.dao;

import java.util.List;

import javax.annotation.Resource;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ein.board.dto.BoardAFileDto;
import com.ein.board.dto.BoardCommentComDto;
import com.ein.board.dto.BoardCommentDto;
import com.ein.board.dto.BoardCommentFileDto;
import com.ein.board.dto.BoardDto;
import com.ein.board.dto.BoardFileDto;
import com.ein.board.dto.BoardFilesDto;
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
 
@Repository
public class BoardDao {
	
	protected final Logger logger = LoggerFactory.getLogger(BoardService.class);
 
    @Resource(name = "sqlSession")
    private SqlSession sqlSession;
 
    private static final String NAMESPACE = "com.ein.board.boardMapper";
    
    /** 占쌉쏙옙占쏙옙 - 占쏙옙占� 占쏙옙 */
	public int getBoardCnt(BoardForm boardForm) throws Exception {

		return sqlSession.selectOne(NAMESPACE + ".getBoardCnt", boardForm);
	}
 
    /** 占쌉쏙옙占쏙옙 - 占쏙옙占� 占쏙옙회 */
    public List<BoardDto> getBoardList(BoardForm boardForm) throws Exception {
 
        return sqlSession.selectList(NAMESPACE + ".getBoardList", boardForm);
    }
    
    /** 占쌉쏙옙占쏙옙 - 占쏙옙회 占쏙옙 占쏙옙占쏙옙 */
	public int updateBoardHits(BoardForm boardForm) throws Exception {

		return sqlSession.update(NAMESPACE + ".updateBoardHits", boardForm);
	}
	
	/** 占쌉쏙옙占쏙옙 - 占쏙옙 占쏙옙회 */
	public BoardDto getBoardDetail(BoardForm boardForm) throws Exception {

		return sqlSession.selectOne(NAMESPACE + ".getBoardDetail", boardForm);
	}
	
	/** 占쌉쏙옙占쏙옙 - 占쏙옙占� */
	public int insertBoard(BoardForm boardForm) throws Exception {
		return sqlSession.insert(NAMESPACE + ".insertBoard", boardForm);
	}
	
	/** 占쌉쏙옙占쏙옙 - 占쏙옙占쏙옙 */
	public int deleteBoard(BoardForm boardForm) throws Exception {

		return sqlSession.delete(NAMESPACE + ".deleteBoard", boardForm);
	}
	
	/** 占쌉쏙옙占쏙옙 - 占쏙옙占쏙옙 */
	public int updateBoard(BoardForm boardForm) throws Exception {
        
        return sqlSession.update(NAMESPACE + ".updateBoard", boardForm);
    }
    
	/* ========================================================================================================
	 * 泥⑤��뙆�씪 愿��젴
	 * ========================================================================================================*/
	
	/** 게시판 - 목록 수 */
	public int getBoardCntAFile(BoardAFileForm boardAFileForm) throws Exception {

		return sqlSession.selectOne(NAMESPACE + ".getBoardCntAFile", boardAFileForm);
	}
 
	/** 게시판 - 목록 조회 */
    public List<BoardAFileDto> getBoardListAFile(BoardAFileForm boardAFileForm) throws Exception {
 
        return sqlSession.selectList(NAMESPACE + ".getBoardListAFile", boardAFileForm);
    }
    
    /** 게시판 - 조회 수 수정 */
	public int updateBoardHitsAFile(BoardAFileForm boardAFileForm) throws Exception {

		return sqlSession.update(NAMESPACE + ".updateBoardHitsAFile", boardAFileForm);
	}
	
	/** 게시판 - 상세 조회 */
	public BoardAFileDto getBoardDetailAFile(BoardAFileForm boardAFileForm) throws Exception {

		return sqlSession.selectOne(NAMESPACE + ".getBoardDetailAFile", boardAFileForm);
	}
	
	/** 게시판 - 첨부파일 조회 */
    public List<BoardFileDto> getBoardFileList(BoardFileForm boardFileForm) throws Exception {
 
        return sqlSession.selectList(NAMESPACE + ".getBoardFileList", boardFileForm);
    }
	
	/** 게시판 - 등록 */
	public int insertBoardAFile(BoardAFileForm boardAFileForm) throws Exception {
		return sqlSession.insert(NAMESPACE + ".insertBoardAFile", boardAFileForm);
	}
	
	/** 게시판 - 첨부파일 등록 */
    public int insertBoardFile(BoardFileForm boardFileForm) throws Exception {
        return sqlSession.insert(NAMESPACE + ".insertBoardFile", boardFileForm);
    }
	
    /** 게시판 - 삭제 */
	public int deleteBoardAFile(BoardAFileForm boardAFileForm) throws Exception {

		return sqlSession.delete(NAMESPACE + ".deleteBoardAFile", boardAFileForm);
	}
	
	/** 게시판 - 수정 */
	public int updateBoardAFile(BoardAFileForm boardAFileForm) throws Exception {
        
        return sqlSession.update(NAMESPACE + ".updateBoardAFile", boardAFileForm);
    }

	/** 첨부파일 - 삭제  */
    public int deleteBoardFile(BoardFileForm boardFileForm) throws Exception {
        
        return sqlSession.delete(NAMESPACE + ".deleteBoardFile", boardFileForm);
    }
    
    /* ========================================================================================================
	 * 다중 첨부파일 관련
	 * ========================================================================================================*/
	
	/** 게시판 - 목록 수 */
	public int getBoardCntThreeFiles(BoardThreeFilesForm boardThreeFilesForm) throws Exception {

		return sqlSession.selectOne(NAMESPACE + ".getBoardCntThreeFiles", boardThreeFilesForm);
	}
 
	/** 게시판 - 목록 조회 */
    public List<BoardThreeFilesDto> getBoardListThreeFiles(BoardThreeFilesForm boardThreeFilesForm) throws Exception {
 
        return sqlSession.selectList(NAMESPACE + ".getBoardListThreeFiles", boardThreeFilesForm);
    }
    
    /** 게시판 - 조회 수 수정 */
	public int updateBoardHitsThreeFiles(BoardThreeFilesForm boardThreeFilesForm) throws Exception {

		return sqlSession.update(NAMESPACE + ".updateBoardHitsThreeFiles", boardThreeFilesForm);
	}
	
	/** 게시판 - 상세 조회 */
	public BoardThreeFilesDto getBoardDetailThreeFiles(BoardThreeFilesForm boardThreeFilesForm) throws Exception {

		return sqlSession.selectOne(NAMESPACE + ".getBoardDetailThreeFiles", boardThreeFilesForm);
	}
	
	/** 게시판 - 첨부파일 조회 */
    public List<BoardFilesDto> getBoardFilesList(BoardFilesForm boardFilesForm) throws Exception {
 
        return sqlSession.selectList(NAMESPACE + ".getBoardFilesList", boardFilesForm);
    }
	
	/** 게시판 - 등록 */
	public int insertBoardThreeFiles(BoardThreeFilesForm boardThreeFilesForm) throws Exception {
		return sqlSession.insert(NAMESPACE + ".insertBoardThreeFiles", boardThreeFilesForm);
	}
	
	/** 게시판 - 첨부파일 등록 */
    public int insertBoardFiles(BoardFilesForm boardFilesForm) throws Exception {
        return sqlSession.insert(NAMESPACE + ".insertBoardFiles", boardFilesForm);
    }
	
    /** 게시판 - 삭제 */
	public int deleteBoardThreeFiles(BoardThreeFilesForm boardThreeFilesForm) throws Exception {

		return sqlSession.delete(NAMESPACE + ".deleteBoardThreeFiles", boardThreeFilesForm);
	}
	
	/** 게시판 - 수정 */
	public int updateBoardThreeFiles(BoardThreeFilesForm boardThreeFilesForm) throws Exception {
        
        return sqlSession.update(NAMESPACE + ".updateBoardThreeFiles", boardThreeFilesForm);
    }

	/** 첨부파일 - 삭제  */
    public int deleteBoardFiles(BoardFilesForm boardFilesForm) throws Exception {
        
        return sqlSession.delete(NAMESPACE + ".deleteBoardFiles", boardFilesForm);
    }
    
    
    /* ========================================================================================================
	 * 다중 첨부파일 관련
	 * ========================================================================================================*/
	
	/** 게시판 - 목록 수 */
	public int getBoardCntComment(BoardCommentForm boardCommentForm) throws Exception {

		return sqlSession.selectOne(NAMESPACE + ".getBoardCntComment", boardCommentForm);
	}
 
	/** 게시판 - 목록 조회 */
    public List<BoardCommentDto> getBoardListComment(BoardCommentForm boardCommentForm) throws Exception {
 
        return sqlSession.selectList(NAMESPACE + ".getBoardListComment", boardCommentForm);
    }
    
    /** 게시판 - 조회 수 수정 */
	public int updateBoardHitsComment(BoardCommentForm boardCommentForm) throws Exception {

		return sqlSession.update(NAMESPACE + ".updateBoardHitsComment", boardCommentForm);
	}
	
	/** 게시판 - 상세 조회 */
	public BoardCommentDto getBoardDetailComment(BoardCommentForm boardCommentForm) throws Exception {

		return sqlSession.selectOne(NAMESPACE + ".getBoardDetailComment", boardCommentForm);
	}
	
	/** 게시판 - 첨부파일 조회 */
    public List<BoardCommentFileDto> getBoardCommentFileList(BoardCommentFileForm boardCommentFileForm) throws Exception {
 
        return sqlSession.selectList(NAMESPACE + ".getBoardCommentFileList", boardCommentFileForm);
    }
	
	/** 게시판 - 등록 */
	public int insertBoardComment(BoardCommentForm boardCommentForm) throws Exception {
		return sqlSession.insert(NAMESPACE + ".insertBoardComment", boardCommentForm);
	}
	
	/** 게시판 - 첨부파일 등록 */
    public int insertBoardCommentFile(BoardCommentFileForm boardCommentFileForm) throws Exception {
        return sqlSession.insert(NAMESPACE + ".insertBoardCommentFile", boardCommentFileForm);
    }
	
    /** 게시판 - 삭제 */
	public int deleteBoardComment(BoardCommentForm boardCommentForm) throws Exception {

		return sqlSession.delete(NAMESPACE + ".deleteBoardComment", boardCommentForm);
	}
	
	/** 게시판 - 수정 */
	public int updateBoardComment(BoardCommentForm boardCommentForm) throws Exception {
        
        return sqlSession.update(NAMESPACE + ".updateBoardComment", boardCommentForm);
    }

	/** 첨부파일 - 삭제  */
    public int deleteBoardCommentFile(BoardCommentFileForm boardCommentFileForm) throws Exception {
        
        return sqlSession.delete(NAMESPACE + ".deleteBoardCommentFile", boardCommentFileForm);
    }
    
    /** 게시판 - 댓글 조회 */
	public List<BoardCommentComDto> getBoardListCommentCom(BoardCommentComForm boardCommentComForm) throws Exception {
		
		logger.debug("=========================== 여기까지 넘어와? =================================");
		
		logger.debug("===========================" + boardCommentComForm.getBb_Comment_Seq() + "=================================");
		
		logger.debug("=========================== 여기까지 넘어와? =================================");
		
		return sqlSession.selectList(NAMESPACE + ".getBoardListCommentCom", boardCommentComForm);
	}
	
	/** 게시판 - 댓글등록 */ 
	public int insertBoardCommentCom(BoardCommentComForm boardCommentComForm) throws Exception {
        
        return sqlSession.insert(NAMESPACE + ".insertBoardCommentCom", boardCommentComForm);
    }
	
	/** 게시판 - 댓글삭제  */
    public int deleteBoardCommentCom(BoardCommentComForm boardCommentComForm) throws Exception {
        
        return sqlSession.delete(NAMESPACE + ".deleteBoardCommentCom", boardCommentComForm);
    }
    
}
