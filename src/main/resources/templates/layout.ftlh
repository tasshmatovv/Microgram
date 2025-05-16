<#import "spring.ftl" as spring>
<#macro layout>
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Microgram</title>
        <link rel="shortcut icon" href="data:," type="image/x-icon">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
        <style>
            body {
                background-color: #000;
                color: #fff;
                font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
            }
            .sidebar {
                width: 240px;
                height: 100vh;
                position: fixed;
                background-color: #000;
                border-right: 1px solid #222;
                padding-top: 20px;
                padding-right: 20px;
                padding-left: 20px;
            }
            .sidebar a {
                display: block;
                color: #fff;
                padding: 15px 20px;
                text-decoration: none;
                font-size: 16px;
                font-weight: 500;
            }
            .sidebar a:hover {
                background-color: #121212;
                border-radius: 10px;
            }
            .content {
                margin-left: 240px;
                padding: 30px;
            }
        </style>
    </head>
    <body>

    <div class="sidebar">
        <a href="/"><i class="bi bi-house-door"></i>Microgram</a>
        <#if SPRING_SECURITY_CONTEXT??>
            <#assign user = SPRING_SECURITY_CONTEXT.authentication.principal>
            <#assign roles = SPRING_SECURITY_CONTEXT.authentication.authorities?map(it -> it.authority)>
            <a href="/profile"><i class="bi bi-person-circle"></i> Профиль</a>
            <a href="/post/create"><i class="bi bi-plus-square"></i> Создать</a>
            <a href="/logout" class="btn btn-outline-light">Выйти</a>
        <#else>
            <a href="/auth/login" class="btn btn-outline-light">Войти</a>
        </#if>
    </div>

    <div class="content">
        <#nested spring>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
    </html>
</#macro>