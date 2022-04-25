import CreateView from "../createView.js"
import {getHeaders,isLoggedIn} from "../auth.js";

export default function Register(props) {
    return `
    <!DOCTYPE html>
        <html>
            <head>
                <meta charset="UTF-8"/>
                <title>Register</title>
            </head>
            <body>
                <h1>Register</h1>
<!--JS generates this HTML form, which allows the user
to enter a username, email address, and password
to register as a new user. Note that the type attribute
of the password input is "password."-->
                <form id="register-form">
                    <label for="username">Username</label>
                    <input id="username" name="username" type="text"/>
                    <label for="email">Email</label>
                    <input id="email" name="email" type="email">
                    <label for="password">Password</label>
                    <input id="password" name="password" type="password"/>
                    <button id="register-btn" type="button">Register</button>
                </form>
            </body>
        </html>
`;
}

export function RegisterEvent(){
    console.log("Am I logged in?" + isLoggedIn());
    $("#register-btn").click(function(){
        //Set the properties for the new User object
        //based on the values in the input boxes; print the results
        //in the console for now.
        let newUser = {
            username: $("#username").val(),
            email: $("#email").val(),
            password: $("#password").val()
        }
        console.log(newUser);

        //Establish a new RequestBody with the usual properties:
        //method, headers, & body.
        //Spring annotations convert this into the createUser method.
        let request = {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(newUser)
        }
        //Call the fetch method (asynchronous), which logs the
        //response's status in the console and creates a new
        //view the ends in a backslash (no queryString in the URI).
        fetch("http://localhost:8081/api/users", request)
            .then(response => {
                console.log(response.status);
                CreateView("/");
            })

    })
}