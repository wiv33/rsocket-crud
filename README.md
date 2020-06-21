# RSocket Posts example on routerFunction / Handler

## ready command

- git clone  
`https://github.com/wvi33/rsocket-crud.git`

- build  
  **you could make file the jar by gradle**
  - rsocket-crud
  - rsocket-crud-client

- docker create network  
`docker network create ps_rsocket_posts`

- docker-compose  (__required delay 1000ms__)  
`docker-compose -f docker-compose-server.yml up -d`      
`docker-comopse -f docker-compose-client.yml up -d`  

## end command

- shutdown  
`docker-compose down -f docker-compose-server.yml -f docker-compose-client-yml`

- remove network    
`docker network remove ps_rsocket_posts`


## Running

### REST API (not RESTful)

- base url  

    **`http://localhost:8080`**

- **Retrieve(find)**
> > > see httpie: [git find url](https://github.com/wiv33/rsocket-crud/blob/master/rsocket-crud-client/src/main/java/org/psawesome/rsocketcrudclient/http/handler/Posts-find.http)  
>
> all  
> > /posts  
>  
> one  
> > /posts/1
---

- **create**
> > > see httpie: [git create url](https://github.com/wiv33/rsocket-crud/blob/master/rsocket-crud-client/src/main/java/org/psawesome/rsocketcrudclient/http/handler/Posts.save.http)
>
> > /posts  
> > Content-Type: application/json  
> > {title: "your title", content: "your content"}
>

---

- **update**
> > > see httpie: [git put url](https://github.com/wiv33/rsocket-crud/blob/master/rsocket-crud-client/src/main/java/org/psawesome/rsocketcrudclient/http/handler/Posts-put.http)
>
> > /posts/2  
> > Content-Type: application/json  
> > {title: "update your title", content: "update your content"}  
>
---
- **delete**
> > > see httpie: [git delete url](https://github.com/wiv33/rsocket-crud/blob/master/rsocket-crud-client/src/main/java/org/psawesome/rsocketcrudclient/http/handler/Posts-delete.http)
>
> > /posts/2
>
>
## Create Dockerfile

    ./gradlew docker
    ./gradlew dockerPushDockerHub 

![gradle location](./img/Screenshot%20from%202020-06-21%2021-02-53.png)

---

### Go to client code

[Test case of client](https://github.com/wiv33/rsocket-crud/blob/master/rsocket-crud-client/src/test/java/org/psawesome/rsocketcrudclient/http/PostClientControllerTest.java)

[RSocketRequester Configuration](https://github.com/wiv33/rsocket-crud/blob/master/rsocket-crud-client/src/main/java/org/psawesome/rsocketcrudclient/http/RSocketRequesterBean.java)

[RouteFunction](https://github.com/wiv33/rsocket-crud/blob/master/rsocket-crud-client/src/main/java/org/psawesome/rsocketcrudclient/http/router/MyPostRouter.java)

[Handler](https://github.com/wiv33/rsocket-crud/blob/master/rsocket-crud-client/src/main/java/org/psawesome/rsocketcrudclient/http/handler/MyPostHandler.java)