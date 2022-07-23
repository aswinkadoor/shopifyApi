$(function () {

    $('#fromDate').val('')
    $('#toDate').val('')
    var fromDate,toDate
    loadData(fromDate, toDate)

    $('#searchBtn').on('click', function () {
        fromDate = $('#fromDate').val()
        toDate = $('#toDate').val()
        if(fromDate||toDate){
            loadData(fromDate, toDate)
        }else{
            alert('Select date ranges before applying filter')
        }
    });


    $('#dataTable').DataTable({
        "paging": 'simple_numbers' // false to disable pagination (or any other option)
    });
    $('.dataTables_length').addClass('bs-select');


    $('#syncButton').on('click', function () {
        $.ajax({
            url: '../api/index',
            method:"POST",
            dataType:'json',
            data:{actionType:'sync'},
            success: function(data){
                if(data['success'] === true){
                    alert(data['message'])
                    loadData(fromDate,toDate)
                }
            }
        });
    })
});


function loadData(fromDate,toDate){
    $.ajax({
        url: '../api/index',
        method:"POST",
        // dataType:'json',
        data:{actionType:'loadData', fromDate:fromDate, toDate:toDate},
        success: function(data){
            $('#dataTemplate').html(data)
            alert("Data loaded")
        }
    });
}