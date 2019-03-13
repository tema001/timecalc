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

    <div class="card mini-card">
        <p>Start</p>
        <input id="input-start" class="custom-input-text mini-input" type="text">
    </div>

    <div class="card mini-card">
        <p>Finish</p>
        <input id="input-finish" class="custom-input-text mini-input" type="text">
    </div>

    <div class="inputs-area"></div>

    <div class="row">
        <button id="add-button" class="custom-button">Add</button>

        <button id="result-button" class="custom-button">Result</button>
    </div>

    <div id="result-div" class="result-div">
        <span id="result-span" class="result-span"></span>
    </div>
</div>

<script>
    $('#add-button').click(function () {
        $('.inputs-area').append($('<div>', {class: 'custom-input-text-group'})
            .append($('<input>', {class: 'custom-input-text', type: 'text', name: 'args-input'}))
            .append($('<label>', {class: 'input-close'})));
    });

    $('.card').on('click', 'label.input-close', function () {
        $(this).parent().remove();
    });


    $('.card').on('click', '#result-button', function () {
        var fields = $(":input[name='args-input']");
        var qw = [];
        $.each(fields, function (i, field) {
            qw.push(field.value);
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
                $('#result-span').text(data);
                $('#result-div').css('display', 'block');
            }
        });
    });
</script>
</body>
</html>