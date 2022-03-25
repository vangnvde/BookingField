<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>



<jsp:include page="header.jsp"></jsp:include>

    <!DOCTYPE html>
    <section class="ftco-section">
        <div class="container">
            <h2 class="">Người Dùng Trong Hệ Thống</h2>
            <table class="table">
                <thead class="thead-primary">
                    <tr>
                        <th>ID</th>
                        <th>Tên Đăng Nhập</th>
                        <th>Mật Khẩu</th>
                        <th>Tên Đầy Đủ</th>
                        <th>Email</th>
                        <th>Sdt</th>
                        <!--                    <th>Image</th>-->
                        <th>Là Quản Lý</th>
                        <th>Trạng Thái</th>

                    </tr>
                </thead>
            <c:forEach items="${listS}" var="x">
                <tr>
                    <td>${x.id}</td>
                    <td>${x.username}</td>
                    <td>${x.password}</td>
                    <td>${x.fullname}</td>
                    <td>${x.email}</td>
                    <td>${x.phone}</td>
        <!--                    <td>${x.image}</td>-->
                    <td class="total">
                        <label class="switch">
                            <input type="checkbox" id="ismanager-${x.id}" onclick="changeIsManagerUser(${x.id})" ${x.isManager == true?"checked":""}>
                            <span class="slider round"></span>
                        </label>
                    </td>
                    <td class="total">
                        <label class="switch">
                            <input type="checkbox" id="isactivate-${x.id}" onclick="changeActivateUser(${x.id})" ${x.activate == true?"checked":""}>
                            <span class="slider round"></span>
                        </label>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </div>
</section>





<jsp:include page="footer.jsp"></jsp:include>
<script>
    function changeActivateUser(idU) {

    var check = document.getElementById('isactivate-' + idU);
    var st = 0
    if (check.checked == true) {
        st = 1;
    }
    $.ajax({
        url: "/admin-load-user",
        type: "post",
        
        data: {
            idUser: idU,
            status: st,
            which: 'a'
        },
        success: function (data) {

        }
    });
}

 function changeIsManagerUser(idU) {

    var check = document.getElementById('ismanager-' + idU);
    var st = 0
    if (check.checked == true) {
        st = 1;
    }
    $.ajax({
        url: "/admin-load-user",
        type: "post",
        data: {
            idUser: idU,
            status: st,
            which: 'm'
        },
        success: function (data) {

        }
    });
}
</script>