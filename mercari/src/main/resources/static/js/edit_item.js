/**
 * 
 */
"use strict";

$('#bigId').change(function() {
	$("#category").html("<option value=\"0\" selected>-- grandChild --</option>");
	let token = $("meta[name='_csrf']").attr("content");
	let header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	//入力値をセット
	let param = {
		id: $("#bigId  option:selected").val(),
		name: $("#bigId option:selected").text(),
	}
	//big情報送信url
	let send_url = "/selectCategory/middle";
	$.ajax({
		url: send_url,
		type: "POST",
		contentType: "application/json",
		cache: false,
		data: JSON.stringify(param),
		dataType: "json",
		success: function(res) {   //resにControllerの戻り値が入る
			let middleList = [];
			let disableLine = "<option value=\"0\" selected>-- childCategory --</option>";
			middleList.push(disableLine);
			for (let i = 0; i < res.length; i++) {
				let middle = "<option value=" + res[i].id + ">"
					+ res[i].name + "</option>";
				middleList.push(middle);
			}
			$("#middleId").html(middleList);
		}
	});
});

$('#middleId').change(function() {
	$("#category").show();
	let token = $("meta[name='_csrf']").attr("content");
	let header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	//入力値をセット
	let param = {
		id: $("#middleId  option:selected").val(),
		name: $("#middleId option:selected").text(),
	}
	//middle情報送信url
	let send_url = "/selectCategory/small";
	$.ajax({
		url: send_url,
		type: "POST",
		contentType: "application/json",
		cache: false,
		data: JSON.stringify(param),
		dataType: "json",
		success: function(res) {   //resにControllerの戻り値が入る
			let smallList = [];
			let disableLine = "<option value=\"0\" selected>-- grandChild --</option>";
			smallList.push(disableLine);
			for (let i = 0; i < res.length; i++) {
				let small = "<option value=" + res[i].id + ">"
					+ res[i].name + "</option>";
				smallList.push(small);
			}
			$("#category").html(smallList);
		}
	});
});

