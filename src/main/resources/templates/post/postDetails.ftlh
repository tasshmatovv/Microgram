<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="d-flex align-items-center mb-3">
                    <img src="/profile/avatars/${post.user.avatar}" class="rounded-circle me-3" width="40" height="40" alt="User Avatar">
                    <h5 class="mb-0">${post.user.nickName}</h5>
                </div>

                <div class="mb-3">
                    <img src="/profile/image/${post.imageUrlString}" alt="Post Image" class="post-image">
                </div>

                <div class="d-flex mb-3">
                    <button class="btn btn-outline-light me-2">
                        <i class="bi bi-heart"></i> 0
                    </button>
                    <button class="btn btn-outline-light me-2">
                        <i class="bi bi-chat"></i>0
                    </button>
                </div>

                <div class="mb-3">
                    <p>${post.description}</p>
                </div>

                <div class="text-muted small">
                    <i class="bi bi-clock"></i> ${post.createdAt}
                </div>

                <div class="mt-4">
                    <h6>Комментарии</h6>
                    <div class="border-top pt-3">
                        <p>Здесь будут комментарии...</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <style>
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }
        .btn-outline-light {
            border-color: #333;
        }
        .btn-outline-light:hover {
            background-color: #333;
        }
    </style>
</@main.layout>
