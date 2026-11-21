[↑ Tutorials](README.md)

# `AggregationMapQueries`

The interface of the queries that return a dictionary with single values. Every sample sheet is shown: *arcade*, *campus*, *cinema*, *clinic*, *conference*.

The result is a `Map` from a key to a single value computed from several objects: a count, a sum, a maximum or an average per key. A condition selects the objects that take part; an average needs a sum and a count per key.

| | Task | Algorithm |
| --- | --- | --- |
| 1 | [enum value -> number of T with at least the given number of U](#D2-enum-count-by-count) | grouping + count (conditional) |
| 2 | [value of a field of the T with the given enum value -> number of T](#D2-field-count-by-enum) | grouping + count (conditional) |
| 3 | [enum value -> average of a numeric field of T](#D2-enum-average) | grouping + average |
| 4 | [enum value -> maximum of a numeric field of T](#D2-enum-max) | grouping + maximum |
| 5 | [enum value -> sum of a numeric field of U](#D2-nested-field-sum) | grouping + sum |

---

<a id="D2-enum-count-by-count"></a>

## 1. enum value -> number of T with at least the given number of U

Template `D2-enum-count-by-count`, algorithm: grouping + count (conditional).

**The algorithm.** Counting per enum value, only the objects meeting the condition: the condition decides first, and only then is the count of the object's enum value increased. An enum value without such objects does not become a key.

**Typical mistakes:**

* Putting the key even for the objects that do not meet the condition (with `0`): those values must be missing.
* `result.put(key, result.get(key) + 1)` for a new key: `get()` returns `null`, and adding to it throws a `NullPointerException`. Use `getOrDefault(key, 0)`.
* Using `>`: *at least* includes the number itself.

| Sheet | Method |
| --- | --- |
| arcade | `Map<Game.Platform, Integer> getCountOfGamesByPlatformsFilterByCountOfCharacters(int count)` |
| campus | `Map<Course.Faculty, Integer> getCountOfCoursesByFacultiesFilterByCountOfStudents(int count)` |
| cinema | `Map<Movie.Studio, Integer> getCountOfMoviesByStudiosFilterByCountOfActors(int count)` |
| clinic | `Map<Doctor.Department, Integer> getCountOfDoctorsByDepartmentsFilterByCountOfPatients(int count)` |
| conference | `Map<Talk.Track, Integer> getCountOfTalksByTracksFilterByCountOfAttendees(int count)` |

### arcade

```java
/**
 * Returns a dictionary that maps each platform to the number of corresponding games that have at least
 * the given number of characters. The platforms without such games are not included.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public Map<Game.Platform, Integer> getCountOfGamesByPlatformsFilterByCountOfCharacters(int count) {
    Map<Game.Platform, Integer> result = new HashMap<>();
    for (Game t : getAll()) {
        if (t.getCharacters().length >= count) {
            result.put(t.getPlatform(), result.getOrDefault(t.getPlatform(), 0) + 1);
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns a dictionary that maps each faculty to the number of corresponding courses that have at least
 * the given number of students. The faculties without such courses are not included.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public Map<Course.Faculty, Integer> getCountOfCoursesByFacultiesFilterByCountOfStudents(int count) {
    Map<Course.Faculty, Integer> result = new HashMap<>();
    for (Course t : getAll()) {
        if (t.getStudents().length >= count) {
            result.put(t.getFaculty(), result.getOrDefault(t.getFaculty(), 0) + 1);
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns a dictionary that maps each studio to the number of corresponding movies that have at least
 * the given number of actors. The studios without such movies are not included.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public Map<Movie.Studio, Integer> getCountOfMoviesByStudiosFilterByCountOfActors(int count) {
    Map<Movie.Studio, Integer> result = new HashMap<>();
    for (Movie t : getAll()) {
        if (t.getActors().length >= count) {
            result.put(t.getStudio(), result.getOrDefault(t.getStudio(), 0) + 1);
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns a dictionary that maps each department to the number of corresponding doctors that have at least
 * the given number of patients. The departments without such doctors are not included.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public Map<Doctor.Department, Integer> getCountOfDoctorsByDepartmentsFilterByCountOfPatients(int count) {
    Map<Doctor.Department, Integer> result = new HashMap<>();
    for (Doctor t : getAll()) {
        if (t.getPatients().size() >= count) {
            result.put(t.getDepartment(), result.getOrDefault(t.getDepartment(), 0) + 1);
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns a dictionary that maps each track to the number of corresponding talks that have at least
 * the given number of attendees. The tracks without such talks are not included.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public Map<Talk.Track, Integer> getCountOfTalksByTracksFilterByCountOfAttendees(int count) {
    Map<Talk.Track, Integer> result = new HashMap<>();
    for (Talk t : getAll()) {
        if (t.getAttendees().size() >= count) {
            result.put(t.getTrack(), result.getOrDefault(t.getTrack(), 0) + 1);
        }
    }
    return result;
}
```

---

<a id="D2-field-count-by-enum"></a>

## 2. value of a field of the T with the given enum value -> number of T

Template `D2-field-count-by-enum`, algorithm: grouping + count (conditional).

**The algorithm.** Counting per value of a field, among the objects with the given enum value: select by the enum value, then count by `getOrDefault()` and `put()`.

**Typical mistakes:**

* `result.put(key, result.get(key) + 1)` for a new key: `get()` returns `null`, and adding to it throws a `NullPointerException`. Use `getOrDefault(key, 0)`.
* Comparing the enum field with the JSON text of the value (e.g. `"Neo Geo"`) or with `toString()`: the field holds an enum constant; compare it with the parameter by `==`.

| Sheet | Method |
| --- | --- |
| arcade | `Map<Integer, Integer> getCountOfGamesByReleasedValuesFilterByPlatform(Game.Platform platform)` |
| campus | `Map<Integer, Integer> getCountOfCoursesByCreditsValuesFilterByFaculty(Course.Faculty faculty)` |
| cinema | `Map<String, Integer> getCountOfMoviesByGenreValuesFilterByStudio(Movie.Studio studio)` |
| clinic | `Map<Integer, Integer> getCountOfDoctorsByExperienceValuesFilterByDepartment(Doctor.Department department)` |
| conference | `Map<String, Integer> getCountOfTalksBySpeakerValuesFilterByTrack(Talk.Track track)` |

### arcade

```java
/**
 * Returns a dictionary that maps each release year to the number of corresponding games that have the given platform.
 *
 * @param platform the platform
 * @return the result
 */
@Override
public Map<Integer, Integer> getCountOfGamesByReleasedValuesFilterByPlatform(@NonNull Game.Platform platform) {
    Map<Integer, Integer> result = new HashMap<>();
    for (Game t : getAll()) {
        if (t.getPlatform() == platform) {
            result.put(t.getReleased(), result.getOrDefault(t.getReleased(), 0) + 1);
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns a dictionary that maps each credits to the number of corresponding courses that have the given faculty.
 *
 * @param faculty the faculty
 * @return the result
 */
@Override
public Map<Integer, Integer> getCountOfCoursesByCreditsValuesFilterByFaculty(@NonNull Course.Faculty faculty) {
    Map<Integer, Integer> result = new HashMap<>();
    for (Course t : getAll()) {
        if (t.getFaculty() == faculty) {
            result.put(t.getCredits(), result.getOrDefault(t.getCredits(), 0) + 1);
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns a dictionary that maps each genre to the number of corresponding movies that have the given studio.
 *
 * @param studio the studio
 * @return the result
 */
@Override
public Map<String, Integer> getCountOfMoviesByGenreValuesFilterByStudio(@NonNull Movie.Studio studio) {
    Map<String, Integer> result = new HashMap<>();
    for (Movie t : getAll()) {
        if (t.getStudio() == studio) {
            result.put(t.getGenre(), result.getOrDefault(t.getGenre(), 0) + 1);
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns a dictionary that maps each years of experience to the number of corresponding doctors that have the given department.
 *
 * @param department the department
 * @return the result
 */
@Override
public Map<Integer, Integer> getCountOfDoctorsByExperienceValuesFilterByDepartment(@NonNull Doctor.Department department) {
    Map<Integer, Integer> result = new HashMap<>();
    for (Doctor t : getAll()) {
        if (t.getDepartment() == department) {
            result.put(t.getExperience(), result.getOrDefault(t.getExperience(), 0) + 1);
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns a dictionary that maps each speaker to the number of corresponding talks that have the given track.
 *
 * @param track the track
 * @return the result
 */
@Override
public Map<String, Integer> getCountOfTalksBySpeakerValuesFilterByTrack(@NonNull Talk.Track track) {
    Map<String, Integer> result = new HashMap<>();
    for (Talk t : getAll()) {
        if (t.getTrack() == track) {
            result.put(t.getSpeaker(), result.getOrDefault(t.getSpeaker(), 0) + 1);
        }
    }
    return result;
}
```

---

<a id="D2-enum-average"></a>

## 3. enum value -> average of a numeric field of T

Template `D2-enum-average`, algorithm: grouping + average.

**The algorithm.** An average per enum value needs a sum and a count per key. (1) Two maps, `sums` and `counts`, filled in the same loop with `getOrDefault()` and `put()`. (2) A second loop over the keys of `sums` puts `sum / count` into the result. Every key has a count of at least 1, so there is no division by zero.

**Typical mistakes:**

* Averaging the averages, or dividing by the number of all the objects: each key has its own count.
* Integer division: keep the sums as `Double` (or cast) before dividing.
* `result.put(key, result.get(key) + 1)` for a new key: `get()` returns `null`, and adding to it throws a `NullPointerException`. Use `getOrDefault(key, 0)`.

| Sheet | Method |
| --- | --- |
| arcade | `Map<Game.Platform, Double> getAverageOfReleasedByPlatforms()` |
| campus | `Map<Course.Faculty, Double> getAverageOfCreditsByFaculties()` |
| cinema | `Map<Movie.Studio, Double> getAverageOfYearByStudios()` |
| clinic | `Map<Doctor.Department, Double> getAverageOfExperienceByDepartments()` |
| conference | `Map<Talk.Track, Double> getAverageOfDurationByTracks()` |

### arcade

```java
/**
 * Returns a dictionary that maps each platform to the average release year of the corresponding games.
 *
 * @return the result
 */
@Override
public Map<Game.Platform, Double> getAverageOfReleasedByPlatforms() {
    Map<Game.Platform, Double> sums = new HashMap<>();
    Map<Game.Platform, Integer> counts = new HashMap<>();
    for (Game t : getAll()) {
        sums.put(t.getPlatform(), sums.getOrDefault(t.getPlatform(), 0.0) + t.getReleased());
        counts.put(t.getPlatform(), counts.getOrDefault(t.getPlatform(), 0) + 1);
    }
    Map<Game.Platform, Double> result = new HashMap<>();
    for (Game.Platform key : sums.keySet()) {
        result.put(key, sums.get(key) / counts.get(key));
    }
    return result;
}
```

### campus

```java
/**
 * Returns a dictionary that maps each faculty to the average credits of the corresponding courses.
 *
 * @return the result
 */
@Override
public Map<Course.Faculty, Double> getAverageOfCreditsByFaculties() {
    Map<Course.Faculty, Double> sums = new HashMap<>();
    Map<Course.Faculty, Integer> counts = new HashMap<>();
    for (Course t : getAll()) {
        sums.put(t.getFaculty(), sums.getOrDefault(t.getFaculty(), 0.0) + t.getCredits());
        counts.put(t.getFaculty(), counts.getOrDefault(t.getFaculty(), 0) + 1);
    }
    Map<Course.Faculty, Double> result = new HashMap<>();
    for (Course.Faculty key : sums.keySet()) {
        result.put(key, sums.get(key) / counts.get(key));
    }
    return result;
}
```

### cinema

```java
/**
 * Returns a dictionary that maps each studio to the average release year of the corresponding movies.
 *
 * @return the result
 */
@Override
public Map<Movie.Studio, Double> getAverageOfYearByStudios() {
    Map<Movie.Studio, Double> sums = new HashMap<>();
    Map<Movie.Studio, Integer> counts = new HashMap<>();
    for (Movie t : getAll()) {
        sums.put(t.getStudio(), sums.getOrDefault(t.getStudio(), 0.0) + t.getYear());
        counts.put(t.getStudio(), counts.getOrDefault(t.getStudio(), 0) + 1);
    }
    Map<Movie.Studio, Double> result = new HashMap<>();
    for (Movie.Studio key : sums.keySet()) {
        result.put(key, sums.get(key) / counts.get(key));
    }
    return result;
}
```

### clinic

```java
/**
 * Returns a dictionary that maps each department to the average years of experience of the corresponding doctors.
 *
 * @return the result
 */
@Override
public Map<Doctor.Department, Double> getAverageOfExperienceByDepartments() {
    Map<Doctor.Department, Double> sums = new HashMap<>();
    Map<Doctor.Department, Integer> counts = new HashMap<>();
    for (Doctor t : getAll()) {
        sums.put(t.getDepartment(), sums.getOrDefault(t.getDepartment(), 0.0) + t.getExperience());
        counts.put(t.getDepartment(), counts.getOrDefault(t.getDepartment(), 0) + 1);
    }
    Map<Doctor.Department, Double> result = new HashMap<>();
    for (Doctor.Department key : sums.keySet()) {
        result.put(key, sums.get(key) / counts.get(key));
    }
    return result;
}
```

### conference

```java
/**
 * Returns a dictionary that maps each track to the average duration in minutes of the corresponding talks.
 *
 * @return the result
 */
@Override
public Map<Talk.Track, Double> getAverageOfDurationByTracks() {
    Map<Talk.Track, Double> sums = new HashMap<>();
    Map<Talk.Track, Integer> counts = new HashMap<>();
    for (Talk t : getAll()) {
        sums.put(t.getTrack(), sums.getOrDefault(t.getTrack(), 0.0) + t.getDuration());
        counts.put(t.getTrack(), counts.getOrDefault(t.getTrack(), 0) + 1);
    }
    Map<Talk.Track, Double> result = new HashMap<>();
    for (Talk.Track key : sums.keySet()) {
        result.put(key, sums.get(key) / counts.get(key));
    }
    return result;
}
```

---

<a id="D2-enum-max"></a>

## 4. enum value -> maximum of a numeric field of T

Template `D2-enum-max`, algorithm: grouping + maximum.

**The algorithm.** A maximum per enum value: if the key is new, store the object's field; otherwise replace the stored value only if the field is greater.

**Typical mistakes:**

* Comparing with `result.get(key)` for a new key: it is `null`, and unboxing it throws a `NullPointerException`; check `containsKey()` first.
* Starting every key at `0`: with negative values the maximum would be wrong, and the key exists only if it has objects.

| Sheet | Method |
| --- | --- |
| arcade | `Map<Game.Platform, Integer> getMaximumOfReleasedByPlatforms()` |
| campus | `Map<Course.Faculty, Integer> getMaximumOfCreditsByFaculties()` |
| cinema | `Map<Movie.Studio, Integer> getMaximumOfYearByStudios()` |
| clinic | `Map<Doctor.Department, Integer> getMaximumOfExperienceByDepartments()` |
| conference | `Map<Talk.Track, Integer> getMaximumOfDurationByTracks()` |

### arcade

```java
/**
 * Returns a dictionary that maps each platform to the greatest release year of the corresponding games.
 *
 * @return the result
 */
@Override
public Map<Game.Platform, Integer> getMaximumOfReleasedByPlatforms() {
    Map<Game.Platform, Integer> result = new HashMap<>();
    for (Game t : getAll()) {
        if (!result.containsKey(t.getPlatform()) || t.getReleased() > result.get(t.getPlatform())) {
            result.put(t.getPlatform(), t.getReleased());
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns a dictionary that maps each faculty to the greatest credits of the corresponding courses.
 *
 * @return the result
 */
@Override
public Map<Course.Faculty, Integer> getMaximumOfCreditsByFaculties() {
    Map<Course.Faculty, Integer> result = new HashMap<>();
    for (Course t : getAll()) {
        if (!result.containsKey(t.getFaculty()) || t.getCredits() > result.get(t.getFaculty())) {
            result.put(t.getFaculty(), t.getCredits());
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns a dictionary that maps each studio to the greatest release year of the corresponding movies.
 *
 * @return the result
 */
@Override
public Map<Movie.Studio, Integer> getMaximumOfYearByStudios() {
    Map<Movie.Studio, Integer> result = new HashMap<>();
    for (Movie t : getAll()) {
        if (!result.containsKey(t.getStudio()) || t.getYear() > result.get(t.getStudio())) {
            result.put(t.getStudio(), t.getYear());
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns a dictionary that maps each department to the greatest years of experience of the corresponding doctors.
 *
 * @return the result
 */
@Override
public Map<Doctor.Department, Integer> getMaximumOfExperienceByDepartments() {
    Map<Doctor.Department, Integer> result = new HashMap<>();
    for (Doctor t : getAll()) {
        if (!result.containsKey(t.getDepartment()) || t.getExperience() > result.get(t.getDepartment())) {
            result.put(t.getDepartment(), t.getExperience());
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns a dictionary that maps each track to the greatest duration in minutes of the corresponding talks.
 *
 * @return the result
 */
@Override
public Map<Talk.Track, Integer> getMaximumOfDurationByTracks() {
    Map<Talk.Track, Integer> result = new HashMap<>();
    for (Talk t : getAll()) {
        if (!result.containsKey(t.getTrack()) || t.getDuration() > result.get(t.getTrack())) {
            result.put(t.getTrack(), t.getDuration());
        }
    }
    return result;
}
```

---

<a id="D2-nested-field-sum"></a>

## 5. enum value -> sum of a numeric field of U

Template `D2-nested-field-sum`, algorithm: grouping + sum.

**The algorithm.** A sum per enum value over the nested objects. (1) For every top-level object, the inner loop sums the field of its nested objects. (2) This sum is added to the sum of the object's enum value (`getOrDefault(key, zero) + sum`). Every enum value that occurs is a key, with `0` if its objects have no nested objects.

**Typical mistakes:**

* Putting the sum of one object instead of adding it to the earlier sum of the key: the earlier objects of the same value would be lost.
* Declaring the inner sum outside the outer loop: it must start at zero for every top-level object.

| Sheet | Method |
| --- | --- |
| arcade | `Map<Game.Platform, Integer> getSumOfCharacterGamesByPlatforms()` |
| campus | `Map<Course.Faculty, Integer> getSumOfStudentGradeByFaculties()` |
| cinema | `Map<Movie.Studio, Integer> getSumOfActorAgeByStudios()` |
| clinic | `Map<Doctor.Department, Long> getSumOfPatientInsuranceNumberByDepartments()` |
| conference | `Map<Talk.Track, Integer> getSumOfAttendeeAgeByTracks()` |

### arcade

```java
/**
 * Returns a dictionary that maps each platform to the sum of the number of games of the characters of the corresponding games.
 *
 * @return the result
 */
@Override
public Map<Game.Platform, Integer> getSumOfCharacterGamesByPlatforms() {
    Map<Game.Platform, Integer> result = new HashMap<>();
    for (Game t : getAll()) {
        int sum = 0;
        for (Game.Character u : t.getCharacters()) {
            sum += u.getGames();
        }
        result.put(t.getPlatform(), result.getOrDefault(t.getPlatform(), 0) + sum);
    }
    return result;
}
```

### campus

```java
/**
 * Returns a dictionary that maps each faculty to the sum of the grade of the students of the corresponding courses.
 *
 * @return the result
 */
@Override
public Map<Course.Faculty, Integer> getSumOfStudentGradeByFaculties() {
    Map<Course.Faculty, Integer> result = new HashMap<>();
    for (Course t : getAll()) {
        int sum = 0;
        for (Course.Student u : t.getStudents()) {
            sum += u.getGrade();
        }
        result.put(t.getFaculty(), result.getOrDefault(t.getFaculty(), 0) + sum);
    }
    return result;
}
```

### cinema

```java
/**
 * Returns a dictionary that maps each studio to the sum of the age of the actors of the corresponding movies.
 *
 * @return the result
 */
@Override
public Map<Movie.Studio, Integer> getSumOfActorAgeByStudios() {
    Map<Movie.Studio, Integer> result = new HashMap<>();
    for (Movie t : getAll()) {
        int sum = 0;
        for (Movie.Actor u : t.getActors()) {
            sum += u.getAge();
        }
        result.put(t.getStudio(), result.getOrDefault(t.getStudio(), 0) + sum);
    }
    return result;
}
```

### clinic

```java
/**
 * Returns a dictionary that maps each department to the sum of the insurance number of the patients of the corresponding doctors.
 *
 * @return the result
 */
@Override
public Map<Doctor.Department, Long> getSumOfPatientInsuranceNumberByDepartments() {
    Map<Doctor.Department, Long> result = new HashMap<>();
    for (Doctor t : getAll()) {
        long sum = 0L;
        for (Doctor.Patient u : t.getPatients()) {
            sum += u.getInsuranceNumber();
        }
        result.put(t.getDepartment(), result.getOrDefault(t.getDepartment(), 0L) + sum);
    }
    return result;
}
```

### conference

```java
/**
 * Returns a dictionary that maps each track to the sum of the age of the attendees of the corresponding talks.
 *
 * @return the result
 */
@Override
public Map<Talk.Track, Integer> getSumOfAttendeeAgeByTracks() {
    Map<Talk.Track, Integer> result = new HashMap<>();
    for (Talk t : getAll()) {
        int sum = 0;
        for (Talk.Attendee u : t.getAttendees()) {
            sum += u.getAge();
        }
        result.put(t.getTrack(), result.getOrDefault(t.getTrack(), 0) + sum);
    }
    return result;
}
```
