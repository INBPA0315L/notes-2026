[↑ Tutorials](README.md)

# `NestedMapQueries`

The interface of the queries that return a dictionary with dictionaries as its values. Every sample sheet is shown: *arcade*, *campus*, *cinema*, *clinic*, *conference*.

The result is a two-level `Map`: the value of an outer key is an inner map. The inner map is created on the first occurrence of the outer key (as the collections of grouping), then it is filled as a simple map (counting, maximum).

| | Task | Algorithm |
| --- | --- | --- |
| 1 | [enum value -> (field value of T -> number of T)](#D4-enum-field-count) | two-level grouping + count |
| 2 | [field value of T -> (enum value -> number of T with at least the given number of U)](#D4-field-enum-count-by-count) | two-level grouping + count (conditional) |
| 3 | [enum value -> (field value of U -> number of U)](#D4-enum-nested-field-count) | two-level grouping + flattening + count |
| 4 | [enum value -> (field value of T -> maximum of a numeric field of T)](#D4-enum-field-max) | two-level grouping + maximum |

---

<a id="D4-enum-field-count"></a>

## 1. enum value -> (field value of T -> number of T)

Template `D4-enum-field-count`, algorithm: two-level grouping + count.

**The algorithm.** A two-level map. (1) For every object: if its enum value is not an outer key yet, put a new inner `HashMap`. (2) Take the inner map (`result.get(key)`) and count the object's field in it with `getOrDefault()` and `put()`, as a simple counting map.

**Typical mistakes:**

* Adding to `result.get(key)` before the inner map of the key exists: `get()` returns `null`, and `put()` on it throws a `NullPointerException`. Create the inner map when the key first occurs.
* `result.put(key, result.get(key) + 1)` for a new key: `get()` returns `null`, and adding to it throws a `NullPointerException`. Use `getOrDefault(key, 0)`.
* Creating the inner map at every object: the earlier counts would be lost.

| Sheet | Method |
| --- | --- |
| arcade | `Map<Game.Platform, Map<Integer, Integer>> getCountOfGamesByPlatformsAndReleased()` |
| campus | `Map<Course.Faculty, Map<Integer, Integer>> getCountOfCoursesByFacultiesAndSemester()` |
| cinema | `Map<Movie.Studio, Map<String, Integer>> getCountOfMoviesByStudiosAndGenre()` |
| clinic | `Map<Doctor.Department, Map<Boolean, Integer>> getCountOfDoctorsByDepartmentsAndOnCall()` |
| conference | `Map<Talk.Track, Map<Double, Integer>> getCountOfTalksByTracksAndRating()` |

### arcade

```java
/**
 * Returns a dictionary that maps each platform to a dictionary that maps each release year of the corresponding
 * games to the number of games having that platform and release year.
 *
 * @return the result
 */
@Override
public Map<Game.Platform, Map<Integer, Integer>> getCountOfGamesByPlatformsAndReleased() {
    Map<Game.Platform, Map<Integer, Integer>> result = new HashMap<>();
    for (Game t : getAll()) {
        if (!result.containsKey(t.getPlatform())) {
            result.put(t.getPlatform(), new HashMap<>());
        }
        Map<Integer, Integer> inner = result.get(t.getPlatform());
        inner.put(t.getReleased(), inner.getOrDefault(t.getReleased(), 0) + 1);
    }
    return result;
}
```

### campus

```java
/**
 * Returns a dictionary that maps each faculty to a dictionary that maps each recommended semester of the corresponding
 * courses to the number of courses having that faculty and recommended semester.
 *
 * @return the result
 */
@Override
public Map<Course.Faculty, Map<Integer, Integer>> getCountOfCoursesByFacultiesAndSemester() {
    Map<Course.Faculty, Map<Integer, Integer>> result = new HashMap<>();
    for (Course t : getAll()) {
        if (!result.containsKey(t.getFaculty())) {
            result.put(t.getFaculty(), new HashMap<>());
        }
        Map<Integer, Integer> inner = result.get(t.getFaculty());
        inner.put(t.getSemester(), inner.getOrDefault(t.getSemester(), 0) + 1);
    }
    return result;
}
```

### cinema

```java
/**
 * Returns a dictionary that maps each studio to a dictionary that maps each genre of the corresponding
 * movies to the number of movies having that studio and genre.
 *
 * @return the result
 */
@Override
public Map<Movie.Studio, Map<String, Integer>> getCountOfMoviesByStudiosAndGenre() {
    Map<Movie.Studio, Map<String, Integer>> result = new HashMap<>();
    for (Movie t : getAll()) {
        if (!result.containsKey(t.getStudio())) {
            result.put(t.getStudio(), new HashMap<>());
        }
        Map<String, Integer> inner = result.get(t.getStudio());
        inner.put(t.getGenre(), inner.getOrDefault(t.getGenre(), 0) + 1);
    }
    return result;
}
```

### clinic

```java
/**
 * Returns a dictionary that maps each department to a dictionary that maps each on-call status of the corresponding
 * doctors to the number of doctors having that department and on-call status.
 *
 * @return the result
 */
@Override
public Map<Doctor.Department, Map<Boolean, Integer>> getCountOfDoctorsByDepartmentsAndOnCall() {
    Map<Doctor.Department, Map<Boolean, Integer>> result = new HashMap<>();
    for (Doctor t : getAll()) {
        if (!result.containsKey(t.getDepartment())) {
            result.put(t.getDepartment(), new HashMap<>());
        }
        Map<Boolean, Integer> inner = result.get(t.getDepartment());
        inner.put(t.isOnCall(), inner.getOrDefault(t.isOnCall(), 0) + 1);
    }
    return result;
}
```

### conference

```java
/**
 * Returns a dictionary that maps each track to a dictionary that maps each average rating of the corresponding
 * talks to the number of talks having that track and average rating.
 *
 * @return the result
 */
@Override
public Map<Talk.Track, Map<Double, Integer>> getCountOfTalksByTracksAndRating() {
    Map<Talk.Track, Map<Double, Integer>> result = new HashMap<>();
    for (Talk t : getAll()) {
        if (!result.containsKey(t.getTrack())) {
            result.put(t.getTrack(), new HashMap<>());
        }
        Map<Double, Integer> inner = result.get(t.getTrack());
        inner.put(t.getRating(), inner.getOrDefault(t.getRating(), 0) + 1);
    }
    return result;
}
```

---

<a id="D4-field-enum-count-by-count"></a>

## 2. field value of T -> (enum value -> number of T with at least the given number of U)

Template `D4-field-enum-count-by-count`, algorithm: two-level grouping + count (conditional).

**The algorithm.** A two-level counting map for the objects meeting the condition: the outer key is a field, the inner key the enum value. Create the inner map on the first occurrence of the outer key, then count in it.

**Typical mistakes:**

* Adding to `result.get(key)` before the inner map of the key exists: `get()` returns `null`, and `put()` on it throws a `NullPointerException`. Create the inner map when the key first occurs.
* `result.put(key, result.get(key) + 1)` for a new key: `get()` returns `null`, and adding to it throws a `NullPointerException`. Use `getOrDefault(key, 0)`.
* Swapping the levels: the outer key is the field, the inner key is the enum value.

| Sheet | Method |
| --- | --- |
| arcade | `Map<Integer, Map<Game.Platform, Integer>> getCountOfGamesByReleasedAndPlatformsFilterByCountOfCharacters(int count)` |
| campus | `Map<Integer, Map<Course.Faculty, Integer>> getCountOfCoursesByCreditsAndFacultiesFilterByCountOfStudents(int count)` |
| cinema | `Map<String, Map<Movie.Studio, Integer>> getCountOfMoviesByGenreAndStudiosFilterByCountOfActors(int count)` |
| clinic | `Map<String, Map<Doctor.Department, Integer>> getCountOfDoctorsByRoomAndDepartmentsFilterByCountOfPatients(int count)` |
| conference | `Map<Integer, Map<Talk.Track, Integer>> getCountOfTalksByDurationAndTracksFilterByCountOfAttendees(int count)` |

### arcade

```java
/**
 * Returns a dictionary that maps each release year to a dictionary that maps each platform to the number of
 * games having that release year and platform.
 * Only the games with at least the given number of characters are taken into account.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public Map<Integer, Map<Game.Platform, Integer>> getCountOfGamesByReleasedAndPlatformsFilterByCountOfCharacters(int count) {
    Map<Integer, Map<Game.Platform, Integer>> result = new HashMap<>();
    for (Game t : getAll()) {
        if (t.getCharacters().length >= count) {
            if (!result.containsKey(t.getReleased())) {
                result.put(t.getReleased(), new HashMap<>());
            }
            Map<Game.Platform, Integer> inner = result.get(t.getReleased());
            inner.put(t.getPlatform(), inner.getOrDefault(t.getPlatform(), 0) + 1);
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns a dictionary that maps each credits to a dictionary that maps each faculty to the number of
 * courses having that credits and faculty.
 * Only the courses with at least the given number of students are taken into account.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public Map<Integer, Map<Course.Faculty, Integer>> getCountOfCoursesByCreditsAndFacultiesFilterByCountOfStudents(int count) {
    Map<Integer, Map<Course.Faculty, Integer>> result = new HashMap<>();
    for (Course t : getAll()) {
        if (t.getStudents().length >= count) {
            if (!result.containsKey(t.getCredits())) {
                result.put(t.getCredits(), new HashMap<>());
            }
            Map<Course.Faculty, Integer> inner = result.get(t.getCredits());
            inner.put(t.getFaculty(), inner.getOrDefault(t.getFaculty(), 0) + 1);
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns a dictionary that maps each genre to a dictionary that maps each studio to the number of
 * movies having that genre and studio.
 * Only the movies with at least the given number of actors are taken into account.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public Map<String, Map<Movie.Studio, Integer>> getCountOfMoviesByGenreAndStudiosFilterByCountOfActors(int count) {
    Map<String, Map<Movie.Studio, Integer>> result = new HashMap<>();
    for (Movie t : getAll()) {
        if (t.getActors().length >= count) {
            if (!result.containsKey(t.getGenre())) {
                result.put(t.getGenre(), new HashMap<>());
            }
            Map<Movie.Studio, Integer> inner = result.get(t.getGenre());
            inner.put(t.getStudio(), inner.getOrDefault(t.getStudio(), 0) + 1);
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns a dictionary that maps each room number to a dictionary that maps each department to the number of
 * doctors having that room number and department.
 * Only the doctors with at least the given number of patients are taken into account.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public Map<String, Map<Doctor.Department, Integer>> getCountOfDoctorsByRoomAndDepartmentsFilterByCountOfPatients(int count) {
    Map<String, Map<Doctor.Department, Integer>> result = new HashMap<>();
    for (Doctor t : getAll()) {
        if (t.getPatients().size() >= count) {
            if (!result.containsKey(t.getRoom())) {
                result.put(t.getRoom(), new HashMap<>());
            }
            Map<Doctor.Department, Integer> inner = result.get(t.getRoom());
            inner.put(t.getDepartment(), inner.getOrDefault(t.getDepartment(), 0) + 1);
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns a dictionary that maps each duration in minutes to a dictionary that maps each track to the number of
 * talks having that duration in minutes and track.
 * Only the talks with at least the given number of attendees are taken into account.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public Map<Integer, Map<Talk.Track, Integer>> getCountOfTalksByDurationAndTracksFilterByCountOfAttendees(int count) {
    Map<Integer, Map<Talk.Track, Integer>> result = new HashMap<>();
    for (Talk t : getAll()) {
        if (t.getAttendees().size() >= count) {
            if (!result.containsKey(t.getDuration())) {
                result.put(t.getDuration(), new HashMap<>());
            }
            Map<Talk.Track, Integer> inner = result.get(t.getDuration());
            inner.put(t.getTrack(), inner.getOrDefault(t.getTrack(), 0) + 1);
        }
    }
    return result;
}
```

---

<a id="D4-enum-nested-field-count"></a>

## 3. enum value -> (field value of U -> number of U)

Template `D4-enum-nested-field-count`, algorithm: two-level grouping + flattening + count.

**The algorithm.** A two-level counting map: the outer key is the enum value of the top-level object, the inner map counts a field of its nested objects. The inner map is created for every enum value that occurs (it may stay empty), then the inner loop counts.

**Typical mistakes:**

* Adding to `result.get(key)` before the inner map of the key exists: `get()` returns `null`, and `put()` on it throws a `NullPointerException`. Create the inner map when the key first occurs.
* `result.put(key, result.get(key) + 1)` for a new key: `get()` returns `null`, and adding to it throws a `NullPointerException`. Use `getOrDefault(key, 0)`.
* Counting the top-level objects instead of the nested ones.

| Sheet | Method |
| --- | --- |
| arcade | `Map<Game.Platform, Map<Integer, Integer>> getCountOfCharactersByPlatformsAndGames()` |
| campus | `Map<Course.Faculty, Map<String, Integer>> getCountOfStudentsByFacultiesAndName()` |
| cinema | `Map<Movie.Studio, Map<String, Integer>> getCountOfActorsByStudiosAndNationality()` |
| clinic | `Map<Doctor.Department, Map<Integer, Integer>> getCountOfPatientsByDepartmentsAndAge()` |
| conference | `Map<Talk.Track, Map<Integer, Integer>> getCountOfAttendeesByTracksAndAge()` |

### arcade

```java
/**
 * Returns a dictionary that maps each platform to a dictionary that maps each number of games of the characters of the
 * corresponding games to the number of such characters.
 *
 * @return the result
 */
@Override
public Map<Game.Platform, Map<Integer, Integer>> getCountOfCharactersByPlatformsAndGames() {
    Map<Game.Platform, Map<Integer, Integer>> result = new HashMap<>();
    for (Game t : getAll()) {
        if (!result.containsKey(t.getPlatform())) {
            result.put(t.getPlatform(), new HashMap<>());
        }
        Map<Integer, Integer> inner = result.get(t.getPlatform());
        for (Game.Character u : t.getCharacters()) {
            inner.put(u.getGames(), inner.getOrDefault(u.getGames(), 0) + 1);
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns a dictionary that maps each faculty to a dictionary that maps each name of the students of the
 * corresponding courses to the number of such students.
 *
 * @return the result
 */
@Override
public Map<Course.Faculty, Map<String, Integer>> getCountOfStudentsByFacultiesAndName() {
    Map<Course.Faculty, Map<String, Integer>> result = new HashMap<>();
    for (Course t : getAll()) {
        if (!result.containsKey(t.getFaculty())) {
            result.put(t.getFaculty(), new HashMap<>());
        }
        Map<String, Integer> inner = result.get(t.getFaculty());
        for (Course.Student u : t.getStudents()) {
            inner.put(u.getName(), inner.getOrDefault(u.getName(), 0) + 1);
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns a dictionary that maps each studio to a dictionary that maps each nationality of the actors of the
 * corresponding movies to the number of such actors.
 *
 * @return the result
 */
@Override
public Map<Movie.Studio, Map<String, Integer>> getCountOfActorsByStudiosAndNationality() {
    Map<Movie.Studio, Map<String, Integer>> result = new HashMap<>();
    for (Movie t : getAll()) {
        if (!result.containsKey(t.getStudio())) {
            result.put(t.getStudio(), new HashMap<>());
        }
        Map<String, Integer> inner = result.get(t.getStudio());
        for (Movie.Actor u : t.getActors()) {
            inner.put(u.getNationality(), inner.getOrDefault(u.getNationality(), 0) + 1);
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns a dictionary that maps each department to a dictionary that maps each age of the patients of the
 * corresponding doctors to the number of such patients.
 *
 * @return the result
 */
@Override
public Map<Doctor.Department, Map<Integer, Integer>> getCountOfPatientsByDepartmentsAndAge() {
    Map<Doctor.Department, Map<Integer, Integer>> result = new HashMap<>();
    for (Doctor t : getAll()) {
        if (!result.containsKey(t.getDepartment())) {
            result.put(t.getDepartment(), new HashMap<>());
        }
        Map<Integer, Integer> inner = result.get(t.getDepartment());
        for (Doctor.Patient u : t.getPatients()) {
            inner.put(u.getAge(), inner.getOrDefault(u.getAge(), 0) + 1);
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns a dictionary that maps each track to a dictionary that maps each age of the attendees of the
 * corresponding talks to the number of such attendees.
 *
 * @return the result
 */
@Override
public Map<Talk.Track, Map<Integer, Integer>> getCountOfAttendeesByTracksAndAge() {
    Map<Talk.Track, Map<Integer, Integer>> result = new HashMap<>();
    for (Talk t : getAll()) {
        if (!result.containsKey(t.getTrack())) {
            result.put(t.getTrack(), new HashMap<>());
        }
        Map<Integer, Integer> inner = result.get(t.getTrack());
        for (Talk.Attendee u : t.getAttendees()) {
            inner.put(u.getAge(), inner.getOrDefault(u.getAge(), 0) + 1);
        }
    }
    return result;
}
```

---

<a id="D4-enum-field-max"></a>

## 4. enum value -> (field value of T -> maximum of a numeric field of T)

Template `D4-enum-field-max`, algorithm: two-level grouping + maximum.

**The algorithm.** A two-level map with a maximum per inner key: create the inner map of the outer key on its first occurrence; then, in the inner map, store the numeric field if the inner key is new, or replace the stored value if the field is greater.

**Typical mistakes:**

* Adding to `result.get(key)` before the inner map of the key exists: `get()` returns `null`, and `put()` on it throws a `NullPointerException`. Create the inner map when the key first occurs.
* Comparing with `inner.get(key)` for a new key: it is `null`, unboxing it throws a `NullPointerException`; check `containsKey()` first.

| Sheet | Method |
| --- | --- |
| arcade | not applicable (the model has no such field) |
| campus | `Map<Course.Faculty, Map<Integer, Integer>> getMaximumOfCreditsByFacultiesAndSemester()` |
| cinema | `Map<Movie.Studio, Map<String, Integer>> getMaximumOfYearByStudiosAndGenre()` |
| clinic | `Map<Doctor.Department, Map<Boolean, Integer>> getMaximumOfExperienceByDepartmentsAndOnCall()` |
| conference | `Map<Talk.Track, Map<String, Double>> getMaximumOfRatingByTracksAndSpeaker()` |

### campus

```java
/**
 * Returns a dictionary that maps each faculty to a dictionary that maps each recommended semester of the corresponding
 * courses to the greatest credits of the courses having that faculty and recommended semester.
 *
 * @return the result
 */
@Override
public Map<Course.Faculty, Map<Integer, Integer>> getMaximumOfCreditsByFacultiesAndSemester() {
    Map<Course.Faculty, Map<Integer, Integer>> result = new HashMap<>();
    for (Course t : getAll()) {
        if (!result.containsKey(t.getFaculty())) {
            result.put(t.getFaculty(), new HashMap<>());
        }
        Map<Integer, Integer> inner = result.get(t.getFaculty());
        if (!inner.containsKey(t.getSemester()) || t.getCredits() > inner.get(t.getSemester())) {
            inner.put(t.getSemester(), t.getCredits());
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns a dictionary that maps each studio to a dictionary that maps each genre of the corresponding
 * movies to the greatest release year of the movies having that studio and genre.
 *
 * @return the result
 */
@Override
public Map<Movie.Studio, Map<String, Integer>> getMaximumOfYearByStudiosAndGenre() {
    Map<Movie.Studio, Map<String, Integer>> result = new HashMap<>();
    for (Movie t : getAll()) {
        if (!result.containsKey(t.getStudio())) {
            result.put(t.getStudio(), new HashMap<>());
        }
        Map<String, Integer> inner = result.get(t.getStudio());
        if (!inner.containsKey(t.getGenre()) || t.getYear() > inner.get(t.getGenre())) {
            inner.put(t.getGenre(), t.getYear());
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns a dictionary that maps each department to a dictionary that maps each on-call status of the corresponding
 * doctors to the greatest years of experience of the doctors having that department and on-call status.
 *
 * @return the result
 */
@Override
public Map<Doctor.Department, Map<Boolean, Integer>> getMaximumOfExperienceByDepartmentsAndOnCall() {
    Map<Doctor.Department, Map<Boolean, Integer>> result = new HashMap<>();
    for (Doctor t : getAll()) {
        if (!result.containsKey(t.getDepartment())) {
            result.put(t.getDepartment(), new HashMap<>());
        }
        Map<Boolean, Integer> inner = result.get(t.getDepartment());
        if (!inner.containsKey(t.isOnCall()) || t.getExperience() > inner.get(t.isOnCall())) {
            inner.put(t.isOnCall(), t.getExperience());
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns a dictionary that maps each track to a dictionary that maps each speaker of the corresponding
 * talks to the greatest average rating of the talks having that track and speaker.
 *
 * @return the result
 */
@Override
public Map<Talk.Track, Map<String, Double>> getMaximumOfRatingByTracksAndSpeaker() {
    Map<Talk.Track, Map<String, Double>> result = new HashMap<>();
    for (Talk t : getAll()) {
        if (!result.containsKey(t.getTrack())) {
            result.put(t.getTrack(), new HashMap<>());
        }
        Map<String, Double> inner = result.get(t.getTrack());
        if (!inner.containsKey(t.getSpeaker()) || t.getRating() > inner.get(t.getSpeaker())) {
            inner.put(t.getSpeaker(), t.getRating());
        }
    }
    return result;
}
```
