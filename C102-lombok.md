[↑ Back](./README.md)

# `C102` - Project Lombok

## Highlights

### Adding the dependency

Add the following element to `pom.xml`:

```xml
<!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
<dependency>
   <groupId>org.projectlombok</groupId>
   <artifactId>lombok</artifactId>
   <version>1.18.48</version>
   <scope>provided</scope>
</dependency>
```

Since JDK 23, `javac` does not run annotation processors found on the classpath implicitly. Add the following property to the `pom.xml` to let Lombok generate the code when building using Maven:

```xml
<properties>
   <maven.compiler.proc>full</maven.compiler.proc>
</properties>
```

### Annotation `@NonNull` ([Reference](https://projectlombok.org/features/NonNull))

You can use `@NonNull` on a record component, or a parameter of a method or constructor. This will cause to lombok generate a null-check statement for you.

#### Places

1. method parameter
1. constructor parameter

#### Configuration

not required for you

### Annotation `@Cleanup` ([Reference](https://projectlombok.org/features/Cleanup))

You can use `@Cleanup` to ensure a given resource is automatically cleaned up before the code execution path exits your current scope.

#### Place

resource declaration

#### Configuration

not required for you

### Annotation `@Getter/@Setter` ([Reference](https://projectlombok.org/features/GetterSetter))

You can annotate any field with `@Getter` and/or `@Setter`, to let lombok generate the default getter/setter automatically. You can also put a `@Getter` and/or `@Setter` annotation on a class. In that case, it's as if you annotate all the non-static fields in that class with the annotation.

#### Places

1. class
1. field

#### Configuration

1. parameter `value`: the visibility modifier
   1. value `AccessLevel.PUBLIC`: public visibility
   1. value `AccessLevel.PROTECTED`: protected visibility
   1. value `AccessLevel.PACKAGE`: package visibility
   1. value `AccessLevel.PRIVATE`: private visibility
   1. value `AccessLevel.NONE`: disable the generation

### Annotation `@ToString` ([Reference](https://projectlombok.org/features/ToString))

Annotating a class with `@ToString` will cause lombok to generate an implementation of the `toString()` method. You use configuration options to specify whether field names should be included but otherwise the format is fixed: the class name followed by parentheses containing fields separated by commas, e.g. `MyClass(foo=123, bar=234)`.

#### Place

class

#### Configuration

1. parameter `onlyExplicitlyIncluded`: how the fields will be used
   1. value `false`: you must annotate all the excluded fields with `@ToString.Exclude` (**default**)
   1. value `true`: you must annotate all the included fields with `@ToString.Include` 

Methods can be also added to the generation by annotating them with `@ToString.Include`.

### Annotation `@EqualsAndHashCode` ([Reference](https://projectlombok.org/features/EqualsAndHashCode))

Any class definition may be annotated with `@EqualsAndHashCode` to let lombok generate implementations of the `equals(Object other)` and `hashCode()` methods. By default, it'll use all non-static, non-transient fields, but you can modify which fields are used (and even specify that the output of various methods is to be used) by marking type members with `@EqualsAndHashCode.Include` or `@EqualsAndHashCode.Exclude`. Alternatively, you can specify exactly which fields or methods you wish to be used by marking them with `@EqualsAndHashCode.Include` and using `@EqualsAndHashCode(onlyExplicitlyIncluded = true)`.

#### Place

class

#### Configuration

parameter `onlyExplicitlyIncluded`: how the fields will be used

1. value `false`: you must annotate all the excluded fields with `@EqualsAndHashCode.Exclude` (**default**)
1. value `true`: you must annotate all the included fields with `@EqualsAndHashCode.Include` 

### Annotation `@NoArgsConstructor` / `@RequiredArgsConstructor` / `@AllArgsConstrutor` ([Reference](https://projectlombok.org/features/constructor))


This set of `3` annotations generate a constructor that will accept 1 parameter for certain fields, and simply assigns this parameter to the field.

`@NoArgsConstructor` will generate a constructor with no parameters. If this is not possible (because of final fields), a compiler error will result instead, unless `@NoArgsConstructor(force = true)` is used, then all final fields are initialized with `0` / `false` / `null`. For fields with constraints, such as `@NonNull` fields, no check is generated,so be aware that these constraints will generally not be fulfilled until those fields are properly initialized later. Certain java constructs, such as hibernate and the Service Provider Interface require a no-args constructor. This annotation is useful primarily in combination with either `@Data` or one of the other constructor generating annotations.

`@RequiredArgsConstructor` generates a constructor with 1 parameter for each field that requires special handling. All non-initialized final fields get a parameter, as well as any fields that are marked as `@NonNull` that aren't initialized where they are declared. For those fields marked with `@NonNull`, an explicit null check is also generated. The constructor will throw a NullPointerException if any of the parameters intended for the fields marked with `@NonNull` contain `null`. The order of the parameters match the order in which the fields appear in your class.

`@AllArgsConstructor` generates a constructor with 1 parameter for each field in your class. Fields marked with `@NonNull `result in `null` checks on those parameters.

#### Place

class

#### Configuration

1. parameter `access`: the visibility modifier
   1. value `AccessLevel.PUBLIC`: public visibility
   1. value `AccessLevel.PROTECTED`: protected visibility
   1. value `AccessLevel.PACKAGE`: package visibility
   1. value `AccessLevel.PRIVATE`: private visibility
   1. value `AccessLevel.NONE`: disable the generation
1. parameter `force`: whether generate a dummy assignment for each `final` field (default: `false`)

   Required for the `@NoArgsConstructor` when using the `@Value` annotation to make the class immutable.

### Annotation `@SneakyThrows` ([Reference](https://projectlombok.org/features/SneakyThrows))

`@SneakyThrows` can be used to sneakily throw checked exceptions without actually declaring this in your method's throws clause. This somewhat contentious ability should be used carefully, of course. The code generated by lombok will not ignore, wrap, replace, or otherwise modify the thrown checked exception; it simply fakes out the compiler. On the JVM (class file) level, all exceptions, checked or not, can be thrown regardless of the `throws` clause of your methods, which is why this works.

#### Place

method

#### Configuration

not required for you

### Annotation `@Data` ([Reference](https://projectlombok.org/features/Data))

A shortcut for `@ToString`, `@EqualsAndHashCode`, `@Getter` on all fields,` @Setter` on all non-final fields, and `@RequiredArgsConstructor`.

#### Place

class

#### Configuration

not acceptible (use the previous annotations individually to change the behavior)

### Annotation `@Value` ([Reference](https://projectlombok.org/features/Value))

`@Value` is the immutable variant of `@Data`; all fields are made private and final by default, and setters are not generated. The class itself is also made `final` by default, because immutability is not something that can be forced onto a subclass. Like `@Data`, useful `toString()`, `equals()` and `hashCode()` methods are also generated, each field gets a getter method, and a constructor that covers every argument (except final fields that are initialized in the field declaration) is also generated.

#### Place

class

#### Configuration

not acceptible (use the previous annotations individually to change the behavior)

## Explanation


Java requires a lot of repetitive code, the so-called *boilerplate*: getters, setters, constructors, `toString()`, `equals()` and `hashCode()`. This code is tedious to write, and it is easy to forget to update it when a field is added or renamed.

*Project Lombok* is a library that **generates this code for you at compile time**. You only describe *what you want* using annotations, and the compiler produces the methods. The generated methods are present in the compiled `.class` file (you can check it using the *Structure* view of your IDE), but they do not appear in your source code.

### Example

Without Lombok:

```java
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    @Override
    public boolean equals(Object o) { /* ... */ }

    @Override
    public int hashCode() { /* ... */ }

    @Override
    public String toString() {
        return "Point(x=" + x + ", y=" + y + ")";
    }
}
```

With Lombok the same class:

```java
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Point {
    private int x;
    private int y;
}
```

### How does it work?

Lombok is an *annotation processor*: the Java compiler calls it while compiling your code, and it modifies the syntax tree of your classes by adding the generated members. This explains the following facts:

1. The dependency is needed **only for compiling**, hence the `provided` scope in the `pom.xml`.
1. Your IDE must understand Lombok too, otherwise it highlights the generated methods as errors. IDEA supports Lombok by default (make sure that *annotation processing* is enabled in the settings).
1. Since JDK 23 annotation processors must be enabled explicitly in the build (see the property `maven.compiler.proc` in the dependency section above).

### Good to know

1. **Generated code is regular code.** The generated getters, constructors, etc. can be called from other classes like hand-written ones. If you add a method with the same name yourself, Lombok skips the generation.
1. **Access levels can be restricted.** For example `@Setter(AccessLevel.PACKAGE)` or `@NoArgsConstructor(access = AccessLevel.PRIVATE)` are useful when a library (e.g. Jackson) needs a member, but you do not want to expose it to the rest of the application.
1. **Select the fields of `equals()` consciously.** Two objects usually should be identical based on their *identifier* (e.g. `code`), not on all the fields. Use `@EqualsAndHashCode(onlyExplicitlyIncluded = true)` together with `@EqualsAndHashCode.Include`. Always generate `equals()` and `hashCode()` together, because collections (e.g. `HashSet`) rely on both.
1. **Immutable classes** have only `final` fields, no setters, and a constructor that sets all the fields. The annotation `@Value` creates such a class. When a library needs a no-args constructor for a class with `final` fields, use `@NoArgsConstructor(force = true)`.
1. **`@Data` is convenient, but coarse.** It generates everything with the default settings. Prefer the individual annotations when you need to control the visibility or the fields used by `equals()`.
1. **Use `@ToString.Exclude` for large or recursive fields** (e.g. arrays), to keep the output readable and to avoid endless recursion between objects referring to each other.

## References

### Documentation

Check the reference for all the annotations (added after the names of the annotations).

### Tutorials

1. [Baeldung | Introduction to Project Lombok](https://www.baeldung.com/intro-to-project-lombok)
1. [Baeldung | Setting up Lombok with Eclipse and Intellij](https://www.baeldung.com/lombok-ide)
