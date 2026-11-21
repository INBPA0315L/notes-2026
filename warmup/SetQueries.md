[↑ Tutorials](README.md)

# `SetQueries`

The interface of the queries that return a set. Every sample sheet is shown: *arcade*, *campus*, *cinema*, *clinic*, *conference*.

The result is a `Set` (`Set<...> result = new HashSet<>();`): `add()` keeps every element once (by `equals()` and `hashCode()`), and the order does not matter. A primitive value is stored boxed (`Set<Integer>`).

| | Task | Algorithm |
| --- | --- | --- |
| 1 | [enum values of the T having any U with the given field value](#C3-enums-with-nested-field) | filtering + projection to set |
| 2 | [enum values of the T containing the given U](#C3-enums-with-nested) | filtering + projection to set |
| 3 | [field values of the T having any U with the given field value](#C3-values-with-nested-field) | filtering + projection to set |
| 4 | [U of the T with the given enum value](#C3-nested-of-enum) | filtering + flattening to set |
| 5 | [enum values of the T whose numeric field is greater than the given value](#C3-enums-greater-than) | filtering + projection to set |
| 6 | [distinct values of a field of U](#C3-nested-field-values) | flattening + projection to set |

---

<a id="C3-enums-with-nested-field"></a>

## 1. enum values of the T having any U with the given field value

Template `C3-enums-with-nested-field`, algorithm: filtering + projection to set.

**The algorithm.** A set of the enum values of the objects that have a matching nested object. (1) A new `HashSet`. (2) For every top-level object, a flag set in the inner loop at a matching nested object. (3) If set, add the enum value of the object; the set keeps every value once.

**Typical mistakes:**

* Declaring the flag before the outer loop: it must start as `false` again for every top-level object, so declare it inside the outer loop.
* Comparing strings with `==`: it compares the references, not the texts, so a matching string may be missed. Use `equals()`.
* Declaring a collection variable by its class (`ArrayList<...> result`, `HashMap<...> result`): the references of collections are interfaces (`List`, `Set`, `Map`), the class appears only after `new`.

| Sheet | Method |
| --- | --- |
| arcade | `Set<Game.Platform> getPlatformsByCharacterName(String name)` |
| campus | `Set<Course.Faculty> getFacultiesByStudentNeptun(String neptun)` |
| cinema | `Set<Movie.Studio> getStudiosByActorNationality(String nationality)` |
| clinic | `Set<Doctor.Department> getDepartmentsByPatientName(String name)` |
| conference | `Set<Talk.Track> getTracksByAttendeeEmail(String email)` |

### arcade

```java
/**
 * Returns all the platforms that have any game with any character having the given name.
 *
 * @param name the name of the character
 * @return the result
 */
@Override
public Set<Game.Platform> getPlatformsByCharacterName(@NonNull String name) {
    Set<Game.Platform> result = new HashSet<>();
    for (Game t : getAll()) {
        boolean contains = false;
        for (Game.Character u : t.getCharacters()) {
            if (u.getName().equals(name)) {
                contains = true;
            }
        }
        if (contains) {
            result.add(t.getPlatform());
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns all the faculties that have any course with any student having the given Neptun code.
 *
 * @param neptun the Neptun code of the student
 * @return the result
 */
@Override
public Set<Course.Faculty> getFacultiesByStudentNeptun(@NonNull String neptun) {
    Set<Course.Faculty> result = new HashSet<>();
    for (Course t : getAll()) {
        boolean contains = false;
        for (Course.Student u : t.getStudents()) {
            if (u.getNeptun().equals(neptun)) {
                contains = true;
            }
        }
        if (contains) {
            result.add(t.getFaculty());
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns all the studios that have any movie with any actor having the given nationality.
 *
 * @param nationality the nationality of the actor
 * @return the result
 */
@Override
public Set<Movie.Studio> getStudiosByActorNationality(@NonNull String nationality) {
    Set<Movie.Studio> result = new HashSet<>();
    for (Movie t : getAll()) {
        boolean contains = false;
        for (Movie.Actor u : t.getActors()) {
            if (u.getNationality().equals(nationality)) {
                contains = true;
            }
        }
        if (contains) {
            result.add(t.getStudio());
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns all the departments that have any doctor with any patient having the given name.
 *
 * @param name the name of the patient
 * @return the result
 */
@Override
public Set<Doctor.Department> getDepartmentsByPatientName(@NonNull String name) {
    Set<Doctor.Department> result = new HashSet<>();
    for (Doctor t : getAll()) {
        boolean contains = false;
        for (Doctor.Patient u : t.getPatients()) {
            if (u.getName().equals(name)) {
                contains = true;
            }
        }
        if (contains) {
            result.add(t.getDepartment());
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns all the tracks that have any talk with any attendee having the given e-mail address.
 *
 * @param email the e-mail address of the attendee
 * @return the result
 */
@Override
public Set<Talk.Track> getTracksByAttendeeEmail(@NonNull String email) {
    Set<Talk.Track> result = new HashSet<>();
    for (Talk t : getAll()) {
        boolean contains = false;
        for (Talk.Attendee u : t.getAttendees()) {
            if (u.getEmail().equals(email)) {
                contains = true;
            }
        }
        if (contains) {
            result.add(t.getTrack());
        }
    }
    return result;
}
```

---

<a id="C3-enums-with-nested"></a>

## 2. enum values of the T containing the given U

Template `C3-enums-with-nested`, algorithm: filtering + projection to set.

**The algorithm.** A set of the enum values of the objects containing the given nested object: a flag in the inner loop (`equals()`), and the enum value of the object is added to the set if the flag is set.

**Typical mistakes:**

* Comparing the nested objects by one of their fields by hand, or with `==`: `equals()` compares them by their identifier fields, as the model defines it; `==` compares the references.
* Declaring the flag before the outer loop: it must start as `false` again for every top-level object, so declare it inside the outer loop.

| Sheet | Method |
| --- | --- |
| arcade | `Set<Game.Platform> getPlatformsContainingCharacter(Game.Character character)` |
| campus | `Set<Course.Faculty> getFacultiesContainingStudent(Course.Student student)` |
| cinema | `Set<Movie.Studio> getStudiosContainingActor(Movie.Actor actor)` |
| clinic | `Set<Doctor.Department> getDepartmentsContainingPatient(Doctor.Patient patient)` |
| conference | `Set<Talk.Track> getTracksContainingAttendee(Talk.Attendee attendee)` |

### arcade

```java
/**
 * Returns all the platforms that have any game containing the given character.
 *
 * @param character the character
 * @return the result
 */
@Override
public Set<Game.Platform> getPlatformsContainingCharacter(@NonNull Game.Character character) {
    Set<Game.Platform> result = new HashSet<>();
    for (Game t : getAll()) {
        boolean contains = false;
        for (Game.Character u : t.getCharacters()) {
            if (u.equals(character)) {
                contains = true;
            }
        }
        if (contains) {
            result.add(t.getPlatform());
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns all the faculties that have any course containing the given student.
 *
 * @param student the student
 * @return the result
 */
@Override
public Set<Course.Faculty> getFacultiesContainingStudent(@NonNull Course.Student student) {
    Set<Course.Faculty> result = new HashSet<>();
    for (Course t : getAll()) {
        boolean contains = false;
        for (Course.Student u : t.getStudents()) {
            if (u.equals(student)) {
                contains = true;
            }
        }
        if (contains) {
            result.add(t.getFaculty());
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns all the studios that have any movie containing the given actor.
 *
 * @param actor the actor
 * @return the result
 */
@Override
public Set<Movie.Studio> getStudiosContainingActor(@NonNull Movie.Actor actor) {
    Set<Movie.Studio> result = new HashSet<>();
    for (Movie t : getAll()) {
        boolean contains = false;
        for (Movie.Actor u : t.getActors()) {
            if (u.equals(actor)) {
                contains = true;
            }
        }
        if (contains) {
            result.add(t.getStudio());
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns all the departments that have any doctor containing the given patient.
 *
 * @param patient the patient
 * @return the result
 */
@Override
public Set<Doctor.Department> getDepartmentsContainingPatient(@NonNull Doctor.Patient patient) {
    Set<Doctor.Department> result = new HashSet<>();
    for (Doctor t : getAll()) {
        boolean contains = false;
        for (Doctor.Patient u : t.getPatients()) {
            if (u.equals(patient)) {
                contains = true;
            }
        }
        if (contains) {
            result.add(t.getDepartment());
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns all the tracks that have any talk containing the given attendee.
 *
 * @param attendee the attendee
 * @return the result
 */
@Override
public Set<Talk.Track> getTracksContainingAttendee(@NonNull Talk.Attendee attendee) {
    Set<Talk.Track> result = new HashSet<>();
    for (Talk t : getAll()) {
        boolean contains = false;
        for (Talk.Attendee u : t.getAttendees()) {
            if (u.equals(attendee)) {
                contains = true;
            }
        }
        if (contains) {
            result.add(t.getTrack());
        }
    }
    return result;
}
```

---

<a id="C3-values-with-nested-field"></a>

## 3. field values of the T having any U with the given field value

Template `C3-values-with-nested-field`, algorithm: filtering + projection to set.

**The algorithm.** A set of the values of a field of the objects that have a matching nested object: a flag in the inner loop, then the field of the top-level object is added to the set.

**Typical mistakes:**

* Adding the field of the nested object instead of the top-level object's field.
* Comparing strings with `==`: it compares the references, not the texts, so a matching string may be missed. Use `equals()`.

| Sheet | Method |
| --- | --- |
| arcade | `Set<String> getNameValuesByCharacterName(String name)` |
| campus | `Set<String> getTitleValuesByStudentName(String name)` |
| cinema | `Set<String> getGenreValuesByActorNationality(String nationality)` |
| clinic | `Set<String> getRoomValuesByPatientName(String name)` |
| conference | `Set<String> getTitleValuesByAttendeeCompany(String company)` |

### arcade

```java
/**
 * Returns the name of each game that has any character with the given name.
 *
 * @param name the name of the character
 * @return the result
 */
@Override
public Set<String> getNameValuesByCharacterName(@NonNull String name) {
    Set<String> result = new HashSet<>();
    for (Game t : getAll()) {
        boolean contains = false;
        for (Game.Character u : t.getCharacters()) {
            if (u.getName().equals(name)) {
                contains = true;
            }
        }
        if (contains) {
            result.add(t.getName());
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns the title of each course that has any student with the given name.
 *
 * @param name the name of the student
 * @return the result
 */
@Override
public Set<String> getTitleValuesByStudentName(@NonNull String name) {
    Set<String> result = new HashSet<>();
    for (Course t : getAll()) {
        boolean contains = false;
        for (Course.Student u : t.getStudents()) {
            if (u.getName().equals(name)) {
                contains = true;
            }
        }
        if (contains) {
            result.add(t.getTitle());
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns the genre of each movie that has any actor with the given nationality.
 *
 * @param nationality the nationality of the actor
 * @return the result
 */
@Override
public Set<String> getGenreValuesByActorNationality(@NonNull String nationality) {
    Set<String> result = new HashSet<>();
    for (Movie t : getAll()) {
        boolean contains = false;
        for (Movie.Actor u : t.getActors()) {
            if (u.getNationality().equals(nationality)) {
                contains = true;
            }
        }
        if (contains) {
            result.add(t.getGenre());
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns the room number of each doctor that has any patient with the given name.
 *
 * @param name the name of the patient
 * @return the result
 */
@Override
public Set<String> getRoomValuesByPatientName(@NonNull String name) {
    Set<String> result = new HashSet<>();
    for (Doctor t : getAll()) {
        boolean contains = false;
        for (Doctor.Patient u : t.getPatients()) {
            if (u.getName().equals(name)) {
                contains = true;
            }
        }
        if (contains) {
            result.add(t.getRoom());
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns the title of each talk that has any attendee with the given company.
 *
 * @param company the company of the attendee
 * @return the result
 */
@Override
public Set<String> getTitleValuesByAttendeeCompany(@NonNull String company) {
    Set<String> result = new HashSet<>();
    for (Talk t : getAll()) {
        boolean contains = false;
        for (Talk.Attendee u : t.getAttendees()) {
            if (u.getCompany().equals(company)) {
                contains = true;
            }
        }
        if (contains) {
            result.add(t.getTitle());
        }
    }
    return result;
}
```

---

<a id="C3-nested-of-enum"></a>

## 4. U of the T with the given enum value

Template `C3-nested-of-enum`, algorithm: filtering + flattening to set.

**The algorithm.** A set of the nested objects of the selected top-level objects: `add()` ignores an element that is already in the set, by `equals()` and `hashCode()`, both generated from the identifier fields of the model.

**Typical mistakes:**

* A wrong `@EqualsAndHashCode` in the model: the set would merge different objects, or keep equal ones twice.
* Comparing the enum field with the JSON text of the value (e.g. `"Neo Geo"`) or with `toString()`: the field holds an enum constant; compare it with the parameter by `==`.
* Declaring a collection variable by its class (`ArrayList<...> result`, `HashMap<...> result`): the references of collections are interfaces (`List`, `Set`, `Map`), the class appears only after `new`.

| Sheet | Method |
| --- | --- |
| arcade | `Set<Game.Character> getCharactersFilterByPlatform(Game.Platform platform)` |
| campus | `Set<Course.Student> getStudentsFilterByFaculty(Course.Faculty faculty)` |
| cinema | `Set<Movie.Actor> getActorsFilterByStudio(Movie.Studio studio)` |
| clinic | `Set<Doctor.Patient> getPatientsFilterByDepartment(Doctor.Department department)` |
| conference | `Set<Talk.Attendee> getAttendeesFilterByTrack(Talk.Track track)` |

### arcade

```java
/**
 * Returns all the characters of the games that have the given platform.
 *
 * @param platform the platform
 * @return the result
 */
@Override
public Set<Game.Character> getCharactersFilterByPlatform(@NonNull Game.Platform platform) {
    Set<Game.Character> result = new HashSet<>();
    for (Game t : getAll()) {
        if (t.getPlatform() == platform) {
            for (Game.Character u : t.getCharacters()) {
                result.add(u);
            }
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns all the students of the courses that have the given faculty.
 *
 * @param faculty the faculty
 * @return the result
 */
@Override
public Set<Course.Student> getStudentsFilterByFaculty(@NonNull Course.Faculty faculty) {
    Set<Course.Student> result = new HashSet<>();
    for (Course t : getAll()) {
        if (t.getFaculty() == faculty) {
            for (Course.Student u : t.getStudents()) {
                result.add(u);
            }
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns all the actors of the movies that have the given studio.
 *
 * @param studio the studio
 * @return the result
 */
@Override
public Set<Movie.Actor> getActorsFilterByStudio(@NonNull Movie.Studio studio) {
    Set<Movie.Actor> result = new HashSet<>();
    for (Movie t : getAll()) {
        if (t.getStudio() == studio) {
            for (Movie.Actor u : t.getActors()) {
                result.add(u);
            }
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns all the patients of the doctors that have the given department.
 *
 * @param department the department
 * @return the result
 */
@Override
public Set<Doctor.Patient> getPatientsFilterByDepartment(@NonNull Doctor.Department department) {
    Set<Doctor.Patient> result = new HashSet<>();
    for (Doctor t : getAll()) {
        if (t.getDepartment() == department) {
            for (Doctor.Patient u : t.getPatients()) {
                result.add(u);
            }
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns all the attendees of the talks that have the given track.
 *
 * @param track the track
 * @return the result
 */
@Override
public Set<Talk.Attendee> getAttendeesFilterByTrack(@NonNull Talk.Track track) {
    Set<Talk.Attendee> result = new HashSet<>();
    for (Talk t : getAll()) {
        if (t.getTrack() == track) {
            for (Talk.Attendee u : t.getAttendees()) {
                result.add(u);
            }
        }
    }
    return result;
}
```

---

<a id="C3-enums-greater-than"></a>

## 5. enum values of the T whose numeric field is greater than the given value

Template `C3-enums-greater-than`, algorithm: filtering + projection to set.

**The algorithm.** A set of the enum values of the objects whose numeric field is strictly greater than the given value.

**Typical mistakes:**

* Using `>=`: *greater than* is strict.
* Declaring a collection variable by its class (`ArrayList<...> result`, `HashMap<...> result`): the references of collections are interfaces (`List`, `Set`, `Map`), the class appears only after `new`.

| Sheet | Method |
| --- | --- |
| arcade | `Set<Game.Platform> getPlatformsAboveReleasedLimit(int released)` |
| campus | `Set<Course.Faculty> getFacultiesAboveCreditsLimit(int credits)` |
| cinema | `Set<Movie.Studio> getStudiosAboveYearLimit(int year)` |
| clinic | `Set<Doctor.Department> getDepartmentsAboveExperienceLimit(int experience)` |
| conference | `Set<Talk.Track> getTracksAboveDurationLimit(int duration)` |

### arcade

```java
/**
 * Returns all the platforms that have any game whose release year is greater than the given value.
 *
 * @param released the release year of the game
 * @return the result
 */
@Override
public Set<Game.Platform> getPlatformsAboveReleasedLimit(int released) {
    Set<Game.Platform> result = new HashSet<>();
    for (Game t : getAll()) {
        if (t.getReleased() > released) {
            result.add(t.getPlatform());
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns all the faculties that have any course whose credits is greater than the given value.
 *
 * @param credits the credits of the course
 * @return the result
 */
@Override
public Set<Course.Faculty> getFacultiesAboveCreditsLimit(int credits) {
    Set<Course.Faculty> result = new HashSet<>();
    for (Course t : getAll()) {
        if (t.getCredits() > credits) {
            result.add(t.getFaculty());
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns all the studios that have any movie whose release year is greater than the given value.
 *
 * @param year the release year of the movie
 * @return the result
 */
@Override
public Set<Movie.Studio> getStudiosAboveYearLimit(int year) {
    Set<Movie.Studio> result = new HashSet<>();
    for (Movie t : getAll()) {
        if (t.getYear() > year) {
            result.add(t.getStudio());
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns all the departments that have any doctor whose years of experience is greater than the given value.
 *
 * @param experience the years of experience of the doctor
 * @return the result
 */
@Override
public Set<Doctor.Department> getDepartmentsAboveExperienceLimit(int experience) {
    Set<Doctor.Department> result = new HashSet<>();
    for (Doctor t : getAll()) {
        if (t.getExperience() > experience) {
            result.add(t.getDepartment());
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns all the tracks that have any talk whose duration in minutes is greater than the given value.
 *
 * @param duration the duration in minutes of the talk
 * @return the result
 */
@Override
public Set<Talk.Track> getTracksAboveDurationLimit(int duration) {
    Set<Talk.Track> result = new HashSet<>();
    for (Talk t : getAll()) {
        if (t.getDuration() > duration) {
            result.add(t.getTrack());
        }
    }
    return result;
}
```

---

<a id="C3-nested-field-values"></a>

## 6. distinct values of a field of U

Template `C3-nested-field-values`, algorithm: flattening + projection to set.

**The algorithm.** A set of the different values of a field of all the nested objects: two loops, every value is added, and the set keeps each value once (a primitive value is stored boxed, e.g. `Set<Integer>`).

**Typical mistakes:**

* Returning a `List` of the values: it would contain the repeated values.
* Declaring a collection variable by its class (`ArrayList<...> result`, `HashMap<...> result`): the references of collections are interfaces (`List`, `Set`, `Map`), the class appears only after `new`.

| Sheet | Method |
| --- | --- |
| arcade | `Set<Integer> getCharacterGamesValues()` |
| campus | `Set<Integer> getStudentGradeValues()` |
| cinema | `Set<String> getActorNationalityValues()` |
| clinic | `Set<Integer> getPatientAgeValues()` |
| conference | `Set<String> getAttendeeCompanyValues()` |

### arcade

```java
/**
 * Returns all the distinct number of games values of the characters.
 *
 * @return the result
 */
@Override
public Set<Integer> getCharacterGamesValues() {
    Set<Integer> result = new HashSet<>();
    for (Game t : getAll()) {
        for (Game.Character u : t.getCharacters()) {
            result.add(u.getGames());
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns all the distinct grade values of the students.
 *
 * @return the result
 */
@Override
public Set<Integer> getStudentGradeValues() {
    Set<Integer> result = new HashSet<>();
    for (Course t : getAll()) {
        for (Course.Student u : t.getStudents()) {
            result.add(u.getGrade());
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns all the distinct nationality values of the actors.
 *
 * @return the result
 */
@Override
public Set<String> getActorNationalityValues() {
    Set<String> result = new HashSet<>();
    for (Movie t : getAll()) {
        for (Movie.Actor u : t.getActors()) {
            result.add(u.getNationality());
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns all the distinct age values of the patients.
 *
 * @return the result
 */
@Override
public Set<Integer> getPatientAgeValues() {
    Set<Integer> result = new HashSet<>();
    for (Doctor t : getAll()) {
        for (Doctor.Patient u : t.getPatients()) {
            result.add(u.getAge());
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns all the distinct company values of the attendees.
 *
 * @return the result
 */
@Override
public Set<String> getAttendeeCompanyValues() {
    Set<String> result = new HashSet<>();
    for (Talk t : getAll()) {
        for (Talk.Attendee u : t.getAttendees()) {
            result.add(u.getCompany());
        }
    }
    return result;
}
```
