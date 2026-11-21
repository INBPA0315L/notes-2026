[↑ Tutorials](README.md)

# `FilteringQueries`

The interface of the queries that return a filtered list. Every sample sheet is shown: *arcade*, *campus*, *cinema*, *clinic*, *conference*.

The result is a new `List` (`List<...> result = new ArrayList<>();`), filled in a loop with the objects meeting the condition, in their original order. A condition on the nested objects ("contains", "has no ...") needs a `boolean` flag set in an inner loop. A list without duplicates keeping the order: add an element only if `!result.contains(element)`.

| | Task | Algorithm |
| --- | --- | --- |
| 1 | [T with the given enum value not containing the given U](#C2-enum-not-containing) | filtering |
| 2 | [T with the given enum value having no U with the given field value](#C2-enum-without-field) | filtering |
| 3 | [T containing the given U](#C2-containing) | filtering |
| 4 | [T whose numeric field is greater than the given value](#C2-greater-than) | filtering |
| 5 | [T with at least the given number of U](#C2-at-least-count) | filtering |
| 6 | [distinct U of the T with the given enum value](#C2-nested-of-enum) | filtering + flattening + distinct |

---

<a id="C2-enum-not-containing"></a>

## 1. T with the given enum value not containing the given U

Template `C2-enum-not-containing`, algorithm: filtering.

**The algorithm.** Filtering with two conditions. (1) A new list. (2) For every top-level object with the given enum value, a `contains` flag starts as `false`, and the inner loop sets it if a nested object `equals()` the given one. (3) After the inner loop, add the object if the flag stayed `false` (it does *not* contain the nested object).

**Typical mistakes:**

* Adding the object inside the inner loop at the first non-matching nested object: *not containing* is known only after checking all of them.
* Declaring the flag before the outer loop: it must start as `false` again for every top-level object, so declare it inside the outer loop.
* Comparing the nested objects by one of their fields by hand, or with `==`: `equals()` compares them by their identifier fields, as the model defines it; `==` compares the references.

| Sheet | Method |
| --- | --- |
| arcade | `List<Game> getGamesFilterByPlatformWithoutCharacter(Game.Platform platform, Game.Character character)` |
| campus | `List<Course> getCoursesFilterByFacultyWithoutStudent(Course.Faculty faculty, Course.Student student)` |
| cinema | `List<Movie> getMoviesFilterByStudioWithoutActor(Movie.Studio studio, Movie.Actor actor)` |
| clinic | `List<Doctor> getDoctorsFilterByDepartmentWithoutPatient(Doctor.Department department, Doctor.Patient patient)` |
| conference | `List<Talk> getTalksFilterByTrackWithoutAttendee(Talk.Track track, Talk.Attendee attendee)` |

### arcade

```java
/**
 * Returns all the games that have the given platform but do not contain the given character.
 *
 * @param platform the platform
 * @param character the character
 * @return the result
 */
@Override
public List<Game> getGamesFilterByPlatformWithoutCharacter(@NonNull Game.Platform platform, @NonNull Game.Character character) {
    List<Game> result = new ArrayList<>();
    for (Game t : getAll()) {
        if (t.getPlatform() == platform) {
            boolean contains = false;
            for (Game.Character u : t.getCharacters()) {
                if (u.equals(character)) {
                    contains = true;
                }
            }
            if (!contains) {
                result.add(t);
            }
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns all the courses that have the given faculty but do not contain the given student.
 *
 * @param faculty the faculty
 * @param student the student
 * @return the result
 */
@Override
public List<Course> getCoursesFilterByFacultyWithoutStudent(@NonNull Course.Faculty faculty, @NonNull Course.Student student) {
    List<Course> result = new ArrayList<>();
    for (Course t : getAll()) {
        if (t.getFaculty() == faculty) {
            boolean contains = false;
            for (Course.Student u : t.getStudents()) {
                if (u.equals(student)) {
                    contains = true;
                }
            }
            if (!contains) {
                result.add(t);
            }
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns all the movies that have the given studio but do not contain the given actor.
 *
 * @param studio the studio
 * @param actor the actor
 * @return the result
 */
@Override
public List<Movie> getMoviesFilterByStudioWithoutActor(@NonNull Movie.Studio studio, @NonNull Movie.Actor actor) {
    List<Movie> result = new ArrayList<>();
    for (Movie t : getAll()) {
        if (t.getStudio() == studio) {
            boolean contains = false;
            for (Movie.Actor u : t.getActors()) {
                if (u.equals(actor)) {
                    contains = true;
                }
            }
            if (!contains) {
                result.add(t);
            }
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns all the doctors that have the given department but do not contain the given patient.
 *
 * @param department the department
 * @param patient the patient
 * @return the result
 */
@Override
public List<Doctor> getDoctorsFilterByDepartmentWithoutPatient(@NonNull Doctor.Department department, @NonNull Doctor.Patient patient) {
    List<Doctor> result = new ArrayList<>();
    for (Doctor t : getAll()) {
        if (t.getDepartment() == department) {
            boolean contains = false;
            for (Doctor.Patient u : t.getPatients()) {
                if (u.equals(patient)) {
                    contains = true;
                }
            }
            if (!contains) {
                result.add(t);
            }
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns all the talks that have the given track but do not contain the given attendee.
 *
 * @param track the track
 * @param attendee the attendee
 * @return the result
 */
@Override
public List<Talk> getTalksFilterByTrackWithoutAttendee(@NonNull Talk.Track track, @NonNull Talk.Attendee attendee) {
    List<Talk> result = new ArrayList<>();
    for (Talk t : getAll()) {
        if (t.getTrack() == track) {
            boolean contains = false;
            for (Talk.Attendee u : t.getAttendees()) {
                if (u.equals(attendee)) {
                    contains = true;
                }
            }
            if (!contains) {
                result.add(t);
            }
        }
    }
    return result;
}
```

---

<a id="C2-enum-without-field"></a>

## 2. T with the given enum value having no U with the given field value

Template `C2-enum-without-field`, algorithm: filtering.

**The algorithm.** Filtering: the given enum value and *no* nested object with the given value. A flag in the inner loop, and the object is added after the inner loop if the flag stayed `false`.

**Typical mistakes:**

* Adding the object inside the inner loop at the first non-matching nested object: *none matches* is known only after the loop.
* Declaring the flag before the outer loop: it must start as `false` again for every top-level object, so declare it inside the outer loop.
* Comparing strings with `==`: it compares the references, not the texts, so a matching string may be missed. Use `equals()`.

| Sheet | Method |
| --- | --- |
| arcade | `List<Game> getGamesFilterByPlatformWithoutCharacterName(Game.Platform platform, String name)` |
| campus | `List<Course> getCoursesFilterByFacultyWithoutStudentName(Course.Faculty faculty, String name)` |
| cinema | `List<Movie> getMoviesFilterByStudioWithoutActorNationality(Movie.Studio studio, String nationality)` |
| clinic | `List<Doctor> getDoctorsFilterByDepartmentWithoutPatientName(Doctor.Department department, String name)` |
| conference | `List<Talk> getTalksFilterByTrackWithoutAttendeeCompany(Talk.Track track, String company)` |

### arcade

```java
/**
 * Returns all the games that have the given platform but do not have any character with the given name.
 *
 * @param platform the platform
 * @param name the name of the character
 * @return the result
 */
@Override
public List<Game> getGamesFilterByPlatformWithoutCharacterName(@NonNull Game.Platform platform, @NonNull String name) {
    List<Game> result = new ArrayList<>();
    for (Game t : getAll()) {
        if (t.getPlatform() == platform) {
            boolean contains = false;
            for (Game.Character u : t.getCharacters()) {
                if (u.getName().equals(name)) {
                    contains = true;
                }
            }
            if (!contains) {
                result.add(t);
            }
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns all the courses that have the given faculty but do not have any student with the given name.
 *
 * @param faculty the faculty
 * @param name the name of the student
 * @return the result
 */
@Override
public List<Course> getCoursesFilterByFacultyWithoutStudentName(@NonNull Course.Faculty faculty, @NonNull String name) {
    List<Course> result = new ArrayList<>();
    for (Course t : getAll()) {
        if (t.getFaculty() == faculty) {
            boolean contains = false;
            for (Course.Student u : t.getStudents()) {
                if (u.getName().equals(name)) {
                    contains = true;
                }
            }
            if (!contains) {
                result.add(t);
            }
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns all the movies that have the given studio but do not have any actor with the given nationality.
 *
 * @param studio the studio
 * @param nationality the nationality of the actor
 * @return the result
 */
@Override
public List<Movie> getMoviesFilterByStudioWithoutActorNationality(@NonNull Movie.Studio studio, @NonNull String nationality) {
    List<Movie> result = new ArrayList<>();
    for (Movie t : getAll()) {
        if (t.getStudio() == studio) {
            boolean contains = false;
            for (Movie.Actor u : t.getActors()) {
                if (u.getNationality().equals(nationality)) {
                    contains = true;
                }
            }
            if (!contains) {
                result.add(t);
            }
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns all the doctors that have the given department but do not have any patient with the given name.
 *
 * @param department the department
 * @param name the name of the patient
 * @return the result
 */
@Override
public List<Doctor> getDoctorsFilterByDepartmentWithoutPatientName(@NonNull Doctor.Department department, @NonNull String name) {
    List<Doctor> result = new ArrayList<>();
    for (Doctor t : getAll()) {
        if (t.getDepartment() == department) {
            boolean contains = false;
            for (Doctor.Patient u : t.getPatients()) {
                if (u.getName().equals(name)) {
                    contains = true;
                }
            }
            if (!contains) {
                result.add(t);
            }
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns all the talks that have the given track but do not have any attendee with the given company.
 *
 * @param track the track
 * @param company the company of the attendee
 * @return the result
 */
@Override
public List<Talk> getTalksFilterByTrackWithoutAttendeeCompany(@NonNull Talk.Track track, @NonNull String company) {
    List<Talk> result = new ArrayList<>();
    for (Talk t : getAll()) {
        if (t.getTrack() == track) {
            boolean contains = false;
            for (Talk.Attendee u : t.getAttendees()) {
                if (u.getCompany().equals(company)) {
                    contains = true;
                }
            }
            if (!contains) {
                result.add(t);
            }
        }
    }
    return result;
}
```

---

<a id="C2-containing"></a>

## 3. T containing the given U

Template `C2-containing`, algorithm: filtering.

**The algorithm.** Filtering the top-level objects that contain the given nested object: a flag set in the inner loop (or `contains()` of a list, which uses `equals()` the same way), and the object is added once after the inner loop.

**Typical mistakes:**

* Acting inside the inner loop (counting, adding) for every matching nested object: a top-level object would be counted or added several times. Set a flag in the inner loop and act once, after it.
* Comparing the nested objects by one of their fields by hand, or with `==`: `equals()` compares them by their identifier fields, as the model defines it; `==` compares the references.
* With an array collection: the size is `length` (a field, no parentheses), not `size()`, and an array has no `contains()`; the `for`-each loop works the same way.

| Sheet | Method |
| --- | --- |
| arcade | `List<Game> getGamesContainingCharacter(Game.Character character)` |
| campus | `List<Course> getCoursesContainingStudent(Course.Student student)` |
| cinema | `List<Movie> getMoviesContainingActor(Movie.Actor actor)` |
| clinic | `List<Doctor> getDoctorsContainingPatient(Doctor.Patient patient)` |
| conference | `List<Talk> getTalksContainingAttendee(Talk.Attendee attendee)` |

### arcade

```java
/**
 * Returns all the games that contain the given character.
 *
 * @param character the character
 * @return the result
 */
@Override
public List<Game> getGamesContainingCharacter(@NonNull Game.Character character) {
    List<Game> result = new ArrayList<>();
    for (Game t : getAll()) {
        boolean contains = false;
        for (Game.Character u : t.getCharacters()) {
            if (u.equals(character)) {
                contains = true;
            }
        }
        if (contains) {
            result.add(t);
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns all the courses that contain the given student.
 *
 * @param student the student
 * @return the result
 */
@Override
public List<Course> getCoursesContainingStudent(@NonNull Course.Student student) {
    List<Course> result = new ArrayList<>();
    for (Course t : getAll()) {
        boolean contains = false;
        for (Course.Student u : t.getStudents()) {
            if (u.equals(student)) {
                contains = true;
            }
        }
        if (contains) {
            result.add(t);
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns all the movies that contain the given actor.
 *
 * @param actor the actor
 * @return the result
 */
@Override
public List<Movie> getMoviesContainingActor(@NonNull Movie.Actor actor) {
    List<Movie> result = new ArrayList<>();
    for (Movie t : getAll()) {
        boolean contains = false;
        for (Movie.Actor u : t.getActors()) {
            if (u.equals(actor)) {
                contains = true;
            }
        }
        if (contains) {
            result.add(t);
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns all the doctors that contain the given patient.
 *
 * @param patient the patient
 * @return the result
 */
@Override
public List<Doctor> getDoctorsContainingPatient(@NonNull Doctor.Patient patient) {
    List<Doctor> result = new ArrayList<>();
    for (Doctor t : getAll()) {
        boolean contains = false;
        for (Doctor.Patient u : t.getPatients()) {
            if (u.equals(patient)) {
                contains = true;
            }
        }
        if (contains) {
            result.add(t);
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns all the talks that contain the given attendee.
 *
 * @param attendee the attendee
 * @return the result
 */
@Override
public List<Talk> getTalksContainingAttendee(@NonNull Talk.Attendee attendee) {
    List<Talk> result = new ArrayList<>();
    for (Talk t : getAll()) {
        boolean contains = false;
        for (Talk.Attendee u : t.getAttendees()) {
            if (u.equals(attendee)) {
                contains = true;
            }
        }
        if (contains) {
            result.add(t);
        }
    }
    return result;
}
```

---

<a id="C2-greater-than"></a>

## 4. T whose numeric field is greater than the given value

Template `C2-greater-than`, algorithm: filtering.

**The algorithm.** Filtering by a numeric field: a new list, and every object whose field is strictly greater than the given value is added, in the original order.

**Typical mistakes:**

* Using `>=`: *greater than* is strict.
* Declaring a collection variable by its class (`ArrayList<...> result`, `HashMap<...> result`): the references of collections are interfaces (`List`, `Set`, `Map`), the class appears only after `new`.

| Sheet | Method |
| --- | --- |
| arcade | `List<Game> getGamesAboveReleasedLimit(int released)` |
| campus | `List<Course> getCoursesAboveCreditsLimit(int credits)` |
| cinema | `List<Movie> getMoviesAboveYearLimit(int year)` |
| clinic | `List<Doctor> getDoctorsAboveExperienceLimit(int experience)` |
| conference | `List<Talk> getTalksAboveDurationLimit(int duration)` |

### arcade

```java
/**
 * Returns all the games whose release year is greater than the given value.
 *
 * @param released the release year of the game
 * @return the result
 */
@Override
public List<Game> getGamesAboveReleasedLimit(int released) {
    List<Game> result = new ArrayList<>();
    for (Game t : getAll()) {
        if (t.getReleased() > released) {
            result.add(t);
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns all the courses whose credits is greater than the given value.
 *
 * @param credits the credits of the course
 * @return the result
 */
@Override
public List<Course> getCoursesAboveCreditsLimit(int credits) {
    List<Course> result = new ArrayList<>();
    for (Course t : getAll()) {
        if (t.getCredits() > credits) {
            result.add(t);
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns all the movies whose release year is greater than the given value.
 *
 * @param year the release year of the movie
 * @return the result
 */
@Override
public List<Movie> getMoviesAboveYearLimit(int year) {
    List<Movie> result = new ArrayList<>();
    for (Movie t : getAll()) {
        if (t.getYear() > year) {
            result.add(t);
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns all the doctors whose years of experience is greater than the given value.
 *
 * @param experience the years of experience of the doctor
 * @return the result
 */
@Override
public List<Doctor> getDoctorsAboveExperienceLimit(int experience) {
    List<Doctor> result = new ArrayList<>();
    for (Doctor t : getAll()) {
        if (t.getExperience() > experience) {
            result.add(t);
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns all the talks whose duration in minutes is greater than the given value.
 *
 * @param duration the duration in minutes of the talk
 * @return the result
 */
@Override
public List<Talk> getTalksAboveDurationLimit(int duration) {
    List<Talk> result = new ArrayList<>();
    for (Talk t : getAll()) {
        if (t.getDuration() > duration) {
            result.add(t);
        }
    }
    return result;
}
```

---

<a id="C2-at-least-count"></a>

## 5. T with at least the given number of U

Template `C2-at-least-count`, algorithm: filtering.

**The algorithm.** Filtering by the size of the nested collection: an object is added if its collection has at least the given number of elements (`>=`).

**Typical mistakes:**

* Using `>`: *at least* includes the number itself.
* With an array collection: the size is `length` (a field, no parentheses), not `size()`, and an array has no `contains()`; the `for`-each loop works the same way.

| Sheet | Method |
| --- | --- |
| arcade | `List<Game> getGamesFilterByCountOfCharacters(int count)` |
| campus | `List<Course> getCoursesFilterByCountOfStudents(int count)` |
| cinema | `List<Movie> getMoviesFilterByCountOfActors(int count)` |
| clinic | `List<Doctor> getDoctorsFilterByCountOfPatients(int count)` |
| conference | `List<Talk> getTalksFilterByCountOfAttendees(int count)` |

### arcade

```java
/**
 * Returns all the games that have at least the given number of characters.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public List<Game> getGamesFilterByCountOfCharacters(int count) {
    List<Game> result = new ArrayList<>();
    for (Game t : getAll()) {
        if (t.getCharacters().length >= count) {
            result.add(t);
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns all the courses that have at least the given number of students.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public List<Course> getCoursesFilterByCountOfStudents(int count) {
    List<Course> result = new ArrayList<>();
    for (Course t : getAll()) {
        if (t.getStudents().length >= count) {
            result.add(t);
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns all the movies that have at least the given number of actors.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public List<Movie> getMoviesFilterByCountOfActors(int count) {
    List<Movie> result = new ArrayList<>();
    for (Movie t : getAll()) {
        if (t.getActors().length >= count) {
            result.add(t);
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns all the doctors that have at least the given number of patients.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public List<Doctor> getDoctorsFilterByCountOfPatients(int count) {
    List<Doctor> result = new ArrayList<>();
    for (Doctor t : getAll()) {
        if (t.getPatients().size() >= count) {
            result.add(t);
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns all the talks that have at least the given number of attendees.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public List<Talk> getTalksFilterByCountOfAttendees(int count) {
    List<Talk> result = new ArrayList<>();
    for (Talk t : getAll()) {
        if (t.getAttendees().size() >= count) {
            result.add(t);
        }
    }
    return result;
}
```

---

<a id="C2-nested-of-enum"></a>

## 6. distinct U of the T with the given enum value

Template `C2-nested-of-enum`, algorithm: filtering + flattening + distinct.

**The algorithm.** Collecting the nested objects of the selected top-level objects without duplicates, keeping the order of the first occurrences. (1) A new list. (2) For every top-level object with the given enum value, loop over its nested objects. (3) Add a nested object only if the list does not contain it yet (`contains()` uses `equals()`, the identity of the model).

**Typical mistakes:**

* Using a `HashSet` for the result: it removes the duplicates but not in the order of the first occurrences, and the method returns a `List`.
* Adding every nested object: the same object in two top-level objects would be listed twice.
* Comparing the enum field with the JSON text of the value (e.g. `"Neo Geo"`) or with `toString()`: the field holds an enum constant; compare it with the parameter by `==`.

| Sheet | Method |
| --- | --- |
| arcade | `List<Game.Character> getDistinctCharactersInOrderFilterByPlatform(Game.Platform platform)` |
| campus | `List<Course.Student> getDistinctStudentsInOrderFilterByFaculty(Course.Faculty faculty)` |
| cinema | `List<Movie.Actor> getDistinctActorsInOrderFilterByStudio(Movie.Studio studio)` |
| clinic | `List<Doctor.Patient> getDistinctPatientsInOrderFilterByDepartment(Doctor.Department department)` |
| conference | `List<Talk.Attendee> getDistinctAttendeesInOrderFilterByTrack(Talk.Track track)` |

### arcade

```java
/**
 * Returns the characters of the games that have the given platform.
 * The result cannot contain duplicates and must keep the order of the first occurrences.
 *
 * @param platform the platform
 * @return the result
 */
@Override
public List<Game.Character> getDistinctCharactersInOrderFilterByPlatform(@NonNull Game.Platform platform) {
    List<Game.Character> result = new ArrayList<>();
    for (Game t : getAll()) {
        if (t.getPlatform() == platform) {
            for (Game.Character u : t.getCharacters()) {
                if (!result.contains(u)) {
                    result.add(u);
                }
            }
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns the students of the courses that have the given faculty.
 * The result cannot contain duplicates and must keep the order of the first occurrences.
 *
 * @param faculty the faculty
 * @return the result
 */
@Override
public List<Course.Student> getDistinctStudentsInOrderFilterByFaculty(@NonNull Course.Faculty faculty) {
    List<Course.Student> result = new ArrayList<>();
    for (Course t : getAll()) {
        if (t.getFaculty() == faculty) {
            for (Course.Student u : t.getStudents()) {
                if (!result.contains(u)) {
                    result.add(u);
                }
            }
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns the actors of the movies that have the given studio.
 * The result cannot contain duplicates and must keep the order of the first occurrences.
 *
 * @param studio the studio
 * @return the result
 */
@Override
public List<Movie.Actor> getDistinctActorsInOrderFilterByStudio(@NonNull Movie.Studio studio) {
    List<Movie.Actor> result = new ArrayList<>();
    for (Movie t : getAll()) {
        if (t.getStudio() == studio) {
            for (Movie.Actor u : t.getActors()) {
                if (!result.contains(u)) {
                    result.add(u);
                }
            }
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns the patients of the doctors that have the given department.
 * The result cannot contain duplicates and must keep the order of the first occurrences.
 *
 * @param department the department
 * @return the result
 */
@Override
public List<Doctor.Patient> getDistinctPatientsInOrderFilterByDepartment(@NonNull Doctor.Department department) {
    List<Doctor.Patient> result = new ArrayList<>();
    for (Doctor t : getAll()) {
        if (t.getDepartment() == department) {
            for (Doctor.Patient u : t.getPatients()) {
                if (!result.contains(u)) {
                    result.add(u);
                }
            }
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns the attendees of the talks that have the given track.
 * The result cannot contain duplicates and must keep the order of the first occurrences.
 *
 * @param track the track
 * @return the result
 */
@Override
public List<Talk.Attendee> getDistinctAttendeesInOrderFilterByTrack(@NonNull Talk.Track track) {
    List<Talk.Attendee> result = new ArrayList<>();
    for (Talk t : getAll()) {
        if (t.getTrack() == track) {
            for (Talk.Attendee u : t.getAttendees()) {
                if (!result.contains(u)) {
                    result.add(u);
                }
            }
        }
    }
    return result;
}
```
