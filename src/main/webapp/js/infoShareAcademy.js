$(function () {

    $(document).ready(function () {

        $(".infoShareAcademy").click(function () {

            $.ajax({
                url: '/homework5/infoShareAcademy?name=Ania',
                type: 'POST',
                success: function (result) {
                    location.reload();
                }
            });
        });
    });
});