<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <div class="profile-container">
        <div class="profile-header">
            <img src="/profile/avatars/${user.avatarUrl!}" class="avatar" alt="Аватар">
            <div class="profile-info">
                <div class="top-row d-flex align-items-center">
                    <h2>${user.nickName}</h2>

                    <#if currentUser??>
                        <#if isFollowing>
                            <button class="btn btn-secondary ms-3" disabled>Подписки</button>
                        <#else>
                            <form action="/subscriptions/follow/${user.id}" method="post" style="display: inline; margin-left: 12px;">
                                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                <button type="submit" class="btn btn-primary">Подписаться</button>
                            </form>
                        </#if>
                    </#if>
                </div>

                <div class="profile-stats">
                    <div><span>${postCount}</span> публикаций</div>
                </div>

                <div class="profile-fullname">${user.fullName!""}</div>
                <#if (user.bio!'')?trim != ''>
                    <div class="profile-bio">${user.bio}</div>
                </#if>
            </div>
        </div>

        <ul class="nav nav-tabs">
            <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="#">ПУБЛИКАЦИИ</a>
            </li>
        </ul>

        <#if posts?size == 0>
            <div class="gallery-placeholder">
                <i class="bi bi-camera"></i>
                <p>Пока нет публикаций</p>
            </div>
        <#else>
            <div class="gallery">
                <#list posts as post>
                    <a href="/post/details/${post.id}">
                        <img src="/profile/image/${post.imageUrlString}" alt="Post Image">
                    </a>
                </#list>
            </div>
        </#if>

    </div>

    <style>
        .profile-container { max-width: 935px; margin: 0 auto; padding: 40px 20px; }
        .profile-header { display: flex; align-items: center; gap: 40px; margin-bottom: 30px; }
        .avatar { width: 150px; height: 150px; border-radius: 50%; object-fit: cover; }
        .profile-info { flex: 1; }
        .top-row h2 { font-size: 26px; font-weight: 400; margin: 0; }
        .profile-stats { display: flex; gap: 30px; margin: 20px 0; font-size: 16px; }
        .profile-fullname { font-weight: 600; margin-bottom: 6px; }
        .profile-bio { font-size: 14px; color: #ccc; }
        .gallery { display: grid; grid-template-columns: repeat(3, 1fr); gap: 3px; margin-top: 30px; }
        .gallery img { width: 100%; height: 310px; object-fit: cover; }
        .gallery-placeholder { text-align: center; margin-top: 60px; color: #999; }
        .btn-secondary { background-color: #555; border: none; }
    </style>
</@main.layout>
