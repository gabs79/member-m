# Gabriel Enriquez - Programming Excercise API

Given the excercise at hand, I came up with 2 designs/implementation that I would like to discuss.

"Images speak a thousdand words", so... 
First things first, the "requierements". Overall, this needed to be a "simple API" for the given xml resource. This could be as simple as:
![User View ](UserView.jpg)

### Option A - Microservices
![Process View - Microservices ](ProcessViewA.jpg)

**Pros:**
- Each container/component could be managed (deployment, maintained, etc) separately. For instance, batch process could come out to its own process and probably verically scale more that given machine. More about the current batch/ETL process ahead. 
- Separate teams could focus on different components, even using different tech stack.
- Several -ilities (salability, high-availability, etc) can be handled with an orechestration service (e.g., k8n).

**Cons:**
- Mostly, with this added distributed flexibility, we are adding complexity for maintaining,distributing, development, deployment, etc.

For this main reason, and because it simply fits the "current requirements", I opted for Option B, see below.

##### Option A INSTALLATION

https://github.com/gabs79/member-api
**Note**, this steps are mostly for demostration purposes about this working part, but these steps should have been simplified further with a docker-compose-like option. But needed more time and effort. Overall, part of the added complexity on these microservices for only this excercise. 
1. Using docker, run an instance of MySQL

```
docker run --name members-mysql -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=members-db -e MYSQL_USER=sa -e MYSQL_PASSWORD=password -d mysql:5.6
```

1. Build 'member-api' Spring Boot applications with Maven. From it's root folder, use command ( (./mvnw for Linux)):
    1. create deliverable jar

```
mvnw.cmd install
```

1. Build docker image for 'member-api'.

```
docker build -t members-api .
```

1. Run docker 'member-api'.

```
docker run -d -p 8081:8081 --name members-api --link members-mysql:mysql members-api
```

1. Repeat steps similar steps for 'member-ui'. Run command will look like.

```
docker run -d -p 8082:8082 --name members-ui --link members-api:members-api members-ui
```



### Option B - Monolith
![Process View - Monolith ](ProcessViewB.jpg)

Pros and Cons, pretty much opposite to Option-A.
- **Single point of failure**? No problem, spawn separate replica(s), especially if this is mostly read-only data (see assumptions below). The obvious trade-off will be between horizontal-scalability (distributed) vs consistency. But these trade-offs and/or decisions will have to be made based on actual business needs.

##### Option B INSTALLATION

- Execute jar x by double clicking (self contained and will spawn a Java process). Or if java is installed and want to see console, enter on command line "java -jar x". Executable jar conveniently uploaded to .

- OR, to build jar on your cloned env then,
```
mvnw.cmd install
```
