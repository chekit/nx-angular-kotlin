## Run Redis conatiner
```sh
docker-compose -f docker-compose.yml up -d
```

## Stop Redis container
```sh
docker-compose -f docker-compose.yml down
```

```mermaid
    flowchart LR
        A([User]) <-->|Bearer <JWT>| B[REST API]
        B <-->|GET / POST / PUT / DELETE| C@{ shape: lin-cyl, label: "DB" }
        B <-->|GET, Validation token| D[Validation Service] <--> C
        B <-->|GET| E[Auth Service]
        D <-->|GET| E[Auth Service]
```
