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

        .login-container {
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .login-card {
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

        .form-control {
            background-color: #262626;
            border: 1px solid #333;
            color: #fff;
            font-size: 14px;
            margin-bottom: 16px;
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

        .alert {
            font-size: 14px;
            padding: 10px;
            margin-bottom: 16px;
        }

        .bottom-links {
            margin-top: 20px;
            font-size: 14px;
            color: #8e8e8e;
        }

        .bottom-links a {
            color: #0095f6;
            text-decoration: none;
        }

        .bottom-links a:hover {
            text-decoration: underline;
        }
    </style>

    <div class="login-container">
        <div class="login-card">
            <div class="logo">Microgram</div>

            <#if RequestParameters.error??>
                <div class="alert alert-danger text-center">
                    Неверный логин или пароль. Попробуйте снова.
                </div>
            </#if>

            <form action="/auth/login" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />

                <input type="email"
                       name="username"
                       class="form-control"
                       placeholder="Электронная почта" />

                <input type="password"
                       name="password"
                       class="form-control"
                       placeholder="Пароль" />

                <button type="submit" class="btn btn-primary w-100 py-2">Войти</button>
            </form>

            <div class="bottom-links">
                <a href="/auth/register">Регистрация</a> |
                <a href="/auth/forgotPassword">Забыли пароль?</a>
            </div>
        </div>
    </div>
</@main.layout>
