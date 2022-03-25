

<%-- 
    Document   : signup
    Created on : 31-01-2022, 17:09:55
    Author     : Vang Nguyen
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="header.jsp"></jsp:include>

    <div class="hero-wrap hero-bread" style="background-image: url('images/banner1.jpg');">
        <div class="container">
            <div class="row no-gutters slider-text align-items-center justify-content-center">
                <div class="col-md-9 ftco-animate text-center">
                    <p class="breadcrumbs"><span class="mr-2"><a href="index.html">Home</a></span> <span>Contact us</span></p>
                    <h1 class="mb-0 bread">Contact us</h1>
                </div>
            </div>
        </div>
    </div>

    <section class="ftco-section contact-section bg-light">
        <div class="container">
            <div class="row block-9">
                <div class="col-md-6 order-md-last d-flex" >
                    <img src="./images/loginimg.png">
                </div>
                <div class="col-md-6 order-md-last d-flex" >                 
                    <form action="signup" class="bg-white p-5 contact-form " onchange="checkPasswordValid(this)" id="form-signup" method="POST">
                        <h2 class="login">Đăng Ký</h2>
                        <div class="form-group">
                            <input type="text" value="${username}"class="form-control" name="username" placeholder="Tài Khoản" required>
                        <p id="nameerror" class="text-warning"></p>
                    </div>
                    <p class="text-left">${NAMEERROR}</p>
                    <div class="form-group">
                        <input type="password"  value="${password}" class="form-control" name="password" placeholder="Mật Khẩu" required>
                        <p id="passerror" class="text-warning"></p>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="rePassword" placeholder="Nhập Lại Mật Khẩu" required>
                        <p id="repasserror" class="text-warning"></p>
                    </div>
                    <p class="text-left">${PASSWORDERROR}</p>
                    <div class="form-group row">
                        <input type="submit" value="Đăng Ký" style="display: none" id="btn-signup" class="btn btn-primary col-md-6 py-3 px-5" >                            
                    </div>
                    <div class="form-group row">
                        <p>Đã Có Tài Khoản <a href="login">Đăng Nhập</a></p>
                    </div>
                </form> 
            </div>

        </div>
    </div>
</section>
<script>
function checkPasswordValid(form)
  {
    if(form.username.value == "") {
      form.username.focus();
      document.getElementById("nameerror").innerHTML="*Tên không bỏ trống!";
    } else{
        document.getElementById("nameerror").innerHTML="";
    }
    re = /^\w+$/;
    if(!re.test(form.username.value)) {
      document.getElementById("nameerror").innerHTML="*Tên có thể bao gồm chữ, số và dấu gạch dưới!";
      form.username.focus();  
    } else{
      document.getElementById("nameerror").innerHTML="";
      form.username.focus();  
    }
    var check = false;
    if(form.password.value != "" ) {
        document.getElementById("passerror").innerHTML="*Mật khẩu phải chứa ";  
      if(form.password.value.length < 6) {
        document.getElementById("passerror").innerHTML+="ít nhất 6 ký tự, ";  
        form.password.focus();
        check=true;
      }
      if(form.password.value == form.username.value) {
        document.getElementById("passerror").innerHTML+="khác tên đăng nhập, ";  
        form.password.focus();
        check=true;
      }
      re = /[0-9]/;
      if(!re.test(form.password.value)) {
        
        document.getElementById("passerror").innerHTML+="ít nhất 1 chữ số, ";  
        form.password.focus();
        check=true;
      }
      re = /[a-z]/;
      if(!re.test(form.password.value)) {
        document.getElementById("passerror").innerHTML+="ít nhất 1 chữ thường, ";  
        form.password.focus();
        check=true;
      }
      re = /[A-Z]/;
      if(!re.test(form.password.value)) {
        document.getElementById("passerror").innerHTML+="ít nhất 1 chữ hoa, ";  
        form.password.focus()
        check=true;
      }
      if(!check){
          document.getElementById("passerror").innerHTML="";
          if(form.password.value!=form.rePassword.value){
              document.getElementById("repasserror").innerHTML="*Mật khẩu không trùng khớp!";  
              form.rePassword.focus();
              
          } else{
              document.getElementById("repasserror").innerHTML=""; 
              document.getElementById("btn-signup").style.display="block";
          }
      }
    } else {
      document.getElementById("passerror").innerHTML="*Mật khẩu đang trống!"; 
      form.password.focus();
    }
  }
</script>

<jsp:include page="footer.jsp"></jsp:include>
