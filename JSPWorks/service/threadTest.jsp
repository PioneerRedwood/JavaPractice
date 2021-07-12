<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common/common.jsp" %>
<%@ include file="../common/properties.jsp" %>

<%!
    public static String showMemStat() {
        long max = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        long total = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        long free = Runtime.getRuntime().freeMemory() / 1024 / 1024;

        return String.format("Max: %,dMB, Total: %,dMB, Free %,dMB, Used: %,dMB",
        max, total, free, total-free);
    }
%>

<html>

<head>
    <title> JSP in VS CODE </title>
</head>

<body>
    <h1> Thread Test </h1>
    <p> <%= Thread.activeCount() %> </p>
    <% for(int i = 0; i < 5; ++i) { %>
        <% 
            try{
                Thread.sleep(2500);
            } catch(InterruptedException e) {
                e.printStackTrace();
            } 
        %>
        <p> <%= showMemStat() %> </p>
    <% } %>
</body>

</html>