<%-- 
    Document   : ResetPassword
    Created on : May 29, 2024, 9:22:59 AM
    Author     : Simon
--%>

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
        <!--        <style>
                    .hidden {
                        display: none;
                    }
                </style>
                <script>
                    function showOtpForm() {
                        document.getElementById("emailForm").classList.add("hidden");
                        document.getElementById("otpForm").classList.remove("hidden");
                    }
                </script>-->
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
                                <button id="resendBtn" name="submit" type="submit" value="Submit" class="btn button-md">ReSend OTP</button>
                            </form>
                            <script>
                                let lastResendTime = 0; // Timestamp of the last time "ReSend OTP" was clicked
                                let resendTimeout = 300000; // 5 minutes in milliseconds
                                let resendEnabled = true; // Flag to track if resend is enabled

                                // Function to enable resend button after a specified timeout
                                function enableResendButton() {
                                document.getElementById('resendBtn').disabled = false;
                                document.getElementById('message').textContent = ''; // Clear any previous message
                                resendEnabled = true; // Update flag
                                }

                                // Function to disable resend button and start timer
                                function disableResendButton() {
                                document.getElementById('resendBtn').disabled = true;
                                document.getElementById('message').textContent = 'Please wait 5 minutes before retrying.'; // Display message
                                setTimeout(enableResendButton, resendTimeout); // Set timeout to enable resend button
                                resendEnabled = false; // Update flag
                                }

                                // Event listener for "ReSend OTP" button click
                                document.getElementById('resendBtn').addEventListener('click', function(event) {
                                let currentTime = Date.now();
                                if (currentTime - lastResendTime >= resendTimeout || lastResendTime === 0) {
                                lastResendTime = currentTime; // Update lastResendTime
                                disableResendButton(); // Disable resend button and start timer
                                } else {
                                event.preventDefault(); // Prevent default action if resend is disabled
                                }
                                });
                                // Initial setup: disable resend button if necessary
                                if (!resendEnabled) {
                                disableResendButton();
                                }
                            </script>
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
    </body>
</html>
