<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 29.11.2019
  Time: 1:13
  To change this template use File | Settings | File Templates.
--%>

<%@ page import ="ru.house.manager.serviceDB.ApplicationsService"%>
<%@ page import ="ru.house.manager.EntityDB.Applications"%>
<%@ page import="ru.house.manager.controller.PageController"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ru.house.manager.serviceDB.UsersService" %>
<%@ page import="ru.house.manager.EntityDB.Users" %>
<%@ page import="ru.house.manager.serviceDB.ManagersService" %>
<%@ page import="ru.house.manager.EntityDB.Managers" %>
<% ApplicationsService applicationsService = new ApplicationsService();
    List<Applications> applicationsOpenList = new ArrayList<>();
    List<Applications> applicationsCloseList = new ArrayList<>();
    ManagersService managersService = new ManagersService();
    Managers manager = new Managers();
    manager = managersService.getByAccountId(PageController.client_account_id);
    applicationsOpenList = applicationsService.getAllForManager(manager.getId(), Applications.STATUS_OPEN);
    ApplicationsService applicationsService2 = new ApplicationsService();
    applicationsCloseList = applicationsService2.getAllForManager(manager.getId(), Applications.STATUS_CLOSE);

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
                <li><a href="mc-houses.html">Дома</a></li>
                <li><a href="mc-requests.html">Заявки</a></li>
                <li><a href="#">Настройки</a></li>
                <li><a href="login.html">Выйти</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container requests manager">
    <div class="form-show-requests">
        <h3 class="form-request-heading">Заявки</h3>
        <div class="requests-types">
            <form method="post" action="/house.manager/manager-requests-1" accept-charset="UTF-8" role ="form">
            <button id="opened" type="submit"  name="button0" value ="-1">Открытые</button>
            </form>
            <form method="post" action="/house.manager/manager-requests-2" accept-charset="UTF-8" role ="form">
            <button id="archived" type="submit"  name="button1" value="-2">Архивные</button>
            </form>
        </div>
        <div id="requests">
            <% %>
            <%
                if (PageController.request_context == 0) {
                    for(int i = 0; i < applicationsOpenList.size(); i++) { %>
            <form method="post" action="/house.manager/manager-requests" accept-charset="UTF-8" role ="form">
                <div class="request">
                    <div class="request-row author-request">
                        <div class="label-description">Отправил:</div>
                        <div class="description"><%=applicationsOpenList.get(i).getUserId()%></div>
                    </div>
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
                    <div class="request-row btn-delete">
                        <button type="submit" value = "<%=i%>" name = "button">Закрыть заявку</button>
                    </div>
                </div>
                </form>
                    <% }
                    } if (PageController.request_context == 1) {
                        for(int j = 0; j < applicationsCloseList.size(); j++) { %>
            <form method="post" action="/house.manager/manager-requests" accept-charset="UTF-8" role ="form">
                <div class="request">
                    <div class="request-row author-request">
                        <div class="label-description">Отправил:</div>
                        <div class="description"><%=applicationsCloseList.get(j).getUserId()%></div>
                    </div>
                    <div class="request-row label-description">Описание:</div>
                    <div class="request-row description"><%=applicationsCloseList.get(j).getText()%></div>
                    <div class="request-row status-and-date">
                        <div class="status">
                            <div class="status-label">Статус:</div>
                            <div class="status-value status-new">Новая</div>
                            <div class="imported-content"><span class="glyphicon glyphicon-picture"></span>1</div>
                        </div>
                        <div class="date"><span class="glyphicon glyphicon-calendar"></span><%=applicationsCloseList.get(j).getData()%></div>
                    </div>
<%--                    <div class="request-row btn-delete">--%>
<%--                        <button type="submit" value = "<%=j%>" name = "button">Закрыть заявку</button>--%>
<%--                    </div>--%>
                </div>
            </form>
            <% }
            }%>
            <% %>
        </div>
    </div>
</div>
<script>
    var april_6 = null;
    var april_8 = null;
    function ChangeToArchivedContent(){
        /*var april_6 = $('.requests>ul>li').html();
        $('.requests>ul>li').remove(':contains("6 апреля")');
        var april8 = $('.requests>ul>li').html();
        $('.requests>ul>li').remove(':contains("8 апреля")');*/
        var r ='                             <div class="label-description">Описание:</div>                            <div class="description">Lorem ipsum dolor sit, amet consectetur adipisicing elit. Ex doloremque magnam dolor ab natus nostrum corporis voluptate repellendus numquam repellat molestiae cum maiores nulla tempora velit, totam necessitatibus, praesentium omnis.</div><div class="status-and-date"><div class="status"><div class="status-label">Статус:</div><div class="status-value label-success">Выполнена</div><div class="imported-content"><span class="glyphicon glyphicon-picture"></span>1</div></div><div class="date"><span class="glyphicon glyphicon-calendar"></span> 18 марта</div></div>'
        $('.requests>ul>li').empty();
        $('.requests>ul').append('<li>'+r+'</li>');
        $('.requests>ul').append(april_6);
        $('.requests>ul').append(april_8);
    }
    function ChangeToOpenedContent(){
        $('.requests>ul>').empty();
        /*var f5 = '<li><div class="label-description">Описание:</div><div class="description">Lorem ipsum dolor sit, amet consectetur adipisicing elit. Ex doloremque magnam dolor ab natus nostrum corporis voluptate repellendus numquam repellat molestiae cum maiores nulla tempora velit, totam necessitatibus, praesentium omnis.</div><div class="status-and-date"><div class="status"><div class="status-label">Статус:</div><div class="status-value label-danger">Новая</div><div class="imported-content"><span class="glyphicon glyphicon-picture"></span>2</div></div><div class="date"><span class="glyphicon glyphicon-calendar"></span> 6 апреля</div></div></li><li><div class="label-description">Описание:</div><div class="description">Lorem ipsum dolor sit, amet consectetur adipisicing elit. Ex doloremque magnam dolor ab natus nostrum corporis voluptate repellendus numquam repellat molestiae cum maiores nulla tempora velit, totam necessitatibus, praesentium omnis.</div><div class="status-and-date"><div class="status"><div class="status-label">Статус:</div><div class="status-value label-danger">Новая</div><div class="imported-content"><span class="glyphicon glyphicon-picture"></span>1</div></div><div class="date"><span class="glyphicon glyphicon-calendar"></span> 8 апреля</div></div></li>'
        $('.requests>ul').append(f5);*/
    }
    function ChangeStatus6April(){
        $('div.button-completed.to-right')[0].remove();
        $('div.status-value.label-danger').text('Выполнена');
        $('.status-value.label-danger').removeClass('label-danger');
        $('.status-value').addClass('label-success');
        april_6 = $('.requests>ul>li');
        $('.requests>ul>li').remove(':contains("6 апреля")');
    }
    function ChangeStatus8April(){
        $('div.button-completed.to-right')[0].remove();
        $('div.status-value.label-danger').text('Выполнена');
        $('.status-value.label-danger').removeClass('label-danger');
        $('.status-value').addClass('label-success');
        april_8 = $('.requests>ul>li');
        $('.requests>ul>li').remove(':contains("8 апреля")');
    }
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="bootstrap.min.js"></script>
</body>
</html>
