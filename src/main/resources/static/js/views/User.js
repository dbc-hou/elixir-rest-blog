// import createView from "../static/js/createView.js";

var myMethod = "PUT";

export default function User(props) {
    return `
        <header>
            <h1>Your Account</h1>
        </header>
        <main>
            <div>
                <p>Greetings, ${props.userInfo.username}!</p>    
                <p>Your email address is ${props.userInfo.email}</p>
                <p>Your current password is ${props.userInfo.password}</p>
            </div>
            <div id="changePWDiv">
                <form id="changePWForm">
                    <label for="oldPasswordBox">New Password:</label>
                    <input disabled id="oldPasswordBox" type="text" class="mb-3" value="${props.userInfo.password}"/>
                    <label for="newPasswordBox">New Password:</label>
                    <input id="newPasswordBox" type="text" class="mb-3"/>
                    <button id="newPasswordSubmit" type="password" class="mb-2" data-id="${props.userInfo.id}">Change It!</button>
                </form>
            </div>
            <div id="postlist">
                ${this.props.user.posts.map(post => {
                    return `<h5>${post.title}</h5>`})
                    .join(" ")}
            </div>
        </main>
    `;
}

export function UsersEvent() {
    updatePWListener();
}

function updatePWListener() {
    const currentID = $("#newPasswordSubmit").data("id");
    let uriExtra = "/password"
    $("#newPasswordSubmit").click = function () {
    if (myMethod != "PUT") {
        myMethod = "PUT";
    }
    const request = {
        method: myMethod
    }
    let oldPW = User(this.props.userInfo.password);
    console.log("Old password for user #" + currentID + " = " + oldPW);
    let newPW = $("newPasswordBox").val();
    console.log("New password = " + newPW);
    fetch(`${BASE_URI}${uriExtra}?newPassword=${newPassword}`, request)
        .then(res => {
            console.log(`${request.method} SUCCESS: ${res.status}`);
        }).catch(error => {
        console.log(`${request.method} ERROR: ${error}`);
    }).finally(() => {
        createView("/users");
    });
    }
}