import createView from "../createView.js";
import fetchData from "../fetchData.js";
import {getHeaders} from "../auth.js";

var myMethod = "POST";
var currentPostId;

//All this does is return a long, complicated string of html, css, & object properties expressed in jquery.
export default function PostIndex(props) {
    return `

<!--The header section is very simple.-->
        <header>
            <h1 class="container-fluid fw-bolder text-success">Posts Page</h1>
        </header>

<!--The main section contains the list of posts and the add/edit form.-->
        <main>
        <div class="container">
        <div class="row align-items-start">

<!--Here is the list of posts. The map function substitutes for a for-each loop.-->
            <div id="posts-container" class="card col-5">
                ${props.posts.map(post => `<h5 class="fw-bold" id="title-${post.id}">${post.title}</h5>
                    <h6 id="content-${post.id}">${post.content}</h6>
                   
                    <a href="#" class="edit-link" data-id="${post.id}">Edit</a>
                    <a href="#" class="delete-link" data-id="${post.id}">Delete</a></p>`).join('')}   
            </div>
<!--Here is the add/edit form.-->
            <div class="card col-7" id="add-post">
                <form id="add-post-form">
                    <div class="mb-3">
                        <label for="add-post-title">Title</label>
                        <input class="form-control" id="add-post-title" value="">
                    </div>
                    <div class="mb-3">
                        <label for="add-post-content">Content</label>
                        <textarea class="form-control" id="add-post-content" row="5" value=""></textarea>
                    </div>
                    <div class="mb-6">
                        <button class="btn btn-primary" id="add-post-btn">Add Post</button>
                        <button class="btn btn-primary" id="clear-btn">Clear Form</button>
                    </div>
                </form>
            </div>
        </div>
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
        const myRequest = {};

        const myTitle = $("#add-post-title").val();
        const myContent = $("#add-post-content").val();
        const myPost = {};
        myPost.title = myTitle;
        myPost.content = myContent;
        myRequest.method = myMethod;
        myRequest.headers = getHeaders();
        myRequest.body = JSON.stringify(myPost);
        if (myMethod == "PUT") {
            fetch("http://localhost:8081/api/posts/" + currentPostId, myRequest)
                .then(res => {
                    console.log(res.status);
                    createView("/posts")
                }).catch(error => {
                console.log(error);
                createView("/posts");
            });
        } else if (myMethod == "POST") {
            fetch("http://localhost:8081/api/posts", myRequest)
                .then(res => {
                    console.log(res.status);
                    createView("/posts")
                }).catch(error => {
                console.log(error);
                createView("/posts");
            });
        }
        myMethod = "POST";
    })
}

function attachEditListener() {
    $(".edit-link").click(function () {
        myMethod = "PUT";
        console.log(currentPostId);
        console.log($(this).data("id"));
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
        myRequest.headers = getHeaders();
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