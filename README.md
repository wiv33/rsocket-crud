# RSocket Posts example on routerFunction / Handler

## ready command

- git clone  
`https://github.com/wvi33/rsocket-crud.git`

- docker create network  
`docker network create ps_rsocket_posts`

- docker-compose  
`docker-compose -f docker-compose.yaml up -d`

## end command

- shutdown  
`docker-compose down -f docker-compose.yaml`

- remove network    
`docker network remove ps_rsocket_posts`


## Running

### REST API (not RESTful)

- base url  

    `http://localhost:8080`

- find
> > > see httpie: [git find url](https://github.com/wiv33/rsocket-crud/blob/master/rsocket-crud-client/src/main/java/org/psawesome/rsocketcrudclient/http/handler/Posts-find.http)  
>
>
> all  
> > /posts  
>  
> one  
> > /posts/1
---

> > > see httpie: [git create url](https://github.com/wiv33/rsocket-crud/blob/master/rsocket-crud-client/src/main/java/org/psawesome/rsocketcrudclient/http/handler/Posts.save.http)
>
> create
> > /posts  
> > Content-Type: application/json  
> > {title: "your title", content: "your content"}
>

---

> > > see httpie: [git put url](https://github.com/wiv33/rsocket-crud/blob/master/rsocket-crud-client/src/main/java/org/psawesome/rsocketcrudclient/http/handler/Posts-put.http)
>
> update
> > /posts/2  
> > Content-Type: application/json  
> > {title: "update your title", content: "update your content"}  
>
---
> > > see httpie: [git delete url](https://github.com/wiv33/rsocket-crud/blob/master/rsocket-crud-client/src/main/java/org/psawesome/rsocketcrudclient/http/handler/Posts-delete.http)
>
> delete
> > /posts/2
>
>

### Go to test code

[Test case of client](https://github.com/wiv33/rsocket-crud/blob/master/rsocket-crud-client/src/test/java/org/psawesome/rsocketcrudclient/http/PostClientControllerTest.java)