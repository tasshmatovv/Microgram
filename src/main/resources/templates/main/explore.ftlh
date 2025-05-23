<#import "../layout.ftlh" as main>
<@main.layout>

    <style>
        body {
            font-family: 'Nunito', sans-serif;
            margin: 0;
            padding: 0;
            color: #fff;
            background: #000;
        }

        .search-container {
            text-align: center;
            margin: 2rem 0;
            position: relative;
            z-index: 1100;
        }

        .search-input {
            padding: 10px 16px;
            border: none;
            border-radius: 12px;
            width: 60%;
            max-width: 500px;
            font-size: 15px;
            background: #121212;
            color: #fff;
            box-shadow: 0 0 5px rgba(255, 255, 255, 0.1);
            transition: background-color 0.3s ease, box-shadow 0.3s ease;
        }

        .search-input::placeholder {
            color: #888;
        }

        .search-input:focus {
            outline: none;
            background: #222;
            box-shadow: 0 0 8px rgba(255, 255, 255, 0.3);
        }

        .search-results {
            position: absolute;
            top: 110%;
            left: 50%;
            transform: translateX(-50%);
            background: #121212;
            border-radius: 12px;
            margin-top: 8px;
            width: 60%;
            max-width: 500px;
            box-shadow: 0 8px 24px rgba(0, 0, 0, 0.8);
            color: #fff;
            overflow: hidden;
            max-height: 300px;
            overflow-y: auto;
            scrollbar-width: thin;
            scrollbar-color: #444 transparent;
        }

        .search-results::-webkit-scrollbar {
            width: 6px;
        }

        .search-results::-webkit-scrollbar-thumb {
            background-color: #444;
            border-radius: 3px;
        }

        .search-result-item {
            display: flex;
            align-items: center;
            padding: 10px 14px;
            cursor: pointer;
            border-bottom: 1px solid #222;
            transition: background-color 0.2s ease;
            background-color: #121212;
        }

        .search-result-item:hover {
            background-color: #222;
        }

        .result-avatar {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            object-fit: cover;
            margin-right: 12px;
            border: 2px solid transparent;
            transition: border-color 0.3s ease;
            flex-shrink: 0;
        }

        .search-result-item:hover .result-avatar {
            border-color: #0095f6;
        }

        .result-text {
            display: flex;
            flex-direction: column;
            justify-content: center;
        }

        .result-username {
            font-weight: 700;
            font-size: 15px;
            color: #fff;
        }

        .result-displayname {
            font-size: 13px;
            color: #aaa;
            margin-top: 2px;
        }

        .search-result-item.no-result {
            color: #666;
            text-align: center;
            cursor: default;
            padding: 14px 0;
        }

        .post-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(180px, 1fr));
            gap: 12px;
            padding: 0 1rem 2rem;
            max-width: 1200px;
            margin: 0 auto;
        }

        .post-grid img {
            width: 100%;
            height: 180px;
            object-fit: cover;
            border-radius: 12px;
            cursor: pointer;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.4);
        }

        .post-grid img:hover {
            opacity: 0.85;
            transform: scale(1.05);
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.7);
        }

        @media (max-width: 768px) {
            .search-input {
                width: 80%;
            }

            .search-results {
                width: 80%;
            }

            .post-grid {
                grid-template-columns: repeat(auto-fill, minmax(140px, 1fr));
                gap: 10px;
            }

            .post-grid img {
                height: 140px;
                border-radius: 10px;
            }
        }

        @media (max-width: 480px) {
            .post-grid {
                grid-template-columns: repeat(auto-fill, minmax(120px, 1fr));
                gap: 8px;
            }

            .post-grid img {
                height: 120px;
                border-radius: 8px;
            }

            .search-input {
                width: 90%;
            }

            .search-results {
                width: 90%;
            }
        }

    </style>

    <div class="container mt-4">


        <h4 class="text-white mb-4">Интересное</h4>

        <div class="search-container">
            <input type="text" id="userSearchInput" placeholder="Найти пользователя..." class="search-input">
            <div id="userSearchResults" class="search-results" style="display:none;"></div>
        </div>

        <div class="row g-2">
            <#if post?? && (post?size > 0)>

                <#list post as p>
                    <div class="col-4">
                        <a href="/post/details/${p.id}">
                            <div class="position-relative">
                                <img src="/profile/image/${p.imageUrlString}" class="img-fluid w-100"
                                     style="object-fit: cover; height: 150px;">
                                <div class="position-absolute top-0 start-0 w-100 h-100"
                                     style="background: rgba(0,0,0,0.1);"></div>
                            </div>
                        </a>
                    </div>
                </#list>

            <#else>
                <div class="text-center text-muted mt-5">
                    <i class="bi bi-camera" style="font-size: 3rem; color: #ffffff"></i>
                    <p class="mt-3" style="color: #ffffff">Пока ничего интересного нет.</p>
                </div>
            </#if>
        </div>

    </div>


    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const userSearchInput = document.getElementById('userSearchInput');
            const userSearchResults = document.getElementById('userSearchResults');
            let debounceTimer;

            userSearchInput.addEventListener('input', function (e) {
                clearTimeout(debounceTimer);

                const query = e.target.value.trim();

                if (query.length < 2) {
                    userSearchResults.style.display = 'none';
                    return;
                }

                debounceTimer = setTimeout(() => {
                    fetchUsers(query);
                }, 300);
            });

            function fetchUsers(query) {
                console.log("Поиск:", query);
                userSearchResults.innerHTML = '<div class="search-result-item">Поиск...</div>';
                userSearchResults.style.display = 'block';

                fetch('/api/user/search?query=' + encodeURIComponent(query))
                    .then(response => {
                        console.log("Response status:", response.status);
                        return response.json();
                    })
                    .then(users => {
                        console.log('Найдено:', users);
                        displayUserResults(users);
                    })
                    .catch(error => {
                        console.error('Ошибка:', error);
                        userSearchResults.innerHTML = '<div class="search-result-item">Ошибка: ' + error.message + '</div>';
                    });
            }

            function displayUserResults(users) {
                if (users.length === 0) {
                    userSearchResults.innerHTML = '<div class="search-result-item no-result">Ничего не найдено</div>';
                    return;
                }

                let resultsHtml = '';
                users.forEach(user => {
                    resultsHtml +=
                        '<div class="search-result-item" onclick="window.location.href=\'/profile/info' + user.id + '\'">' +
                        '<img src="/profile/avatars/' + user.avatarUrl + '" alt="avatar" class="result-avatar">' +
                        '<div class="result-text">' +
                        '<div class="result-username">' + user.fullName + '</div>' +
                        '<div class="result-displayname">' + user.nickName + '</div>' +
                        '</div>' +
                        '</div>';
                });

                userSearchResults.innerHTML = resultsHtml;
            }

            document.addEventListener('click', function (e) {
                if (!userSearchInput.contains(e.target) && !userSearchResults.contains(e.target)) {
                    userSearchResults.style.display = 'none';
                }
            });
        });
    </script>

</@main.layout>
