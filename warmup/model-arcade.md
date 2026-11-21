[↑ Tutorials](README.md)

# Arcade – the model and the repository

The practice sheet: a JSON array of *games*, each with its *characters*. Package: `arcade`.

## 1. From the input to the fields

The field names are the names of the JSON properties: *Jackson* finds the values by these names.

| JSON property | JSON type | Java field |
| --- | --- | --- |
| `name` | `string` | `String name` |
| `description` | `string` | `String description` |
| `platform` | `string` | `Platform platform` (the nested enum) |
| `released` | `number`, contains integers | `int released` |
| `characters` | `array`, contains objects, each element represents a *character* | `Character[] characters` (*represented as an array*) |
| *character* `name` | `string` | `String name` |
| *character* `description` | `string` | `String description` |
| *character* `games` | `number`, contains integers | `int games` |

## 2. The class `Game`

| The sheet says | Code | Why |
| --- | --- | --- |
| *Make the class immutable (guarantee that the fields are invisible from outside of the class and cannot be modified, and the class cannot be extended).* | `public final class Game`, every field `private final` | invisible: `private`; cannot be modified: `final` fields; cannot be extended: `final` class |
| *Field `platform` must be represented as an enum constant (see the details later).* | `Platform platform` | the type is the nested enum |
| *Field `characters` must be represented as an array.* | `Character[] characters` | *Jackson* reads a JSON array into a Java array as well; the queries use `length` instead of `size()` |
| *Generate a constructor having all the parameters. Use the public visibility.* | `@AllArgsConstructor` |  |
| *Generate a constructor having no parameter. Use the package-level visibility.* | `@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)` | `force = true`: the `final` fields must get a value (`null`, `0`, `false`); *Jackson* fills them in later |
| *Generate a getter method for each field. Use the public visibility.* | `@Getter` |  |
| *Generate a `toString()` method which contains all the fields except the `characters` field.* | `@ToString`, `@ToString.Exclude` on `characters` |  |
| *Two objects are identical if their `name` fields are the same.* | `@EqualsAndHashCode(onlyExplicitlyIncluded = true)`, `@EqualsAndHashCode.Include` on `name` | only the marked field takes part in `equals()` and `hashCode()` |

## 3. The class `Game.Character`

A `public static` nested class inside `Game`.

| The sheet says | Code | Why |
| --- | --- | --- |
| *Make the class immutable (having the common features).* | `public static final class Character`, every field `private final` | the short wording: the common features of an immutable class are a `final` class and `private final` fields |
| *Generate a constructor having all the parameters. Use the public visibility.* | `@AllArgsConstructor` |  |
| *Generate a constructor having no parameter. Use the package-level visibility.* | `@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)` | `force = true`: the `final` fields must get a value (`null`, `0`, `false`); *Jackson* fills them in later |
| *Generate a getter method for each field. Use the public visibility.* | `@Getter` |  |
| *Generate a `toString()` method which contains all the fields.* | `@ToString` |  |
| *Two objects are identical if their `name` fields are the same.* | `@EqualsAndHashCode(onlyExplicitlyIncluded = true)`, `@EqualsAndHashCode.Include` on `name` | only the marked field takes part in `equals()` and `hashCode()` |

## 4. The enum `Game.Platform`

A `public` nested enum inside `Game`: one constant per possible value of `platform`, in upper snake case, each mapped to its JSON value by `@JsonProperty`. Without the mapping, *Jackson* would look for a constant named like the JSON value, and reading the document would fail.

```java
public enum Platform {
    @JsonProperty("Neo Geo") NEO_GEO,
    @JsonProperty("Capcom Play System") CAPCOM_PLAY_SYSTEM,
    @JsonProperty("Sega Model Series") SEGA_MODEL_SERIES,
    @JsonProperty("Nintendo") NINTENDO
}
```

## 5. The complete model

```java
package arcade;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public final class Game {

    @EqualsAndHashCode.Include
    private final String name;

    private final String description;

    private final Platform platform;

    private final int released;

    @ToString.Exclude
    private final Character[] characters;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
    @ToString
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static final class Character {

        @EqualsAndHashCode.Include
        private final String name;

        private final String description;

        private final int games;
    }

    public enum Platform {
        @JsonProperty("Neo Geo") NEO_GEO,
        @JsonProperty("Capcom Play System") CAPCOM_PLAY_SYSTEM,
        @JsonProperty("Sega Model Series") SEGA_MODEL_SERIES,
        @JsonProperty("Nintendo") NINTENDO
    }
}
```

The sorting tasks (`SortingQueries`) add to this class: `implements Comparable<Game>` with `compareTo()`, and a static nested `Comparator` class. The final file: [`solutions/arcade/.../Game.java`](solutions/arcade/arcade/Game.java).

## 6. Typical mistakes

* **A field name that differs from the JSON property** (e.g. `names` or a capitalized name): *Jackson* finds the values by the names, so reading the document fails ("Unrecognized property"), and the mandatory part fails with it.
* **An enum constant without `@JsonProperty`**, or with a mistyped JSON value: *Jackson* cannot map the text of the document to a constant, and reading fails.
* **A nested class without `static`** (`public class Character` inside `Game`): an inner class needs an instance of the outer class, *Jackson* cannot create it, and the required `Game.Character` is not a static nested class.
* **`@Data`, `@Value`, or a constructor, getter, `toString()`, `equals()`, `hashCode()` written by hand**: every member must come from its own *Lombok* annotation; this fails the mandatory part.
* **`@EqualsAndHashCode` without `onlyExplicitlyIncluded = true`**: every field takes part in the comparison, not only the identifier(s); this costs penalty points, and sets, maps and `contains()` behave differently in the queries.
* **`@NoArgsConstructor` without `force = true` on a class with `final` fields**: the constructor cannot leave the `final` fields without a value, so the class does not compile ("variable … not initialized in the default constructor").
* **A `@Setter` the sheet does not ask for**: a setter is a forbidden member here (penalty points), and with `final` fields it would not even compile.
* **A wrong visibility of a constructor or a getter** (e.g. a public no-argument constructor where `package-level` is asked): every generated member is checked (penalty points).
* **A `List` instead of the asked array**: the field must be `Character[] characters`.
* **Forgetting `@ToString.Exclude` on `characters`**: the `toString()` of the game would also list its characters.

## 7. The repository

```java
public class GameRepository extends Repository<Game>
        implements AggregationQueries<Game, Game.Character, Game.Platform>,
                   SortingQueries<Game, Game.Character, Game.Platform>,
                   SearchQueries<Game, Game.Character, Game.Platform>,
                   FilteringQueries<Game, Game.Character, Game.Platform>,
                   SetQueries<Game, Game.Character, Game.Platform>,
                   BasicMapQueries<Game, Game.Character, Game.Platform>,
                   AggregationMapQueries<Game, Game.Character, Game.Platform>,
                   GroupingMapQueries<Game, Game.Character, Game.Platform>,
                   NestedMapQueries<Game, Game.Character, Game.Platform> {

    public GameRepository() throws IOException {
        super(Game.class);
    }

    public static void main(String[] args) throws IOException {
        GameRepository repository = new GameRepository();
        System.out.println(repository);
    }
}
```

`T` = `Game`, `U` = `Game.Character`, `V` = `Game.Platform`. Run `main()`: if every game is printed, the JSON document has been read, so the field names, the types and the enum mapping are right.

Next: the queries, by interface: [`AggregationQueries`](AggregationQueries.md), [`SortingQueries`](SortingQueries.md), [`SearchQueries`](SearchQueries.md), [`FilteringQueries`](FilteringQueries.md), [`SetQueries`](SetQueries.md), [`BasicMapQueries`](BasicMapQueries.md), [`AggregationMapQueries`](AggregationMapQueries.md), [`GroupingMapQueries`](GroupingMapQueries.md), [`NestedMapQueries`](NestedMapQueries.md).
