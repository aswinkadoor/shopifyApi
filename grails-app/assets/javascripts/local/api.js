$(function () {
    $('#dtBasicExample').DataTable({
        "paging": 'simple_numbers' // false to disable pagination (or any other option)
    });
    $('.dataTables_length').addClass('bs-select');
    $('#syncButton').on('click', function () {
        console.log('111111111111111111111111111111111')
        $.ajax({
            url: '../api/index',
            method:"POST",
            dataType:'json',
            data:{actionType:'sync'},
            success: function(data){
                if(data['success'] === true){
                    alert(data['message'])
                }
            }
        });
    })

})
