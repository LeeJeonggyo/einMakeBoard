<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 상세</title>
<%    
    String bbFileSeq = request.getParameter("bbFileSeq");        
%>
 
<c:set var="bbFileSeq" value="<%=bbFileSeq%>"/> <!-- 게시글 번호 -->
 
<!-- 공통 CSS -->
<link rel="stylesheet" type="text/css" href="/css/common/common.css"/>
 
<!-- 공통 JavaScript -->
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript">
    
    $(document).ready(function(){ 
    	getBoardDetailThreeFiles();      
    });
    
    /** 게시판 - 목록 페이지 이동 */
    function goBoardListThreeFiles(currentPageNo){      
    	
    	var currentPageNo = $("#current_page_no").val();
        //alert(currentPageNo);
        
        location.href = "/board/boardListThreeFiles?currentPageNo="+ currentPageNo;
    }
    
    /** 게시판 - 수정 페이지 이동 */
    function goBoardWriteThreeFiles(bbFileSeq, currentPageNo){
        
        var bbFileSeq = $("#bb_File_Seq").val();
        var currentPageNo = $("#current_page_no").val();
        //alert(currentPageNo);
        
        location.href = "/board/boardWriteThreeFiles?bbFileSeq="+ bbFileSeq + "&currentPageNo=" + currentPageNo;
    }
    
    /** 게시판 - 첨부파일 다운로드 */
    function fileDownload(fileNameKey, fileName, filePath){
            
        location.href = "/board/fileDownload?fileNameKey="+fileNameKey+"&fileName="+fileName+"&filePath="+filePath;
    }
    
    /** 게시판 - 상세 조회  */
    function getBoardDetailThreeFiles(bbFileSeq){
        
    	var bbFileSeq = $("#bb_File_Seq").val();
        var currentPageNo = <%= request.getParameter("currentPageNo") %>
        $("#current_page_no").val(currentPageNo);
        //alert(currentPageNo);
 
        if(bbFileSeq != ""){
            
            $.ajax({    
                
                url        : "/board/getBoardDetailThreeFiles",
                data    : $("#boardForm4").serialize(),
                dataType: "JSON",
                cache   : false,
                async   : true,
                type    : "POST",    
                success : function(obj) {
                    getBoardDetailThreeFilesCallback(obj);                
                },           
                error     : function(xhr, status, error) {}
                
             });
            
        } else {
            alert("오류가 발생했습니다.\n관리자에게 문의하세요.");
        }    
    }
    
    /** 게시판 - 상세 조회  콜백 함수 */
    function getBoardDetailThreeFilesCallback(obj){
        
        var str = "";
        
        if(obj != null){                                
                            
        	var bbFileSeq         = obj.bb_File_Seq ; 
            var bbFileWriter     = obj.bb_File_Writer ; 
            var bbFileSubject     = obj.bb_File_Subject ; 
            var bbFileContent     = obj.bb_File_Content ; 
            var bbFileHits         = obj.bb_File_Hits ;
            var bbFileInsDate         = obj.bb_File_Ins_Date ;
            var files            = obj.files;        
            var filesLen        = files.length;
            
            $("#bb_File_Subject").text(bbFileSubject);            
            $("#bb_File_Content").text(bbFileContent);
            $("#bb_File_Writer").text(bbFileWriter);
            
            if(filesLen > 0){
                
                for(var a=0; a<filesLen; a++){
                    
                	var bbFileSeq    = files[a].bb_File_Seq;
                    var fileNo         = files[a].file_no;
                    var fileNameKey = files[a].file_name_key;
                    var fileName     = files[a].file_name;
                    var filePath     = files[a].file_path;
                    var fileSize     = files[a].file_size;
                    var insDate     = files[a].ins_date;
                    
                    console.log("fileName : " + fileName);
                    
                    str += "<tr>"
                    str += "<th>첨부파일</th>";
                    str += "<td><a href='/board/fileDownload?fileNameKey="+encodeURI(fileNameKey)+"&fileName="+encodeURI(fileName)+"&filePath="+encodeURI(filePath)+"'>" + fileName + "</a></td>";
                    str += "</tr>"
                }    
            }    
        } else {            
            alert("등록된 글이 존재하지 않습니다.");
            return;
        }    
        
        $("#tr").html(str);
    }
    
    /** 게시판 - 삭제  */
    function deleteBoardThreeFiles(){
 
        var bbFileSeq = $("#bb_File_Seq").val();
        
        var yn = confirm("게시글을 삭제하시겠습니까?");        
        if(yn){
            
            $.ajax({    
                
                url        : "/board/deleteBoardThreeFiles",
                data    : $("#boardForm4").serialize(),
                dataType: "JSON",
                cache   : false,
                async   : true,
                type    : "POST",    
                success : function(obj) {
                    deleteBoardThreeFilesCallback(obj);                
                },           
                error     : function(xhr, status, error) {}
                
             });
        }        
    }
    
    /** 게시판 - 삭제 콜백 함수 */
    function deleteBoardThreeFilesCallback(obj){
    
        if(obj != null){        
            
            var result = obj.result;
            
            if(result == "SUCCESS"){                
                alert("게시글 삭제를 성공하였습니다.");                
                goBoardListThreeFiles();                
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
							<th onclick="location.href='/board/boardListAFile'" style="cursor:Pointer;">첨부파일 게시판</th>
							<th onclick="javascript:goBoardListThreeFiles();" style="cursor:Pointer; background-color:Black; color:White">다중 첨부파일 게시판</th>
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
                            <td id="bb_File_Subject"></td>
                        </tr>
                         <tr>
                            <th>작성자</th>
                            <td id="bb_File_Writer"></td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td id="bb_File_Content" style="white-space:pre-wrap; text-align:left;"></td>
                        </tr>                      
                    </tbody>
                    <tbody id="tr">
                                             
                    </tbody>
                </table>   
                <input type="hidden" id="current_page_no"        name="current_page_no"    value="${currentPageNo}"/> <!-- 게시글 페이지 -->     
                <input type="hidden" id="bb_File_Seq"        name="bb_File_Seq"    value="${bbFileSeq}"/> <!-- 게시글 번호 -->
                <input type="hidden" id="search_type"    name="search_type"     value="S"/> <!-- 조회 타입 - 상세(S)/수정(U) -->
            </form>
            <div class="btn_right mt15">                
                <button type="button" class="btn black mr5" onclick="javascript:goBoardWriteThreeFiles();">수정</button>
                <button type="button" class="btn black" onclick="javascript:deleteBoardThreeFiles();">삭제</button>
                <button type="button" class="btn black mr5" onclick="javascript:goBoardListThreeFiles();">목록</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>