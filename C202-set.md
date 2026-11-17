[↑ Back](./README.md)

# `C202` - `Set` interface and its implementation

## Sample code

### Using the class `HashSet`

```java
import java.util.HashSet;
import java.util.Set;

public class TestHashSet {
    public static void main(String[] args) {
        Set<Integer> numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(5);
        System.out.println(numbers.add(3));
        System.out.println(numbers.add(5));
        System.out.println(numbers);

        System.out.println(numbers.size());
        System.out.println(numbers.contains(1));
        for(int number: numbers){
            System.out.println(number);
        }
    }
}
```

### Using the class `TreeSet`

```java
import java.util.Set;
import java.util.TreeSet;

public class TestTreeSet {
    public static void main(String[] args) {
        Set<String> fruits = new TreeSet<>();
        // var fruits = new TreeSet<String>();
        fruits.add("banana");
        fruits.add("apple");
        fruits.add("lemon");
        System.out.println(fruits);

        for(String fruit: fruits){
            System.out.println(fruit);
        }
    }
}
```

Always use the interface `Set` as the type of the variables, fields, parameters, and return values; the concrete implementation appears only after `new`.

## Typical queries (based on the `flights` solutions)

The examples below use the model of the `flights` project: an `Airport` has a `code`, a `city`, a `country` (enum `Airport.Country`), a number of `runways`, and an array of `Airport.Flight` objects (each with a `number`, a `destination`, and a `duration`). `getAll()` returns the list of airports.

### Equality matters

A `HashSet` uses `hashCode()` and `equals()` to detect duplicates. In the `flights` project two flights are equal if their `number`s are equal:

```java
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public static final class Flight {
    @EqualsAndHashCode.Include
    private final String number;
    // ...
}
```

So a `Set<Airport.Flight>` contains at most one flight with a given number, regardless of the other fields. Without overriding `equals()` and `hashCode()`, every object would be different (identity).

### Collecting values into a set

*The countries of the airports with more runways than the limit, the distinct flight durations.*

```java
Set<Airport.Country> countries = new HashSet<>();
for (Airport airport : getAll()) {
    if (airport.getRunways() > runways) {
        countries.add(airport.getCountry());
    }
}

Set<Integer> durations = new HashSet<>();
for (Airport airport : getAll()) {
    for (Airport.Flight flight : airport.getFlights()) {
        durations.add(flight.getDuration());
    }
}
```

### Adding a whole array or collection

*All the flights of the airports of a country.*

```java
Set<Airport.Flight> flights = new HashSet<>();
for (Airport airport : getAll()) {
    if (airport.getCountry() == country) {
        flights.addAll(Arrays.asList(airport.getFlights()));
    }
}
```

### Counting distinct values

*The number of distinct flights, the number of distinct cities of a country.* Collect the values into a set, then use its `size()`.

```java
Set<String> cities = new HashSet<>();
for (Airport airport : getAll()) {
    if (airport.getCountry() == country) {
        cities.add(airport.getCity());
    }
}
return cities.size();
```

### Removing duplicates while keeping the order

*The distinct flights of a country in the order of their first occurrences.* A `HashSet` has no order, so the result is built in a list, and a helper set remembers the elements already seen. Since `add()` returns `false` for a duplicate, it can be used as the condition.

```java
Set<Airport.Flight> seen = new HashSet<>();
List<Airport.Flight> result = new ArrayList<>();
for (Airport airport : getAll()) {
    if (airport.getCountry() == country) {
        for (Airport.Flight flight : airport.getFlights()) {
            if (seen.add(flight)) {
                result.add(flight);
            }
        }
    }
}
return result;
```

### Sorted set

If the result must be sorted, use a `TreeSet`. The element type must be comparable, or you must pass a `Comparator`:

```java
Set<String> sortedCities = new TreeSet<>(cities);
Set<Airport> airports = new TreeSet<>(new AirportComparator());
airports.addAll(getAll());
```

> [!WARNING]
> A `TreeSet` uses `compareTo()`/`compare()` instead of `equals()` to detect duplicates: two elements comparing as `0` are considered equal, and only one of them is kept.

## Highlights

1. A set contains no duplicates; `add()` returns `false` if the element was already present.
1. `HashSet`: no order, requires `equals()` and `hashCode()`. `TreeSet`: sorted, requires `Comparable` or a `Comparator`.
1. To count distinct values, collect them into a set and use `size()`.
1. Never modify an element after you put it into a set.

Check the documentation's *Method Summary* section for further highlights.

## References

1. [Interface `Collection<E>`](https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/util/Collection.html)
1. [Interface `Set<E>`](https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/util/Set.html)
1. [Class `HashSet<E>`](https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/util/HashSet.html)
1. [Class `TreeSet<E>`](https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/util/TreeSet.html)
## Tutorials

1. [Baeldung | Java Collections Series](https://www.baeldung.com/java-collections)