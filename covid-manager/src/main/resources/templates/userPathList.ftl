<#include "inc/header.ftl">
        <div class="right">
            <div class="location">
                <strong>The current position:</strong>
                <span>USERPATH MANAGER</span>
            </div>
            <div class="search">
           		<form method="get" action="${request.contextPath}/userPath/query">
					<input name="method" value="query" class="input-text" type="hidden">
					 <span>userName：</span>
					 <input name="queryname" class="input-text"	type="text" value="${queryUserName}">
					 
					 <span>ADDRESS：</span>
					 <input name="queryaddressName" class="input-text"	type="text" value="${queryAddressName}">
		    		
		    		 <select name="queryIsCarryVirus" style="width:180px;">
				 		<option value="">--IS CARRY VIRUS--</option>
				 		<#if queryIsCarryVirus?? && queryIsCarryVirus != ''>
				 			<option value="1" <#if '1'== queryIsCarryVirus>selected="selected"</#if>>YES</option>
				 			<option value="0" <#if '0'== queryIsCarryVirus>selected="selected"</#if>>NO</option>
				 		<#else>
				 			<option value="1">YES</option>
				 			<option value="0">NO</option>
				 		</#if>
		    		</select>
		    		
		    		<select name="queryType" style="width:180px;">
				 		<option value="">--ALL TYPE--</option>
				 		<#if queryType?? && queryType != ''>
				 			<option value="2" <#if '2'== queryType>selected="selected"</#if>>qrcode</option>
				 			<option value="1" <#if '1'== queryType>selected="selected"</#if>>bluetooth</option>
				 		<#else>
				 			<option value="2">qrcode</option>
				 			<option value="1">bluetooth</option>
				 		</#if>
		    		</select>
					 
					 <input type="hidden" name="pageIndex" value="1"/>
					 <input	value="query" type="submit" id="searchbutton">
				</form>
            </div>
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                    <th width="20%">userName</th>
                    <th width="10%">addressName</th>
                    <th width="10%">startTime</th>
                    <th width="20%">is carry virus</th>
                    <th width="20%">type</th>
                    <th width="10%">createTime</th>
                </tr>
                	<#list userPathList as userPath>
                		<tr>
							<td><span>${userPath.userName?default('')}</span></td>
							<td><span>${userPath.addressName?default('')}</span></td>
							<td><span>${userPath.startTime?default('')}</span></td>
							<td><span><#if userPath.isCarryVirus == '1'>YES<#else>NO</#if></span></td>
							<td><span><#if userPath.type == '1'>bluetooth<#else>qrcode</#if></span></td>
							<td><span>${userPath.createTime?string('yyyy-MM-dd HH:mm')}</span>
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

<#include "inc/footer.ftl">

<script type="text/javascript" src="${request.contextPath}/js/rollpage.js"></script>
