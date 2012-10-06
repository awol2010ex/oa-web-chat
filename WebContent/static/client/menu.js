//联系人菜单
var roster_menu = null;
// 初始化联系人菜单
function init_roster_menu() {
	roster_menu = $.ligerMenu({
		top : 100,
		left : 100,
		width : 120,
		items : [ 
		  {
			text : '查看资料',
			click : showVCard,
			icon : 'search'
		  },
		  {
		    text : '删除联系人',
			click : removeRoster,
			icon : 'delete'
		  }

		]
	});
}
