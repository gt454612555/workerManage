<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String[] names = request.getParameterValues("name");

    int count = 0;
    if(names!=null){
        count=names.length;
    }

%>
<head>
    <title>填表</title>
    <script type="text/javascript" src="static/js/jquery-3.5.1.js"></script>
    <link rel="stylesheet" href="static/css/writeTable.css">
    <script type="text/javascript">
        $(function () {
            var workerCount = <%=count%>;
            var formInnerHtml="";
            //循环多少，表单就有几行

            <%
                for (int i = 0;i < count;i++){
            %>
                formInnerHtml+="<tr>";
                formInnerHtml+="<td><input type='date' name='date' class='date_picker'></td>";
                formInnerHtml+="<td><input type='text' name='name' value='<%=names[i]%>'></td>";
                formInnerHtml+="<td><input type='text' name='timeDay'></td>";
                formInnerHtml+="<td><input type='text' name='timeHour'></td>";
                formInnerHtml+="<td>";
                formInnerHtml+="<select name='isPaid'>";
                formInnerHtml+="<option  value='true'>是</option>";
                formInnerHtml+="<option  value='false' selected>否</option>";
                formInnerHtml+="</select>";
                formInnerHtml+="</td>";
                formInnerHtml+="</tr>";
            <%
                }
            %>
            $("tbody").html(formInnerHtml);
            //将日期默认赋值为当天
            $(".date_picker").each(function (index, elem) {
                var time = new Date();
                var day = ('0' + time.getDate()).slice(-2);
                var month = ('0' + (time.getMonth() + 1)).slice(-2);
                var today = time.getFullYear() + '-' + month + '-' + day;
                elem.value=today;
            });
            //给提交按钮增加一个点击事件函数，提交ajax请求
            $("#toDB").on("click",function () {
                if(confirm("确定提交吗？提交后无法更改！")){
                    var data = [];
                    $("#tb tr").each(function (i) {//遍历tr

                        if(i!==0){
                            var item = {}
                            $(this).children("td").each(function (j) {//遍历tr的各个td
                                if(j===0){//这是date
                                    item["date"]=$(this).children().val();
                                }else if(j===1){//这是姓名
                                    item["name"]=$(this).children().val();
                                }else if(j===2){//这是天数时长
                                    item["timeDay"]=$(this).children().val();
                                }else if(j===3){//这是小时时长
                                    item["timeHour"]=$(this).children().val();
                                }else {//这是是否结算
                                    item["isPaid"]=$(this).children().children("option:selected").val();
                                }
                            });
                            data.push(item);
                        }

                    });

                    //alert(JSON.stringify(data));

                    //把data做ajax发出去
                    $.ajax({
                        url:"writeLog",
                        type:"post",
                        contentType:"application/json;charset=utf-8",
                        data:JSON.stringify(data),
                        dataType:"html",//要写这个，不然报错
                        success:function (resp) {
                            if(resp!=="failed"){
                                alert("打卡成功，写入数据库"+resp+"行");
                                window.location.href="index.jsp";
                            }else {
                                alert("打卡失败");
                                window.location.href="daka";
                            }

                        },
                        error:function (resp) {

                        }

                    })

                }else {

                }
            })

        })
    </script>
</head>
<body>
    <div>
        <%--这里有个地方要注意：是from里面写table，而不是table里面写form--%>
            <table border=1 cellpadding="0" cellspacing="0" id="tb">
                <thead>
                <tr>
                    <td>日期</td>
                    <td>姓名</td>
                    <td>工作时间(天)</td>
                    <td>工作时间(小时)</td>
                    <td>是否结清当天工资</td>
                </tr>
                </thead>


                <tbody>

                </tbody>
            </table><br><br>



        <button id="toDB" class="send">提交打卡</button>
    </div>

</body>
</html>
