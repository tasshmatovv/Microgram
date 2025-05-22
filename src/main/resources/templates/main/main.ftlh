<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <div class="container mt-4" style="max-width: 540px;">

        <#list feed as post>
            <div class="card mb-4 bg-black text-white border-0">
                <div class="d-flex align-items-center px-3 py-2">
                    <img src="/profile/avatars/${post.user.avatarUrl}" class="rounded-circle me-2" width="32" height="32">
                    <strong>${post.user.nickName}</strong>
                </div>

                <div class="position-relative">
                    <img src="/profile/image/${post.imageUrlString}" class="img-fluid w-100">
                    <div class="position-absolute bottom-0 start-0 p-3 w-100"
                         style="background: linear-gradient(to top, rgba(0,0,0,0.7), transparent);">
                        <p class="mb-0 text-white" style="font-size: 0.9rem;">${post.description}</p>
                    </div>
                </div>

                <div class="d-flex px-3 py-2">
                    <i class="bi bi-heart me-3 fs-5"></i>
                    <i class="bi bi-chat fs-5"></i>
                </div>

                <div class="px-3 pb-3">
                    <p class="mb-1"><strong>${post.user.nickName}</strong> ${post.description}</p>
                    <p class="text-muted small"> • Комментарии: ${post.comments}</p>
                </div>
            </div>
        </#list>
    </div>
</@main.layout>
