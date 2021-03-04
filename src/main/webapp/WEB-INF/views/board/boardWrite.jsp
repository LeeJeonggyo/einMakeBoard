<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 수정</title>
<%
	String bbSeq = request.getParameter("bbSeq");
	
	if(bbSeq == null || bbSeq.equals("")){
		bbSeq = "0";
	}

%>
 
<c:set var="bbSeq" value="<%=bbSeq%>"/> <!-- 게시글 번호 -->
 
<!-- 공통 CSS -->
<link rel="stylesheet" type="text/css" href="/css/common/common.css"/>
 
<!-- 공통 JavaScript -->
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript">

	//<td>${A}</td>
    
    $(document).ready(function(){   
    	var bbSeq = "<%=bbSeq%>";
		var currentPageNo = <%= request.getParameter("currentPageNo") %>
        $("#current_page_no").val(currentPageNo);
    	
    	if(bbSeq != "0"){
    		getBoardDetail();	
    	}        
    });
    
    /** 게시판 - 목록 페이지 이동 */
    function goBoardList(currentPageNo){   
        var currentPageNo = $("#current_page_no").val();
        //alert(currentPageNo);
        
        location.href = "/board/boardList?currentPageNo="+currentPageNo;
    }
    
    /** 게시판 - 상세 조회  */
    function getBoardDetail(bbSeq, currentPageNo){
        
        var bbSeq = $("#bb_Seq").val();
        var currentPageNo = <%= request.getParameter("currentPageNo") %>
        $("#current_page_no").val(currentPageNo);
        //alert(currentPageNo);

        if(bbSeq != ""){
            
            $.ajax({    
                
                url        : "/board/getBoardDetail",
                data    : $("#boardForm").serialize(),
                dataType: "JSON",
                cache   : false,
                async   : true,
                type    : "POST",    
                success : function(obj) {
                    getBoardDetailCallback(obj);                
                },           
                error     : function(xhr, status, error) {}
                
             });
            
        }  
    }
    
    /** 게시판 - 상세 조회  콜백 함수 */
    function getBoardDetailCallback(obj){
        
        var str = "";
        
        if(obj != null){                                
                            
        	var bbSeq         = obj.bb_Seq ; 
            var bbWriter     = obj.bb_Writer ; 
            var bbSubject     = obj.bb_Subject ; 
            var bbContent     = obj.bb_Content ; 
            var bbHits         = obj.bb_Hits ;
            var bbInsDate         = obj.bb_Ins_Date ;
                    
            $("#bb_Subject").val(bbSubject);    
            $("#bb_Writer").val(bbWriter);
            $("#bb_Content").val(bbContent);
            
        } else {            
            alert("등록된 글이 존재하지 않습니다.");
            return;
        }        
    }
    
    /*
    * 게시판 insert, update 분기
    */
    function boardDiv()
    {
    	var bbSeq = $("#bb_Seq").val();
    	
    	if(bbSeq == "0"){
    		insertBoard();
    	}else{
    		updateBoard();
    	}
    	
    }
    
    /** 게시판 - 작성  */
    function insertBoard(){
        var bbSubject = $("#bb_Subject").val();
        var bbWriter     = $("#bb_Writer").val();
        var bbContent = $("#bb_Content").val();
            
        if (spaceCheck(bbSubject) == 0){            
            alert("제목을 입력해주세요.");
            
            
            //onFocus="this.value=''; 
            //return true;
            //$("#bb_Subject").val(bbSubject);
            //bbSubject.replace(/^/gi,"");
            //document.boardForm.bb_Subject.value='';
            $("#bb_Subject").val('');
            $("#bb_Subject").focus();
            
            return;
        } 
        
        if (spaceCheck(bbWriter) == 0){            
            alert("작성자를 입력해주세요.");
            $("#bb_Writer").val('');
            $("#bb_Writer").focus();
            return;
        }
        
        if (spaceCheck(bbContent) == 0){            
            alert("내용을 입력해주세요.");
            $("#bb_Content").val('');
            $("#bb_Content").focus();
            return;
        }
        
        var yn = confirm("게시글을 등록하시겠습니까?");        
        if(yn){
            
            $.ajax({    
                url        : "/board/insertBoard",
                data    : $("#boardForm").serialize(),
                dataType: "JSON",
                cache   : false,
                async   : true,
                type    : "POST",    
                success : function(obj) {
                    insertBoardCallback(obj);                
                },           
                error     : function(xhr, status, error) {}
                
            });
        }
    }
    
    /** 게시판 - 작성 콜백 함수 */
    function insertBoardCallback(obj){
    
        if(obj != null){        
            
            var result = obj.result;
            
            if(result == "SUCCESS"){                
                alert("게시글 등록을 성공하였습니다.");                
                goBoardList();                 
            } else {                
                alert("게시글 등록을 실패하였습니다.");    
                return;
            }
        }
    }
    
    /** 게시판 - 수정  */
    function updateBoard(){
    	
    	var bbSeq = $("#bb_Seq").val();
    	var bbSubject    = $("#bb_Subject").val();
    	var bbWriter     = $("#bb_Writer").val();
        var bbContent     = $("#bb_Content").val();
        
        if (bbSeq == ""){  
        	insertBoard();            
            return;
        }
            
        if (spaceCheck(bbSubject) == 0){            
            alert("제목을 입력해주세요.");
            $("#bb_Subject").val('');
            $("#bb_Subject").focus();
            return;
        }
        
        if (spaceCheck(bbWriter) == 0){            
            alert("작성자를 입력해주세요.");
            $("#bb_Writer").val('');
            $("#bb_Writer").focus();
            return;
        }
        
        if (spaceCheck(bbContent) == 0){            
            alert("내용을 입력해주세요.");
            $("#bb_Content").val('');
            $("#bb_Content").focus();
            return;
        }
        
        var yn = confirm("게시글을 수정하시겠습니까?");        
        if(yn){
                
            $.ajax({    
                
                url        : "/board/updateBoard",
                data    : $("#boardForm").serialize(),
                dataType: "JSON",
                cache   : false,
                async   : true,
                type    : "POST",    
                success : function(obj) {
                    updateBoardCallback(obj);                
                },           
                error     : function(xhr, status, error) {}
                
            });
        }
    }
    
    /** 게시판 - 수정 콜백 함수 */
    function updateBoardCallback(obj){
    
        if(obj != null){        
            
            var result = obj.result;
            
            if(result == "SUCCESS"){                
                alert("게시글 수정을 성공하였습니다.");                
                goBoardList();                 
            } else {                 
                alert("게시글 수정을 실패하였습니다.");    
                return;
            }
        }
    }
    
    function spaceCheck(instring){
    	
    	var content = instring;
    	var contentLen = instring.length;
    	
    	if(contentLen > 0){
    		for (var a = 0; a < contentLen; a++){
    			if (content[a] != " " && content[a] != "\n"){
    				return 1;
    			} 
    		}	
    	}
    	return 0;
    }


</script>
</head>
<body>
<div id="wrap">
    <div id="container">
        <div class="inner">    
            <form id="boardForm1" name="boardForm1">
                <table width="100%" class="table01">
                    <colgroup>
                        <col width="25%" />
                        <col width="25%" />
                        <col width="25%" />
                        <col width="25%" />                        
                    </colgroup>
                    <thead>        
                        <tr>
                            <th onclick="javascript:goBoardList();" style="cursor:Pointer; background-color:Black; color:White">일반 게시판</th>
                            <th onclick="location.href='/board/boardListAFile'" style="cursor:Pointer;">첨부파일 게시판</th>
                            <th onclick="location.href='/board/boardListThreeFiles'" style="cursor:Pointer;">다중 첨부파일 게시판</th>
                            <th onclick="location.href='/board/boardListComment'" style="cursor:Pointer;">댓글 게시판</th>                            
                        </tr>
                    </thead>  
                </table>
            </form>
            <form id="boardForm" name="boardForm">    
                <table width="100%" class="table02">
                <caption><strong><span class="t_red">*</span> 표시는 필수입력 항목입니다.</strong></caption>
                    <colgroup>
                         <col width="20%">
                        <col width="*">
                    </colgroup>
                    <tbody>
                       <tr>                      		
                            <th>제목<span class="t_red">*</span></th>
                            <td><input id="bb_Subject" name="bb_Subject" value="" class="tbox01"/></td>
                        </tr>
                         <tr>
                            <th>작성자<span class="t_red">*</span></th>
                            <td><input id="bb_Writer" name="bb_Writer" value="" class="tbox01"/></td>
                        </tr>
                        <tr>
                            <th>내용<span class="t_red">*</span></th>
                            <td><textarea id="bb_Content" name="bb_Content" cols="50" rows="5" class="textarea01"></textarea></td>
                        </tr>
                        
                    </tbody>
                </table>    
                <input type="hidden" id="current_page_no"        name="current_page_no"    value="${currentPageNo}"/> <!-- 게시글 페이지 -->
                <input type="hidden" id="A" />
                <input type="hidden" id="bb_Seq" name = "bb_Seq" value="<%=bbSeq%>"/>
                <input type="hidden" id="search_type"    name="search_type"    value="U"/> <!-- 조회 타입 - 상세(S)/수정(U) -->
            </form>
            <div class="btn_right mt15">
            	<button type="button" class="btn black mr5" onclick="javascript:boardDiv();">저장</button>
                <button type="button" class="btn black" onclick="javascript:goBoardList();">목록</button>
                
            </div>
        </div>
    </div>
</div>
</body>
</html>