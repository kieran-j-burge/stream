$(document).ready(function () {
    $('.log-out-btn').on('click', function () {
        var url = "/logout";

        $.post(url, function (data, status) {
            // $('#response-message')
            window.location.href = "/" ;
            // + url
        });

    })


    $('.add-club-btn').on('click', function () {
        var club = $('.club-name-input').val();
        var img = $('.club-img-input').val();
        alert(club);
        alert(img);
        url = "/admin/add-club/" + club + "/" + img;

        $.post(url, function (data, status) {
            // $('#response-message')
            // window.location.href = "/" ;
            alert("Club added")
            // + url
        });

    })

    $("#searchGame").on('keyup', function() {
        var searchText = $('#searchGame').val();
        var searchLen  = searchText.length;

        if (searchLen >= 1) {
            if (/^[a-zA-Z0-9- ]*$/.test(searchText) == false) {
                alert('Your search string contains illegal characters.');
                $('#searchGame').val("");
            }
            else {
                url = '/search/' + $('#searchGame').val();
                $("#resultsBlock").load(url);
            }
        }
        else{
            url ='/load_home';
            $("#resultsBlock").load(url);
        }
    });

    $("#add-game-home").on('keyup', function() {
        var searchText = $('#add-game-home').val();
        var searchLen  = searchText.length;
        if (searchLen >= 1) {
            if (/^[a-zA-Z0-9- ]*$/.test(searchText) == false) {
                alert('Your search string contains illegal characters.');
                $('#home-club-result-box').html("");
            }
            else {
                url = '/search/club/' + searchText;

                $("#home-club-result-box").load(url);
            }
        }
        else{
            $("#home-club-result-box").html("");
        }
    });

    $("#add-game-away").on('keyup', function() {
        var searchText = $('#add-game-away').val();
        var searchLen  = searchText.length;

        if (searchLen >= 1) {
            if (/^[a-zA-Z0-9- ]*$/.test(searchText) == false) {
                alert('Your search string contains illegal characters.');
                $('#away-club-result-box').html("");
            }
            else {
                url = '/search/club/' + searchText;

                $("#away-club-result-box").load(url);
            }
        }
        else{
            $("#away-club-result-box").html("");
        }
    });

    $(document).on('click', '.clubs-result-container', function(){
        var containerClicked = $(this);
        var clubIdContainer = containerClicked.find('.club-id-admin');
        var id = clubIdContainer.text();
        var closestInputContainer = containerClicked.closest('.col-sm-3');
        var closestInput = closestInputContainer.find('input');
        closestInput.val(id);
    });

        $(function () {
            $('#datetimepicker1').datetimepicker();
        });



});

