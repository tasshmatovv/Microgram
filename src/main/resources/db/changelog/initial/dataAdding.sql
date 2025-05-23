INSERT INTO USERS(nickname, email, password, fullname, avatarurl, bio, enabled, accounttypeid)
VALUES
('LXTRQ', 'q@q.q', '$2a$12$/i4.Cdg0a3tWHDYu7.mpq.q6sBobm3P3nzxwkfXlFQz0aEiQH.RVa', 'Dima', 'FirstUserAvatar.jpeg', 'Love coding', true, (SELECT ID FROM ACCOUNTTYPE WHERE NAME = 'USER')),
('KABAN', 'w@w.w', '$2a$12$/i4.Cdg0a3tWHDYu7.mpq.q6sBobm3P3nzxwkfXlFQz0aEiQH.RVa', 'Ray', 'SecondUserAvatar.jpeg', 'Like sweeties', true, (SELECT ID FROM ACCOUNTTYPE WHERE NAME = 'USER')),
('FOXER', 'fox@site.com', '$2a$12$/i4.Cdg0a3tWHDYu7.mpq.q6sBobm3P3nzxwkfXlFQz0aEiQH.RVa', 'Alice Fox', 'ThirdUserAvatar.jpeg', 'Frontend wizard', true, (SELECT ID FROM ACCOUNTTYPE WHERE NAME = 'USER')),
('MEGAMAN', 'mega@hero.com', '$2a$12$/i4.Cdg0a3tWHDYu7.mpq.q6sBobm3P3nzxwkfXlFQz0aEiQH.RVa', 'Thomas Mega', 'FourthUserAvatar.jpeg', 'Gaming pro', true, (SELECT ID FROM ACCOUNTTYPE WHERE NAME = 'USER')),
('PIXELART', 'art@pix.com', '$2a$12$/i4.Cdg0a3tWHDYu7.mpq.q6sBobm3P3nzxwkfXlFQz0aEiQH.RVa', 'Nina Pixel', 'FifthUserAvatar.jpeg', 'Digital artist', true, (SELECT ID FROM ACCOUNTTYPE WHERE NAME = 'USER')),
('JAVADEV', 'dev@java.com', '$2a$12$/i4.Cdg0a3tWHDYu7.mpq.q6sBobm3P3nzxwkfXlFQz0aEiQH.RVa', 'Ivan Petrov', 'SixthUserAvatar.jpeg', 'Java backend developer', true, (SELECT ID FROM ACCOUNTTYPE WHERE NAME = 'USER'));


INSERT INTO posts (userId, imageUrl, description, likeCount, commentCount, createdAt)
VALUES
    ((SELECT id FROM users WHERE nickname = 'LXTRQ'), 'post1.jpeg', 'Первый пост в Microgram!', 5, 2, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE nickname = 'LXTRQ'), 'post2.jpeg', 'Работаю над новым проектом!', 3, 1, CURRENT_TIMESTAMP),

    ((SELECT id FROM users WHERE nickname = 'KABAN'), 'post3.jpeg', 'Конфеты — моя слабость 🍬', 10, 5, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE nickname = 'KABAN'), 'post4.jpeg', 'Утренний кофе и хорошее настроение ☕', 7, 3, CURRENT_TIMESTAMP),

    ((SELECT id FROM users WHERE nickname = 'FOXER'), 'post5.jpeg', 'Новая кнопка на сайте — 🔥', 4, 0, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE nickname = 'FOXER'), 'post6.jpeg', 'CSS победил меня снова 😅', 2, 1, CURRENT_TIMESTAMP),

    ((SELECT id FROM users WHERE nickname = 'MEGAMAN'), 'post7.jpeg', 'Прошел босса на первом уровне!', 8, 4, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE nickname = 'MEGAMAN'), 'post8.jpeg', 'Скоро стрим, не пропустите!', 6, 2, CURRENT_TIMESTAMP),

    ((SELECT id FROM users WHERE nickname = 'PIXELART'), 'post9.jpeg', 'Рисунок недели: Cybercat 🐱‍💻', 9, 6, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE nickname = 'PIXELART'), 'post10.jpeg', 'Спидпейнтинг за 30 минут!', 5, 2, CURRENT_TIMESTAMP),

    ((SELECT id FROM users WHERE nickname = 'JAVADEV'), 'post11.jpeg', 'Наконец-то понял, как работает Spring Security!', 11, 3, CURRENT_TIMESTAMP),
    ((SELECT id FROM users WHERE nickname = 'JAVADEV'), 'post12.jpeg', 'Пишу REST API для pet-проекта 💻', 7, 1, CURRENT_TIMESTAMP);

INSERT INTO comments (userId, postId, TEXT, createdAt)
VALUES
((SELECT id FROM users WHERE nickname = 'KABAN'),
 (SELECT id FROM posts WHERE description = 'Первый пост в Microgram!'),
 'Круто, жду обновлений!', CURRENT_TIMESTAMP),

((SELECT id FROM users WHERE nickname = 'FOXER'),
 (SELECT id FROM posts WHERE description = 'Первый пост в Microgram!'),
 'Отличный старт!', CURRENT_TIMESTAMP),

((SELECT id FROM users WHERE nickname = 'PIXELART'),
 (SELECT id FROM posts WHERE description = 'Конфеты — моя слабость 🍬'),
 'О, у меня тоже!', CURRENT_TIMESTAMP),

((SELECT id FROM users WHERE nickname = 'JAVADEV'),
 (SELECT id FROM posts WHERE description = 'Конфеты — моя слабость 🍬'),
 'Надо попробовать, где покупаешь?', CURRENT_TIMESTAMP),

((SELECT id FROM users WHERE nickname = 'MEGAMAN'),
 (SELECT id FROM posts WHERE description = 'CSS победил меня снова 😅'),
 'Не сдавайся, ты справишься!', CURRENT_TIMESTAMP),

((SELECT id FROM users WHERE nickname = 'PIXELART'),
 (SELECT id FROM posts WHERE description = 'Наконец-то понял, как работает Spring Security!'),
 'Ого, круто! Поделишься гайдом?', CURRENT_TIMESTAMP),

((SELECT id FROM users WHERE nickname = 'FOXER'),
 (SELECT id FROM posts WHERE description = 'Наконец-то понял, как работает Spring Security!'),
 'Поздравляю, это непросто!', CURRENT_TIMESTAMP);

INSERT INTO followers (follower_id, followed_id)
VALUES
((SELECT id FROM users WHERE nickname = 'KABAN'),
 (SELECT id FROM users WHERE nickname = 'LXTRQ')),

((SELECT id FROM users WHERE nickname = 'FOXER'),
 (SELECT id FROM users WHERE nickname = 'LXTRQ')),

((SELECT id FROM users WHERE nickname = 'MEGAMAN'),
 (SELECT id FROM users WHERE nickname = 'KABAN')),

((SELECT id FROM users WHERE nickname = 'PIXELART'),
 (SELECT id FROM users WHERE nickname = 'MEGAMAN')),

((SELECT id FROM users WHERE nickname = 'JAVADEV'),
 (SELECT id FROM users WHERE nickname = 'LXTRQ')),

((SELECT id FROM users WHERE nickname = 'JAVADEV'),
 (SELECT id FROM users WHERE nickname = 'KABAN')),

((SELECT id FROM users WHERE nickname = 'JAVADEV'),
 (SELECT id FROM users WHERE nickname = 'FOXER')),

((SELECT id FROM users WHERE nickname = 'JAVADEV'),
 (SELECT id FROM users WHERE nickname = 'MEGAMAN')),

((SELECT id FROM users WHERE nickname = 'JAVADEV'),
 (SELECT id FROM users WHERE nickname = 'PIXELART')),

((SELECT id FROM users WHERE nickname = 'LXTRQ'),
 (SELECT id FROM users WHERE nickname = 'FOXER'));

INSERT INTO likes (user_id, post_id, liked_at)
VALUES
((SELECT id FROM users WHERE nickname = 'LXTRQ'),
 (SELECT id FROM posts WHERE imageUrl = 'post1.jpeg'),
 CURRENT_TIMESTAMP),

((SELECT id FROM users WHERE nickname = 'LXTRQ'),
 (SELECT id FROM posts WHERE imageUrl = 'post2.jpeg'),
 CURRENT_TIMESTAMP),

((SELECT id FROM users WHERE nickname = 'KABAN'),
 (SELECT id FROM posts WHERE imageUrl = 'post1.jpeg'),
 CURRENT_TIMESTAMP),

((SELECT id FROM users WHERE nickname = 'KABAN'),
 (SELECT id FROM posts WHERE imageUrl = 'post3.jpeg'),
 CURRENT_TIMESTAMP),

((SELECT id FROM users WHERE nickname = 'FOXER'),
 (SELECT id FROM posts WHERE imageUrl = 'post7.jpeg'),
 CURRENT_TIMESTAMP),

((SELECT id FROM users WHERE nickname = 'FOXER'),
 (SELECT id FROM posts WHERE imageUrl = 'post9.jpeg'),
 CURRENT_TIMESTAMP),

((SELECT id FROM users WHERE nickname = 'MEGAMAN'),
 (SELECT id FROM posts WHERE imageUrl = 'post11.jpeg'),
 CURRENT_TIMESTAMP),

((SELECT id FROM users WHERE nickname = 'MEGAMAN'),
 (SELECT id FROM posts WHERE imageUrl = 'post12.jpeg'),
 CURRENT_TIMESTAMP),

((SELECT id FROM users WHERE nickname = 'JAVADEV'),
 (SELECT id FROM posts WHERE imageUrl = 'post1.jpeg'),
 CURRENT_TIMESTAMP),

((SELECT id FROM users WHERE nickname = 'JAVADEV'),
 (SELECT id FROM posts WHERE imageUrl = 'post2.jpeg'),
 CURRENT_TIMESTAMP),

((SELECT id FROM users WHERE nickname = 'PIXELART'),
 (SELECT id FROM posts WHERE imageUrl = 'post5.jpeg'),
 CURRENT_TIMESTAMP);
