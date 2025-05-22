<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <head>
        <meta charset="UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
        <style>
            body {
                background-color: #000;
                color: #fff;
            }
            .profile-container {
                max-width: 935px;
                margin: 0 auto;
                padding: 40px 20px;
            }
            .profile-header {
                display: flex;
                align-items: center;
                gap: 40px;
                margin-bottom: 30px;
            }
            .avatar {
                width: 150px;
                height: 150px;
                border-radius: 50%;
                object-fit: cover;
            }
            .profile-info {
                flex: 1;
            }
            .profile-info .top-row {
                display: flex;
                align-items: center;
                gap: 20px;
                margin-bottom: 20px;
            }
            .profile-info h2 {
                font-size: 26px;
                font-weight: 400;
                margin: 0;
            }
            .btn-custom {
                background-color: #262626;
                color: #fff;
                border: 1px solid #333;
                padding: 6px 18px;
                border-radius: 8px;
                font-size: 14px;
            }
            .profile-stats {
                display: flex;
                gap: 30px;
                margin-bottom: 15px;
            }
            .profile-stats div {
                font-size: 16px;
            }
            .profile-stats span {
                font-weight: 500;
                margin-right: 6px;
            }
            .profile-fullname {
                font-weight: 600;
                margin-bottom: 6px;
            }
            .profile-bio {
                font-size: 14px;
                color: #ccc;
            }
            .nav-tabs {
                border-bottom: none;
                margin-top: 30px;
                justify-content: center;
            }
            .nav-link {
                color: #fff;
                border: none;
                font-size: 14px;
            }
            .nav-link.active {
                border-bottom: 1px solid #fff;
            }
            .gallery {
                display: grid;
                grid-template-columns: repeat(3, 1fr);
                gap: 3px;
                margin-top: 30px;
            }
            .gallery img {
                width: 100%;
                height: 310px;
                object-fit: cover;
            }
            .gallery-placeholder {
                text-align: center;
                margin-top: 60px;
            }
            .gallery-placeholder i {
                font-size: 64px;
                color: #555;
            }
            .gallery-placeholder p {
                margin-top: 15px;
                font-size: 18px;
                color: #aaa;
            }
            .avatar-wrapper {
                position: relative;
                display: inline-block;
            }
            .edit-avatar-btn {
                position: absolute;
                bottom: 0;
                right: 0;
                width: 36px;
                height: 36px;
                background-color: #111;
                border: 1px solid #333;
                border-radius: 50%;
                display: flex;
                align-items: center;
                justify-content: center;
                color: #fff;
                cursor: pointer;
                transition: background 0.2s ease;
            }
            .edit-avatar-btn:hover {
                background-color: #222;
            }
            .modal-content {
                background-color: #1c1c1c;
                color: #fff;
                border: none;
                border-radius: 12px;
            }
            .modal-header, .modal-footer {
                border: none;
            }
            .modal-body input[type="file"] {
                background-color: #262626;
                color: #ccc;
                border: 1px solid #333;
            }
            .btn-primary-custom {
                background-color: #3897f0;
                color: #fff;
                border: none;
            }
            .btn-primary-custom:hover {
                background-color: #2a85d0;
            }

        </style>
    </head>
    <body>
    <div class="profile-container">
        <div class="profile-header">
            <div class="avatar-wrapper position-relative">
                <img src="/profile/avatars/${user.avatarUrl!}" class="avatar" alt="Аватар">
                <button type="button" class="edit-avatar-btn" data-bs-toggle="modal" data-bs-target="#avatarModal">
                    <i class="bi bi-pencil"></i>
                </button>
            </div>
            <div class="profile-info">
                <div class="top-row">
                    <h2>${user.nickName!"Имя"}</h2>
                    <a href="/profile/redactProfile" class="btn btn-custom">Редактировать профиль</a>
                </div>
                <div class="profile-stats">
                    <div><span>${postCount}</span> публикаций</div>
                    <div><span>${followerCount!0}</span> подписчиков</div>
                    <div><span>${followedCount!0}</span> подписок</div>
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
                <p>Поделиться фото</p>
                <p>Фото, которыми вы делитесь, будут показываться в вашем профиле.</p>
                <a href="/post/create" class="btn btn-custom">Поделитесь своим первым фото</a>
            </div>
        <#else>
            <div class="gallery">
                <#list posts as post>
                    <a href="post/details/${post.id}">
                        <img src="/profile/image/${post.imageUrlString}" alt="Post Image">
                    </a>
                </#list>
            </div>
        </#if>
    </div>

    <div class="modal fade" id="avatarModal" tabindex="-1" aria-labelledby="avatarModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <form class="modal-content" action="/profile/editAvatar" method="post" enctype="multipart/form-data">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <div class="modal-header">
                    <h5 class="modal-title" id="avatarModalLabel">Изменить аватар</h5>
                    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Закрыть"></button>
                </div>
                <div class="modal-body">
                    <input type="file" name="avatar" accept="image/*" class="form-control mb-3">
                    <input type="hidden" name="existingAvatar" value="${user.avatarUrl!}">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Отмена</button>
                    <button type="submit" class="btn btn-primary-custom">Обновить</button>
                </div>
            </form>
        </div>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</@main.layout>
