<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" %>
<%@ include file="../common/common.jsp" %>
<%@ include file="../common/properties.jsp" %>

<%!
    private final String[][] strss = {
        {"cmd", "/c"},
        // {"chcp", "437"},
        {"netstat", "-sp", "tcp"},
        // {"start"},
        {"ipconfig"},
        // {"tracert", "-d", "-h", "16", "-w", "500", "-4"},
        {"tracert"},
        {"ping", "localhost"},
        // {"telnet"},
        // {"ftp"},
        {"route", "PRINT", "-4", "157*"},
        {"arp", "-a"},
        // {"nslookup"}, 
        {"nbtstat", "-a", "-c"},
        // {"netsh"},
        {"getmac"},
        {"tasklist", "-v", "/fi", "\"sessionname eq console\""},
        {"cmd", "/c", " & ", "dir"},
    };

%>

<html lang="ko">

<head>
    <meta charset="UTF-8">

</head>

<body>
    <p>processing..</p>

    <% for(String[] str : strss) { %>
        <p> <%= netCmdExeInBg(str) %> </p>
        <br>
    <% } %>

</body>

</html>