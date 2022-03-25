function loadWaitingReceipt() {
    $.ajax({
        url: "/manager-waiting-receipt",
        type: "get",
        data: {
        },
        success: function (data) {
            var row = document.getElementById('manager-tab');
            row.innerHTML = data;
        }
    });
}


function confirmReceipt(idReceipt) {

    $.ajax({
        url: "/manager-confirm-receipt",
        type: "get",
        data: {
            idR: idReceipt
        },
        success: function (data) {
            document.getElementById('receipt-' + idReceipt).style.display = "none";
        }
    });

}

function refuseReceipt(idReceipt) {
    $.ajax({
        url: "/manager-refuse-receipt",
        type: "get",
        data: {
            idR: idReceipt
        },
        success: function (data) {
            document.getElementById('receipt-' + idReceipt).style.display = "none";
        }
    });
}

function historyReceipt() {
    $.ajax({
        url: "/manager-history-receipt",
        type: "get",
        data: {
        },
        success: function (data) {
            var row = document.getElementById('manager-tab');
            row.innerHTML = data;
        }
    });
}

function loadServerField() {
    $.ajax({
        url: "/manager-server-field",
        type: "get",
        data: {
        },
        success: function (data) {
            var row = document.getElementById('manager-tab');
            row.innerHTML = data;
        }
    });
}

function changeServerField() {
    try {
        var imageChange = document.getElementById('linkImage').value;
        var nameChange = document.getElementById('name').value;
        var emailChange = document.getElementById('email').value;
        var phoneChange = document.getElementById('phone').value;
        var countyChange = document.getElementById('county').value;
        var addressChange = document.getElementById('address').value;
        var check = document.getElementById('status');
        var st = 0;
        if (check.checked === true) {
            st = 1;
        }
        window.console.log(st);
        // var mapChange = document.getElementById('ggmap').value();                    
        $.ajax({
            url: "/manager-server-field",
            type: "post",
            data: {
                image: imageChange,
                name: nameChange,
                email: emailChange,
                phone: phoneChange,
                county: countyChange,
                address: addressChange,
                status: st
            },
            success: function (data) {
                var row = document.getElementById('error-input');
                row.innerHTML = data;
            }
        });
    } catch (e) {
        var row = document.getElementById('error-input');
        row.innerHTML = "<p class=\"text-center text-danger\">Không được bỏ trống mục nào!</p>";
    }
}

function loadField() {
    $.ajax({
        url: "/manager-field-controll",
        type: "get",
        data: {
        },
        success: function (data) {
            var row = document.getElementById('manager-tab');
            row.innerHTML = data;
        }
    });
}

function managerPriceTime(idField) {
    $.ajax({
        url: "/manager-price-time",
        type: "get",
        data: {
            idF: idField
        },
        success: function (data) {
            var row = document.getElementById('manager-tab');
            row.innerHTML = data;
        }
    });
}


function deletePriceTime(idPriceTime, idField) {
    $.ajax({
        url: "/manager-delete-priceTime",
        type: "get",
        data: {
            idPT: idPriceTime
        },
        success: function (data) {
            if (data === 'done') {
                console.log(idField+"------");
                managerPriceTime(idField);
            } else {
                var row = document.getElementById('add-message');
                row.innerHTML = data;
            }

        }
    });
}

function changeStatusField(idField) {

    var check = document.getElementById('status-' + idField);
    var st = 0
    if (check.checked == true) {
        st = 1;
    }
    $.ajax({
        url: "/manager-field-controll",
        type: "post",
        data: {
            idF: idField,
            status: st
        },
        success: function (data) {

        }
    });
}
function addFieldForm() {
    if (document.getElementById("add").style.display == "none") {
        document.getElementById("add").style.display = "block";
        document.getElementById("add-new-field").style.display = "none";
    } else {
        document.getElementById("add").style.display = "none";
        document.getElementById("add-new-field").style.display = "block";
    }
}

function addTimeForm() {
    if (document.getElementById("add").style.display == "none") {
        document.getElementById("add").style.display = "block";
        document.getElementById("add-time-form").style.display = "none";
    } else {
        document.getElementById("add").style.display = "none";
        document.getElementById("add-time-form").style.display = "";
    }
}
function addPriceTime(idField) {

    var start = document.getElementById('add-time-start').value;
    var end = document.getElementById('add-time-end').value;
    var addPrice = document.getElementById('add-price').value;

    $.ajax({
        url: "/manager-add-time",
        type: "get",
        data: {
            idF: idField,
            timeStart: start,
            timeEnd: end,
            price: addPrice
        },
        success: function (data) {
            if (data == 'done') {
                managerPriceTime(idField);
            } else {
                document.getElementById('add-message').innerHTML = data;
            }


        }
    });
}

function updatePriceTime(idPriceTime, idField) {

    var start = document.getElementById('time-start-' + idPriceTime).value;
    var end = document.getElementById('time-end-' + idPriceTime).value;
    var addPrice = document.getElementById('price-' + idPriceTime).value;

    $.ajax({
        url: "/manager-price-time",
        type: "post",
        data: {
            idF: idField,
            idPT: idPriceTime,
            timeStart: start,
            timeEnd: end,
            price: addPrice
        },
        success: function (data) {

            document.getElementById('add-message').innerHTML = data;



        }
    });
}

function addNewField() {

    var nameF = document.getElementById('name-new-field').value;
    var typeF = document.getElementById('type-new-field').value;


    $.ajax({
        url: "/manager-add-field",
        type: "get",
        data: {
            name: nameF,
            type: typeF
        },
        success: function (data) {
            if (data == 'done') {
                loadField();
                addFieldForm()
            } else {
                document.getElementById('error-input').innerHTML = data;

            }
        }
    });
}
loadWaitingReceipt();


