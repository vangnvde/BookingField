<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="utf-8">
        <title>${title}</title>
        
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="icon" href="./images/logoweb.png" type="image/gif" sizes="16x16">
        <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Amatic+SC:400,700&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
        <link rel="stylesheet" href="css/animate.css">

        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/owl.theme.default.min.css">
        <link rel="stylesheet" href="css/magnific-popup.css">

        <link rel="stylesheet" href="css/aos.css">

        <link rel="stylesheet" href="css/ionicons.min.css">

        <link rel="stylesheet" href="css/bootstrap-datepicker.css">
        <link rel="stylesheet" href="css/jquery.timepicker.css">


        <link rel="stylesheet" href="css/flaticon.css">
        <link rel="stylesheet" href="css/icomoon.css">
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body class="goto-here">

        <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
            <div class="container">
                <a class="navbar-brand" href="home">FootBallField</a>
               

                <div class="collapse navbar-collapse" id="ftco-nav">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item active"><a href="home" class="nav-link">Trang Chủ</a></li>
                        <c:if test="${sessionScope.ACCOUNT.isAdmin == true}">
                            <li class="nav-item"><a href="admin-load-user" class="nav-link">Quản Lý Người Dùng</a></li>
                            </c:if>
                            <li class="nav-item"><a href="filter-controll" class="nav-link">Tìm Kiếm</a></li>
                        <c:if test="${sessionScope.ACCOUNT == null}">
                            <li class="nav-item"><a href="/login?from=${pageContext.request.requestURI}" class="nav-link">Đăng Nhập</a></li>
                            </c:if>
                            <c:if test="${sessionScope.ACCOUNT != null }">
                                <c:if test="${sessionScope.ACCOUNT.isAdmin == false}">   
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" onclick="getNotification()" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="images/notification.png" style="width: 25px"></a>
                                <div id="notification-menu" class="dropdown-menu" aria-labelledby="dropdown04">
                                    
                                </div>
                            </li>
                            </c:if>
                            <li class="nav-item dropdown">
                                <c:if test="${sessionScope.ACCOUNT.image != null}">
                                    <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img style="width: 25px" src=${sessionScope.ACCOUNT.image}  ></a>
                                    </c:if>
                                    <c:if test="${sessionScope.ACCOUNT.image == null}">
                                    <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="images/avatar.png" style="width: 25px"></a>
                                    </c:if>
                                <div class="dropdown-menu" aria-labelledby="dropdown04">
                                    <a class="dropdown-item">${sessionScope.ACCOUNT.fullname}</a>
                                    <a class="dropdown-item" href="showInfor">Xem Profile</a>
                                    <a class="dropdown-item" href="changePass">Đổi mật khẩu</a>
                                    <c:if test="${sessionScope.ACCOUNT.isManager == true}">
                                    <a class="dropdown-item" href="manager-controll">Quản Lý Sân</a>   
                                    </c:if>
                                    <a class="dropdown-item" href="logoutControll">Đăng Xuất</a>                                                                     
                                </div>
                            </li>
                        </c:if>               
                    </ul>
                </div>
            </div>
        </nav>

        <!-- END nav -->
        
<script>

    function getNotification() {       
        $.ajax({
            url: "/loadUserReceiptByAjax",
            type: "get",
            data: {    
            },
            success: function (data) {
                var alert = document.getElementById('notification-menu');
                alert.innerHTML = data;
            }
        });
    }
</script>