<#import "../layout.ftlh" as main>
<@main.layout ; spring>

    <head>
        <meta charset="UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #000;
                color: #fff;
            }
            .form-container {
                max-width: 500px;
                margin: 60px auto;
                padding: 30px;
                background-color: #1c1c1c;
                border-radius: 12px;
                box-shadow: 0 4px 16px rgba(0,0,0,0.6);
            }
            .form-container h2 {
                text-align: center;
                margin-bottom: 25px;
            }
            .form-control, .form-label {
                color: #fff;
            }
            .form-control::placeholder {
                color: #aaa;
            }
            .btn-custom {
                background-color: #262626;
                color: #fff;
                border: 1px solid #333;
                padding: 10px 20px;
                border-radius: 8px;
                width: 100%;
                margin-top: 20px;
            }
            .btn-custom:hover {
                background-color: #333;
            }
        </style>
    </head>

    <body>
    <div class="form-container">
        <h2>Создать публикацию</h2>

        <form action="/post/create" method="post" enctype="multipart/form-data">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <div class="mb-3">
                <label for="imageUrl" class="form-label">Изображение:</label>
                <input type="file" id="imageUrl" name="imageUrl" class="form-control">
                <@spring.bind path="post.imageUrl" />
                <#if spring.status.error>
                    <div style="color: red;">
                        <#list spring.status.errorMessages as errorMsg>
                            ${errorMsg}<br>
                        </#list>
                    </div>
                </#if>
            </div>


            <div class="mb-3">
                <label for="post.description" class="form-label">Описание публикации</label>
                <@spring.formInput "post.description" 'class="form-control" id="post.description" placeholder="Напишите что-нибудь..."' />
            </div>

            <button type="submit" class="btn btn-custom">Опубликовать</button>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>

</@main.layout>
