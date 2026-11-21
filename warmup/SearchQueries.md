[↑ Tutorials](README.md)

# `SearchQueries`

The interface of the queries that return a single object (searching, selecting the maximum). Every sample sheet is shown: *arcade*, *campus*, *cinema*, *clinic*, *conference*.

The result is one object: a top-level object (`T`) or a nested one (`U`). Two patterns: **linear search** (return the first match inside the loop; after the loop nothing matched) and **maximum selection** (keep the best object so far, replace it only by a strictly greater one, so the first one wins on ties). The documentation tells what to do if there is no such object: return `null` or throw the given exception.

| | Task | Algorithm |
| --- | --- | --- |
| 1 | [first T with the given enum value](#C1-first-by-enum) | linear search |
| 2 | [first T with the given value of a String field](#C1-first-by-field) | linear search |
| 3 | [first T having any U with the given field value](#C1-first-by-nested-field) | linear search (nested decision) |
| 4 | [first U with the given field value](#C1-first-nested-by-field) | linear search (flattened) |
| 5 | [T with the greatest value of a numeric field](#C1-max-top) | maximum selection |
| 6 | [T with the most nested objects](#C1-most-nested) | maximum selection |
| 7 | [U with the greatest value of a numeric field](#C1-max-nested) | maximum selection (flattened) |

---

<a id="C1-first-by-enum"></a>

## 1. first T with the given enum value

Template `C1-first-by-enum`, algorithm: linear search.

**The algorithm.** Linear search. (1) Loop over the top-level objects. (2) Return the first object with the given enum value right inside the loop: it is the first one. (3) If the loop ends, nothing matched: `null` or the documented exception.

**Typical mistakes:**

* Not reading the end of the documentation: if nothing matches, the method returns `null` or throws the given (checked) exception, which the method declares with `throws`.
* Returning inside the loop the *else* case too (`else return null;`): the first non-matching object would end the search.
* Comparing the enum field with the JSON text of the value (e.g. `"Neo Geo"`) or with `toString()`: the field holds an enum constant; compare it with the parameter by `==`.

| Sheet | Method |
| --- | --- |
| arcade | `Game getFirstGameByPlatform(Game.Platform platform)` |
| campus | `Course getFirstCourseByFaculty(Course.Faculty faculty) throws NotFoundException` |
| cinema | `Movie getFirstMovieByStudio(Movie.Studio studio) throws NoResultException` |
| clinic | `Doctor getFirstDoctorByDepartment(Doctor.Department department)` |
| conference | `Talk getFirstTalkByTrack(Talk.Track track) throws EmptyResultException` |

### arcade

```java
/**
 * Returns the first game that has the given platform.
 * Returns <code>null</code> if no <i>game</i> matches the condition.
 *
 * @param platform the platform
 * @return the result
 */
@Override
public Game getFirstGameByPlatform(@NonNull Game.Platform platform) {
    for (Game t : getAll()) {
        if (t.getPlatform() == platform) {
            return t;
        }
    }
    return null;
}
```

### campus

```java
/**
 * Returns the first course that has the given faculty.
 *
 * @param faculty the faculty
 * @return the result
 * @throws NotFoundException if no <i>course</i> matches the condition
 */
@Override
public Course getFirstCourseByFaculty(@NonNull Course.Faculty faculty) throws NotFoundException {
    for (Course t : getAll()) {
        if (t.getFaculty() == faculty) {
            return t;
        }
    }
    throw new NotFoundException();
}
```

### cinema

```java
/**
 * Returns the first movie that has the given studio.
 *
 * @param studio the studio
 * @return the result
 * @throws NoResultException if no <i>movie</i> matches the condition
 */
@Override
public Movie getFirstMovieByStudio(@NonNull Movie.Studio studio) throws NoResultException {
    for (Movie t : getAll()) {
        if (t.getStudio() == studio) {
            return t;
        }
    }
    throw new NoResultException();
}
```

### clinic

```java
/**
 * Returns the first doctor that has the given department.
 * Returns <code>null</code> if no <i>doctor</i> matches the condition.
 *
 * @param department the department
 * @return the result
 */
@Override
public Doctor getFirstDoctorByDepartment(@NonNull Doctor.Department department) {
    for (Doctor t : getAll()) {
        if (t.getDepartment() == department) {
            return t;
        }
    }
    return null;
}
```

### conference

```java
/**
 * Returns the first talk that has the given track.
 *
 * @param track the track
 * @return the result
 * @throws EmptyResultException if no <i>talk</i> matches the condition
 */
@Override
public Talk getFirstTalkByTrack(@NonNull Talk.Track track) throws EmptyResultException {
    for (Talk t : getAll()) {
        if (t.getTrack() == track) {
            return t;
        }
    }
    throw new EmptyResultException();
}
```

---

<a id="C1-first-by-field"></a>

## 2. first T with the given value of a String field

Template `C1-first-by-field`, algorithm: linear search.

**The algorithm.** Linear search by a `String` field: return the first object whose field `equals()` the parameter; after the loop `null` or the documented exception.

**Typical mistakes:**

* Comparing strings with `==`: it compares the references, not the texts, so a matching string may be missed. Use `equals()`.
* Not reading the end of the documentation: if nothing matches, the method returns `null` or throws the given (checked) exception, which the method declares with `throws`.
* Continuing the loop after a match and returning the last match: the first one is asked.

| Sheet | Method |
| --- | --- |
| arcade | `Game getFirstGameByName(String name) throws NotFoundException` |
| campus | `Course getFirstCourseByCode(String code)` |
| cinema | `Movie getFirstMovieByTitle(String title) throws EmptyResultException` |
| clinic | `Doctor getFirstDoctorByName(String name) throws EmptyResultException` |
| conference | `Talk getFirstTalkBySpeaker(String speaker)` |

### arcade

```java
/**
 * Returns the first game that has the given name.
 *
 * @param name the name of the game
 * @return the result
 * @throws NotFoundException if no <i>game</i> matches the condition
 */
@Override
public Game getFirstGameByName(@NonNull String name) throws NotFoundException {
    for (Game t : getAll()) {
        if (t.getName().equals(name)) {
            return t;
        }
    }
    throw new NotFoundException();
}
```

### campus

```java
/**
 * Returns the first course that has the given course code.
 * Returns <code>null</code> if no <i>course</i> matches the condition.
 *
 * @param code the course code of the course
 * @return the result
 */
@Override
public Course getFirstCourseByCode(@NonNull String code) {
    for (Course t : getAll()) {
        if (t.getCode().equals(code)) {
            return t;
        }
    }
    return null;
}
```

### cinema

```java
/**
 * Returns the first movie that has the given title.
 *
 * @param title the title of the movie
 * @return the result
 * @throws EmptyResultException if no <i>movie</i> matches the condition
 */
@Override
public Movie getFirstMovieByTitle(@NonNull String title) throws EmptyResultException {
    for (Movie t : getAll()) {
        if (t.getTitle().equals(title)) {
            return t;
        }
    }
    throw new EmptyResultException();
}
```

### clinic

```java
/**
 * Returns the first doctor that has the given name.
 *
 * @param name the name of the doctor
 * @return the result
 * @throws EmptyResultException if no <i>doctor</i> matches the condition
 */
@Override
public Doctor getFirstDoctorByName(@NonNull String name) throws EmptyResultException {
    for (Doctor t : getAll()) {
        if (t.getName().equals(name)) {
            return t;
        }
    }
    throw new EmptyResultException();
}
```

### conference

```java
/**
 * Returns the first talk that has the given speaker.
 * Returns <code>null</code> if no <i>talk</i> matches the condition.
 *
 * @param speaker the speaker of the talk
 * @return the result
 */
@Override
public Talk getFirstTalkBySpeaker(@NonNull String speaker) {
    for (Talk t : getAll()) {
        if (t.getSpeaker().equals(speaker)) {
            return t;
        }
    }
    return null;
}
```

---

<a id="C1-first-by-nested-field"></a>

## 3. first T having any U with the given field value

Template `C1-first-by-nested-field`, algorithm: linear search (nested decision).

**The algorithm.** Linear search for the first top-level object having a matching nested object. The outer loop goes over the top-level objects, the inner one over their nested objects; at a match the method returns the **outer** object `t`. After both loops: `null` or the documented exception.

**Typical mistakes:**

* Returning the nested object `u` instead of the top-level object `t`.
* Comparing strings with `==`: it compares the references, not the texts, so a matching string may be missed. Use `equals()`.
* Not reading the end of the documentation: if nothing matches, the method returns `null` or throws the given (checked) exception, which the method declares with `throws`.

| Sheet | Method |
| --- | --- |
| arcade | `Game getFirstGameByCharacterName(String name) throws EmptyResultException` |
| campus | `Course getFirstCourseByStudentName(String name)` |
| cinema | `Movie getFirstMovieByActorName(String name)` |
| clinic | `Doctor getFirstDoctorByPatientName(String name)` |
| conference | `Talk getFirstTalkByAttendeeEmail(String email)` |

### arcade

```java
/**
 * Returns the first game that has any character with the given name.
 *
 * @param name the name of the character
 * @return the result
 * @throws EmptyResultException if no <i>game</i> matches the condition
 */
@Override
public Game getFirstGameByCharacterName(@NonNull String name) throws EmptyResultException {
    for (Game t : getAll()) {
        for (Game.Character u : t.getCharacters()) {
            if (u.getName().equals(name)) {
                return t;
            }
        }
    }
    throw new EmptyResultException();
}
```

### campus

```java
/**
 * Returns the first course that has any student with the given name.
 * Returns <code>null</code> if no <i>course</i> matches the condition.
 *
 * @param name the name of the student
 * @return the result
 */
@Override
public Course getFirstCourseByStudentName(@NonNull String name) {
    for (Course t : getAll()) {
        for (Course.Student u : t.getStudents()) {
            if (u.getName().equals(name)) {
                return t;
            }
        }
    }
    return null;
}
```

### cinema

```java
/**
 * Returns the first movie that has any actor with the given name.
 * Returns <code>null</code> if no <i>movie</i> matches the condition.
 *
 * @param name the name of the actor
 * @return the result
 */
@Override
public Movie getFirstMovieByActorName(@NonNull String name) {
    for (Movie t : getAll()) {
        for (Movie.Actor u : t.getActors()) {
            if (u.getName().equals(name)) {
                return t;
            }
        }
    }
    return null;
}
```

### clinic

```java
/**
 * Returns the first doctor that has any patient with the given name.
 * Returns <code>null</code> if no <i>doctor</i> matches the condition.
 *
 * @param name the name of the patient
 * @return the result
 */
@Override
public Doctor getFirstDoctorByPatientName(@NonNull String name) {
    for (Doctor t : getAll()) {
        for (Doctor.Patient u : t.getPatients()) {
            if (u.getName().equals(name)) {
                return t;
            }
        }
    }
    return null;
}
```

### conference

```java
/**
 * Returns the first talk that has any attendee with the given e-mail address.
 * Returns <code>null</code> if no <i>talk</i> matches the condition.
 *
 * @param email the e-mail address of the attendee
 * @return the result
 */
@Override
public Talk getFirstTalkByAttendeeEmail(@NonNull String email) {
    for (Talk t : getAll()) {
        for (Talk.Attendee u : t.getAttendees()) {
            if (u.getEmail().equals(email)) {
                return t;
            }
        }
    }
    return null;
}
```

---

<a id="C1-first-nested-by-field"></a>

## 4. first U with the given field value

Template `C1-first-nested-by-field`, algorithm: linear search (flattened).

**The algorithm.** Linear search over all the nested objects, in the order of the top-level objects and of their collections: the first nested object with the given value is returned (`u`). After both loops: `null` or the documented exception.

**Typical mistakes:**

* Comparing strings with `==`: it compares the references, not the texts, so a matching string may be missed. Use `equals()`.
* Not reading the end of the documentation: if nothing matches, the method returns `null` or throws the given (checked) exception, which the method declares with `throws`.

| Sheet | Method |
| --- | --- |
| arcade | `Game.Character getFirstCharacterByName(String name) throws NoResultException` |
| campus | `Course.Student getFirstStudentByName(String name) throws NotFoundException` |
| cinema | `Movie.Actor getFirstActorByName(String name)` |
| clinic | `Doctor.Patient getFirstPatientByName(String name)` |
| conference | `Talk.Attendee getFirstAttendeeByCompany(String company) throws NoResultException` |

### arcade

```java
/**
 * Returns the first character that has the given name.
 *
 * @param name the name of the character
 * @return the result
 * @throws NoResultException if no <i>character</i> matches the condition
 */
@Override
public Game.Character getFirstCharacterByName(@NonNull String name) throws NoResultException {
    for (Game t : getAll()) {
        for (Game.Character u : t.getCharacters()) {
            if (u.getName().equals(name)) {
                return u;
            }
        }
    }
    throw new NoResultException();
}
```

### campus

```java
/**
 * Returns the first student that has the given name.
 *
 * @param name the name of the student
 * @return the result
 * @throws NotFoundException if no <i>student</i> matches the condition
 */
@Override
public Course.Student getFirstStudentByName(@NonNull String name) throws NotFoundException {
    for (Course t : getAll()) {
        for (Course.Student u : t.getStudents()) {
            if (u.getName().equals(name)) {
                return u;
            }
        }
    }
    throw new NotFoundException();
}
```

### cinema

```java
/**
 * Returns the first actor that has the given name.
 * Returns <code>null</code> if no <i>actor</i> matches the condition.
 *
 * @param name the name of the actor
 * @return the result
 */
@Override
public Movie.Actor getFirstActorByName(@NonNull String name) {
    for (Movie t : getAll()) {
        for (Movie.Actor u : t.getActors()) {
            if (u.getName().equals(name)) {
                return u;
            }
        }
    }
    return null;
}
```

### clinic

```java
/**
 * Returns the first patient that has the given name.
 * Returns <code>null</code> if no <i>patient</i> matches the condition.
 *
 * @param name the name of the patient
 * @return the result
 */
@Override
public Doctor.Patient getFirstPatientByName(@NonNull String name) {
    for (Doctor t : getAll()) {
        for (Doctor.Patient u : t.getPatients()) {
            if (u.getName().equals(name)) {
                return u;
            }
        }
    }
    return null;
}
```

### conference

```java
/**
 * Returns the first attendee that has the given company.
 *
 * @param company the company of the attendee
 * @return the result
 * @throws NoResultException if no <i>attendee</i> matches the condition
 */
@Override
public Talk.Attendee getFirstAttendeeByCompany(@NonNull String company) throws NoResultException {
    for (Talk t : getAll()) {
        for (Talk.Attendee u : t.getAttendees()) {
            if (u.getCompany().equals(company)) {
                return u;
            }
        }
    }
    throw new NoResultException();
}
```

---

<a id="C1-max-top"></a>

## 5. T with the greatest value of a numeric field

Template `C1-max-top`, algorithm: maximum selection.

**The algorithm.** Maximum selection. (1) `best` starts as `null`. (2) In the loop the first object replaces `null`; later an object replaces the best only if its field is strictly greater. (3) After the loop `best == null` means no objects: `null` or the documented exception; otherwise return `best`.

**Typical mistakes:**

* Using `>=` (or `<=`) instead of `>`: on ties the last object would win, but the documentation asks for the first one.
* Keeping only the greatest value: the result is the object, not the number.
* Not reading the end of the documentation: if nothing matches, the method returns `null` or throws the given (checked) exception, which the method declares with `throws`.

| Sheet | Method |
| --- | --- |
| arcade | `Game getGameWithMaximumReleased()` |
| campus | `Course getCourseWithMaximumCredits() throws EmptyResultException` |
| cinema | `Movie getMovieWithMaximumYear()` |
| clinic | `Doctor getDoctorWithMaximumExperience()` |
| conference | `Talk getTalkWithMaximumRating()` |

### arcade

```java
/**
 * Returns the game that has the greatest release year.
 * If more games have the greatest value, the first one is returned.
 * Returns <code>null</code> if there are no <i>games</i>.
 *
 * @return the result
 */
@Override
public Game getGameWithMaximumReleased() {
    Game best = null;
    for (Game t : getAll()) {
        if (best == null || t.getReleased() > best.getReleased()) {
            best = t;
        }
    }
    if (best == null) {
        return null;
    }
    return best;
}
```

### campus

```java
/**
 * Returns the course that has the greatest credits.
 * If more courses have the greatest value, the first one is returned.
 *
 * @return the result
 * @throws EmptyResultException if there are no <i>courses</i>
 */
@Override
public Course getCourseWithMaximumCredits() throws EmptyResultException {
    Course best = null;
    for (Course t : getAll()) {
        if (best == null || t.getCredits() > best.getCredits()) {
            best = t;
        }
    }
    if (best == null) {
        throw new EmptyResultException();
    }
    return best;
}
```

### cinema

```java
/**
 * Returns the movie that has the greatest release year.
 * If more movies have the greatest value, the first one is returned.
 * Returns <code>null</code> if there are no <i>movies</i>.
 *
 * @return the result
 */
@Override
public Movie getMovieWithMaximumYear() {
    Movie best = null;
    for (Movie t : getAll()) {
        if (best == null || t.getYear() > best.getYear()) {
            best = t;
        }
    }
    if (best == null) {
        return null;
    }
    return best;
}
```

### clinic

```java
/**
 * Returns the doctor that has the greatest years of experience.
 * If more doctors have the greatest value, the first one is returned.
 * Returns <code>null</code> if there are no <i>doctors</i>.
 *
 * @return the result
 */
@Override
public Doctor getDoctorWithMaximumExperience() {
    Doctor best = null;
    for (Doctor t : getAll()) {
        if (best == null || t.getExperience() > best.getExperience()) {
            best = t;
        }
    }
    if (best == null) {
        return null;
    }
    return best;
}
```

### conference

```java
/**
 * Returns the talk that has the greatest average rating.
 * If more talks have the greatest value, the first one is returned.
 * Returns <code>null</code> if there are no <i>talks</i>.
 *
 * @return the result
 */
@Override
public Talk getTalkWithMaximumRating() {
    Talk best = null;
    for (Talk t : getAll()) {
        if (best == null || t.getRating() > best.getRating()) {
            best = t;
        }
    }
    if (best == null) {
        return null;
    }
    return best;
}
```

---

<a id="C1-most-nested"></a>

## 6. T with the most nested objects

Template `C1-most-nested`, algorithm: maximum selection.

**The algorithm.** Maximum selection by the size of the nested collection: the same steps as any maximum selection, comparing `t.getItems().size()` (or `length`) with the size of the best object's collection.

**Typical mistakes:**

* Using `>=` (or `<=`) instead of `>`: on ties the last object would win, but the documentation asks for the first one.
* With an array collection: the size is `length` (a field, no parentheses), not `size()`, and an array has no `contains()`; the `for`-each loop works the same way.
* Not reading the end of the documentation: if nothing matches, the method returns `null` or throws the given (checked) exception, which the method declares with `throws`.

| Sheet | Method |
| --- | --- |
| arcade | `Game getGameWithMostCharacters()` |
| campus | `Course getCourseWithMostStudents() throws EmptyResultException` |
| cinema | `Movie getMovieWithMostActors() throws EmptyResultException` |
| clinic | `Doctor getDoctorWithMostPatients()` |
| conference | `Talk getTalkWithMostAttendees()` |

### arcade

```java
/**
 * Returns the game that has the most characters.
 * If more games have the greatest count, the first one is returned.
 * Returns <code>null</code> if there are no <i>games</i>.
 *
 * @return the result
 */
@Override
public Game getGameWithMostCharacters() {
    Game best = null;
    for (Game t : getAll()) {
        if (best == null || t.getCharacters().length > best.getCharacters().length) {
            best = t;
        }
    }
    if (best == null) {
        return null;
    }
    return best;
}
```

### campus

```java
/**
 * Returns the course that has the most students.
 * If more courses have the greatest count, the first one is returned.
 *
 * @return the result
 * @throws EmptyResultException if there are no <i>courses</i>
 */
@Override
public Course getCourseWithMostStudents() throws EmptyResultException {
    Course best = null;
    for (Course t : getAll()) {
        if (best == null || t.getStudents().length > best.getStudents().length) {
            best = t;
        }
    }
    if (best == null) {
        throw new EmptyResultException();
    }
    return best;
}
```

### cinema

```java
/**
 * Returns the movie that has the most actors.
 * If more movies have the greatest count, the first one is returned.
 *
 * @return the result
 * @throws EmptyResultException if there are no <i>movies</i>
 */
@Override
public Movie getMovieWithMostActors() throws EmptyResultException {
    Movie best = null;
    for (Movie t : getAll()) {
        if (best == null || t.getActors().length > best.getActors().length) {
            best = t;
        }
    }
    if (best == null) {
        throw new EmptyResultException();
    }
    return best;
}
```

### clinic

```java
/**
 * Returns the doctor that has the most patients.
 * If more doctors have the greatest count, the first one is returned.
 * Returns <code>null</code> if there are no <i>doctors</i>.
 *
 * @return the result
 */
@Override
public Doctor getDoctorWithMostPatients() {
    Doctor best = null;
    for (Doctor t : getAll()) {
        if (best == null || t.getPatients().size() > best.getPatients().size()) {
            best = t;
        }
    }
    if (best == null) {
        return null;
    }
    return best;
}
```

### conference

```java
/**
 * Returns the talk that has the most attendees.
 * If more talks have the greatest count, the first one is returned.
 * Returns <code>null</code> if there are no <i>talks</i>.
 *
 * @return the result
 */
@Override
public Talk getTalkWithMostAttendees() {
    Talk best = null;
    for (Talk t : getAll()) {
        if (best == null || t.getAttendees().size() > best.getAttendees().size()) {
            best = t;
        }
    }
    if (best == null) {
        return null;
    }
    return best;
}
```

---

<a id="C1-max-nested"></a>

## 7. U with the greatest value of a numeric field

Template `C1-max-nested`, algorithm: maximum selection (flattened).

**The algorithm.** Maximum selection over all the nested objects: two loops, `best` (a nested object) starts as `null`, the first nested object replaces it, later only a strictly greater one. No nested objects at all: `null` or the documented exception.

**Typical mistakes:**

* Using `>=` (or `<=`) instead of `>`: on ties the last object would win, but the documentation asks for the first one.
* Selecting the top-level object of the greatest nested object: the result is the nested object itself.
* Not reading the end of the documentation: if nothing matches, the method returns `null` or throws the given (checked) exception, which the method declares with `throws`.

| Sheet | Method |
| --- | --- |
| arcade | `Game.Character getCharacterWithMaximumGames()` |
| campus | `Course.Student getStudentWithMaximumGrade() throws NotFoundException` |
| cinema | `Movie.Actor getActorWithMaximumAge() throws NoResultException` |
| clinic | `Doctor.Patient getPatientWithMaximumInsuranceNumber()` |
| conference | `Talk.Attendee getAttendeeWithMaximumAge() throws NotFoundException` |

### arcade

```java
/**
 * Returns the character that has the greatest number of games among all the characters.
 * If more characters have the greatest value, the first one is returned.
 * Returns <code>null</code> if there are no <i>characters</i>.
 *
 * @return the result
 */
@Override
public Game.Character getCharacterWithMaximumGames() {
    Game.Character best = null;
    for (Game t : getAll()) {
        for (Game.Character u : t.getCharacters()) {
            if (best == null || u.getGames() > best.getGames()) {
                best = u;
            }
        }
    }
    if (best == null) {
        return null;
    }
    return best;
}
```

### campus

```java
/**
 * Returns the student that has the greatest grade among all the students.
 * If more students have the greatest value, the first one is returned.
 *
 * @return the result
 * @throws NotFoundException if there are no <i>students</i>
 */
@Override
public Course.Student getStudentWithMaximumGrade() throws NotFoundException {
    Course.Student best = null;
    for (Course t : getAll()) {
        for (Course.Student u : t.getStudents()) {
            if (best == null || u.getGrade() > best.getGrade()) {
                best = u;
            }
        }
    }
    if (best == null) {
        throw new NotFoundException();
    }
    return best;
}
```

### cinema

```java
/**
 * Returns the actor that has the greatest age among all the actors.
 * If more actors have the greatest value, the first one is returned.
 *
 * @return the result
 * @throws NoResultException if there are no <i>actors</i>
 */
@Override
public Movie.Actor getActorWithMaximumAge() throws NoResultException {
    Movie.Actor best = null;
    for (Movie t : getAll()) {
        for (Movie.Actor u : t.getActors()) {
            if (best == null || u.getAge() > best.getAge()) {
                best = u;
            }
        }
    }
    if (best == null) {
        throw new NoResultException();
    }
    return best;
}
```

### clinic

```java
/**
 * Returns the patient that has the greatest insurance number among all the patients.
 * If more patients have the greatest value, the first one is returned.
 * Returns <code>null</code> if there are no <i>patients</i>.
 *
 * @return the result
 */
@Override
public Doctor.Patient getPatientWithMaximumInsuranceNumber() {
    Doctor.Patient best = null;
    for (Doctor t : getAll()) {
        for (Doctor.Patient u : t.getPatients()) {
            if (best == null || u.getInsuranceNumber() > best.getInsuranceNumber()) {
                best = u;
            }
        }
    }
    if (best == null) {
        return null;
    }
    return best;
}
```

### conference

```java
/**
 * Returns the attendee that has the greatest age among all the attendees.
 * If more attendees have the greatest value, the first one is returned.
 *
 * @return the result
 * @throws NotFoundException if there are no <i>attendees</i>
 */
@Override
public Talk.Attendee getAttendeeWithMaximumAge() throws NotFoundException {
    Talk.Attendee best = null;
    for (Talk t : getAll()) {
        for (Talk.Attendee u : t.getAttendees()) {
            if (best == null || u.getAge() > best.getAge()) {
                best = u;
            }
        }
    }
    if (best == null) {
        throw new NotFoundException();
    }
    return best;
}
```
