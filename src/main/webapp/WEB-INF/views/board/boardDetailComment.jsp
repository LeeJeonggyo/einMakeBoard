<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 상세</title>
<%    
    String bbCommentSeq = request.getParameter("bbCommentSeq");        
%>
 
<c:set var="bbCommentSeq" value="<%=bbCommentSeq%>"/> <!-- 게시글 번호 -->
 
<!-- 공통 CSS -->
<link rel="stylesheet" type="text/css" href="/css/common/common.css"/>
 
<!-- 공통 JavaScript -->
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript" src="/js/common/jquery.form.js"></script>
<script type="text/javascript">
    
    $(document).ready(function(){ 
    	getBoardDetailComment();     
    	getBoardListCommentCom();
    });
    
    /** 게시판 - 목록 페이지 이동 */
    function goBoardListComment(currentPageNo){      
    	
    	var currentPageNo = $("#current_page_no").val();
        //alert(currentPageNo);
        
        location.href = "/board/boardListComment?currentPageNo="+ currentPageNo;
    }
    
    /** 게시판 - 상세 페이지 이동 */
	function goBoardDetailComment(bbCommentSeq, currentPageNo){  
	    location.href = "/board/boardDetailComment?bbCommentSeq="+ bbCommentSeq + "&currentPageNo=" + currentPageNo;
	}
    
    /** 게시판 - 수정 페이지 이동 */
    function goBoardWriteComment(bbCommentSeq, currentPageNo){
        
        var bbCommentSeq = $("#bb_Comment_Seq").val();
        var currentPageNo = $("#current_page_no").val();
        //alert(currentPageNo);
        
        location.href = "/board/boardWriteComment?bbCommentSeq="+ bbCommentSeq + "&currentPageNo=" + currentPageNo;
    }
    
    /** 게시판 - 첨부파일 다운로드 */
    function fileDownload(fileNameKey, fileName, filePath){
            
        location.href = "/board/fileDownload?fileNameKey="+fileNameKey+"&fileName="+fileName+"&filePath="+filePath;
    }
    
    /** 게시판 - 상세 조회  */
    function getBoardDetailComment(bbCommentSeq){
        
    	var bbCommentSeq = $("#bb_Comment_Seq").val();
        var currentPageNo = <%= request.getParameter("currentPageNo") %>
        $("#current_page_no").val(currentPageNo);
        //alert(currentPageNo);
 
        if(bbCommentSeq != ""){
            
            $.ajax({    
                
                url        : "/board/getBoardDetailComment",
                data    : $("#boardForm4").serialize(),
                dataType: "JSON",
                cache   : false,
                async   : true,
                type    : "POST",    
                success : function(obj) {
                    getBoardDetailCommentCallback(obj);                
                },           
                error     : function(xhr, status, error) {}
                
             });
            
        } else {
            alert("오류가 발생했습니다.\n관리자에게 문의하세요.");
        }    
    }
    
    /** 게시판 - 상세 조회  콜백 함수 */
    function getBoardDetailCommentCallback(obj){
        
        var str = "";
        
        if(obj != null){                                
                            
        	var bbCommentSeq         = obj.bb_Comment_Seq ; 
            var bbCommentWriter     = obj.bb_Comment_Writer ; 
            var bbCommentSubject     = obj.bb_Comment_Subject ; 
            var bbCommentContent     = obj.bb_Comment_Content ; 
            var bbCommentHits         = obj.bb_Comment_Hits ;
            var bbCommentInsDate         = obj.bb_Comment_Ins_Date ;
            var files            = obj.files;        
            var filesLen        = files.length;
            
            $("#bb_Comment_Subject").text(bbCommentSubject);            
            $("#bb_Comment_Content").text(bbCommentContent);
            $("#bb_Comment_Writer").text(bbCommentWriter);
            
            if(filesLen > 0){
                
                for(var a=0; a<filesLen; a++){
                    
                	var bbCommentSeq    = files[a].bb_Comment_Seq;
                    var fileNo         = files[a].file_no;
                    var fileNameKey = files[a].file_name_key;
                    var fileName     = files[a].file_name;
                    var filePath     = files[a].file_path;
                    var fileSize     = files[a].file_size;
                    var insDate     = files[a].ins_date;
                    
                    console.log("fileName : " + fileName);
                    console.log("fileName : " + filePath);
                    
                    alert(filePath);
                    
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
    
    
    /** 게시판 - 상세 조회  */
    function getBoardListCommentCom(bbCommentSeq){
        
    	var bbCommentSeq = $("#bb_Comment_Seq").val();
 
        if(bbCommentSeq != ""){
            
            $.ajax({    
                
                url        : "/board/getBoardListCommentCom",
                data    : $("#boardForm5").serialize(),
                dataType: "JSON",
                cache   : false,
                async   : true,
                type    : "POST",    
                success : function(obj) {
                    getBoardListCommentComCallback(obj);                
                },           
                error     : function(xhr, status, error) {}
                
             });
            
        } else {
            alert("오류가 발생했습니다.\n관리자에게 문의하세요.");
        }    
    }
    
    /** 게시판 - 상세 조회  콜백 함수 */
    function getBoardListCommentComCallback(obj){
        
		var listCom = obj
		var listComLen = obj.length;
		
		console.log(listCom);
        console.log(listComLen);
		
        var str = "";
        
        if(listComLen >  0){
            
            for(var a=0; a<listComLen; a++){
                
                var comNo        = listCom[a].com_No; 
                var comContent         = listCom[a].com_Content; 
                var insDate     = listCom[a].ins_Date; 
                
                str += "<tr>";
                str += "<td style='white-space:pre-wrap; text-align:left; border-right:hidden; word-break:break-all;'>"+ comContent +"</td>";
                str += '<td><span style="margin-left:10px;">[ '+ insDate +' ]</span>';  
                str += '<input type="button" id="del_check_'+a+'"value="X" class="deleteButton" style="margin-left:26px; cursor:Pointer; background-color:RED; color:White"/> </td>';
                str += '<input type="hidden"  id="comNo_'+a+'" name="comNoZip" value="'+ comNo +'"/>'
                str += '<input type="hidden"  id="comNoAll_'+a+'" name="comNoAllZip" value="'+ comNo +'"/>'
                str += "</tr>"; 
            } 
            
        } else {
            
            str += "<tr colspan='4'>";
            str += "<td>등록된 댓글이 존재하지 않습니다.</td>";
            str += "<tr>";
        }
        
        $("#tbody").html(str);
        page_events();
    }
    
    function page_events(){
     	
     	$('#tbody').find('.deleteButton').on('click',function(){
     		console.log('xxxxxxxxxxxxxxxxxx:'+$(this).attr('id'));
     		//console.log('xxxxxxxxxxxxxxxxxx:'+$('#'+$(this).attr('id').replace('del_check_','span_')).text(""));
     		console.log('xxxxxxxxxxxxxxxxxx:'+$(this).val());
     		console.log('xxxxxxxxxxxxxxxxxx:'+$('#'+$(this).attr('id').replace('del_check_','comNo_')).val());
     		
     		var yn = confirm("댓글을 삭제하시겠습니까?");
     		if(yn){
     			
     			$('#'+$(this).attr('id').replace('del_check_','comNo_')).val("0");
         		console.log('xxxxxxxxxxxxxxxxxx:'+$('#'+$(this).attr('id').replace('del_check_','comNo_')).val());
         		
     			deleteBoardCommentCom();
     		}
     		console.log('xxxxxxxxxxxxxxxxxx:'+$('#'+$(this).attr('id').replace('del_check_','comNo_')).val());
     		
     	});
    }
    
    
    
    
    
    /** 게시판 - 댓글작성 */
    function insertBoardCommentCom(){
    	var bbCommentSeq = $("#bb_Comment_Seq").val();
   		var currentPageNo = $("#current_page_no").val();
       	var bbCommentContent = $("#bb_Comment_Content2").val();
        
        if (spaceCheck(bbCommentContent) == 0){            
            alert("댓글 내용을 입력해주세요.");
            $("#bb_Comment_Content2").val('');
            $("#bb_Comment_Content2").focus();
            return;
        }
        
        var yn = confirm("댓글을 등록하시겠습니까?");        
        if(yn){
            
            $.ajax({    
                   url        : "/board/insertBoardCommentCom",
                   data    : $("#boardForm6").serialize(),
                   dataType: "JSON",
                   cache   : false,
                   async   : true,
                   type    : "POST",    
                   success : function(obj) {
                       insertBoardCommentComCallback(obj, bbCommentSeq, currentPageNo);                
                   },           
                   error     : function(xhr, status, error) {}
                   
               });
        }
    }
    
    /** 게시판 - 댓글작성 콜백 함수 */
    function insertBoardCommentComCallback(obj, bbCommentSeq, currentPageNo){
    
        if(obj != null){        
            
            var result = obj.result;
            
            if(result == "SUCCESS"){                
                alert("게시글 등록을 성공하였습니다.");                
                goBoardDetailComment(bbCommentSeq, currentPageNo);                 
            } else {                
                alert("게시글 등록을 실패하였습니다.");    
                return;
            }
        }
    }
    
    
    
    
    
    
    /** 게시판 - 댓글삭제 */  
    function deleteBoardCommentCom(){
    	
    	var bbCommentSeq = $("#bb_Comment_Seq").val();
    	var currentPageNo = $("#current_page_no").val();
        
    	// call - ajax
    	$("#boardForm5").ajaxForm({
            
            url        : "/board/deleteBoardCommentCom",
            enctype    : "multipart/form-data",
            cache   : false,
            async   : true,
            type    : "POST",                         
            success : function(obj) {
            	deleteBoardCommentComCallback(obj, bbCommentSeq, currentPageNo);                
            },           
            error     : function(xhr, status, error) {}
            
        }).submit();  
    }
    
    /** 게시판 - eotrmf삭제 콜백 함수 */
    function deleteBoardCommentComCallback(obj, bbCommentSeq, currentPageNo){
    
        if(obj != null){        
            
            var result = obj.result;
            
            if(result == "SUCCESS"){                
                alert("게시글 삭제를 성공하였습니다.");                
                goBoardDetailComment(bbCommentSeq, currentPageNo);                
            } else {                
                alert("게시글 삭제를 실패하였습니다.");    
                return;
            }
        }
    }
        
    /** 게시판 - 삭제  */
    function deleteBoardComment(){
 
        var bbCommentSeq = $("#bb_Comment_Seq").val();
        
        var yn = confirm("게시글을 삭제하시겠습니까?");        
        if(yn){
            
            $.ajax({    
                
                url        : "/board/deleteBoardComment",
                data    : $("#boardForm4").serialize(),
                dataType: "JSON",
                cache   : false,
                async   : true,
                type    : "POST",    
                success : function(obj) {
                    deleteBoardCommentCallback(obj);                
                },           
                error     : function(xhr, status, error) {}
                
             });
        }        
    }
    
    /** 게시판 - 삭제 콜백 함수 */
    function deleteBoardCommentCallback(obj){
    
        if(obj != null){        
            
            var result = obj.result;
            
            if(result == "SUCCESS"){                
                alert("게시글 삭제를 성공하였습니다.");                
                goBoardListComment();                
            } else {                
                alert("게시글 삭제를 실패하였습니다.");    
                return;
            }
        }
    }
    
	//입력받은 문자에 스페이스바와 엔터키만 입력 된 경우 확인
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
<div style="position:relative; width:100%; height:100%; overflow-y:auto;">
    <div style="position:relative; top:10px;right:0;bottom:38px;left:0; ">
        <div style="width:680px; margin:0 auto; padding:10px 0">  
        	<form id="boardForm1" name="boardForm1">
                <table width="100%" class="table01">
                	<colgroup>
                        <col width="25%">
                        <col width="25%">
                        <col width="25%">
                        <col width="25%">
                    </colgroup>
                    <thead>        
                        <tr>
                            <th onclick="location.href='/board/boardList'" style="cursor:Pointer;">일반 게시판</th>
							<th onclick="location.href='/board/boardListAFile'" style="cursor:Pointer;">첨부파일 게시판</th>
							<th onclick="location.href='/board/boardListThreeFiles'" style="cursor:Pointer;">다중 첨부파일 게시판</th>
							<th onclick="javascript:goBoardListComment();" style="cursor:Pointer; background-color:Black; color:White">댓글 게시판</th>                             
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
                            <td id="bb_Comment_Subject"></td>
                        </tr>
                         <tr>
                            <th>작성자</th>
                            <td id="bb_Comment_Writer"></td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td id="bb_Comment_Content" style="white-space:pre-wrap; text-align:left;"></td>
                        </tr>                      
                    </tbody>
                    <tbody id="tr">
                                             
                    </tbody>
                </table>   
                <input type="hidden" id="current_page_no"        name="current_page_no"    value="${currentPageNo}"/> <!-- 게시글 페이지 -->     
                <input type="hidden" id="bb_Comment_Seq"        name="bb_Comment_Seq"    value="${bbCommentSeq}"/> <!-- 게시글 번호 -->
                <input type="hidden" id="search_type"    name="search_type"     value="S"/> <!-- 조회 타입 - 상세(S)/수정(U) -->
            </form>        
                
            <div class="btn_right mt15">                
                <button type="button" class="btn black mr5" onclick="javascript:goBoardWriteComment();">수정</button>
                <button type="button" class="btn black" onclick="javascript:deleteBoardComment();">삭제</button>
                <button type="button" class="btn black mr5" onclick="javascript:goBoardListComment();">목록</button>
            </div>
            <div style="height:150;"><br/><br/><br/><br/> </div>
            
        </div>
        
        <form id="boardForm5" name="boardForm5" style=" width:680px; margin:0 auto; padding:10px 0 ">
		<table width="100%" class="table01">
			<colgroup>
				<col width="70%">
				<col width="30%">
			</colgroup>
			<tbody id="tbody">
			       	                    
			</tbody>
		       
		</table>  
		<input type="hidden" id="bb_Comment_Seq"        name="bb_Comment_Seq"    value="${bbCommentSeq}"/> <!-- 게시글 번호 -->
	</form>
	
	<form id="boardForm6" name="boardForm6" style="width:680px; margin:0 auto; padding:10px 0 ">
		<table width="100%" class="table01" style="border-right: none; border-left: none; border-top: none; border-bottom: none;">
			<colgroup>
				<col width="80%">
				<col width="20%">
			</colgroup>
			<tbody style="border-right: none; border-left: none; border-top: none; border-bottom: none;">
				<tr style="border-right: none; border-left: none; border-top: none; border-bottom: none;">
					<td  style="border-right: none; border-left: none; border-top: none; border-bottom: none;">
						<textarea id="bb_Comment_Content2" name="com_Content" cols="50" rows="5" style="white-space:pre-wrap; resize: none; width:500px;height:95px;margin:10px 0"></textarea>
						<input type="button" value="댓글저장" style="height:100px; width:80px" onclick='javascript:insertBoardCommentCom();'/> 
					</td>
				</tr>
			</tbody>
		       
		</table>  
		<input type="hidden" id="bb_Comment_Seq"        name="bb_Comment_Seq"    value="${bbCommentSeq}"/> <!-- 게시글 번호 -->
	</form>
    </div>
    
	
</div>

</body>
</html>