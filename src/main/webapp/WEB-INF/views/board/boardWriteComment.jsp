<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 수정</title>
<%
	String bbCommentSeq = request.getParameter("bbCommentSeq");
	
	if(bbCommentSeq == null || bbCommentSeq.equals("")){
		bbCommentSeq = "0";
	} 
%>
 
<c:set var="bbCommentSeq" value="<%=bbCommentSeq%>"/> <!-- 게시글 번호 -->
 
<!-- 공통 CSS -->
<link rel="stylesheet" type="text/css" href="/css/common/common.css"/>
 
<!-- 공통 JavaScript -->
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript" src="/js/common/jquery.form.js"></script>
<script type="text/javascript">
//-----------------------------------------------------------------------------
// 공백이나 널인지 확인
//@return : boolean
//-----------------------------------------------------------------------------
function isBlank(p) {
	if(p){
	    var str = p.replace(/&nbsp;/gi,'').replace(/\n/gi,'').trim();
	    for(var i = 0; i < str.length; i++) {
	        if ((str.charAt(i) != "\t") && (str.charAt(i) != "\n") && (str.charAt(i)!="\r")) {
	            return false;
	        }
	    }
	}
    return true;
}

function isNotBlank(p){
	return !isBlank(p);
}

function removeBlank(p) {
	if(p){
	    var str = p.replace(/&nbsp;/gi,' ').replace(/\n/gi,'').trim();
	    for(var i = 0; i < str.length; i++) {
	        if ((str.charAt(i) != "\t") && (str.charAt(i) != "\n") && (str.charAt(i)!="\r")) {
	            return false;
	        }
	    }
	}
    return '';
}

var filesNo;
$("#files_No").val(filesNo);
var filesNoAll;
$("#files_No_All").val(filesNoAll);
   
$(document).ready(function(){   
	
	var bbCommentSeq = "<%=bbCommentSeq%>";
	var currentPageNo = <%= request.getParameter("currentPageNo") %>
    $("#current_page_no").val(currentPageNo);
    
    //alert(bbCommentSeq);
 	
 	if(bbCommentSeq != "0"){
 		getBoardDetailComment();	
 	} else {
 		var str = "";    		
 		str += "<tr>"
		str += "<th scope='row'>첨부파일</th>"
        str += "<td><input type='file' id='files[0]' name='files[0]' value=''></td>"
        str += "</tr>"
        str += "<tr>"
        str += "<th scope='row'>첨부파일</th>"
        str += "<td><input type='file' id='files[1]' name='files[1]' value=''></td>"
        str += "</tr>"
        str += "<tr>"
        str += "<th scope='row'>첨부파일</th>"
        str += "<td><input type='file' id='files[2]' name='files[2]' value=''></td>"
		str += "</tr>"         
        
        $("#ins").html(str);
 	}
});
 
/** 게시판 - 목록 페이지 이동 */
function goBoardListComment(currentPageNo){  
	var currentPageNo = $("#current_page_no").val();
	location.href = "/board/boardListComment?currentPageNo="+currentPageNo;
}
 
/** 게시판 - 수정 페이지 이동 */
function goBoardWriteComment(bbCommentSeq, currentPageNo){
 
	var bbCommentSeq = $("#bb_Comment_Seq").val();
	var currentPageNo = $("#current_page_no").val();
 
	location.href = "/board/boardWriteComment?bbCommentSeq="+ bbCommentSeq + "&currentPageNo=" + currentPageNo;
}
 
 /** 게시판 - 상세 조회  */
function getBoardDetailComment(bbCommentSeq){
 
	var bbCommentSeq = $("#bb_Comment_Seq").val();
	var currentPageNo = <%= request.getParameter("currentPageNo") %>
	$("#current_page_no").val(currentPageNo);

	if(bbCommentSeq != ""){
	 
		$.ajax({    
		 
			url        : "/board/getBoardDetailComment",
			data    : $("#boardForm").serialize(),
			dataType: "JSON",
			cache   : false,
			async   : true,
			type    : "POST",    
			success : function(obj) {
				getBoardDetailCommentCallback(obj);                
			},           
			error     : function(xhr, status, error) {}
		 
		});
	 
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
        var fileNo         
        var fileNameKey 
        var fileName    
        var filePath 
        var fileSize 
        var insDate    
                
        $("#bb_Comment_Subject").val(bbCommentSubject);    
        $("#bb_Comment_Writer").val(bbCommentWriter);
        $("#bb_Comment_Content").val(bbCommentContent);
         
		for(var a=0; a<3; a++){
             
            if(a < filesLen){
                fileNo         = files[a].file_no;
                fileNameKey = files[a].file_name_key;
                fileName     = files[a].file_name;
                filePath     = files[a].file_path;
                fileSize     = files[a].file_size;
                insDate     = files[a].ins_date;
                
                str += "<tr>"
                str += "<th>첨부파일</th>";
                str += "<td>";
                str += '<input type="file" id="files_' + a + '" name="files" value="" >';
                str += '<input type="button" id="del_check_'+a+'" class="btn black deleteButton" value="삭제" style="margin-left:26px;" fileNo='+fileNo+' />';
                str += '<span id="span_'+a+'" style="margin-left:10px;">' + fileName + '</span>';
                str += '<input type="hidden" id="filesNo_'+a+'" name= "filesNo" value="'+ fileNo +'" />';
                str += '<input type="hidden" id="filesNoAll_'+a+'" name= "filesNoAll" value="'+ fileNo +'" />';
                str += "</td>";
                str += "</tr>";
            } else {    
            	str += "<tr>"
                str += "<th scope='row'>첨부파일</th>"
                str += "<td>";
                str += "<input type='file' id='files_" + a + "' name='files' value=''>"
                str += '<input type="hidden" id="del_check_'+a+'" class="btn black deleteButton" value="삭제" style="margin-left:26px;" fileNo='+fileNo+' />';
                str += '<span id="span_'+a+'" style="margin-left:10px;"> </span>';
                str += '<input type="hidden" id="filesNo_'+a+'" name= "filesNo" value="" />';
                str += "</td>";
                str += "</tr>"
            }
        } 

		$("#files").text(files);
	        
	    } else {            
	        alert("등록된 글이 존재하지 않습니다.");
	        return;
	    } 
	    
	    $("#deltr").html(str);
	    page_events();
	}
 
function page_events(){
 	
 	$('#deltr input[id^=files]').on('change',function(){
 		console.log('xxxxxxxxxxxxxxxxxx:'+$(this).val());
 		//debugger;
 		var splitedFullName = $(this).val().split('\\');
 		var filename = splitedFullName[splitedFullName.length - 1];
 		
 		if(splitedFullName.length > 1){
 			
 			if($('#'+$(this).attr('id').replace('files_','filesNo_')).val() > 0){
 				var yn = confirm("파일을 변경하시겠습니까?");        
 	 	 	    if(yn){
		 			console.log('xxxxxxxxxxxxxxxxxx:');
		 			$('#'+$(this).attr('id').replace('files_','filesNo_')).val('');
		 			$('#'+$(this).attr('id').replace('files_','del_check_')).attr("type","button");
	 	 	    	$('#'+$(this).attr('id').replace('files_','span_')).text(filename); 		  		
	 		  		console.log('xxxxxxxxxxxxxxxxxx:');
 	 	 	    } else {
 	 	 	    	$(this).val("");
 	 	 	    }  
	 		} else {
	 			$('#'+$(this).attr('id').replace('files_','del_check_')).attr("type","button");
 	 	    	$('#'+$(this).attr('id').replace('files_','span_')).text(filename); 		  		
 		  		console.log('xxxxxxxxxxxxxxxxxx:');
	 		}
 		
 		} else {
 			if($('#'+$(this).attr('id').replace('files_','filesNo_')).val() == ''){
	 			$('#'+$(this).attr('id').replace('files_','span_')).text('');
		  		$('#'+$(this).attr('id').replace('files_','del_check_')).attr("type","hidden");
	 		}
 		} 		
 	});
 	
 	$('#deltr').find('.deleteButton').on('click',function(){
 		console.log('xxxxxxxxxxxxxxxxxx:'+$(this).attr('id'));
 		console.log('xxxxxxxxxxxxxxxxxx:'+$(this).val());
 		console.log('xxxxxxxxxxxxxxxxxx:'+$('#'+$(this).attr('id').replace('del_check_','filesNo_')).val());

 		$('#'+$(this).attr('id').replace('del_check_','span_')).text("");
 		$(this).attr("type","hidden");
 		$('#'+$(this).attr('id').replace('del_check_','files_')).val("");
 		if($('#'+$(this).attr('id').replace('del_check_','filesNo_')).val() > 0){
 			console.log('xxxxxxxxxxxxxxxxxx:');
 			$('#'+$(this).attr('id').replace('del_check_','filesNo_')).val('');
 		}
 	});
 }
 
/*
* 게시판 insert, update 분기
*/
function boardDivComment()
{
	var bbCommentSeq = $("#bb_Comment_Seq").val();
	
	if(bbCommentSeq == "0"){
		insertBoardComment();
	}else{
		updateBoardComment();
	}
	
}
 
 /** 게시판 - 작성  */
function insertBoardComment(){
    var bbCommentSubject = $("#bb_Comment_Subject").val();
    var bbCommentWriter = $("#bb_Comment_Writer").val();
    var bbCommentContent = $("#bb_Comment_Content").val();
    var currentPageNo = 1;
    $("#current_page_no").val(currentPageNo);
        
    if (spaceCheck(bbCommentSubject) == 0){            
        alert("제목을 입력해주세요.");
        $("#bb_Comment_Subject").val('');
        $("#bb_Comment_Subject").focus();
        return;
    }
    
    if (spaceCheck(bbCommentWriter) == 0){            
        alert("작성자를 입력해주세요.");
        $("#bb_Comment_Writer").val('');
        $("#bb_Comment_Writer").focus();
        return;
    }
    
    if (spaceCheck(bbCommentContent) == 0){            
        alert("내용을 입력해주세요.");
        $("#bb_Comment_Content").val('');
        $("#bb_Comment_Content").focus();
        return;
    }
    
    var yn = confirm("게시글을 등록하시겠습니까?");        
    if(yn){
    	
    	var filesChk0 = $("input[name='files[0]']").val();
    	var filesChk1 = $("input[name='files[1]']").val();
    	var filesChk2 = $("input[name='files[2]']").val();
    	
    	
        if(filesChk0 == ""){
            $("input[name='files[0]']").remove();
        }
    	if(filesChk1 == ""){
            $("input[name='files[1]']").remove();
        }
    	if(filesChk2 == ""){
            $("input[name='files[2]']").remove();
        }
        
        $("#boardForm").ajaxForm({
            
            url        : "/board/insertBoardComment",
            enctype    : "multipart/form-data",
            cache   : false,
            async   : true,
            type    : "POST",                         
            success : function(obj) {
            	insertBoardCommentCallback(obj, currentPageNo);                
            },           
            error     : function(xhr, status, error) {}
            
        }).submit(); 
    }
}

/** 게시판 - 작성 콜백 함수 */
function insertBoardCommentCallback(obj, currentPageNo){

    if(obj != null){        
        
        var result = obj.result;
        
        if(result == "SUCCESS"){                
            alert("게시글 등록을 성공하였습니다.");                
            goBoardListComment(currentPageNo);                 
        } else {                
            alert("게시글 등록을 실패하였습니다.");    
            return;
        }
    }
}

/** 게시판 - 수정  */
function updateBoardComment(){
	
	var bbCommentSeq = $("#bb_Comment_Seq").val();
	var bbCommentSubject    = $("#bb_Comment_Subject").val();
	var bbCommentWriter     = $("#bb_Comment_Writer").val();
    var bbCommentContent     = $("#bb_Comment_Content").val();
    
    if (bbCommentSeq == ""){  
    	insertBoardComment();            
        return;
    }
        
    if (spaceCheck(bbCommentSubject) == 0){            
        alert("제목을 입력해주세요.");
        $("#bb_Comment_Subject").val('');
        $("#bb_Comment_Subject").focus();
        return;
    }
    
    if (spaceCheck(bbCommentWriter) == 0){            
        alert("작성자를 입력해주세요.");
        $("#bb_Comment_Writer").val('');
        $("#bb_Comment_Writer").focus();
        return;
    }
    
    if (spaceCheck(bbCommentContent) == 0){            
        alert("내용을 입력해주세요.");
        $("#bb_Comment_Content").val('');
        $("#bb_Comment_Content").focus();
        return;
    }
    
	if(confirm('게시글을 수정하시겠습니까?')){
   	
		// remove that un-used 
		$.each($('#deltr input[type="file"]'),function(i,v){
			console.log('i='+i+'::v='+v);
			//debugger;				
			if( isBlank( $(this).val() ) ){
				$(this).remove();
			}
		});
   	
		// call - ajax
		$("#boardForm").ajaxForm({
           
		   url        : "/board/updateBoardComment",
		   enctype    : "multipart/form-data",
		   cache   : false,
		   async   : true,
		   type    : "POST",                         
		   success : function(obj) {
			updateBoardCommentCallback(obj);                
		   },           
		   error     : function(xhr, status, error) {}
           
		}).submit();
    }
}
 
/** 게시판 - 수정 콜백 함수 */
function updateBoardCommentCallback(obj){

    if(obj != null){        
        
        var result = obj.result;
        
        if(result == "SUCCESS"){                
            alert("게시글 수정을 성공하였습니다.");                
            goBoardListComment();                 
        } else {                 
            alert("게시글 수정을 실패하였습니다.");    
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
							<th onclick="location.href='/board/boardListThreeFiles'" style="cursor:Pointer;">다중 첨부파일 게시판</th>
							<th onclick="javascript:goBoardListComment();" style="cursor:Pointer; background-color:Black; color:White">댓글 게시판</th>                             
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
                            <td><input id="bb_Comment_Subject" name="bb_Comment_Subject" value="" class="tbox01"/></td>
                        </tr>
                         <tr>
                            <th>작성자<span class="t_red">*</span></th>
                            <td><input id="bb_Comment_Writer" name="bb_Comment_Writer" value="" class="tbox01"/></td>
                        </tr>
                        <tr>
                            <th>내용<span class="t_red">*</span></th>
                            <td><textarea id="bb_Comment_Content" name="bb_Comment_Content" cols="50" rows="5" class="textarea01"></textarea></td>
                        </tr>
                    </tbody>
                    <tbody id="ins">
                    </tbody>
                    <tbody id="deltr">
                    </tbody>
                </table>    
                <input type="hidden" id="current_page_no"        name="current_page_no"    value="${currentPageNo}"/> <!-- 게시글 페이지 -->
                <input type="hidden" id="bb_Comment_Seq" name = "bb_Comment_Seq" value="<%=bbCommentSeq%>"/>
                <input type="hidden" id="search_type"    name="search_type"    value="U"/> <!-- 조회 타입 - 상세(S)/수정(U) -->
            </form>
            <div class="btn_right mt15">
            	<button type="button" class="btn black mr5" onclick="javascript:boardDivComment();">저장</button>
                <button type="button" class="btn black" onclick="javascript:goBoardListComment();">목록</button>
                
            </div>
        </div>
    </div>
</div>
</body>
</html>