<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>工人打卡记账系统</title>
    <script type="text/javascript" src="static/js/jquery-3.5.1.js"></script>
    <link rel="stylesheet" href="static/css/index.css">
</head>
<body>
    <div class="square">
        <span></span>
        <span></span>
        <span></span>
        <div class="content">
            <h2>每日打卡</h2>
            <p>
                每日记录工人工作时间，并自动生成日志。
                能够自动计算薪资。但是每日只能打卡一次，避免重复打卡。
            </p>
            <a href="daka">进入</a>
        </div>
    </div>

    <div class="square">
        <span></span>
        <span></span>
        <span></span>
        <div class="content">
            <h2>工人管理</h2>
            <p>
                当有新的工人上岗，或者旧的工人离职，可以在这里进行
                相关的添加和删除操作，这将和对应的工资表同步
            </p>
            <a href="static/jsp/workerManager.jsp">进入</a>
        </div>
    </div>

    <div class="square">
        <span></span>
        <span></span>
        <span></span>
        <div class="content">
            <h2>账目管理</h2>
            <p>
                在这里可以查看各个工人的总工资以及结算情况。
                当工人的工资发放结算后，别忘了来这里销账噢
            </p>
            <a href="account">进入</a>
        </div>
    </div>
</body>
</html>

