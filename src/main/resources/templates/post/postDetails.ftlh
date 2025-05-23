<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="d-flex align-items-center mb-3">
                    <img src="/profile/avatars/${post.user.avatarUrl}" class="rounded-circle me-3" width="40" height="40" alt="User Avatar">
                    <h5 class="mb-0">${post.user.nickName}</h5>
                </div>

                <div class="mb-3">
                    <img src="/profile/image/${post.imageUrlString}" alt="Post Image" class="img-fluid rounded">
                </div>

                <div class="d-flex mb-3">
                    <button id="likeBtn" data-post-id="${post.id}" class="btn btn-outline-light me-2">
                        <i class="bi bi-heart"></i>
                        <span id="likeCount">${post.likes}</span>
                    </button>
                    <button class="btn btn-outline-light me-2">
                        <i class="bi bi-chat"></i> ${post.comments}
                    </button>
                </div>
               <#if (post.description!'')?trim != ''>
                <div class="mb-3">
                    <p>${post.description}</p>
                </div>
               </#if>

                <div class="small mb-3" style="color: #ffffff">
                    <i class="bi bi-clock"></i> ${post.getFormattedUpdatedTime()}
                </div>

                <form action="/post/${post.id}/comment" method="post" class="mt-3">
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />

                    <div class="mb-3">
                        <label for="newComment.text">Комментарий</label>
                        <@spring.formInput "newComment.text" 'class="form-control bg-dark text-white border-secondary" id="newComment.text" placeholder="Добавить комментарий..."' />
                        <div class="text-danger small">
                            <@spring.showErrors "<br>" "error-message" />
                        </div>
                    </div>

                </form>

                <div class="mt-4 border-top pt-3">
                    <h6>Комментарии (${comments?size})</h6>

                    <div class="comments-list mb-4">
                        <#if comments?size == 0>
                            <p style="color: #ffffff">Пока нет комментариев</p>
                        <#else>
                            <#list comments as comment>
                                <div class="comment-item mb-3">
                                    <div class="d-flex align-items-center">
                                        <img src="${(comment.user.avatarUrl)!'/images/default-avatar.png'}"
                                             class="rounded-circle me-2"
                                             width="32" height="32"
                                             alt="User Avatar">

                                        <strong>${comment.user.nickName}</strong>
                                    </div>
                                    <p class="mt-1 mb-0">${comment.text}</p>
                                    <small class="text-muted">${comment.createdAt}</small>
                                </div>

                                <#if post.user.id == currentUser.id || comment.user.id == currentUser.id>
                                    <form action="/post/${post.id}/comment/${comment.id}/delete" method="post" style="margin: 0;">
                                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                        <button type="submit" class="btn btn-sm btn-danger">Удалить</button>
                                    </form>
                                </#if>


                            </#list>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <style>
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }
        .btn-outline-light {
            border-color: #333;
        }
        .btn-outline-light:hover {
            background-color: #333;
        }
        .comment-item {
            padding: 10px;
            background-color: #111;
            border-radius: 8px;
        }
        .comments-list {
            max-height: 400px;
            overflow-y: auto;
        }
    </style>


    <script>
        document.addEventListener("DOMContentLoaded", () => {
            const likeBtn = document.getElementById("likeBtn");
            const likeCountSpan = document.getElementById("likeCount");
            const postId = likeBtn.getAttribute("data-post-id");

            fetch(`/api/like/status/${post.id}`)
                .then(response => response.json())
                .then(data => {
                    likeCountSpan.textContent = data.likesCount;
                    const icon = likeBtn.querySelector("i");
                    if (data.liked) {
                        likeBtn.classList.add("liked");
                        icon.className = "bi bi-heart-fill";
                    } else {
                        likeBtn.classList.remove("liked");
                        icon.className = "bi bi-heart";
                    }
                });

            likeBtn.addEventListener("click", () => {
                fetch(`/api/like/${post.id}`, {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded",
                        "X-CSRF-TOKEN": '${_csrf.token}'
                    },
                    body: ""
                })
                    .then(response => response.json())
                    .then(data => {
                        likeCountSpan.textContent = data.likesCount;
                        const icon = likeBtn.querySelector("i");
                        if (data.liked) {
                            likeBtn.classList.add("liked");
                            icon.className = "bi bi-heart-fill";
                        } else {
                            likeBtn.classList.remove("liked");
                            icon.className = "bi bi-heart";
                        }
                    });
            });
        });
    </script>
</@main.layout>