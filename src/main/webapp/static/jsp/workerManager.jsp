<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<head>
    <base href="<%=basePath%>">
    <title></title>
    <script type="text/javascript" src="static/js/jquery-3.5.1.js"></script>
    <link rel="stylesheet" href="static/css/workerManager.css">
    <script type="text/javascript">
        workerArr = [];
        $(function () {
            $.ajax({
                url: "workerManager.do",
                type: "post",
                contentType: "application/json;charset:utf-8",//设置响应体的数据格式
                dataType: "json",
                success: function (resp) {
                    //为添加工人绑定点击事件
                    $("#addWorkers").click(function () {
                        var appendHtml = "<tr>" +
                            "<td><input type='text' name='name' ></td>" +
                            "<td><input type='text' name='priceDay' ></td>" +
                            "<td><input type='text' name='priceHour' ></td>" +
                            "<td><button class='delA'>删除</button></td>"+
                            "</tr>"
                        $("tbody").append(appendHtml);
                        $(".delA:last").click(function () {
                            var tr = $(this).parent().parent();
                            if(confirm("确定要删除吗？")){
                                tr.remove();
                            }else {

                            }
                        });

                    });
                    $.each(resp, function (index, elem) {
                        //得到每一个工人的json对象，一个elem就是一个工人对象
                        //alert("工人姓名："+elem.name+"天薪："+elem.priceDay+"时薪："+elem.priceHour);
                        workerArr.push(elem);
                    });

                    var tbodyInnerHtml = "";
                    //加载表格，如果数据库的worker表是空的，就啥也不做
                    if(workerArr.length!==0){
                        //加载tbody
                        for(var i = 0;i<workerArr.length;i++){
                            tbodyInnerHtml+="<tr>" +
                                "<td><input type='text' class='oop' readonly='readonly' name='name' value='"+workerArr[i].name+"'></td>" +
                                "<td><input type='text' name='priceDay' value='"+workerArr[i].priceDay+"'></td>" +
                                "<td><input type='text' name='priceHour' value='"+workerArr[i].priceHour+"'></td>" +
                                "<td><button class='delA'>删除</button></td>"+
                                "</tr>"
                        }
                        $("tbody").html(tbodyInnerHtml);
                        $(".delA").click(function () {
                            var tr = $(this).parent().parent();
                            if(confirm("确定要删除吗？")){
                                tr.remove();
                            }else {

                            }
                        });
                    }

                }
            });

            //为保存数据按钮添加点击事件
            $("#saveWorkers").click(function () {
                if(confirm("确定要提交保存吗？")){
                    var data = [];
                    $("#tb tr").each(function (i) {//遍历tr
                        if(i!==0){
                            var item = {}
                            $(this).children("td").each(function (j) {//遍历tr的各个td
                                if(j===0){//这是name
                                    item["name"]=$(this).children().val();
                                }else if(j===1){//priceDay
                                    item["priceDay"]=$(this).children().val();
                                }else if(j===2){//priceHour
                                    item["priceHour"]=$(this).children().val();
                                }else {//这是删除button

                                }
                            });
                            data.push(item);
                        }
                    });
                    //ajax发送data
                    $.ajax({
                        url:"submitChanges",
                        type:"post",
                        contentType:"application/json;charset=utf-8",
                        data:JSON.stringify(data),
                        dataType:"html",//要写这个，不然报错
                        success:function (resp) {
                            if(resp!=="failed"){
                                alert("保存成功!");
                                window.location.href="index.jsp";
                            }else {
                                alert("修改失败");
                                window.location.href="";
                            }

                        },
                        error:function (resp) {
                            alert("保存失败，请检查")

                        }
                    })
                }else {

                }
            })
        });
    </script>
</head>
<body>
<div>

    <table cellspacing="0" cellpadding="0" border=1 id="tb">
        <thead>
        <tr>
            <td>姓名</td>
            <td>工价(天)</td>
            <td>工价(小时)</td>
            <td>操作</td>
        </tr>
        </thead>

        <tbody>



        </tbody>
    </table>
    <br>
    <br>

    <button id="saveWorkers">保存数据</button>
    <br>
    <br>
    <button id="addWorkers">添加工人</button>
</div>
</body>
</html>
