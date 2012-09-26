<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" href="<%=contextPath %>/static/resources/jquery/css/jquery-ui-1.8.2.custom.css" rel="stylesheet" />
<script type="text/javascript">

//this is needed by flXHR to automatically include its dependencies
if(window.flensed) window.flensed.base_path="<%=contextPath %>/static/resources/flxhr/";
</script>


<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/static/skins/ligerui-icons.css" />
<link rel="stylesheet" type="text/css"
	href="<%=contextPath%>/static/skins/Aqua/css/ligerui-all.css" />


<script src="<%=contextPath %>/static/scripts/jquery-1.7.2.min.js"></script>
<script src="<%=contextPath %>/static/resources/jquery/jquery-ui-1.8.2.custom.min.js"></script>
<script src="<%=contextPath %>/static/resources/flxhr/flensed.js"></script>
<script src="<%=contextPath %>/static/resources/flxhr/flXHR.js"></script>
<script src="<%=contextPath %>/static/resources/strophe/core.js"></script>
<script src="<%=contextPath %>/static/resources/strophe/base64.js"></script>
<script src="<%=contextPath %>/static/resources/strophe/md5.js"></script>
<script src="<%=contextPath %>/static/resources/strophe/strophe.flxhr.js"></script>
<script src="<%=contextPath %>/static/client/client.js"></script>
<script src="<%=contextPath %>/static/client/locale_zh.js"></script>



<script type="text/javascript" src="<%=contextPath%>/static/scripts/ligerui/core/base.js"></script>
<script type="text/javascript" src="<%=contextPath%>/static/scripts/ligerui/plugins/ligerAccordion.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/static/scripts/ligerui/plugins/ligerButton.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/static/scripts/ligerui/plugins/ligerCheckBox.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/static/scripts/ligerui/plugins/ligerComboBox.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/static/scripts/ligerui/plugins/ligerDateEditor.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/static/scripts/ligerui/plugins/ligerDialog.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/static/scripts/ligerui/plugins/ligerDrag.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/static/scripts/ligerui/plugins/ligerEasyTab.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/static/scripts/ligerui/plugins/ligerForm.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/static/scripts/ligerui/plugins/ligerGrid.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/static/scripts/ligerui/plugins/ligerLayout.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/static/scripts/ligerui/plugins/ligerMenu.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/static/scripts/ligerui/plugins/ligerMenuBar.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/static/scripts/ligerui/plugins/ligerMessageBox.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/static/scripts/ligerui/plugins/ligerNoSelect.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/static/scripts/ligerui/plugins/ligerRadio.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/static/scripts/ligerui/plugins/ligerResizable.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/static/scripts/ligerui/plugins/ligerSpinner.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/static/scripts/ligerui/plugins/ligerTab.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/static/scripts/ligerui/plugins/ligerTextBox.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/static/scripts/ligerui/plugins/ligerTip.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/static/scripts/ligerui/plugins/ligerToolBar.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/static/scripts/ligerui/plugins/ligerTree.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/static/scripts/ligerui/plugins/ligerWindow.js"></script> 

<script type="text/javascript">
$(document).ready(function() {
	//初始化客户端
	initComponent();
	
	f_connect();//连接
	
});
</script>
</head>
<body style="font-family: arial;">
<!-- 连接窗口 -->
	<div id="connect-form" style="margin: auto; width: 300px;">
		<table>
			<tr>
				<td>Server</td>
				<!-- 服务器IP  -->
				<td><input type="text" id="server" value="<%=  request.getServerName()%>" />
				</td>
			</tr>
			<tr>
				<td>Port</td>
				<!--  bosh 端口 -->
				<td><input type="text" id="port" value="${requestScope.jabber_bosh_port}" />
				</td>
			</tr>
			<tr>
				<td>Context path</td>
				<td><input type="text" id="contextPath" value="bosh/" />
				</td>
			</tr>
			<tr>
				<td>JID</td>
				<td><input type="text" id="jid" value="${requestScope.jid}" />
				</td>
			</tr>
			<tr>
				<td style="padding-right: 10px;">Password</td>
				<td><input type="password" id="password" value="${requestScope.j_password}" />
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="button" id="connect" value="Connect"
					style="float: right; margin-top: 5px;" />
				</td>
			</tr>
		</table>
	</div>

	<div id="workspace" style="display: none;">

		
<!-- 对话 -->
		<div id="tabs" style="width: 50%; height: 400px; display: none;">
			<ul></ul>
		</div>

<!-- 日志 -->
		<div
			style="position: absolute; left: 0; width: 100%; height: 30%; bottom: 0px;">
			<div id="logger"
				style="border: 1px solid; height: 100%; overflow: auto;"></div>
		</div>

	</div>
	
	
	<!-- 联系人窗口 -->
    <div id="roster"></div>
</body>
</html>
