<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <div class="container mt-4" style="max-width: 540px;">

        <#if feed??>
            <#list feed as post>
                <div class="card mb-4 bg-black text-white border-0">
                    <div class="d-flex align-items-center px-3 py-2">
                        <img src="/profile/avatars/${post.user.avatarUrl}" class="rounded-circle me-2" width="32" height="32">
                        <h5><a href="/profile/info/${post.user.id}" class="text-decoration-none text-white">${post.user.nickName}</a></h5>
                    </div>

                    <div class="position-relative">
                        <img src="/profile/image/${post.imageUrlString}" class="img-fluid w-100">
                    </div>

                    <div class="d-flex px-3 py-2">
                        <button data-post-id="${post.id}" class="btn btn-outline-light me-2 likeBtn">
                            <i class="bi bi-heart"></i>
                            <span class="likeCount">${post.likes}</span>
                        </button>
                        <button class="btn btn-outline-light me-2">
                            <a href="/post/details/${post.id}" style="text-decoration: none; color: inherit;">
                                <i class="bi bi-chat fs-5"></i>
                            </a>
                        </button>

                    </div>

                    <div class="px-3 pb-3">
                        <p class="mb-1"><strong>${post.user.nickName}</strong> ${post.description}</p>
                        <p class="text-muted small"> • Комментарии: ${post.comments}</p>
                    </div>
                </div>
            </#list>
        </#if>

        <#if (!feed??) && (suggestedUsers??) && (suggestedUsers?size > 0)>
            <div class="text-center text-white mt-5">
                <h4 class="mb-3">Вы пока ни на кого не подписаны</h4>
                <p class="text-muted">Попробуйте подписаться на кого-нибудь:</p>
                <#list suggestedUsers as user>
                    <div class="d-flex align-items-center justify-content-between mb-3">
                        <div class="d-flex align-items-center">
                            <img src="/profile/avatars/${user.avatarUrl}" class="rounded-circle me-2" width="40" height="40">
                            <h5><a href="/profile/${user.id}" class="text-decoration-none text-white">${user.nickName}</a></h5>
                        </div>
                        <form action="/subscriptions/follow/${user.id}" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <button type="submit" class="btn btn-sm btn-primary">Подписаться</button>
                        </form>
                    </div>
                </#list>
            </div>
        </#if>

    </div>


    <script>
        document.addEventListener("DOMContentLoaded", () => {
            const likeButtons = document.querySelectorAll(".likeBtn");

            likeButtons.forEach(likeBtn => {
                const likeCountSpan = likeBtn.querySelector(".likeCount");
                const postId = likeBtn.getAttribute("data-post-id");

                fetch(`/api/like/status/` + postId)
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
                    fetch(`/api/like/` + postId, {
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
        });

    </script>

</@main.layout>
