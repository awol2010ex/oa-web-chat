//加入群聊流程开始
function addMUC(){
	//<query xmlns="http://jabber.org/protocol/disco#info"></query>
	//http://jabber.org/protocol/disco#items
	var myjid =$("#jid").val();//本人JID
	var mytokens =myjid.split("@");
	var mydomain =mytokens[mytokens.length-1];
	
	//取得服务列表
	var iq = $iq({
		type : 'get',
		id : 'v' + new Date().getTime(),
		to : mydomain
	}).c(

	'query', {
		xmlns : 'http://jabber.org/protocol/disco#items'
	}

	);
	log("正在获取服务列表", iq.toString());
	
	
	waiting = $.ligerDialog.waitting('正在获取服务列表,请稍候...');
	
	connection.sendIQ(iq, getDiscoItems);
}

//取得服务列表
function getDiscoItems(iq){
	
	waiting.hide();

	log("已收到服务列表:", Strophe.serialize(iq));
	
	var chatService=$(iq).find("query > item[jid='chat.ghac.cn'] ");//检测是否有群聊服务
	if(chatService.length>0){
		
	}else{
		alert("不支持群聊");
	}
}