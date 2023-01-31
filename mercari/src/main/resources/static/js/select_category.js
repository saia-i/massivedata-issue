/**
 * 
 */
"use strict";

$("#bigName").change(function() {
	$("#smallName option:nth-child(n+1)").remove();
	let defaultSmall = document.createElement("option");
				defaultSmall.value = "0";
				defaultSmall.text = "-- grandChild --";
				document.getElementById("smallName").append(defaultSmall);
	let token = $("meta[name='_csrf']").attr("content");
	let header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	$("#middleName option:nth-child(n+1)").remove();
	let defaultMiddle = document.createElement("option");
				defaultMiddle.value = "0";
				defaultMiddle.text = "-- childCategory --";
				document.getElementById("middleName").append(defaultMiddle);
	//入力値をセット
	let param = {
		path: $("#bigName option:selected").text() + "/",
		hierarchy: 2
	};
	//big情報送信url
	let send_url = "/selectCategory/getChildList";
	$.ajax({
		url: send_url,
		type: "POST",
		contentType: "application/json",
		cache: false,
		data: JSON.stringify(param),
		dataType: "json",
		success: function(res) {//resにControllerの戻り値が入る
			for (let i = 0; i < res.length; i++) {
				let op = document.createElement("option");
				op.value = res[i].name;
				op.text = res[i].name;
				document.getElementById("middleName").append(op);
			}
		},
	});
});

$("#middleName").change(function() {
	let token = $("meta[name='_csrf']").attr("content");
	let header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	$("#smallName option:nth-child(n+1)").remove();
	let defaultSmall = document.createElement("option");
				defaultSmall.value = "0";
				defaultSmall.text = "-- grandChild --";
				document.getElementById("smallName").append(defaultSmall);
	//入力値をセット
	let param = {
		path: $("#bigName option:selected").text() + "/" + $("#middleName option:selected").text() + "/",
		hierarchy: 3
	};
	//middle情報送信url
	let send_url = "/selectCategory/getChildList";
	$.ajax({
		url: send_url,
		type: "POST",
		contentType: "application/json",
		cache: false,
		data: JSON.stringify(param),
		dataType: "json",
		success: function(res) {//resにControllerの戻り値が入る
			for (let i = 0; i < res.length; i++) {
				let op = document.createElement("option");
				op.value = res[i].name;
				op.text = res[i].name;
				document.getElementById("smallName").append(op);
			}
		},
	});
});