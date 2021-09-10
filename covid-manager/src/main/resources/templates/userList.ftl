<#include "inc/header.ftl">
        <div class="right">
            <div class="location">
                <strong>The current position:</strong>
                <span>SYSTEM USER MANAGER</span>
            </div>
            <div class="search">
           		<form method="get" action="${request.contextPath}/user/query">
					<input name="method" value="query" class="input-text" type="hidden">
					 <span>userName：</span>
					 <input name="queryname" class="input-text"	type="text" value="${queryUserName}">
					 
					 <input type="hidden" name="pageIndex" value="1"/>
					 <input	value="query" type="submit" id="searchbutton">
					 <a href="${request.contextPath}/user/addUserShow">ADD</a>
				</form>
            </div>
            <!--user-->
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                    <th width="20%">userName</th>
                    <th width="20%">realName</th>
                    <th width="10%">sex</th>
                    <th width="20%">mobile</th>
                    <th width="10%">createDate</th>
                    <th width="20%">operation</th>
                </tr>
                	<#list userList as user>
                		<tr>
							<td><span>${user.userName}</span></td>
							<td><span>${user.realName}</span></td>
							<td><span>${user.sex}</span></td>
							<td><span>${user.mobile}</span></td>
							<td><span>${user.createDate?string('yyyy-MM-dd HH:mm')}</span>
							<td>
								<span><a class="updateUser" href="javascript:;" userid=${user.userId}><img src="${request.contextPath}/images/upd.png" alt="update" title="update"/></a></span>
								<span><a class="deleteUser" href="javascript:;" userid=${user.userId}><img src="${request.contextPath}/images/del.png" alt="delete" title="delete"/></a></span>
							</td>
						</tr>
                	</#list>
			</table>
			<input type="hidden" id="totalPageCount" value="${totalPageCount}"/>
			<div class="page-bar">
				<ul class="page-num-ul clearfix">
					<li>A total of ${totalCount} records&nbsp;&nbsp; ${currentPageNo}/${totalPageCount}</li>
					&nbsp;&nbsp;
					<#if currentPageNo lt 1>
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

<div class="del"></div>
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>prompt</h2>
        <div class="removeMain">
            <p>Are you sure you want to delete this user</p>
            <a href="#" id="yes">YES</a>
            <a href="#" id="no">NO</a>
        </div>
    </div>
</div>

<#include "inc/footer.ftl">
<script type="text/javascript" src="${request.contextPath}/js/userList.js"></script>
<script type="text/javascript" src="${request.contextPath}/js/rollpage.js"></script>
