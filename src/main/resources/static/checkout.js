    //Checks to see if any fields in the checkout modal is empty and prevents the user from checking out if so
    $(document).ready(function() {
        $('#firstName, #lastName, #address').on('input', function() {
            var firstName = $('#firstName').val();
            var lastName = $('#lastName').val();
            var address = $('#address').val();
            if (firstName.trim() === '' || lastName.trim() === '' || address.trim() === '') {
                $('#checkoutButton').prop('disabled', true);
            } else {
                $('#checkoutButton').prop('disabled', false);
            }
        });
    });