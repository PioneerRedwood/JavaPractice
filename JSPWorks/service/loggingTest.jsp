<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" %>
<%@ include file="../common/common.jsp" %>
<%@ include file="../common/properties.jsp" %>


<%
    final String[][] strss = {
        //{"netstat", "-s", "-p", "tcp", "|", "find", "\"https\""},
        {"tasklist", "-v", "/fi", "\"sessionname eq console\""},
    };
    
%>

<%!
    public static String getTrimmedLine(String origin, String offset) {
        String originStr = origin.trim();
        char[] chars = originStr.toCharArray();
        int[] idxs = new int[chars.length];

        for(int i = 0; i < idxs.length; ++i) {
            if(chars[i] == ' ') {
                idxs[i] = i;
            }
        }
        
        int[] result = new int[(int)(idxs.length / 2)];
        int j = 0;
        int past = 0;

        for(int i = 0; i < idxs.length; ++i) {
            if(idxs[i] != 0 && past == 0) {
                past = 1;
                // System.out.println(i);
                result[j++] = i;
                continue;
            }
            if(idxs[i] == 0 && past == 1) {
                past = 0;
                result[j++] = i;
                continue;
            }
        }

        int[] values;
        int cnt = 0;
        for(int i = 0; i < result.length; i++) {
            if(result[i] != 0) {
                cnt++;
            }
        }

        values = new int[cnt];
        for(int i = 0; i < values.length; i++) {
            values[i] = result[i];
        }


        String valueString = "";
        for(int k = 0; k < values.length; k++) {
            
            if(k == 0) {
                // start
                // System.out.println(originStr.substring(k, values[k]));
                valueString += originStr.substring(k, values[k]) + offset;
            } else if(k == values.length - 1) {
                // end
                // System.out.println(originStr.substring(values[k], originStr.length() - 1));
                valueString += originStr.substring(values[k], originStr.length() - 1) + offset;
            } else {
                // middle
                // System.out.println(originStr.substring(values[k], values[k + 1]));
                valueString += originStr.substring(values[k], values[k + 1]) + offset;
                k++;
            }
        }

        return valueString;
    }

%>

<html lang="ko">

<head>
    <meta charset="UTF-8">
</head>

<body>
    <p>processing..</p>

    <% for(String[] str : strss) { %>
        
        <%
            ArrayList<String> cmdResults = new ArrayList<String>();

            cmdResults = cmdExecToList(strss[0]);
        
            String allResultStr = "";

            for(int i = 0; i < cmdResults.size(); ++i) {
                //allResultStr += getTrimmedLine(cmdResults.get(i), "\t") + "<br>";
                allResultStr += cmdResults.get(i) + "<br>";
            }
        %>
        <%= allResultStr %>
        <br>
    <% } %>

</body>

</html>