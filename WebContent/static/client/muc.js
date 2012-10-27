//取得当前域名
function getMyDomain(){
	
	var myjid =$("#jid").val();//本人JID
	var mytokens =myjid.split("@");
	var mydomain =mytokens[mytokens.length-1];
	
	return mydomain;
}


//加入群聊流程开始
function addMUC(){
	//<query xmlns="http://jabber.org/protocol/disco#info"></query>
	//http://jabber.org/protocol/disco#items
	
	//取得服务列表
	var iq = $iq({
		type : 'get',
		id : 'v' + new Date().getTime(),
		to : getMyDomain()//域名
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
	
	var chatService=$(iq).find("query > item[jid='chat."+getMyDomain()+"'] ");//检测是否有群聊服务
	if(chatService.length>0){
		getChatRoomList();//取得群聊房间
	}else{
		alert("不支持群聊");
	}
}
//取得群聊房间列表
function getChatRoomList(){
	//取得服务列表
	var iq = $iq({
		type : 'get',
		id : 'v' + new Date().getTime(),
		to : "chat."+getMyDomain()//群聊服务名
	}).c(

	'query', {
		xmlns : 'http://jabber.org/protocol/disco#items'
	}

	);
	log("正在获取房间列表", iq.toString());
	
	
	waiting = $.ligerDialog.waitting('正在获取房间列表,请稍候...');
	
	connection.sendIQ(iq, recevieRoomList);//获取房间列表
	
}

//获取房间列表
function recevieRoomList(iq){
	waiting.hide();

	log("已收到服务列表:", Strophe.serialize(iq));
	
}