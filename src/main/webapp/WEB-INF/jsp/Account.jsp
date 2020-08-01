<%@ page import="java.util.List" %>
<%@ page import="com.cqupt.entity.Count" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    List<Count> countList= (List<Count>) session.getAttribute("countList");
    for (int i = 0; i < countList.size(); i++) {
        Count count=countList.get(i);
        if(count.getTotalDay()==null){
            count.setTotalDay(0.0);
        }
        if(count.getTotalHour()==null){
            count.setTotalHour(0.0);
        }
        if(count.getTotalWages()==null){
            count.setTotalWages(0.0);
        }
        if (count.getPaidWages()==null) {
            count.setPaidWages(0.0);
        }
        if (count.getNotPaidWages()==null) {
            count.setNotPaidWages(0.0);
        }
    }
%>
<head>
    <base href="<%=basePath%>">
    <title>账目管理</title>
    <script type="text/javascript" src="static/js/jquery-3.5.1.js"></script>
    <link rel="stylesheet" href="static/css/Account.css">
</head>
<body>
    <div>

        <h3>总账表</h3>
        <table border=1 cellpadding="0" cellspacing="0">
            <thead>
                <tr>
                    <td>姓名</td>
                    <td>工作总天数</td>
                    <td>工作总小时数</td>
                    <td>总工资</td>
                    <td>已付工资</td>
                    <td>未付工资</td>
                </tr>
            </thead>
            <tbody>
                <%
                    for (int i = 0; i<countList.size();i++){
                %>
                    <tr>
                        <td><%=countList.get(i).getName()%></td>
                        <td><%=countList.get(i).getTotalDay()%></td>
                        <td><%=countList.get(i).getTotalHour()%></td>
                        <td><%=countList.get(i).getTotalWages()%></td>
                        <td><%=countList.get(i).getPaidWages()%></td>
                        <td><%=countList.get(i).getNotPaidWages()%></td>
                    </tr>

                <%
                    }
                %>

            </tbody>

        </table>
    </div>
    <br>
    <div>
        <h3>结账表</h3>
        <table border=1 cellpadding="0" cellspacing="0">
            <thead>
                <tr>
                    <td>姓名</td>
                    <td>结算金额</td>
                    <td>操作</td>
                </tr>
            </thead>
            <tbody>
                <%
                    for (int i = 0;i<countList.size();i++){
                %>
                    <tr>
                        <td><%=countList.get(i).getName()%></td>
                        <td><input type="text" placeholder="最多为<%=countList.get(i).getNotPaidWages()%>"></td>
                        <td><button class="sub">提交</button></td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>

    </div>
    <br><br>
    <button class="return" >返回首页</button>
</body>
</html>
<script type="text/javascript">
    $(function () {

        $(".sub").on("click",function () {
            var tr = $(this).parent().parent();
            var tds=tr.children();
            var name = tds.html();
            var paid = $(tds[1]).children().val();
            var paidNum = parseFloat(paid);
            var max = $(tds[1]).children()[0].placeholder.substring(3);
            var maxNum = parseFloat(max);

            //alert("最多结算"+maxNum+"-----实际结算值"+paidNum);

            if(paidNum>maxNum){
                alert("超出最多结算金额，请重新输入！");
            }else {
                if(confirm("确认提交吗？")){
                    $.ajax({
                        url:"sendPaidAccount",
                        data:{
                            "name":name,
                            "paid":paid
                        },
                        type:"post",
                        dataType:"html",
                        success:function () {
                            alert("结算成功");
                            window.location.href = "account";

                        },
                        error:function () {
                            alert("结算出错,请检查您的输入");
                        }
                    })

                }else {

                }
            }

        });
        $(".return").click(function () {
            window.location.href="index.jsp";
        })
    })
</script>
