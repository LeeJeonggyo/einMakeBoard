<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 목록</title>

<!-- 공통 CSS -->
<link rel="stylesheet" type="text/css" href="/css/common/common.css"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- 공통 JavaScript -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
    
		$(document).ready(function(){        
			getBoardListComment();
		    
		});
		
		/** 게시판 - 목록 페이지 이동 */
		function goBoardListComment(){                
		    location.href = "/board/boardListComment";
		}
		
		/** 게시판 - 상세 페이지 이동 */
		function goBoardDetailComment(bbCommentSeq, currentPageNo){  
		    location.href = "/board/boardDetailComment?bbCommentSeq="+ bbCommentSeq + "&currentPageNo=" + currentPageNo;
		}
		
		/** 게시판 - 작성 페이지 이동 */
		function goBoardWriteComment(currentPageNo){
			
			var currentPageNo = $("#current_page_no").val();
	        //alert(currentPageNo);
	        
		    location.href = "/board/boardWriteComment?currentPageNo="+currentPageNo;
		}
		
		/** 게시판 - 목록 조회  */
		function getBoardListComment(currentPageNo){
			
			var paging = <%= request.getParameter("currentPageNo") %>;
	    	
	    	if(currentPageNo != undefined){
	    		
	    	} else if (paging != null){
	    		currentPageNo = paging
	    	} else if (currentPageNo === undefined){
				currentPageNo = "1";
			}
			
			$("#current_page_no").val(currentPageNo);

			$.ajax({    
		        
		        url     :"/board/getBoardListComment",
		        data    : $("#boardForm").serialize(),
		        dataType:"JSON",
		        cache   : false,
		        async   : true,
		        type    :"POST",    
		        success : function(obj) {
		            getBoardListCommentCallback(obj, currentPageNo);                
		        },           
		        error     : function(xhr, status, error) {}
		        
		     });
		}
		
		/** 게시판 - 목록 조회  콜백 함수 */
		function getBoardListCommentCallback(obj, currentPageNo){
			
			var state = obj.state;
			
			if(state == "SUCCESS"){
		    
				var data = obj.data;			
				var list = data.list;
				var listLen = list.length;		
				var totalCount = data.totalCount;
				var pagination = data.pagination;
		        
		        var str = "";
		        
		        if(listLen >  0){
		            
		            for(var a=0; a<listLen; a++){
		                
		            	var bbCommentSeq         = list[a].bb_Comment_Seq; 
		                var bbCommentWriter          = list[a].bb_Comment_Writer; 
		                var bbCommentSubject          = list[a].bb_Comment_Subject; 
		                var bbCommentContent          = list[a].bb_Comment_Content; 
		                var bbCommentHits      = list[a].bb_Comment_Hits; 
		                var bbCommentInsDate      = list[a].bb_Comment_Ins_Date; 
		                var filecnt 	= list[a].fileCnt;
		                
		                str += "<tr>";
		                str += "<td>"+ bbCommentSeq +"</td>";
		                str += "<td onclick='javascript:goBoardDetailComment(" + bbCommentSeq + "," + currentPageNo + ");' style='cursor:Pointer'>"+ bbCommentSubject +"</td>";
		                str += "<td>"+ bbCommentWriter +"</td>";
		                
		                if(filecnt > 0){
		                	str += "<td><i class='fa fa-file'></i></td>";
		                } else {
		                	str += "<td></td>";
		                }
		                
		                str += "<td>"+ bbCommentHits +"</td>";                  
		                str += "<td>"+ bbCommentInsDate +"</td>";
		                str += "</tr>";
		                
		            } 
		            
		        } else {
		            
		        	str += "<tr>";
		            str += "<td colspan='6'>등록된 글이 존재하지 않습니다.</td>";
		            str += "<tr>";
		        }
		        
		        $("#tbody").html(str);
		        $("#total_count").text(totalCount);
				$("#pagination").html(pagination);
				
				
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
						<th onclick="location.href='/board/boardListThreeFiles'" style="cursor:Pointer;">다중 첨부파일 게시판</th>
						<th onclick="javascript:goBoardListComment();" style="cursor:Pointer; background-color:Black; color:White">댓글 게시판</th>                             
                    </tr>
                </thead>  
            </table>
        </form>            
        <form id="boardForm" name="boardForm">
        	<input type="hidden" id="function_name" name="function_name" value="getBoardListComment" />
			<input type="hidden" id="current_page_no" name="current_page_no" value="1" />
			
			<div class="page_info">
				<span class="total_count"><strong>전체</strong> : <span id="total_count" class="t_red">0</span>개</span>
			</div>
			
            <table width="100%" class="table01">
                <colgroup>
                    <col width="5%" />
                    <col width="30%" />
                    <col width="15%" />
                    <col width="10%" />
                    <col width="10%" />                        
                    <col width="10%" />
                </colgroup>
                <thead>        
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>첨부파일</th>
                        <th>조회수</th>                            
                        <th>등록일자</th>
                    </tr>
                </thead>
                <tbody id="tbody">
                
                </tbody>    
            </table>
        </form>            
        <div class="btn_right mt15">
            <button type="button" class="btn black mr5" onclick="javascript:goBoardWriteComment();">등록</button>
        </div>
    </div>        
    <div id="pagination"></div>
</div>
</div>
</body>
</html>