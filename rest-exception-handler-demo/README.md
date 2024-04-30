### Custom REST Exception Handler Library

[wimdeblauwe.github.io](https://wimdeblauwe.github.io/error-handling-spring-boot-starter/current/)

```xml
<dependency>
    <groupId>io.github.wimdeblauwe</groupId>
    <artifactId>error-handling-spring-boot-starter</artifactId>
    <version>4.3.0</version>
</dependency>
```

### Sample

Custom Exception Response

```json
{
  "status": 404,
  "code": "CUSTOM",
  "message": "Custom Exception For Request : demo"
}
```

Validation Violation Request-Response

```json
{
  "vendor": "",
  "price": -1,
  "amount": 11
}
```

```json
{
  "status": 400,
  "code": "VALIDATION_FAILED",
  "message": "Validation failed for object='request'. Error count: 3",
  "fieldErrors": [
    {
      "code": "VALUE_LESS_THAN_MIN",
      "message": "'0' değerinden büyük yada eşit olmalı",
      "property": "price",
      "rejectedValue": -1,
      "path": "price"
    },
    {
      "code": "VALUE_GREATER_THAN_MAX",
      "message": "'10' değerinden küçük yada eşit olmalı",
      "property": "amount",
      "rejectedValue": 11,
      "path": "amount"
    },
    {
      "code": "REQUIRED_NOT_BLANK",
      "message": "boş değer olamaz",
      "property": "vendor",
      "rejectedValue": "",
      "path": "vendor"
    }
  ]
}
```
