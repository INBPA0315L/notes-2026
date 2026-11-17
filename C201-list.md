[↑ Back](./README.md)

# `C201` - `List` interface and its implementation

## Sample code

```java
import java.util.ArrayList;
import java.util.List;

public class TestList {
    public static void main(String[] args) {
        // ArrayList<Integer> list = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        System.out.println(list);
        list.add(4);
        list.add(4);
        list.add(5);
        System.out.println(list);
        System.out.println(list.contains(4));
        System.out.println(list.indexOf(4));
        System.out.println(list.lastIndexOf(4));
        System.out.println(list.size());
        System.out.println(list.get(2));
        // System.out.println(list.get(4));
        list.set(2, 7);
        System.out.println(list);

        for(int i=0; i<list.size(); i++){
            System.out.println(i);
        }

        for(int number: list){
            System.out.println(number);
        }
    }
}
```

## The `Collection` interface

`Collection<E>` is the common superinterface of `List<E>` and `Set<E>` (but **not** of `Map<K, V>`). It describes a group of elements without saying anything about their order or duplicates. Its most important methods are therefore available on every list and set:

| Method | Meaning |
|---|---|
| `add(e)` | adds an element, returns whether the collection changed |
| `addAll(c)` | adds all the elements of another collection |
| `remove(o)` | removes one occurrence of an element (by `equals()`) |
| `contains(o)` | whether the collection contains the element (by `equals()`) |
| `size()`, `isEmpty()` | the number of elements, whether it is empty |
| `clear()` | removes all the elements |
| `iterator()` | makes the *for-each* loop work (`Collection` extends `Iterable`) |

```java
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestCollection {

    // works with any list or set
    static int sumOf(Collection<Integer> numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(2);
        Set<Integer> set = new HashSet<>(list);   // every collection type has a copy constructor
        System.out.println(sumOf(list));          // 5
        System.out.println(sumOf(set));           // 3
    }
}
```

What a `List` adds on top of `Collection`: indices (`get(i)`, `set(i, e)`, `add(i, e)`, `remove(i)`, `indexOf()`), a fixed order, and duplicates. A `Set` adds no new methods, it only forbids duplicates.

## The `Collections` utility class

`Collections` (with an `s`) is a class of static helper methods, similar to `Arrays` for arrays. Do not confuse it with the `Collection` interface.

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TestCollections {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);                     // random order
        System.out.println(numbers);
        Collections.shuffle(numbers, new Random(42));     // reproducible order (same seed, same result)
        System.out.println(numbers);

        Collections.sort(numbers);                        // natural order (Integer is Comparable)
        System.out.println(numbers);
        Collections.sort(numbers, Collections.reverseOrder());  // using a Comparator (reverse natural order)
        System.out.println(numbers);

        System.out.println(Collections.max(numbers));
        System.out.println(Collections.min(numbers));
        Collections.reverse(numbers);
        System.out.println(numbers);
    }
}
```

1. `Collections.sort(list)` sorts the list **in place** by the natural order of its elements, so the element type must implement `Comparable` (see [`A301`](A301-oo-basics-2.md)). The sort is *stable*: equal elements keep their original relative order.
1. `Collections.sort(list, comparator)` sorts by a `Comparator` (see [`B102`](B102-comparator.md)). It is the same as `list.sort(comparator)`.
1. `Collections.shuffle(list)` puts the elements in a random order in place. With a `Random` object of a fixed seed the result is reproducible, which is useful for testing.
1. Both methods modify the list and return `void`, so they cannot be used on an unmodifiable list (`UnsupportedOperationException`) - make a copy first: `List<Airport> copy = new ArrayList<>(getAll());`.
1. Both methods take a `List`, since a `Set` has no positions to rearrange. Use a `TreeSet` for a sorted set (see [`C202`](C202-set.md)).

## Typical queries (based on the `flights` solutions)

The examples below use the model of the `flights` project: an `Airport` has a `code`, a `city`, a `country` (enum `Airport.Country`), a number of `runways`, and an array of `Airport.Flight` objects (each with a `number`, a `destination`, and a `duration`). `getAll()` returns the list of airports.

### The list of the repository is unmodifiable

`getAll()` returns `Collections.unmodifiableList(...)`: you can read it (`get()`, `size()`, `getFirst()`, `contains()`, iteration), but `add()`, `set()`, `remove()`, and `sort()` throw an `UnsupportedOperationException`. Make a copy if you need to modify it:

```java
List<Airport> copy = new ArrayList<>(getAll());
Airport first = getAll().getFirst();          // Java 21+, same as getAll().get(0)
```

### Aggregation: count, sum, minimum, maximum, average

*The number of airports of a country, the greatest number of runways, the average number of flights of a country.*

```java
int count = 0;
for (Airport airport : getAll()) {
    if (airport.getCountry() == country) {
        count++;
    }
}

if (getAll().isEmpty()) {
    throw new NoSuchElementException();
}
int max = getAll().getFirst().getRunways();
for (Airport airport : getAll()) {
    max = Math.max(max, airport.getRunways());
}

int sum = 0;
int n = 0;
for (Airport airport : getAll()) {
    if (airport.getCountry() == country) {
        sum += airport.getFlights().length;
        n++;
    }
}
if (n == 0) {
    throw new NoSuchElementException();
}
double average = sum / (double) n;
```

> [!NOTE]
> Read the JavaDoc of the query: it specifies the exception to throw (`NoSuchElementException`, `IllegalStateException`, `IllegalArgumentException`) or that `null` must be returned if there is no result.

### Iterating over a nested array

*The greatest duration of all the flights.*

```java
Integer max = null;
for (Airport airport : getAll()) {
    for (Airport.Flight flight : airport.getFlights()) {
        if (max == null || flight.getDuration() > max) {
            max = flight.getDuration();
        }
    }
}
if (max == null) {
    throw new IllegalStateException();
}
return max;
```

### Searching: the first element, the element with the maximum

*The first airport of a country, the airport with the most flights* (if more airports have the greatest value, the first one is returned - so use `>`, not `>=`).

```java
for (Airport airport : getAll()) {
    if (airport.getCountry() == country) {
        return airport;
    }
}
throw new NoSuchElementException();

Airport best = null;
for (Airport airport : getAll()) {
    if (best == null || airport.getFlights().length > best.getFlights().length) {
        best = airport;
    }
}
return best;
```

### Decisions: any, all, none

*Does any airport have the given number of runways? Does every airport have at least `count` flights? Does an airport contain the given flight?*

```java
boolean any = false;
for (Airport airport : getAll()) {
    if (airport.getRunways() == runways) {
        any = true;
        break;
    }
}

boolean all = true;
for (Airport airport : getAll()) {
    if (airport.getFlights().length < count) {
        all = false;
        break;
    }
}

boolean contains = Arrays.asList(airport.getFlights()).contains(flight);   // uses equals()
```

### Filtering into a new list

*The airports of a country that do not contain the given flight number* (the result keeps the original order).

```java
List<Airport> result = new ArrayList<>();
for (Airport airport : getAll()) {
    if (airport.getCountry() != country) {
        continue;
    }
    boolean found = false;
    for (Airport.Flight flight : airport.getFlights()) {
        if (flight.getNumber().equals(number)) {
            found = true;
            break;
        }
    }
    if (!found) {
        result.add(airport);
    }
}
return result;
```

### Distinct elements, keeping the order of the first occurrences

```java
List<Airport.Flight> result = new ArrayList<>();
for (Airport airport : getAll()) {
    if (airport.getCountry() == country) {
        for (Airport.Flight flight : airport.getFlights()) {
            if (!result.contains(flight)) {             // uses equals()
                result.add(flight);
            }
        }
    }
}
return result;
```

> [!TIP]
> For large lists, a helper `HashSet` is faster than calling `contains()` repeatedly (see [`C202`](C202-set.md)).

### Sorted copy

The original list cannot be modified, so sort a copy. The ordering can come from the natural order (`Comparable`), or a `Comparator` implemented as a top-level class, a static nested class, or an anonymous class (see [`B102`](B102-comparator.md) and [`B201`](B201-comparator-2.md)).

```java
List<Airport> result = new ArrayList<>(getAll());
Collections.sort(result);                              // natural order (Airport implements Comparable)
result.sort(new AirportComparator());                  // top-level class
result.sort(new Airport.AirportComparator());          // static nested class of Airport
result.sort(new Comparator<Airport>() {                // anonymous class
    @Override
    public int compare(Airport first, Airport second) {
        int result = first.getCity().compareTo(second.getCity());
        if (result != 0) {
            return result;
        }
        return Integer.compare(second.getRunways(), first.getRunways());  // descending
    }
});
```

## Highlights

1. Program to the interface: `List<Integer> list = new ArrayList<>();`.
1. `contains()`, `indexOf()`, and `remove(Object)` use `equals()` of the elements.
1. `list.remove(1)` removes by index, `list.remove(Integer.valueOf(1))` removes by value.
1. Never sort or modify the list returned by `getAll()`; make a copy with `new ArrayList<>(...)`.

Check the documentation's *Method Summary* section for further highlights.

## References

1. [Interface `Collection<E>`](https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/util/Collection.html)
1. [Interface `List<E>`](https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/util/List.html)
1. [Class `ArrayList<E>`](https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/util/ArrayList.html)
1. [Class `Collectons`](https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/util/Collections.html)

## Tutorials

1. [Baeldung | Java Collections Series](https://www.baeldung.com/java-collections)