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
	
	$.ligerDialog.open({
		title : "房间列表",
		target : $("#chat_room_list_win"),
		isResize : true,
		width : 400,
		height : 300
	});
	
	//房间列表表格
	var  chat_room_list_grid=$("#chat_room_list_grid").ligerGrid({

        columns: [
            {display:"房间JID",name:"jid", isAllowHide: true ,align:"left"}    ,
            {display:"房间名称",name:"name", isAllowHide: true ,align:"left"} 
        ],
        data:{"Total":0 ,Rows:[]},
        sortName: 'id',
        showTitle: false,
        dataAction:'local',
        enabledEdit: false,
        rownumbers:true,
        height:"90%",
        width:"100%",
        colDraggable:true,
        usePager: false

    });
	
	var Rows =[];//房间列表数据
	
	$(iq).find("query > item ").each(function(index){
		var me =$(this);
		Rows.push({
			jid:me.attr("jid"),
			name :me.attr("name")
		});
	});
	
	//房间列表管理器
	var chat_room_list_grid_manager =$("#chat_room_list_grid").ligerGetGridManager();
	//房间列表结果
	chat_room_list_grid_manager.loadData({Total:Rows.length,Rows:Rows});
	
}