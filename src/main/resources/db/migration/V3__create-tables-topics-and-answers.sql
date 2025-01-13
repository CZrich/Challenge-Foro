CREATE TABLE topics (
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    date_creation DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status TINYINT NOT NULL DEFAULT FALSE,
    author_id BIGINT NOT NULL,
    course_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES users(id),
    FOREIGN KEY (course_id) REFERENCES courses(id)
);
CREATE TABLE answers (
    id BIGINT AUTO_INCREMENT,
    message TEXT NOT NULL,
    date_creation DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    topic_id BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    solution TINYINT NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id),
    FOREIGN KEY (topic_id) REFERENCES topics(id),
    FOREIGN KEY (author_id) REFERENCES users(id)
);
