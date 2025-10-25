DELETE FROM users WHERE role_id IN (0, 1, 2);
DELETE FROM role;

INSERT INTO role(id, name)
VALUES
    (0, 'User'),
    (1, 'Moderator'),
    (2, 'Admin');
