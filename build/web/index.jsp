
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="header.jsp"></jsp:include>
    <section id="home-section" class="hero">
        <div class="home-slider owl-carousel">
            <div class="slider-item" style="background-image: url(images/banner1.jpg);">
                <div class="overlay"></div>
                <div class="container">
                    <div class="row slider-text justify-content-center align-items-center" data-scrollax-parent="true">

                        <div class="col-md-12 ftco-animate text-center">
                            <h1 class="mb-2">ĐẶT SÂN CỎ MINI TẠI ĐÀ NẴNG</h1>

                            <p><a href="filter-controll" class="btn btn-primary">Xem Chi Tiết</a></p>
                        </div>

                    </div>
                </div>
            </div>

            <div class="slider-item" style="background-image: url(./images/banner2.jpg);">
                <div class="overlay"></div>
                <div class="container">
                    <div class="row slider-text justify-content-center align-items-center" data-scrollax-parent="true">

                        <div class="col-sm-12 ftco-animate text-center">
                            <h1 class="mb-2">SÂN CỎ TẠI ĐÀ NẴNG</h1>
                            <p><a href="filter-controll" class="btn btn-primary">Xem Chi Tiết</a></p>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </section>


    <section class="ftco-section">
        <div class="container">
            <div class="row justify-content-center mb-3 pb-3">
                <div class="col-md-12 heading-section text-center ftco-animate">
                    <span class="subheading">Sân Cỏ Mini Tại Đà Nẵng</span>
                    <h2 class="mb-4">Trung Tâm</h2>              
                </div>
            </div>   		
        </div>
        <div class="container">
            <div class="row">
            <c:forEach items="${listSF}" var="sf"> 
                <div class="col-md-6 col-lg-3 ftco-animate" st >
                    <div class="product" >
                        <a href="LoadDetailServerField?idSF=${sf.id}" class="img-prod" style="border-radius: 10px"><img class="img-fluid" src="${sf.image}" alt="Colorlib Template"style="height: 180px; border-radius: 10px; width: 100%;" />
                        </a>
                        <div class="text py-3 pb-4 px-3 text-center">
                            <h6><a href="LoadDetailServerField?idSF=${sf.id}" >${sf.nameServer}</a></h6>
                            <p><img src="images/address.png" style="width: 15px;margin-right: 5px">${sf.county}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"></jsp:include>
