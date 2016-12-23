
function toggleSelectedItems(isChecked) {
	$(".selectedItem").each(function(index, el) {
		$(el).prop("checked", isChecked);
		if ($(el).parent()[0].tagName != 'SPAN' && $(el).parent()[0].tagName != 'span') {
			return;
		}
		if (isChecked) {
			$(el).parent().addClass("checked");
		} else {
			$(el).parent().removeClass("checked");
		}
	});
}
// 多选框组2
function toggleSelectedItems2(isChecked) {
	$(".selectedItem2").each(function(index, el) {
		$(el).prop("checked", isChecked);
		if ($(el).parent()[0].tagName != 'SPAN' && $(el).parent()[0].tagName != 'span') {
			return;
		}
		if (isChecked) {
			$(el).parent().addClass("checked");
		} else {
			$(el).parent().removeClass("checked");
		}
	});
}

//多选框组3
function toggleSelectedItems3(isChecked) {
	$(".selectedItem3").each(function(index, el) {
		$(el).prop("checked", isChecked);
		if ($(el).parent()[0].tagName != 'SPAN' && $(el).parent()[0].tagName != 'span') {
			return;
		}
		if (isChecked) {
			$(el).parent().addClass("checked");
		} else {
			$(el).parent().removeClass("checked");
		}
	});
}
