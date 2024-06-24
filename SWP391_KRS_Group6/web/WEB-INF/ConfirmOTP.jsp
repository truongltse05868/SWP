<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- META ============================================= -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="keywords" content="" />
        <meta name="author" content="" />
        <meta name="robots" content="" />

        <!-- DESCRIPTION -->
        <meta name="description" content="EduChamp : Education HTML Template" />

        <!-- OG -->
        <meta property="og:title" content="EduChamp : Education HTML Template" />
        <meta property="og:description" content="EduChamp : Education HTML Template" />
        <meta property="og:image" content="" />
        <meta name="format-detection" content="telephone=no">

        <!-- FAVICONS ICON ============================================= -->
        <link rel="icon" href="assets/images/favicon.ico" type="image/x-icon" />
        <link rel="shortcut icon" type="image/x-icon" href="assets/images/favicon.png" />

        <!-- PAGE TITLE HERE ============================================= -->
        <title>ForgotPassword</title>

        <!-- MOBILE SPECIFIC ============================================= -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!--[if lt IE 9]>
        <script src="assets/js/html5shiv.min.js"></script>
        <script src="assets/js/respond.min.js"></script>
        <![endif]-->

        <!-- All PLUGINS CSS ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/assets.css">

        <!-- TYPOGRAPHY ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/typography.css">

        <!-- SHORTCODES ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/shortcodes/shortcodes.css">

        <!-- STYLESHEETS ============================================= -->
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">
        <link class="skin" rel="stylesheet" type="text/css" href="assets/css/color/color-1.css">
    </head>
    <body id="bg">
        <div class="page-wraper">
            <div id="loading-icon-bx"></div>
            <div class="account-form">
                <div class="account-head" style="background-image:url(assets/images/background/bg2.jpg);">
                    <a href="index.html"><img src="assets/images/logo-white-2.png" alt=""></a>
                </div>
                <div class="account-form-inner">
                    <div class="account-container">
                        <div class="heading-bx left">
                            <h2 class="title-head">Forget <span>Password</span></h2>
                            <p>Login Your Account <a href="Login">Click here</a></p>
                        </div>
                        <div>
                            <form class="contact-bx" action="${pageContext.request.contextPath}/ForgotPasswordController" method="post">
                                <input type="hidden" name="action" value="renewpass"> 
                                <input type="hidden" name="email" value="${email}" class="form-control">
                                <div class="row placeani">
                                    <div class="col-lg-12">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label for="email">OTP:</label>
                                                <input type="text" id="otp" name="otp" required class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label for="newPassword">New Password:</label>
                                                <input type="password" id="newPassword" name="newPassword" required class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label for="reNewPassword">Re-enter New Password:</label>
                                                <input type="password" id="reNewPassword" name="reNewPassword" required class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-12 m-b30">
                                        <button name="submit" type="submit" value="Submit" class="btn button-md">Submit</button>
                                    </div>
                                </div>
                            </form>
                            <form id="otpForm" action="${pageContext.request.contextPath}/ForgotPasswordController" method="post">
                                <input type="hidden" name="action" value="reSentOTP"> 
                                <input type="hidden" name="email" value="${email}" class="form-control">
                                <button id="resendBtn" name="submit" type="submit" value="Submit" class="btn button-md">Re-send OTP</button>
                            </form>
                            <p id="countdown"></p>
                            <p>${message}</p>
                            <p id="message"></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- External JavaScripts -->
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/vendors/bootstrap/js/popper.min.js"></script>
        <script src="assets/vendors/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/vendors/bootstrap-select/bootstrap-select.min.js"></script>
        <script src="assets/vendors/bootstrap-touchspin/jquery.bootstrap-touchspin.js"></script>
        <script src="assets/vendors/magnific-popup/magnific-popup.js"></script>
        <script src="assets/vendors/counter/waypoints-min.js"></script>
        <script src="assets/vendors/counter/counterup.min.js"></script>
        <script src="assets/vendors/imagesloaded/imagesloaded.js"></script>
        <script src="assets/vendors/masonry/masonry.js"></script>
        <script src="assets/vendors/masonry/filter.js"></script>
        <script src="assets/vendors/owl-carousel/owl.carousel.js"></script>
        <script src="assets/js/functions.js"></script>
        <script src="assets/js/contact.js"></script>
        <script src='assets/vendors/switcher/switcher.js'></script>
        <script>
            let resendTimeout = 60000; // 1 minute in milliseconds
            let countdownElement = document.getElementById('countdown');
            let resendButton = document.getElementById('resendBtn');

            function startCountdown(duration) {
                let timer = duration, minutes, seconds;
                let countdownInterval = setInterval(function () {
                    minutes = parseInt(timer / 60, 10);
                    seconds = parseInt(timer % 60, 10);

                    minutes = minutes < 10 ? "0" + minutes : minutes;
                    seconds = seconds < 10 ? "0" + seconds : seconds;

                    countdownElement.textContent = "Resend OTP in " + minutes + ":" + seconds;

                    if (--timer < 0) {
                        clearInterval(countdownInterval);
                        countdownElement.textContent = "";
                        resendButton.disabled = false;
                    }
                }, 1000);
            }

            function disableResendButton() {
                resendButton.disabled = true;
                startCountdown(resendTimeout / 1000);
            }

            function sendOTPRequest(event) {
                event.preventDefault(); // Prevent form submission
                $.ajax({
                    url: $("#otpForm").attr("action"),
                    type: "POST",
                    data: $("#otpForm").serialize(),
                    success: function (response) {
                        // Handle success
                        disableResendButton();
                        countdownElement.textContent = "OTP sent successfully!";
                    },
                    error: function (xhr, status, error) {
                        // Handle error
                        countdownElement.textContent = "Error in sending OTP. Please try again.";
                    }
                });
            }

            resendButton.addEventListener('click', function (event) {
                if (!resendButton.disabled) {
                    sendOTPRequest(event);
                } else {
                    event.preventDefault();
                }
            });

            // Initial setup: disable resend button and start countdown
            disableResendButton();
        </script>
    </body>
</html>
