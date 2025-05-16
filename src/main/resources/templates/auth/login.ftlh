<#import "../layou.ftlh" as main>
<@main.layout ; spring>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        .login-container {
            min-height: calc(100vh - 56px);
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }

        .login-wrapper {
            display: flex;
            background-color: #000;
            border: 1px solid #222;
            border-radius: 12px;
            overflow: hidden;
            max-width: 900px;
            width: 100%;
            box-shadow: 0 0 20px rgba(255,255,255,0.05);
        }

        .login-form {
            flex: 1;
            padding: 60px 40px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            background-color: #000;
        }

        .login-form h2 {
            font-size: 28px;
            color: #fff;
            text-align: center;
            margin-bottom: 30px;
        }

        .form-control {
            background-color: #121212;
            border: 1px solid #333;
            border-radius: 8px;
            padding: 12px 15px;
            color: #fff;
            margin-bottom: 16px;
        }

        .form-control::placeholder {
            color: #aaa;
        }

        .login-btn {
            background-color: #0095f6;
            color: #fff;
            border: none;
            padding: 12px;
            border-radius: 8px;
            font-weight: 600;
            margin-top: 10px;
        }

        .login-btn:hover {
            background-color: #007dc1;
        }

        .divider {
            height: 1px;
            background-color: #222;
            margin: 25px 0;
        }

        .bottom-links {
            text-align: center;
            color: #aaa;
            margin-top: 10px;
        }

        .bottom-links a {
            color: #fff;
            text-decoration: none;
            font-weight: 500;
            margin: 0 8px;
        }

        .bottom-links a:hover {
            text-decoration: underline;
        }

        .alert {
            font-size: 14px;
            padding: 10px;
        }
    </style>

    <div class="login-container">
        <div class="login-wrapper">
            <div class="login-form">
                <h2>Microgram</h2>

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
                           placeholder="Электронная почта" required />

                    <input type="password"
                           name="password"
                           class="form-control"
                           placeholder="Пароль" required />

                    <button type="submit" class="login-btn w-100">Войти</button>
                </form>

                <div class="divider"></div>

                <div class="bottom-links">
                    <a href="/auth/register">Регистрация</a> |
                    <a href="/auth/forgotPassword">Забыли пароль?</a>
                </div>
            </div>
        </div>
    </div>

</@main.layout>
