<#include "inc/header.ftl">
        <div class="right">
            <div class="location">
                <strong>The current position:</strong>
                <span>USER MANAGER</span>
            </div>
            <div class="search">
           		<form method="get" action="${request.contextPath}/covid/user/query">
					<input name="method" value="query" class="input-text" type="hidden">
					 <span>userName：</span>
					 <input name="queryname" class="input-text"	type="text" value="${queryUserName}">
					 
					 <input type="hidden" name="pageIndex" value="1"/>
					 <input	value="query" type="submit" id="searchbutton">
				</form>
            </div>
            <!--user-->
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                    <th width="20%">userName</th>
                    <th width="10%">password</th>
                    <th width="10%">isInfect</th>
                    <th width="10%">nric</th>
                    <th width="10%">createTime</th>
                </tr>
                	<#list userList as user>
                		<tr>
							<td><span>${user.userName}</span></td>
							<td><span>${user.password}</span></td>
							<td><span>
								<#if user.isInfect == '1'>
									YES
								<#else>
									NO
								</#if>
							</span></td>
							<td><span>${user.nric?default('')}</span></td>
							<td><span>${user.createTime?string('yyyy-MM-dd HH:mm')}</span>
						</tr>
                	</#list>
			</table>
			<input type="hidden" id="totalPageCount" value="${totalPageCount}"/>
		  	<div class="page-bar">
				<ul class="page-num-ul clearfix">
					<li>A total of ${totalCount} records&nbsp;&nbsp; ${currentPageNo}/${totalPageCount}</li>
					&nbsp;&nbsp;
					<#if currentPageNo gt 1>
						<a href="javascript:page_nav(document.forms[0],1);">first page</a>
						&nbsp;&nbsp;
						<a href="javascript:page_nav(document.forms[0],${currentPageNo-1});">prev</a>
					<#else>
						<a href="javascript:page_nav(document.forms[0],${currentPageNo+1});">next</a>
						&nbsp;&nbsp;
						<a href="javascript:page_nav(document.forms[0],${totalPageCount});">last page</a>
					</#if>
					&nbsp;&nbsp;
				</ul>
			 	<span class="page-go-form"><label>TO</label>
		     		<input type="text" name="inputPage" id="inputPage" class="page-key" />
		     		<button type="button" class="page-btn" onClick='jump_to(document.forms[0],document.getElementById("inputPage").value)'>GO</button>
				</span>
			</div> 
        </div>
    </section>
<#include "inc/footer.ftl">
<script type="text/javascript" src="${request.contextPath}/js/rollpage.js"></script>
