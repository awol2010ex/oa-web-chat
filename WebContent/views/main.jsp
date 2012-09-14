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

</head>
<body style="font-family: arial;">

<div id="connect-form" style="margin: auto; width: 300px;">
<table>
	<tr>
		<td>Server</td>
		<td><input type="text" id="server" value="vysper.org" /></td>
	</tr>
	<tr>
		<td>Port</td>
		<td><input type="text" id="port" value="8080" /></td>
	</tr>
	<tr>
		<td>Context path</td>
		<td><input type="text" id="contextPath" value="bosh/" /></td>
	</tr>
	<tr>
		<td>JID</td>
		<td><input type="text" id="jid" value="user1@vysper.org" /></td>
	</tr>
	<tr>
		<td style="padding-right: 10px;">Password</td>
		<td><input type="password" id="password" value="password1" /></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td><input type="button" id="connect" value="Connect"
			style="float: right; margin-top: 5px;" /></td>
	</tr>
</table>
</div>

<div id="workspace" style="display: none;">

<div id="roster"></div>

<div id="tabs" style="width: 50%; height: 400px; display: none;">
<ul></ul>
</div>

<div style="position: absolute; left: 0; width: 100%; height: 30%; bottom: 0px;">
<div id="logger" style="border: 1px solid; height: 100%; overflow: auto;">
</div>
</div>

</div>
<script type="text/javascript">

//this is needed by flXHR to automatically include its dependencies
if(window.flensed) window.flensed.base_path="<%=contextPath %>/static/resources/flxhr/";
</script>
<script src="<%=contextPath %>/static/resources/jquery/jquery-1.4.2.min.js"></script>
<script src="<%=contextPath %>/static/resources/jquery/jquery-ui-1.8.2.custom.min.js"></script>
<script src="<%=contextPath %>/static/resources/flxhr/flensed.js"></script>
<script src="<%=contextPath %>/static/resources/flxhr/flXHR.js"></script>
<script src="<%=contextPath %>/static/resources/strophe/core.js"></script>
<script src="<%=contextPath %>/static/resources/strophe/base64.js"></script>
<script src="<%=contextPath %>/static/resources/strophe/md5.js"></script>
<script src="<%=contextPath %>/static/resources/strophe/strophe.flxhr.js"></script>
<script src="<%=contextPath %>/static/client/client.js"></script>
</body>
</html>
