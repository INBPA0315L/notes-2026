[↑ Back](./README.md)

# `C203` - `Map` interface and its implementations

A `Map<K, V>` (dictionary) stores *key-value pairs* (entries). Every key is unique, a value can occur many times. A `Map` is **not** a `Collection`, but its views (`keySet()`, `values()`, `entrySet()`) are.

| | `HashMap<K, V>` | `TreeMap<K, V>` |
|---|---|---|
| Order of the keys | no guaranteed order | sorted by keys (natural order or a `Comparator`) |
| Requirement on the key type | proper `equals()` and `hashCode()` | `Comparable` or a `Comparator` passed to the constructor |
| `null` key | allowed (one) | not allowed (`NullPointerException`) |
| `get()`, `put()`, `containsKey()` | *O(1)* on average | *O(log n)* |

Always use the interface `Map` as the type of the variables, fields, parameters, and return values; the concrete implementation appears only after `new`.

## Sample code

### Using the class `HashMap`

```java
import java.util.HashMap;
import java.util.Map;

public class TestHashMap {
    public static void main(String[] args) {
        Map<String, Integer> ages = new HashMap<>();
        System.out.println(ages.put("Alice", 21));    // null (no previous value)
        ages.put("Bob", 23);
        System.out.println(ages.put("Alice", 22));    // 21 (the previous value is replaced)
        System.out.println(ages);

        System.out.println(ages.size());
        System.out.println(ages.get("Alice"));        // 22
        System.out.println(ages.get("Cecil"));        // null (no such key)
        System.out.println(ages.getOrDefault("Cecil", 0));
        System.out.println(ages.containsKey("Bob"));
        System.out.println(ages.containsValue(23));
        ages.remove("Bob");
        System.out.println(ages);

        // iterating over the keys, the values, and the entries
        for (String name : ages.keySet()) {
            System.out.println(name);
        }
        for (int age : ages.values()) {
            System.out.println(age);
        }
        for (Map.Entry<String, Integer> entry : ages.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
```

### Using the class `TreeMap`

```java
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class TestTreeMap {
    public static void main(String[] args) {
        Map<String, Integer> stock = new TreeMap<>();
        stock.put("lemon", 3);
        stock.put("apple", 10);
        stock.put("banana", 7);
        System.out.println(stock);                    // {apple=10, banana=7, lemon=3}

        // the keys are iterated in ascending order
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // keys in reverse order using a comparator
        Map<String, Integer> reversed = new TreeMap<>(Collections.reverseOrder());
        reversed.putAll(stock);
        System.out.println(reversed);                 // {lemon=3, banana=7, apple=10}
    }
}
```

### The three basic patterns

Almost every map query is built from the following three patterns. They use only `get()`, `getOrDefault()`, `containsKey()`, and `put()`.

```java
// 1. updating a number: counting, summing
Map<String, Integer> counts = new HashMap<>();
counts.put("a", counts.getOrDefault("a", 0) + 1);       // missing key: 0 + 1, otherwise: old + 1

// 2. keeping the greatest value
Map<String, Integer> maximums = new HashMap<>();
if (!maximums.containsKey("b") || 5 > maximums.get("b")) {
    maximums.put("b", 5);
}

// 3. collecting values into a list (or a set) that is created on demand
Map<String, List<Integer>> groups = new HashMap<>();
if (!groups.containsKey("x")) {
    groups.put("x", new ArrayList<>());
}
groups.get("x").add(42);
```

> [!NOTE]
> Two maps are equal (`equals()`) if they contain the same entries, regardless of their implementation, e.g., a `HashMap` and a `TreeMap` with the same entries are equal.

## Typical queries (based on the `flights` solutions)

The examples below use the model of the `flights` project: an `Airport` has a `code`, a `city`, a `country` (enum `Airport.Country`), a number of `runways`, and an array of `Airport.Flight` objects (each with a `number`, a `destination`, and a `duration`). `getAll()` returns the list of airports.

### Counting by key - `Map<K, Integer>`

*The number of airports by city.*

```java
Map<String, Integer> result = new HashMap<>();
for (Airport airport : getAll()) {
    result.put(airport.getCity(), result.getOrDefault(airport.getCity(), 0) + 1);
}
return result;
```

### Summing by key

*The sum of the duration of the flights by country.*

```java
Map<Airport.Country, Integer> result = new HashMap<>();
for (Airport airport : getAll()) {
    int sum = 0;
    for (Airport.Flight flight : airport.getFlights()) {
        sum += flight.getDuration();
    }
    result.put(airport.getCountry(), result.getOrDefault(airport.getCountry(), 0) + sum);
}
return result;
```

### Maximum by key

*The greatest number of runways by country.*

```java
Map<Airport.Country, Integer> result = new HashMap<>();
for (Airport airport : getAll()) {
    if (!result.containsKey(airport.getCountry()) || airport.getRunways() > result.get(airport.getCountry())) {
        result.put(airport.getCountry(), airport.getRunways());
    }
}
return result;
```

### Average by key - `Map<K, Double>`

*The average number of runways by country.* With loops, collect the sums and the counts first, then divide.

```java
Map<Airport.Country, Integer> sums = new HashMap<>();
Map<Airport.Country, Integer> counts = new HashMap<>();
for (Airport airport : getAll()) {
    sums.put(airport.getCountry(), sums.getOrDefault(airport.getCountry(), 0) + airport.getRunways());
    counts.put(airport.getCountry(), counts.getOrDefault(airport.getCountry(), 0) + 1);
}
Map<Airport.Country, Double> result = new HashMap<>();
for (Map.Entry<Airport.Country, Integer> entry : sums.entrySet()) {
    result.put(entry.getKey(), entry.getValue() / (double) counts.get(entry.getKey()));
}
return result;
```

### Grouping into a `List` or a `Set` - `Map<K, List<V>>`, `Map<K, Set<V>>`

*The airports by country (in their original order), and the cities by country.*

```java
Map<Airport.Country, List<Airport>> airports = new HashMap<>();
Map<Airport.Country, Set<String>> cities = new HashMap<>();
for (Airport airport : getAll()) {
    if (!airports.containsKey(airport.getCountry())) {
        airports.put(airport.getCountry(), new ArrayList<>());
        cities.put(airport.getCountry(), new HashSet<>());
    }
    airports.get(airport.getCountry()).add(airport);
    cities.get(airport.getCountry()).add(airport.getCity());
}
```

*The flights by country* - the values come from a nested array.

```java
Map<Airport.Country, Set<Airport.Flight>> result = new HashMap<>();
for (Airport airport : getAll()) {
    if (!result.containsKey(airport.getCountry())) {
        result.put(airport.getCountry(), new HashSet<>());
    }
    result.get(airport.getCountry()).addAll(Arrays.asList(airport.getFlights()));
}
return result;
```

> [!NOTE]
> Here, an airport without flights still creates an empty set for its country. If a key must appear only when it has at least one value (e.g., *"the countries without such airports are not included"*), filter **before** grouping.

### Inverting a relation

*The countries by flight duration* - the key comes from the nested object, the value from the outer one.

```java
Map<Integer, Set<Airport.Country>> result = new HashMap<>();
for (Airport airport : getAll()) {
    for (Airport.Flight flight : airport.getFlights()) {
        if (!result.containsKey(flight.getDuration())) {
            result.put(flight.getDuration(), new HashSet<>());
        }
        result.get(flight.getDuration()).add(airport.getCountry());
    }
}
return result;
```

### Nested maps - `Map<K1, Map<K2, V>>`

*The number of airports by country and by number of runways*, and *the greatest number of runways by country and by city*. First get (or create) the inner map, then update it with one of the basic patterns.

```java
Map<Airport.Country, Map<Integer, Integer>> counts = new HashMap<>();
Map<Airport.Country, Map<String, Integer>> maximums = new HashMap<>();
for (Airport airport : getAll()) {
    if (!counts.containsKey(airport.getCountry())) {
        counts.put(airport.getCountry(), new HashMap<>());
        maximums.put(airport.getCountry(), new HashMap<>());
    }

    Map<Integer, Integer> countsByRunways = counts.get(airport.getCountry());
    countsByRunways.put(airport.getRunways(), countsByRunways.getOrDefault(airport.getRunways(), 0) + 1);

    Map<String, Integer> maximumsByCity = maximums.get(airport.getCountry());
    if (!maximumsByCity.containsKey(airport.getCity()) || airport.getRunways() > maximumsByCity.get(airport.getCity())) {
        maximumsByCity.put(airport.getCity(), airport.getRunways());
    }
}
```

### Sorted result

If the keys of the result must be sorted, create it as a `TreeMap` instead of a `HashMap`: `Map<String, Integer> result = new TreeMap<>();`. An enum key is sorted by the order of the declaration of its constants.

## Highlights

1. Choose `HashMap` by default, and `TreeMap` if you need the keys in order.
1. The key type of a `HashMap` must override `equals()` and `hashCode()` consistently (see [`A201`](A201-oo-basics-1.md)), the key type of a `TreeMap` must be comparable (see [`A301`](A301-oo-basics-2.md)) or you must provide a `Comparator` (see [`B102`](B102-comparator.md)).
1. Never modify a key object after you put it into a map.
1. `get()` returns `null` for a missing key; unboxing it into an `int` throws a `NullPointerException`. Use `getOrDefault()` or `containsKey()`.
1. Learn the three basic patterns: `put(key, getOrDefault(key, 0) + 1)`, keeping the maximum with `containsKey()`, and creating the inner list/set/map with `containsKey()` + `put()` before `get(key).add(...)`.

Check the documentation's *Method Summary* section for further highlights.

## References

1. [Interface `Map<K,V>`](https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/util/Map.html)
1. [Interface `Map.Entry<K,V>`](https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/util/Map.Entry.html)
1. [Class `HashMap<K,V>`](https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/util/HashMap.html)
1. [Class `TreeMap<K,V>`](https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/util/TreeMap.html)

## Tutorials

1. [Baeldung | Java Collections Series](https://www.baeldung.com/java-collections)
1. [Baeldung | A Guide to Java HashMap](https://www.baeldung.com/java-hashmap)
1. [Baeldung | A Guide to TreeMap in Java](https://www.baeldung.com/java-treemap)
