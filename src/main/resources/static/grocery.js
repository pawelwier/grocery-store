$("document").ready(function() {

    $(".change_price").click(function () {
        $(this).text($(this).text() == "Schowaj" ? "Zmień" : "Schowaj");
       $(this).next(".change_price_form").toggleClass("show_hide");
    });

    $(".change_quantity").click(function () {
        $(this).text($(this).text() == "Schowaj" ? "Dodaj" : "Schowaj");
        $(this).next(".change_quantity_form").toggleClass("show_hide");
    });

    var new_message_content = $(".new_message").text();

    if (new_message_content == "Dodano produkt") {
        $(".new_message").css("color", "green");
    }

});