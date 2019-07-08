# JRCC Document Receiver

A Receiver application that can store a document and publish a message that a document is ready to be processed

# Run

Install [Docker](https://www.docker.com/) and [Docker Compose](https://docs.docker.com/compose/)

Run

```bash
docker-compose up
```

You should get the following output:

you can interact with the api using this (postman collection)[https://github.com/bcgov/jrcc-document-access-libs/blob/v0.2.1/jrcc-access-api/jrcc-document-api.postman_collection.json], just update the port to `5050` when running on docker

you can access the (rabbit-mq management console here)[http://localhost:15672]

you can access the (redis commander console here)[http://localhost:8081]

## local development

Clone [bcgov/jrcc-document-access-libs](https://github.com/bcgov/jrcc-document-access-libs)

```bash
git clone https://github.com/bcgov/jrcc-document-access-libs
```

Get v0.2.1

```bash
cd jrcc-document-access-libs
git checkout v0.2.1
```

Install the libs using the [following instructions](https://github.com/bcgov/jrcc-document-access-libs/blob/v0.2.1/README.md)

Set environment variables specified in `jrcc-document-receiver\src\main\resources\application.yml`

install using Maven

```bash
cd jrcc-document-receiver
mvn install
```
run

```bash
mvn spring-boot:run
```

## Road Map

* [X] Receive Documents
* [X] Store Documents
* [X] Publish Notifications
