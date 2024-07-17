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
                    .toast-container {
                        position: fixed;
                        top: 80px; /* Điều chỉnh giá trị top để đặt vị trí thấp hơn */
                        right: 20px;
                        z-index: 1050;
                    }
                    .toast {
                        opacity: 0.95;
                        font-size: 1.2em; /* Kích thước hợp lý cho toast */
                        background-color: #4CAF50; /* Nền màu xanh lá cây tươi */
                        color: white; /* Màu chữ trắng */
                        border: none; /* Loại bỏ viền */
                        border-radius: 10px; /* Đường viền cong */
                        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Hiệu ứng đổ bóng */
                        padding: 0;
                    }
                    .toast-header {
                        font-size: 1.4em; /* Tăng kích thước tiêu đề của toast */
                        background-color: #388E3C; /* Nền màu xanh đậm cho tiêu đề */
                        color: white; /* Màu chữ trắng */
                        border-bottom: 1px solid rgba(255, 255, 255, 0.2); /* Viền dưới tiêu đề */
                        border-top-left-radius: 10px; /* Đường viền cong góc trên bên trái */
                        border-top-right-radius: 10px; /* Đường viền cong góc trên bên phải */
                        padding: 10px 15px; /* Thêm khoảng cách bên trong */
                        display: flex;
                        justify-content: space-between;
                        align-items: center;
                    }
                    .toast-body {
                        font-size: 1.2em; /* Tăng kích thước nội dung của toast */
                        padding: 15px; /* Thêm khoảng cách bên trong */
                        border-bottom-left-radius: 10px; /* Đường viền cong góc dưới bên trái */
                        border-bottom-right-radius: 10px; /* Đường viền cong góc dưới bên phải */
                    }
                    .toast .close {
                        color: white; /* Màu chữ trắng */
                        background: none; /* Loại bỏ nền */
                        border: none; /* Loại bỏ viền */
                        font-size: 1.5em; /* Kích thước nút đóng */
                        padding: 0;
                        margin: 0;
                    }
                </style>-->
        <style>
            .toast-container {
                position: fixed;
                top: 80px; /* Điều chỉnh giá trị top để đặt vị trí thấp hơn */
                right: 20px;
                z-index: 1050;
            }
            .toast {
                opacity: 0.95;
                font-size: 1.2em; /* Kích thước hợp lý cho toast */
                color: white; /* Màu chữ trắng */
                border: none; /* Loại bỏ viền */
                border-radius: 10px; /* Đường viền cong */
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Hiệu ứng đổ bóng */
                padding: 0;
            }
            .toast-header {
                font-size: 1.4em; /* Tăng kích thước tiêu đề của toast */
                color: white; /* Màu chữ trắng */
                border-bottom: 1px solid rgba(255, 255, 255, 0.2); /* Viền dưới tiêu đề */
                border-top-left-radius: 10px; /* Đường viền cong góc trên bên trái */
                border-top-right-radius: 10px; /* Đường viền cong góc trên bên phải */
                padding: 10px 15px; /* Thêm khoảng cách bên trong */
                display: flex;
                justify-content: space-between;
                align-items: center;
            }
            .toast-body {
                font-size: 1.2em; /* Tăng kích thước nội dung của toast */
                padding: 15px; /* Thêm khoảng cách bên trong */
                border-bottom-left-radius: 10px; /* Đường viền cong góc dưới bên trái */
                border-bottom-right-radius: 10px; /* Đường viền cong góc dưới bên phải */
            }
            .toast .close {
                color: white; /* Màu chữ trắng */
                background: none; /* Loại bỏ nền */
                border: none; /* Loại bỏ viền */
                font-size: 1.5em; /* Kích thước nút đóng */
                padding: 0;
                margin: 0;
            }

            /* Style for error toast */
            .error-toast {
                background-color: #f44336; /* Nền màu đỏ tươi */
            }
            .error-toast-header {
                background-color: #d32f2f; /* Nền màu đỏ đậm cho tiêu đề */
            }

            /* Style for success toast */
            .success-toast {
                background-color: #4CAF50; /* Nền màu xanh lá cây tươi */
            }
            .success-toast-header {
                background-color: #388E3C; /* Nền màu xanh đậm cho tiêu đề */
            }
        </style>

    </head>
    <body id="bg">
        <%
        String email = (String) session.getAttribute("email");
        
        %>
        <div class="page-wraper">
            <div id="loading-icon-bx"></div>
            <div class="account-form">
                <div class="account-head" style="background-image:url(assets/images/background/bg2.jpg);">
                    <a href="Home"><img src="assets/images/logo-white-2.png" alt=""></a>
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
                                                <input type="text" id="otp" name="otp" value="${otp}" required class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label for="newPassword">New Password:</label>
                                                <input type="password" id="newPassword" name="newPassword" value="${newPassword}" required class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label for="reNewPassword">Re-enter New Password:</label>
                                                <input type="password" id="reNewPassword" name="reNewPassword" value="${reNewPassword}" required class="form-control">
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
                            <!--<p>${message}</p>-->
                            <!--hiển thị thông báo-->
                            <c:if test="${not empty message}">
                                <div class="toast-container">
                                    <div id="errorsToast" class="toast error-toast" role="alert" aria-live="assertive" aria-atomic="true" data-autohide="true">
                                        <div class="toast-header error-toast-header">
                                            <strong class="mr-auto">Thông báo</strong>
                                            <button type="button" class="close" data-dismiss="toast" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="toast-body">
                                            ${message} 
                                        </div>
                                    </div>
                                </div>
                            </c:if>

                            <c:if test="${not empty SuccessMessage}">
                                <div class="toast-container">
                                    <div id="successToast" class="toast success-toast" role="alert" aria-live="assertive" aria-atomic="true" data-autohide="true">
                                        <div class="toast-header success-toast-header">
                                            <strong class="mr-auto">Thông báo</strong>
                                            <button type="button" class="close" data-dismiss="toast" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="toast-body">
                                            ${SuccessMessage} 
                                        </div>
                                    </div>
                                </div>
                            </c:if>

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
        <!-- jQuery and Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <script>
            $(document).ready(function () {
                var errorsMessage = "${message}";
                if (errorsMessage) {
                    $('#errorsToast').toast({
                        delay: 5000
                    });
                    $('#errorsToast').toast('show');
                }
            });
        </script>
        <script>
            $(document).ready(function () {
                var successMessage = "${SuccessMessage}";
                if (successMessage) {
                    $('#successToast').toast({
                        delay: 5000
                    });
                    $('#successToast').toast('show');
                }
            });
        </script>
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
