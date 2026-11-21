[↑ Tutorials](README.md)

# Cinema – the model and the repository

The practice sheet: a JSON array of *movies*, each with its *actors*. Package: `cinema`.

## 1. From the input to the fields

The field names are the names of the JSON properties: *Jackson* finds the values by these names.

| JSON property | JSON type | Java field |
| --- | --- | --- |
| `title` | `string` | `String title` |
| `genre` | `string` | `String genre` |
| `studio` | `string` | `Studio studio` (the nested enum) |
| `year` | `number`, contains integers | `int year` |
| `actors` | `array`, contains objects, each element represents an *actor* | `Actor[] actors` (*represented as an array*) |
| *actor* `name` | `string` | `String name` |
| *actor* `nationality` | `string` | `String nationality` |
| *actor* `age` | `number`, contains integers | `int age` |

## 2. The class `Movie`

| The sheet says | Code | Why |
| --- | --- | --- |
| *Guarantee that all the fields are accessible only from the same package and derived classes.* | `protected` fields | `protected` = the same package and the subclasses |
| *Prevent other classes from being derived from this class.* | `public final class Movie` |  |
| *Guarantee that the fields cannot be modified after instantiation.* | `final` fields, e.g. `protected final String title;` |  |
| *Field `studio` must be represented as an enum constant (see the details later).* | `Studio studio` | the type is the nested enum |
| *Field `actors` must be represented as an array.* | `Actor[] actors` | *Jackson* reads a JSON array into a Java array as well; the queries use `length` instead of `size()` |
| *Generate a constructor having all the parameters. Use the public visibility.* | `@AllArgsConstructor` |  |
| *Generate a constructor having no parameter. Use the private visibility.* | `@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)` | `force = true`: the `final` fields must get a value (`null`, `0`, `false`); *Jackson* fills them in later |
| *Generate a getter method for each field. Use the public visibility.* | `@Getter` |  |
| *Generate a `toString()` method which contains all the fields except the `actors` field.* | `@ToString`, `@ToString.Exclude` on `actors` |  |
| *Two objects are identical if their `title` and `year` fields are the same.* | `@EqualsAndHashCode(onlyExplicitlyIncluded = true)`, `@EqualsAndHashCode.Include` on `title` and `year` | only the marked fields take part in `equals()` and `hashCode()` |

## 3. The class `Movie.Actor`

A `public static` nested class inside `Movie`.

| The sheet says | Code | Why |
| --- | --- | --- |
| *Guarantee that all the fields are accessible only from the same package.* | no modifier on the fields, e.g. `final String name;` | package-private is the default visibility |
| *Prevent other classes from being derived from this class.* | `public static final class Actor` |  |
| *Guarantee that the fields cannot be modified after instantiation.* | `final` fields |  |
| *Generate a constructor having all the parameters. Use the public visibility.* | `@AllArgsConstructor` |  |
| *Generate a constructor having no parameter. Use the private visibility.* | `@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)` | `force = true`: the `final` fields must get a value (`null`, `0`, `false`); *Jackson* fills them in later |
| *Generate a getter method for each field. Use the public visibility.* | `@Getter` |  |
| *Generate a `toString()` method which contains all the fields.* | `@ToString` |  |
| *Two objects are identical if their `name` fields are the same.* | `@EqualsAndHashCode(onlyExplicitlyIncluded = true)`, `@EqualsAndHashCode.Include` on `name` | only the marked field takes part in `equals()` and `hashCode()` |

## 4. The enum `Movie.Studio`

A `public` nested enum inside `Movie`: one constant per possible value of `studio`, in upper snake case, each mapped to its JSON value by `@JsonProperty`. Without the mapping, *Jackson* would look for a constant named like the JSON value, and reading the document would fail.

```java
public enum Studio {
    @JsonProperty("Paramount Pictures") PARAMOUNT_PICTURES,
    @JsonProperty("Universal Pictures") UNIVERSAL_PICTURES,
    @JsonProperty("Columbia Pictures") COLUMBIA_PICTURES,
    @JsonProperty("Warner Bros.") WARNER_BROS
}
```

## 5. The complete model

```java
package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public final class Movie {

    @EqualsAndHashCode.Include
    protected final String title;

    protected final String genre;

    protected final Studio studio;

    @EqualsAndHashCode.Include
    protected final int year;

    @ToString.Exclude
    protected final Actor[] actors;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
    @ToString
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static final class Actor {

        @EqualsAndHashCode.Include
        final String name;

        final String nationality;

        final int age;
    }

    public enum Studio {
        @JsonProperty("Paramount Pictures") PARAMOUNT_PICTURES,
        @JsonProperty("Universal Pictures") UNIVERSAL_PICTURES,
        @JsonProperty("Columbia Pictures") COLUMBIA_PICTURES,
        @JsonProperty("Warner Bros.") WARNER_BROS
    }
}
```

The sorting tasks (`SortingQueries`) add to this class: `implements Comparable<Movie>` with `compareTo()`, and a static nested `Comparator` class. The final file: [`solutions/cinema/.../Movie.java`](solutions/cinema/cinema/Movie.java).

## 6. Typical mistakes

* **A field name that differs from the JSON property** (e.g. `titles` or a capitalized name): *Jackson* finds the values by the names, so reading the document fails ("Unrecognized property"), and the mandatory part fails with it.
* **An enum constant without `@JsonProperty`**, or with a mistyped JSON value: *Jackson* cannot map the text of the document to a constant, and reading fails.
* **A nested class without `static`** (`public class Actor` inside `Movie`): an inner class needs an instance of the outer class, *Jackson* cannot create it, and the required `Movie.Actor` is not a static nested class.
* **`@Data`, `@Value`, or a constructor, getter, `toString()`, `equals()`, `hashCode()` written by hand**: every member must come from its own *Lombok* annotation; this fails the mandatory part.
* **`@EqualsAndHashCode` without `onlyExplicitlyIncluded = true`**: every field takes part in the comparison, not only the identifier(s); this costs penalty points, and sets, maps and `contains()` behave differently in the queries.
* **`@NoArgsConstructor` without `force = true` on a class with `final` fields**: the constructor cannot leave the `final` fields without a value, so the class does not compile ("variable … not initialized in the default constructor").
* **`private` (or any) modifier on the package-private fields** (in `Movie.Actor`): *accessible only from the same package* means no modifier at all.
* **`private` instead of `protected`** (in `Movie`): *the same package and derived classes* is exactly `protected`.
* **A `@Setter` the sheet does not ask for**: a setter is a forbidden member here (penalty points), and with `final` fields it would not even compile.
* **A wrong visibility of a constructor or a getter** (e.g. a public no-argument constructor where `private` is asked): every generated member is checked (penalty points).
* **A `List` instead of the asked array**: the field must be `Actor[] actors`.
* **Forgetting `@ToString.Exclude` on `actors`**: the `toString()` of the movie would also list its actors.

## 7. The repository

```java
public class MovieRepository extends Repository<Movie>
        implements AggregationQueries<Movie, Movie.Actor, Movie.Studio>,
                   SortingQueries<Movie, Movie.Actor, Movie.Studio>,
                   SearchQueries<Movie, Movie.Actor, Movie.Studio>,
                   FilteringQueries<Movie, Movie.Actor, Movie.Studio>,
                   SetQueries<Movie, Movie.Actor, Movie.Studio>,
                   BasicMapQueries<Movie, Movie.Actor, Movie.Studio>,
                   AggregationMapQueries<Movie, Movie.Actor, Movie.Studio>,
                   GroupingMapQueries<Movie, Movie.Actor, Movie.Studio>,
                   NestedMapQueries<Movie, Movie.Actor, Movie.Studio> {

    public MovieRepository() throws IOException {
        super(Movie.class);
    }

    public static void main(String[] args) throws IOException {
        MovieRepository repository = new MovieRepository();
        System.out.println(repository);
    }
}
```

`T` = `Movie`, `U` = `Movie.Actor`, `V` = `Movie.Studio`. Run `main()`: if every movie is printed, the JSON document has been read, so the field names, the types and the enum mapping are right.

Next: the queries, by interface: [`AggregationQueries`](AggregationQueries.md), [`SortingQueries`](SortingQueries.md), [`SearchQueries`](SearchQueries.md), [`FilteringQueries`](FilteringQueries.md), [`SetQueries`](SetQueries.md), [`BasicMapQueries`](BasicMapQueries.md), [`AggregationMapQueries`](AggregationMapQueries.md), [`GroupingMapQueries`](GroupingMapQueries.md), [`NestedMapQueries`](NestedMapQueries.md).
