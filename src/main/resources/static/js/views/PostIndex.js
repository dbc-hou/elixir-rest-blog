import createView from "../createView.js";
import fetchData from "../fetchData.js";

var myMethod = "POST";
var currentPostId = 1;

//All this does is return a long, complicated string of html, css, & object properties expressed in jquery.
export default function PostIndex(props) {
    return `

<!--The header section is very simple.-->
        <header>
            <h1 class="container-fluid fw-bolder text-success">Posts Page</h1>
        </header>

<!--The main section contains the list of posts and the add/edit form.-->
        <main>
<!--Here is the list of posts. The map function substitutes for a for-each loop.-->
            <div id="posts-container" class="container-fluid">
                ${props.posts.map(post => `<h3 class="fw-bold" id="title-${post.id}">${post.title}</h3>
                    <h4 id="content-${post.id}">${post.content}</h4>
                    <a href="#" class="edit-link" data-id="${post.id}">Edit</a>
                    <a href="#" class="delete-link" data-id="${post.id}">Delete</a></p>`).join('')}   
            </div>
<!--Here is the add/edit form.-->
            <div class="container-fluid" id="add-post">
                <form id="add-post-form">
                    <div class="mb-3">
                        <label for="add-post-title">Title</label>
                        <input class="form-control" id="add-post-title" value="">
                    </div>
                    <div class="mb-3">
                        <label for="add-post-content">Content</label>
                        <textarea class="form-control" id="add-post-content" row="5" value=""></textarea>
                    </div>
                    <div class="mb-4">
                        <button class="btn btn-primary col-2" id="add-post-btn">Add Post</button>
                        <button class="btn btn-primary col-2" id="clear-btn">Clear</button>
                    </div>
                </form>
            </div>
        </main>
    `;
}

export function PostsEvent() {
    attachAddListener();
    attachEditListener();
    attachClearListener();
    attachDeleteListener();
}
function attachAddListener () {
    $("#add-post-btn").click(function (e) {
        if (myMethod == "POST") {
            const myTitle = $("#add-post-title").val();
            const myContent = $("#add-post-content").val();
            const myPost = {};
            myPost.title = myTitle;
            myPost.content = myContent;
            const myRequest = {};
            myRequest.method = myMethod;
            myRequest.headers = {'Content-Type': 'application/json'};
            myRequest.body = JSON.stringify(myPost);
            } else {
                $("#title-" + currentPostId).text($("#add-post-title").val());
                $("#content-" + currentPostId).text($("#add-post-content").val());
            }
        fetch("http://localhost:8081/api/posts", myRequest)
            .then(res => {
                console.log(res.status);
                createView("/posts")
            }).catch(error => {
            console.log(error);
            createView("/posts");
        });
    });
}

function attachEditListener() {
    $(".edit-link").click(function () {
        myMethod = "PUT";
        $("#add-post-btn").text("Update");
        const postId = $(this).data("id");
        const postTitle = $("#title-" + postId).text();
        const postContent = $("#content-" + postId).text();
        currentPostId = postId;
        console.log(postId);
        $("#add-post-title").val(postTitle);
        $("#add-post-content").val(postContent);
    })
}

function attachDeleteListener() {
    $(".delete-link").click(function () {
        myMethod = "DELETE";
        const postId = $(this).data("id");
        const myRequest = {};
        myRequest.method = myMethod;
        myRequest.headers = {'Content-Type': 'application/json'};
        fetch("http://localhost:8081/api/posts/" + postId, myRequest)
            .then(res => {
                console.log(res.status);
                createView("/posts")
            }).catch(error => {
            console.log(error);
            createView("/posts");
        });
    })
}
function attachClearListener() {
    $("#clear-btn").click (function () {
        myMethod = "POST";
        $("#add-post-btn").text("Add Post");
        $("#add-post-title").val("");
        $("#add-post-content").val("");
    })
}