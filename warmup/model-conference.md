[↑ Tutorials](README.md)

# Conference – the model and the repository

The practice sheet: a JSON array of *talks*, each with its *attendees*. Package: `conference`.

## 1. From the input to the fields

The field names are the names of the JSON properties: *Jackson* finds the values by these names.

| JSON property | JSON type | Java field |
| --- | --- | --- |
| `title` | `string` | `String title` |
| `speaker` | `string` | `String speaker` |
| `track` | `string` | `Track track` (the nested enum) |
| `duration` | `number`, contains integers | `int duration` |
| `rating` | `number`, contains floating-point numbers | `double rating` |
| `attendees` | `array`, contains objects, each element represents an *attendee* | `List<Attendee> attendees` (*represented as a list*) |
| *attendee* `email` | `string` | `String email` |
| *attendee* `company` | `string` | `String company` |
| *attendee* `age` | `number`, contains integers | `int age` |

## 2. The class `Talk`

| The sheet says | Code | Why |
| --- | --- | --- |
| *Define all the fields having private visibility.* | `private` fields, not `final`; `public class Talk` | only the visibility is prescribed: neither the fields nor the class are `final` |
| *Field `track` must be represented as an enum constant (see the details later).* | `Track track` | the type is the nested enum |
| *Field `attendees` must be represented as a list.* | `List<Attendee> attendees` | the interface `List` as the type of the field |
| *Generate a constructor having all the parameters. Use the public visibility.* | `@AllArgsConstructor` |  |
| *Generate a constructor having no parameter. Use the protected visibility.* | `@NoArgsConstructor(access = AccessLevel.PROTECTED)` | no `force` needed: the fields are not `final` |
| *Generate a getter method for each field. Use the public visibility.* | `@Getter` |  |
| *Generate a `toString()` method which contains all the fields except the `attendees` field.* | `@ToString`, `@ToString.Exclude` on `attendees` |  |
| *Two objects are identical if their `title` fields are the same.* | `@EqualsAndHashCode(onlyExplicitlyIncluded = true)`, `@EqualsAndHashCode.Include` on `title` | only the marked field takes part in `equals()` and `hashCode()` |

## 3. The class `Talk.Attendee`

A `public static` nested class inside `Talk`.

| The sheet says | Code | Why |
| --- | --- | --- |
| *Define all the fields having private visibility.* | `private` fields, not `final`; `public static class Attendee` | only the visibility is prescribed: neither the fields nor the class are `final` |
| *Generate a constructor having all the parameters. Use the public visibility.* | `@AllArgsConstructor` |  |
| *Generate a constructor having no parameter. Use the protected visibility.* | `@NoArgsConstructor(access = AccessLevel.PROTECTED)` | no `force` needed: the fields are not `final` |
| *Generate a getter method for each field. Use the public visibility.* | `@Getter` |  |
| *Generate a setter method for each field. Use the protected visibility.* | `@Setter(AccessLevel.PROTECTED)` |  |
| *Generate a `toString()` method which contains all the fields.* | `@ToString` |  |
| *Two objects are identical if their `email` fields are the same.* | `@EqualsAndHashCode(onlyExplicitlyIncluded = true)`, `@EqualsAndHashCode.Include` on `email` | only the marked field takes part in `equals()` and `hashCode()` |

## 4. The enum `Talk.Track`

A `public` nested enum inside `Talk`: one constant per possible value of `track`, in upper snake case, each mapped to its JSON value by `@JsonProperty`. Without the mapping, *Jackson* would look for a constant named like the JSON value, and reading the document would fail.

```java
public enum Track {
    @JsonProperty("Backend") BACKEND,
    @JsonProperty("Frontend") FRONTEND,
    @JsonProperty("Data") DATA,
    @JsonProperty("Security") SECURITY,
    @JsonProperty("Cloud") CLOUD
}
```

## 5. The complete model

```java
package conference;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Talk {

    @EqualsAndHashCode.Include
    private String title;

    private String speaker;

    private Track track;

    private int duration;

    private double rating;

    @ToString.Exclude
    private List<Attendee> attendees;

    @Getter
    @Setter(AccessLevel.PROTECTED)
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @ToString
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Attendee {

        @EqualsAndHashCode.Include
        private String email;

        private String company;

        private int age;
    }

    public enum Track {
        @JsonProperty("Backend") BACKEND,
        @JsonProperty("Frontend") FRONTEND,
        @JsonProperty("Data") DATA,
        @JsonProperty("Security") SECURITY,
        @JsonProperty("Cloud") CLOUD
    }
}
```

The sorting tasks (`SortingQueries`) add to this class: `implements Comparable<Talk>` with `compareTo()`, and a static nested `Comparator` class. The final file: [`solutions/conference/.../Talk.java`](solutions/conference/conference/Talk.java).

## 6. Typical mistakes

* **A field name that differs from the JSON property** (e.g. `titles` or a capitalized name): *Jackson* finds the values by the names, so reading the document fails ("Unrecognized property"), and the mandatory part fails with it.
* **An enum constant without `@JsonProperty`**, or with a mistyped JSON value: *Jackson* cannot map the text of the document to a constant, and reading fails.
* **A nested class without `static`** (`public class Attendee` inside `Talk`): an inner class needs an instance of the outer class, *Jackson* cannot create it, and the required `Talk.Attendee` is not a static nested class.
* **`@Data`, `@Value`, or a constructor, getter, `toString()`, `equals()`, `hashCode()` written by hand**: every member must come from its own *Lombok* annotation; this fails the mandatory part.
* **`@EqualsAndHashCode` without `onlyExplicitlyIncluded = true`**: every field takes part in the comparison, not only the identifier(s); this costs penalty points, and sets, maps and `contains()` behave differently in the queries.
* **`final` fields or a `final` class where only *private visibility* is asked** (in `Talk` and `Talk.Attendee`): the sheet prescribes only the modifier `private`; every extra modifier is a wrong modifier (penalty points).
* **A wrong visibility of a constructor or a getter** (e.g. a public no-argument constructor where `protected` is asked): every generated member is checked (penalty points).
* **An array or `ArrayList<Attendee>` instead of `List<Attendee>`**: the sheet asks for a list, and the type of the field is the interface.
* **Forgetting `@ToString.Exclude` on `attendees`**: the `toString()` of the talk would also list its attendees.

## 7. The repository

```java
public class TalkRepository extends Repository<Talk>
        implements AggregationQueries<Talk, Talk.Attendee, Talk.Track>,
                   SortingQueries<Talk, Talk.Attendee, Talk.Track>,
                   SearchQueries<Talk, Talk.Attendee, Talk.Track>,
                   FilteringQueries<Talk, Talk.Attendee, Talk.Track>,
                   SetQueries<Talk, Talk.Attendee, Talk.Track>,
                   BasicMapQueries<Talk, Talk.Attendee, Talk.Track>,
                   AggregationMapQueries<Talk, Talk.Attendee, Talk.Track>,
                   GroupingMapQueries<Talk, Talk.Attendee, Talk.Track>,
                   NestedMapQueries<Talk, Talk.Attendee, Talk.Track> {

    public TalkRepository() throws IOException {
        super(Talk.class);
    }

    public static void main(String[] args) throws IOException {
        TalkRepository repository = new TalkRepository();
        System.out.println(repository);
    }
}
```

`T` = `Talk`, `U` = `Talk.Attendee`, `V` = `Talk.Track`. Run `main()`: if every talk is printed, the JSON document has been read, so the field names, the types and the enum mapping are right.

Next: the queries, by interface: [`AggregationQueries`](AggregationQueries.md), [`SortingQueries`](SortingQueries.md), [`SearchQueries`](SearchQueries.md), [`FilteringQueries`](FilteringQueries.md), [`SetQueries`](SetQueries.md), [`BasicMapQueries`](BasicMapQueries.md), [`AggregationMapQueries`](AggregationMapQueries.md), [`GroupingMapQueries`](GroupingMapQueries.md), [`NestedMapQueries`](NestedMapQueries.md).
