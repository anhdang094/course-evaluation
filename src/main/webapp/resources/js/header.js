$(document).ready(function(){
    $('#header_menu a[href^="/EvaluationSource/' + location.pathname.split("/")[2]  + '"]').addClass('active');
    if ((location.pathname.split("/")[2] === "create-question") || (location.pathname.split("/")[2] === "edit-question")){
        $('#header_menu a[name^="dropdown-question"]').addClass('active');
    }
    $('#accordion1 li').click(function() {
        $(this).siblings('li').removeClass('active');
        $(this).addClass('active');
    });
}
);
