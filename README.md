# cloud-data-provider

[![Build Status](https://api.travis-ci.org/sarbull/blog-api.svg?branch=master)](https://travis-ci.org/sarbull/blog-api)

[![Deploy to Heroku](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy?template=https://github.com/spring-rookie/cloud-data-provider/tree/master)

# Demo
[https://cloud-data-provider.herokuapp.com](https://cloud-data-provider.herokuapp.com/posts)

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
