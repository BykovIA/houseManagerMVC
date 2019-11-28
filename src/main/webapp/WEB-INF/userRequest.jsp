<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 28.11.2019
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page import ="ru.house.manager.serviceDB.ApplicationsService"%>
<%@ page import ="ru.house.manager.EntityDB.Applications"%>
<%@ page import="ru.house.manager.controller.PageController"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ru.house.manager.serviceDB.UsersService" %>
<%@ page import="ru.house.manager.EntityDB.Users" %>
<% ApplicationsService applicationsService = new ApplicationsService();
    List<Applications> applicationsOpenList = new ArrayList<>();
    List<Applications> applicationsCloseList = new ArrayList<>();
    UsersService usersService = new UsersService();
    Users user = new Users();
    user = usersService.getById(PageController.client_account_id);
    applicationsOpenList = applicationsService.getAllForResident(user.getId(), Applications.STATUS_OPEN);
    ApplicationsService applicationsService2 = new ApplicationsService();
    applicationsCloseList = applicationsService2.getAllForResident(user.getId(), Applications.STATUS_CLOSE);
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../assets/ico/favicon.ico">
    <!-- Bootstrap core CSS -->
    <link href="bootstrap.css" rel="stylesheet">
    <link href="bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
    <link href="requests.css" rel="stylesheet">
    <link href="navigation.css" rel="stylesheet">
    <!-- Fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">

    <title>Ваши заявки</title>

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
        <%@include file="requests.css"%>
    </style>
    <script type="text/javascript">
        <%@include file="jquery.min.js"%>
    </script>

    <script src="WEB-INF/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous" ></script>
</head>
<body>

<div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container navigation">
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
                <li><a href="resident-requests.html">Оставить заявку</a></li>
                <li><a href="#">Новости и объявления</a></li>
                <li><a href="#">Настройки</a></li>
                <li><a href="login.html">Выйти</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container requests">
    <div class="form-create-request">
        <form class="form-request" role="form" method="POST" action="/house.manager/users-requests">
            <h3 class="form-request-heading">Создание новой заявки</h3>
            <div class="form-row">
                <div class="form-row-label">
                    <p>Опишите проблему, с которой столкнулись</p>
                </div>
                <div class="form-row-value">
                    <textarea rows="5" placeholder="Текст заявки" required name = "text"></textarea>
                </div>
            </div>
            <div class="form-row file">
                <div class="form-row-label">
                    <p>Прикрепите фотографии</p>
                </div>
                <div class="form-row-value">
                    <input type="file" id="file" multiple accept="image/*" class="inputfile">
                    <label for="file" class="choosefile">Выберите файлы</label>
                </div>
            </div>
            <div class="form-row to-right">
                <button class="button-create" type="submit">Отправить</button>
            </div>
        </form>
    </div>
    <div class="form-show-requests">
        <h3 class="form-request-heading">Заявки</h3>
        <div class="requests-types">
            <button id="opened" onclick="ChangeToOpenedContent()">Открытые</button>
            <button id="archived" onclick="ChangeToArchivedContent()">Архивные</button>
        </div>
        <div id = "requests" >

        <% for(int i = 0; i < applicationsOpenList.size(); i++) {
         int j = 0;%>
        <div class="request">
            <div class="request-row label-description">Описание:</div>
            <div class="request-row description"><%=applicationsOpenList.get(i).getText()%></div>
            <div class="request-row status-and-date">
                <div class="status">
                    <div class="status-label">Статус:</div>
                    <div class="status-value status-new">Новая</div>
                    <div class="imported-content"><span class="glyphicon glyphicon-picture"></span>1</div>
                </div>
                <div class="date"><span class="glyphicon glyphicon-calendar"></span><%=applicationsOpenList.get(i).getData()%></div>
            </div>
        </div>
        <% } %>
        </div>
        </div>
    </div>
<script>
    function ChangeToArchivedContent(){
        $('.requests>ul').empty();
        var r ='                             <div class="label-description">Описание:</div>                            <div class="description">Lorem ipsum dolor sit, amet consectetur adipisicing elit. Ex doloremque magnam dolor ab natus nostrum corporis voluptate repellendus numquam repellat molestiae cum maiores nulla tempora velit, totam necessitatibus, praesentium omnis.</div><div class="status-and-date"><div class="status"><div class="status-label">Статус:</div><div class="status-value label-success">Выполнена</div><div class="imported-content"><span class="glyphicon glyphicon-picture"></span>1</div></div><div class="date"><span class="glyphicon glyphicon-calendar"></span> 18 марта</div></div>'
        $('.requests>ul').append('<li>'+r+'</li>');
    }
    function ChangeToOpenedContent(){
        $('.requests>ul').empty();
        var f5 = '<li><div class="label-description">Описание:</div><div class="description">Lorem ipsum dolor sit, amet consectetur adipisicing elit. Ex doloremque magnam dolor ab natus nostrum corporis voluptate repellendus numquam repellat molestiae cum maiores nulla tempora velit, totam necessitatibus, praesentium omnis.</div><div class="status-and-date"><div class="status"><div class="status-label">Статус:</div><div class="status-value label-danger">Новая</div><div class="imported-content"><span class="glyphicon glyphicon-picture"></span>2</div></div><div class="date"><span class="glyphicon glyphicon-calendar"></span> 6 апреля</div></div></li><li><div class="label-description">Описание:</div><div class="description">Lorem ipsum dolor sit, amet consectetur adipisicing elit. Ex doloremque magnam dolor ab natus nostrum corporis voluptate repellendus numquam repellat molestiae cum maiores nulla tempora velit, totam necessitatibus, praesentium omnis.</div><div class="status-and-date"><div class="status"><div class="status-label">Статус:</div><div class="status-value label-danger">Новая</div><div class="imported-content"><span class="glyphicon glyphicon-picture"></span>1</div></div><div class="date"><span class="glyphicon glyphicon-calendar"></span> 8 апреля</div></div></li>'
        $('.requests>ul').append(f5);
    }
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="bootstrap.min.js"></script>

</body>
</html>
