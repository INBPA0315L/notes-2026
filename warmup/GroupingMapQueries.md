[↑ Tutorials](README.md)

# `GroupingMapQueries`

The interface of the queries that return a dictionary with collections as its values. Every sample sheet is shown: *arcade*, *campus*, *cinema*, *clinic*, *conference*.

The result is a `Map` whose values are collections (`Map<K, Set<V>>` or `Map<K, List<V>>`). The collection of a key is created when the key first occurs (`if (!result.containsKey(key)) result.put(key, new HashSet<>());`), and then the elements are added to `result.get(key)`.

| | Task | Algorithm |
| --- | --- | --- |
| 1 | [enum value -> set of U](#D3-enum-nested) | grouping + flattening to set |
| 2 | [enum value -> list of T with at least the given number of U](#D3-enum-tops-by-count) | grouping + filtering to list |
| 3 | [enum value -> set of field values of the T whose numeric field is greater than the given value](#D3-enum-field-values-above) | grouping + filtering + projection to set |
| 4 | [field value of the T with the given enum value -> set of field values of its U](#D3-field-nested-values-by-enum) | grouping + filtering + flattening to set |
| 5 | [field value of U -> set of enum values of the T with at least the given number of U](#D3-nested-field-enums-by-count) | grouping + filtering + flattening to set |

---

<a id="D3-enum-nested"></a>

## 1. enum value -> set of U

Template `D3-enum-nested`, algorithm: grouping + flattening to set.

**The algorithm.** Grouping into sets. (1) A new `HashMap`. (2) For every top-level object: if its enum value is not a key yet, put a new `HashSet` for it. (3) Add every nested object of the object to `result.get(key)`. A key whose objects have no nested objects has an empty set.

**Typical mistakes:**

* Adding to `result.get(key)` before the collection of the key exists: `get()` returns `null`, and `add()` on it throws a `NullPointerException`. Create the collection when the key first occurs.
* `result.put(key, new HashSet<>())` at every object, without checking `containsKey()`: the new empty collection replaces the one already filled, and the earlier elements are lost.
* Declaring a collection variable by its class (`ArrayList<...> result`, `HashMap<...> result`): the references of collections are interfaces (`List`, `Set`, `Map`), the class appears only after `new`.

| Sheet | Method |
| --- | --- |
| arcade | `Map<Game.Platform, Set<Game.Character>> getCharactersByPlatforms()` |
| campus | `Map<Course.Faculty, Set<Course.Student>> getStudentsByFaculties()` |
| cinema | `Map<Movie.Studio, Set<Movie.Actor>> getActorsByStudios()` |
| clinic | `Map<Doctor.Department, Set<Doctor.Patient>> getPatientsByDepartments()` |
| conference | `Map<Talk.Track, Set<Talk.Attendee>> getAttendeesByTracks()` |

### arcade

```java
/**
 * Returns a dictionary that maps each platform to the characters of the corresponding games.
 *
 * @return the result
 */
@Override
public Map<Game.Platform, Set<Game.Character>> getCharactersByPlatforms() {
    Map<Game.Platform, Set<Game.Character>> result = new HashMap<>();
    for (Game t : getAll()) {
        if (!result.containsKey(t.getPlatform())) {
            result.put(t.getPlatform(), new HashSet<>());
        }
        for (Game.Character u : t.getCharacters()) {
            result.get(t.getPlatform()).add(u);
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns a dictionary that maps each faculty to the students of the corresponding courses.
 *
 * @return the result
 */
@Override
public Map<Course.Faculty, Set<Course.Student>> getStudentsByFaculties() {
    Map<Course.Faculty, Set<Course.Student>> result = new HashMap<>();
    for (Course t : getAll()) {
        if (!result.containsKey(t.getFaculty())) {
            result.put(t.getFaculty(), new HashSet<>());
        }
        for (Course.Student u : t.getStudents()) {
            result.get(t.getFaculty()).add(u);
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns a dictionary that maps each studio to the actors of the corresponding movies.
 *
 * @return the result
 */
@Override
public Map<Movie.Studio, Set<Movie.Actor>> getActorsByStudios() {
    Map<Movie.Studio, Set<Movie.Actor>> result = new HashMap<>();
    for (Movie t : getAll()) {
        if (!result.containsKey(t.getStudio())) {
            result.put(t.getStudio(), new HashSet<>());
        }
        for (Movie.Actor u : t.getActors()) {
            result.get(t.getStudio()).add(u);
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns a dictionary that maps each department to the patients of the corresponding doctors.
 *
 * @return the result
 */
@Override
public Map<Doctor.Department, Set<Doctor.Patient>> getPatientsByDepartments() {
    Map<Doctor.Department, Set<Doctor.Patient>> result = new HashMap<>();
    for (Doctor t : getAll()) {
        if (!result.containsKey(t.getDepartment())) {
            result.put(t.getDepartment(), new HashSet<>());
        }
        for (Doctor.Patient u : t.getPatients()) {
            result.get(t.getDepartment()).add(u);
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns a dictionary that maps each track to the attendees of the corresponding talks.
 *
 * @return the result
 */
@Override
public Map<Talk.Track, Set<Talk.Attendee>> getAttendeesByTracks() {
    Map<Talk.Track, Set<Talk.Attendee>> result = new HashMap<>();
    for (Talk t : getAll()) {
        if (!result.containsKey(t.getTrack())) {
            result.put(t.getTrack(), new HashSet<>());
        }
        for (Talk.Attendee u : t.getAttendees()) {
            result.get(t.getTrack()).add(u);
        }
    }
    return result;
}
```

---

<a id="D3-enum-tops-by-count"></a>

## 2. enum value -> list of T with at least the given number of U

Template `D3-enum-tops-by-count`, algorithm: grouping + filtering to list.

**The algorithm.** Grouping into lists, keeping the original order: for every object meeting the condition, create the list of its enum value on the first occurrence, then add the object to it.

**Typical mistakes:**

* Adding to `result.get(key)` before the collection of the key exists: `get()` returns `null`, and `add()` on it throws a `NullPointerException`. Create the collection when the key first occurs.
* Creating the list before checking the condition: a key without matching objects would appear with an empty list.
* Using a `Set`: the result asks for lists in the original order.

| Sheet | Method |
| --- | --- |
| arcade | `Map<Game.Platform, List<Game>> getGamesByPlatformsFilterByCountOfCharacters(int count)` |
| campus | `Map<Course.Faculty, List<Course>> getCoursesByFacultiesFilterByCountOfStudents(int count)` |
| cinema | `Map<Movie.Studio, List<Movie>> getMoviesByStudiosFilterByCountOfActors(int count)` |
| clinic | `Map<Doctor.Department, List<Doctor>> getDoctorsByDepartmentsFilterByCountOfPatients(int count)` |
| conference | `Map<Talk.Track, List<Talk>> getTalksByTracksFilterByCountOfAttendees(int count)` |

### arcade

```java
/**
 * Returns a dictionary that maps each platform to the list of corresponding games that have at least
 * the given number of characters, in their original order. The platforms without such games are not included.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public Map<Game.Platform, List<Game>> getGamesByPlatformsFilterByCountOfCharacters(int count) {
    Map<Game.Platform, List<Game>> result = new HashMap<>();
    for (Game t : getAll()) {
        if (t.getCharacters().length >= count) {
            if (!result.containsKey(t.getPlatform())) {
                result.put(t.getPlatform(), new ArrayList<>());
            }
            result.get(t.getPlatform()).add(t);
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns a dictionary that maps each faculty to the list of corresponding courses that have at least
 * the given number of students, in their original order. The faculties without such courses are not included.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public Map<Course.Faculty, List<Course>> getCoursesByFacultiesFilterByCountOfStudents(int count) {
    Map<Course.Faculty, List<Course>> result = new HashMap<>();
    for (Course t : getAll()) {
        if (t.getStudents().length >= count) {
            if (!result.containsKey(t.getFaculty())) {
                result.put(t.getFaculty(), new ArrayList<>());
            }
            result.get(t.getFaculty()).add(t);
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns a dictionary that maps each studio to the list of corresponding movies that have at least
 * the given number of actors, in their original order. The studios without such movies are not included.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public Map<Movie.Studio, List<Movie>> getMoviesByStudiosFilterByCountOfActors(int count) {
    Map<Movie.Studio, List<Movie>> result = new HashMap<>();
    for (Movie t : getAll()) {
        if (t.getActors().length >= count) {
            if (!result.containsKey(t.getStudio())) {
                result.put(t.getStudio(), new ArrayList<>());
            }
            result.get(t.getStudio()).add(t);
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns a dictionary that maps each department to the list of corresponding doctors that have at least
 * the given number of patients, in their original order. The departments without such doctors are not included.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public Map<Doctor.Department, List<Doctor>> getDoctorsByDepartmentsFilterByCountOfPatients(int count) {
    Map<Doctor.Department, List<Doctor>> result = new HashMap<>();
    for (Doctor t : getAll()) {
        if (t.getPatients().size() >= count) {
            if (!result.containsKey(t.getDepartment())) {
                result.put(t.getDepartment(), new ArrayList<>());
            }
            result.get(t.getDepartment()).add(t);
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns a dictionary that maps each track to the list of corresponding talks that have at least
 * the given number of attendees, in their original order. The tracks without such talks are not included.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public Map<Talk.Track, List<Talk>> getTalksByTracksFilterByCountOfAttendees(int count) {
    Map<Talk.Track, List<Talk>> result = new HashMap<>();
    for (Talk t : getAll()) {
        if (t.getAttendees().size() >= count) {
            if (!result.containsKey(t.getTrack())) {
                result.put(t.getTrack(), new ArrayList<>());
            }
            result.get(t.getTrack()).add(t);
        }
    }
    return result;
}
```

---

<a id="D3-enum-field-values-above"></a>

## 3. enum value -> set of field values of the T whose numeric field is greater than the given value

Template `D3-enum-field-values-above`, algorithm: grouping + filtering + projection to set.

**The algorithm.** Grouping the values of a field into sets per enum value, for the objects whose other, numeric field is greater than the given value: create the set of the key on its first occurrence, then add the field.

**Typical mistakes:**

* Adding to `result.get(key)` before the collection of the key exists: `get()` returns `null`, and `add()` on it throws a `NullPointerException`. Create the collection when the key first occurs.
* `result.put(key, new HashSet<>())` at every object, without checking `containsKey()`: the new empty collection replaces the one already filled, and the earlier elements are lost.
* Using `>=`: *greater than* is strict.

| Sheet | Method |
| --- | --- |
| arcade | not applicable (the model has no such field) |
| campus | `Map<Course.Faculty, Set<Integer>> getSemesterValuesByFacultiesFilterByCredits(int credits)` |
| cinema | `Map<Movie.Studio, Set<String>> getGenreValuesByStudiosFilterByYear(int year)` |
| clinic | `Map<Doctor.Department, Set<String>> getRoomValuesByDepartmentsFilterByExperience(int experience)` |
| conference | `Map<Talk.Track, Set<String>> getSpeakerValuesByTracksFilterByRating(double rating)` |

### campus

```java
/**
 * Returns a dictionary that maps each faculty to the set of recommended semester values of the corresponding courses
 * whose credits is greater than the given value. The faculties without such courses are not included.
 *
 * @param credits the credits of the course
 * @return the result
 */
@Override
public Map<Course.Faculty, Set<Integer>> getSemesterValuesByFacultiesFilterByCredits(int credits) {
    Map<Course.Faculty, Set<Integer>> result = new HashMap<>();
    for (Course t : getAll()) {
        if (t.getCredits() > credits) {
            if (!result.containsKey(t.getFaculty())) {
                result.put(t.getFaculty(), new HashSet<>());
            }
            result.get(t.getFaculty()).add(t.getSemester());
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns a dictionary that maps each studio to the set of genre values of the corresponding movies
 * whose release year is greater than the given value. The studios without such movies are not included.
 *
 * @param year the release year of the movie
 * @return the result
 */
@Override
public Map<Movie.Studio, Set<String>> getGenreValuesByStudiosFilterByYear(int year) {
    Map<Movie.Studio, Set<String>> result = new HashMap<>();
    for (Movie t : getAll()) {
        if (t.getYear() > year) {
            if (!result.containsKey(t.getStudio())) {
                result.put(t.getStudio(), new HashSet<>());
            }
            result.get(t.getStudio()).add(t.getGenre());
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns a dictionary that maps each department to the set of room number values of the corresponding doctors
 * whose years of experience is greater than the given value. The departments without such doctors are not included.
 *
 * @param experience the years of experience of the doctor
 * @return the result
 */
@Override
public Map<Doctor.Department, Set<String>> getRoomValuesByDepartmentsFilterByExperience(int experience) {
    Map<Doctor.Department, Set<String>> result = new HashMap<>();
    for (Doctor t : getAll()) {
        if (t.getExperience() > experience) {
            if (!result.containsKey(t.getDepartment())) {
                result.put(t.getDepartment(), new HashSet<>());
            }
            result.get(t.getDepartment()).add(t.getRoom());
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns a dictionary that maps each track to the set of speaker values of the corresponding talks
 * whose average rating is greater than the given value. The tracks without such talks are not included.
 *
 * @param rating the average rating of the talk
 * @return the result
 */
@Override
public Map<Talk.Track, Set<String>> getSpeakerValuesByTracksFilterByRating(double rating) {
    Map<Talk.Track, Set<String>> result = new HashMap<>();
    for (Talk t : getAll()) {
        if (t.getRating() > rating) {
            if (!result.containsKey(t.getTrack())) {
                result.put(t.getTrack(), new HashSet<>());
            }
            result.get(t.getTrack()).add(t.getSpeaker());
        }
    }
    return result;
}
```

---

<a id="D3-field-nested-values-by-enum"></a>

## 4. field value of the T with the given enum value -> set of field values of its U

Template `D3-field-nested-values-by-enum`, algorithm: grouping + filtering + flattening to set.

**The algorithm.** The key is a field of the top-level object, the set collects a field of its nested objects. For every object with the given enum value: create the set of its key if needed, then add the field of every nested object.

**Typical mistakes:**

* Adding to `result.get(key)` before the collection of the key exists: `get()` returns `null`, and `add()` on it throws a `NullPointerException`. Create the collection when the key first occurs.
* `result.put(key, new HashSet<>())` at every object, without checking `containsKey()`: the new empty collection replaces the one already filled, and the earlier elements are lost.
* Mixing up the two fields: the key comes from the top-level object, the elements from the nested objects.

| Sheet | Method |
| --- | --- |
| arcade | `Map<Integer, Set<Integer>> getGamesValuesOfCharactersByReleasedFilterByPlatform(Game.Platform platform)` |
| campus | `Map<String, Set<Integer>> getGradeValuesOfStudentsByTitleFilterByFaculty(Course.Faculty faculty)` |
| cinema | `Map<String, Set<String>> getNationalityValuesOfActorsByGenreFilterByStudio(Movie.Studio studio)` |
| clinic | `Map<Integer, Set<String>> getNameValuesOfPatientsByExperienceFilterByDepartment(Doctor.Department department)` |
| conference | `Map<Integer, Set<Integer>> getAgeValuesOfAttendeesByDurationFilterByTrack(Talk.Track track)` |

### arcade

```java
/**
 * Returns a dictionary that maps each release year of the games having the given platform to the set of
 * number of games values of the characters of the corresponding games.
 *
 * @param platform the platform
 * @return the result
 */
@Override
public Map<Integer, Set<Integer>> getGamesValuesOfCharactersByReleasedFilterByPlatform(@NonNull Game.Platform platform) {
    Map<Integer, Set<Integer>> result = new HashMap<>();
    for (Game t : getAll()) {
        if (t.getPlatform() == platform) {
            if (!result.containsKey(t.getReleased())) {
                result.put(t.getReleased(), new HashSet<>());
            }
            for (Game.Character u : t.getCharacters()) {
                result.get(t.getReleased()).add(u.getGames());
            }
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns a dictionary that maps each title of the courses having the given faculty to the set of
 * grade values of the students of the corresponding courses.
 *
 * @param faculty the faculty
 * @return the result
 */
@Override
public Map<String, Set<Integer>> getGradeValuesOfStudentsByTitleFilterByFaculty(@NonNull Course.Faculty faculty) {
    Map<String, Set<Integer>> result = new HashMap<>();
    for (Course t : getAll()) {
        if (t.getFaculty() == faculty) {
            if (!result.containsKey(t.getTitle())) {
                result.put(t.getTitle(), new HashSet<>());
            }
            for (Course.Student u : t.getStudents()) {
                result.get(t.getTitle()).add(u.getGrade());
            }
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns a dictionary that maps each genre of the movies having the given studio to the set of
 * nationality values of the actors of the corresponding movies.
 *
 * @param studio the studio
 * @return the result
 */
@Override
public Map<String, Set<String>> getNationalityValuesOfActorsByGenreFilterByStudio(@NonNull Movie.Studio studio) {
    Map<String, Set<String>> result = new HashMap<>();
    for (Movie t : getAll()) {
        if (t.getStudio() == studio) {
            if (!result.containsKey(t.getGenre())) {
                result.put(t.getGenre(), new HashSet<>());
            }
            for (Movie.Actor u : t.getActors()) {
                result.get(t.getGenre()).add(u.getNationality());
            }
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns a dictionary that maps each years of experience of the doctors having the given department to the set of
 * name values of the patients of the corresponding doctors.
 *
 * @param department the department
 * @return the result
 */
@Override
public Map<Integer, Set<String>> getNameValuesOfPatientsByExperienceFilterByDepartment(@NonNull Doctor.Department department) {
    Map<Integer, Set<String>> result = new HashMap<>();
    for (Doctor t : getAll()) {
        if (t.getDepartment() == department) {
            if (!result.containsKey(t.getExperience())) {
                result.put(t.getExperience(), new HashSet<>());
            }
            for (Doctor.Patient u : t.getPatients()) {
                result.get(t.getExperience()).add(u.getName());
            }
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns a dictionary that maps each duration in minutes of the talks having the given track to the set of
 * age values of the attendees of the corresponding talks.
 *
 * @param track the track
 * @return the result
 */
@Override
public Map<Integer, Set<Integer>> getAgeValuesOfAttendeesByDurationFilterByTrack(@NonNull Talk.Track track) {
    Map<Integer, Set<Integer>> result = new HashMap<>();
    for (Talk t : getAll()) {
        if (t.getTrack() == track) {
            if (!result.containsKey(t.getDuration())) {
                result.put(t.getDuration(), new HashSet<>());
            }
            for (Talk.Attendee u : t.getAttendees()) {
                result.get(t.getDuration()).add(u.getAge());
            }
        }
    }
    return result;
}
```

---

<a id="D3-nested-field-enums-by-count"></a>

## 5. field value of U -> set of enum values of the T with at least the given number of U

Template `D3-nested-field-enums-by-count`, algorithm: grouping + filtering + flattening to set.

**The algorithm.** The key comes from the nested object, the value from the top-level object. For every top-level object meeting the condition, loop over its nested objects: create the set of the nested object's field if needed, and add the enum value of the **outer** object `t` to it.

**Typical mistakes:**

* Adding to `result.get(key)` before the collection of the key exists: `get()` returns `null`, and `add()` on it throws a `NullPointerException`. Create the collection when the key first occurs.
* Adding the nested object instead of the enum value of the top-level object.
* Checking the condition on the nested object: the size condition is about the top-level object.

| Sheet | Method |
| --- | --- |
| arcade | `Map<Integer, Set<Game.Platform>> getPlatformsByCharacterGamesFilterByCountOfCharacters(int count)` |
| campus | `Map<Integer, Set<Course.Faculty>> getFacultiesByStudentGradeFilterByCountOfStudents(int count)` |
| cinema | `Map<String, Set<Movie.Studio>> getStudiosByActorNationalityFilterByCountOfActors(int count)` |
| clinic | `Map<String, Set<Doctor.Department>> getDepartmentsByPatientNameFilterByCountOfPatients(int count)` |
| conference | `Map<Integer, Set<Talk.Track>> getTracksByAttendeeAgeFilterByCountOfAttendees(int count)` |

### arcade

```java
/**
 * Returns a dictionary that maps each number of games of the characters to the set of platforms of the games
 * that have such character and at least the given number of characters.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public Map<Integer, Set<Game.Platform>> getPlatformsByCharacterGamesFilterByCountOfCharacters(int count) {
    Map<Integer, Set<Game.Platform>> result = new HashMap<>();
    for (Game t : getAll()) {
        if (t.getCharacters().length >= count) {
            for (Game.Character u : t.getCharacters()) {
                if (!result.containsKey(u.getGames())) {
                    result.put(u.getGames(), new HashSet<>());
                }
                result.get(u.getGames()).add(t.getPlatform());
            }
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns a dictionary that maps each grade of the students to the set of faculties of the courses
 * that have such student and at least the given number of students.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public Map<Integer, Set<Course.Faculty>> getFacultiesByStudentGradeFilterByCountOfStudents(int count) {
    Map<Integer, Set<Course.Faculty>> result = new HashMap<>();
    for (Course t : getAll()) {
        if (t.getStudents().length >= count) {
            for (Course.Student u : t.getStudents()) {
                if (!result.containsKey(u.getGrade())) {
                    result.put(u.getGrade(), new HashSet<>());
                }
                result.get(u.getGrade()).add(t.getFaculty());
            }
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns a dictionary that maps each nationality of the actors to the set of studios of the movies
 * that have such actor and at least the given number of actors.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public Map<String, Set<Movie.Studio>> getStudiosByActorNationalityFilterByCountOfActors(int count) {
    Map<String, Set<Movie.Studio>> result = new HashMap<>();
    for (Movie t : getAll()) {
        if (t.getActors().length >= count) {
            for (Movie.Actor u : t.getActors()) {
                if (!result.containsKey(u.getNationality())) {
                    result.put(u.getNationality(), new HashSet<>());
                }
                result.get(u.getNationality()).add(t.getStudio());
            }
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns a dictionary that maps each name of the patients to the set of departments of the doctors
 * that have such patient and at least the given number of patients.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public Map<String, Set<Doctor.Department>> getDepartmentsByPatientNameFilterByCountOfPatients(int count) {
    Map<String, Set<Doctor.Department>> result = new HashMap<>();
    for (Doctor t : getAll()) {
        if (t.getPatients().size() >= count) {
            for (Doctor.Patient u : t.getPatients()) {
                if (!result.containsKey(u.getName())) {
                    result.put(u.getName(), new HashSet<>());
                }
                result.get(u.getName()).add(t.getDepartment());
            }
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns a dictionary that maps each age of the attendees to the set of tracks of the talks
 * that have such attendee and at least the given number of attendees.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public Map<Integer, Set<Talk.Track>> getTracksByAttendeeAgeFilterByCountOfAttendees(int count) {
    Map<Integer, Set<Talk.Track>> result = new HashMap<>();
    for (Talk t : getAll()) {
        if (t.getAttendees().size() >= count) {
            for (Talk.Attendee u : t.getAttendees()) {
                if (!result.containsKey(u.getAge())) {
                    result.put(u.getAge(), new HashSet<>());
                }
                result.get(u.getAge()).add(t.getTrack());
            }
        }
    }
    return result;
}
```
