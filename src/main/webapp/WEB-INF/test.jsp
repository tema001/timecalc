<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">

    <link rel="stylesheet" href="webcontent/qwe.css">

    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
</head>

<body>
<div class="card md">
    <h2>time calculator</h2>

    <div class="help-ribbon">help?</div>

    <div class="flex-row">
        <div class="card mini-card tlp">
            <p>Start</p>
            <input id="input-start" class="custom-input-text mini-input" type="text">

            <div class="tooltip md">24-hour clock, strict record form <b>23(;/:)59</b></div>
        </div>

        <div class="card mini-card">
            <p>Finish</p>
            <input id="input-finish" class="custom-input-text mini-input" type="text">
        </div>
    </div>

    <div class="inputs-area"></div>

    <div class="flex-row">
        <button id="add-button" class="custom-button">Add</button>

        <button id="result-button" class="custom-button">Result</button>

        <button id="reset-button" class="custom-button">Reset</button>
    </div>

    <div id="result-div" class="result-div flex-column">
        <span id="result-span" class="result-span"></span>
        <p class="custom-modal-button">how the fuck I get it?</p>
        <div class="card custom-modal">
            <div id="modal-content" class="content"></div>
        </div>
    </div>
</div>

<script>
    $('#reset-button').on('click', function () {
        $('.custom-input-text-group').remove();
        $('#result-div').css('display', 'none');
        $('.custom-input-text').val('');
    });

    $('#add-button').click(function () {
        $('.inputs-area').append($('<div>', {class: 'custom-input-text-group tlp'})
            .append($('<input>', {class: 'custom-input-text', type: 'text', name: 'args-input'}))
            .append($('<label>', {class: 'input-close'}))
            .append($('<div>', {class: 'tooltip lg', text: description})));
    });

    var description = "there are 2 types of recording: a record in total time, " +
        "strict record form +/- 59 only for minutes, " +
        " +/- 1(./,)00 for hours and minutes; " +
        "a record in time range, strict record form +/- 23(;/:)59 23(;/:)59";

    $('.card').on('click', 'label.input-close', function () {
        $(this).parent().remove();
    });

    $('.card').on('click', '.help-ribbon', function () {
        var actv = 'is-active';
        if ($(this).hasClass(actv)) {
            $(this).removeClass(actv);
            $('.tooltip').removeClass('tlp-active');
        } else if (!$(this).hasClass(actv)) {
            $(this).addClass(actv);
            $('.tooltip').addClass('tlp-active');
        }
    });

    $('.card').on('click', '.custom-modal-button', function () {
        var $modal = $('.custom-modal');
        var vsbl = 'is-visible';
        if ($modal.hasClass(vsbl)) {
            $modal.removeClass(vsbl);
        } else if (!$modal.hasClass(vsbl)) {
            $modal.addClass(vsbl);
        }
    });

    $('.card').on('click', '#result-button', function () {
        var fields = $(":input[name='args-input']");
        var qw = [];
        $.each(fields, function (i, field) {
            if (field.value !== '') {
                qw.push(field.value);
            }
        });

        var args = JSON.stringify({
            start: $('#input-start').val(),
            finish: $('#input-finish').val(),
            inputs: qw
        });

        $.post({
            url: '/timecalc/result',
            contentType: "application/json",
            data: args,
            success: function (data) {
                $('#result-span').html(data.result).text();
                $('#modal-content').html(data.description).text();
                $('#result-div').css('display', 'flex');
            }
        });
    });
</script>
</body>
</html>