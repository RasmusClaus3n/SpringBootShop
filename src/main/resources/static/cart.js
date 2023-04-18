// Close and open checkout modal
$(document).ready(function() {

    $('.btn-success').on('click', function() {
        $('#checkoutModal').modal('show');
    });

    $('.btn-secondary').on('click', function() {
        $('#checkoutModal').modal('hide');
    });

    $('.btn-close').on('click', function() {
        $('#checkoutModal').modal('hide');
    });
    $('.btn-primary').on('click', function() {
        $('#checkoutModal').modal('hide');
    });
});
