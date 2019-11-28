<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 28.11.2019
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="ru.house.manager.controller.PageController"%>
<%@ page import ="ru.house.manager.serviceDB.ManagersService"%>
<%@ page import ="ru.house.manager.EntityDB.Managers"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% ManagersService managersService = new ManagersService();
    Managers manager = new Managers();
    manager = managersService.getByAccountId(PageController.client_account_id);
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
    <!-- Custom styles for this template -->
    <link href="account.css" rel="stylesheet">
    <link href="navigation.css" rel="stylesheet">
    <!-- Fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <style>
        <%@include file="bootstrap.css"%>
    </style>
    <style>
        <%@include file="account.css"%>
    </style>
    <style>
        <%@include file="bootstrap.min.css"%>
    </style>
    <style>
        <%@include file="navigation.css"%>
    </style>
    <style>
        <%@include file="registration.css"%>
    </style>
    <style>
        <%@include file="signin.css"%>
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
                <li><a href="mc-main.html">Главная</a></li>
                <li><a href="mc-account.html">Профиль</a></li>
                <li><a href="/house.manager/house-registration">Дома</a></li>
                <li><a href="/house.manager/manager-requests">Заявки</a></li>
                <li><a href="#">Настройки</a></li>
                <li><a href="login.html">Выйти</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container account">
    <div class="form-user-info">
        <form id="form-account">
            <h3 class="account-info-heading">Информация об УК</h3>
            <div class="form-row">
                <div class="form-row-label">
                    <p>Название:</p>
                </div>
                <div class="form-row-value">
                    <input id="user-info" disabled value="<%=manager.getCompanyName()%>">
                </div>
            </div>
            <div class="form-row">
                <div class="form-row-label">
                    <p>Телефон:</p>
                </div>
                <div class="form-row-value">
                    <input id="user-info" disabled value="<%=manager.getPhoneNumber()%>">
                </div>
            </div>
            <div class="form-row">
                <div class="form-row-label">
                    <p>Электронная почта:</p>
                </div>
                <div class="form-row-value">
                    <input id="user-info" disabled value="<%=manager.getEmail()%>">
                </div>
            </div>
            <div class="form-row edit to-right">
                <button class="button-edit" id="edit" onclick="Edit()" type="button">Редактировать</button>
            </div>
            <script>
                function Edit(){
                    $(':disabled').each(function(){
                        $(this).removeAttr('disabled');
                    });
                    $('button.button-edit#edit').hide();
                    $('div.form-row.edit.to-right').append("<button class='button-save' type='button' onclick='Save()'>Сохранить изменения</button>");
                }
                function Save(){
                    $('div.form-row-value>input#user-info').each(function(){
                        $(this).prop('disabled', true);
                    });
                    $('button.button-save').remove();
                    $('button.button-edit#edit').show();
                }
            </script>
        </form>
    </div>
    <div class="form-user-password">
        <form id="form-account">
            <h3 class="account-info-heading">Сменить пароль</h3>
            <div class="form-row">
                <div class="form-row-label">
                    <p>Введите пароль:</p>
                </div>
                <div class="form-row-value">
                    <input type="password">
                </div>
            </div>
            <div class="form-row">
                <div class="form-row-label">
                    <p>Введите новый пароль:</p>
                </div>
                <div class="form-row-value">
                    <input type="password">
                </div>
            </div>
            <div class="form-row">
                <div class="form-row-label">
                    <p>Повторите пароль:</p>
                </div>
                <div class="form-row-value">
                    <input type="password">
                </div>
            </div>
            <div class="form-row to-right">
                <button class="button-edit" type="submit">Сменить пароль</button>
            </div>
        </form>
    </div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="bootstrap.min.js"></script>
</body>
</html>
