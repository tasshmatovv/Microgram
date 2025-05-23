<#import "../layout.ftlh" as main>
<@main.layout ; spring>

    <h2>Редактирование профиля</h2>


    <form action="/profile/edit" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="form-group mb-3">
                <label for="nickName">Ник</label>
                <@spring.formInput "userDto.nickName" 'class="form-control" id="nickName"' />
                <div class="text-danger">
                    <@spring.showErrors "nickName" "<br>" />
                </div>
                <#if nickError??>
                    <div class="text-danger">${nickError}</div>
                </#if>
            </div>

            <div class="form-group mb-3">
                <label for="fullName">Полное имя</label>
                <@spring.formInput "userDto.fullName" 'class="form-control" id="fullName"' />
                <div class="text-danger">
                    <@spring.showErrors "fullName" "<br>" />
                </div>
            </div>

            <div class="form-group mb-3">
                <label for="bio">Биография</label>
                <@spring.formTextarea "userDto.bio" 'class="form-control" id="bio" rows="3"' />
                <div class="text-danger">
                    <@spring.showErrors "bio" "<br>" />
                </div>
            </div>

            <button type="submit" class="btn btn-primary">Сохранить</button>
    </form>


</@main.layout>
