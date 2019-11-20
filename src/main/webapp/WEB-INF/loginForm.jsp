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
        <link href="http://allfont.ru/allfont.css?fonts=montserrat" rel="stylesheet" type="text/css" />
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
    <div class="container">
        <div class="navbar-header">
            <img class="navbar-left" alt="gavniWE" src="https://i.ibb.co/qm4cXtK/logo-site.png" style="width: 40px; height: 40px;">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">HOUSE MANAGER</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/house.manager/">Авторизация</a></li>
                <li><a href="/house.manager/user-registration/">Регистрация</a></li>
                <!--<li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                    </ul>
                </li>-->
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</div>
<div class="container">
    <form class="form-signin" role="form" action="/house.manager/" method="post">

        <h3 class="form-signin-heading">Вход в сервис</h3>
        <input type="email" class="form-control" placeholder="Адрес электронной почты" name = "eMail" autofocus>
        <input type="password" class="form-control" placeholder="Пароль" name = "password">
        <label class="checkbox">
            <input type="checkbox" value="remember-me">Запомнить меня
        </label>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
        <div class="form-group">
            <a href="#">Забыли пароль?</a>
        </div>
        <h3 class="form-signin-footing">Нет учётной записи?</h3>
        <form>
            <button class="btn btn-lg btn-info btn-block" >
                <a class="redirection" href="/house.manager/user-registration">Зарегистрироваться</a>
            </button>
        </form>
    </form>
</div> <!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->


<!--<a href="/simplmvcapp_war/add-new-order">Add new order</a>-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="WEB-INF/bootstrap.min.js"></script>
</body>
</html>