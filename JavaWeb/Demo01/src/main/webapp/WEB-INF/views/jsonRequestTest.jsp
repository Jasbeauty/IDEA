<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>测试接收JSON格式的数据</title>
    <%--<script type="text/javascript" src="../../statics/js/jquery.min.js"></script>--%>
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.4.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            testRequestBody();
        });

        function testRequestBody() {
            $.ajax({
                url: "${pageContext.request.contextPath}/json/testRequestBody", // 发送请求的URL字符串
                dataType: "json",
                type: "post",
                contentType: "application/json; charset=utf-8",    // 表示发送的内容编码格式为json类型
                data: JSON.stringify({id: 1, name: "Spring mvc json数据接收测试"}),   // 发送到服务器的数据（如果想以json格式把数据提交到后台的话，JSON.stringify()必须有，否则只会当做表单提交）
                async: true,    // 默认情况下，所有请求均为异步请求
                // 请求成功后的回调函数
                success: function (data) {
                    console.log(data);
                    var html = "";
                    html += "<tr><th>编号</th><th>书名</th><th>作者</th></tr>";
                    for(var i in data){
                        html += "<tr><td>"+data[i].id+"</td><td>"+data[i].name+"</td><td>"+data[i].author+"</td></tr>";
                    }
                    $("table").html(html);
                },
                // 请求出错时调用的函数
                error: function () {
                    alert("发送数据失败 ！")
                }
            });
        }

    </script>

</head>
<body>
<table></table>
</body>
</html>
