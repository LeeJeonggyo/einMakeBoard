<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 상세</title>
<%    
    String bbSeq = request.getParameter("bbSeq");        
%>
 
<c:set var="bbSeq" value="<%=bbSeq%>"/> <!-- 게시글 번호 -->
 
<!-- 공통 CSS -->
<link rel="stylesheet" type="text/css" href="/css/common/common.css"/>
 
<!-- 공통 JavaScript -->
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript">
    
    $(document).ready(function(){ 
    	getBoardDetail();      
    });
    
    /** 게시판 - 목록 페이지 이동 */
    function goBoardList(currentPageNo){   
    	var bbSeq = $("#bb_Seq").val();
        var currentPageNo = $("#current_page_no").val();
        //alert(currentPageNo);
        location.href = "/board/boardList?currentPageNo="+ currentPageNo;
    }
    
    /** 게시판 - 수정 페이지 이동 */
    function goBoardWrite(bbSeq, currentPageNo){
        
        var bbSeq = $("#bb_Seq").val();
        var currentPageNo = $("#current_page_no").val();
        //alert(currentPageNo);
        
        location.href = "/board/boardWrite?bbSeq="+ bbSeq + "&currentPageNo=" + currentPageNo;
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
                data    : $("#boardForm4").serialize(),
                dataType: "JSON",
                cache   : false,
                async   : true,
                type    : "POST",    
                success : function(obj) {
                    getBoardDetailCallback(obj);                
                },           
                error     : function(xhr, status, error) {}
                
             });
            
        } else {
            alert("오류가 발생했습니다.\n관리자에게 문의하세요.");
        }    
    }
    
    /** 게시판 - 상세 조회  콜백 함수 */
    function getBoardDetailCallback(obj){
        
        if(obj != null){                                
                            
        	var bbSeq         = obj.bb_Seq ; 
            var bbWriter     = obj.bb_Writer ; 
            var bbSubject     = obj.bb_Subject ; 
            var bbContent     = obj.bb_Content ; 
            var bbHits         = obj.bb_Hits ;
            var bbInsDate         = obj.bb_Ins_Date ;
            
            $("#bb_Subject").text(bbSubject);            
            $("#bb_Content").text(bbContent);
            $("#bb_Writer").text(bbWriter);
            
            
            
        } else {            
            alert("등록된 글이 존재하지 않습니다.");
            return;
        }        
    }
    
    /** 게시판 - 삭제  */
    function deleteBoard(){
 
        var bbSeq = $("#bb_Seq").val();
        
        var yn = confirm("게시글을 삭제하시겠습니까?");        
        if(yn){
            
            $.ajax({    
                
                url        : "/board/deleteBoard",
                data    : $("#boardForm4").serialize(),
                dataType: "JSON",
                cache   : false,
                async   : true,
                type    : "POST",    
                success : function(obj) {
                    deleteBoardCallback(obj);                
                },           
                error     : function(xhr, status, error) {}
                
             });
        }        
    }
    
    /** 게시판 - 삭제 콜백 함수 */
    function deleteBoardCallback(obj){
    
        if(obj != null){        
            
            var result = obj.result;
            
            if(result == "SUCCESS"){                
                alert("게시글 삭제를 성공하였습니다.");                
                goBoardList();                
            } else {                
                alert("게시글 삭제를 실패하였습니다.");    
                return;
            }
        }
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
                    <tbody >
                    
                    </tbody>    
                </table>
            </form>      	
              
            <form id="boardForm4" name="boardForm4">        
                <table width="100%" class="table01">
                    <colgroup>
                        <col width="15%">
                        <col width="35%">
                        <col width="15%">
                        <col width="35%">
                    </colgroup>
                    <tbody>
                    	<tr>
                            <th>제목</th>
                            <td id="bb_Subject"></td>
                        </tr>
                         <tr>
                            <th>작성자</th>
                            <td id="bb_Writer"></td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td id="bb_Content" style="white-space:pre-wrap; text-align:left; "><span></span></td>
                        </tr>
                       
                    </tbody>
                </table>        
                <input type="hidden" id="current_page_no"        name="current_page_no"    value="${currentPageNo}"/> <!-- 게시글 페이지 -->
                <input type="hidden" id="bb_Seq"        name="bb_Seq"    value="${bbSeq}"/> <!-- 게시글 번호 -->
                <input type="hidden" id="search_type"    name="search_type"     value="S"/> <!-- 조회 타입 - 상세(S)/수정(U) -->
            </form>
            <div class="btn_right mt15">                
                <button type="button" class="btn black mr5" onclick="javascript:goBoardWrite();">수정</button>
                <button type="button" class="btn black" onclick="javascript:deleteBoard();">삭제</button>
                <button type="button" class="btn black mr5" onclick="javascript:goBoardList();">목록</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>