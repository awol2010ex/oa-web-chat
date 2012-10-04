
//显示VCARD
function  showVCard(){
	var  r_jid=$(roster_menu.element).data("jid");//联系人JID
	//取得VCARD
	getVCard(r_jid);
}
//取得VCARD
function getVCard(r_jid){
	var iq = $iq({
		type: 'get',
		id :'v'+new Date().getTime(),
		to :r_jid,
	}).c(
			
	  'vCard', {xmlns: 'vcard-temp'}
	
	);
	log("正在获取联系人VCARD", iq.toString());
	
	connection.sendIQ(iq, VCardReceived);
}

//获取VCARD
function VCardReceived(iq){
	log("已收到VCARD:", Strophe.serialize(iq));
}