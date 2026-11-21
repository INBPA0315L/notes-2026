# Lab test 3 – solution tutorials

These tutorials walk through the complete solution of a lab test 3 exercise sheet on sample practice sheets. Every
sheet has the same structure (a model, a repository and the queries); only the details differ, so the samples together
show the typical variations. The queries are grouped by their kind into interfaces, as in the practice repositories;
a lab test contains a few of them.

**The model and the repository**, step by step, for each sample sheet:

* [arcade](model-arcade.md): games and their characters
* [campus](model-campus.md): courses and their students
* [cinema](model-cinema.md): movies and their actors
* [clinic](model-clinic.md): doctors and their patients
* [conference](model-conference.md): talks and their attendees

**The queries**, one page per interface: every task explained in general, then its solution on every sample sheet,
side by side:

* [`AggregationQueries`](AggregationQueries.md) (23 tasks): queries that return a single (atomic) value
* [`SortingQueries`](SortingQueries.md) (5 tasks): queries that return a sorted list
* [`SearchQueries`](SearchQueries.md) (7 tasks): queries that return a single object (searching, selecting the maximum)
* [`FilteringQueries`](FilteringQueries.md) (6 tasks): queries that return a filtered list
* [`SetQueries`](SetQueries.md) (6 tasks): queries that return a set
* [`BasicMapQueries`](BasicMapQueries.md) (10 tasks): queries that return a simple dictionary (counting occurrences, first or last occurrences)
* [`AggregationMapQueries`](AggregationMapQueries.md) (5 tasks): queries that return a dictionary with single values
* [`GroupingMapQueries`](GroupingMapQueries.md) (5 tasks): queries that return a dictionary with collections as its values
* [`NestedMapQueries`](NestedMapQueries.md) (4 tasks): queries that return a dictionary with dictionaries as its values

The complete solutions of the samples are in the folder [`solutions`](solutions). They are the reference solutions of
the practice sheets: they pass the grading tests (the mandatory part, no penalty points, every query perfect).

## The rules of the code

The solutions use only what the course has covered:

1. **No functional tools.** No streams, no lambda expressions, no method references, no `Comparator.comparing(...)`.
   Loops (`for`), conditions (`if`), `Collections.sort()`, and for comparisons
   `Objects.compare(a, b, Comparator.naturalOrder())` / `Comparator.reverseOrder()` (as in the course notes, *B102*).
2. **The references of collections are always interfaces.** Variables, fields, parameters and return values have the
   types `List`, `Set` and `Map`; the concrete class (`ArrayList`, `HashSet`, `HashMap`) appears only after `new`:

   ```java
   List<Game> result = new ArrayList<>();          // good
   ArrayList<Game> result = new ArrayList<>();     // avoid
   ```

## The order of work

1. **Model** (the mandatory part): the classes, the fields and the *Lombok*/*Jackson* annotations, as the sheet
   prescribes. Without it, nothing compiles.
2. **Repository**: the class `<Top>Repository` extending `base.Repository<T>`, with a constructor and a `main()` that
   prints the repository. Run it: if every object is printed, the JSON document has been read, so the field names,
   the types and the enum mapping are right.
3. **Queries**: implement the query interfaces. A method you have not solved yet can simply
   `throw new UnsupportedOperationException();`, so the class compiles and you can run `main()` at any time. The
   documentation comment of each method is the exact specification: read its last sentences carefully (ties,
   duplicates, `null` or an exception).

## From the sheet to the code

Every line of the *Creating the representation* part of a sheet maps to a fixed piece of code. The names of the
classes, the fields and the enum are mandatory: the fields have the names of the JSON properties.

| The sheet says | You write |
| --- | --- |
| *Make the class immutable* (short or long wording) | `public final class X`, every field `private final` |
| *accessible only from the same package* | the fields have no modifier (package-private) |
| *accessible only from the same package and derived classes* | the fields are `protected` |
| *Define all the fields having private visibility* | the fields are `private` (not `final`, the class is not `final`) |
| *Prevent other classes from being derived from this class* | `final class` |
| *Guarantee that the fields cannot be modified after instantiation* | the fields are `final` |
| *Field `x` must be represented as an enum constant* | the field's type is the nested enum |
| *Field `xs` must be represented as a list* / *an array* | `List<Item> xs` / `Item[] xs` |
| *Generate a constructor having all the parameters. Use the public visibility.* | `@AllArgsConstructor` |
| *Generate a constructor having no parameter. Use the … visibility.* | `@NoArgsConstructor(access = AccessLevel.PRIVATE)` (`PROTECTED`, `PACKAGE`; `PUBLIC` is the default); with `final` fields also `force = true` |
| *Generate a getter method for each field. Use the public visibility.* | `@Getter` (a `boolean` field gets `isX()`) |
| *Generate a setter method for each field. Use the … visibility.* | `@Setter` or `@Setter(AccessLevel.PROTECTED)` … (only for fields that are not `final`) |
| *`toString()` which contains all the fields* | `@ToString` |
| *… all the fields except the `xs` field* | `@ToString` and `@ToString.Exclude` on `xs` |
| *… only the `x` field* | `@ToString(onlyExplicitlyIncluded = true)` and `@ToString.Include` on `x` |
| *Two objects are identical if their `x` (and `y`) fields are the same* | `@EqualsAndHashCode(onlyExplicitlyIncluded = true)` and `@EqualsAndHashCode.Include` on `x` (and `y`) |
| *the public static nested class / enum class of the class `X`* | `public static (final) class Item` / `public enum Kind` inside `X` |
| the values of the enum property (e.g. `"Neo Geo"`) | one constant per value, each mapped by *Jackson*: `@JsonProperty("Neo Geo") NEO_GEO` |

Why `force = true`? A constructor without parameters must give every `final` field a value. With `force = true`
Lombok initializes them to `null`, `0` or `false`; *Jackson* then fills them in while reading the document.

`@Data` and `@Value` must not be used, and no constructor, getter, `toString()`, `equals()` or `hashCode()` may be
written by hand: each one is generated by its own *Lombok* annotation.

## The repository

The same in every sheet, only the names differ:

```java
public class GameRepository extends Repository<Game> implements Queries<Game, Game.Character, Game.Platform> {

    public GameRepository() throws IOException {
        super(Game.class);
    }

    public static void main(String[] args) throws IOException {
        GameRepository repository = new GameRepository();
        System.out.println(repository);
    }
}
```

* `extends Repository<Game>`: the given class reads `data.json` into a list of `Game` objects; `getAll()` returns this
  list (it cannot be modified).
* `super(Game.class)`: tells the given class the type of the objects to read. It may throw an `IOException`, so the
  constructor declares it.
* `implements Queries<Game, Game.Character, Game.Platform>` (in a practice repository: every query interface): `T` is
  the top-level class, `U` the nested class, `V` the enum. After this, IntelliJ IDEA can generate the method stubs
  (*Code > Implement Methods*).

## The building blocks of the queries

* `getAll()` is the list of the top-level objects (`T`); loop over it: `for (Game t : getAll())`.
* The nested objects (`U`) are in the collection field of each `T`: a second loop inside the first,
  `for (Game.Character u : t.getCharacters())` (the same `for`-each works on an array).
* The result is built in a variable (a number, an object, or a new `ArrayList`/`HashSet`/`HashMap` referenced by its
  interface) and returned at the end.
* Strings are compared with `equals()`, enum values with `==`, objects (as in `contains()`) by `equals()`, which compares
  the identifier fields of the model.
* If the result does not exist, the documentation says what to do: return `null`, or throw an exception of the given
  `base` package (`NoResultException`, `NotFoundException`, `EmptyResultException`). These are **checked** exceptions:
  the interface declares them (`int getMaximumOfAge() throws NoResultException;`), so your method declares the same
  `throws` clause and simply `throw new NoResultException();` where the result is missing.
