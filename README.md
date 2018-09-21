# blog-api

[![Build Status](https://api.travis-ci.org/sarbull/blog-api.svg?branch=master)](https://travis-ci.org/sarbull/blog-api)

[![Deploy to Heroku](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy?template=https://github.com/sarbull/blog-api/tree/master)

Blog's API using Spring and JPA.

# Demo
[https://blog-api-2.herokuapp.com](https://blog-api-2.herokuapp.com/posts)

# Tools
- Swagger
- Spring Data JPA
- H2 for development

# API

## Posts
| Method        | URL                     |
|---------------|-------------------------|
| GET           | /posts                  |
| POST          | /posts                  |
| GET           | /posts/:postId          |
| DELETE        | /posts/:postId          |
| GET           | /posts/:postId/comments |
| POST          | /posts/:postId/comments |

## Posts
![Posts](https://raw.githubusercontent.com/sarbull/blog-api/master/media/posts.png)
