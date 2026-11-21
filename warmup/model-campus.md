[↑ Tutorials](README.md)

# Campus – the model and the repository

The practice sheet: a JSON array of *courses*, each with its *students*. Package: `campus`.

## 1. From the input to the fields

The field names are the names of the JSON properties: *Jackson* finds the values by these names.

| JSON property | JSON type | Java field |
| --- | --- | --- |
| `code` | `string` | `String code` |
| `title` | `string` | `String title` |
| `faculty` | `string` | `Faculty faculty` (the nested enum) |
| `credits` | `number`, contains integers | `int credits` |
| `semester` | `number`, contains integers | `int semester` |
| `students` | `array`, contains objects, each element represents a *student* | `Student[] students` (*represented as an array*) |
| *student* `neptun` | `string` | `String neptun` |
| *student* `name` | `string` | `String name` |
| *student* `grade` | `number`, contains integers | `int grade` |

## 2. The class `Course`

| The sheet says | Code | Why |
| --- | --- | --- |
| *Guarantee that all the fields are accessible only from the same package.* | no modifier on the fields, e.g. `final String code;` | package-private is the default visibility |
| *Prevent other classes from being derived from this class.* | `public final class Course` |  |
| *Guarantee that the fields cannot be modified after instantiation.* | `final` fields |  |
| *Field `faculty` must be represented as an enum constant (see the details later).* | `Faculty faculty` | the type is the nested enum |
| *Field `students` must be represented as an array.* | `Student[] students` | *Jackson* reads a JSON array into a Java array as well; the queries use `length` instead of `size()` |
| *Generate a constructor having all the parameters. Use the public visibility.* | `@AllArgsConstructor` |  |
| *Generate a constructor having no parameter. Use the private visibility.* | `@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)` | `force = true`: the `final` fields must get a value (`null`, `0`, `false`); *Jackson* fills them in later |
| *Generate a getter method for each field. Use the public visibility.* | `@Getter` |  |
| *Generate a `toString()` method which contains all the fields except the `students` field.* | `@ToString`, `@ToString.Exclude` on `students` |  |
| *Two objects are identical if their `code` fields are the same.* | `@EqualsAndHashCode(onlyExplicitlyIncluded = true)`, `@EqualsAndHashCode.Include` on `code` | only the marked field takes part in `equals()` and `hashCode()` |

## 3. The class `Course.Student`

A `public static` nested class inside `Course`.

| The sheet says | Code | Why |
| --- | --- | --- |
| *Make the class immutable (having the common features).* | `public static final class Student`, every field `private final` | the short wording: the common features of an immutable class are a `final` class and `private final` fields |
| *Generate a constructor having all the parameters. Use the public visibility.* | `@AllArgsConstructor` |  |
| *Generate a constructor having no parameter. Use the private visibility.* | `@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)` | `force = true`: the `final` fields must get a value (`null`, `0`, `false`); *Jackson* fills them in later |
| *Generate a getter method for each field. Use the public visibility.* | `@Getter` |  |
| *Generate a `toString()` method which contains all the fields.* | `@ToString` |  |
| *Two objects are identical if their `neptun` fields are the same.* | `@EqualsAndHashCode(onlyExplicitlyIncluded = true)`, `@EqualsAndHashCode.Include` on `neptun` | only the marked field takes part in `equals()` and `hashCode()` |

## 4. The enum `Course.Faculty`

A `public` nested enum inside `Course`: one constant per possible value of `faculty`, in upper snake case, each mapped to its JSON value by `@JsonProperty`. Without the mapping, *Jackson* would look for a constant named like the JSON value, and reading the document would fail.

```java
public enum Faculty {
    @JsonProperty("Informatics") INFORMATICS,
    @JsonProperty("Science") SCIENCE,
    @JsonProperty("Engineering") ENGINEERING,
    @JsonProperty("Economics") ECONOMICS,
    @JsonProperty("Humanities") HUMANITIES
}
```

## 5. The complete model

```java
package campus;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public final class Course {

    @EqualsAndHashCode.Include
    final String code;

    final String title;

    final Faculty faculty;

    final int credits;

    final int semester;

    @ToString.Exclude
    final Student[] students;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
    @ToString
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static final class Student {

        @EqualsAndHashCode.Include
        private final String neptun;

        private final String name;

        private final int grade;
    }

    public enum Faculty {
        @JsonProperty("Informatics") INFORMATICS,
        @JsonProperty("Science") SCIENCE,
        @JsonProperty("Engineering") ENGINEERING,
        @JsonProperty("Economics") ECONOMICS,
        @JsonProperty("Humanities") HUMANITIES
    }
}
```

The sorting tasks (`SortingQueries`) add to this class: `implements Comparable<Course>` with `compareTo()`, and a static nested `Comparator` class. The final file: [`solutions/campus/.../Course.java`](solutions/campus/campus/Course.java).

## 6. Typical mistakes

* **A field name that differs from the JSON property** (e.g. `codes` or a capitalized name): *Jackson* finds the values by the names, so reading the document fails ("Unrecognized property"), and the mandatory part fails with it.
* **An enum constant without `@JsonProperty`**, or with a mistyped JSON value: *Jackson* cannot map the text of the document to a constant, and reading fails.
* **A nested class without `static`** (`public class Student` inside `Course`): an inner class needs an instance of the outer class, *Jackson* cannot create it, and the required `Course.Student` is not a static nested class.
* **`@Data`, `@Value`, or a constructor, getter, `toString()`, `equals()`, `hashCode()` written by hand**: every member must come from its own *Lombok* annotation; this fails the mandatory part.
* **`@EqualsAndHashCode` without `onlyExplicitlyIncluded = true`**: every field takes part in the comparison, not only the identifier(s); this costs penalty points, and sets, maps and `contains()` behave differently in the queries.
* **`@NoArgsConstructor` without `force = true` on a class with `final` fields**: the constructor cannot leave the `final` fields without a value, so the class does not compile ("variable … not initialized in the default constructor").
* **`private` (or any) modifier on the package-private fields** (in `Course`): *accessible only from the same package* means no modifier at all.
* **A `@Setter` the sheet does not ask for**: a setter is a forbidden member here (penalty points), and with `final` fields it would not even compile.
* **A wrong visibility of a constructor or a getter** (e.g. a public no-argument constructor where `private` is asked): every generated member is checked (penalty points).
* **A `List` instead of the asked array**: the field must be `Student[] students`.
* **Forgetting `@ToString.Exclude` on `students`**: the `toString()` of the course would also list its students.

## 7. The repository

```java
public class CourseRepository extends Repository<Course>
        implements AggregationQueries<Course, Course.Student, Course.Faculty>,
                   SortingQueries<Course, Course.Student, Course.Faculty>,
                   SearchQueries<Course, Course.Student, Course.Faculty>,
                   FilteringQueries<Course, Course.Student, Course.Faculty>,
                   SetQueries<Course, Course.Student, Course.Faculty>,
                   BasicMapQueries<Course, Course.Student, Course.Faculty>,
                   AggregationMapQueries<Course, Course.Student, Course.Faculty>,
                   GroupingMapQueries<Course, Course.Student, Course.Faculty>,
                   NestedMapQueries<Course, Course.Student, Course.Faculty> {

    public CourseRepository() throws IOException {
        super(Course.class);
    }

    public static void main(String[] args) throws IOException {
        CourseRepository repository = new CourseRepository();
        System.out.println(repository);
    }
}
```

`T` = `Course`, `U` = `Course.Student`, `V` = `Course.Faculty`. Run `main()`: if every course is printed, the JSON document has been read, so the field names, the types and the enum mapping are right.

Next: the queries, by interface: [`AggregationQueries`](AggregationQueries.md), [`SortingQueries`](SortingQueries.md), [`SearchQueries`](SearchQueries.md), [`FilteringQueries`](FilteringQueries.md), [`SetQueries`](SetQueries.md), [`BasicMapQueries`](BasicMapQueries.md), [`AggregationMapQueries`](AggregationMapQueries.md), [`GroupingMapQueries`](GroupingMapQueries.md), [`NestedMapQueries`](NestedMapQueries.md).
