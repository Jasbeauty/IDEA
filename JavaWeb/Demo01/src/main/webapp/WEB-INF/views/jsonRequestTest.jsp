<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>测试接收JSON格式的数据</title>
    <%--<script type="text/javascript" src="../../statics/js/jquery.min.js"></script>--%>
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.4.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            testRequestBody();
            testGetBody();
            sendxml();
            readxml();
        });

        function testRequestBody() {
            $.ajax({
                url: "${pageContext.request.contextPath}/json/testRequestBody", // 发送请求的URL字符串
                dataType: "json",   // 返回值类型为json
                type: "post",
                contentType: "application/json; charset=utf-8",    // 表示发送的内容编码格式为json类型
                data: JSON.stringify({id: 1, name: "Spring mvc json数据接收测试"}),   // 发送到服务器的数据（如果想以json格式把数据提交到后台的话，JSON.stringify()必须有，否则只会当做表单提交）
                async: true,    // 默认情况下，所有请求均为异步请求
                // 请求成功后的回调函数
                success: function (data) {
                    console.log(data);
                    $("#id1").html(data.id);
                    $("#name1").html(data.name);
                    $("#author1").html(data.author);
                },
                // 请求出错时调用的函数
                error: function () {
                    alert("发送数据失败 ！")
                }
            });
        }

        function testGetBody() {
            $.ajax({
                url: "${pageContext.request.contextPath}/json/testGetBody",
                dataType: "json",
                type: "get",
                contentType: "application/json",
                async: true,
                success: function (data) {
                    console.log(data);
                    var html = "";
                    html += "<tr><th>编号</th><th>书名</th><th>作者</th></tr>";
                    for (var i in data) {
                        html += "<tr><td>" + data[i].id + "</td><td>" + data[i].name + "</td><td>" + data[i].author + "</td></tr>";
                    }
                    $("#jsonTable").html(html);
                },
                // 请求出错时调用的函数
                error: function () {
                    alert("发送数据失败 ！")
                }
            })
        }

        function sendxml() {
            var xmldata = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><bookByxml><id>1</id><name>疯狂Java讲义</name><author>李刚</author></bookByxml>";
            $.ajax({
                url: "${pageContext.request.contextPath}/json/sendxml",
                type: "post",
                contentType: "application/xml", // 发送至服务器的编码类型
                data: xmldata,
                async: true
            });
        }

        function readxml() {
            $.ajax({
                url:"${pageContext.request.contextPath}/json/readxml",
                type:"get",
                dataType:"text",      // 预期服务器返回的数据类型
                async:true,
                // 请求成功后的回调函数。
                success :function(xml){
                    // 获得xml数据的id，name，author
                    var id = $("id", xml).text();
                    var name = $("name", xml).text();
                    var author = $("author", xml).text();
                    var tr  = $("<tr align='center'/>");
                    $("<td/>").html(id).appendTo(tr);
                    $("<td/>").html(name).appendTo(tr);
                    $("<td/>").html(author).appendTo(tr);
                    $("#booktable").append(tr);
                },
                // 请求出错时调用的函数
                error:function(){
                    alert("数据接收失败");
                }
            })
        }
    </script>

</head>
<body>

<table id="booktable" border="1"  style="border-collapse: collapse;">
    <tr align="center">
        <th>编号</th>
        <th>书名</th>
        <th>作者</th>
    </tr>
</table>

<br><br>
编号：<span id="id1"></span><br>
书名：<span id="name1"></span><br>
作者：<span id="author1"></span><br>

<table id="jsonTable"></table>
</body>
</html>
