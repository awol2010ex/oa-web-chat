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

	log("已收到房间列表:", Strophe.serialize(iq));
	
	var Rows =[];//房间列表数据
	
	$(iq).find("query > item ").each(function(index){
		var me =$(this);
		Rows.push({
			jid:me.attr("jid"),
			name :me.attr("name")
		});
	});
	if(Rows.length==0){
		log("没有房间");
	}
	//进入OA公共群房间
	enterRoom("oa-room@chat."+getMyDomain());
	
}


//进入房间
function enterRoom(jid){//房间JID
	//进入房间
	var pres = $pres({
		id : 'v' + new Date().getTime(),
		to : jid+"/"+($("#jid").val().split("@")[0])
	}).c(

	'x', {
		xmlns : 'http://jabber.org/protocol/muc'
	}

	);
	log("正在进入房间", pres.toString());
	
	
	connection.sendIQ(pres);//获取房间列表
}
//接收分组对话信息
function MucMessageReceived(msg){
	log("已取得分组对话信息：", Strophe.serialize(msg));
	
	
	
	
	var jid = $(msg).attr("from");//发送组
	
	verifyMucChatTab(jid);//显示多人对话框
	
	var body = $(msg).find("> body");
	if (body.length === 1) {
		showMessage(jid2id(jid), jid.split("/")[1], body.text());
	}
	return true;
}

//验证多人对话框
function verifyMucChatTab(jid) {
	var id = jid2id(jid.split("/")[0]);//JID
	var bareJid = Strophe.getBareJidFromJid(jid.split("/")[0]);
	$("#tabs").show();
	
	var chat =$(".l-tab-content > div[tabid ='chat"+id+"']");
	if (chat.length === 0) {
		//$("#tabs").tabs("add", "#chat" + id, bareJid);
		//添加一个tab
		tabs_manager.addTabItem({tabid:"chat"+id,text :bareJid}); 
		
		//对话窗口
		chat =$(".l-tab-content > div[tabid ='chat"+id+"']");
		
		
		chat.append("<div style='height: 290px; margin-bottom: 10px; overflow: auto;'></div><textarea style='width: 100%;height:110px'/>");
		chat.data("jid", jid);
		$(".l-tab-content > div[tabid =chat'"+id+"']>textarea").keydown(function(event) {
			if (event.which === 13) {
				event.preventDefault();
				sendMucMessage($(this).parent().data("jid"), $(this).val());//发送分组信息
				$(this).val("");
			}
		});
	}
	//$("#tabs").tabs("select", "#chat" + id);
	//选中一个tab
	tabs_manager.selectTabItem("chat" + id);
	
	//对话输入框焦点
	$(".l-tab-content > div[tabid =chat'"+id+"']>textarea").focus();
}


//发送对话信息
function sendMucMessage(toJid, text) {
	//showMessage(jid2id(toJid), jid, text);//显示对话信息框
    var msg = $msg({to: toJid.split("/")[0], "type": "groupchat"}).c('body').t(text);
    
    
    //日志
    log("正在分组发送信息:", Strophe.serialize(msg));
    
    
    //发送信息
    connection.send(msg);
}