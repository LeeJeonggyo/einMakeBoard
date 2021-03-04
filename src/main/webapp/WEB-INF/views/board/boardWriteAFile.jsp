<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 수정</title>
<%
	String bbASeq = request.getParameter("bbASeq");
	
	if(bbASeq == null || bbASeq.equals("")){
		bbASeq = "0";
	} 
%>
 
<c:set var="bbASeq" value="<%=bbASeq%>"/> <!-- 게시글 번호 -->
 
<!-- 공통 CSS -->
<link rel="stylesheet" type="text/css" href="/css/common/common.css"/>
 
<!-- 공통 JavaScript -->
<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript" src="/js/common/jquery.form.js"></script>
<script type="text/javascript">

//-----------------------------------------------------------------------------
//공백이나 널인지 확인
//@return : boolean
//-----------------------------------------------------------------------------
function isBlank(p) {
	if(this != null){
	    var str = p.trim();
	    for(var i = 0; i < str.length; i++) {
	        if ((str.charAt(i) != "\t") && (str.charAt(i) != "\n") && (str.charAt(i)!="\r")) {
	            return false;
	        }
	    }
	}
 return true;
}
function isNotBlank(p){
	return !p.isBlank;
}

var filesNo;
$("#files_No").val(filesNo);
var filesNoAll;
$("#files_No_All").val(filesNoAll);

	//<td>${A}</td>
    
    $(document).ready(function(){   
    	var bbASeq = "<%=bbASeq%>";
		var currentPageNo = <%= request.getParameter("currentPageNo") %>
        $("#current_page_no").val(currentPageNo);
    	
    	if(bbASeq != "0"){
    		getBoardDetailAFile();	
    	} else {
    		var str = "";    		
    		str += "<th scope='row'>첨부파일</th>"
            str += "<td><input type='file' id='files[0]' name='files[0]' value=''></td>"
            $("#ins").html(str);
    	}  
    });
    
    /** 게시판 - 목록 페이지 이동 */
    function goBoardListAFile(currentPageNo){  
    	var currentPageNo = $("#current_page_no").val();
        location.href = "/board/boardListAFile?currentPageNo="+currentPageNo;
    }
    
    /** 게시판 - 수정 페이지 이동 */
    function goBoardWriteAFile(bbASeq, currentPageNo){
        
        var bbASeq = $("#bb_A_Seq").val();
        var currentPageNo = $("#current_page_no").val();
        //alert(currentPageNo);
        
        location.href = "/board/boardWriteAFile?bbASeq="+ bbASeq + "&currentPageNo=" + currentPageNo;
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
                data    : $("#boardForm").serialize(),
                dataType: "JSON",
                cache   : false,
                async   : true,
                type    : "POST",    
                success : function(obj) {
                    getBoardDetailAFileCallback(obj);                
                },           
                error     : function(xhr, status, error) {}
                
             });
            
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
            var fileNo         
            var fileNameKey 
            var fileName    
            var filePath 
            var fileSize 
            var insDate    
                    
            $("#bb_A_Subject").val(bbASubject);    
            $("#bb_A_Writer").val(bbAWriter);
            $("#bb_A_Content").val(bbAContent);
            
            if(0 < filesLen){
                fileNo         = files[0].file_no;
                fileNameKey = files[0].file_name_key;
                fileName     = files[0].file_name;
                filePath     = files[0].file_path;
                fileSize     = files[0].file_size;
                insDate     = files[0].ins_date;
                
                str += "<tr>"
                str += "<th>첨부파일</th>";
                str += "<td>";
                str += '<input type="file" id="files_0" name="files" value="" >';
                str += '<input type="button" id="del_check_0" class="btn black deleteButton" value="삭제" style="margin-left:26px;" fileNo='+fileNo+' />';
                str += '<span id="span_0" style="margin-left:10px;">' + fileName + '</span>';
                str += '<input type="hidden" id="filesNo_0" name= "filesNo" value="'+ fileNo +'" />';
                str += '<input type="hidden" id="filesNoAll_0" name= "filesNoAll" value="'+ fileNo +'" />';
                str += "</td>";
                str += "</tr>";
            } else {    
            	str += "<tr>"
                str += "<th scope='row'>첨부파일</th>"
                str += "<td>";
                str += "<input type='file' id='files_0' name='files' value=''>"
                str += '<input type="hidden" id="del_check_0" class="btn black deleteButton" value="삭제" style="margin-left:26px;" fileNo='+fileNo+' />';
                str += '<span id="span_0" style="margin-left:10px;"> </span>';
                str += '<input type="hidden" id="filesNo_0" name= "filesNo" value="0" />';
                str += "</td>";
                str += "</tr>"
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
     			
     			if($('#'+$(this).attr('id').replace('files_','filesNo_')).val() != "0"){
     				var yn = confirm("파일을 변경하시겠습니까?");        
     	 	 	    if(yn){
    		 			console.log('xxxxxxxxxxxxxxxxxx:');
    		 			$('#'+$(this).attr('id').replace('files_','filesNo_')).val("0");
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
     			if($('#'+$(this).attr('id').replace('files_','filesNo_')).val() == "0"){
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
     			$('#'+$(this).attr('id').replace('del_check_','filesNo_')).val("0");
     		}
     		
     	});
     	
     }
    
    /*
    * 게시판 insert, update 분기
    */
    function boardDivAFile()
    {
    	var bbASeq = $("#bb_A_Seq").val();
    	
    	if(bbASeq == "0"){
    		insertBoardAFile();
    	}else{
    		updateBoardAFile();
    	}
    	
    }
    
    /** 게시판 - 작성  */
    function insertBoardAFile(){
        var bbASubject = $("#bb_A_Subject").val();
        var bbAWriter = $("#bb_A_Writer").val();
        var bbAContent = $("#bb_A_Content").val();
        var currentPageNo = 1;
        $("#current_page_no").val(currentPageNo);
            
        if (spaceCheck(bbASubject) == 0){            
            alert("제목을 입력해주세요.");
            $("#bb_A_Subject").val('');
            $("#bb_A_Subject").focus();
            return;
        }
        
        if (spaceCheck(bbAWriter) == 0){            
            alert("작성자를 입력해주세요.");
            $("#bb_A_Writer").val('');
            $("#bb_A_Writer").focus();
            return;
        }
        
        if (spaceCheck(bbAContent) == 0){            
            alert("내용을 입력해주세요.");
            $("#bb_A_Content").val('');
            $("#bb_A_Content").focus();
            return;
        }
        
        var yn = confirm("게시글을 등록하시겠습니까?");        
        if(yn){
        	
        	// remove that un-used 
        	$.each($('#ins input[type="file"]'),function(i,v){
        		console.log('i='+i+'::v='+v);
        		//debugger;
       			//$(this).remove();
       			
        		if( isBlank( $(this).val() ) ){
        			$(this).remove();
        		}
       			
        	});
            
            $("#boardForm").ajaxForm({
                
                url        : "/board/insertBoardAFile",
                enctype    : "multipart/form-data",
                cache   : false,
                async   : true,
                type    : "POST",                         
                success : function(obj) {
                	insertBoardAFileCallback(obj, currentPageNo);                
                },           
                error     : function(xhr, status, error) {}
                
            }).submit(); 
        }
    }
    
    /** 게시판 - 작성 콜백 함수 */
    function insertBoardAFileCallback(obj, currentPageNo){
    
        if(obj != null){        
            
            var result = obj.result;
            
            if(result == "SUCCESS"){                
                alert("게시글 등록을 성공하였습니다.");                
                goBoardListAFile(currentPageNo);                 
            } else {                
                alert("게시글 등록을 실패하였습니다.");    
                return;
            }
        }
    }
    
    /** 게시판 - 수정  */
    function updateBoardAFile(){
    	
    	var bbASeq = $("#bb_A_Seq").val();
    	var bbASubject    = $("#bb_A_Subject").val();
    	var bbAWriter     = $("#bb_A_Writer").val();
        var bbAContent     = $("#bb_A_Content").val();
        
        if (bbASeq == ""){  
        	insertBoardAFile();            
            return;
        }
            
        if (spaceCheck(bbASubject) == 0){            
            alert("제목을 입력해주세요.");
            $("#bb_A_Subject").val('');
            $("#bb_A_Subject").focus();
            return;
        }
        
        if (spaceCheck(bbAWriter) == 0){            
            alert("작성자를 입력해주세요.");
            $("#bb_A_Writer").val('');
            $("#bb_A_Writer").focus();
            return;
        }
        
        if (spaceCheck(bbAContent) == 0){            
            alert("내용을 입력해주세요.");
            $("#bb_A_Content").val('');
            $("#bb_A_Content").focus();
            return;
        }
        
        /*
        var yn = confirm("게시글을 수정하시겠습니까?");        
        if(yn){
        	
        	var filesChk = $("input[name='files[0]']").val();
            if(filesChk == ""){
                $("input[name='files[0]']").remove();
            }
                
            $("#boardForm").ajaxForm({
                
                url        : "/board/updateBoardAFile",
                enctype    : "multipart/form-data",
                cache   : false,
                async   : true,
                type    : "POST",                         
                success : function(obj) {
                	updateBoardAFileCallback(obj);                
                },           
                error     : function(xhr, status, error) {}
                
            }).submit();
        }*/
        
        if(confirm('게시글을 수정하시겠습니까?')){
        	
        	// remove that un-used 
        	$.each($('#deltr input[type="file"]'),function(i,v){
        		console.log('i='+i+'::v='+v);
        		//debugger;
       			//$(this).remove();
       			
        		if( isBlank( $(this).val() ) ){
        			$(this).remove();
        		}
       			
        	});
        	
        	// call - ajax
        	$("#boardForm").ajaxForm({
                
                url        : "/board/updateBoardAFile",
                enctype    : "multipart/form-data",
                cache   : false,
                async   : true,
                type    : "POST",                         
                success : function(obj) {
                	updateBoardAFileCallback(obj);                
                },           
                error     : function(xhr, status, error) {}
                
            }).submit();
        	
        	
         }
        
    }
    
    /** 게시판 - 수정 콜백 함수 */
    function updateBoardAFileCallback(obj){
    
        if(obj != null){        
            
            var result = obj.result;
            
            if(result == "SUCCESS"){                
                alert("게시글 수정을 성공하였습니다.");                
                goBoardListAFile();                 
            } else {                 
                alert("게시글 수정을 실패하였습니다.");    
                return;
            }
        }
    }
    
    // 입력받은 문자에 스페이스바와 엔터키만 입력 된 경우 확인
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
                            <th onclick="javascript:goBoardListAFile();" style="cursor:Pointer; background-color:Black; color:White">첨부파일 게시판</th>
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
                            <td><input id="bb_A_Subject" name="bb_A_Subject" value="" class="tbox01"/></td>
                        </tr>
                         <tr>
                            <th>작성자<span class="t_red">*</span></th>
                            <td><input id="bb_A_Writer" name="bb_A_Writer" value="" class="tbox01"/></td>
                        </tr>
                        <tr>
                            <th>내용<span class="t_red">*</span></th>
                            <td><textarea id="bb_A_Content" name="bb_A_Content" cols="50" rows="5" class="textarea01"></textarea></td>
                        </tr>
                    </tbody>
                    <tbody id="ins">
                    </tbody>
                    <tbody id="deltr">
                    </tbody>
                </table>    
                <input type="hidden" id="current_page_no"        name="current_page_no"    value="${currentPageNo}"/> <!-- 게시글 페이지 -->
                <input type="hidden" id="A" />
                <input type="hidden" id="bb_A_Seq" name = "bb_A_Seq" value="<%=bbASeq%>"/>
                <input type="hidden" id="search_type"    name="search_type"    value="U"/> <!-- 조회 타입 - 상세(S)/수정(U) -->
            </form>
            <div class="btn_right mt15">
            	<button type="button" class="btn black mr5" onclick="javascript:boardDivAFile();">저장</button>
                <button type="button" class="btn black" onclick="javascript:goBoardListAFile();">목록</button>
                
            </div>
        </div>
    </div>
</div>
</body>
</html>