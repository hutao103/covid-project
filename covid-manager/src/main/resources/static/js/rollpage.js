function page_nav(frm,num){
		frm.pageIndex.value = num;
		frm.submit();
}

function jump_to(frm,num){
    //alert(num);
	var regexp=/^[1-9]\d*$/;
	var totalPageCount = document.getElementById("totalPageCount").value;
	//alert(totalPageCount);
	if(!regexp.test(num)){
		alert("must over 0！");
		return false;
	}else if((num-totalPageCount) > 0){
		alert("page too big");
		return false;
	}else{
		page_nav(frm,num);
	}
}