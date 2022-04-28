<%-- 
    Document   : index
    Created on : Apr 27, 2022, 6:22:08 PM
    Author     : casto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CSU Book Kiosk</title>
    </head>
    <body>
        <div class="e0h0a4p9 block">
            <div class="e08f56b5 block">
                <div class="e0c6plsa block"><img class="e0bziouh" srcset="asset/image/clayton-state-primary-logo-2.png 1x, asset/image/clayton-state-primary-logo-2@2x.png 2x, asset/image/clayton-state-primary-logo-2@3x.png 3x">
                    <p class="e0ggsnmg text"><em class="e0brvbg8">Welcome to the</em> Library Kiosk!</p>
                    <p class="e06zlmb3 text">A all-in-one platform built for students, by students. Easily borrow, return, reserve, and donate any book at the click of a button.</p>
                </div>
                <div class="e0ajrzvv block">
                    <div class="e0p0qmwx block"><img class="e0pvtvxd" srcset="asset/image/clayton-state-primary-logo-2-4428ebd4501d342171219233648dc0c3.png 1x, asset/image/clayton-state-primary-logo-2-4428ebd4501d342171219233648dc0c3@2x.png 2x, asset/image/clayton-state-primary-logo-2-4428ebd4501d342171219233648dc0c3@3x.png 3x">
                        <p class="e08yeny9 text">Login with your CSU Credentials</p>
                        <form action="LoginFormCTL" method="POST">

                            <div class="e06zyghm block">
                                <input type="text" name="User ID" placeholder="UserID" />
                            </div>
                            <div class="e0b2cjwi block">
                                <input type="password" name="pwd" placeholder="Password" />
                            </div>
                            <div class="e03oqbgs block">
                                <input type="submit" value="Login" name="Log-buttn" />
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
