<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common/common.jsp" %>
<%@ include file="../common/properties.jsp" %>

<%
    String result = getHeaderInfoByStr(dataMap.get("Naver"));
%>

<html>

<head>
    <title> JSP in VS CODE </title>
</head>

<body>
    <h1> curl -i <%= dataMap.get("Naver") %> </h1>
    <p> <%= result %> </p>
</body>

</html>