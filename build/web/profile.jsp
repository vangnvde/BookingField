<%-- 
    Document   : profile
    Created on : 26-01-2022, 11:24:58
    Author     : Vang Nguyen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="header.jsp"></jsp:include>
    <section class="ftco-section">

        <div class="row" >
            <div class="col-md-3" > 
                <div style="border: 1px darkgrey solid; border-radius: 10px; width: 100%; padding: 20px">
                <h1 class="text-center">Thông Tin Cá Nhân</h1>
                <form action="edituser" method="post">
                <center>
                    <div class="image">
                        <img style="width: 100px; border-radius: 20px" src="${sessionScope.ACCOUNT.image}" alt="">
                    </div>
                </center>
                <div class="form-group">
                    <label>Tên Đầy Đủ: </label> 
                    <input type="text" id="image" class="form-control" name="txtFullName" value="${sessionScope.ACCOUNT.fullname}">
                </div>     
                <div class="form-group">
                    <label>Email: </label>
                    <input type="text" id="author" class="form-control" name="txtEmail" value="${sessionScope.ACCOUNT.email}">
                </div>
                <div class="form-group">
                    <label>Số Điện Thoại: </label>
                    <input type="text" id="content" class="form-control" name="txtPhone" value="${sessionScope.ACCOUNT.phone}"> 
                </div>
                <div class="form-group">
                    <label>Ảnh: </label>
                    <input type="text" id="image" class="form-control" name="txtImage" value="${sessionScope.ACCOUNT.image}">
                </div>
                <center>
                    <button type="submit" style="width: 30%" class="btn btn-primary">Edit</button>
                </center>
                <p class="text-center text-danger"><strong>${MESSAGE}</strong></p>
            </form>
            </div>
        </div>
        <div class="col-md-7" id="history-receipt">

        </div>
    </div>
</section>
<jsp:include page="footer.jsp"></jsp:include>
<script>
    function loadWaitingReceipt() {
        $.ajax({
            url: "/load-history-receipt-user",
            type: "get",
            data: {
            },
            success: function (data) {
                var row = document.getElementById('history-receipt');
                row.innerHTML = data;
            }
        });
    }
    
     function cancerReceipt(idR) {
        $.ajax({
            url: "/load-history-receipt-user",
            type: "post",
            data: {
                idReceipt: idR
            },
            success: function (data) {
                loadWaitingReceipt();
            }
        });
    }
    loadWaitingReceipt();
</script>