

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="header.jsp"></jsp:include>
<c:if test="${sessionScope.ACCOUNT != null}">
    <section class="ftco-section">
        <div class="row" >
            <div class="col-md-6" > 
                <div style="border: 1px darkgrey solid; border-radius: 10px; width: 100%; padding: 20px">
                    <h1 class="text-center">Đổi mật khẩu </h1>
                    <p class="text-center text-danger"><strong>${MESSAGE}</strong></p>
                    <p class="text-center text-success"><strong>${SUCCESS}</strong></p>
                    <form action="changePass" method="post">
                        <div class="form-group">
                            <label>Tên đăng nhập <strong style="color: red;">*</strong> : </label> 
                            <input type="text" id="userName" class="form-control" name="txtUserName" disabled="true" readonly="true" value="${sessionScope.ACCOUNT.username}">
                        </div>     
                        <div class="form-group">
                            <label>Mật khẩu cũ <strong style="color: red;">*</strong> : </label>
                            <input type="password" id="passOld" class="form-control" name="txtPassOld" value="">
                        </div>
                        <div class="form-group">
                            <label>Mật khẩu mới <strong style="color: red;">*</strong> : </label>
                            <input type="password" id="passNew" class="form-control" name="txtPassNew" value=""> 
                        </div>
                        <div class="form-group">
                            <label>Nhập lại mật khẩu mới <strong style="color: red;">*</strong> : </label>
                            <input type="password" id="passNewAgain" class="form-control" name="txtPassNewAgain" value=""> 
                        </div>
                        <center>
                            <button type="submit" style="width: 30%" class="btn btn-primary">Cập nhật</button>
                        </center>
                    </form>
                </div>
            </div>
        </div>
    </section>
</c:if>
<jsp:include page="footer.jsp"></jsp:include>
<script>
    $(function(){
        var userName = $("#userName").val()
        if(typeof userName === "undefined"){
            window.location.href = "login";
        }
    })
</script>