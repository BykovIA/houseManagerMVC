<%@ page import ="ru.house.manager.serviceDB.HousesService"%>
<%@ page import ="ru.house.manager.EntityDB.Houses"%>
<%@ page import="ru.house.manager.controller.PageController"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ru.house.manager.serviceDB.ManagersService" %>
<%@ page import="ru.house.manager.EntityDB.Managers" %>
<% HousesService housesService = new HousesService();
    Houses house = new Houses();
    List<Houses> housesList = new ArrayList<>();
    ManagersService managersService = new ManagersService();
    Managers manager = managersService.getByAccountId(PageController.client_account_id);
    PageController.manager_id = manager.getId();
    housesList = housesService.getAllHousesFromManagerId(PageController.manager_id);
    int accessToken = -1;
    if (housesList.size() > 0) {
        accessToken = housesList.get(housesList.size() - 1).getAccessToken();
    }
%>
<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 28.11.2019
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../assets/ico/favicon.ico">

    <title>Регистрация дома</title>

    <!-- Bootstrap core CSS -->
    <link href="WEB-INF/bootstrap.css" rel="stylesheet">
    <link href="WEB-INF/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="WEB-INF/navigation.css" rel="stylesheet">
    <link href="WEB-INF/mc-houses.css" rel="stylesheet">
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
        <%@include file="mc-houses.css"%>
    </style>
    <script type="text/javascript">
        <%@include file="jquery.min.js"%>
    </script>

    <script src="WEB-INF/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous" ></script>
    <script>
        $(document).ready(function() {
            $(".form-register-house").hide();
        });
    </script>
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
<div class="container register-house">
    <div class="container-list-houses">
        <h3 id="list-houses-heading">Список ваших домов</h3>
        <ul id="list-houses">
            <% for(int i = 0; i < housesList.size(); i++) { %>
            <li class="house" onclick="location.href='/house.manager/house-menu/<%=housesList.get(i).getHouseId()%>';">
                <a href="/house.manager/house-menu/<%=housesList.get(i).getHouseId()%>"><%=housesList.get(i).getCity() + " " + housesList.get(i).getAdress()%></a>
            </li>
            <% } %>
        </ul>
        <button id="add-new-house" onclick="ShowRegisterForm()">
            <span class="glyphicon glyphicon-plus"></span>
            Добавить дом
        </button>
        <script>
            function ShowRegisterForm(){
                $(".form-register-house").show();
            }
        </script>
    </div>
    <div class="form-register-house">
        <form id="form-house" action="/house.manager/house-registration" method="post" accept-charset="UTF-8">
            <h3 class="house-register-heading">Подключить новый дом</h3>
            <div class="form-row">
                <div class="form-row-label">
                    <p>Город:</p>
                </div>
                <div class="form-row-value">
                    <input class="house-info" id="house-city" placeholder="Город" required name="city">
                </div>
            </div>
            <div class="form-row">
                <div class="form-row-label">
                    <p>Улица:</p>
                </div>
                <div class="form-row-value">
                    <input class="house-info" id="house-street" placeholder="Название улицы" pattern="[а-Я]{1,30}" required name="street">
                </div>
            </div>
            <div class="form-row">
                <div class="form-row-label">
                    <p>Дом:</p>
                </div>
                <div class="form-row-value">
                    <input class="house-info" id="house-house" placeholder="Номер дома" pattern="[0-9]{1,4}" required title="0-9999" name="house">
                </div>
            </div>
            <div class="form-row">
                <div class="form-row-label">
                    <p>Корпус:</p>
                </div>
                <div class="form-row-value">
                    <input class="house-info" id="house-flat" placeholder="Корпус" required name="flat">
                </div>
            </div>
            <div class="form-row">
                <div class="form-row-label">
                    <p>Количество жителей: </p>
                </div>
                <div class="form-row-value">
                    <input class="house-info" id="house-capacity" placeholder="Количество жителей" pattern="[0-9]{1,4}" required title="0-9999", name="ResidentsNumber">
                </div>
            </div>
            <div class="form-row to-right">
                <button class="button-register" id="register"  type="submit">Зарегистрировать дом</button>
            </div>
            <div class="form-row">
                <div class="form-row-label">
                    <p>Токен дома:</p>
                </div>
                <div class="form-row-value">
                    <input class="house-info" id="token" value="<%=accessToken%>" disabled>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
