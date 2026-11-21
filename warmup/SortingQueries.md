[↑ Tutorials](README.md)

# `SortingQueries`

The interface of the queries that return a sorted list. Every sample sheet is shown: *arcade*, *campus*, *cinema*, *clinic*, *conference*.

Every task returns a sorted copy of the top-level objects: `new ArrayList<>(getAll())`, then `Collections.sort()` (the list of the repository cannot be modified). The tasks differ in *where* the comparison is implemented, and the documentation prescribes it; the grading checks the place. The stages are compared one by one with `Objects.compare(a, b, Comparator.naturalOrder())` (ascending) or `Comparator.reverseOrder()` (descending): the first stage that differs decides.

| | Task | Algorithm |
| --- | --- | --- |
| 1 | [multi-stage sorting of T, Comparable implemented by T](#B-sort-comparable) | sorting (multi-key, Comparable) |
| 2 | [multi-stage sorting of T, Comparator as a public top-level class](#B-sort-comparator) | sorting (multi-key, top-level Comparator) |
| 3 | [multi-stage sorting of T, Comparator as an anonymous class](#B-sort-anonymous) | sorting (multi-key, anonymous Comparator) |
| 4 | [multi-stage sorting of T, Comparator as a static nested class of the repository](#B-sort-nested-repository) | sorting (multi-key, nested Comparator in the repository) |
| 5 | [multi-stage sorting of T, Comparator as a static nested class of the model](#B-sort-nested-model) | sorting (multi-key, nested Comparator in the model) |

---

<a id="B-sort-comparable"></a>

## 1. multi-stage sorting of T, Comparable implemented by T

Template `B-sort-comparable`, algorithm: sorting (multi-key, Comparable).

**The algorithm.** (1) The model class implements `Comparable<T>`, with the type parameter. (2) `compareTo(other)` compares the stages one by one: compute the comparison of the first stage with `Objects.compare(this.x, other.x, Comparator.naturalOrder())` (ascending) or `Comparator.reverseOrder()` (descending); if it is not `0`, return it; otherwise go on with the next stage; the last stage's comparison is returned. (3) The query copies the list (`new ArrayList<>(getAll())`), sorts the copy by the natural order (`Collections.sort(result)`) and returns it. Numbers: smaller first; strings: alphabetical; `boolean`: `false` first.

**Typical mistakes:**

* Implementing the raw `Comparable` (without `<T>`): the parameter of `compareTo()` becomes `Object`, and every raw usage costs penalty points.
* Returning the comparison of a later stage even when an earlier stage differs: a later stage only breaks the ties of the earlier ones.
* Mixing up the directions: descending is `Comparator.reverseOrder()` (or swapping the two arguments), not a negated condition somewhere else.
* Modifying the data (e.g. sorting or removing from `getAll()`, or from a nested collection): a query must not modify the original data, and the list of the repository cannot be modified at all.

| Sheet | Method |
| --- | --- |
| arcade | `List<Game> getGamesOrderByReleasedDescThenByDescriptionDescThenByNameDesc()` |
| campus | `List<Course> getCoursesOrderBySemesterDescThenByTitleDescThenByCode()` |
| cinema | `List<Movie> getMoviesOrderByGenreDescThenByYearDesc()` |
| clinic | `List<Doctor> getDoctorsOrderByExperienceDescThenByRoomDescThenByName()` |
| conference | `List<Talk> getTalksOrderBySpeakerThenByRatingDescThenByTitleDesc()` |

### arcade

```java
/**
 * Returns the sorted copy of the games using the {@link Comparable} interface.
 *
 * <p>
 * The stages:
 * <ol>
 *     <li> the release year of the <i>game</i> (descending)
 *     <li> the description of the <i>game</i> (descending)
 *     <li> the name of the <i>game</i> (descending)
 * </ol>
 * </p>
 *
 * <p>
 * Notes:
 * <ol>
 *     <li>The method cannot modify the original list.
 *     <li>This task includes the implementation of the {@link Comparable} interface in your model class due to its nature.
 * </ol>
 * </p>
 *
 * @return the result
 */
@Override
public List<Game> getGamesOrderByReleasedDescThenByDescriptionDescThenByNameDesc() {
    List<Game> result = new ArrayList<>(getAll());
    Collections.sort(result);
    return result;
}

// in the class Game (... implements Comparable<Game>)
@Override
public int compareTo(@NonNull Game other) {
    int result = Objects.compare(this.getReleased(), other.getReleased(), Comparator.reverseOrder());
    if (result != 0) {
        return result;
    }
    result = Objects.compare(this.getDescription(), other.getDescription(), Comparator.reverseOrder());
    if (result != 0) {
        return result;
    }
    return Objects.compare(this.getName(), other.getName(), Comparator.reverseOrder());
}
```

### campus

```java
/**
 * Returns the sorted copy of the courses using the {@link Comparable} interface.
 *
 * <p>
 * The stages:
 * <ol>
 *     <li> the recommended semester of the <i>course</i> (descending)
 *     <li> the title of the <i>course</i> (descending)
 *     <li> the course code of the <i>course</i> (ascending)
 * </ol>
 * </p>
 *
 * <p>
 * Notes:
 * <ol>
 *     <li>The method cannot modify the original list.
 *     <li>This task includes the implementation of the {@link Comparable} interface in your model class due to its nature.
 * </ol>
 * </p>
 *
 * @return the result
 */
@Override
public List<Course> getCoursesOrderBySemesterDescThenByTitleDescThenByCode() {
    List<Course> result = new ArrayList<>(getAll());
    Collections.sort(result);
    return result;
}

// in the class Course (... implements Comparable<Course>)
@Override
public int compareTo(@NonNull Course other) {
    int result = Objects.compare(this.getSemester(), other.getSemester(), Comparator.reverseOrder());
    if (result != 0) {
        return result;
    }
    result = Objects.compare(this.getTitle(), other.getTitle(), Comparator.reverseOrder());
    if (result != 0) {
        return result;
    }
    return Objects.compare(this.getCode(), other.getCode(), Comparator.naturalOrder());
}
```

### cinema

```java
/**
 * Returns the sorted copy of the movies using the {@link Comparable} interface.
 *
 * <p>
 * The stages:
 * <ol>
 *     <li> the genre of the <i>movie</i> (descending)
 *     <li> the release year of the <i>movie</i> (descending)
 * </ol>
 * </p>
 *
 * <p>
 * Notes:
 * <ol>
 *     <li>The method cannot modify the original list.
 *     <li>This task includes the implementation of the {@link Comparable} interface in your model class due to its nature.
 * </ol>
 * </p>
 *
 * @return the result
 */
@Override
public List<Movie> getMoviesOrderByGenreDescThenByYearDesc() {
    List<Movie> result = new ArrayList<>(getAll());
    Collections.sort(result);
    return result;
}

// in the class Movie (... implements Comparable<Movie>)
@Override
public int compareTo(@NonNull Movie other) {
    int result = Objects.compare(this.getGenre(), other.getGenre(), Comparator.reverseOrder());
    if (result != 0) {
        return result;
    }
    return Objects.compare(this.getYear(), other.getYear(), Comparator.reverseOrder());
}
```

### clinic

```java
/**
 * Returns the sorted copy of the doctors using the {@link Comparable} interface.
 *
 * <p>
 * The stages:
 * <ol>
 *     <li> the years of experience of the <i>doctor</i> (descending)
 *     <li> the room number of the <i>doctor</i> (descending)
 *     <li> the name of the <i>doctor</i> (ascending)
 * </ol>
 * </p>
 *
 * <p>
 * Notes:
 * <ol>
 *     <li>The method cannot modify the original list.
 *     <li>This task includes the implementation of the {@link Comparable} interface in your model class due to its nature.
 * </ol>
 * </p>
 *
 * @return the result
 */
@Override
public List<Doctor> getDoctorsOrderByExperienceDescThenByRoomDescThenByName() {
    List<Doctor> result = new ArrayList<>(getAll());
    Collections.sort(result);
    return result;
}

// in the class Doctor (... implements Comparable<Doctor>)
@Override
public int compareTo(@NonNull Doctor other) {
    int result = Objects.compare(this.getExperience(), other.getExperience(), Comparator.reverseOrder());
    if (result != 0) {
        return result;
    }
    result = Objects.compare(this.getRoom(), other.getRoom(), Comparator.reverseOrder());
    if (result != 0) {
        return result;
    }
    return Objects.compare(this.getName(), other.getName(), Comparator.naturalOrder());
}
```

### conference

```java
/**
 * Returns the sorted copy of the talks using the {@link Comparable} interface.
 *
 * <p>
 * The stages:
 * <ol>
 *     <li> the speaker of the <i>talk</i> (ascending)
 *     <li> the average rating of the <i>talk</i> (descending)
 *     <li> the title of the <i>talk</i> (descending)
 * </ol>
 * </p>
 *
 * <p>
 * Notes:
 * <ol>
 *     <li>The method cannot modify the original list.
 *     <li>This task includes the implementation of the {@link Comparable} interface in your model class due to its nature.
 * </ol>
 * </p>
 *
 * @return the result
 */
@Override
public List<Talk> getTalksOrderBySpeakerThenByRatingDescThenByTitleDesc() {
    List<Talk> result = new ArrayList<>(getAll());
    Collections.sort(result);
    return result;
}

// in the class Talk (... implements Comparable<Talk>)
@Override
public int compareTo(@NonNull Talk other) {
    int result = Objects.compare(this.getSpeaker(), other.getSpeaker(), Comparator.naturalOrder());
    if (result != 0) {
        return result;
    }
    result = Objects.compare(this.getRating(), other.getRating(), Comparator.reverseOrder());
    if (result != 0) {
        return result;
    }
    return Objects.compare(this.getTitle(), other.getTitle(), Comparator.reverseOrder());
}
```

---

<a id="B-sort-comparator"></a>

## 2. multi-stage sorting of T, Comparator as a public top-level class

Template `B-sort-comparator`, algorithm: sorting (multi-key, top-level Comparator).

**The algorithm.** (1) A `public` class `TComparator implements Comparator<T>` in its own source file (in the package of the model). (2) `compare(first, second)` compares the stages one by one with `Objects.compare(...)` and the getters, as `compareTo()` does. (3) The query sorts a copy: `Collections.sort(result, new TComparator())`.

**Typical mistakes:**

* Writing the class inside another class or in the repository's file: the documentation prescribes a public top-level class in its own source file, and the grading checks the place.
* A raw `Comparator` (without `<T>`): penalty points, and the parameters become `Object`.
* Modifying the data (e.g. sorting or removing from `getAll()`, or from a nested collection): a query must not modify the original data, and the list of the repository cannot be modified at all.

| Sheet | Method |
| --- | --- |
| arcade | `List<Game> getGamesOrderByReleasedThenByDescriptionThenByName()` |
| campus | `List<Course> getCoursesOrderByTitleThenBySemesterThenByCodeDesc()` |
| cinema | `List<Movie> getMoviesOrderByGenreDescThenByYearDesc2()` |
| clinic | `List<Doctor> getDoctorsOrderByExperienceThenByRoomDescThenByName()` |
| conference | `List<Talk> getTalksOrderByDurationDescThenByRatingThenByTitleDesc()` |

### arcade

```java
/**
 * Returns the sorted copy of the games using the {@link java.util.Comparator} interface.
 *
 * <p>
 * The stages:
 * <ol>
 *     <li> the release year of the <i>game</i> (ascending)
 *     <li> the description of the <i>game</i> (ascending)
 *     <li> the name of the <i>game</i> (ascending)
 * </ol>
 * </p>
 *
 * <p>
 * Notes:
 * <ol>
 *     <li>The method cannot modify the original list.
 *     <li>The {@link java.util.Comparator} interface must be implemented as a public top-level class in its own source file.
 * </ol>
 * </p>
 *
 * @return the result
 */
@Override
public List<Game> getGamesOrderByReleasedThenByDescriptionThenByName() {
    List<Game> result = new ArrayList<>(getAll());
    Collections.sort(result, new arcade.GameComparator());
    return result;
}

// GameComparator.java
public class GameComparator implements Comparator<Game> {

    @Override
    public int compare(@NonNull Game first, @NonNull Game second) {
        int result = Objects.compare(first.getReleased(), second.getReleased(), Comparator.naturalOrder());
        if (result != 0) {
            return result;
        }
        result = Objects.compare(first.getDescription(), second.getDescription(), Comparator.naturalOrder());
        if (result != 0) {
            return result;
        }
        return Objects.compare(first.getName(), second.getName(), Comparator.naturalOrder());
    }
}
```

### campus

```java
/**
 * Returns the sorted copy of the courses using the {@link java.util.Comparator} interface.
 *
 * <p>
 * The stages:
 * <ol>
 *     <li> the title of the <i>course</i> (ascending)
 *     <li> the recommended semester of the <i>course</i> (ascending)
 *     <li> the course code of the <i>course</i> (descending)
 * </ol>
 * </p>
 *
 * <p>
 * Notes:
 * <ol>
 *     <li>The method cannot modify the original list.
 *     <li>The {@link java.util.Comparator} interface must be implemented as a public top-level class in its own source file.
 * </ol>
 * </p>
 *
 * @return the result
 */
@Override
public List<Course> getCoursesOrderByTitleThenBySemesterThenByCodeDesc() {
    List<Course> result = new ArrayList<>(getAll());
    Collections.sort(result, new campus.CourseComparator());
    return result;
}

// CourseComparator.java
public class CourseComparator implements Comparator<Course> {

    @Override
    public int compare(@NonNull Course first, @NonNull Course second) {
        int result = Objects.compare(first.getTitle(), second.getTitle(), Comparator.naturalOrder());
        if (result != 0) {
            return result;
        }
        result = Objects.compare(first.getSemester(), second.getSemester(), Comparator.naturalOrder());
        if (result != 0) {
            return result;
        }
        return Objects.compare(first.getCode(), second.getCode(), Comparator.reverseOrder());
    }
}
```

### cinema

```java
/**
 * Returns the sorted copy of the movies using the {@link java.util.Comparator} interface.
 *
 * <p>
 * The stages:
 * <ol>
 *     <li> the genre of the <i>movie</i> (descending)
 *     <li> the release year of the <i>movie</i> (descending)
 * </ol>
 * </p>
 *
 * <p>
 * Notes:
 * <ol>
 *     <li>The method cannot modify the original list.
 *     <li>The {@link java.util.Comparator} interface must be implemented as a public top-level class in its own source file.
 * </ol>
 * </p>
 *
 * @return the result
 */
@Override
public List<Movie> getMoviesOrderByGenreDescThenByYearDesc2() {
    List<Movie> result = new ArrayList<>(getAll());
    Collections.sort(result, new cinema.MovieComparator());
    return result;
}

// MovieComparator.java
public class MovieComparator implements Comparator<Movie> {

    @Override
    public int compare(@NonNull Movie first, @NonNull Movie second) {
        int result = Objects.compare(first.getGenre(), second.getGenre(), Comparator.reverseOrder());
        if (result != 0) {
            return result;
        }
        return Objects.compare(first.getYear(), second.getYear(), Comparator.reverseOrder());
    }
}
```

### clinic

```java
/**
 * Returns the sorted copy of the doctors using the {@link java.util.Comparator} interface.
 *
 * <p>
 * The stages:
 * <ol>
 *     <li> the years of experience of the <i>doctor</i> (ascending)
 *     <li> the room number of the <i>doctor</i> (descending)
 *     <li> the name of the <i>doctor</i> (ascending)
 * </ol>
 * </p>
 *
 * <p>
 * Notes:
 * <ol>
 *     <li>The method cannot modify the original list.
 *     <li>The {@link java.util.Comparator} interface must be implemented as a public top-level class in its own source file.
 * </ol>
 * </p>
 *
 * @return the result
 */
@Override
public List<Doctor> getDoctorsOrderByExperienceThenByRoomDescThenByName() {
    List<Doctor> result = new ArrayList<>(getAll());
    Collections.sort(result, new clinic.DoctorComparator());
    return result;
}

// DoctorComparator.java
public class DoctorComparator implements Comparator<Doctor> {

    @Override
    public int compare(@NonNull Doctor first, @NonNull Doctor second) {
        int result = Objects.compare(first.getExperience(), second.getExperience(), Comparator.naturalOrder());
        if (result != 0) {
            return result;
        }
        result = Objects.compare(first.getRoom(), second.getRoom(), Comparator.reverseOrder());
        if (result != 0) {
            return result;
        }
        return Objects.compare(first.getName(), second.getName(), Comparator.naturalOrder());
    }
}
```

### conference

```java
/**
 * Returns the sorted copy of the talks using the {@link java.util.Comparator} interface.
 *
 * <p>
 * The stages:
 * <ol>
 *     <li> the duration in minutes of the <i>talk</i> (descending)
 *     <li> the average rating of the <i>talk</i> (ascending)
 *     <li> the title of the <i>talk</i> (descending)
 * </ol>
 * </p>
 *
 * <p>
 * Notes:
 * <ol>
 *     <li>The method cannot modify the original list.
 *     <li>The {@link java.util.Comparator} interface must be implemented as a public top-level class in its own source file.
 * </ol>
 * </p>
 *
 * @return the result
 */
@Override
public List<Talk> getTalksOrderByDurationDescThenByRatingThenByTitleDesc() {
    List<Talk> result = new ArrayList<>(getAll());
    Collections.sort(result, new conference.TalkComparator());
    return result;
}

// TalkComparator.java
public class TalkComparator implements Comparator<Talk> {

    @Override
    public int compare(@NonNull Talk first, @NonNull Talk second) {
        int result = Objects.compare(first.getDuration(), second.getDuration(), Comparator.reverseOrder());
        if (result != 0) {
            return result;
        }
        result = Objects.compare(first.getRating(), second.getRating(), Comparator.naturalOrder());
        if (result != 0) {
            return result;
        }
        return Objects.compare(first.getTitle(), second.getTitle(), Comparator.reverseOrder());
    }
}
```

---

<a id="B-sort-anonymous"></a>

## 3. multi-stage sorting of T, Comparator as an anonymous class

Template `B-sort-anonymous`, algorithm: sorting (multi-key, anonymous Comparator).

**The algorithm.** (1) Copy the list. (2) Call `Collections.sort(result, new Comparator<T>() { ... })`: the anonymous class is written right there, with `compare(first, second)` comparing the stages as usual. (3) Return the copy. An anonymous class has no name: it is a one-off implementation with a single instance.

**Typical mistakes:**

* Writing a lambda expression (`(a, b) -> ...`): the documentation asks for an anonymous class, and the course has not covered lambdas.
* Defining a named class elsewhere: the grading looks for an anonymous class inside this method.
* Modifying the data (e.g. sorting or removing from `getAll()`, or from a nested collection): a query must not modify the original data, and the list of the repository cannot be modified at all.

| Sheet | Method |
| --- | --- |
| arcade | `List<Game> getGamesOrderByReleasedThenByDescriptionDescThenByNameDesc()` |
| campus | `List<Course> getCoursesOrderBySemesterThenByTitleDescThenByCodeDesc()` |
| cinema | `List<Movie> getMoviesOrderByGenreThenByYearDesc()` |
| clinic | `List<Doctor> getDoctorsOrderByRoomThenByExperienceThenByNameDesc()` |
| conference | `List<Talk> getTalksOrderByRatingThenByDurationDescThenByTitleDesc()` |

### arcade

```java
/**
 * Returns the sorted copy of the games using the {@link java.util.Comparator} interface.
 *
 * <p>
 * The stages:
 * <ol>
 *     <li> the release year of the <i>game</i> (ascending)
 *     <li> the description of the <i>game</i> (descending)
 *     <li> the name of the <i>game</i> (descending)
 * </ol>
 * </p>
 *
 * <p>
 * Notes:
 * <ol>
 *     <li>The method cannot modify the original list.
 *     <li>The {@link java.util.Comparator} interface must be implemented as an anonymous class inside this method (not as a lambda expression).
 * </ol>
 * </p>
 *
 * @return the result
 */
@Override
public List<Game> getGamesOrderByReleasedThenByDescriptionDescThenByNameDesc() {
    List<Game> result = new ArrayList<>(getAll());
    Collections.sort(result, new Comparator<Game>() {
        @Override
        public int compare(@NonNull Game first, @NonNull Game second) {
            int result = Objects.compare(first.getReleased(), second.getReleased(), Comparator.naturalOrder());
            if (result != 0) {
                return result;
            }
            result = Objects.compare(first.getDescription(), second.getDescription(), Comparator.reverseOrder());
            if (result != 0) {
                return result;
            }
            return Objects.compare(first.getName(), second.getName(), Comparator.reverseOrder());
        }
    });
    return result;
}
```

### campus

```java
/**
 * Returns the sorted copy of the courses using the {@link java.util.Comparator} interface.
 *
 * <p>
 * The stages:
 * <ol>
 *     <li> the recommended semester of the <i>course</i> (ascending)
 *     <li> the title of the <i>course</i> (descending)
 *     <li> the course code of the <i>course</i> (descending)
 * </ol>
 * </p>
 *
 * <p>
 * Notes:
 * <ol>
 *     <li>The method cannot modify the original list.
 *     <li>The {@link java.util.Comparator} interface must be implemented as an anonymous class inside this method (not as a lambda expression).
 * </ol>
 * </p>
 *
 * @return the result
 */
@Override
public List<Course> getCoursesOrderBySemesterThenByTitleDescThenByCodeDesc() {
    List<Course> result = new ArrayList<>(getAll());
    Collections.sort(result, new Comparator<Course>() {
        @Override
        public int compare(@NonNull Course first, @NonNull Course second) {
            int result = Objects.compare(first.getSemester(), second.getSemester(), Comparator.naturalOrder());
            if (result != 0) {
                return result;
            }
            result = Objects.compare(first.getTitle(), second.getTitle(), Comparator.reverseOrder());
            if (result != 0) {
                return result;
            }
            return Objects.compare(first.getCode(), second.getCode(), Comparator.reverseOrder());
        }
    });
    return result;
}
```

### cinema

```java
/**
 * Returns the sorted copy of the movies using the {@link java.util.Comparator} interface.
 *
 * <p>
 * The stages:
 * <ol>
 *     <li> the genre of the <i>movie</i> (ascending)
 *     <li> the release year of the <i>movie</i> (descending)
 * </ol>
 * </p>
 *
 * <p>
 * Notes:
 * <ol>
 *     <li>The method cannot modify the original list.
 *     <li>The {@link java.util.Comparator} interface must be implemented as an anonymous class inside this method (not as a lambda expression).
 * </ol>
 * </p>
 *
 * @return the result
 */
@Override
public List<Movie> getMoviesOrderByGenreThenByYearDesc() {
    List<Movie> result = new ArrayList<>(getAll());
    Collections.sort(result, new Comparator<Movie>() {
        @Override
        public int compare(@NonNull Movie first, @NonNull Movie second) {
            int result = Objects.compare(first.getGenre(), second.getGenre(), Comparator.naturalOrder());
            if (result != 0) {
                return result;
            }
            return Objects.compare(first.getYear(), second.getYear(), Comparator.reverseOrder());
        }
    });
    return result;
}
```

### clinic

```java
/**
 * Returns the sorted copy of the doctors using the {@link java.util.Comparator} interface.
 *
 * <p>
 * The stages:
 * <ol>
 *     <li> the room number of the <i>doctor</i> (ascending)
 *     <li> the years of experience of the <i>doctor</i> (ascending)
 *     <li> the name of the <i>doctor</i> (descending)
 * </ol>
 * </p>
 *
 * <p>
 * Notes:
 * <ol>
 *     <li>The method cannot modify the original list.
 *     <li>The {@link java.util.Comparator} interface must be implemented as an anonymous class inside this method (not as a lambda expression).
 * </ol>
 * </p>
 *
 * @return the result
 */
@Override
public List<Doctor> getDoctorsOrderByRoomThenByExperienceThenByNameDesc() {
    List<Doctor> result = new ArrayList<>(getAll());
    Collections.sort(result, new Comparator<Doctor>() {
        @Override
        public int compare(@NonNull Doctor first, @NonNull Doctor second) {
            int result = Objects.compare(first.getRoom(), second.getRoom(), Comparator.naturalOrder());
            if (result != 0) {
                return result;
            }
            result = Objects.compare(first.getExperience(), second.getExperience(), Comparator.naturalOrder());
            if (result != 0) {
                return result;
            }
            return Objects.compare(first.getName(), second.getName(), Comparator.reverseOrder());
        }
    });
    return result;
}
```

### conference

```java
/**
 * Returns the sorted copy of the talks using the {@link java.util.Comparator} interface.
 *
 * <p>
 * The stages:
 * <ol>
 *     <li> the average rating of the <i>talk</i> (ascending)
 *     <li> the duration in minutes of the <i>talk</i> (descending)
 *     <li> the title of the <i>talk</i> (descending)
 * </ol>
 * </p>
 *
 * <p>
 * Notes:
 * <ol>
 *     <li>The method cannot modify the original list.
 *     <li>The {@link java.util.Comparator} interface must be implemented as an anonymous class inside this method (not as a lambda expression).
 * </ol>
 * </p>
 *
 * @return the result
 */
@Override
public List<Talk> getTalksOrderByRatingThenByDurationDescThenByTitleDesc() {
    List<Talk> result = new ArrayList<>(getAll());
    Collections.sort(result, new Comparator<Talk>() {
        @Override
        public int compare(@NonNull Talk first, @NonNull Talk second) {
            int result = Objects.compare(first.getRating(), second.getRating(), Comparator.naturalOrder());
            if (result != 0) {
                return result;
            }
            result = Objects.compare(first.getDuration(), second.getDuration(), Comparator.reverseOrder());
            if (result != 0) {
                return result;
            }
            return Objects.compare(first.getTitle(), second.getTitle(), Comparator.reverseOrder());
        }
    });
    return result;
}
```

---

<a id="B-sort-nested-repository"></a>

## 4. multi-stage sorting of T, Comparator as a static nested class of the repository

Template `B-sort-nested-repository`, algorithm: sorting (multi-key, nested Comparator in the repository).

**The algorithm.** (1) A `public static class TComparator implements Comparator<T>` inside the repository class. (2) `compare()` compares the stages with `Objects.compare(...)`. (3) The query sorts a copy with `new TComparator()`.

**Typical mistakes:**

* Forgetting `static`: a non-static nested class needs an instance of the repository, and the grading checks a static nested class.
* Putting the class into the model or into its own file: the documentation prescribes the repository.
* Modifying the data (e.g. sorting or removing from `getAll()`, or from a nested collection): a query must not modify the original data, and the list of the repository cannot be modified at all.

| Sheet | Method |
| --- | --- |
| arcade | `List<Game> getGamesOrderByDescriptionDescThenByReleasedDescThenByNameDesc()` |
| campus | `List<Course> getCoursesOrderByCreditsThenByTitleDescThenByCodeDesc()` |
| cinema | `List<Movie> getMoviesOrderByGenreDescThenByYear()` |
| clinic | `List<Doctor> getDoctorsOrderByOnCallDescThenByRoomThenByNameDesc()` |
| conference | `List<Talk> getTalksOrderBySpeakerDescThenByRatingThenByTitle()` |

### arcade

```java
/**
 * Returns the sorted copy of the games using the {@link java.util.Comparator} interface.
 *
 * <p>
 * The stages:
 * <ol>
 *     <li> the description of the <i>game</i> (descending)
 *     <li> the release year of the <i>game</i> (descending)
 *     <li> the name of the <i>game</i> (descending)
 * </ol>
 * </p>
 *
 * <p>
 * Notes:
 * <ol>
 *     <li>The method cannot modify the original list.
 *     <li>The {@link java.util.Comparator} interface must be implemented as a static nested class of your repository class.
 * </ol>
 * </p>
 *
 * @return the result
 */
@Override
public List<Game> getGamesOrderByDescriptionDescThenByReleasedDescThenByNameDesc() {
    List<Game> result = new ArrayList<>(getAll());
    Collections.sort(result, new GameComparator());
    return result;
}

// in the class GameRepository
public static class GameComparator implements Comparator<Game> {

    @Override
    public int compare(@NonNull Game first, @NonNull Game second) {
        int result = Objects.compare(first.getDescription(), second.getDescription(), Comparator.reverseOrder());
        if (result != 0) {
            return result;
        }
        result = Objects.compare(first.getReleased(), second.getReleased(), Comparator.reverseOrder());
        if (result != 0) {
            return result;
        }
        return Objects.compare(first.getName(), second.getName(), Comparator.reverseOrder());
    }
}
```

### campus

```java
/**
 * Returns the sorted copy of the courses using the {@link java.util.Comparator} interface.
 *
 * <p>
 * The stages:
 * <ol>
 *     <li> the credits of the <i>course</i> (ascending)
 *     <li> the title of the <i>course</i> (descending)
 *     <li> the course code of the <i>course</i> (descending)
 * </ol>
 * </p>
 *
 * <p>
 * Notes:
 * <ol>
 *     <li>The method cannot modify the original list.
 *     <li>The {@link java.util.Comparator} interface must be implemented as a static nested class of your repository class.
 * </ol>
 * </p>
 *
 * @return the result
 */
@Override
public List<Course> getCoursesOrderByCreditsThenByTitleDescThenByCodeDesc() {
    List<Course> result = new ArrayList<>(getAll());
    Collections.sort(result, new CourseComparator());
    return result;
}

// in the class CourseRepository
public static class CourseComparator implements Comparator<Course> {

    @Override
    public int compare(@NonNull Course first, @NonNull Course second) {
        int result = Objects.compare(first.getCredits(), second.getCredits(), Comparator.naturalOrder());
        if (result != 0) {
            return result;
        }
        result = Objects.compare(first.getTitle(), second.getTitle(), Comparator.reverseOrder());
        if (result != 0) {
            return result;
        }
        return Objects.compare(first.getCode(), second.getCode(), Comparator.reverseOrder());
    }
}
```

### cinema

```java
/**
 * Returns the sorted copy of the movies using the {@link java.util.Comparator} interface.
 *
 * <p>
 * The stages:
 * <ol>
 *     <li> the genre of the <i>movie</i> (descending)
 *     <li> the release year of the <i>movie</i> (ascending)
 * </ol>
 * </p>
 *
 * <p>
 * Notes:
 * <ol>
 *     <li>The method cannot modify the original list.
 *     <li>The {@link java.util.Comparator} interface must be implemented as a static nested class of your repository class.
 * </ol>
 * </p>
 *
 * @return the result
 */
@Override
public List<Movie> getMoviesOrderByGenreDescThenByYear() {
    List<Movie> result = new ArrayList<>(getAll());
    Collections.sort(result, new MovieComparator());
    return result;
}

// in the class MovieRepository
public static class MovieComparator implements Comparator<Movie> {

    @Override
    public int compare(@NonNull Movie first, @NonNull Movie second) {
        int result = Objects.compare(first.getGenre(), second.getGenre(), Comparator.reverseOrder());
        if (result != 0) {
            return result;
        }
        return Objects.compare(first.getYear(), second.getYear(), Comparator.naturalOrder());
    }
}
```

### clinic

```java
/**
 * Returns the sorted copy of the doctors using the {@link java.util.Comparator} interface.
 *
 * <p>
 * The stages:
 * <ol>
 *     <li> the on-call status of the <i>doctor</i> (descending)
 *     <li> the room number of the <i>doctor</i> (ascending)
 *     <li> the name of the <i>doctor</i> (descending)
 * </ol>
 * </p>
 *
 * <p>
 * Notes:
 * <ol>
 *     <li>The method cannot modify the original list.
 *     <li>The {@link java.util.Comparator} interface must be implemented as a static nested class of your repository class.
 * </ol>
 * </p>
 *
 * @return the result
 */
@Override
public List<Doctor> getDoctorsOrderByOnCallDescThenByRoomThenByNameDesc() {
    List<Doctor> result = new ArrayList<>(getAll());
    Collections.sort(result, new DoctorComparator());
    return result;
}

// in the class DoctorRepository
public static class DoctorComparator implements Comparator<Doctor> {

    @Override
    public int compare(@NonNull Doctor first, @NonNull Doctor second) {
        int result = Objects.compare(first.isOnCall(), second.isOnCall(), Comparator.reverseOrder());
        if (result != 0) {
            return result;
        }
        result = Objects.compare(first.getRoom(), second.getRoom(), Comparator.naturalOrder());
        if (result != 0) {
            return result;
        }
        return Objects.compare(first.getName(), second.getName(), Comparator.reverseOrder());
    }
}
```

### conference

```java
/**
 * Returns the sorted copy of the talks using the {@link java.util.Comparator} interface.
 *
 * <p>
 * The stages:
 * <ol>
 *     <li> the speaker of the <i>talk</i> (descending)
 *     <li> the average rating of the <i>talk</i> (ascending)
 *     <li> the title of the <i>talk</i> (ascending)
 * </ol>
 * </p>
 *
 * <p>
 * Notes:
 * <ol>
 *     <li>The method cannot modify the original list.
 *     <li>The {@link java.util.Comparator} interface must be implemented as a static nested class of your repository class.
 * </ol>
 * </p>
 *
 * @return the result
 */
@Override
public List<Talk> getTalksOrderBySpeakerDescThenByRatingThenByTitle() {
    List<Talk> result = new ArrayList<>(getAll());
    Collections.sort(result, new TalkComparator());
    return result;
}

// in the class TalkRepository
public static class TalkComparator implements Comparator<Talk> {

    @Override
    public int compare(@NonNull Talk first, @NonNull Talk second) {
        int result = Objects.compare(first.getSpeaker(), second.getSpeaker(), Comparator.reverseOrder());
        if (result != 0) {
            return result;
        }
        result = Objects.compare(first.getRating(), second.getRating(), Comparator.naturalOrder());
        if (result != 0) {
            return result;
        }
        return Objects.compare(first.getTitle(), second.getTitle(), Comparator.naturalOrder());
    }
}
```

---

<a id="B-sort-nested-model"></a>

## 5. multi-stage sorting of T, Comparator as a static nested class of the model

Template `B-sort-nested-model`, algorithm: sorting (multi-key, nested Comparator in the model).

**The algorithm.** (1) A `public static class TComparator implements Comparator<T>` inside the model class (beside the nested class and the enum). (2) `compare()` compares the stages. (3) The query sorts a copy with `new T.TComparator()`.

**Typical mistakes:**

* Forgetting `static` or `public`: the class must be a public static nested class of the model.
* Putting the class into the repository: the documentation prescribes the model class.
* Modifying the data (e.g. sorting or removing from `getAll()`, or from a nested collection): a query must not modify the original data, and the list of the repository cannot be modified at all.

| Sheet | Method |
| --- | --- |
| arcade | `List<Game> getGamesOrderByDescriptionDescThenByReleasedThenByNameDesc()` |
| campus | `List<Course> getCoursesOrderByCreditsThenByTitleDescThenByCode()` |
| cinema | `List<Movie> getMoviesOrderByGenreThenByTitle()` |
| clinic | `List<Doctor> getDoctorsOrderByRoomThenByOnCallThenByName()` |
| conference | `List<Talk> getTalksOrderBySpeakerDescThenByRatingDescThenByTitle()` |

### arcade

```java
/**
 * Returns the sorted copy of the games using the {@link java.util.Comparator} interface.
 *
 * <p>
 * The stages:
 * <ol>
 *     <li> the description of the <i>game</i> (descending)
 *     <li> the release year of the <i>game</i> (ascending)
 *     <li> the name of the <i>game</i> (descending)
 * </ol>
 * </p>
 *
 * <p>
 * Notes:
 * <ol>
 *     <li>The method cannot modify the original list.
 *     <li>The {@link java.util.Comparator} interface must be implemented as a static nested class of the class representing the <i>game</i>.
 * </ol>
 * </p>
 *
 * @return the result
 */
@Override
public List<Game> getGamesOrderByDescriptionDescThenByReleasedThenByNameDesc() {
    List<Game> result = new ArrayList<>(getAll());
    Collections.sort(result, new Game.GameComparator());
    return result;
}

// in the class Game
public static class GameComparator implements Comparator<Game> {

    @Override
    public int compare(@NonNull Game first, @NonNull Game second) {
        int result = Objects.compare(first.getDescription(), second.getDescription(), Comparator.reverseOrder());
        if (result != 0) {
            return result;
        }
        result = Objects.compare(first.getReleased(), second.getReleased(), Comparator.naturalOrder());
        if (result != 0) {
            return result;
        }
        return Objects.compare(first.getName(), second.getName(), Comparator.reverseOrder());
    }
}
```

### campus

```java
/**
 * Returns the sorted copy of the courses using the {@link java.util.Comparator} interface.
 *
 * <p>
 * The stages:
 * <ol>
 *     <li> the credits of the <i>course</i> (ascending)
 *     <li> the title of the <i>course</i> (descending)
 *     <li> the course code of the <i>course</i> (ascending)
 * </ol>
 * </p>
 *
 * <p>
 * Notes:
 * <ol>
 *     <li>The method cannot modify the original list.
 *     <li>The {@link java.util.Comparator} interface must be implemented as a static nested class of the class representing the <i>course</i>.
 * </ol>
 * </p>
 *
 * @return the result
 */
@Override
public List<Course> getCoursesOrderByCreditsThenByTitleDescThenByCode() {
    List<Course> result = new ArrayList<>(getAll());
    Collections.sort(result, new Course.CourseComparator());
    return result;
}

// in the class Course
public static class CourseComparator implements Comparator<Course> {

    @Override
    public int compare(@NonNull Course first, @NonNull Course second) {
        int result = Objects.compare(first.getCredits(), second.getCredits(), Comparator.naturalOrder());
        if (result != 0) {
            return result;
        }
        result = Objects.compare(first.getTitle(), second.getTitle(), Comparator.reverseOrder());
        if (result != 0) {
            return result;
        }
        return Objects.compare(first.getCode(), second.getCode(), Comparator.naturalOrder());
    }
}
```

### cinema

```java
/**
 * Returns the sorted copy of the movies using the {@link java.util.Comparator} interface.
 *
 * <p>
 * The stages:
 * <ol>
 *     <li> the genre of the <i>movie</i> (ascending)
 *     <li> the title of the <i>movie</i> (ascending)
 * </ol>
 * </p>
 *
 * <p>
 * Notes:
 * <ol>
 *     <li>The method cannot modify the original list.
 *     <li>The {@link java.util.Comparator} interface must be implemented as a static nested class of the class representing the <i>movie</i>.
 * </ol>
 * </p>
 *
 * @return the result
 */
@Override
public List<Movie> getMoviesOrderByGenreThenByTitle() {
    List<Movie> result = new ArrayList<>(getAll());
    Collections.sort(result, new Movie.MovieComparator());
    return result;
}

// in the class Movie
public static class MovieComparator implements Comparator<Movie> {

    @Override
    public int compare(@NonNull Movie first, @NonNull Movie second) {
        int result = Objects.compare(first.getGenre(), second.getGenre(), Comparator.naturalOrder());
        if (result != 0) {
            return result;
        }
        return Objects.compare(first.getTitle(), second.getTitle(), Comparator.naturalOrder());
    }
}
```

### clinic

```java
/**
 * Returns the sorted copy of the doctors using the {@link java.util.Comparator} interface.
 *
 * <p>
 * The stages:
 * <ol>
 *     <li> the room number of the <i>doctor</i> (ascending)
 *     <li> the on-call status of the <i>doctor</i> (ascending)
 *     <li> the name of the <i>doctor</i> (ascending)
 * </ol>
 * </p>
 *
 * <p>
 * Notes:
 * <ol>
 *     <li>The method cannot modify the original list.
 *     <li>The {@link java.util.Comparator} interface must be implemented as a static nested class of the class representing the <i>doctor</i>.
 * </ol>
 * </p>
 *
 * @return the result
 */
@Override
public List<Doctor> getDoctorsOrderByRoomThenByOnCallThenByName() {
    List<Doctor> result = new ArrayList<>(getAll());
    Collections.sort(result, new Doctor.DoctorComparator());
    return result;
}

// in the class Doctor
public static class DoctorComparator implements Comparator<Doctor> {

    @Override
    public int compare(@NonNull Doctor first, @NonNull Doctor second) {
        int result = Objects.compare(first.getRoom(), second.getRoom(), Comparator.naturalOrder());
        if (result != 0) {
            return result;
        }
        result = Objects.compare(first.isOnCall(), second.isOnCall(), Comparator.naturalOrder());
        if (result != 0) {
            return result;
        }
        return Objects.compare(first.getName(), second.getName(), Comparator.naturalOrder());
    }
}
```

### conference

```java
/**
 * Returns the sorted copy of the talks using the {@link java.util.Comparator} interface.
 *
 * <p>
 * The stages:
 * <ol>
 *     <li> the speaker of the <i>talk</i> (descending)
 *     <li> the average rating of the <i>talk</i> (descending)
 *     <li> the title of the <i>talk</i> (ascending)
 * </ol>
 * </p>
 *
 * <p>
 * Notes:
 * <ol>
 *     <li>The method cannot modify the original list.
 *     <li>The {@link java.util.Comparator} interface must be implemented as a static nested class of the class representing the <i>talk</i>.
 * </ol>
 * </p>
 *
 * @return the result
 */
@Override
public List<Talk> getTalksOrderBySpeakerDescThenByRatingDescThenByTitle() {
    List<Talk> result = new ArrayList<>(getAll());
    Collections.sort(result, new Talk.TalkComparator());
    return result;
}

// in the class Talk
public static class TalkComparator implements Comparator<Talk> {

    @Override
    public int compare(@NonNull Talk first, @NonNull Talk second) {
        int result = Objects.compare(first.getSpeaker(), second.getSpeaker(), Comparator.reverseOrder());
        if (result != 0) {
            return result;
        }
        result = Objects.compare(first.getRating(), second.getRating(), Comparator.reverseOrder());
        if (result != 0) {
            return result;
        }
        return Objects.compare(first.getTitle(), second.getTitle(), Comparator.naturalOrder());
    }
}
```
