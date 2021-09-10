<#include "inc/header.ftl">

<div class="right">
        <div class="location">
            <strong>The current position:</strong>
            <span>UPDATE SYSTEM USER</span>
        </div>
        <div class="providerAdd">
            <form id="userForm" method="post">
                <div>
                    <label>USERNAME:</label>
                    <input type="text" name="userName" id="userName" value="${ab.userName}" maxlength="50"> 
					<font color="red"></font>
                </div>
                <div>
                	<label>PASSWORD</label>
                   	<input type="password" name="password" id="password" value="${ab.password}" maxlength="20">
					<font color="red"></font>
                </div>
                <div>
                    <label>REALNAME:</label>
                    <input type="text" name="realName" id="realName" value="${ab.realName}" maxlength="50"> 
					<font color="red"></font>
                </div>
                <div>
                    <label>SEX:</label>
					<select name="sex" id="sex" required="required">
					    <option value="M" <#if ab.sex == 'M'>selected="selected"</#if>>M</option>
					    <option value="F" <#if ab.sex == 'F'>selected="selected"</#if>>F</option>
					 </select>
                </div>
                <div>
                    <label>MOBILE:</label>
                    <input type="text" name="mobile" id="mobile" value="${ab.mobile}" required="required" maxlength="20"> 
                </div>
                <div class="providerAddBtn">
                    <input type="button" id="update" value="SAVE" data-run="0">
					<input type="button" id="back" value="BACK" >
                </div>
            </form>
        </div>
</div>
</section>
<#include "inc/footer.ftl">

<script>
$(function(){
	$("#update").on('click',function(){
		if($("#userName").val() == null || $("#userName").val() == ''){
			alert('UserName can not be empty');
			return false;
		}
		if($("#password").val() == null || $("#password").val() == ''){
			alert('Password can not be empty');
			return false;
		}
		if($("#realName").val() == null || $("#realName").val() == ''){
			alert('RealName can not be empty');
			return false;
		}
		if($("#sex").val() == null || $("#sex").val() == ''){
			alert('Sex can not be empty');
			return false;
		}
		if($("#mobile").val() == null || $("#mobile").val() == ''){
			alert('Mobile can not be empty');
			return false;
		}
		
		
		if($(this).attr('data-run') == 0){
			$(this).attr('data-run',1)
			$.ajax({
				type:"GET",
				url:'${request.contextPath}/user/update',
				data:{userId:'${ab.userId}',userName:$("#userName").val(),password:$("#password").val(),realName:$("#realName").val(),sex:$("#sex").val(),mobile:$("#mobile").val()},
				dataType:"json",
				success:function(data){
					if(data.success == false){
						alert(data.message);
						$("#update").attr('data-run',0);
						return false;
					}else{
						alert('UPDATE SUCCESS!');
						window.location.href="${request.contextPath}/user/query";
					}
				},
				error:function(res){
					alert("update fail");
					$("#update").attr('data-run',0);
				}
			});
		}
	})
	
	$("#back").on('click',function(){
		window.location.href="${request.contextPath}/user/query";
	})
});
</script>
