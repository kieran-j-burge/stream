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

        url = "/admin/add-club/" + club + "/" + img;

        $.post(url, function (data, status) {
            // $('#response-message')
            // window.location.href = "/" ;
            alert("Club added")
            // + url
        });

    })

    $('.alter-club-btn').on('click', function () {
        var club = $('.club-name-alter-input').val();
        var img = $('.club-img-alter-input').val();

        url = "/admin/alter-club/" + club + "/" + img;

        $.post(url, function (data, status) {
            alert("Club added")
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
                url = '/search/' + searchText;
                $("#resultsBlock").load(url);
            }
        }
        else{
            url ='/load_home';
            $("#resultsBlock").load(url);
        }
    });

    $("#searchGameLeague").on('keyup', function() {
        var searchText = $('#searchGameLeague').val();
        var searchLen  = searchText.length;
        var leagueId = $('.league-id-hidden').text();

        if (searchLen >= 1) {
            if (/^[a-zA-Z0-9- ]*$/.test(searchText) == false) {
                alert('Your search string contains illegal characters.');
                $('#searchGame').val("");
            }
            else {
                url = '/search/' + searchText+"/"+leagueId;
                $("#resultsBlock").load(url);
            }
        }
        else{
            url ='/load_home/'+leagueId;
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

    $("#add-game-league").on('keyup', function() {
        var searchText = $('#add-game-league').val();
        var searchLen  = searchText.length;

        if (searchLen >= 1) {
            if (/^[a-zA-Z0-9- ]*$/.test(searchText) == false) {
                alert('Your search string contains illegal characters.');
                $('#league-result-box').html("");
            }
            else {
                url = '/league/search/admin/' + searchText;

                $("#league-result-box").load(url);
            }
        }
        else{
            $("#league-result-box").html("");
        }
    });



    $(document).on('click', '.clubs-result-container', function(){
        var containerClicked = $(this);
        var clubIdContainer = containerClicked.find('.club-id-admin');
        var id = clubIdContainer.text();
        var closestInputContainer = containerClicked.closest('.col-sm-3');
        var closestInput = closestInputContainer.find('input');
        closestInput.val(id);
        var clubResultBox = closestInputContainer.find(".club-result-box");
        clubResultBox.html("");
    });

    $(document).on('click', '.league-result-container', function(){
        var containerClicked = $(this);
        var clubIdContainer = containerClicked.find('.league-id-admin');
        var id = clubIdContainer.text();

        var closestInputContainer = containerClicked.closest('.col-sm-3');
        var closestInput = closestInputContainer.find('input');
        closestInput.val(id);
        var leagueResultBox = closestInputContainer.find(".league-result-box");
        leagueResultBox.html("");
    });

        $(function () {
            $('#datetimepicker1').datetimepicker();
        });


    $(document).on('click', '.add-game-btn', function(){
        var dateTime = $('.date-time-value').val();
        var homeId = $('#add-game-home').val();
        var awayId =$('#add-game-away').val();
        var leagueId = $('#add-game-league').val();

        var url = "/add/game/"+homeId+"&"+awayId+"&"+leagueId+"&"+dateTime;

        $.post(url, function (data, status) {
            $("#result-box-add-game").find("p").text("Game Added");
        });

    });

    $(document).on('click', '.btn-remove', function(){
        var gameId = $(this).closest('.game-info-holder').find('#rmv-game-id').text();

        var url = "/admin/remove-game/" + gameId;

            $(this).closest('.game-info-holder').load(url);


    });


    $(document).on('click', '.add-stream-btn', function(){

        var gameId = $("#add-links-id").text();
        var link = $(this).closest(".add-links-spacer").find(".add-stream").val();
        link = link.replace(/\./g,'£');
        link = link.replace(/\//g,'`');

        var url = "/admin/add-stream/" + gameId+"/"+link;


        $(this).closest('.add-links-spacer').load(url);


    });

    $(document).on('click', '.sub-feedback', function(){
        var msg = $("#Feedback").val();
        var url = "/post/feedback/" + msg;

        $.post(url, function (data, status) {
            alert("Feedback submitted - Thank you :)");
            window.location.href = "/" ;

        });


    });

});

