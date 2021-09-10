<#include "inc/header.ftl">
        <div class="right">
            <div class="location">
                <strong>The current position:</strong>
                <span>ADDRESS MANAGER</span>
            </div>
            <div class="search">
           		<form method="get" action="${request.contextPath}/address/query">
					<input name="method" value="query" class="input-text" type="hidden">
					 <span>userName：</span>
					 <input name="queryname" class="input-text"	type="text" value="${queryUserName}">
					 
					 <input type="hidden" name="pageIndex" value="1"/>
					 <input	value="query" type="submit" id="searchbutton">
					 <a href="${request.contextPath}/address/addShow">ADD</a>
				</form>
            </div>
            <!--user-->
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                    <th width="20%">name</th>
                    <th width="20%">lnglat</th>
                    <th width="10%">createTime</th>
                    <th width="20%">updateTime</th>
                    <th width="30%">operation</th>
                </tr>
                	<#list addressList as address>
                		<tr>
							<td><span>${address.name}</span></td>
							<td><span>${address.lnglat}</span></td>
							<td><span>${address.createTime?string('yyyy-MM-dd HH:mm')}</span>
							<td><span>${address.updateTime?string('yyyy-MM-dd HH:mm')}</span>
							<td>
								<span><a class="updateAddress" href="javascript:;" addressid='${address.addressId}'><img src="${request.contextPath}/images/upd.png" alt="update" title="update"/></a></span>
								<span><a class="deleteAddress" href="javascript:;" addressid='${address.addressId}' name="${address.name}"><img src="${request.contextPath}/images/del.png" alt="delete" title="delete"/></a></span>
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
            <p>Are you sure you want to delete this address</p>
            <a href="#" id="yes">YES</a>
            <a href="#" id="no">NO</a>
        </div>
    </div>
</div>

<#include "inc/footer.ftl">
<script type="text/javascript" src="${request.contextPath}/js/addressList.js"></script>
<script type="text/javascript" src="${request.contextPath}/js/rollpage.js"></script>
