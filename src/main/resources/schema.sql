CREATE TABLE IF NOT EXISTS app_users (
    user_id SERIAL PRIMARY KEY,
    fullname VARCHAR(100) NOT NULL,
    email VARCHAR(200) UNIQUE NOT NULL,
    password VARCHAR(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS app_roles (
    role_id SERIAL PRIMARY KEY,
    role_name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS app_user_role (
    user_id INTEGER,
    role_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES app_users(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (role_id) REFERENCES app_roles(role_id) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (user_id, role_id)
)