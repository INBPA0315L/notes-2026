[↑ Tutorials](README.md)

# `BasicMapQueries`

The interface of the queries that return a simple dictionary (counting occurrences, first or last occurrences). Every sample sheet is shown: *arcade*, *campus*, *cinema*, *clinic*, *conference*.

The result is a simple `Map` filled in a single loop over the top-level objects (`Map<K, V> result = new HashMap<>();`). The question is what happens when a key comes again: **counting** (`put(key, getOrDefault(key, 0) + 1)`), **the last** (`put()` replaces the value), **the first** (put only if `!containsKey(key)`), **summing** (`getOrDefault(key, 0) + value`). Only the keys that occur are in the map.

| | Task | Algorithm |
| --- | --- | --- |
| 1 | [enum value -> number of T](#D1-enum-count) | counting occurrences |
| 2 | [value of a field of T -> number of T](#D1-field-count) | counting occurrences |
| 3 | [T -> its enum value](#D1-top-enum) | mapping |
| 4 | [enum value -> first T with that value](#D1-enum-first) | first occurrence |
| 5 | [enum value -> last T with that value](#D1-enum-last) | last occurrence |
| 6 | [enum value -> field value of the first T with that value](#D1-enum-first-field) | first occurrence |
| 7 | [enum value -> field value of the last T with that value](#D1-enum-last-field) | last occurrence |
| 8 | [value of a field of T -> first T with that value](#D1-field-first) | first occurrence |
| 9 | [value of a field of T -> enum value of the last T with that value](#D1-field-last-enum) | last occurrence |
| 10 | [enum value -> sum of a numeric field of T](#D1-enum-sum) | grouping + sum |

---

<a id="D1-enum-count"></a>

## 1. enum value -> number of T

Template `D1-enum-count`, algorithm: counting occurrences.

**The algorithm.** Counting the occurrences of every enum value. (1) A new `HashMap`. (2) For every object, the current count of its enum value is `result.getOrDefault(key, 0)` (`0` for a key not seen yet); `put()` stores it increased by one. (3) Only the values that occur become keys.

**Typical mistakes:**

* `result.put(key, result.get(key) + 1)` for a new key: `get()` returns `null`, and adding to it throws a `NullPointerException`. Use `getOrDefault(key, 0)`.
* Putting every enum value into the map in advance (e.g. by `V.values()`): only the values that occur (that have such objects) may be keys.
* Declaring a collection variable by its class (`ArrayList<...> result`, `HashMap<...> result`): the references of collections are interfaces (`List`, `Set`, `Map`), the class appears only after `new`.

| Sheet | Method |
| --- | --- |
| arcade | `Map<Game.Platform, Integer> getCountOfGamesByPlatforms()` |
| campus | `Map<Course.Faculty, Integer> getCountOfCoursesByFaculties()` |
| cinema | `Map<Movie.Studio, Integer> getCountOfMoviesByStudios()` |
| clinic | `Map<Doctor.Department, Integer> getCountOfDoctorsByDepartments()` |
| conference | `Map<Talk.Track, Integer> getCountOfTalksByTracks()` |

### arcade

```java
/**
 * Returns a dictionary that maps each platform to the number of corresponding games.
 * The platforms without games are not included.
 *
 * @return the result
 */
@Override
public Map<Game.Platform, Integer> getCountOfGamesByPlatforms() {
    Map<Game.Platform, Integer> result = new HashMap<>();
    for (Game t : getAll()) {
        result.put(t.getPlatform(), result.getOrDefault(t.getPlatform(), 0) + 1);
    }
    return result;
}
```

### campus

```java
/**
 * Returns a dictionary that maps each faculty to the number of corresponding courses.
 * The faculties without courses are not included.
 *
 * @return the result
 */
@Override
public Map<Course.Faculty, Integer> getCountOfCoursesByFaculties() {
    Map<Course.Faculty, Integer> result = new HashMap<>();
    for (Course t : getAll()) {
        result.put(t.getFaculty(), result.getOrDefault(t.getFaculty(), 0) + 1);
    }
    return result;
}
```

### cinema

```java
/**
 * Returns a dictionary that maps each studio to the number of corresponding movies.
 * The studios without movies are not included.
 *
 * @return the result
 */
@Override
public Map<Movie.Studio, Integer> getCountOfMoviesByStudios() {
    Map<Movie.Studio, Integer> result = new HashMap<>();
    for (Movie t : getAll()) {
        result.put(t.getStudio(), result.getOrDefault(t.getStudio(), 0) + 1);
    }
    return result;
}
```

### clinic

```java
/**
 * Returns a dictionary that maps each department to the number of corresponding doctors.
 * The departments without doctors are not included.
 *
 * @return the result
 */
@Override
public Map<Doctor.Department, Integer> getCountOfDoctorsByDepartments() {
    Map<Doctor.Department, Integer> result = new HashMap<>();
    for (Doctor t : getAll()) {
        result.put(t.getDepartment(), result.getOrDefault(t.getDepartment(), 0) + 1);
    }
    return result;
}
```

### conference

```java
/**
 * Returns a dictionary that maps each track to the number of corresponding talks.
 * The tracks without talks are not included.
 *
 * @return the result
 */
@Override
public Map<Talk.Track, Integer> getCountOfTalksByTracks() {
    Map<Talk.Track, Integer> result = new HashMap<>();
    for (Talk t : getAll()) {
        result.put(t.getTrack(), result.getOrDefault(t.getTrack(), 0) + 1);
    }
    return result;
}
```

---

<a id="D1-field-count"></a>

## 2. value of a field of T -> number of T

Template `D1-field-count`, algorithm: counting occurrences.

**The algorithm.** Counting the occurrences of every value of a field: the same as counting enum values, the key being the field (boxed in the type: `Map<Integer, Integer>` for an `int` field).

**Typical mistakes:**

* `result.put(key, result.get(key) + 1)` for a new key: `get()` returns `null`, and adding to it throws a `NullPointerException`. Use `getOrDefault(key, 0)`.
* Writing `Map<int, Integer>`: a type argument cannot be primitive, use the wrapper class.

| Sheet | Method |
| --- | --- |
| arcade | `Map<Integer, Integer> getCountOfGamesByReleased()` |
| campus | `Map<String, Integer> getCountOfCoursesByTitle()` |
| cinema | `Map<String, Integer> getCountOfMoviesByGenre()` |
| clinic | `Map<String, Integer> getCountOfDoctorsByRoom()` |
| conference | `Map<Double, Integer> getCountOfTalksByRating()` |

### arcade

```java
/**
 * Returns a dictionary that maps each release year to the number of corresponding games.
 *
 * @return the result
 */
@Override
public Map<Integer, Integer> getCountOfGamesByReleased() {
    Map<Integer, Integer> result = new HashMap<>();
    for (Game t : getAll()) {
        result.put(t.getReleased(), result.getOrDefault(t.getReleased(), 0) + 1);
    }
    return result;
}
```

### campus

```java
/**
 * Returns a dictionary that maps each title to the number of corresponding courses.
 *
 * @return the result
 */
@Override
public Map<String, Integer> getCountOfCoursesByTitle() {
    Map<String, Integer> result = new HashMap<>();
    for (Course t : getAll()) {
        result.put(t.getTitle(), result.getOrDefault(t.getTitle(), 0) + 1);
    }
    return result;
}
```

### cinema

```java
/**
 * Returns a dictionary that maps each genre to the number of corresponding movies.
 *
 * @return the result
 */
@Override
public Map<String, Integer> getCountOfMoviesByGenre() {
    Map<String, Integer> result = new HashMap<>();
    for (Movie t : getAll()) {
        result.put(t.getGenre(), result.getOrDefault(t.getGenre(), 0) + 1);
    }
    return result;
}
```

### clinic

```java
/**
 * Returns a dictionary that maps each room number to the number of corresponding doctors.
 *
 * @return the result
 */
@Override
public Map<String, Integer> getCountOfDoctorsByRoom() {
    Map<String, Integer> result = new HashMap<>();
    for (Doctor t : getAll()) {
        result.put(t.getRoom(), result.getOrDefault(t.getRoom(), 0) + 1);
    }
    return result;
}
```

### conference

```java
/**
 * Returns a dictionary that maps each average rating to the number of corresponding talks.
 *
 * @return the result
 */
@Override
public Map<Double, Integer> getCountOfTalksByRating() {
    Map<Double, Integer> result = new HashMap<>();
    for (Talk t : getAll()) {
        result.put(t.getRating(), result.getOrDefault(t.getRating(), 0) + 1);
    }
    return result;
}
```

---

<a id="D1-top-enum"></a>

## 3. T -> its enum value

Template `D1-top-enum`, algorithm: mapping.

**The algorithm.** Every object is a key, its enum value the value: one `put()` per object. An object works as a key by the `equals()` and `hashCode()` generated from its identifier fields.

**Typical mistakes:**

* Using the name (or another field) of the object as the key: the key type is the class itself.
* A model without `@EqualsAndHashCode` on the identifier: equal objects would be different keys.

| Sheet | Method |
| --- | --- |
| arcade | `Map<Game, Game.Platform> getPlatformsByGames()` |
| campus | `Map<Course, Course.Faculty> getFacultiesByCourses()` |
| cinema | `Map<Movie, Movie.Studio> getStudiosByMovies()` |
| clinic | `Map<Doctor, Doctor.Department> getDepartmentsByDoctors()` |
| conference | `Map<Talk, Talk.Track> getTracksByTalks()` |

### arcade

```java
/**
 * Returns a dictionary that maps each game to the corresponding platform.
 *
 * @return the result
 */
@Override
public Map<Game, Game.Platform> getPlatformsByGames() {
    Map<Game, Game.Platform> result = new HashMap<>();
    for (Game t : getAll()) {
        result.put(t, t.getPlatform());
    }
    return result;
}
```

### campus

```java
/**
 * Returns a dictionary that maps each course to the corresponding faculty.
 *
 * @return the result
 */
@Override
public Map<Course, Course.Faculty> getFacultiesByCourses() {
    Map<Course, Course.Faculty> result = new HashMap<>();
    for (Course t : getAll()) {
        result.put(t, t.getFaculty());
    }
    return result;
}
```

### cinema

```java
/**
 * Returns a dictionary that maps each movie to the corresponding studio.
 *
 * @return the result
 */
@Override
public Map<Movie, Movie.Studio> getStudiosByMovies() {
    Map<Movie, Movie.Studio> result = new HashMap<>();
    for (Movie t : getAll()) {
        result.put(t, t.getStudio());
    }
    return result;
}
```

### clinic

```java
/**
 * Returns a dictionary that maps each doctor to the corresponding department.
 *
 * @return the result
 */
@Override
public Map<Doctor, Doctor.Department> getDepartmentsByDoctors() {
    Map<Doctor, Doctor.Department> result = new HashMap<>();
    for (Doctor t : getAll()) {
        result.put(t, t.getDepartment());
    }
    return result;
}
```

### conference

```java
/**
 * Returns a dictionary that maps each talk to the corresponding track.
 *
 * @return the result
 */
@Override
public Map<Talk, Talk.Track> getTracksByTalks() {
    Map<Talk, Talk.Track> result = new HashMap<>();
    for (Talk t : getAll()) {
        result.put(t, t.getTrack());
    }
    return result;
}
```

---

<a id="D1-enum-first"></a>

## 4. enum value -> first T with that value

Template `D1-enum-first`, algorithm: first occurrence.

**The algorithm.** The *first* object of every enum value: put only if the key is not in the map yet. A later object with the same value does not change the map.

**Typical mistakes:**

* Putting for every object: `put()` replaces the value, so the *last* object would remain. Put only if `!result.containsKey(key)`.
* Putting every enum value into the map in advance (e.g. by `V.values()`): only the values that occur (that have such objects) may be keys.

| Sheet | Method |
| --- | --- |
| arcade | `Map<Game.Platform, Game> getFirstGameByPlatforms()` |
| campus | `Map<Course.Faculty, Course> getFirstCourseByFaculties()` |
| cinema | `Map<Movie.Studio, Movie> getFirstMovieByStudios()` |
| clinic | `Map<Doctor.Department, Doctor> getFirstDoctorByDepartments()` |
| conference | `Map<Talk.Track, Talk> getFirstTalkByTracks()` |

### arcade

```java
/**
 * Returns a dictionary that maps each platform to the first corresponding game.
 * The platforms without games are not included.
 *
 * @return the result
 */
@Override
public Map<Game.Platform, Game> getFirstGameByPlatforms() {
    Map<Game.Platform, Game> result = new HashMap<>();
    for (Game t : getAll()) {
        if (!result.containsKey(t.getPlatform())) {
            result.put(t.getPlatform(), t);
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns a dictionary that maps each faculty to the first corresponding course.
 * The faculties without courses are not included.
 *
 * @return the result
 */
@Override
public Map<Course.Faculty, Course> getFirstCourseByFaculties() {
    Map<Course.Faculty, Course> result = new HashMap<>();
    for (Course t : getAll()) {
        if (!result.containsKey(t.getFaculty())) {
            result.put(t.getFaculty(), t);
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns a dictionary that maps each studio to the first corresponding movie.
 * The studios without movies are not included.
 *
 * @return the result
 */
@Override
public Map<Movie.Studio, Movie> getFirstMovieByStudios() {
    Map<Movie.Studio, Movie> result = new HashMap<>();
    for (Movie t : getAll()) {
        if (!result.containsKey(t.getStudio())) {
            result.put(t.getStudio(), t);
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns a dictionary that maps each department to the first corresponding doctor.
 * The departments without doctors are not included.
 *
 * @return the result
 */
@Override
public Map<Doctor.Department, Doctor> getFirstDoctorByDepartments() {
    Map<Doctor.Department, Doctor> result = new HashMap<>();
    for (Doctor t : getAll()) {
        if (!result.containsKey(t.getDepartment())) {
            result.put(t.getDepartment(), t);
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns a dictionary that maps each track to the first corresponding talk.
 * The tracks without talks are not included.
 *
 * @return the result
 */
@Override
public Map<Talk.Track, Talk> getFirstTalkByTracks() {
    Map<Talk.Track, Talk> result = new HashMap<>();
    for (Talk t : getAll()) {
        if (!result.containsKey(t.getTrack())) {
            result.put(t.getTrack(), t);
        }
    }
    return result;
}
```

---

<a id="D1-enum-last"></a>

## 5. enum value -> last T with that value

Template `D1-enum-last`, algorithm: last occurrence.

**The algorithm.** The *last* object of every enum value: `put()` for every object. It replaces the value of an existing key, so after the loop every key has the value of its last object.

**Typical mistakes:**

* Checking `containsKey()` before `put()`: then the *first* value would remain. For the last one, simply `put()` every time.
* Putting every enum value into the map in advance (e.g. by `V.values()`): only the values that occur (that have such objects) may be keys.

| Sheet | Method |
| --- | --- |
| arcade | `Map<Game.Platform, Game> getLastGameByPlatforms()` |
| campus | `Map<Course.Faculty, Course> getLastCourseByFaculties()` |
| cinema | `Map<Movie.Studio, Movie> getLastMovieByStudios()` |
| clinic | `Map<Doctor.Department, Doctor> getLastDoctorByDepartments()` |
| conference | `Map<Talk.Track, Talk> getLastTalkByTracks()` |

### arcade

```java
/**
 * Returns a dictionary that maps each platform to the last corresponding game.
 * The platforms without games are not included.
 *
 * @return the result
 */
@Override
public Map<Game.Platform, Game> getLastGameByPlatforms() {
    Map<Game.Platform, Game> result = new HashMap<>();
    for (Game t : getAll()) {
        result.put(t.getPlatform(), t);
    }
    return result;
}
```

### campus

```java
/**
 * Returns a dictionary that maps each faculty to the last corresponding course.
 * The faculties without courses are not included.
 *
 * @return the result
 */
@Override
public Map<Course.Faculty, Course> getLastCourseByFaculties() {
    Map<Course.Faculty, Course> result = new HashMap<>();
    for (Course t : getAll()) {
        result.put(t.getFaculty(), t);
    }
    return result;
}
```

### cinema

```java
/**
 * Returns a dictionary that maps each studio to the last corresponding movie.
 * The studios without movies are not included.
 *
 * @return the result
 */
@Override
public Map<Movie.Studio, Movie> getLastMovieByStudios() {
    Map<Movie.Studio, Movie> result = new HashMap<>();
    for (Movie t : getAll()) {
        result.put(t.getStudio(), t);
    }
    return result;
}
```

### clinic

```java
/**
 * Returns a dictionary that maps each department to the last corresponding doctor.
 * The departments without doctors are not included.
 *
 * @return the result
 */
@Override
public Map<Doctor.Department, Doctor> getLastDoctorByDepartments() {
    Map<Doctor.Department, Doctor> result = new HashMap<>();
    for (Doctor t : getAll()) {
        result.put(t.getDepartment(), t);
    }
    return result;
}
```

### conference

```java
/**
 * Returns a dictionary that maps each track to the last corresponding talk.
 * The tracks without talks are not included.
 *
 * @return the result
 */
@Override
public Map<Talk.Track, Talk> getLastTalkByTracks() {
    Map<Talk.Track, Talk> result = new HashMap<>();
    for (Talk t : getAll()) {
        result.put(t.getTrack(), t);
    }
    return result;
}
```

---

<a id="D1-enum-first-field"></a>

## 6. enum value -> field value of the first T with that value

Template `D1-enum-first-field`, algorithm: first occurrence.

**The algorithm.** A field of the *first* object of every enum value: put only for a new key (`!containsKey(key)`).

**Typical mistakes:**

* Putting for every object: `put()` replaces the value, so the *last* object would remain. Put only if `!result.containsKey(key)`.
* Storing the object instead of its field: the value type is the field's type.

| Sheet | Method |
| --- | --- |
| arcade | `Map<Game.Platform, Integer> getReleasedOfFirstGameByPlatforms()` |
| campus | `Map<Course.Faculty, String> getCodeOfFirstCourseByFaculties()` |
| cinema | `Map<Movie.Studio, String> getGenreOfFirstMovieByStudios()` |
| clinic | `Map<Doctor.Department, Integer> getExperienceOfFirstDoctorByDepartments()` |
| conference | `Map<Talk.Track, Double> getRatingOfFirstTalkByTracks()` |

### arcade

```java
/**
 * Returns a dictionary that maps each platform to the release year of the first corresponding game.
 * The platforms without games are not included.
 *
 * @return the result
 */
@Override
public Map<Game.Platform, Integer> getReleasedOfFirstGameByPlatforms() {
    Map<Game.Platform, Integer> result = new HashMap<>();
    for (Game t : getAll()) {
        if (!result.containsKey(t.getPlatform())) {
            result.put(t.getPlatform(), t.getReleased());
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns a dictionary that maps each faculty to the course code of the first corresponding course.
 * The faculties without courses are not included.
 *
 * @return the result
 */
@Override
public Map<Course.Faculty, String> getCodeOfFirstCourseByFaculties() {
    Map<Course.Faculty, String> result = new HashMap<>();
    for (Course t : getAll()) {
        if (!result.containsKey(t.getFaculty())) {
            result.put(t.getFaculty(), t.getCode());
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns a dictionary that maps each studio to the genre of the first corresponding movie.
 * The studios without movies are not included.
 *
 * @return the result
 */
@Override
public Map<Movie.Studio, String> getGenreOfFirstMovieByStudios() {
    Map<Movie.Studio, String> result = new HashMap<>();
    for (Movie t : getAll()) {
        if (!result.containsKey(t.getStudio())) {
            result.put(t.getStudio(), t.getGenre());
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns a dictionary that maps each department to the years of experience of the first corresponding doctor.
 * The departments without doctors are not included.
 *
 * @return the result
 */
@Override
public Map<Doctor.Department, Integer> getExperienceOfFirstDoctorByDepartments() {
    Map<Doctor.Department, Integer> result = new HashMap<>();
    for (Doctor t : getAll()) {
        if (!result.containsKey(t.getDepartment())) {
            result.put(t.getDepartment(), t.getExperience());
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns a dictionary that maps each track to the average rating of the first corresponding talk.
 * The tracks without talks are not included.
 *
 * @return the result
 */
@Override
public Map<Talk.Track, Double> getRatingOfFirstTalkByTracks() {
    Map<Talk.Track, Double> result = new HashMap<>();
    for (Talk t : getAll()) {
        if (!result.containsKey(t.getTrack())) {
            result.put(t.getTrack(), t.getRating());
        }
    }
    return result;
}
```

---

<a id="D1-enum-last-field"></a>

## 7. enum value -> field value of the last T with that value

Template `D1-enum-last-field`, algorithm: last occurrence.

**The algorithm.** A field of the *last* object of every enum value: `put()` for every object, the last one remains.

**Typical mistakes:**

* Checking `containsKey()` before `put()`: then the *first* value would remain. For the last one, simply `put()` every time.
* Storing the object instead of its field.

| Sheet | Method |
| --- | --- |
| arcade | `Map<Game.Platform, String> getNameOfLastGameByPlatforms()` |
| campus | `Map<Course.Faculty, String> getTitleOfLastCourseByFaculties()` |
| cinema | `Map<Movie.Studio, Integer> getYearOfLastMovieByStudios()` |
| clinic | `Map<Doctor.Department, String> getNameOfLastDoctorByDepartments()` |
| conference | `Map<Talk.Track, Double> getRatingOfLastTalkByTracks()` |

### arcade

```java
/**
 * Returns a dictionary that maps each platform to the name of the last corresponding game.
 * The platforms without games are not included.
 *
 * @return the result
 */
@Override
public Map<Game.Platform, String> getNameOfLastGameByPlatforms() {
    Map<Game.Platform, String> result = new HashMap<>();
    for (Game t : getAll()) {
        result.put(t.getPlatform(), t.getName());
    }
    return result;
}
```

### campus

```java
/**
 * Returns a dictionary that maps each faculty to the title of the last corresponding course.
 * The faculties without courses are not included.
 *
 * @return the result
 */
@Override
public Map<Course.Faculty, String> getTitleOfLastCourseByFaculties() {
    Map<Course.Faculty, String> result = new HashMap<>();
    for (Course t : getAll()) {
        result.put(t.getFaculty(), t.getTitle());
    }
    return result;
}
```

### cinema

```java
/**
 * Returns a dictionary that maps each studio to the release year of the last corresponding movie.
 * The studios without movies are not included.
 *
 * @return the result
 */
@Override
public Map<Movie.Studio, Integer> getYearOfLastMovieByStudios() {
    Map<Movie.Studio, Integer> result = new HashMap<>();
    for (Movie t : getAll()) {
        result.put(t.getStudio(), t.getYear());
    }
    return result;
}
```

### clinic

```java
/**
 * Returns a dictionary that maps each department to the name of the last corresponding doctor.
 * The departments without doctors are not included.
 *
 * @return the result
 */
@Override
public Map<Doctor.Department, String> getNameOfLastDoctorByDepartments() {
    Map<Doctor.Department, String> result = new HashMap<>();
    for (Doctor t : getAll()) {
        result.put(t.getDepartment(), t.getName());
    }
    return result;
}
```

### conference

```java
/**
 * Returns a dictionary that maps each track to the average rating of the last corresponding talk.
 * The tracks without talks are not included.
 *
 * @return the result
 */
@Override
public Map<Talk.Track, Double> getRatingOfLastTalkByTracks() {
    Map<Talk.Track, Double> result = new HashMap<>();
    for (Talk t : getAll()) {
        result.put(t.getTrack(), t.getRating());
    }
    return result;
}
```

---

<a id="D1-field-first"></a>

## 8. value of a field of T -> first T with that value

Template `D1-field-first`, algorithm: first occurrence.

**The algorithm.** The *first* object of every value of a field: the key is the field, put only if the key is new.

**Typical mistakes:**

* Putting for every object: `put()` replaces the value, so the *last* object would remain. Put only if `!result.containsKey(key)`.
* Writing a primitive key type (`Map<int, ...>`): use the wrapper class.

| Sheet | Method |
| --- | --- |
| arcade | `Map<Integer, Game> getFirstGameByReleased()` |
| campus | `Map<Integer, Course> getFirstCourseBySemester()` |
| cinema | `Map<String, Movie> getFirstMovieByGenre()` |
| clinic | `Map<String, Doctor> getFirstDoctorByRoom()` |
| conference | `Map<Integer, Talk> getFirstTalkByDuration()` |

### arcade

```java
/**
 * Returns a dictionary that maps each release year to the first game having that release year.
 *
 * @return the result
 */
@Override
public Map<Integer, Game> getFirstGameByReleased() {
    Map<Integer, Game> result = new HashMap<>();
    for (Game t : getAll()) {
        if (!result.containsKey(t.getReleased())) {
            result.put(t.getReleased(), t);
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns a dictionary that maps each recommended semester to the first course having that recommended semester.
 *
 * @return the result
 */
@Override
public Map<Integer, Course> getFirstCourseBySemester() {
    Map<Integer, Course> result = new HashMap<>();
    for (Course t : getAll()) {
        if (!result.containsKey(t.getSemester())) {
            result.put(t.getSemester(), t);
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns a dictionary that maps each genre to the first movie having that genre.
 *
 * @return the result
 */
@Override
public Map<String, Movie> getFirstMovieByGenre() {
    Map<String, Movie> result = new HashMap<>();
    for (Movie t : getAll()) {
        if (!result.containsKey(t.getGenre())) {
            result.put(t.getGenre(), t);
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns a dictionary that maps each room number to the first doctor having that room number.
 *
 * @return the result
 */
@Override
public Map<String, Doctor> getFirstDoctorByRoom() {
    Map<String, Doctor> result = new HashMap<>();
    for (Doctor t : getAll()) {
        if (!result.containsKey(t.getRoom())) {
            result.put(t.getRoom(), t);
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns a dictionary that maps each duration in minutes to the first talk having that duration in minutes.
 *
 * @return the result
 */
@Override
public Map<Integer, Talk> getFirstTalkByDuration() {
    Map<Integer, Talk> result = new HashMap<>();
    for (Talk t : getAll()) {
        if (!result.containsKey(t.getDuration())) {
            result.put(t.getDuration(), t);
        }
    }
    return result;
}
```

---

<a id="D1-field-last-enum"></a>

## 9. value of a field of T -> enum value of the last T with that value

Template `D1-field-last-enum`, algorithm: last occurrence.

**The algorithm.** The enum value of the *last* object of every value of a field: `put()` for every object, the later object replaces the value.

**Typical mistakes:**

* Checking `containsKey()` before `put()`: then the *first* value would remain. For the last one, simply `put()` every time.
* Swapping the key and the value: the key is the field, the value is the enum.

| Sheet | Method |
| --- | --- |
| arcade | `Map<Integer, Game.Platform> getPlatformOfLastGameByReleased()` |
| campus | `Map<String, Course.Faculty> getFacultyOfLastCourseByTitle()` |
| cinema | `Map<String, Movie.Studio> getStudioOfLastMovieByGenre()` |
| clinic | `Map<Integer, Doctor.Department> getDepartmentOfLastDoctorByExperience()` |
| conference | `Map<String, Talk.Track> getTrackOfLastTalkBySpeaker()` |

### arcade

```java
/**
 * Returns a dictionary that maps each release year to the platform of the last game having that release year.
 *
 * @return the result
 */
@Override
public Map<Integer, Game.Platform> getPlatformOfLastGameByReleased() {
    Map<Integer, Game.Platform> result = new HashMap<>();
    for (Game t : getAll()) {
        result.put(t.getReleased(), t.getPlatform());
    }
    return result;
}
```

### campus

```java
/**
 * Returns a dictionary that maps each title to the faculty of the last course having that title.
 *
 * @return the result
 */
@Override
public Map<String, Course.Faculty> getFacultyOfLastCourseByTitle() {
    Map<String, Course.Faculty> result = new HashMap<>();
    for (Course t : getAll()) {
        result.put(t.getTitle(), t.getFaculty());
    }
    return result;
}
```

### cinema

```java
/**
 * Returns a dictionary that maps each genre to the studio of the last movie having that genre.
 *
 * @return the result
 */
@Override
public Map<String, Movie.Studio> getStudioOfLastMovieByGenre() {
    Map<String, Movie.Studio> result = new HashMap<>();
    for (Movie t : getAll()) {
        result.put(t.getGenre(), t.getStudio());
    }
    return result;
}
```

### clinic

```java
/**
 * Returns a dictionary that maps each years of experience to the department of the last doctor having that years of experience.
 *
 * @return the result
 */
@Override
public Map<Integer, Doctor.Department> getDepartmentOfLastDoctorByExperience() {
    Map<Integer, Doctor.Department> result = new HashMap<>();
    for (Doctor t : getAll()) {
        result.put(t.getExperience(), t.getDepartment());
    }
    return result;
}
```

### conference

```java
/**
 * Returns a dictionary that maps each speaker to the track of the last talk having that speaker.
 *
 * @return the result
 */
@Override
public Map<String, Talk.Track> getTrackOfLastTalkBySpeaker() {
    Map<String, Talk.Track> result = new HashMap<>();
    for (Talk t : getAll()) {
        result.put(t.getSpeaker(), t.getTrack());
    }
    return result;
}
```

---

<a id="D1-enum-sum"></a>

## 10. enum value -> sum of a numeric field of T

Template `D1-enum-sum`, algorithm: grouping + sum.

**The algorithm.** A sum per enum value: for every object, the current sum of its enum value is `getOrDefault(key, zero)` (with the zero of the field's type: `0`, `0L` or `0.0`), and `put()` stores it increased by the object's field.

**Typical mistakes:**

* `result.put(key, result.get(key) + value)` for a new key: `get()` returns `null`, and adding to it throws a `NullPointerException`. Use `getOrDefault(key, 0)`.
* `getOrDefault(key, 0)` for a `long` or `double` field: the default must have the type of the values (`0L`, `0.0`), otherwise it does not compile.
* Putting every enum value into the map in advance (e.g. by `V.values()`): only the values that occur (that have such objects) may be keys.

| Sheet | Method |
| --- | --- |
| arcade | `Map<Game.Platform, Integer> getSumOfReleasedByPlatforms()` |
| campus | `Map<Course.Faculty, Integer> getSumOfSemesterByFaculties()` |
| cinema | `Map<Movie.Studio, Integer> getSumOfYearByStudios()` |
| clinic | `Map<Doctor.Department, Integer> getSumOfExperienceByDepartments()` |
| conference | `Map<Talk.Track, Double> getSumOfRatingByTracks()` |

### arcade

```java
/**
 * Returns a dictionary that maps each platform to the sum of the release year of the corresponding games.
 * The platforms without games are not included.
 *
 * @return the result
 */
@Override
public Map<Game.Platform, Integer> getSumOfReleasedByPlatforms() {
    Map<Game.Platform, Integer> result = new HashMap<>();
    for (Game t : getAll()) {
        result.put(t.getPlatform(), result.getOrDefault(t.getPlatform(), 0) + t.getReleased());
    }
    return result;
}
```

### campus

```java
/**
 * Returns a dictionary that maps each faculty to the sum of the recommended semester of the corresponding courses.
 * The faculties without courses are not included.
 *
 * @return the result
 */
@Override
public Map<Course.Faculty, Integer> getSumOfSemesterByFaculties() {
    Map<Course.Faculty, Integer> result = new HashMap<>();
    for (Course t : getAll()) {
        result.put(t.getFaculty(), result.getOrDefault(t.getFaculty(), 0) + t.getSemester());
    }
    return result;
}
```

### cinema

```java
/**
 * Returns a dictionary that maps each studio to the sum of the release year of the corresponding movies.
 * The studios without movies are not included.
 *
 * @return the result
 */
@Override
public Map<Movie.Studio, Integer> getSumOfYearByStudios() {
    Map<Movie.Studio, Integer> result = new HashMap<>();
    for (Movie t : getAll()) {
        result.put(t.getStudio(), result.getOrDefault(t.getStudio(), 0) + t.getYear());
    }
    return result;
}
```

### clinic

```java
/**
 * Returns a dictionary that maps each department to the sum of the years of experience of the corresponding doctors.
 * The departments without doctors are not included.
 *
 * @return the result
 */
@Override
public Map<Doctor.Department, Integer> getSumOfExperienceByDepartments() {
    Map<Doctor.Department, Integer> result = new HashMap<>();
    for (Doctor t : getAll()) {
        result.put(t.getDepartment(), result.getOrDefault(t.getDepartment(), 0) + t.getExperience());
    }
    return result;
}
```

### conference

```java
/**
 * Returns a dictionary that maps each track to the sum of the average rating of the corresponding talks.
 * The tracks without talks are not included.
 *
 * @return the result
 */
@Override
public Map<Talk.Track, Double> getSumOfRatingByTracks() {
    Map<Talk.Track, Double> result = new HashMap<>();
    for (Talk t : getAll()) {
        result.put(t.getTrack(), result.getOrDefault(t.getTrack(), 0.0) + t.getRating());
    }
    return result;
}
```
