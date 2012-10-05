var searchWin =null ;//搜索窗口

function openSearch(){//打开搜索窗口
	var iq = $iq({
		type : 'get',
		id : 'v' + new Date().getTime()
	}).c(

	'query', {
		xmlns : 'jabber:iq:search'
	}

	);
	log("正在获取搜索表单", iq.toString());

	waiting = $.ligerDialog.waitting('正在获取搜索表单,请稍候...');

	connection.sendIQ(iq, searchFormReceived);
}

//获取搜索表单
function searchFormReceived(iq){
	waiting.hide();

	log("已收到搜索表单:", Strophe.serialize(iq));
	
	var search_instructions = $(iq).find("query>instructions ").text();// 名称
	
	searchWin= $.ligerDialog.open({ 
		title:"搜索窗口",  
		target: $("#search_win") ,
		isResize:true ,
		width:800,
		height:300,
		modal:false,
		showMax: true,
		showToggle: true, 
		showMin: true
	});
	var fields=[];//表单字段
	var i=0;
	$(iq).find("query").children().each(function(){
		
		if(this.tagName!='instructions'){
			var o={
				display:this.tagName,
				name:"search_"+this.tagName,
				type:'text'
			};
			if(i==0){
				o.group=search_instructions;
			}
			fields.push(o);
			
			i++;
		}
	});
	
	
	//搜索表单
	$("#search_form").ligerForm({
		inputWidth : 100,
		labelWidth : 90,
		space : 40,
		fields:fields
		
	});
	
	$("#search_btn").ligerButton({
		text:'搜索',
		click:function(){
			var search_fields =$("#search_btn").data("search_fields");
			var iq = $iq({
				type : 'set',
				id : 'v' + new Date().getTime()
			}).c(

			'query', {
				xmlns : 'jabber:iq:search'
			}

			);
			
			for(var i=0,s= search_fields.length;i<s;i++){
				var  val=$("#"+search_fields[i].name).val();
				
				if(val && val!=''){
		              iq.c(search_fields[i].display,{}).t(val);
				}
			}
			
			log("正在提交搜索表单:", Strophe.serialize(iq));
			waiting = $.ligerDialog.waitting('正在提交搜索表单,请稍候...')
			
			connection.sendIQ(iq, searchResultReceived);
			
		}
	});
	
	$("#search_btn").data("search_fields",fields);//缓存字段列表

}

//返回查询结果
function searchResultReceived(iq){
	waiting.hide();
	log("已收到查询结果:", Strophe.serialize(iq));
	
	//获取字段列表
	var first =$(iq).find("item").first();
	var columns =[];
	first.children().each(function(){
		columns.push({
			
			display:this.tagName,
			name:this.tagName, isAllowHide: true ,align:"left" 	
		});
	});
	
	//搜索结果列表
	var  search_result_grid=$("#search_result_grid").ligerGrid({

        columns: columns,
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
	//搜索结果列表管理器
	var search_result_grid_manager =$("#search_result_grid").ligerGetGridManager();
	
	var Rows =[];//搜索结果列表
	
	$(iq).find("item").each(function(){
		var row ={};
		var  me= this ;
		
		$(me).children().each(function(){
			row[this.tagName]=$(me).find(this.tagName).text();
		});
		
		Rows.push(row);
	});
	//导入搜索结果
	search_result_grid_manager.loadData({Total:Rows.length,Rows:Rows});
	
	
}