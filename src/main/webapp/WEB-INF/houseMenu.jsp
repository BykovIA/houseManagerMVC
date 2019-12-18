<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 17.12.2019
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.house.manager.controller.PageController"%>
<%@ page import ="ru.house.manager.serviceDB.ManagersService"%>
<%@ page import ="ru.house.manager.EntityDB.Managers"%>
<%@ page import="ru.house.manager.EntityDB.Houses" %>
<%@ page import="ru.house.manager.serviceDB.HousesService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% ManagersService managersService = new ManagersService();
    Managers manager = new Managers();
    manager = managersService.getByAccountId(PageController.client_account_id);
    HousesService housesService = new HousesService();
    Houses house = new Houses();
    house = housesService.getById(PageController.house_id);
%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../assets/ico/favicon.ico">
    <title>Личный кабинет УК</title>
    <!-- Bootstrap core CSS -->
    <link href="bootstrap.css" rel="stylesheet">
    <link href="bootstrap.min.css" rel="stylesheet">
    <link href="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="navigation.css" rel="stylesheet">
    <link href="mc-house.css" rel="stylesheet">
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
        <%@include file="mc-house.css"%>
    </style>
    <script type="text/javascript">
        <%@include file="bootstrap.min.js"%>
    </script>
    <script type="text/javascript">
        <%@include file="jquery.min.js"%>
    </script>
    <script src="bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous" ></script>
</head>
<body>
<div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div id="logo-brand">
                <img id="logo" src="C://Users/Igor/IdeaProjects/houseManagerMVC/src/main/webapp/images/brandlogo.png" alt="Лого"/>
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
                <li><a href="mc-main.html">Главная</a></li>
                <li><a href="mc-account.html">Профиль</a></li>
                <li><a href="mc-houses.html">Дома</a></li>
                <li><a href="mc-requests.html">Заявки</a></li>
                <li><a href="#">Настройки</a></li>
                <li><a href="login.html">Выйти</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container house">
    <h3 id="house-address-heading">
        <a href="mc-houses.html" title="К списку домов"><span class="glyphicon glyphicon-arrow-left"></span></a>
        <%="город " +house.getCity() + " улица " + house.getAdress()%>
    </h3>
    <div class="container-grid-options">
        <div class="option" onclick="location.href='/house.manager/house-info/<%=PageController.house_id%>';">
            <div class="option-logo">
                <img src="<c:url value="images/house.png"/>" alt="logo">
            </div>
            <div class="option-text">
                Смотрите и редактируйте сведения о доме
            </div>
        </div>
        <div class="option">
            <div class="option-logo">
                <img src="<c:url value="${vote.png}"/>" alt="logo">
            </div>
            <div class="option-text">
                Создавайте голосования для решения общедомовых вопросов
            </div>
        </div>
        <div class="option" onclick="location.href='/house.manager/house-requests/<%=PageController.house_id%>';">
            <div class="option-logo">
                <img src="<c:url value="${request.png}"/>" alt="logo">
            </div>
            <div class="option-text">
                Принимайте заявки от жильцов
            </div>
        </div>
        <div class="option" onclick="location.href='/house.manager/house-ads/<%=PageController.house_id%>';">
            <div class="option-logo">
                <img src="<c:url value="${news.png}"/>" alt="logo">
            </div>
            <div class="option-text">
                Выкладывайте важные объявления и новости
            </div>
        </div>
    </div>
</div>
</body>
</html>
