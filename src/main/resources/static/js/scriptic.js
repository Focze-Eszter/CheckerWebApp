
var airline_placeholders = ['Search by name', 'Search by country', 'Search by icao'];
var airport_placeholders = ['Search by name', 'Search by city', 'Search by country'];
var airlines_length = airline_placeholders.length;
var airports_length = airport_placeholders.length;
var count = 0;
var search_airline = document.querySelector(".search-airline");
var search_airport = document.querySelector(".search-airport");

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

	function changeAirlinePlaceholder() {

                if (count==airlines_length) {
                    count = 0;
                }
                search_airline.setAttribute('placeholder', airline_placeholders[count]);
                count++;
    	}
    	setInterval(changeAirlinePlaceholder, 1800);


    	function changeAirportPlaceholder() {

                    if (count==airports_length) {
                        count = 0;
                    }
                    search_airport.setAttribute('placeholder', airport_placeholders[count]);
                    count++;
        	}
        	setInterval(changeAirportPlaceholder, 1800);


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