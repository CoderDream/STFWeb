<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>XBRL Shared Testing Facility Main Page</title>
</head>
<frameset rows="64,*,20" frameborder="no" border="0" framespacing="0"
	id="container">
	<frame src="<s:property value="contextPath" />/login/header.action"
		name="topFrame" noresize="noresize" frameborder="0" scrolling="no"
		marginwidth="0" marginheight="0" />
	<frameset cols="200,*" id="frame">
		<frame src="<s:property value="contextPath" />/login/left.action"
			name="leftFrame" noresize="noresize" marginwidth="0" marginheight="0"
			frameborder="0" scrolling="auto" />
		<frame src="<s:property value="contextPath" />/login/right.action"
			name="main" marginwidth="0" marginheight="0" frameborder="0"
			scrolling="auto" />
	</frameset>
	<frame src='<s:property value="contextPath" />/login/bottom.action'
		name="bottomFrame" noresize="noresize" frameborder="0" scrolling="no"
		marginwidth="0" marginheight="0" />
</frameset>

<noframes>
	<body>
		<p>Your brower can't handle frame!</p>
	</body>
</noframes>
</html>