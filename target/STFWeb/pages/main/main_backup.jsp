<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
		<title>管理中心</title>
	</head>
	<frameset rows="64,*"  frameborder="NO" border="0" framespacing="0">
		<frame src='<s:property value="contextPath" />/login/header.action' noresize="noresize" frameborder="NO" name="topFrame" scrolling="no" marginwidth="0" marginheight="0" target="main" />
  		<frameset cols="200,*"  rows="560,*" id="frame">
			<frame src="<s:property value="contextPath" />/login/left.action" name="leftFrame" noresize="noresize" marginwidth="0" marginheight="0" frameborder="0" scrolling="no" target="main" />
			<frame src="<s:property value="contextPath" />/login/right.action" name="main" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" target="_self" />
  		</frameset>
  	</frameset>
	<noframes>
  		<body></body>
    </noframes>
</html>