$(document).ready(function () {
    console.log(">>>>>>>>>>>>>>.SAMPLE LISTING JS CALLED");
    $.fn.dataTable.ext.errMode = 'none';
    //bindDateTimePicker();
    var table = jQuery("#sampleList").DataTable({
        dom: 'l<"table-data"t>ip',
        //"order": [[9, "desc"]],
        "bSort": true,
        "bFilter": false,
        "processing": true,
        "serverSide": true,
        "responsive": true,
        "ajax": {
            //"url": $("#fetchUsersUrl").val(),
            "url": "/fetchSampleList",
            "type": "post",
            data: alterDataTableRequestParameters
        },
        "columns": [
            {"data": "id"},
            {"data": "firstName"},
            {"data": "middleName"},
            {"data": "lastName"},
            {"data": "address"},
            {"data": "phoneNumber"}
        ]
    });

    $("#find").on("click", function () {
        searchUsers(table)
    });

    $(document).keypress(function (e) {
        if (e.keyCode == 13) {
            searchUsers(table);
        }
    });

    $(document).on("click", ".enableDisableUser", function (e) {
        e.preventDefault();
        $.ajax({
            url: $("#toggleEnableUserUrl").val(),
            data: {id: $(this).attr('userId')},
            success: function (data) {
                table.draw();
                showStatus(data)
            }
        })
    });

    $(document).on("click", ".lockUser", function (e) {
        e.preventDefault();
        $.ajax({
            url: $("#toggleLockUserUrl").val(),
            data: {id: $(this).attr('userId')},
            success: function (data) {
                table.draw();
                showStatus(data)
            }
        })
    });


    $("#create-csv").on("click", function () {
        var url = $("#create-csv").val();
        var paramObject = $('#userList').DataTable().ajax.params();
        var str = jQuery.param(paramObject);
        $.ajax({
            url: $("#fetchUsersUrl").val() + "?" + str + "&fileType=CSV",
            success: function (data) {
                var jsonContent = data.data;
                if (jsonContent == '')
                    return;
                JSONToCSVConvertor(jsonContent, "Users", true);
            }
        })


    });

    $(".user-select-all").on("change", function () {
        $("input:checkbox").prop('checked', $(this).prop("checked"));
    });

    $("#userListActions").on("change", function (e) {
        changeStatus($(this).find(":selected").data("url"));
        table.draw();
    });


    $("#reset-filter-fields").on("click", function () {
        $("input[type=text]").val("");
        $('select').prop('selectedIndex', 0);
        location.reload();
    });


    function changeStatus(url) {
        var count = 0;
        var jForm = new FormData();
        //jForm.append(changedValue, $selectBox.val());
        $("input:checkbox.user-checkbox").each(function (i) {
            if (this.checked == true) {
                jForm.append("userId", $(this).attr("userId"));
                count = count + 1
            }
        });

        if (count > 0) {
            $.ajax({
                url: url,
                type: 'POST',
                data: jForm,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (data) {
                    showStatus(data);
                    $('#userListActions :selected').attr("selected", null);
                }
            });
        } else {
            showErrorMsg("Please select atleast one user.")
        }
        $(".user-select-all").attr("checked", false);
    }

    function searchUsers(table) {
        var from = $("#fromDate").val();
        var to = $("#toDate").val();
        if (from != "" && to != "" && to < from) {
            showErrorMsg("From date should be less than to date")
        } else {
            table.ajax.reload();
        }
    }

    function alterDataTableRequestParameters(data) {
        var dataToSend = {};
        dataToSend.sortColumn = data.columns[data.order[0].column].data;
        console.log(dataToSend.sortColumn);
        dataToSend.order = data.order[0].dir;
        console.log(dataToSend.order);
        dataToSend.max = data.length;
        dataToSend.offset = data.start;

        var table = $('#sampleList').DataTable();
        var info = table.page.info();
        dataToSend.pageIndex = info.page;

        dataToSend.firstName = "nitin";
        dataToSend.middleName = "kumar";
        dataToSend.lastName = "Singh";
        dataToSend.phoneNumber = "9953801744";
        dataToSend.address = "shahdara";
        return dataToSend
    }

    var dataTableColumnDefinitions = [
        {
            "render": function (data, type, row) {
                return data;
            },
            orderable: false,
            "targets": 7
        },
        {
            "render": function (data, type, row) {
                return data;
            },
            orderable: false,
            "targets": 29
        }
    ];


    var dataTableAJAXOptions = {
        "url": $("#fetchUserURL").val(),
        "method": "GET",
        "data": alterDataTableRequestParameters
    };

    $('#userList').wrap('<div class="table-responsive table-wrapper"></div>');
});
