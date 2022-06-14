$(document).ready(function() {
    $(window).scroll(function() {
        if($(this).scrollTop() > 40) {
            $('#scrollToTop').fadeIn();
        } else {
            $('#scrollToTop').fadeOut();
        }
    });
    $("#scrollToTop").click(function() {
        $('html, body').animate({scrollTop : 0}, 800);
    });
});


    window.addEventListener("scroll", function() {
    var header = document.querySelector("header");
    header.classList.toggle("sticky", window.scrollY > 0);
});


        $('#magic').on('click',function(event) {
            event.preventDefault();
            swal({
                title: "Confirmation? ",
                text: "Are you sure about deleting this record?",
                type: 'warning',
                showCancelButton: true,
                confirmButtonClass: "btn-danger",
                confirmButtonText: 'Yes, please!',
                cancelButtonText: "No, please don't!",
                closeOnConfirm: false,
                closeOnCancel: false
            }).then((result) => {
                if(result.isConfirmed) {
                    $(".form-control").closest('form').submit();
                }
            }
           );
        });


$(document).ready(function() {
    $("#magic").click(function() {
        swal({
                title: "Confirmation? ",
                text: "Are you sure about deleting this record?",
                type: 'warning',
                showCancelButton: true,
                confirmButtonClass: "btn-danger",
                confirmButtonText: 'Yes, please!',
                cancelButtonText: "No, please don't!",
                closeOnConfirm: false,
                closeOnCancel: false
            },
            function(isConfirm) {
                if(isConfirm) {
                    swal("Deleted!","Data has been successfully deleted.", "success")
                } else {
                    swal("Cancelled", "Your data is safe now", "error");

                }
            });
    });
});