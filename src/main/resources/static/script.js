// Wait for the document to be fully loaded
$(document).ready(function() {
    // Trigger the modal when the "Checkout" button is clicked
    $('.btn-success').on('click', function() {
        $('#checkoutModal').modal('show'); // Show the modal
    });
    // Close the modal when the "Close" button is clicked
    $('.btn-secondary').on('click', function() {
        $('#checkoutModal').modal('hide'); // Hide the modal
    });

   // Close the modal when the "btn-close" button is clicked
    $('.btn-close').on('click', function() {
        $('#checkoutModal').modal('hide'); // Hide the modal
    });
    $('.btn-primary').on('click', function() {
        $('#checkoutModal').modal('hide'); // Hide the modal
    });
});