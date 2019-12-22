<%@ page import ="ru.house.manager.Hash.magicString"%>
<%@ page import ="ru.house.manager.serviceDB.UsersService"%>
<%@ page import ="ru.house.manager.EntityDB.Users"%>
<%@ page import ="ru.house.manager.serviceDB.HousesService"%>
<%@ page import ="ru.house.manager.EntityDB.Houses"%>
<%@ page import="ru.house.manager.controller.PageController"%>
<% UsersService usersService = new UsersService();
    HousesService housesService = new HousesService();
    Houses house = new Houses();
    house = housesService.getById(PageController.house_id);
    String[] strArr;
    strArr = (house.getAdress()).split(" ");
%>
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

    <title>Информация о доме</title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap.css" rel="stylesheet">
    <link href="bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="navigation.css" rel="stylesheet">
    <link href="mc-houses.css" rel="stylesheet">
    <!-- Fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <style>
        <%@include file="../css/bootstrap.css"%>
    </style>
    <style>
        <%@include file="../css/bootstrap.min.css"%>
    </style>
    <style>
        <%@include file="../css/navigation.css"%>
    </style>
    <style>
        <%@include file="../css/mc-houses.css"%>
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
<div class="container house-info">
    <div class="form-edit-house">
        <form id="form-house" action="/house.manager/house-info/<%=house.getHouseId()%>" method="post" accept-charset="UTF-8">
            <h3 class="house-edit-heading">Информация о доме</h3>
            <div class="form-row">
                <div class="form-row-label">
                    <p>Город:</p>
                </div>
                <div class="form-row-value">
                    <input class="house-info" id="house-city" value="<%=house.getCity()%>" placeholder="Город" required disabled name="city">
                </div>
            </div>
            <div class="form-row">
                <div class="form-row-label">
                    <p>Улица:</p>
                </div>
                <div class="form-row-value">
                    <input class="house-info" id="house-street" placeholder="Название улицы" value="<%=strArr[0]%>" pattern="[а-Я]{1,30}" required disabled name="street">
                </div>
            </div>
            <div class="form-row">
                <div class="form-row-label">
                    <p>Дом:</p>
                </div>
                <div class="form-row-value">
                    <input class="house-info" id="house-house" placeholder="Номер дома" value="<%=strArr[1]%>" pattern="[0-9]{1,4}" required title="0-9999" disabled name="houseNumber">
                </div>
            </div>
            <div class="form-row">
                <div class="form-row-label">
                    <p>Корпус:</p>
                </div>
                <div class="form-row-value">
                    <input class="house-info" id="house-roomnumber" placeholder="Корпус" value="<%=strArr[2]%>" pattern="[0-9]{1,4}" required title="0-9999" disabled name="corpus">
                </div>
            </div>
            <div class="form-row">
                <div class="form-row-label">
                    <p>Количество жителей: </p>
                </div>
                <div class="form-row-value">
                    <input class="house-info" id="house-capacity" placeholder="Количество жителей" value="<%=house.getResidentsNumber()%>"pattern="[0-9]{1,4}" required title="0-9999" disabled name="residentNumber">
                </div>
            </div>
            <div class="form-row">
                <div class="form-row-label">
                    <p>Токен дома:</p>
                </div>
                <div class="form-row-value">
                    <input class="house-info" id="token" value="<%=house.getAccessToken()%>"disabled name="token">
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
                    $('div.form-row.edit.to-right').append("<button class='button-save' type='submit' >Сохранить изменения</button>" );
                }
                function Save(){
                    $('div.form-row-value>input.house-info').each(function(){
                        $(this).prop('disabled', true);
                    });
                    $('button.button-save').remove();
                    $('button.button-edit#edit').show();
                    $("#form-house").submit();
                }
            </script>
        </form>
    </div>
</div>
</body>
</html>
