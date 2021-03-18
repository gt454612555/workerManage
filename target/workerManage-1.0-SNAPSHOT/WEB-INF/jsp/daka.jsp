<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>每日工人打卡记账</title>
    <script type="text/javascript" src="static/js/jquery-3.5.1.js"></script>
    <link rel="stylesheet" href="static/css/daka.css">
    <script type="text/javascript">
        var localdate = new Date().toLocaleDateString();
        $(function(){
            $("#title").html("今天是"+localdate+"<br>请选择今天来了哪些工人")
            $.ajax({
                url:"showWorkers",
                type:"post",
                dataType:"json",
                cache:false,
                success:function(resp){
                    var tableInner="";
                    $.each(resp,function(index,elem){
                        tableInner+="<tr><td><input type='checkbox' value='"+elem.name+"'></td><td>"+"<p style='color: white'>"+elem.name+"</p>"+"</td></tr>";
                    })
                    $("#workerTable").html(tableInner);
                }
            })
            $("#toNext").click(function(){
                var checkedArr="";
                //获取checked状态的值
                $(":checkbox:checked").each(function(index,elem){
                    if(index===0){
                        checkedArr=checkedArr+"?name="+elem.value;
                    }
                    else {
                        checkedArr=checkedArr+"&name="+elem.value;
                    }
                });

                window.location.href="wT"+checkedArr;
            })
            $("#noWorker").click(function () {
                $.ajax({
                    url:"writeNoWorker",
                    type:"post",
                    dataType:"html",
                    success:function () {
                        alert("打卡成功！");
                        window.location.href="index.jsp"
                    }
                })
            })

        })
    </script>
</head>
<body>

    <div class="square">
        <span></span>
        <span></span>
        <span></span>
        <div class="content">
            <h2 id="title"></h2>
            <div class="ctDiv">
                <table id="workerTable">


                </table>
            </div>

            <button id="toNext">下一步</button>
            <button id="noWorker">没工人</button>
        </div>
    </div>
</body>
</html>
