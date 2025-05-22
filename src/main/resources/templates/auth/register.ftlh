<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Pacifico&display=swap" rel="stylesheet">

    <style>
        body {
            background-color: #000;
            font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
            color: #fff;
        }

        .registration-container {
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .registration-card {
            width: 100%;
            max-width: 350px;
            padding: 40px 32px;
            background-color: #121212;
            border: 1px solid #262626;
            border-radius: 8px;
            text-align: center;
        }

        .logo {
            font-family: 'Pacifico', cursive;
            font-size: 40px;
            color: #fff;
            margin-bottom: 24px;
        }

        .form-group {
            text-align: left;
        }

        .form-group label {
            font-size: 12px;
            color: #8e8e8e;
            margin-bottom: 4px;
        }

        .form-control {
            background-color: #262626;
            border: 1px solid #333;
            color: #fff;
            font-size: 14px;
        }

        .form-control::placeholder {
            color: #8e8e8e;
        }

        .btn-primary {
            background-color: #0095f6;
            border: none;
            font-weight: bold;
        }

        .btn-primary:hover {
            background-color: #1877f2;
        }

        .error-block {
            color: #ff4d4d;
            font-size: 12px;
            margin-top: 4px;
        }

        .login-link {
            margin-top: 20px;
            font-size: 14px;
            color: #8e8e8e;
        }

        .login-link a {
            color: #0095f6;
            text-decoration: none;
        }

        .login-link a:hover {
            text-decoration: underline;
        }
    </style>

    <div class="registration-container">
        <div class="registration-card">
            <div class="logo">Microgram</div>

            <form action="/auth/register" method="post" enctype="multipart/form-data">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />

                <div class="form-group mb-3">
                    <label for="userRegisterDto.nickName">Никнейм</label>
                    <@spring.formInput "userRegisterDto.nickName" 'class="form-control" id="userRegisterDto.nickName" placeholder="Ваш никнейм"' />
                    <div class="error-block">
                        <@spring.showErrors "<br>" "error-message" />
                    </div>
                    <#if nickError??>
                        <div class="error text-danger">${nickError}</div>
                    </#if>
                </div>

                <div class="form-group mb-3">
                    <label for="userRegisterDto.fullName">Имя и фамилия</label>
                    <@spring.formInput "userRegisterDto.fullName" 'class="form-control" id="userRegisterDto.fullName" placeholder="Ваше имя"' />
                    <div class="error-block"><@spring.showErrors "<br>" "error-message" /></div>
                </div>

                <div class="form-group mb-3">
                    <label for="userRegisterDto.email">Электронная почта</label>
                    <@spring.formInput "userRegisterDto.email" 'class="form-control" id="userRegisterDto.email"' />
                    <div class="error-block">
                        <@spring.showErrors "<br>" "error-message" />
                    </div>
                    <#if emailError??>
                        <div class="error text-danger">${emailError}</div>
                    </#if>
                </div>

                <div class="form-group mb-4">
                    <label for="userRegisterDto.password">Пароль</label>
                    <@spring.formInput "userRegisterDto.password" 'class="form-control" id="userRegisterDto.password" type="password" placeholder="••••••••"' />
                    <div class="error-block"><@spring.showErrors "<br>" "error-message" /></div>
                </div>

                <input type="file" name="avatar"/>

                <button type="submit" class="btn btn-primary w-100 py-2">Зарегистрироваться</button>
            </form>

            <div class="login-link">
                Уже есть аккаунт? <a href="/auth/login">Войти</a>
            </div>
        </div>
    </div>
</@main.layout>
