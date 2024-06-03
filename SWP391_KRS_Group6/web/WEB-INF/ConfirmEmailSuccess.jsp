<%-- 
    Document   : ConfirmEmailSuccess
    Created on : Jun 3, 2024, 7:18:28 PM
    Author     : Sunshine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirm Email Success</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background: linear-gradient(to right, #ff7e5f, #feb47b);
            font-family: 'Arial', sans-serif;
            color: #fff;
        }

        .container {
            text-align: center;
            background: rgba(255, 255, 255, 0.1);
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .container h1 {
            font-size: 2.5em;
            margin-bottom: 20px;
        }

        .container p {
            font-size: 1.2em;
            margin-bottom: 30px;
        }

        .container .checkmark {
            font-size: 4em;
            color: #28a745;
            margin-bottom: 20px;
        }

        .container button {
            background-color: #28a745;
            border: none;
            color: white;
            padding: 10px 20px;
            font-size: 1em;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .container button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="checkmark">✓</div>
        <h1>Confirm Email Success</h1>
        <p>Your email has been successfully confirmed!</p>
        <!--<button onclick="goToDashboard()">Go to Dashboard</button>-->
    </div>

<!--    <script>
        function goToDashboard() {
            window.location.href = 'https://yourwebsite.com/dashboard';
        }
    </script>-->
</body>
</html>
