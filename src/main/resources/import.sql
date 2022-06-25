-- USER
INSERT INTO users (id, username, password) VALUES (1, 'rootadmin', '$2a$10$7TB3GlYYojP3qBKEPoU2Ou1hLsX6/V5iO.V3xMLJCX0rCC0UbrPZm');
INSERT INTO users (id, username, password) VALUES (2, 'rootclient', '$2a$10$7TB3GlYYojP3qBKEPoU2Ou1hLsX6/V5iO.V3xMLJCX0rCC0UbrPZm');

-- ROLE
INSERT INTO roles (id, role, id_user) VALUES (1, 'ROLE_ADMIN', 1);
INSERT INTO roles (id, role, id_user) VALUES (2, 'ROLE_CLIENT', 2);

-- APP
-- INSERT INTO apps (name, current_version, update_URL) VALUES('Build Tool', '0.0.1', '');