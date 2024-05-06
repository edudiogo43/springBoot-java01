# Sample SpringBoot Project + DockerFile + Postgresql
That is a sample Java springboot project using dockerfile and Postgresql database on docker

## How to use
Just clone it and have fun !

```javascript
git clone https://github.com/edudiogo43/springBoot-java01.git
```

## Docker start Postgresql

```javascript
docker run --name some-postgres -p 5432:5432 -e POSTGRES_PASSWORD=mysecretpassword -d postgres
```