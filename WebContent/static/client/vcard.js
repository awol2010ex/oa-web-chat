//显示VCARD
function showVCard() {
	var r_jid = $(roster_menu.element).data("jid");// 联系人JID
	// 取得VCARD
	getVCard(r_jid);
}
// 取得VCARD
function getVCard(r_jid) {
	var iq = $iq({
		type : 'get',
		id : 'v' + new Date().getTime(),
		to : r_jid
	}).c(

	'vCard', {
		xmlns : 'vcard-temp'
	}

	);
	log("正在获取联系人VCARD", iq.toString());

	waiting = $.ligerDialog.waitting('正在取得联系人信息,请稍候...');

	connection.sendIQ(iq, VCardReceived);
}

// 获取VCARD
function VCardReceived(iq) {
	waiting.hide();

	log("已收到VCARD:", Strophe.serialize(iq));
	$.ligerDialog.open({
		title : "联系人信息",
		target : $("#vcard_win"),
		isResize : true,
		width : 400,
		height : 300
	});

	var GIVEN = $(iq).find("vCard >N>GIVEN ").text();// 名称
	var ORGNAME = $(iq).find("vCard >ORG>ORGNAME ").text();// 机构
    var REGION = $(iq).find("vCard >ADR>REGION").first().text();// 地区
	$("#vcard_form").ligerForm({
		inputWidth : 170,
		labelWidth : 90,
		space : 40,
		fields : [ {
			name : "VCARD_GIVEN",
			display : "名称",
			type : "text"
		}, {
			name : "VCARD_ORGNAME",
			display : "机构",
			type : "text"
		}, {
			name : "VCARD_REGION",
			display : "地区",
			type : "text"
		}  ]
	});

	$("#VCARD_GIVEN").val(GIVEN);// 名称
	$("#VCARD_GIVEN").attr("readOnly", true);

	$("#VCARD_ORGNAME").val(ORGNAME);// 机构
	$("#VCARD_ORGNAME").attr("readOnly", true);
	
	$("#VCARD_REGION").val(REGION);//地区
	$("#VCARD_REGION").attr("readOnly", true);

}