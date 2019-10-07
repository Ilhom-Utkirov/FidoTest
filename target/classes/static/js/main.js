/*
*
* */



$(document).ready(function () {

    $('.nBtn, .table .eBtn').on('click',function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();

        if(text == "Edit") {
            $.get(href, function (Goods, status) {
                $('.myForm #id').val(Goods.id);
                $('.myForm #name').val(Goods.name);
                $('.myForm #cost').val(Goods.cost);
            });
            $('.myForm #exampleModal').modal();
        }else{
            $('.myForm #id').val('');
            $('.myForm #name').val('');
            $('.myForm #cost').val('');
            $('.myForm #exampleModal').modal();
        }
    });

    $('.table .delBtn').on('click',function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#myModal #delRef').attr('href',href);
        /*trigger model*/
        $('#myModal').modal();
    });
});