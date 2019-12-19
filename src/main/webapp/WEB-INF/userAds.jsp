<%@ page import="java.util.List" %>
<%@ page import="ru.house.manager.EntityDB.Notifications" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ru.house.manager.serviceDB.NotificationsService" %>
<%@ page import="ru.house.manager.controller.PageController" %>
<%@ page import="ru.house.manager.serviceDB.UsersService" %>
<%@ page import="ru.house.manager.EntityDB.Users" %><%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 20.12.2019
  Time: 0:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  UsersService usersService = new UsersService();
    Users user = new Users();
    user = usersService.getById(PageController.client_account_id);
    List<Notifications> notificationsList = new ArrayList<>();
    NotificationsService notificationsService = new NotificationsService();
    notificationsList = notificationsService.getAllFromHouseId(user.getHouseId());
%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../assets/ico/favicon.ico">

    <title>Личный кабинет</title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap.css" rel="stylesheet">
    <link href="bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
    <link href="navigation.css" rel="stylesheet">
    <link href="mc-ads.css" rel="stylesheet">
    <!-- Fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">

    <style>
        <%@include file="bootstrap.css"%>
    </style>
    <style>
        <%@include file="bootstrap.min.css"%>
    </style>
    <style>
        <%@include file="navigation.css"%>
    </style>
    <style>
        <%@include file="mc-ads.css"%>
    </style>
    <script type="text/javascript">
        <%@include file="jquery.min.js"%>
    </script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="bootstrap.min.js"></script>
</head>
<body>
<div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div id="logo-brand">
                <img id="logo" src="brandlogo.png" alt="Лого">
                <div id="brand">
                    <a href="#">HOUSE MANAGER</a>
                </div>
            </div>
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="resident-main.html">Главная</a></li>
                <li><a href="resident-account.html">Профиль</a></li>
                <li><a href="/house.manager/users-requests">Оставить заявку</a></li>
                <li><a href="/house.manager/users-ads">Новости и объявления</a></li>
                <li><a href="#">Настройки</a></li>
                <li><a href="login.html">Выйти</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container ads">
    <div id="news-ads-container">
        <h3 id="news-ads-heading">Новости и объявления</h3>
        <ul id="news-ads-list">
            <% for(int i = 0; i <notificationsList.size(); i++) { %>
            <li class="news-ads-item">
                <div class="news-ads-textlogo">
                    <div class="news-ads-text"><%=notificationsList.get(i).getText()%></div>
                    <img src="https://photogora.ru/img/product/thumb/3600/1473674102993123600.jpg" alt="" class="news-ads-logo">
                </div>
                <div class="news-ads-statusdate">
                    <div class="news-ads-status">
                        <div class="news-ads-status-label">Статус:</div>
                        <div class="news-ads-status-value <% if (notificationsList.get(i).getStatus().equals("Important")){%>important<%} else {%>common<%}%>"><%=notificationsList.get(i).getStatus()%></div>
                    </div>
                    <div class="news-ads-date"><span class="glyphicon glyphicon-calendar"></span><%=notificationsList.get(i).getDate()%></div>
                </div>
            </li>
            <% } %>
        </ul>
    </div>
</div>
</body>
</html>