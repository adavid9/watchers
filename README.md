# Watchers

Watchers is a website about movies and tv shows, user can register and login in to watchers, after that he is able to add some movies/tv shows he watched to his list. There is a ranking list that shows users with the greatest amount of watched tv shows/movies.

- Administrator is able to add movie, tv show with seasons and episodes.
- User is only available to see the existing movies/tv shows and add them to his list.

# Not finished - There left a lot things to do!

 - Not finished registration/login system
 - No ranking functionality
 - No pages for actions


# How to run?
> If you are using docker, you can just simply go to the root directory of the project
> and run the following command `docker-compose up` there is prepared a docker-compose file
> that puts the database together with the tables.
>
> If you are not using docker the you have to configure your own MySQL database with the 
> following credentials:
> `username: watchers_admin`
> `password: watchers_admin`
> `database: watchers`
> The database port should be `3306` and the port on which works the application is `8000`
> so before running it ensure that you have those ports free.

### Technologies used in the project.
I decided to use the following technologies in the project.

* JAVA
* SPRING
* HIBERNATE
* MYSQL
* JSP
* JPA
* DOCKER
* GROOVY
* SPOCK
