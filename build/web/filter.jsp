<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!--<div class="py-1 bg-primary">
    <div class="container">
        <div class="row no-gutters d-flex align-items-start align-items-center px-md-0">
            <div class="col-lg-12 d-block">
                <div class="row d-flex">
                    <div class="col-md pr-4 d-flex topper align-items-center">
                        <div class="icon mr-2 d-flex justify-content-center align-items-center"><span class="icon-phone2"></span></div>
                        <span class="text">+ 1235 2355 98</span>
                    </div>
                    <div class="col-md pr-4 d-flex topper align-items-center">
                        <div class="icon mr-2 d-flex justify-content-center align-items-center"><span class="icon-paper-plane"></span></div>
                        <span class="text">youremail@email.com</span>
                    </div>
                    <div class="col-md-5 pr-4 d-flex topper align-items-center text-lg-right">
                        <span class="text">3-5 Business days delivery &amp; Free Returns</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>-->
<jsp:include page="header.jsp"></jsp:include>
<!-- END nav -->

<div class="hero-wrap hero-bread" style="background-image: url('images/banner2.jpg');">
    <div class="container">
        <div class="row no-gutters slider-text align-items-center justify-content-center">
            <div class="col-md-9 ftco-animate text-center">
                <p class="breadcrumbs"><span class="mr-2"><a href="home">Home</a></span> <span>Blog</span></p>
                <h1 class="mb-0 bread">Tìm Sân Bóng</h1>
            </div>
        </div>
    </div>
</div>

<section class="ftco-section ftco-degree-bg">
    <div class="container">
        <div class="row">
            <h2>Tìm Sân Bóng Tại Đà Nẵng</h2>
            <div class="col-lg-8 ftco-animate">
                <c:forEach items="${listSF}" var="sf">
                    <div class="row">
                        <div class="col-md-12 d-flex ftco-animate">
                            <div class="blog-entry align-self-stretch d-md-flex">
                                <a href="LoadDetailServerField?idSF=${sf.id}" class="block-20" style="background-image: url('${sf.image}');">
                                </a>
                                <div class="text d-block pl-md-4">
                                    <div class="meta mb-3">
                                        <div><a>${sf.phone},</a></div>
                                        <div><a>${sf.manager}</a></div>
                                        
                                    </div>
                                    <h3 class="heading"><a href="#">${sf.nameServer}</a></h3>
                                    <p><img src="images/address.png" style="width: 15px;margin-right: 5px">${sf.county}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                </div>
            <div class="col-lg-4 sidebar ftco-animate">
                <div class="sidebar-box">
                    <form action="filter-controll" class="search-form" method="GET" enctype="multipart/form-data">
                        <div class="form-group row">     
                            <div class="col-10">
                            <input type="text" id="name" name="name" value="${name}" class="form-control" placeholder="Search...">
                           
                            </div>
                            <div class="col-2">
                                <button class="btn btn-primary" type="submit" >Tìm</button>
                            </div>
                        </div>
                    
                        <div class="form-group">
                            <select name="county" id="changeCounty" class="form-control">
                                <option value="0">--Tất cả--</option>
                                <c:forEach items="${listCT}" var="ct">
                                    <option ${ct.id == county ? "selected" : ""} value="${ct.id}">${ct.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </form>
                </div>
               

                
                 
                   
               
                
            </div>

        </div>
    </div>
</section> <!-- .section -->

<jsp:include page="footer.jsp"></jsp:include>
<script>
    $(function(){
        $("#changeCounty").change(function (){
            var county = $(this).val();
            var name = $("#name").val();
            window.location.href = 'filter-controll?name='+name+'&county='+county;
        })
    })
</script>