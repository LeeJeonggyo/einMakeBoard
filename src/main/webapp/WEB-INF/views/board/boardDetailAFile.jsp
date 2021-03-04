<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 상세</title>
<%    
    String bbASeq = request.getParameter("bbASeq");        
%>
 
<c:set var="bbASeq" value="<%=bbASeq%>"/> <!-- 게시글 번호 -->
 
<!-- 공통 CSS -->
<link rel="stylesheet" type="text/css" href="/css/common/common.css"/>
 
<!-- 공통 JavaScript -->
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript">
    
    $(document).ready(function(){ 
    	getBoardDetailAFile();      
    });
    
    /** 게시판 - 목록 페이지 이동 */
    function goBoardListAFile(currentPageNo){      
    	
    	var currentPageNo = $("#current_page_no").val();
        //alert(currentPageNo);
        
        location.href = "/board/boardListAFile?currentPageNo="+ currentPageNo;
    }
    
    /** 게시판 - 수정 페이지 이동 */
    function goBoardWriteAFile(bbASeq, currentPageNo){
        
        var bbASeq = $("#bb_A_Seq").val();
        var currentPageNo = $("#current_page_no").val();
        //alert(currentPageNo);
        
        location.href = "/board/boardWriteAFile?bbASeq="+ bbASeq + "&currentPageNo=" + currentPageNo;
    }
    
    /** 게시판 - 첨부파일 다운로드 */
    function fileDownload(fileNameKey, fileName, filePath){
            
        location.href = "/board/fileDownload?fileNameKey="+fileNameKey+"&fileName="+fileName+"&filePath="+filePath;
    }
    
    /** 게시판 - 상세 조회  */
    function getBoardDetailAFile(bbASeq){
        
    	var bbASeq = $("#bb_A_Seq").val();
        var currentPageNo = <%= request.getParameter("currentPageNo") %>
        $("#current_page_no").val(currentPageNo);
        //alert(currentPageNo);
 
        if(bbASeq != ""){
            
            $.ajax({    
                
                url        : "/board/getBoardDetailAFile",
                data    : $("#boardForm4").serialize(),
                dataType: "JSON",
                cache   : false,
                async   : true,
                type    : "POST",    
                success : function(obj) {
                    getBoardDetailAFileCallback(obj);                
                },           
                error     : function(xhr, status, error) {}
                
             });
            
        } else {
            alert("오류가 발생했습니다.\n관리자에게 문의하세요.");
        }    
    }
    
    /** 게시판 - 상세 조회  콜백 함수 */
    function getBoardDetailAFileCallback(obj){
        
        var str = "";
        
        if(obj != null){                                
                            
        	var bbASeq         = obj.bb_A_Seq ; 
            var bbAWriter     = obj.bb_A_Writer ; 
            var bbASubject     = obj.bb_A_Subject ; 
            var bbAContent     = obj.bb_A_Content ; 
            var bbAHits         = obj.bb_A_Hits ;
            var bbAInsDate         = obj.bb_A_Ins_Date ;
            var files            = obj.files;        
            var filesLen        = files.length;
            
            $("#bb_A_Subject").text(bbASubject);            
            $("#bb_A_Content").text(bbAContent);
            $("#bb_A_Writer").text(bbAWriter);
            
            if(filesLen > 0){
                
                for(var a=0; a<filesLen; a++){
                    
                    var bbASeq    = files[a].bb_A_Seq;
                    var fileNo         = files[a].file_no;
                    var fileNameKey = files[a].file_name_key;
                    var fileName     = files[a].file_name;
                    var filePath     = files[a].file_path;
                    var fileSize     = files[a].file_size;
                    var insDate     = files[a].ins_date;
                    
                    console.log("fileName : " + fileName);
                    
                    str += "<th>첨부파일</th>";
                    str += "<td><a href='/board/fileDownload?fileNameKey="+encodeURI(fileNameKey)+"&fileName="+encodeURI(fileName)+"&filePath="+encodeURI(filePath)+"'>" + fileName + "</a></td>";
                }    
            }    
        } else {            
            alert("등록된 글이 존재하지 않습니다.");
            return;
        }    
        
        $("#tr").html(str);
    }
    
    /** 게시판 - 삭제  */
    function deleteBoardAFile(){
 
        var bbASeq = $("#bb_A_Seq").val();
        
        var yn = confirm("게시글을 삭제하시겠습니까?");        
        if(yn){
            
            $.ajax({    
                
                url        : "/board/deleteBoardAFile",
                data    : $("#boardForm4").serialize(),
                dataType: "JSON",
                cache   : false,
                async   : true,
                type    : "POST",    
                success : function(obj) {
                    deleteBoardAFileCallback(obj);                
                },           
                error     : function(xhr, status, error) {}
                
             });
        }        
    }
    
    /** 게시판 - 삭제 콜백 함수 */
    function deleteBoardAFileCallback(obj){
    
        if(obj != null){        
            
            var result = obj.result;
            
            if(result == "SUCCESS"){                
                alert("게시글 삭제를 성공하였습니다.");                
                goBoardListAFile();                
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
                            <th onclick="location.href='/board/boardList'" style="cursor:Pointer;">일반 게시판</th>
                            <th onclick="javascript:goBoardListAFile();" style="cursor:Pointer; background-color:Black; color:White">첨부파일 게시판</th>
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
                            <td id="bb_A_Subject"></td>
                        </tr>
                         <tr>
                            <th>작성자</th>
                            <td id="bb_A_Writer"></td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td id="bb_A_Content" style="white-space:pre-wrap; text-align:left;"></td>
                        </tr>
                        <tr id="tr">
                        </tr>                       
                    </tbody>
                </table>   
                <input type="hidden" id="current_page_no"        name="current_page_no"    value="${currentPageNo}"/> <!-- 게시글 페이지 -->     
                <input type="hidden" id="bb_A_Seq"        name="bb_A_Seq"    value="${bbASeq}"/> <!-- 게시글 번호 -->
                <input type="hidden" id="search_type"    name="search_type"     value="S"/> <!-- 조회 타입 - 상세(S)/수정(U) -->
            </form>
            <div class="btn_right mt15">                
                <button type="button" class="btn black mr5" onclick="javascript:goBoardWriteAFile();">수정</button>
                <button type="button" class="btn black" onclick="javascript:deleteBoardAFile();">삭제</button>
                <button type="button" class="btn black mr5" onclick="javascript:goBoardListAFile();">목록</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>