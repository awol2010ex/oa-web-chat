//取得联系人
function getRoster() {
	var iq = $iq({type: 'get'}).c('query', {xmlns: 'jabber:iq:roster'});
	log("正在获取联系人列表", iq.toString());
	connection.sendIQ(iq, rosterReceived);
}


var rosterGroupMap ={} ;//联系人组映射

//获得信息
function rosterReceived(iq) {
	log("已收到接收人:", Strophe.serialize(iq));
	$("#roster").empty();
	
	
	rosterGroupMap={};
	
	$(iq).find("item").each(function() {
		// if a contact is still pending subscription then do not show it in the list
		
		if ($(this).attr('ask')) {
			return true;
		}
		//展示添加的联系人
		addToRoster($(this).attr('jid'),$(this).find("group").text());
	});
	log("发送我的在线信息:", $pres().toString());
	connection.send($pres());
}
//添加联系人
function addToRoster(jid,group) {
	var id = jid2id(jid);//去掉@等符号
	
	var groupname =group?group:"未分组";
	if(!rosterGroupMap[groupname]){//添加联系人分组
		$("#roster").append("<div style='padding:5px;' ><img src='"+window.basePath+"/static/images/roster_group.png'>&nbsp;"+groupname+"</div>");
		rosterGroupMap[groupname]=true;
		
	}
	
	$("#roster").append("<div style='padding:5px;' jid='" + id + "'>&nbsp;&nbsp;&nbsp;&nbsp;<img style='cursor: pointer;' src='"+window.basePath+"/static/images/roster.png'><a style='cursor: pointer;'>"+ jid + " ("+locale.offline+")</a></div>");
	$("#roster > div[jid=" + id + "]>a").click(function() {
		chatWith(jid);//点击与选择的人对话
	});
	$("#roster > div[jid=" + id + "]>a").hover(function() {
		$(this).css("color", "red");
	}, function() {
		$(this).css("color", "#333333");
	});
	
	
	//点击打开菜单
	$("#roster > div[jid=" + id + "]>img").bind("contextmenu",function(e) {
		 roster_menu.show({ top: e.pageY, left: e.pageX });
		 $(roster_menu.element).css("z-index", "10000").data("jid",jid); 
		 return false;
        
	});
}



//删除联系人
function removeRoster(){
	var r_jid = $(roster_menu.element).data("jid");// 联系人JID
	
	
	var iq = $iq({type: 'set'})
	.c('query', {xmlns: 'jabber:iq:roster'})
	.c('item',{jid:r_jid ,subscription:"remove"});//要删除的联系人JID
	
	
	log("正在删除联系人..", iq.toString());
	
	
	connection.sendIQ(iq, afterRemoveRoster);
	
}

//删除联系人后
function afterRemoveRoster(iq){
	log("已收到删除联系人后信息:", Strophe.serialize(iq));
	
	//刷新联系人窗口
	getRoster();
}

//添加而不邀请联系人
function addContactOnly(){
	var toJid = prompt("输入联系人JID:");//输入新的联系人
	var id = jid2id(toJid);
	if (toJid === null) {
		return;
	}
	
	var toGroup=null;
	toGroup = prompt("输入联系人分组:");//输入新的联系人分组
	if(toGroup==''){
		toGroup=null;
	}
	
	
	if (toJid === jid) {//不能添加自己
		alert("不能添加自己为联系人!");
		return;
	}
	if ($("#roster > div[jid=" + id + "]").length > 0) {
		alert("JID 已经在你的联系人列表内!");
		return;
	}
	addToRosterOnly(toJid,toGroup);//添加联系人
}

//添加而不邀请联系人
function addToRosterOnly(jid , group){
	var iq = $iq({type: 'set'})
	.c('query', {xmlns: 'jabber:iq:roster'})
	.c('item',{jid:jid});//要删除的联系人JID
	
	if(group){
		//分组
		iq.c("group").t(group);
	}
	log("正在添加联系人..", iq.toString());
	
	connection.sendIQ(iq, afterAddToRosterOnly);
}
//添加而不邀请联系人
function afterAddToRosterOnly(iq){
    log("已收到添加联系人后信息:", Strophe.serialize(iq));
	
	//刷新联系人窗口
	getRoster();
}

