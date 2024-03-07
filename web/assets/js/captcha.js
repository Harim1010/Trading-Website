/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
function refresh1() {
    $.ajax({
        type: "GET",
        url: "/DemoSWP391/login", 
        success: function () {
            console.log("Captcha refreshed successfully");
            var captchaImage = document.querySelector('.group-captcha img');

            var timestamp = new Date().getTime();

            captchaImage.src = '/DemoSWP391/login?' + timestamp;
        },
        error: function (xhr, status, error) {
            console.error("Error refreshing captcha:", error);
        }
    });
}

function refresh2() {
    $.ajax({
        type: "GET",
        url: "/DemoSWP391/register", // 
        success: function () {
            console.log("Captcha refreshed successfully");
            var captchaImage = document.querySelector('.group-captcha img');

            var timestamp = new Date().getTime();

            captchaImage.src = '/DemoSWP391/register?' + timestamp;
        },
        error: function (xhr, status, error) {
            console.error("Error refreshing captcha:", error);
        }
    });
}

