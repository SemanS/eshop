(function ($) {


    $(function () {

        $('.special.cards .image').dimmer({
            on: 'hover'
        });

        $('.ui.slider').slider('behavior', arguments);

        $('.ui.accordion')
            .accordion()
        ;
        $('.ui.sticky')
            .sticky({
                context: '#railShoppingCartId'
            })
        ;

    }); // end of document ready
})(jQuery); // end of jQuery name space