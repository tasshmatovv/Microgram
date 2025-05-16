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
                margin-bottom: 40px;
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
            .profile-info h2 {
                font-size: 28px;
                font-weight: 300;
            }
            .profile-stats {
                display: flex;
                gap: 30px;
                margin-top: 15px;
            }
            .profile-stats span {
                font-weight: 500;
            }
            .btn-custom {
                background-color: #262626;
                color: #fff;
                border: 1px solid #333;
                padding: 6px 14px;
                border-radius: 8px;
                font-size: 14px;
            }
            .gallery-placeholder {
                text-align: center;
                margin-top: 60px;
            }
            .gallery-placeholder i {
                font-size: 48px;
                color: #888;
            }
            .gallery-placeholder p {
                margin-top: 15px;
                font-size: 18px;
                color: #aaa;
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
        </style>
    </head>
    <body>
    <div class="profile-container">
        <div class="profile-header">
            <img src="/profile/avatars/${user.avatar!''}" class="avatar" alt="Аватар" onerror="this.src='/static/images/noProfilePhoto.jpg'">
            <div class="profile-info">
                <div class="d-flex align-items-center gap-3">
                    <h2>${user.nickName!"Имя"} ${user.fullName!""}</h2>
                    <a href="/profile/redactProfile" class="btn btn-custom">Редактировать профиль</a>
                </div>
                <div class="profile-stats mt-3">
                    <div><span>0</span> публикаций</div>
                    <div><span>4</span> подписчиков</div>
                    <div><span>5</span> подписок</div>
                </div>
            </div>
        </div>

        <ul class="nav nav-tabs">
            <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="#">ПУБЛИКАЦИИ</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">СОХРАНЕННОЕ</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">ОТМЕТКИ</a>
            </li>
        </ul>

        <div class="gallery-placeholder">
            <i class="bi bi-camera"></i>
            <p>Поделиться фото</p>
            <p>Фото, которыми вы делитесь, будут показываться в вашем профиле.</p>
            <a href="#" class="btn btn-custom">Поделитесь своим первым фото</a>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</@main.layout>
