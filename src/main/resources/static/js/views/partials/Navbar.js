import {isLoggedIn} from "../../auth.js";

export default function Navbar(props) {
    let loggedIn = isLoggedIn();

    // everyone can see home
    let html = `
        <nav class="navbar bg-light">
            <div class="container row d-inline-flex justify-content-center">
                <a class="btn btn-success rounded-pill col-2" href="/" data-link>Home </a>`;

    html = html + `<a class="btn btn-success rounded-pill col-2" href="/posts" data-link>Posts </a>`;

    // only logged in can see posts

    // everyone can see about
    html = html + `<a class="btn btn-success rounded-pill col-2" href="/about" data-link>About </a>`;

    // only logged in can see user info and logout
    if(loggedIn) {
        html = html + `<a class="btn btn-success rounded-pill col-2" href="/users" data-link>User Info </a>
            <a class="btn btn-success rounded-pill col-2" href="/logout" data-link>Logout </a>`;
    } else {
        // if not logged in, can see login and register
        html = html + `<a class="btn btn-success rounded-pill col-2" href="/login" data-link>Login </a>
        <a class="btn btn-success rounded-pill col-2" href="/register" data-link>Register</a>`;
    }

    html = html + `</div></nav>`;
    return html;
}
