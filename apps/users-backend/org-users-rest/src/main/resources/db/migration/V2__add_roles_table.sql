CREATE TABLE IF NOT EXISTS roles (
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

ALTER TABLE users
    ADD COLUMN role_id INTEGER;

ALTER TABLE users
    ADD CONSTRAINT fk_users_role
        FOREIGN KEY (role_id)
        REFERENCES roles(id);

INSERT INTO roles (name) SELECT DISTINCT role FROM users;

UPDATE users SET role_id = (SELECT id FROM roles WHERE roles.name = users.role);

ALTER TABLE users DROP COLUMN role;