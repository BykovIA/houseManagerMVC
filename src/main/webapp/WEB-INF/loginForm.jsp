<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="shortcut icon" href="../../assets/ico/favicon.ico">

        <title>Сервис УК - Жильцы</title>

        <!-- Bootstrap core CSS -->
        <link href="WEB-INF/bootstrap.css" rel="stylesheet">
        <link href="WEB-INF/bootstrap.min.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="signin.css" rel="stylesheet">
        <link href="navigation.css" rel="stylesheet">
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
    <div class="container navigation">
        <div class="navbar-header">
            <div id="logo-brand">
                <img id="logo" src="logo-site.png" alt="Лого">
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
            </ul>
        </div>
    </div>
</div>
<div class="container roflan">
    <div class="roflan-content">
        <img src="https://www.nastol.com.ua/pic/201712/960x800/nastol.com.ua-261758.jpg">
    </div>
    <div class="expire">
        <div>
            <form class="form-signin" action="/house.manager/user-profile" method="POST" accept-charset="UTF-8" role="form">
                <h3 class="form-signin-heading">Вход в сервис</h3>
                <input type="email" class="form-control" placeholder="Адрес электронной почты" name = "eMail" autofocus required>
                <input type="password" class="form-control" placeholder="Пароль" name = "password" required>
                <label class="checkbox">
                    <input type="checkbox" value="remember-me">Запомнить меня
                </label>
                <div class="btn-signin">
                    <button class="button-signin" type="submit">Войти</button>
                </div>
                <div class="form-group">
                    <a href="#">Забыли пароль?</a>
                </div>
                <h3 class="form-signin-footing">Нет учётной записи?</h3>
                <div class="btn-registration">
                    <a class="button-registration" href="/house.manager/user-registration">Зарегистрироваться</a>
                </div>
            </form>
        </div>
        <div id="management-company">
            <h3 id="management-company-heading">Вы из Управляющей Компании?</h3>
            <div class="btn-mc-registration">
                <a class="button-mc-registration" href="management-company.html">Подключиться к системе</a>
            </div>
        </div>
    </div>
</div>
<div class="container">
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="WEB-INF/bootstrap.min.js"></script>
</body>
</html>
href="/house.manager/user-registration">Зарегистрироваться
<li><a href="/house.manager/">Авторизация</a></li>