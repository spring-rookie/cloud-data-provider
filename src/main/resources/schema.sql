CREATE SCHEMA blog;

CREATE SEQUENCE blog.HIBERNATE_SEQUENCE
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;

CREATE TABLE blog.posts (
  id BIGINT NOT NULL,
  title varchar(255) NOT NULL,
  content TEXT,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  PRIMARY KEY (id)
);

CREATE TABLE blog.comments (
  id BIGINT NOT NULL,
  content TEXT,
  post_id BIGINT NOT NULL,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (post_id) REFERENCES posts(id)
);
