<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="header.jsp"></jsp:include>



    <section class="ftco-section">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-xl-7 ftco-animate">
                    <h3 class="mb-4 billing-heading">Xác Nhận Thông Tin Đặt Sân</h3>
                    <div class="row align-items-end">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="firstname">Họ Và Tên</label>
                                <input type="text" id="fullname" value="${sessionScope.ACCOUNT.fullname}" class="form-control"  required="">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="phone">Số Điện Thoại</label>
                            <input type="text" id="phone" value="${sessionScope.ACCOUNT.phone}" class="form-control" required="">
                        </div>
                    </div>
                    <div class="w-100"></div>
                    <div class="col-md-12">
                        <div class="form-group">
                            <label for="emailaddress">Email Address</label>
                            <input type="text" id="email" value="${sessionScope.ACCOUNT.email}" class="form-control"  required="">
                        </div>
                    </div>  

                    <button id="btn-info" onclick="changeInfo()" class="btn btn-primary py-3 px-4" >Thay Đổi</button>

                </div>
                <div id="success-change">

                </div>   

            </div>
            <div class="col-xl-5">
                <div class="row mt-5 pt-3">
                    <div class="col-md-12 d-flex mb-5">
                        <div class="cart-detail cart-total p-3 p-md-4">
                            <h3 class="billing-heading mb-4">Thông Tin Sân</h3>
                            <p class="d-flex">
                                <span>Trung Tâm</span>
                                <span>${Receipt.nameServerField}</span>

                            </p>
                            <p class="d-flex">
                                <span>Mã Sân</span>
                                <span>${Receipt.nameField}</span>

                            </p>
                            <p class="d-flex">
                                <span>Ngày</span>
                                <span>${Receipt.date}</span>
                            </p>
                            <p class="d-flex">
                                <span>Thời Gian</span>
                                <span>${Receipt.timeStart}-${Receipt.timeEnd}</span>
                            </p>
                            <hr>
                            <p class="d-flex total-price">
                                <span>Giá</span>
                                <span>${Receipt.price} VND</span>
                            </p>
                        </div>
                    </div>

                </div>
            </div> <!-- .col-md-8 -->
        </div>
    </div>
    <center >
        <div class="col-md-5" id="confirm">     
            <button onclick="comfirmBooking(${idTF})"  id="btn-confirm" class="btn btn-primary py-3 px-4" style="display: block">Xác Nhận Đặt Sân</button>
            <p><a href="home" id="btn-home"  class="btn btn-primary py-3 px-4" style="display: none">Trở Về Trang Chủ</a></p>
        </div>
    </center>

</section> <!-- .section -->


<script>
    function comfirmBooking(idTF) {

        $.ajax({
            url: "/confirmBookingInfo",
            type: "post",
            data: {
                id: idTF
            },
            success: function (data) {
                document.getElementById('btn-home').style.display = "block";
                document.getElementById('btn-confirm').style.display = "none";
                document.getElementById('btn-info').style.display = "none";
                var alert = document.getElementById('confirm');
                alert.innerHTML += data;

            }
        });

    }

    function changeInfo() {
        var changeName = document.getElementById('fullname').value;
        var changePhone = document.getElementById('phone').value;
        var changeEmail = document.getElementById('email').value;
        $.ajax({
            url: "/confirmChangeUserInfo",
            type: "post",
            data: {
                fullname: changeName,
                phone: changePhone,
                email: changeEmail
            },
            success: function (data) {
                var alert = document.getElementById('success-change');
                alert.innerHTML = data;

            }
        });
    }
</script>
<jsp:include page="footer.jsp"></jsp:include>