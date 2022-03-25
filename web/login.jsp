<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="header.jsp"></jsp:include>

    <div class="hero-wrap hero-bread" style="background-image: url('images/banner1.jpg');">
        <div class="container">
            <div class="row no-gutters slider-text align-items-center justify-content-center">
                <div class="col-md-9 ftco-animate text-center">
                    <p class="breadcrumbs"><span class="mr-2"><a href="home">Trang Chủ</a></span></p>             
                </div>
            </div>
        </div>
    </div>

    <section class="ftco-section contact-section bg-light">
        <div class="container">
            <div class="row block-12">
                <div class="col-md-6 order-md-last d-flex" >
                    <img src="./images/loginimg.png">
                </div>
                <div class="col-md-6 order-md-last d-flex" >
                    <form action="login" class="bg-white p-5 contact-form " id="form-signin" method="POST">
                        <h2 class="login">Đăng Nhập</h2>
                        <div class="form-group">
                            <input type="text" class="form-control" name="username" value="${username}" placeholder="Your Name" required>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" name="password" value="${password}"placeholder="Your Password" required>
                        </div>
<!--                        remember-->
                        <div class="form-group form-check">
                    <input name="remember" value="1" type="checkbox" class="form-check-input" id="exampleCheck1">
                    <label class="form-check-label" for="exampleCheck1">Ghi Nhớ Đăng Nhập</label>
                </div>
                        <div class="form-group">
                            <p class="text-center">${ERRORMASSAGE}</p>
                    </div>
                    <div class="form-group row">
                        <input type="submit" value="Đăng Nhập" class="btn btn-primary col-md-6 py-3 px-5" >                            
                    </div>
                    <div class="form-group row">
                        <center>
                            <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=https://booking-field.herokuapp.com/login-google&response_type=code
                               &client_id=932164836392-bm4k84o4if6v044v8lkevn114dqs9qtn.apps.googleusercontent.com&approval_prompt=force"><img src="images/google.png" style="width: 30px;margin-right: 5px"></a>                            
                        </center>
                    </div>
                    <div class="form-group row">
                        <p>Chưa có tài khoản <a href="signup">Đăng Ký</a></p>
                    </div>
                </form>                 
            </div>
            
        </div>
    </div>
</section>


<jsp:include page="footer.jsp"></jsp:include>