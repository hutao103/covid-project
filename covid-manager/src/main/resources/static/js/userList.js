var userObj;

function deleteUser(obj){
	$.ajax({
		type:"GET",
		url:path+"/user/deluser/"+obj.attr("userid"),
		dataType:"json",
		success:function(data){
			if(data.delResult == "true"){//DEL SUCCESS
				cancleBtn();
				obj.parents("tr").remove();
			}else if(data.delResult == "false"){//DEL FAIL
				changeDLGContent("del fail");
			}else if(data.delResult == "notexist"){
				changeDLGContent("del fail,userId not exist!");
			}
		},
		error:function(data){
			changeDLGContent("del fail");
		}
	});
}

function openYesOrNoDLG(){
	$('.del').css('display', 'block');
	$('#removeUse').fadeIn();
}

function cancleBtn(){
	$('.del').css('display', 'none');
	$('#removeUse').fadeOut();
}
function changeDLGContent(contentStr){
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}

$(function(){
	/**
	 * bind
	 * on
	 */
	$('#no').click(function () {
		cancleBtn();
	});
	
	$('#yes').click(function () {
		deleteUser(userObj);
	});
	
	$(".updateUser").on("click",function(){
		window.location.href=path+"/user/updateShow?userId="+$(this).attr("userid");
	});

	$(".deleteUser").on("click",function(){
		userObj = $(this);
		changeDLGContent("Are you sure delete this user["+userObj.attr("username")+"]");
		openYesOrNoDLG();
	});
});