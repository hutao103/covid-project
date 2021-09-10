<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	
</script>
</head>
<body>
 		<div class="page-bar">
			<ul class="page-num-ul clearfix">
				<li>A total of ${totalCount} records&nbsp;&nbsp; ${currentPageNo}/${totalPageCount}</li>
				<c:if test="${currentPageNo > 1}">
					<a href="javascript:page_nav(document.forms[0],1);">first page</a>
					<a href="javascript:page_nav(document.forms[0],${currentPageNo-1});">prev</a>
				</c:if>
				<c:if test="${currentPageNo < totalPageCount}">
					<a href="javascript:page_nav(document.forms[0],${currentPageNo+1});">next</a>
					<a href="javascript:page_nav(document.forms[0],${totalPageCount});">last page</a>
				</c:if>
				&nbsp;&nbsp;
			</ul>
		 <span class="page-go-form"><label>TO</label>
	     <input type="text" name="inputPage" id="inputPage" class="page-key" />
	     <button type="button" class="page-btn" onClick='jump_to(document.forms[0],document.getElementById("inputPage").value)'>GO</button>
		</span>
		</div> 
</body>
<script type="text/javascript" src="${request.contextPath }/js/rollpage.js"></script>
</html>