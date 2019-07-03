# JRCC Document Receiver

A Java Spring Boot Application

# Run

Install [Docker](https://www.docker.com/) and [Docker Compose](https://docs.docker.com/compose/)

Run

```bash
docker-compose up
```

You should get the following output:

```console
document-receiver_1  |
document-receiver_1  |   .   ____          _            __ _ _
document-receiver_1  |  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
document-receiver_1  | ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
document-receiver_1  |  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
document-receiver_1  |   '  |____| .__|_| |_|_| |_\__, | / / / /
document-receiver_1  |  =========|_|==============|___/=/_/_/_/
document-receiver_1  |  :: Spring Boot ::        (v2.1.6.RELEASE)
document-receiver_1  |
document-receiver_1  | 2019-07-03 15:56:52.316  INFO [document-receiver,,,] 1 --- [           main] ca.gov.bc.open.jrccdocumentreceiver.App  : No active profile set, falling back to default profiles: default
document-receiver_1  | 2019-07-03 15:56:53.715  INFO [document-receiver,,,] 1 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Multiple Spring Data modules found, entering strict repository configuration mode!
document-receiver_1  | 2019-07-03 15:56:53.721  INFO [document-receiver,,,] 1 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data repositories in DEFAULT mode.
document-receiver_1  | 2019-07-03 15:56:53.777  INFO [document-receiver,,,] 1 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 22ms. Found 0 repository interfaces.
document-receiver_1  | 2019-07-03 15:56:54.427  INFO [document-receiver,,,] 1 --- [           main] o.s.cloud.context.scope.GenericScope     : BeanFactory id=3f88d53b-ae9f-39e1-99c0-d03265fc0eac
document-receiver_1  | 2019-07-03 15:56:55.229  INFO [document-receiver,,,] 1 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'org.springframework.cloud.autoconfigure.ConfigurationPropertiesRebinderAutoConfiguration' of type [org.springframework.cloud.autoconfigure.ConfigurationPropertiesRebinderAutoConfiguration$$EnhancerBySpringCGLIB$$fd2281ce] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
document-receiver_1  | 2019-07-03 15:56:57.499  INFO [document-receiver,,,] 1 --- [           main] ca.gov.bc.open.document-receiver_1  | 2019-07-03 15:56:57.542  INFO [document-receiver,ee77e12e8953341a,ee77e12e8953341a,false] 1
--- [           main] c.g.b.o.j.DocumentController             : receiving new document
document-receiver_1  | 2019-07-03 15:56:57.748  INFO [document-receiver,ee77e12e8953341a,ee77e12e8953341a,false] 1
--- [           main] c.g.b.o.jrccdocumentreceiver.AppRunner   : Document successfully stored, key: 0788ab3a-f742-4515-8411-374a219e8d0djrcc_document_receiver_document-receiver_1 exited with code 0
```

## local development

Clone [bcgov/jrcc-document-access-libs](https://github.com/bcgov/jrcc-document-access-libs)

```bash
git clone https://github.com/bcgov/jrcc-document-access-libs
```

Get v0.0.1

```bash
cd jrcc-document-access-libs
git checkout v0.0.1
```

Install the libs using the [following instructions](https://github.com/bcgov/jrcc-document-access-libs/blob/v0.0.1/README.md)

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

* [ ] Receive Documents
* [X] Store Documents
* [ ] Publish Notifications
