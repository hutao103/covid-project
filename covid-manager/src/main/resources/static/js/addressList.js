var addressObj;

function deleteAddress(obj){
	$.ajax({
		type:"GET",
		url:path+"/address/delete/"+obj.attr("addressid"),
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
		deleteAddress(addressObj);
	});
	
	$(".updateAddress").on("click",function(){
		window.location.href=path+"/address/updateShow?addressId="+$(this).attr("addressid");
	});

	$(".deleteAddress").on("click",function(){
		addressObj = $(this);
		changeDLGContent("Are you sure delete this address["+$(this).attr("name")+"]");
		openYesOrNoDLG();
	});
});