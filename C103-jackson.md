[↑ Back](./README.md)

# `C103` - Jackson

## Highlights

### Adding the dependency

Add the following element to `pom.xml`:

```xml
<!-- https://mvnrepository.com/artifact/tools.jackson.core/jackson-databind -->
<dependency>
    <groupId>tools.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>3.3.0</version>
</dependency>
```

### The `JsonMapper` class

Since *Jackson 3* the package is `tools.jackson.databind` (it was `com.fasterxml.jackson.databind` in *Jackson 2*). The mapper is **immutable**: it is configured using a builder when it is created. The `JsonMapper` class is the `ObjectMapper` implementation for JSON documents.

1. Instantiation

    ```java
    JsonMapper mapper = JsonMapper.builder()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY)
            .enable(MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS)
            .build();
    ```

1. Reading a document from a file

    ```java
    mapper.readValue(File, Class);
    ```

1. Writing a document to a file

    ```java
    mapper.writeValue(File, value);
    ```

1. Exceptions

    The exceptions thrown by *Jackson 3* (`JacksonException`) are **unchecked**, so you do not need a `throws` clause or `@SneakyThrows`.

### Requirements

1. Serialization: getter methods
1. Deserialization: no-args constructor + setter methods (or the `final` fields if `ALLOW_FINAL_FIELDS_AS_MUTATORS` is enabled)

### Settings

| Setting                                      | Purpose                                                                                              |
|----------------------------------------------|------------------------------------------------------------------------------------------------------|
| `SerializationFeature.INDENT_OUTPUT`         | indents the output (disabled by default)                                                             |
| `MapperFeature.SORT_PROPERTIES_ALPHABETICALLY` | **enabled by default in Jackson 3**: disable it to keep the declaration order of the fields        |
| `MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS` | **disabled by default in Jackson 3**: enable it to deserialize into the `final` fields of immutable classes |

### Additional

1. Annotation `@JsonProperty`

   1. to specify the property's name
   1. to specify the enum constant's representation

   Note that the annotations remain in the package `com.fasterxml.jackson.annotation`.

### Migrating from Jackson 2

1. dependency: `com.fasterxml.jackson.core` → `tools.jackson.core`
1. imports: `com.fasterxml.jackson.databind.*` → `tools.jackson.databind.*`
1. `new ObjectMapper()` → `JsonMapper.builder()...build()` (the mapper cannot be reconfigured after it was built)
1. checked `IOException` → unchecked `JacksonException`
1. changed defaults: properties are sorted alphabetically, `final` fields are not mutators

## Explanation


*JSON* (*JavaScript Object Notation*) is a lightweight, text-based format for storing and exchanging structured data. It is used by most web services and many applications for storing data. A JSON document consists of the following elements:

1. **object**: unordered name-value pairs in curly braces, e.g. `{"code": "30356", "bricks": 43}`
1. **array**: an ordered list of values in square brackets, e.g. `[1, 2, 3]`
1. **string**, **number**, **boolean** (`true`/`false`) and `null`

*Jackson* is a library that converts between JSON documents and Java objects automatically:

1. **serialization**: Java object → JSON document
1. **deserialization**: JSON document → Java object

You do not have to parse the text yourself. You write classes whose structure follows the structure of the document, and Jackson fills them in.

### The mapping

| JSON                  | Java                                        |
|-----------------------|---------------------------------------------|
| object                | class (a field for each name)               |
| array                 | array (or a `List`)                         |
| string                | `String`, enum constant                     |
| number (integer)      | `int`, `long`, ...                          |
| number (decimal)      | `double`, ...                               |
| `true` / `false`      | `boolean`                                   |

For example the following document:

```json
{
  "code" : "30356",
  "name" : "Hot Dog Stand polybag",
  "theme" : "City",
  "bricks" : 43,
  "minifigures" : [ { "id" : "cty0878", "name" : "Hot Dog Chef", "quantity" : 1 } ]
}
```

is represented by the following class (see the project [`C1`](./codes/C1)):

```java
class LegoSet {
    private String code;
    private String name;
    private Theme theme;               // enum, the constant "City" is mapped by @JsonProperty("City")
    private int bricks;
    private Minifigure[] minifigures;  // JSON array -> Java array
}
```

The root of the `input.json` of the project [`C1`](./codes/C1) is an array, so it is read as `LegoSet[].class`.

### How does Jackson find the properties?

Jackson works with *properties*: a name in the JSON document is matched with a property of the class. The properties are discovered by the **getter methods** (and the visible fields). This is why the classes need the `@Getter` annotation of *Lombok*.

When the document is read, Jackson:

1. creates the instance using the **no-args constructor** (it can be private, Jackson is able to call it),
1. sets the values using the **setter methods**, or directly the fields when there is no setter (for `final` fields this must be enabled by `ALLOW_FINAL_FIELDS_AS_MUTATORS`, see the section *Settings* above).

If a name of the JSON document does not match any property of the class, Jackson throws an exception by default. If the name of the property differs from the name in the document, use `@JsonProperty("name-in-the-document")`.

### Typical problems

1. **No suitable constructor**: the class has no no-args constructor. Add `@NoArgsConstructor` (using `AccessLevel.PRIVATE` is enough).
1. **`null` or `0` values after reading**: the values could not be set. Check the setters, or the `final` fields (see the section *Settings* above).
1. **Unrecognized field**: the document contains a name that has no matching property.
1. **Cannot deserialize the enum**: the text of the document does not match the name of the enum constant. Use `@JsonProperty` on the constants.
1. **The package is not found**: *Jackson 3* uses the package `tools.jackson.databind`, while most of the tutorials on the web (written for *Jackson 2*) use `com.fasterxml.jackson.databind`.

## References

1. [Jackson 3 Migration Guide](https://github.com/FasterXML/jackson/blob/main/jackson3/MIGRATING_TO_JACKSON_3.md)
1. [Baeldung | Jackson JSON Series](https://www.baeldung.com/jackson)
1. [Baeldung | Intro to the Jackson ObjectMapper](https://www.baeldung.com/jackson-object-mapper-tutorial)
