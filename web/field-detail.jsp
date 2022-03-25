
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="header.jsp"></jsp:include>

    <section class="ftco-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 mb-5 ftco-animate">
                    <a href="${sf.image}" class="image-popup"><img src="${sf.image}" class="img-detail" alt="Colorlib Template"></a>
            </div>
            <input id="id-SF" type="hidden" value="${sf.id}">
            <div class="col-lg-6 product-details pl-md-5 ftco-animate">

                <a><span class="icon-calendar"></span> April 09, 2019</a>

                <h3>${sf.nameServer}</h3>
                <p>${sf.county}</p>             
                <hr>
                <p><img src="images/phone.png" style="width: 20px;margin-right: 5px"> ${sf.phone}</p>             
                <p><img src="images/email.png" style="width: 20px;margin-right: 5px"> ${sf.email}</p>              
                <p><img src="images/address.png" style="width: 20px;margin-right: 5px"> ${sf.address}</p>               
            </div>
        </div>
        <hr>
        <section class="field-booking">
            <div class="container">
                <h3>Những Sân Đang Có Tại Trung Tâm</h3>
                <c:forEach items="${listF}" var="f">               
                    <h5 style="font-size: 18px">Giá Thuê Sân ${f.nameField}: ${f.typeField} Người</h5>
                    <hr>
                    <div class="row" >
                        <div class="col-md-10 row " >
                            <c:forEach items="${listPT}" var="pt">
                                <c:if test="${pt.idField == f.id}">
                                    <div class="col-md-9">
                                        <a>${pt.timeStart} - ${pt.timeEnd}</a>
                                    </div>
                                    <div class="col-md-3">
                                        <a style="color: green"><img src="images/prices.png" style="width: 20px;margin-right: 5px">${pt.price} VND/1 Giờ</a>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                        <div class="col-md-2">
                            <button type="button" onclick="bookingTab(${f.id})" id="button-book-${f.id}" class="btn btn-outline-success">Đặt Sân</button>
                        </div>
                    </div>
                    <div class="row booking-time" id="booking-tab-${f.id}">
                        <div class="col-md-5">
                            <p>Thời Gian:</p>
                            <input type="date" id="date-field-${f.id}" name="date-field" >
                            <button onclick="findTimeByDay(${f.id})" class="btn btn-outline-success">Tìm giờ trống</button>
                        </div>
                        <div class="col-md-6">
                            <p>Khung Giờ Hiện Có:</p>                        
                            <div class="tagcloud" id="time-field-${f.id}" >                                
                            </div>                            
                        </div>
                        <div class="col-md-1">
                            <a onclick="closeBookingTab(${f.id})"><img src="images/close.png" style="width: 30px;margin-left:  10px"></a>
                        </div>  
                    </div>
                    <hr>                  
                </c:forEach>
            </div>
        </section>
        <hr>
        <h4><img src="images/map.png" style="width: 30px;margin-right: 10px">Vị trí trên Google Map</h4>
        <br>
        <center>
            ${sf.linkmaps}
        </center>
        <!--Danh sach binh luan-->

        <div class="pt-5 mt-5">
            <h3 class="mb-5">Bình Luận</h3>
            <ul id="cmt-list" class="comment-list">

            </ul>
        </div>

        <div id="comment-form-0" class="comment-form-wrap pt-5">
            <c:if test="${sessionScope.ACCOUNT != null}">                                                  
                <button onclick="loadCommentForm(0)" class="btn btn-primary">Viết Bình Luận</button>
            </c:if>
        </div>


    </div>
</section>

<jsp:include page="footer.jsp"></jsp:include>
<!-- tab tim san -->
<script>
    function bookingTab(idF) {
        var today = new Date();
        var dd = String(today.getDate()).padStart(2, '0');
        var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
        var yyyy = today.getFullYear();
        today = yyyy + '-' + mm + '-' + dd;
        document.getElementById('date-field-' + idF).value = today;
        document.getElementById('date-field-' + idF).min = today;
        var tab = document.getElementById('booking-tab-' + idF);
        var button = document.getElementById('button-book-' + idF);
        tab.style.display = 'flex';
        button.style.display = 'none';
    }
    function findTimeByDay(idField) {
        var dayF = document.getElementById('date-field-' + idField).value;
        $.ajax({
            url: "/findTimeFieldByAjax",
            type: "get",
            data: {
                idF: idField,
                day: dayF
            },
            success: function (data) {
                var row = document.getElementById('time-field-' + idField);
                row.innerHTML = data;
            }
        });


    }

    function loadComment() {
        var id = document.getElementById('id-SF').value;
        $.ajax({
            url: "/load-comment",
            type: "get",
            data: {
                idSF: id
            },
            success: function (data) {
                var row = document.getElementById('cmt-list');
                row.innerHTML = data;
            }
        });


    }
    function loadCommentForm(idC) {
        console.log(idC);
        $.ajax({
            url: "/load-comment-form",
            type: "get",
            data: {
                id: idC
            },
            success: function (data) {
                var row = document.getElementById('comment-form-' + idC);
                row.innerHTML = data;
            }
        });


    }

    function addComment(idC) {

        var idServer = document.getElementById('id-SF').value;
        var content = document.getElementById("message-" + idC).value;
        $.ajax({
            url: "/add-comment",
            type: "post",
            data: {
                id: idC,
                idSF: idServer,
                cmt: content
            },
            success: function (data) {
                if (idC !== 0) {
                    if (data === 'done') {
                        document.getElementById('form-cmt-' + idC).style.display = "none";
                        loadReply(idC);
                        var row = document.getElementById('comment-form-'+idC);
                        row.innerHTML = '<button onclick="loadCommentForm('+idC+')" class="btn btn-primary">Viết Bình Luận</button>';
                    } else {
                        var row = document.getElementById('result-' + idC);
                        row.innerHTML = data;
                    }
                } else {
                    if (data === 'done') {
                        loadComment();
                        document.getElementById('form-cmt-0').style.display = "none";
                        var row = document.getElementById('comment-form-0');
                        row.innerHTML = '<button onclick="loadCommentForm(0)" class="btn btn-primary">Viết Bình Luận</button>';
                    } else {
                        var row = document.getElementById('result-0');
                        
                    }
                }
            }
        });
    }


    function loadReply(idComment) {

        $.ajax({
            url: "/load-reply-comment",
            type: "get",
            data: {
                idC: idComment
            },
            success: function (data) {
                var row = document.getElementById('comment-' + idComment);
                row.innerHTML = data;
                document.getElementById('btn-show' + idComment).style.display = "none";

            }
        });


    }
    function closeBookingTab(idF) {
        document.getElementById('booking-tab-' + idF).style.display = 'none';
        document.getElementById('button-book-' + idF).style.display = 'block';
    }
    loadComment();

</script>
<!-- logic cho tab tim san -->
