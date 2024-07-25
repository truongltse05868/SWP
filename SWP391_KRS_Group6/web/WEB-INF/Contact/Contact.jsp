

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">


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
        <title>EduChamp : Education HTML Template </title>

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
        </style>
    </head>
    <body id="bg">
        <div class="page-wraper">
            <div id="loading-icon-bx"></div>

            <!-- Header Top ==== -->
            <jsp:include page="../Header.jsp"/>
            <!-- header END ==== -->
            <!-- Content -->
            <div class="page-content bg-white">
                <!-- inner page banner -->
                <div class="page-banner ovbl-dark" style="background-image:url(assets/images/banner/banner2.jpg);">
                    <div class="container">
                        <div class="page-banner-entry">
                            <h1 class="text-white">Contact Us 2</h1>
                        </div>
                    </div>
                </div>
                <!-- Breadcrumb row -->
                <div class="breadcrumb-row">
                    <div class="container">
                        <ul class="list-inline">
                            <li><a href="#">Home</a></li>
                            <li>Contact Us 2</li>
                        </ul>
                    </div>
                </div>
                <!-- Breadcrumb row END -->

                <!-- inner page banner -->
                <div class="page-banner contact-page">
                    <div class="container-fuild">
                        <div class="row m-lr0">
                            <div class="col-lg-6 col-md-6 p-lr0 d-flex">
                                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3728.7459782743214!2d105.525860!3d21.011422!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x0!2zMjHigJAwJzM2LjAiTiAxMDXCsDMxJzMzLjEiRQ!5e0!3m2!1sen!2s!4v1689665482312" class="align-self-stretch d-flex" style="width:100%; min-height: 300px;" allowfullscreen> </iframe>
                            </div>
                            <div class="col-lg-6 col-md-6 section-sp2 p-lr30">
                                <form class="contact-bx" action="ContactController" method="post">
                                    <input type="hidden" name="action" value="sentContact" >

                                    <div class="heading-bx left p-r15">
                                        <h2 class="title-head">Get In <span>Touch</span></h2>
                                        <!--<p>It is a long established fact that a reader will be distracted by the readable content of a page</p>-->
                                    </div>
                                    <div class="row placeani">
                                        <div class="col-lg-6 ">
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <label>Your Name *</label>
                                                    <input name="name" type="text" required class="form-control valid-character">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <div class="input-group"> 
                                                    <label>Your Email Address *</label>
                                                    <input name="email" type="email" class="form-control" required >
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <div class="input-group"> 
                                                    <label>Your Phone *</label>
                                                    <input name="phone" type="number" class="form-control" required >
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <label>Type</label>
                                                    <select class="form-control" name="contactTypeId">
                                                        <c:forEach var="contactType" items="${setting}">
                                                            <option value="${contactType.settingId}"<c:if test="${contactType.settingId == contactTypeId}">selected</c:if>>${contactType.settingName}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <label>Subject</label>
                                                    <input name="subject" type="text" required class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-lg-12">
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <label>Type Message</label>
                                                    <textarea name="message" rows="4" class="form-control" required ></textarea>
                                                </div>
                                            </div>
                                        </div>
                                        <!--                                        <div class="col-lg-12">
                                                                                    <div class="form-group">
                                                                                        <div class="input-group">
                                                                                            <div class="g-recaptcha" data-sitekey="6Lf2gYwUAAAAAJLxwnZTvpJqbYFWqVyzE-8BWhVe" data-callback="verifyRecaptchaCallback" data-expired-callback="expiredRecaptchaCallback"></div>
                                                                                            <input class="form-control d-none" style="display:none;" data-recaptcha="true" required data-error="Please complete the Captcha">
                                                                                        </div>
                                                                                    </div>
                                                                                </div>-->
                                        <div class="col-lg-12">
                                            <button type="submit" class="btn button-md"> Send Message</button>
                                        </div>
                                        <!--hiển thị thông báo-->
                                        <c:if test="${not empty successMessage}">
                                            <div class="toast-container">
                                                <div id="successToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true" data-autohide="true">
                                                    <div class="toast-header">
                                                        <strong class="mr-auto">Thông báo</strong>
                                                        <button type="button" class="close" data-dismiss="toast" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="toast-body">
                                                        ${successMessage} 
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- inner page banner END -->
            </div>
            <!-- Content END-->
            <!-- Footer ==== -->
            <jsp:include page="../Footer.jsp"/>
            <!-- Footer END ==== -->
            <!-- scroll top button -->
            <button class="back-to-top fa fa-chevron-up" ></button>
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
        <!--<script src='../../www.google.com/recaptcha/api.js'></script>-->

        <script>
            $(document).ready(function () {
                var successMessage = "${successMessage}";
                if (successMessage) {
                    $('#successToast').toast({
                        delay: 5000
                    });
                    $('#successToast').toast('show');
                }
            });
        </script>
    </body>

</html>

