[↑ Tutorials](README.md)

# Clinic – the model and the repository

The practice sheet: a JSON array of *doctors*, each with its *patients*. Package: `clinic`.

## 1. From the input to the fields

The field names are the names of the JSON properties: *Jackson* finds the values by these names.

| JSON property | JSON type | Java field |
| --- | --- | --- |
| `name` | `string` | `String name` |
| `department` | `string` | `Department department` (the nested enum) |
| `experience` | `number`, contains integers | `int experience` |
| `room` | `string` | `String room` |
| `onCall` | `boolean` | `boolean onCall` |
| `patients` | `array`, contains objects, each element represents a *patient* | `List<Patient> patients` (*represented as a list*) |
| *patient* `insuranceNumber` | `number`, contains integers | `long insuranceNumber` (`long`, as in the reference model) |
| *patient* `name` | `string` | `String name` |
| *patient* `age` | `number`, contains integers | `int age` |

## 2. The class `Doctor`

| The sheet says | Code | Why |
| --- | --- | --- |
| *Guarantee that all the fields are accessible only from the same package.* | no modifier on the fields, e.g. `final String name;` | package-private is the default visibility |
| *Prevent other classes from being derived from this class.* | `public final class Doctor` |  |
| *Guarantee that the fields cannot be modified after instantiation.* | `final` fields |  |
| *Field `department` must be represented as an enum constant (see the details later).* | `Department department` | the type is the nested enum |
| *Field `patients` must be represented as a list.* | `List<Patient> patients` | the interface `List` as the type of the field |
| *Generate a constructor having all the parameters. Use the public visibility.* | `@AllArgsConstructor` |  |
| *Generate a constructor having no parameter. Use the protected visibility.* | `@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)` | `force = true`: the `final` fields must get a value (`null`, `0`, `false`); *Jackson* fills them in later |
| *Generate a getter method for each field. Use the public visibility.* | `@Getter` | for a `boolean` field Lombok generates `isX()`, not `getX()` |
| *Generate a `toString()` method which contains all the fields except the `patients` field.* | `@ToString`, `@ToString.Exclude` on `patients` |  |
| *Two objects are identical if their `name` fields are the same.* | `@EqualsAndHashCode(onlyExplicitlyIncluded = true)`, `@EqualsAndHashCode.Include` on `name` | only the marked field takes part in `equals()` and `hashCode()` |

## 3. The class `Doctor.Patient`

A `public static` nested class inside `Doctor`.

| The sheet says | Code | Why |
| --- | --- | --- |
| *Guarantee that all the fields are accessible only from the same package.* | no modifier on the fields, e.g. `final long insuranceNumber;` | package-private is the default visibility |
| *Prevent other classes from being derived from this class.* | `public static final class Patient` |  |
| *Guarantee that the fields cannot be modified after instantiation.* | `final` fields |  |
| *Generate a constructor having all the parameters. Use the public visibility.* | `@AllArgsConstructor` |  |
| *Generate a constructor having no parameter. Use the protected visibility.* | `@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)` | `force = true`: the `final` fields must get a value (`null`, `0`, `false`); *Jackson* fills them in later |
| *Generate a getter method for each field. Use the public visibility.* | `@Getter` |  |
| *Generate a `toString()` method which contains all the fields.* | `@ToString` |  |
| *Two objects are identical if their `insuranceNumber` fields are the same.* | `@EqualsAndHashCode(onlyExplicitlyIncluded = true)`, `@EqualsAndHashCode.Include` on `insuranceNumber` | only the marked field takes part in `equals()` and `hashCode()` |

## 4. The enum `Doctor.Department`

A `public` nested enum inside `Doctor`: one constant per possible value of `department`, in upper snake case, each mapped to its JSON value by `@JsonProperty`. Without the mapping, *Jackson* would look for a constant named like the JSON value, and reading the document would fail.

```java
public enum Department {
    @JsonProperty("Cardiology") CARDIOLOGY,
    @JsonProperty("Neurology") NEUROLOGY,
    @JsonProperty("Pediatrics") PEDIATRICS,
    @JsonProperty("Orthopedics") ORTHOPEDICS,
    @JsonProperty("Dermatology") DERMATOLOGY
}
```

## 5. The complete model

```java
package clinic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public final class Doctor {

    @EqualsAndHashCode.Include
    final String name;

    final Department department;

    final int experience;

    final String room;

    final boolean onCall;

    @ToString.Exclude
    final List<Patient> patients;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
    @ToString
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static final class Patient {

        @EqualsAndHashCode.Include
        final long insuranceNumber;

        final String name;

        final int age;
    }

    public enum Department {
        @JsonProperty("Cardiology") CARDIOLOGY,
        @JsonProperty("Neurology") NEUROLOGY,
        @JsonProperty("Pediatrics") PEDIATRICS,
        @JsonProperty("Orthopedics") ORTHOPEDICS,
        @JsonProperty("Dermatology") DERMATOLOGY
    }
}
```

The sorting tasks (`SortingQueries`) add to this class: `implements Comparable<Doctor>` with `compareTo()`, and a static nested `Comparator` class. The final file: [`solutions/clinic/.../Doctor.java`](solutions/clinic/clinic/Doctor.java).

## 6. Typical mistakes

* **A field name that differs from the JSON property** (e.g. `names` or a capitalized name): *Jackson* finds the values by the names, so reading the document fails ("Unrecognized property"), and the mandatory part fails with it.
* **An enum constant without `@JsonProperty`**, or with a mistyped JSON value: *Jackson* cannot map the text of the document to a constant, and reading fails.
* **A nested class without `static`** (`public class Patient` inside `Doctor`): an inner class needs an instance of the outer class, *Jackson* cannot create it, and the required `Doctor.Patient` is not a static nested class.
* **`@Data`, `@Value`, or a constructor, getter, `toString()`, `equals()`, `hashCode()` written by hand**: every member must come from its own *Lombok* annotation; this fails the mandatory part.
* **`@EqualsAndHashCode` without `onlyExplicitlyIncluded = true`**: every field takes part in the comparison, not only the identifier(s); this costs penalty points, and sets, maps and `contains()` behave differently in the queries.
* **`@NoArgsConstructor` without `force = true` on a class with `final` fields**: the constructor cannot leave the `final` fields without a value, so the class does not compile ("variable … not initialized in the default constructor").
* **`private` (or any) modifier on the package-private fields** (in `Doctor` and `Doctor.Patient`): *accessible only from the same package* means no modifier at all.
* **A `@Setter` the sheet does not ask for**: a setter is a forbidden member here (penalty points), and with `final` fields it would not even compile.
* **A wrong visibility of a constructor or a getter** (e.g. a public no-argument constructor where `protected` is asked): every generated member is checked (penalty points).
* **An array or `ArrayList<Patient>` instead of `List<Patient>`**: the sheet asks for a list, and the type of the field is the interface.
* **Forgetting `@ToString.Exclude` on `patients`**: the `toString()` of the doctor would also list its patients.
* **`int` for the `long` field**: the type of every field is checked (penalty points).
* **Calling `getX()` for a `boolean` field**: Lombok names its getter `isX()`.

## 7. The repository

```java
public class DoctorRepository extends Repository<Doctor>
        implements AggregationQueries<Doctor, Doctor.Patient, Doctor.Department>,
                   SortingQueries<Doctor, Doctor.Patient, Doctor.Department>,
                   SearchQueries<Doctor, Doctor.Patient, Doctor.Department>,
                   FilteringQueries<Doctor, Doctor.Patient, Doctor.Department>,
                   SetQueries<Doctor, Doctor.Patient, Doctor.Department>,
                   BasicMapQueries<Doctor, Doctor.Patient, Doctor.Department>,
                   AggregationMapQueries<Doctor, Doctor.Patient, Doctor.Department>,
                   GroupingMapQueries<Doctor, Doctor.Patient, Doctor.Department>,
                   NestedMapQueries<Doctor, Doctor.Patient, Doctor.Department> {

    public DoctorRepository() throws IOException {
        super(Doctor.class);
    }

    public static void main(String[] args) throws IOException {
        DoctorRepository repository = new DoctorRepository();
        System.out.println(repository);
    }
}
```

`T` = `Doctor`, `U` = `Doctor.Patient`, `V` = `Doctor.Department`. Run `main()`: if every doctor is printed, the JSON document has been read, so the field names, the types and the enum mapping are right.

Next: the queries, by interface: [`AggregationQueries`](AggregationQueries.md), [`SortingQueries`](SortingQueries.md), [`SearchQueries`](SearchQueries.md), [`FilteringQueries`](FilteringQueries.md), [`SetQueries`](SetQueries.md), [`BasicMapQueries`](BasicMapQueries.md), [`AggregationMapQueries`](AggregationMapQueries.md), [`GroupingMapQueries`](GroupingMapQueries.md), [`NestedMapQueries`](NestedMapQueries.md).
