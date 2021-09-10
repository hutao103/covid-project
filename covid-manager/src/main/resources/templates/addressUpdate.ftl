<#include "inc/header.ftl">

<div class="right">
        <div class="location">
            <strong>The current position:</strong>
            <span>UPDATE ADDRESS</span>
        </div>
        <div class="providerAdd">
            <form id="form" method="post">
                <div>
                    <label for="userName">name:</label>
                    <input type="text" name="name" id="name" value="${ab.name}" maxlength="200"> 
                </div>
                <div>
                	<label for="userPassword">lnglat</label>
                   	<input type="text" name="lnglat" id="lnglat" value="${ab.lnglat}" maxlength="60">
                   	<span style="color:red">Latitude in the former,Longitude in the back.  USED,Separated<span>
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
		
		if($("#name").val() == null || $("#name").val() == ''){
			alert('name required');
			return false;
		}
		if($("#lnglat").val() == null || $("#lnglat").val() == ''){
			alert('lnglat required');
			return false;
		}
		
		var longrg = /^(\-|\+)?(((\d|[1-9]\d|1[0-7]\d|0{1,3})\.\d{0,6})|(\d|[1-9]\d|1[0-7]\d|0{1,3})|180\.0{0,6}|180)$/;
		var latreg = /^(\-|\+)?([0-8]?\d{1}\.\d{0,6}|90\.0{0,6}|[0-8]?\d{1}|90)$/;
		if($("#lnglat").val().split(",").length !=2){
			alert('USED,Separated Lnglat');
			return false;
		}
		
		if(!latreg.test($("#lnglat").val().split(",")[0])){
			alert('Latitude error');
			return false;
		}
		
		if(!longrg.test($("#lnglat").val().split(",")[1])){
			alert('Longitude error');
			return false;
		}
	
		if($(this).attr('data-run') == 0){
			$(this).attr('data-run',1)
			$.ajax({ 
				type:"GET",
				url:'${request.contextPath}/address/update',
				data:{addressId:'${ab.addressId}',name:$("#name").val(),lnglat:$("#lnglat").val()},
				dataType:"json",
				success:function(data){
					if(data.success == false){
						alert(data.message);
						$("#update").attr('data-run',0);
						return false;
					}else{
						alert('UPDATE SUCCESS!');
						window.location.href="${request.contextPath}/address/query";
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
		window.location.href="${request.contextPath}/address/query";
	})
});
</script>

