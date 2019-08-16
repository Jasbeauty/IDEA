<%--
  Created by IntelliJ IDEA.
  User: wenjiasun
  Date: 2019/8/16
  Time: 1:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.4.1.min.js"></script>
</head>
<body>
<button onclick="clickMe()">点我</button>
<script>
    function clickMe() {
        $.ajax({
            type: 'POST',
            url: "/acceptJsonByEntity/json",
            contentType: "application/json;charset=utf-8",
            // 如果想以json格式把数据提交到后台的话，JSON.stringify()必须有，否则只会当做表单提交
            data: JSON.stringify({
                "bookId": 1,
                "author": "Jack"
            }),
            // 期待返回的数据类型
            dataType: "json",
            success: function (data) {
                var bookId = data.bookId;
                var author = data.author;
                alert("success:" + bookId + ',' + author);
            },
            error: function (data) {
                alert("error" + data);
            }
        });
    }
</script>
</body>
</html>
