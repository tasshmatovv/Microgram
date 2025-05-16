<#import "../layout.ftlh" as main>
<@main.layout ; spring>

    <h2>Создать публикацию</h2>

    <form action="/post/create" method="post" enctype="multipart/form-data">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <div>
            <label for="imageUrl">Изображение:</label>
            <input type="file" id="imageUrl" name="imageUrl" >
        </div>


        <div class="form-group mb-3">
            <label for="post.description">Имя и фамилия</label>
            <@spring.formInput "post.description" 'class="form-control" id="post.description" placeholder="Описание"' />
        </div>


        <div>
            <button type="submit">Опубликовать</button>
        </div>
    </form>

</@main.layout>
