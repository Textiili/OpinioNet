-- Drop tables if they exist
DROP TABLE IF EXISTS game_genre;
DROP TABLE IF EXISTS game_platform;
DROP TABLE IF EXISTS review;
DROP TABLE IF EXISTS game;
DROP TABLE IF EXISTS platform;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS users;

-- Create tables
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);

CREATE TABLE game (
    id SERIAL PRIMARY KEY,
    title VARCHAR(60) NOT NULL,
    developer VARCHAR(40) NOT NULL,
    release_year INT,
    game_description TEXT,
    price FLOAT,
    banner_image_url VARCHAR(255),
    background_image_url VARCHAR(255)
);

CREATE TABLE genre (
    id SERIAL PRIMARY KEY,
    name VARCHAR(60) NOT NULL
);

CREATE TABLE platform (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

CREATE TABLE review (
    id SERIAL PRIMARY KEY,
    review_text TEXT,
    rating FLOAT,
    created_at TIMESTAMP,
    user_id BIGINT,
    game_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (game_id) REFERENCES game(id)
);

CREATE TABLE game_genre (
    game_id BIGINT,
    genre_id BIGINT,
    PRIMARY KEY (game_id, genre_id),
    FOREIGN KEY (game_id) REFERENCES game(id) ON DELETE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES genre(id) ON DELETE CASCADE
);

CREATE TABLE game_platform (
    game_id BIGINT,
    platform_id BIGINT,
    PRIMARY KEY (game_id, platform_id),
    FOREIGN KEY (game_id) REFERENCES game(id) ON DELETE CASCADE,
    FOREIGN KEY (platform_id) REFERENCES platform(id) ON DELETE CASCADE
);

-- Insert genres
INSERT INTO genre (name) VALUES
('undefined'),
('Sokoban'),
('Roguelike Deck-Building'),
('Shoot''Em Up'),
('Metroidvania'),
('Turn-based RPG'),
('Roguelike');

-- Insert platforms
INSERT INTO platform (name) VALUES
('undefined'),
('PC'),
('PlayStation 4'),
('Xbox One'),
('Nintendo Switch');

-- Insert games
INSERT INTO game (title, developer, release_year, game_description, price)
VALUES
('Void Stranger', 'System Erasure', 2023, 'ing very important to you.', 11.79),


