<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <div class="container mt-4" style="max-width: 540px;">

        <#if feed??>
            <#list feed as post>
                <div class="card mb-4 bg-black text-white border-0">
                    <div class="d-flex align-items-center px-3 py-2">
                        <img src="/profile/avatars/${post.user.avatarUrl}" class="rounded-circle me-2" width="32" height="32">
                        <h5><a href="/profile/${post.user.id}" class="text-decoration-none text-white">${post.user.nickName}</a></h5>
                    </div>

                    <div class="position-relative">
                        <img src="/profile/image/${post.imageUrlString}" class="img-fluid w-100">
                    </div>

                    <div class="d-flex px-3 py-2">
                        <i class="bi bi-heart me-3 fs-5"></i>
                        <a href="/post/details/${post.id}" style="text-decoration: none; color: inherit;">
                            <i class="bi bi-chat fs-5"></i>
                        </a>
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
                            <span>${user.nickName}</span>
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

</@main.layout>
