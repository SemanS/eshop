(function ($) {

    $(function () {

        $('.span')
            .popup({
                inline: true
            })
        ;

        $('.ui.accordion')
            .accordion()
        ;

        $('.accordion')
            .accordion({
                selector: {
                    trigger: '.title .icon'
                }
            })
        ;
        $('.cookie.nag')
          .nag('clear')
        ;

        /*$('.special.cards .image').dimmer({
         on: 'hover'
         });

         $('.ui.slider').slider('behavior', arguments);


         $('.ui.sticky')
         .sticky({
         context: '#railShoppingCartId'
         })
         ;*/

        /*$('.context.example .ui.sidebar')
         .sidebar({
         context: $('.context.example .bottom.segment')
         })
         .sidebar('attach events', '.context.example .menu .item')
         ;*/

        $('a.sidebar-toggle').click(function () {
            $('#sidebar').sidebar('toggle')
        })

    }); // end of document ready
})(jQuery); // end of jQuery name space