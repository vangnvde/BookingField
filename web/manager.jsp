<%-- 
    Document   : manager
    Created on : 19-02-2022, 13:11:16
    Author     : Vang Nguyen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="header.jsp"></jsp:include>
    <section class="ftco-section">
        <div class="row">
            <div class="sidebar-box ftco-animate col-md-2">
                <h3 class="heading">Tác Vụ</h3>
                <ul class="categories">
                    <li><button class="none-background-btn" onclick="loadWaitingReceipt()" >Yêu cầu đặt sân</button></li>
                    <li><button class="none-background-btn" onclick="historyReceipt()" >Lịch sử đặt sân </button></li>
                    <li><button class="none-background-btn" onclick="loadServerField()">Trung tâm sân</button></li>
                    <li><button class="none-background-btn" onclick="loadField()">Sân trong trung tâm</button></li>
                </ul>
            </div>
            <div class="col-md-9 " id="manager-tab">

            </div>
            <div class="col-md-4" id="add-new-field" style="display: none">        
                <div class="cart-detail cart-total ">
                    <div class="row">
                        <h3 class="billing-heading mb-4 col-11">Thêm Sân Mới</h3>  
                        <a class="col-1" onclick="addFieldForm()"><img src="images/close.png" style="width: 25px;"></a>
                    </div>       
                    <p class="d-flex">
                        <span>Tên Sân(mã sân): </span>
                        <input id="name-new-field" type="text" class="form-control">
                    <div id="error-input">
                        
                    </div>
                    </p>
                    <p class="d-flex">
                        <span>Loại Sân </span>
                        <select id="type-new-field" class="form-control">
                            <option value="5">5 Người</option>
                            <option value="7">7 Người</option>
                            <option value="11">11 Người</option>		                   
                        </select>
                    </p>
                    <hr>                  
                    <center>
                        <button style="width: 20%" class="btn btn-primary" onclick="addNewField()">Thêm</button>
                    </center>
                </div>
            </div>
        </div>      
    </div>
</section>


<jsp:include page="footer.jsp"></jsp:include>
<script src="./js/manager.js"></script>