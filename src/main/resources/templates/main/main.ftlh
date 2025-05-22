<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <div class="container mt-4">

        <#if isGuest?? && isGuest>
            <h3 class="mb-3">Все публикации</h3>
            <#list feed as post>
                <div class="card mb-3 bg-dark text-white">
                    <div class="card-body">
                        <h5>${post.user.nickName}</h5>
                        <img src="/profile/image/${post.imageUrlString}" class="img-fluid rounded mb-2">
                        <p>${post.description}</p>
                    </div>
                </div>
            </#list>
        <#elseif suggestedUsers??>
            <h4>Вы пока ни на кого не подписаны</h4>
            <p>Попробуйте подписаться на кого-нибудь:</p>
            <ul>
                <#list suggestedUsers as user>
                    <li>${user.nickName}</li>
                </#list>
            </ul>
        <#else>
            <h3 class="mb-3">Ваша лента</h3>
            <#list feed as post>
                <div class="card mb-3 bg-dark text-white">
                    <div class="card-body">
                        <h5>${post.user.nickName}</h5>
                        <img src="/profile/image/${post.imageUrlString}" class="img-fluid rounded mb-2">
                        <p>${post.description}</p>
                    </div>
                </div>
            </#list>
        </#if>

    </div>
</@main.layout>
