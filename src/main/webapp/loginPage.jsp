<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Bookstore Website</title>
    </head>
    <body>



        <style>
            body{
                background-color: white;     
                margin-top: 20%;
                box-shadow: 0px 0px 1000px 30px #87CEEB;
                padding: 10px 10px;
                width: 500px;
                height: 600px;
                position: absolute;
                left: 37%;
                top: -19%;
                border: 5px solid black;
                border-radius: 20px;
            } 

            h1{

                font-size: 3em;  
                font-family: arial, sans-serif;
                border: 5px solid black;
                text-align: center;

                border-radius: 20px;

            }
            label{
                text-align: center;
                color:black;
                font-size: 1.5em;


            }
            input{
                text-align: center;



            }
            button{
                font-size: 1.2em; 

            }
            button:hover{
                font-size: 2em; 
                border: 3px solid blue;

            }
            .nome{
                text-align: center; 
                margin-top: 27%;
                border: 5px solid black;
                border-radius: 20px;
                max-width: max-content-content;
                min-height: min-content-content;
                font-size: 1.5em;



            }






        </style>
    <center>
        <div style="text-align: center">
            <h1>Admin Login</h1>
            <form action="login" method="post">
                <div class="nome">
                    <label for="email">Email:</label>
                    <input name="email" size="20"/>
                    <br>
                    <label for="password">Password:</label>
                    <input type="password" name="password" size="20" />

                    <br><br>
                    <br>${message}<br><br>

                    <button type="submit" >Login</button>
                    <button type="submit"><a href="index.html">Voltar</a></button>

                </div>
            </form> 

        </div>
        <p>



        </p>
    </center>
</body>
</html>