### Jasypt Spring Boot

Jasypt Spring Boot provides Encryption support for property sources in Spring Boot Applications.

```xml
<!-- Source: https://mvnrepository.com/artifact/com.github.ulisesbocchio/jasypt-spring-boot-starter -->
<dependency>
    <groupId>com.github.ulisesbocchio</groupId>
    <artifactId>jasypt-spring-boot-starter</artifactId>
    <version>3.0.5</version>
</dependency>
```

```java
@EnableEncryptableProperties
public class JasyptApplication {}
```

```yaml
jasypt:
  encryptor:
    #algorithm: PBEWithMD5AndDES
    password: password

app:
  secret: ENC(tnKiYeUdWHmKgA6NwMv4QO0S5GheF1Z7Xqu+EGOss54wBtDQ8AXG7tpqmuzXb730)
  open: openValue
```