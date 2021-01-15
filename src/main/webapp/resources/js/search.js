 $('#buttonsearch').click(function(){
        $('#formsearch').slideToggle( "fast",function(){
           $( '#content' ).toggleClass( "moremargin" );
        } );
        $('#searchbox').focus()
        $('.openclosesearch').toggle();
    });
  //social sharer anchors
  var url = window.location;
  var title = document.title;
  var mnsocial = document.getElementsByClassName('mn-social-bottom');
  mnsocial[0].href = 'https://www.facebook.com/sharer/sharer.php?u=' + url;
  mnsocial[1].href = 'https://twitter.com/home?status=' + url + ' ' + title;
  mnsocial[2].href = 'https://plus.google.com/share?url=' + url;
  mnsocial[3].href = 'https://www.facebook.com/sharer/sharer.php?u=' + url;
  mnsocial[4].href = 'https://twitter.com/home?status=' + url + ' ' + title;
  mnsocial[5].href = 'https://plus.google.com/share?url=' + url;
  mnsocial[6].href = 'https://www.facebook.com/sharer/sharer.php?u=' + url;
  mnsocial[7].href = 'https://twitter.com/home?status=' + url + ' ' + title;
  mnsocial[8].href = 'https://plus.google.com/share?url=' + url;