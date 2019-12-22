<%@ page import="java.util.List" %>
<%@ page import="ru.house.manager.EntityDB.Notifications" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ru.house.manager.serviceDB.NotificationsService" %>
<%@ page import="ru.house.manager.controller.PageController" %><%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 20.12.2019
  Time: 0:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Notifications> notificationsList = new ArrayList<>();
    NotificationsService notificationsService = new NotificationsService();
    notificationsList = notificationsService.getAllFromHouseId(PageController.house_id);
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
                <img id="logo" src="https://i.ibb.co/Thxf6jk/brandlogo.png" alt="Лого">
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
                <li><a href="/house.manager/manager-profile/">Профиль</a></li>
                <li><a href="/house.manager/house-registration/">Дома</a></li>
                <li><a href="/house.manager/manager-requests/">Заявки</a></li>
                <li><a href="#">Настройки</a></li>
                <li><a href="/house.manager/">Выйти</a></li>
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
                        <% if (notificationsList.get(i).getStatus().equals("Important")){%>
                        <div class="news-ads-status-value important">Важное</div>
                        <% } else { %>
                        <div class="news-ads-status-value common">Обычное</div>
                        <% } %>
                    </div>
                    <div class="news-ads-date"><span class="glyphicon glyphicon-calendar"></span><%=notificationsList.get(i).getDate()%></div>
                </div>
            </li>
            <% } %>
        </ul>
    </div>
    <form class="create-news-ads-form" action="/house.manager//house-ads/<%=PageController.house_id%>" method="post" accept-charset="UTF-8">
        <h3 id="create-news-ads-heading">Создать объявление</h3>
        <div id="news-ads-text">
            <div id="news-ads-text-label">Текст объявления</div>
            <textarea id="news-ads-text-value" type="text" name="text"></textarea>
        </div>
        <div id="news-ads-status">
            <div id="news-ads-status-label">Укажите статус объявления</div>
            <select id="news-ads-status-value" name="status">
                <option value="Important">Важное</option>
                <option value="Common">Обычное</option>
            </select>
        </div>
        <div id="news-ads-btn-create">
            <button id="button-create" type="submit">Создать</button>
        </div>
    </form>
</div>
</body>
</html>