# Watchers

Watchers is a website about movies and tv shows, user can register and login in to watchers, after that he is able to add some movies/tv shows he watched to his list. There is a ranking list that shows users with the greatest amount of watched tv shows/movies.

- Administrator is able to add movie, tv show with seasons and episodes.
- User is only available to see the existing movies/tv shows and add them to his list.

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

* Java
* Spring
* Spock
* Docker

### Available actions:
Admin is available to add movies, series, seasons and episodes. User can see added entities by admin and add i.e. movies
to his own list. Every user has his own movie or series list. In future is planned to add top 10 movies and functionality to 
give starts for movies or series. It would be also great to add a posibility to upload an image to a movie or series.