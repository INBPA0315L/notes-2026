[↑ Tutorials](README.md)

# `AggregationQueries`

The interface of the queries that return a single (atomic) value. Every sample sheet is shown: *arcade*, *campus*, *cinema*, *clinic*, *conference*.

The result is a single number, string or `boolean`, built in one variable while looping over the objects (two nested loops for the nested objects). The typical patterns: **count** (`result++` under a condition), **sum** (`result +=`), **minimum/maximum** (a `found` flag: the first value is taken unconditionally, later only a smaller/greater one), **average** (a sum and a count, then a division), and **decision** (a `boolean` flag): no collection is needed. If the result does not exist (a minimum, a maximum or an average of nothing), the method throws the exception named in its documentation.

| | Task | Algorithm |
| --- | --- | --- |
| 1 | [greatest size of the nested collection](#A-max-count) | maximum |
| 2 | [smallest size of the nested collection](#A-min-count) | minimum |
| 3 | [total number of U of the T with the given enum value](#A-total-count-by-enum) | sum (conditional) |
| 4 | [maximum of a numeric field of T](#A-max-top) | maximum |
| 5 | [minimum of a numeric field of T](#A-min-top) | minimum |
| 6 | [average of a numeric field of the T with the given enum value](#A-avg-by-enum) | average (conditional) |
| 7 | [maximum of a numeric field of U](#A-max-nested) | maximum |
| 8 | [average of a numeric field of the U of the T with the given enum value](#A-avg-nested-by-enum) | average (conditional) |
| 9 | [number of T with the given enum value](#A-count-by-enum) | count |
| 10 | [number of T whose numeric field is greater than the given value](#A-count-greater-than) | count |
| 11 | [number of T whose String field starts with the given prefix](#A-count-starting-with) | count |
| 12 | [number of T having any U with the given field value](#A-count-with-nested-field) | count |
| 13 | [number of T without any U](#A-count-without-nested) | count |
| 14 | [number of U with the given field value](#A-count-nested-by-field) | count |
| 15 | [sum of a numeric field of the T with the given enum value](#A-sum-by-enum) | sum (conditional) |
| 16 | [sum of a numeric field of the U with the given field value](#A-sum-nested-by-field) | sum (conditional) |
| 17 | [minimum of a numeric field of U](#A-min-nested) | minimum |
| 18 | [average size of the nested collection of the T with the given enum value](#A-avg-count-by-enum) | average (conditional) |
| 19 | [greatest length of a String field of T](#A-max-length) | maximum |
| 20 | [a String field of the T with the greatest numeric field](#A-field-of-max) | maximum selection |
| 21 | [decides whether any T has the given value of a numeric field](#A-exists-value) | decision |
| 22 | [decides whether any U has the given field value](#A-exists-nested-field) | decision |
| 23 | [decides whether every T has at least the given number of U](#A-all-have-nested) | decision (for all) |

---

<a id="A-max-count"></a>

## 1. greatest size of the nested collection

Template `A-max-count`, algorithm: maximum.

**The algorithm.** The maximum of the sizes of the nested collections. (1) Declare a `found` flag (`false`) and a `result`. (2) Loop over the top-level objects; if nothing has been found yet, or the size of the current object's collection is greater than the result, store the size and set the flag. (3) After the loop, if the flag is still `false`, there were no objects: the maximum does not exist, throw the documented exception. (4) Otherwise return the result.

**Typical mistakes:**

* Starting the result at `0` (or at `Integer.MAX_VALUE` for a minimum) instead of using a flag: with no values the method returns a made-up number, and a fixed start may be greater (smaller) than every real value.
* Returning `0` (or anything) when there are no values: the documentation asks for the exception. It is a checked exception, so the method declares it (`throws ...`) as the interface does.
* With an array collection: the size is `length` (a field, no parentheses), not `size()`, and an array has no `contains()`; the `for`-each loop works the same way.

| Sheet | Method |
| --- | --- |
| arcade | `int getMaximumCountOfCharacters() throws EmptyResultException` |
| campus | `int getMaximumCountOfStudents() throws EmptyResultException` |
| cinema | `int getMaximumCountOfActors() throws NotFoundException` |
| clinic | `int getMaximumCountOfPatients() throws NoResultException` |
| conference | `int getMaximumCountOfAttendees() throws NoResultException` |

### arcade

```java
/**
 * Returns the greatest count of characters of a game.
 *
 * @return the result
 * @throws EmptyResultException if there are no <i>games</i>
 */
@Override
public int getMaximumCountOfCharacters() throws EmptyResultException {
    boolean found = false;
    int result = 0;
    for (Game t : getAll()) {
        if (!found || t.getCharacters().length > result) {
            result = t.getCharacters().length;
            found = true;
        }
    }
    if (!found) {
        throw new EmptyResultException();
    }
    return result;
}
```

### campus

```java
/**
 * Returns the greatest count of students of a course.
 *
 * @return the result
 * @throws EmptyResultException if there are no <i>courses</i>
 */
@Override
public int getMaximumCountOfStudents() throws EmptyResultException {
    boolean found = false;
    int result = 0;
    for (Course t : getAll()) {
        if (!found || t.getStudents().length > result) {
            result = t.getStudents().length;
            found = true;
        }
    }
    if (!found) {
        throw new EmptyResultException();
    }
    return result;
}
```

### cinema

```java
/**
 * Returns the greatest count of actors of a movie.
 *
 * @return the result
 * @throws NotFoundException if there are no <i>movies</i>
 */
@Override
public int getMaximumCountOfActors() throws NotFoundException {
    boolean found = false;
    int result = 0;
    for (Movie t : getAll()) {
        if (!found || t.getActors().length > result) {
            result = t.getActors().length;
            found = true;
        }
    }
    if (!found) {
        throw new NotFoundException();
    }
    return result;
}
```

### clinic

```java
/**
 * Returns the greatest count of patients of a doctor.
 *
 * @return the result
 * @throws NoResultException if there are no <i>doctors</i>
 */
@Override
public int getMaximumCountOfPatients() throws NoResultException {
    boolean found = false;
    int result = 0;
    for (Doctor t : getAll()) {
        if (!found || t.getPatients().size() > result) {
            result = t.getPatients().size();
            found = true;
        }
    }
    if (!found) {
        throw new NoResultException();
    }
    return result;
}
```

### conference

```java
/**
 * Returns the greatest count of attendees of a talk.
 *
 * @return the result
 * @throws NoResultException if there are no <i>talks</i>
 */
@Override
public int getMaximumCountOfAttendees() throws NoResultException {
    boolean found = false;
    int result = 0;
    for (Talk t : getAll()) {
        if (!found || t.getAttendees().size() > result) {
            result = t.getAttendees().size();
            found = true;
        }
    }
    if (!found) {
        throw new NoResultException();
    }
    return result;
}
```

---

<a id="A-min-count"></a>

## 2. smallest size of the nested collection

Template `A-min-count`, algorithm: minimum.

**The algorithm.** The minimum of the sizes of the nested collections, with the same steps as the maximum but with `<`: the first size is taken unconditionally (the flag is `false`), a later one only if it is smaller. With no objects the documented exception.

**Typical mistakes:**

* Starting the result at `0` (or at `Integer.MAX_VALUE` for a minimum) instead of using a flag: with no values the method returns a made-up number, and a fixed start may be greater (smaller) than every real value.
* Returning `0` (or anything) when there are no values: the documentation asks for the exception. It is a checked exception, so the method declares it (`throws ...`) as the interface does.
* Using `>` (copied from a maximum): the result would be the maximum.

| Sheet | Method |
| --- | --- |
| arcade | `int getMinimumCountOfCharacters() throws NoResultException` |
| campus | `int getMinimumCountOfStudents() throws NotFoundException` |
| cinema | `int getMinimumCountOfActors() throws NotFoundException` |
| clinic | `int getMinimumCountOfPatients() throws NotFoundException` |
| conference | `int getMinimumCountOfAttendees() throws EmptyResultException` |

### arcade

```java
/**
 * Returns the smallest count of characters of a game.
 *
 * @return the result
 * @throws NoResultException if there are no <i>games</i>
 */
@Override
public int getMinimumCountOfCharacters() throws NoResultException {
    boolean found = false;
    int result = 0;
    for (Game t : getAll()) {
        if (!found || t.getCharacters().length < result) {
            result = t.getCharacters().length;
            found = true;
        }
    }
    if (!found) {
        throw new NoResultException();
    }
    return result;
}
```

### campus

```java
/**
 * Returns the smallest count of students of a course.
 *
 * @return the result
 * @throws NotFoundException if there are no <i>courses</i>
 */
@Override
public int getMinimumCountOfStudents() throws NotFoundException {
    boolean found = false;
    int result = 0;
    for (Course t : getAll()) {
        if (!found || t.getStudents().length < result) {
            result = t.getStudents().length;
            found = true;
        }
    }
    if (!found) {
        throw new NotFoundException();
    }
    return result;
}
```

### cinema

```java
/**
 * Returns the smallest count of actors of a movie.
 *
 * @return the result
 * @throws NotFoundException if there are no <i>movies</i>
 */
@Override
public int getMinimumCountOfActors() throws NotFoundException {
    boolean found = false;
    int result = 0;
    for (Movie t : getAll()) {
        if (!found || t.getActors().length < result) {
            result = t.getActors().length;
            found = true;
        }
    }
    if (!found) {
        throw new NotFoundException();
    }
    return result;
}
```

### clinic

```java
/**
 * Returns the smallest count of patients of a doctor.
 *
 * @return the result
 * @throws NotFoundException if there are no <i>doctors</i>
 */
@Override
public int getMinimumCountOfPatients() throws NotFoundException {
    boolean found = false;
    int result = 0;
    for (Doctor t : getAll()) {
        if (!found || t.getPatients().size() < result) {
            result = t.getPatients().size();
            found = true;
        }
    }
    if (!found) {
        throw new NotFoundException();
    }
    return result;
}
```

### conference

```java
/**
 * Returns the smallest count of attendees of a talk.
 *
 * @return the result
 * @throws EmptyResultException if there are no <i>talks</i>
 */
@Override
public int getMinimumCountOfAttendees() throws EmptyResultException {
    boolean found = false;
    int result = 0;
    for (Talk t : getAll()) {
        if (!found || t.getAttendees().size() < result) {
            result = t.getAttendees().size();
            found = true;
        }
    }
    if (!found) {
        throw new EmptyResultException();
    }
    return result;
}
```

---

<a id="A-total-count-by-enum"></a>

## 3. total number of U of the T with the given enum value

Template `A-total-count-by-enum`, algorithm: sum (conditional).

**The algorithm.** A conditional sum. (1) `result` starts at `0`. (2) Loop over the top-level objects; for an object with the given enum value (`==`), add the size of its nested collection. (3) Return the result; it is `0` if no object has that value, which is a valid answer here (a sum of nothing is zero).

**Typical mistakes:**

* Comparing the enum field with the JSON text of the value (e.g. `"Neo Geo"`) or with `toString()`: the field holds an enum constant; compare it with the parameter by `==`.
* Counting the objects (`result++`) instead of adding the sizes of their collections.
* With an array collection: the size is `length` (a field, no parentheses), not `size()`, and an array has no `contains()`; the `for`-each loop works the same way.

| Sheet | Method |
| --- | --- |
| arcade | `int getTotalCountOfCharactersFilterByPlatform(Game.Platform platform)` |
| campus | `int getTotalCountOfStudentsFilterByFaculty(Course.Faculty faculty)` |
| cinema | `int getTotalCountOfActorsFilterByStudio(Movie.Studio studio)` |
| clinic | `int getTotalCountOfPatientsFilterByDepartment(Doctor.Department department)` |
| conference | `int getTotalCountOfAttendeesFilterByTrack(Talk.Track track)` |

### arcade

```java
/**
 * Returns the total count of characters of the games that have the given platform.
 *
 * @param platform the platform
 * @return the result
 */
@Override
public int getTotalCountOfCharactersFilterByPlatform(@NonNull Game.Platform platform) {
    int result = 0;
    for (Game t : getAll()) {
        if (t.getPlatform() == platform) {
            result += t.getCharacters().length;
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns the total count of students of the courses that have the given faculty.
 *
 * @param faculty the faculty
 * @return the result
 */
@Override
public int getTotalCountOfStudentsFilterByFaculty(@NonNull Course.Faculty faculty) {
    int result = 0;
    for (Course t : getAll()) {
        if (t.getFaculty() == faculty) {
            result += t.getStudents().length;
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns the total count of actors of the movies that have the given studio.
 *
 * @param studio the studio
 * @return the result
 */
@Override
public int getTotalCountOfActorsFilterByStudio(@NonNull Movie.Studio studio) {
    int result = 0;
    for (Movie t : getAll()) {
        if (t.getStudio() == studio) {
            result += t.getActors().length;
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns the total count of patients of the doctors that have the given department.
 *
 * @param department the department
 * @return the result
 */
@Override
public int getTotalCountOfPatientsFilterByDepartment(@NonNull Doctor.Department department) {
    int result = 0;
    for (Doctor t : getAll()) {
        if (t.getDepartment() == department) {
            result += t.getPatients().size();
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns the total count of attendees of the talks that have the given track.
 *
 * @param track the track
 * @return the result
 */
@Override
public int getTotalCountOfAttendeesFilterByTrack(@NonNull Talk.Track track) {
    int result = 0;
    for (Talk t : getAll()) {
        if (t.getTrack() == track) {
            result += t.getAttendees().size();
        }
    }
    return result;
}
```

---

<a id="A-max-top"></a>

## 4. maximum of a numeric field of T

Template `A-max-top`, algorithm: maximum.

**The algorithm.** The maximum of a numeric field of the top-level objects. (1) A `found` flag and a `result` of the field's type. (2) In the loop the first value is taken unconditionally, later ones only if greater. (3) No objects: the documented exception; otherwise the result.

**Typical mistakes:**

* Starting the result at `0` (or at `Integer.MAX_VALUE` for a minimum) instead of using a flag: with no values the method returns a made-up number, and a fixed start may be greater (smaller) than every real value.
* Returning `0` (or anything) when there are no values: the documentation asks for the exception. It is a checked exception, so the method declares it (`throws ...`) as the interface does.
* Declaring the result as `int` for a `long` or `double` field: the value does not fit or loses its fraction.

| Sheet | Method |
| --- | --- |
| arcade | `int getMaximumOfReleased() throws NoResultException` |
| campus | `int getMaximumOfSemester() throws EmptyResultException` |
| cinema | `int getMaximumOfYear() throws EmptyResultException` |
| clinic | `int getMaximumOfExperience() throws NotFoundException` |
| conference | `double getMaximumOfRating() throws NoResultException` |

### arcade

```java
/**
 * Returns the greatest release year of the games.
 *
 * @return the result
 * @throws NoResultException if there are no <i>games</i>
 */
@Override
public int getMaximumOfReleased() throws NoResultException {
    boolean found = false;
    int result = 0;
    for (Game t : getAll()) {
        if (!found || t.getReleased() > result) {
            result = t.getReleased();
            found = true;
        }
    }
    if (!found) {
        throw new NoResultException();
    }
    return result;
}
```

### campus

```java
/**
 * Returns the greatest recommended semester of the courses.
 *
 * @return the result
 * @throws EmptyResultException if there are no <i>courses</i>
 */
@Override
public int getMaximumOfSemester() throws EmptyResultException {
    boolean found = false;
    int result = 0;
    for (Course t : getAll()) {
        if (!found || t.getSemester() > result) {
            result = t.getSemester();
            found = true;
        }
    }
    if (!found) {
        throw new EmptyResultException();
    }
    return result;
}
```

### cinema

```java
/**
 * Returns the greatest release year of the movies.
 *
 * @return the result
 * @throws EmptyResultException if there are no <i>movies</i>
 */
@Override
public int getMaximumOfYear() throws EmptyResultException {
    boolean found = false;
    int result = 0;
    for (Movie t : getAll()) {
        if (!found || t.getYear() > result) {
            result = t.getYear();
            found = true;
        }
    }
    if (!found) {
        throw new EmptyResultException();
    }
    return result;
}
```

### clinic

```java
/**
 * Returns the greatest years of experience of the doctors.
 *
 * @return the result
 * @throws NotFoundException if there are no <i>doctors</i>
 */
@Override
public int getMaximumOfExperience() throws NotFoundException {
    boolean found = false;
    int result = 0;
    for (Doctor t : getAll()) {
        if (!found || t.getExperience() > result) {
            result = t.getExperience();
            found = true;
        }
    }
    if (!found) {
        throw new NotFoundException();
    }
    return result;
}
```

### conference

```java
/**
 * Returns the greatest average rating of the talks.
 *
 * @return the result
 * @throws NoResultException if there are no <i>talks</i>
 */
@Override
public double getMaximumOfRating() throws NoResultException {
    boolean found = false;
    double result = 0.0;
    for (Talk t : getAll()) {
        if (!found || t.getRating() > result) {
            result = t.getRating();
            found = true;
        }
    }
    if (!found) {
        throw new NoResultException();
    }
    return result;
}
```

---

<a id="A-min-top"></a>

## 5. minimum of a numeric field of T

Template `A-min-top`, algorithm: minimum.

**The algorithm.** The minimum of a numeric field of the top-level objects: as the maximum, with `<`. No objects: the documented exception.

**Typical mistakes:**

* Starting the result at `0` (or at `Integer.MAX_VALUE` for a minimum) instead of using a flag: with no values the method returns a made-up number, and a fixed start may be greater (smaller) than every real value.
* Returning `0` (or anything) when there are no values: the documentation asks for the exception. It is a checked exception, so the method declares it (`throws ...`) as the interface does.

| Sheet | Method |
| --- | --- |
| arcade | `int getMinimumOfReleased() throws NoResultException` |
| campus | `int getMinimumOfSemester() throws NotFoundException` |
| cinema | `int getMinimumOfYear() throws EmptyResultException` |
| clinic | `int getMinimumOfExperience() throws NoResultException` |
| conference | `double getMinimumOfRating() throws EmptyResultException` |

### arcade

```java
/**
 * Returns the smallest release year of the games.
 *
 * @return the result
 * @throws NoResultException if there are no <i>games</i>
 */
@Override
public int getMinimumOfReleased() throws NoResultException {
    boolean found = false;
    int result = 0;
    for (Game t : getAll()) {
        if (!found || t.getReleased() < result) {
            result = t.getReleased();
            found = true;
        }
    }
    if (!found) {
        throw new NoResultException();
    }
    return result;
}
```

### campus

```java
/**
 * Returns the smallest recommended semester of the courses.
 *
 * @return the result
 * @throws NotFoundException if there are no <i>courses</i>
 */
@Override
public int getMinimumOfSemester() throws NotFoundException {
    boolean found = false;
    int result = 0;
    for (Course t : getAll()) {
        if (!found || t.getSemester() < result) {
            result = t.getSemester();
            found = true;
        }
    }
    if (!found) {
        throw new NotFoundException();
    }
    return result;
}
```

### cinema

```java
/**
 * Returns the smallest release year of the movies.
 *
 * @return the result
 * @throws EmptyResultException if there are no <i>movies</i>
 */
@Override
public int getMinimumOfYear() throws EmptyResultException {
    boolean found = false;
    int result = 0;
    for (Movie t : getAll()) {
        if (!found || t.getYear() < result) {
            result = t.getYear();
            found = true;
        }
    }
    if (!found) {
        throw new EmptyResultException();
    }
    return result;
}
```

### clinic

```java
/**
 * Returns the smallest years of experience of the doctors.
 *
 * @return the result
 * @throws NoResultException if there are no <i>doctors</i>
 */
@Override
public int getMinimumOfExperience() throws NoResultException {
    boolean found = false;
    int result = 0;
    for (Doctor t : getAll()) {
        if (!found || t.getExperience() < result) {
            result = t.getExperience();
            found = true;
        }
    }
    if (!found) {
        throw new NoResultException();
    }
    return result;
}
```

### conference

```java
/**
 * Returns the smallest average rating of the talks.
 *
 * @return the result
 * @throws EmptyResultException if there are no <i>talks</i>
 */
@Override
public double getMinimumOfRating() throws EmptyResultException {
    boolean found = false;
    double result = 0.0;
    for (Talk t : getAll()) {
        if (!found || t.getRating() < result) {
            result = t.getRating();
            found = true;
        }
    }
    if (!found) {
        throw new EmptyResultException();
    }
    return result;
}
```

---

<a id="A-avg-by-enum"></a>

## 6. average of a numeric field of the T with the given enum value

Template `A-avg-by-enum`, algorithm: average (conditional).

**The algorithm.** An average is a sum divided by a count. (1) A `double sum` and an `int count`, both `0`. (2) In the loop, for an object with the given enum value, add its field to the sum and increase the count. (3) After the loop, if the count is `0`, no object was selected: the average does not exist, throw the documented exception (instead of dividing by zero). (4) Return `sum / count`.

**Typical mistakes:**

* Integer division: `sum / count` with two `int` operands drops the fraction (7 / 2 is 3). Make the sum a `double`, or cast: `(double) sum / count`.
* Returning `0` (or anything) when there are no values: the documentation asks for the exception. It is a checked exception, so the method declares it (`throws ...`) as the interface does.
* Dividing by the number of all the objects instead of the selected ones.
* Comparing the enum field with the JSON text of the value (e.g. `"Neo Geo"`) or with `toString()`: the field holds an enum constant; compare it with the parameter by `==`.

| Sheet | Method |
| --- | --- |
| arcade | `double getAverageOfReleasedFilterByPlatform(Game.Platform platform) throws EmptyResultException` |
| campus | `double getAverageOfCreditsFilterByFaculty(Course.Faculty faculty) throws NotFoundException` |
| cinema | `double getAverageOfYearFilterByStudio(Movie.Studio studio) throws NotFoundException` |
| clinic | `double getAverageOfExperienceFilterByDepartment(Doctor.Department department) throws NotFoundException` |
| conference | `double getAverageOfRatingFilterByTrack(Talk.Track track) throws NotFoundException` |

### arcade

```java
/**
 * Returns the average release year of the games that have the given platform.
 *
 * @param platform the platform
 * @return the result
 * @throws EmptyResultException if no <i>game</i> has the given platform
 */
@Override
public double getAverageOfReleasedFilterByPlatform(@NonNull Game.Platform platform) throws EmptyResultException {
    double sum = 0;
    int count = 0;
    for (Game t : getAll()) {
        if (t.getPlatform() == platform) {
            sum += t.getReleased();
            count++;
        }
    }
    if (count == 0) {
        throw new EmptyResultException();
    }
    return sum / count;
}
```

### campus

```java
/**
 * Returns the average credits of the courses that have the given faculty.
 *
 * @param faculty the faculty
 * @return the result
 * @throws NotFoundException if no <i>course</i> has the given faculty
 */
@Override
public double getAverageOfCreditsFilterByFaculty(@NonNull Course.Faculty faculty) throws NotFoundException {
    double sum = 0;
    int count = 0;
    for (Course t : getAll()) {
        if (t.getFaculty() == faculty) {
            sum += t.getCredits();
            count++;
        }
    }
    if (count == 0) {
        throw new NotFoundException();
    }
    return sum / count;
}
```

### cinema

```java
/**
 * Returns the average release year of the movies that have the given studio.
 *
 * @param studio the studio
 * @return the result
 * @throws NotFoundException if no <i>movie</i> has the given studio
 */
@Override
public double getAverageOfYearFilterByStudio(@NonNull Movie.Studio studio) throws NotFoundException {
    double sum = 0;
    int count = 0;
    for (Movie t : getAll()) {
        if (t.getStudio() == studio) {
            sum += t.getYear();
            count++;
        }
    }
    if (count == 0) {
        throw new NotFoundException();
    }
    return sum / count;
}
```

### clinic

```java
/**
 * Returns the average years of experience of the doctors that have the given department.
 *
 * @param department the department
 * @return the result
 * @throws NotFoundException if no <i>doctor</i> has the given department
 */
@Override
public double getAverageOfExperienceFilterByDepartment(@NonNull Doctor.Department department) throws NotFoundException {
    double sum = 0;
    int count = 0;
    for (Doctor t : getAll()) {
        if (t.getDepartment() == department) {
            sum += t.getExperience();
            count++;
        }
    }
    if (count == 0) {
        throw new NotFoundException();
    }
    return sum / count;
}
```

### conference

```java
/**
 * Returns the average average rating of the talks that have the given track.
 *
 * @param track the track
 * @return the result
 * @throws NotFoundException if no <i>talk</i> has the given track
 */
@Override
public double getAverageOfRatingFilterByTrack(@NonNull Talk.Track track) throws NotFoundException {
    double sum = 0;
    int count = 0;
    for (Talk t : getAll()) {
        if (t.getTrack() == track) {
            sum += t.getRating();
            count++;
        }
    }
    if (count == 0) {
        throw new NotFoundException();
    }
    return sum / count;
}
```

---

<a id="A-max-nested"></a>

## 7. maximum of a numeric field of U

Template `A-max-nested`, algorithm: maximum.

**The algorithm.** The maximum of a numeric field of all the nested objects. (1) A `found` flag and a `result`. (2) Two loops: over the top-level objects, and inside over their nested collections; the first nested value is taken unconditionally, later ones only if greater. (3) If no nested object was seen (no top-level objects, or only empty collections), throw the documented exception.

**Typical mistakes:**

* Starting the result at `0` (or at `Integer.MAX_VALUE` for a minimum) instead of using a flag: with no values the method returns a made-up number, and a fixed start may be greater (smaller) than every real value.
* Returning `0` (or anything) when there are no values: the documentation asks for the exception. It is a checked exception, so the method declares it (`throws ...`) as the interface does.
* Checking only that there are top-level objects: they may all have empty collections, then there is still no value.

| Sheet | Method |
| --- | --- |
| arcade | `int getMaximumOfCharacterGames() throws NoResultException` |
| campus | `int getMaximumOfStudentGrade() throws NoResultException` |
| cinema | `int getMaximumOfActorAge() throws EmptyResultException` |
| clinic | `long getMaximumOfPatientInsuranceNumber() throws EmptyResultException` |
| conference | `int getMaximumOfAttendeeAge() throws NotFoundException` |

### arcade

```java
/**
 * Returns the greatest number of games of all the characters.
 *
 * @return the result
 * @throws NoResultException if there are no <i>characters</i>
 */
@Override
public int getMaximumOfCharacterGames() throws NoResultException {
    boolean found = false;
    int result = 0;
    for (Game t : getAll()) {
        for (Game.Character u : t.getCharacters()) {
            if (!found || u.getGames() > result) {
                result = u.getGames();
                found = true;
            }
        }
    }
    if (!found) {
        throw new NoResultException();
    }
    return result;
}
```

### campus

```java
/**
 * Returns the greatest grade of all the students.
 *
 * @return the result
 * @throws NoResultException if there are no <i>students</i>
 */
@Override
public int getMaximumOfStudentGrade() throws NoResultException {
    boolean found = false;
    int result = 0;
    for (Course t : getAll()) {
        for (Course.Student u : t.getStudents()) {
            if (!found || u.getGrade() > result) {
                result = u.getGrade();
                found = true;
            }
        }
    }
    if (!found) {
        throw new NoResultException();
    }
    return result;
}
```

### cinema

```java
/**
 * Returns the greatest age of all the actors.
 *
 * @return the result
 * @throws EmptyResultException if there are no <i>actors</i>
 */
@Override
public int getMaximumOfActorAge() throws EmptyResultException {
    boolean found = false;
    int result = 0;
    for (Movie t : getAll()) {
        for (Movie.Actor u : t.getActors()) {
            if (!found || u.getAge() > result) {
                result = u.getAge();
                found = true;
            }
        }
    }
    if (!found) {
        throw new EmptyResultException();
    }
    return result;
}
```

### clinic

```java
/**
 * Returns the greatest insurance number of all the patients.
 *
 * @return the result
 * @throws EmptyResultException if there are no <i>patients</i>
 */
@Override
public long getMaximumOfPatientInsuranceNumber() throws EmptyResultException {
    boolean found = false;
    long result = 0L;
    for (Doctor t : getAll()) {
        for (Doctor.Patient u : t.getPatients()) {
            if (!found || u.getInsuranceNumber() > result) {
                result = u.getInsuranceNumber();
                found = true;
            }
        }
    }
    if (!found) {
        throw new EmptyResultException();
    }
    return result;
}
```

### conference

```java
/**
 * Returns the greatest age of all the attendees.
 *
 * @return the result
 * @throws NotFoundException if there are no <i>attendees</i>
 */
@Override
public int getMaximumOfAttendeeAge() throws NotFoundException {
    boolean found = false;
    int result = 0;
    for (Talk t : getAll()) {
        for (Talk.Attendee u : t.getAttendees()) {
            if (!found || u.getAge() > result) {
                result = u.getAge();
                found = true;
            }
        }
    }
    if (!found) {
        throw new NotFoundException();
    }
    return result;
}
```

---

<a id="A-avg-nested-by-enum"></a>

## 8. average of a numeric field of the U of the T with the given enum value

Template `A-avg-nested-by-enum`, algorithm: average (conditional).

**The algorithm.** The average of a field of the nested objects of the selected top-level objects. (1) `double sum`, `int count`. (2) In the outer loop select the top-level objects by the enum value; in the inner loop add the field of every nested object and count them. (3) If the count is `0`, throw the documented exception; otherwise return `sum / count`.

**Typical mistakes:**

* Counting the top-level objects instead of the nested ones: the average is per nested object.
* Integer division: `sum / count` with two `int` operands drops the fraction (7 / 2 is 3). Make the sum a `double`, or cast: `(double) sum / count`.
* Returning `0` (or anything) when there are no values: the documentation asks for the exception. It is a checked exception, so the method declares it (`throws ...`) as the interface does.

| Sheet | Method |
| --- | --- |
| arcade | `double getAverageOfCharacterGamesFilterByPlatform(Game.Platform platform) throws NotFoundException` |
| campus | `double getAverageOfStudentGradeFilterByFaculty(Course.Faculty faculty) throws EmptyResultException` |
| cinema | `double getAverageOfActorAgeFilterByStudio(Movie.Studio studio) throws EmptyResultException` |
| clinic | `double getAverageOfPatientInsuranceNumberFilterByDepartment(Doctor.Department department) throws NotFoundException` |
| conference | `double getAverageOfAttendeeAgeFilterByTrack(Talk.Track track) throws EmptyResultException` |

### arcade

```java
/**
 * Returns the average number of games of the characters of the games that have the given platform.
 *
 * @param platform the platform
 * @return the result
 * @throws NotFoundException if the <i>games</i> having the given platform have no <i>characters</i>
 */
@Override
public double getAverageOfCharacterGamesFilterByPlatform(@NonNull Game.Platform platform) throws NotFoundException {
    double sum = 0;
    int count = 0;
    for (Game t : getAll()) {
        if (t.getPlatform() == platform) {
            for (Game.Character u : t.getCharacters()) {
                sum += u.getGames();
                count++;
            }
        }
    }
    if (count == 0) {
        throw new NotFoundException();
    }
    return sum / count;
}
```

### campus

```java
/**
 * Returns the average grade of the students of the courses that have the given faculty.
 *
 * @param faculty the faculty
 * @return the result
 * @throws EmptyResultException if the <i>courses</i> having the given faculty have no <i>students</i>
 */
@Override
public double getAverageOfStudentGradeFilterByFaculty(@NonNull Course.Faculty faculty) throws EmptyResultException {
    double sum = 0;
    int count = 0;
    for (Course t : getAll()) {
        if (t.getFaculty() == faculty) {
            for (Course.Student u : t.getStudents()) {
                sum += u.getGrade();
                count++;
            }
        }
    }
    if (count == 0) {
        throw new EmptyResultException();
    }
    return sum / count;
}
```

### cinema

```java
/**
 * Returns the average age of the actors of the movies that have the given studio.
 *
 * @param studio the studio
 * @return the result
 * @throws EmptyResultException if the <i>movies</i> having the given studio have no <i>actors</i>
 */
@Override
public double getAverageOfActorAgeFilterByStudio(@NonNull Movie.Studio studio) throws EmptyResultException {
    double sum = 0;
    int count = 0;
    for (Movie t : getAll()) {
        if (t.getStudio() == studio) {
            for (Movie.Actor u : t.getActors()) {
                sum += u.getAge();
                count++;
            }
        }
    }
    if (count == 0) {
        throw new EmptyResultException();
    }
    return sum / count;
}
```

### clinic

```java
/**
 * Returns the average insurance number of the patients of the doctors that have the given department.
 *
 * @param department the department
 * @return the result
 * @throws NotFoundException if the <i>doctors</i> having the given department have no <i>patients</i>
 */
@Override
public double getAverageOfPatientInsuranceNumberFilterByDepartment(@NonNull Doctor.Department department) throws NotFoundException {
    double sum = 0;
    int count = 0;
    for (Doctor t : getAll()) {
        if (t.getDepartment() == department) {
            for (Doctor.Patient u : t.getPatients()) {
                sum += u.getInsuranceNumber();
                count++;
            }
        }
    }
    if (count == 0) {
        throw new NotFoundException();
    }
    return sum / count;
}
```

### conference

```java
/**
 * Returns the average age of the attendees of the talks that have the given track.
 *
 * @param track the track
 * @return the result
 * @throws EmptyResultException if the <i>talks</i> having the given track have no <i>attendees</i>
 */
@Override
public double getAverageOfAttendeeAgeFilterByTrack(@NonNull Talk.Track track) throws EmptyResultException {
    double sum = 0;
    int count = 0;
    for (Talk t : getAll()) {
        if (t.getTrack() == track) {
            for (Talk.Attendee u : t.getAttendees()) {
                sum += u.getAge();
                count++;
            }
        }
    }
    if (count == 0) {
        throw new EmptyResultException();
    }
    return sum / count;
}
```

---

<a id="A-count-by-enum"></a>

## 9. number of T with the given enum value

Template `A-count-by-enum`, algorithm: count.

**The algorithm.** Counting with a condition: `result` starts at `0` and grows by one for every top-level object with the given enum value.

**Typical mistakes:**

* Comparing the enum field with the JSON text of the value (e.g. `"Neo Geo"`) or with `toString()`: the field holds an enum constant; compare it with the parameter by `==`.
* Returning the size of `getAll()`: that counts every object, without the condition.

| Sheet | Method |
| --- | --- |
| arcade | `int getCountOfGamesFilterByPlatform(Game.Platform platform)` |
| campus | `int getCountOfCoursesFilterByFaculty(Course.Faculty faculty)` |
| cinema | `int getCountOfMoviesFilterByStudio(Movie.Studio studio)` |
| clinic | `int getCountOfDoctorsFilterByDepartment(Doctor.Department department)` |
| conference | `int getCountOfTalksFilterByTrack(Talk.Track track)` |

### arcade

```java
/**
 * Returns the number of games that have the given platform.
 *
 * @param platform the platform
 * @return the result
 */
@Override
public int getCountOfGamesFilterByPlatform(@NonNull Game.Platform platform) {
    int result = 0;
    for (Game t : getAll()) {
        if (t.getPlatform() == platform) {
            result++;
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns the number of courses that have the given faculty.
 *
 * @param faculty the faculty
 * @return the result
 */
@Override
public int getCountOfCoursesFilterByFaculty(@NonNull Course.Faculty faculty) {
    int result = 0;
    for (Course t : getAll()) {
        if (t.getFaculty() == faculty) {
            result++;
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns the number of movies that have the given studio.
 *
 * @param studio the studio
 * @return the result
 */
@Override
public int getCountOfMoviesFilterByStudio(@NonNull Movie.Studio studio) {
    int result = 0;
    for (Movie t : getAll()) {
        if (t.getStudio() == studio) {
            result++;
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns the number of doctors that have the given department.
 *
 * @param department the department
 * @return the result
 */
@Override
public int getCountOfDoctorsFilterByDepartment(@NonNull Doctor.Department department) {
    int result = 0;
    for (Doctor t : getAll()) {
        if (t.getDepartment() == department) {
            result++;
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns the number of talks that have the given track.
 *
 * @param track the track
 * @return the result
 */
@Override
public int getCountOfTalksFilterByTrack(@NonNull Talk.Track track) {
    int result = 0;
    for (Talk t : getAll()) {
        if (t.getTrack() == track) {
            result++;
        }
    }
    return result;
}
```

---

<a id="A-count-greater-than"></a>

## 10. number of T whose numeric field is greater than the given value

Template `A-count-greater-than`, algorithm: count.

**The algorithm.** Counting with a condition on a numeric field: the counter grows for every object whose field is strictly greater than the given value.

**Typical mistakes:**

* Using `>=` instead of `>`: *greater than* is strict, an object with the same value does not count.

| Sheet | Method |
| --- | --- |
| arcade | `int getCountOfGamesAboveReleasedLimit(int released)` |
| campus | `int getCountOfCoursesAboveCreditsLimit(int credits)` |
| cinema | `int getCountOfMoviesAboveYearLimit(int year)` |
| clinic | `int getCountOfDoctorsAboveExperienceLimit(int experience)` |
| conference | `int getCountOfTalksAboveRatingLimit(double rating)` |

### arcade

```java
/**
 * Returns the number of games whose release year is greater than the given value.
 *
 * @param released the release year of the game
 * @return the result
 */
@Override
public int getCountOfGamesAboveReleasedLimit(int released) {
    int result = 0;
    for (Game t : getAll()) {
        if (t.getReleased() > released) {
            result++;
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns the number of courses whose credits is greater than the given value.
 *
 * @param credits the credits of the course
 * @return the result
 */
@Override
public int getCountOfCoursesAboveCreditsLimit(int credits) {
    int result = 0;
    for (Course t : getAll()) {
        if (t.getCredits() > credits) {
            result++;
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns the number of movies whose release year is greater than the given value.
 *
 * @param year the release year of the movie
 * @return the result
 */
@Override
public int getCountOfMoviesAboveYearLimit(int year) {
    int result = 0;
    for (Movie t : getAll()) {
        if (t.getYear() > year) {
            result++;
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns the number of doctors whose years of experience is greater than the given value.
 *
 * @param experience the years of experience of the doctor
 * @return the result
 */
@Override
public int getCountOfDoctorsAboveExperienceLimit(int experience) {
    int result = 0;
    for (Doctor t : getAll()) {
        if (t.getExperience() > experience) {
            result++;
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns the number of talks whose average rating is greater than the given value.
 *
 * @param rating the average rating of the talk
 * @return the result
 */
@Override
public int getCountOfTalksAboveRatingLimit(double rating) {
    int result = 0;
    for (Talk t : getAll()) {
        if (t.getRating() > rating) {
            result++;
        }
    }
    return result;
}
```

---

<a id="A-count-starting-with"></a>

## 11. number of T whose String field starts with the given prefix

Template `A-count-starting-with`, algorithm: count.

**The algorithm.** Counting with a condition on a `String` field: `startsWith(prefix)` tells whether the text begins with the prefix (case-sensitive, as the documentation says).

**Typical mistakes:**

* Using `contains()` or `equals()`: `contains()` finds the text anywhere, `equals()` needs the whole text; the task is about the beginning.
* Converting to lower or upper case: the comparison is case-sensitive.

| Sheet | Method |
| --- | --- |
| arcade | `int getCountOfGamesByNamePrefix(String prefix)` |
| campus | `int getCountOfCoursesByTitlePrefix(String prefix)` |
| cinema | `int getCountOfMoviesByGenrePrefix(String prefix)` |
| clinic | `int getCountOfDoctorsByNamePrefix(String prefix)` |
| conference | `int getCountOfTalksByTitlePrefix(String prefix)` |

### arcade

```java
/**
 * Returns the number of games whose name starts with the given prefix (case-sensitive).
 *
 * @param prefix the prefix
 * @return the result
 */
@Override
public int getCountOfGamesByNamePrefix(@NonNull String prefix) {
    int result = 0;
    for (Game t : getAll()) {
        if (t.getName().startsWith(prefix)) {
            result++;
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns the number of courses whose title starts with the given prefix (case-sensitive).
 *
 * @param prefix the prefix
 * @return the result
 */
@Override
public int getCountOfCoursesByTitlePrefix(@NonNull String prefix) {
    int result = 0;
    for (Course t : getAll()) {
        if (t.getTitle().startsWith(prefix)) {
            result++;
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns the number of movies whose genre starts with the given prefix (case-sensitive).
 *
 * @param prefix the prefix
 * @return the result
 */
@Override
public int getCountOfMoviesByGenrePrefix(@NonNull String prefix) {
    int result = 0;
    for (Movie t : getAll()) {
        if (t.getGenre().startsWith(prefix)) {
            result++;
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns the number of doctors whose name starts with the given prefix (case-sensitive).
 *
 * @param prefix the prefix
 * @return the result
 */
@Override
public int getCountOfDoctorsByNamePrefix(@NonNull String prefix) {
    int result = 0;
    for (Doctor t : getAll()) {
        if (t.getName().startsWith(prefix)) {
            result++;
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns the number of talks whose title starts with the given prefix (case-sensitive).
 *
 * @param prefix the prefix
 * @return the result
 */
@Override
public int getCountOfTalksByTitlePrefix(@NonNull String prefix) {
    int result = 0;
    for (Talk t : getAll()) {
        if (t.getTitle().startsWith(prefix)) {
            result++;
        }
    }
    return result;
}
```

---

<a id="A-count-with-nested-field"></a>

## 12. number of T having any U with the given field value

Template `A-count-with-nested-field`, algorithm: count.

**The algorithm.** Counting the top-level objects that have *any* nested object with the given value. (1) `result` starts at `0`. (2) For every top-level object, a `contains` flag starts as `false`; the inner loop sets it if a nested object matches. (3) After the inner loop, if the flag is set, count the object once.

**Typical mistakes:**

* Acting inside the inner loop (counting, adding) for every matching nested object: a top-level object would be counted or added several times. Set a flag in the inner loop and act once, after it.
* Declaring the flag before the outer loop: it must start as `false` again for every top-level object, so declare it inside the outer loop.
* Comparing strings with `==`: it compares the references, not the texts, so a matching string may be missed. Use `equals()`.

| Sheet | Method |
| --- | --- |
| arcade | `int getCountOfGamesByCharacterName(String name)` |
| campus | `int getCountOfCoursesByStudentName(String name)` |
| cinema | `int getCountOfMoviesByActorName(String name)` |
| clinic | `int getCountOfDoctorsByPatientName(String name)` |
| conference | `int getCountOfTalksByAttendeeCompany(String company)` |

### arcade

```java
/**
 * Returns the number of games that have any character with the given name.
 *
 * @param name the name of the character
 * @return the result
 */
@Override
public int getCountOfGamesByCharacterName(@NonNull String name) {
    int result = 0;
    for (Game t : getAll()) {
        boolean contains = false;
        for (Game.Character u : t.getCharacters()) {
            if (u.getName().equals(name)) {
                contains = true;
            }
        }
        if (contains) {
            result++;
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns the number of courses that have any student with the given name.
 *
 * @param name the name of the student
 * @return the result
 */
@Override
public int getCountOfCoursesByStudentName(@NonNull String name) {
    int result = 0;
    for (Course t : getAll()) {
        boolean contains = false;
        for (Course.Student u : t.getStudents()) {
            if (u.getName().equals(name)) {
                contains = true;
            }
        }
        if (contains) {
            result++;
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns the number of movies that have any actor with the given name.
 *
 * @param name the name of the actor
 * @return the result
 */
@Override
public int getCountOfMoviesByActorName(@NonNull String name) {
    int result = 0;
    for (Movie t : getAll()) {
        boolean contains = false;
        for (Movie.Actor u : t.getActors()) {
            if (u.getName().equals(name)) {
                contains = true;
            }
        }
        if (contains) {
            result++;
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns the number of doctors that have any patient with the given name.
 *
 * @param name the name of the patient
 * @return the result
 */
@Override
public int getCountOfDoctorsByPatientName(@NonNull String name) {
    int result = 0;
    for (Doctor t : getAll()) {
        boolean contains = false;
        for (Doctor.Patient u : t.getPatients()) {
            if (u.getName().equals(name)) {
                contains = true;
            }
        }
        if (contains) {
            result++;
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns the number of talks that have any attendee with the given company.
 *
 * @param company the company of the attendee
 * @return the result
 */
@Override
public int getCountOfTalksByAttendeeCompany(@NonNull String company) {
    int result = 0;
    for (Talk t : getAll()) {
        boolean contains = false;
        for (Talk.Attendee u : t.getAttendees()) {
            if (u.getCompany().equals(company)) {
                contains = true;
            }
        }
        if (contains) {
            result++;
        }
    }
    return result;
}
```

---

<a id="A-count-without-nested"></a>

## 13. number of T without any U

Template `A-count-without-nested`, algorithm: count.

**The algorithm.** Counting the top-level objects whose nested collection is empty (its size is `0`).

**Typical mistakes:**

* Counting the nested objects, or the objects that *have* nested objects: the opposite condition.
* With an array collection: the size is `length` (a field, no parentheses), not `size()`, and an array has no `contains()`; the `for`-each loop works the same way.

| Sheet | Method |
| --- | --- |
| arcade | `int getCountOfGamesWithoutCharacters()` |
| campus | `int getCountOfCoursesWithoutStudents()` |
| cinema | `int getCountOfMoviesWithoutActors()` |
| clinic | `int getCountOfDoctorsWithoutPatients()` |
| conference | `int getCountOfTalksWithoutAttendees()` |

### arcade

```java
/**
 * Returns the number of games that do not have any characters.
 *
 * @return the result
 */
@Override
public int getCountOfGamesWithoutCharacters() {
    int result = 0;
    for (Game t : getAll()) {
        if (t.getCharacters().length == 0) {
            result++;
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns the number of courses that do not have any students.
 *
 * @return the result
 */
@Override
public int getCountOfCoursesWithoutStudents() {
    int result = 0;
    for (Course t : getAll()) {
        if (t.getStudents().length == 0) {
            result++;
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns the number of movies that do not have any actors.
 *
 * @return the result
 */
@Override
public int getCountOfMoviesWithoutActors() {
    int result = 0;
    for (Movie t : getAll()) {
        if (t.getActors().length == 0) {
            result++;
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns the number of doctors that do not have any patients.
 *
 * @return the result
 */
@Override
public int getCountOfDoctorsWithoutPatients() {
    int result = 0;
    for (Doctor t : getAll()) {
        if (t.getPatients().size() == 0) {
            result++;
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns the number of talks that do not have any attendees.
 *
 * @return the result
 */
@Override
public int getCountOfTalksWithoutAttendees() {
    int result = 0;
    for (Talk t : getAll()) {
        if (t.getAttendees().size() == 0) {
            result++;
        }
    }
    return result;
}
```

---

<a id="A-count-nested-by-field"></a>

## 14. number of U with the given field value

Template `A-count-nested-by-field`, algorithm: count.

**The algorithm.** Counting the nested objects with the given value of a field: two loops, and every matching nested object counts. The same nested object in two top-level objects counts twice (there is no duplicate check).

**Typical mistakes:**

* Comparing strings with `==`: it compares the references, not the texts, so a matching string may be missed. Use `equals()`.
* Counting the top-level objects that have a match: here every matching nested object counts.

| Sheet | Method |
| --- | --- |
| arcade | `int getCountOfCharactersByName(String name)` |
| campus | `int getCountOfStudentsByNeptun(String neptun)` |
| cinema | `int getCountOfActorsByName(String name)` |
| clinic | `int getCountOfPatientsByName(String name)` |
| conference | `int getCountOfAttendeesByCompany(String company)` |

### arcade

```java
/**
 * Returns the number of characters (counting each occurrence) that have the given name.
 *
 * @param name the name of the character
 * @return the result
 */
@Override
public int getCountOfCharactersByName(@NonNull String name) {
    int result = 0;
    for (Game t : getAll()) {
        for (Game.Character u : t.getCharacters()) {
            if (u.getName().equals(name)) {
                result++;
            }
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns the number of students (counting each occurrence) that have the given Neptun code.
 *
 * @param neptun the Neptun code of the student
 * @return the result
 */
@Override
public int getCountOfStudentsByNeptun(@NonNull String neptun) {
    int result = 0;
    for (Course t : getAll()) {
        for (Course.Student u : t.getStudents()) {
            if (u.getNeptun().equals(neptun)) {
                result++;
            }
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns the number of actors (counting each occurrence) that have the given name.
 *
 * @param name the name of the actor
 * @return the result
 */
@Override
public int getCountOfActorsByName(@NonNull String name) {
    int result = 0;
    for (Movie t : getAll()) {
        for (Movie.Actor u : t.getActors()) {
            if (u.getName().equals(name)) {
                result++;
            }
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns the number of patients (counting each occurrence) that have the given name.
 *
 * @param name the name of the patient
 * @return the result
 */
@Override
public int getCountOfPatientsByName(@NonNull String name) {
    int result = 0;
    for (Doctor t : getAll()) {
        for (Doctor.Patient u : t.getPatients()) {
            if (u.getName().equals(name)) {
                result++;
            }
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns the number of attendees (counting each occurrence) that have the given company.
 *
 * @param company the company of the attendee
 * @return the result
 */
@Override
public int getCountOfAttendeesByCompany(@NonNull String company) {
    int result = 0;
    for (Talk t : getAll()) {
        for (Talk.Attendee u : t.getAttendees()) {
            if (u.getCompany().equals(company)) {
                result++;
            }
        }
    }
    return result;
}
```

---

<a id="A-sum-by-enum"></a>

## 15. sum of a numeric field of the T with the given enum value

Template `A-sum-by-enum`, algorithm: sum (conditional).

**The algorithm.** A conditional sum of a numeric field: `result` (of the field's type) starts at `0`, and the field of every object with the given enum value is added. It is `0` if no object matches.

**Typical mistakes:**

* Comparing the enum field with the JSON text of the value (e.g. `"Neo Geo"`) or with `toString()`: the field holds an enum constant; compare it with the parameter by `==`.
* Declaring the sum as `int` for a `long` or `double` field.

| Sheet | Method |
| --- | --- |
| arcade | `int getSumOfReleasedFilterByPlatform(Game.Platform platform)` |
| campus | `int getSumOfSemesterFilterByFaculty(Course.Faculty faculty)` |
| cinema | `int getSumOfYearFilterByStudio(Movie.Studio studio)` |
| clinic | `int getSumOfExperienceFilterByDepartment(Doctor.Department department)` |
| conference | `double getSumOfRatingFilterByTrack(Talk.Track track)` |

### arcade

```java
/**
 * Returns the sum of the release year of the games that have the given platform.
 *
 * @param platform the platform
 * @return the result
 */
@Override
public int getSumOfReleasedFilterByPlatform(@NonNull Game.Platform platform) {
    int result = 0;
    for (Game t : getAll()) {
        if (t.getPlatform() == platform) {
            result += t.getReleased();
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns the sum of the recommended semester of the courses that have the given faculty.
 *
 * @param faculty the faculty
 * @return the result
 */
@Override
public int getSumOfSemesterFilterByFaculty(@NonNull Course.Faculty faculty) {
    int result = 0;
    for (Course t : getAll()) {
        if (t.getFaculty() == faculty) {
            result += t.getSemester();
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns the sum of the release year of the movies that have the given studio.
 *
 * @param studio the studio
 * @return the result
 */
@Override
public int getSumOfYearFilterByStudio(@NonNull Movie.Studio studio) {
    int result = 0;
    for (Movie t : getAll()) {
        if (t.getStudio() == studio) {
            result += t.getYear();
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns the sum of the years of experience of the doctors that have the given department.
 *
 * @param department the department
 * @return the result
 */
@Override
public int getSumOfExperienceFilterByDepartment(@NonNull Doctor.Department department) {
    int result = 0;
    for (Doctor t : getAll()) {
        if (t.getDepartment() == department) {
            result += t.getExperience();
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns the sum of the average rating of the talks that have the given track.
 *
 * @param track the track
 * @return the result
 */
@Override
public double getSumOfRatingFilterByTrack(@NonNull Talk.Track track) {
    double result = 0.0;
    for (Talk t : getAll()) {
        if (t.getTrack() == track) {
            result += t.getRating();
        }
    }
    return result;
}
```

---

<a id="A-sum-nested-by-field"></a>

## 16. sum of a numeric field of the U with the given field value

Template `A-sum-nested-by-field`, algorithm: sum (conditional).

**The algorithm.** A conditional sum over the nested objects: two loops, and a nested object whose one field equals the given value adds its other, numeric field to the result. *Counting each occurrence*: the same nested object in two top-level objects is added twice.

**Typical mistakes:**

* Comparing strings with `==`: it compares the references, not the texts, so a matching string may be missed. Use `equals()`.
* Skipping the repeated nested objects: the documentation asks to count each occurrence.
* Declaring the sum as `int` when the summed field is `long`: the method returns the field's type.

| Sheet | Method |
| --- | --- |
| arcade | `int getSumOfCharacterGamesByName(String name)` |
| campus | `int getSumOfStudentGradeByNeptun(String neptun)` |
| cinema | `int getSumOfActorAgeByName(String name)` |
| clinic | `int getSumOfPatientAgeByName(String name)` |
| conference | `int getSumOfAttendeeAgeByEmail(String email)` |

### arcade

```java
/**
 * Returns the sum of the number of games of the characters (counting each occurrence) that have the given name.
 *
 * @param name the name of the character
 * @return the result
 */
@Override
public int getSumOfCharacterGamesByName(@NonNull String name) {
    int result = 0;
    for (Game t : getAll()) {
        for (Game.Character u : t.getCharacters()) {
            if (u.getName().equals(name)) {
                result += u.getGames();
            }
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns the sum of the grade of the students (counting each occurrence) that have the given Neptun code.
 *
 * @param neptun the Neptun code of the student
 * @return the result
 */
@Override
public int getSumOfStudentGradeByNeptun(@NonNull String neptun) {
    int result = 0;
    for (Course t : getAll()) {
        for (Course.Student u : t.getStudents()) {
            if (u.getNeptun().equals(neptun)) {
                result += u.getGrade();
            }
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns the sum of the age of the actors (counting each occurrence) that have the given name.
 *
 * @param name the name of the actor
 * @return the result
 */
@Override
public int getSumOfActorAgeByName(@NonNull String name) {
    int result = 0;
    for (Movie t : getAll()) {
        for (Movie.Actor u : t.getActors()) {
            if (u.getName().equals(name)) {
                result += u.getAge();
            }
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns the sum of the age of the patients (counting each occurrence) that have the given name.
 *
 * @param name the name of the patient
 * @return the result
 */
@Override
public int getSumOfPatientAgeByName(@NonNull String name) {
    int result = 0;
    for (Doctor t : getAll()) {
        for (Doctor.Patient u : t.getPatients()) {
            if (u.getName().equals(name)) {
                result += u.getAge();
            }
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns the sum of the age of the attendees (counting each occurrence) that have the given e-mail address.
 *
 * @param email the e-mail address of the attendee
 * @return the result
 */
@Override
public int getSumOfAttendeeAgeByEmail(@NonNull String email) {
    int result = 0;
    for (Talk t : getAll()) {
        for (Talk.Attendee u : t.getAttendees()) {
            if (u.getEmail().equals(email)) {
                result += u.getAge();
            }
        }
    }
    return result;
}
```

---

<a id="A-min-nested"></a>

## 17. minimum of a numeric field of U

Template `A-min-nested`, algorithm: minimum.

**The algorithm.** The minimum of a numeric field of all the nested objects: two loops, a `found` flag (the first value is taken unconditionally, later only a smaller one), and the documented exception if no nested object was seen.

**Typical mistakes:**

* Starting the result at `0` (or at `Integer.MAX_VALUE` for a minimum) instead of using a flag: with no values the method returns a made-up number, and a fixed start may be greater (smaller) than every real value.
* Returning `0` (or anything) when there are no values: the documentation asks for the exception. It is a checked exception, so the method declares it (`throws ...`) as the interface does.

| Sheet | Method |
| --- | --- |
| arcade | `int getMinimumOfCharacterGames() throws NoResultException` |
| campus | `int getMinimumOfStudentGrade() throws EmptyResultException` |
| cinema | `int getMinimumOfActorAge() throws NotFoundException` |
| clinic | `long getMinimumOfPatientInsuranceNumber() throws NoResultException` |
| conference | `int getMinimumOfAttendeeAge() throws NoResultException` |

### arcade

```java
/**
 * Returns the smallest number of games of all the characters.
 *
 * @return the result
 * @throws NoResultException if there are no <i>characters</i>
 */
@Override
public int getMinimumOfCharacterGames() throws NoResultException {
    boolean found = false;
    int result = 0;
    for (Game t : getAll()) {
        for (Game.Character u : t.getCharacters()) {
            if (!found || u.getGames() < result) {
                result = u.getGames();
                found = true;
            }
        }
    }
    if (!found) {
        throw new NoResultException();
    }
    return result;
}
```

### campus

```java
/**
 * Returns the smallest grade of all the students.
 *
 * @return the result
 * @throws EmptyResultException if there are no <i>students</i>
 */
@Override
public int getMinimumOfStudentGrade() throws EmptyResultException {
    boolean found = false;
    int result = 0;
    for (Course t : getAll()) {
        for (Course.Student u : t.getStudents()) {
            if (!found || u.getGrade() < result) {
                result = u.getGrade();
                found = true;
            }
        }
    }
    if (!found) {
        throw new EmptyResultException();
    }
    return result;
}
```

### cinema

```java
/**
 * Returns the smallest age of all the actors.
 *
 * @return the result
 * @throws NotFoundException if there are no <i>actors</i>
 */
@Override
public int getMinimumOfActorAge() throws NotFoundException {
    boolean found = false;
    int result = 0;
    for (Movie t : getAll()) {
        for (Movie.Actor u : t.getActors()) {
            if (!found || u.getAge() < result) {
                result = u.getAge();
                found = true;
            }
        }
    }
    if (!found) {
        throw new NotFoundException();
    }
    return result;
}
```

### clinic

```java
/**
 * Returns the smallest insurance number of all the patients.
 *
 * @return the result
 * @throws NoResultException if there are no <i>patients</i>
 */
@Override
public long getMinimumOfPatientInsuranceNumber() throws NoResultException {
    boolean found = false;
    long result = 0L;
    for (Doctor t : getAll()) {
        for (Doctor.Patient u : t.getPatients()) {
            if (!found || u.getInsuranceNumber() < result) {
                result = u.getInsuranceNumber();
                found = true;
            }
        }
    }
    if (!found) {
        throw new NoResultException();
    }
    return result;
}
```

### conference

```java
/**
 * Returns the smallest age of all the attendees.
 *
 * @return the result
 * @throws NoResultException if there are no <i>attendees</i>
 */
@Override
public int getMinimumOfAttendeeAge() throws NoResultException {
    boolean found = false;
    int result = 0;
    for (Talk t : getAll()) {
        for (Talk.Attendee u : t.getAttendees()) {
            if (!found || u.getAge() < result) {
                result = u.getAge();
                found = true;
            }
        }
    }
    if (!found) {
        throw new NoResultException();
    }
    return result;
}
```

---

<a id="A-avg-count-by-enum"></a>

## 18. average size of the nested collection of the T with the given enum value

Template `A-avg-count-by-enum`, algorithm: average (conditional).

**The algorithm.** The average size of the nested collections of the selected top-level objects. (1) `int sum`, `int count`. (2) For every object with the given enum value, add the size of its collection and count the object. (3) No selected object: the documented exception. (4) Return `(double) sum / count`: the cast makes the division a floating-point one.

**Typical mistakes:**

* Integer division: `sum / count` with two `int` operands drops the fraction (7 / 2 is 3). Make the sum a `double`, or cast: `(double) sum / count`.
* Returning `0` (or anything) when there are no values: the documentation asks for the exception. It is a checked exception, so the method declares it (`throws ...`) as the interface does.
* Comparing the enum field with the JSON text of the value (e.g. `"Neo Geo"`) or with `toString()`: the field holds an enum constant; compare it with the parameter by `==`.

| Sheet | Method |
| --- | --- |
| arcade | `double getAverageCountOfCharactersFilterByPlatform(Game.Platform platform) throws EmptyResultException` |
| campus | `double getAverageCountOfStudentsFilterByFaculty(Course.Faculty faculty) throws NotFoundException` |
| cinema | `double getAverageCountOfActorsFilterByStudio(Movie.Studio studio) throws NoResultException` |
| clinic | `double getAverageCountOfPatientsFilterByDepartment(Doctor.Department department) throws EmptyResultException` |
| conference | `double getAverageCountOfAttendeesFilterByTrack(Talk.Track track) throws NotFoundException` |

### arcade

```java
/**
 * Returns the average count of characters of the games that have the given platform.
 *
 * @param platform the platform
 * @return the result
 * @throws EmptyResultException if no <i>game</i> has the given platform
 */
@Override
public double getAverageCountOfCharactersFilterByPlatform(@NonNull Game.Platform platform) throws EmptyResultException {
    int sum = 0;
    int count = 0;
    for (Game t : getAll()) {
        if (t.getPlatform() == platform) {
            sum += t.getCharacters().length;
            count++;
        }
    }
    if (count == 0) {
        throw new EmptyResultException();
    }
    return (double) sum / count;
}
```

### campus

```java
/**
 * Returns the average count of students of the courses that have the given faculty.
 *
 * @param faculty the faculty
 * @return the result
 * @throws NotFoundException if no <i>course</i> has the given faculty
 */
@Override
public double getAverageCountOfStudentsFilterByFaculty(@NonNull Course.Faculty faculty) throws NotFoundException {
    int sum = 0;
    int count = 0;
    for (Course t : getAll()) {
        if (t.getFaculty() == faculty) {
            sum += t.getStudents().length;
            count++;
        }
    }
    if (count == 0) {
        throw new NotFoundException();
    }
    return (double) sum / count;
}
```

### cinema

```java
/**
 * Returns the average count of actors of the movies that have the given studio.
 *
 * @param studio the studio
 * @return the result
 * @throws NoResultException if no <i>movie</i> has the given studio
 */
@Override
public double getAverageCountOfActorsFilterByStudio(@NonNull Movie.Studio studio) throws NoResultException {
    int sum = 0;
    int count = 0;
    for (Movie t : getAll()) {
        if (t.getStudio() == studio) {
            sum += t.getActors().length;
            count++;
        }
    }
    if (count == 0) {
        throw new NoResultException();
    }
    return (double) sum / count;
}
```

### clinic

```java
/**
 * Returns the average count of patients of the doctors that have the given department.
 *
 * @param department the department
 * @return the result
 * @throws EmptyResultException if no <i>doctor</i> has the given department
 */
@Override
public double getAverageCountOfPatientsFilterByDepartment(@NonNull Doctor.Department department) throws EmptyResultException {
    int sum = 0;
    int count = 0;
    for (Doctor t : getAll()) {
        if (t.getDepartment() == department) {
            sum += t.getPatients().size();
            count++;
        }
    }
    if (count == 0) {
        throw new EmptyResultException();
    }
    return (double) sum / count;
}
```

### conference

```java
/**
 * Returns the average count of attendees of the talks that have the given track.
 *
 * @param track the track
 * @return the result
 * @throws NotFoundException if no <i>talk</i> has the given track
 */
@Override
public double getAverageCountOfAttendeesFilterByTrack(@NonNull Talk.Track track) throws NotFoundException {
    int sum = 0;
    int count = 0;
    for (Talk t : getAll()) {
        if (t.getTrack() == track) {
            sum += t.getAttendees().size();
            count++;
        }
    }
    if (count == 0) {
        throw new NotFoundException();
    }
    return (double) sum / count;
}
```

---

<a id="A-max-length"></a>

## 19. greatest length of a String field of T

Template `A-max-length`, algorithm: maximum.

**The algorithm.** The maximum of the lengths of a `String` field: the same as a maximum of numbers, the value being `length()` of the text. A `found` flag, and the documented exception for no objects.

**Typical mistakes:**

* Starting the result at `0` (or at `Integer.MAX_VALUE` for a minimum) instead of using a flag: with no values the method returns a made-up number, and a fixed start may be greater (smaller) than every real value.
* Returning `0` (or anything) when there are no values: the documentation asks for the exception. It is a checked exception, so the method declares it (`throws ...`) as the interface does.
* Comparing the texts (`compareTo()`) instead of their lengths: that is the alphabetical order.

| Sheet | Method |
| --- | --- |
| arcade | `int getMaximumLengthOfName() throws EmptyResultException` |
| campus | `int getMaximumLengthOfCode() throws NoResultException` |
| cinema | `int getMaximumLengthOfTitle() throws NotFoundException` |
| clinic | `int getMaximumLengthOfRoom() throws NoResultException` |
| conference | `int getMaximumLengthOfSpeaker() throws NoResultException` |

### arcade

```java
/**
 * Returns the greatest length of the name of the games.
 *
 * @return the result
 * @throws EmptyResultException if there are no <i>games</i>
 */
@Override
public int getMaximumLengthOfName() throws EmptyResultException {
    boolean found = false;
    int result = 0;
    for (Game t : getAll()) {
        if (!found || t.getName().length() > result) {
            result = t.getName().length();
            found = true;
        }
    }
    if (!found) {
        throw new EmptyResultException();
    }
    return result;
}
```

### campus

```java
/**
 * Returns the greatest length of the course code of the courses.
 *
 * @return the result
 * @throws NoResultException if there are no <i>courses</i>
 */
@Override
public int getMaximumLengthOfCode() throws NoResultException {
    boolean found = false;
    int result = 0;
    for (Course t : getAll()) {
        if (!found || t.getCode().length() > result) {
            result = t.getCode().length();
            found = true;
        }
    }
    if (!found) {
        throw new NoResultException();
    }
    return result;
}
```

### cinema

```java
/**
 * Returns the greatest length of the title of the movies.
 *
 * @return the result
 * @throws NotFoundException if there are no <i>movies</i>
 */
@Override
public int getMaximumLengthOfTitle() throws NotFoundException {
    boolean found = false;
    int result = 0;
    for (Movie t : getAll()) {
        if (!found || t.getTitle().length() > result) {
            result = t.getTitle().length();
            found = true;
        }
    }
    if (!found) {
        throw new NotFoundException();
    }
    return result;
}
```

### clinic

```java
/**
 * Returns the greatest length of the room number of the doctors.
 *
 * @return the result
 * @throws NoResultException if there are no <i>doctors</i>
 */
@Override
public int getMaximumLengthOfRoom() throws NoResultException {
    boolean found = false;
    int result = 0;
    for (Doctor t : getAll()) {
        if (!found || t.getRoom().length() > result) {
            result = t.getRoom().length();
            found = true;
        }
    }
    if (!found) {
        throw new NoResultException();
    }
    return result;
}
```

### conference

```java
/**
 * Returns the greatest length of the speaker of the talks.
 *
 * @return the result
 * @throws NoResultException if there are no <i>talks</i>
 */
@Override
public int getMaximumLengthOfSpeaker() throws NoResultException {
    boolean found = false;
    int result = 0;
    for (Talk t : getAll()) {
        if (!found || t.getSpeaker().length() > result) {
            result = t.getSpeaker().length();
            found = true;
        }
    }
    if (!found) {
        throw new NoResultException();
    }
    return result;
}
```

---

<a id="A-field-of-max"></a>

## 20. a String field of the T with the greatest numeric field

Template `A-field-of-max`, algorithm: maximum selection.

**The algorithm.** Maximum selection, then a field of the selected object. (1) `best` starts as `null`. (2) In the loop, the first object replaces `null`, and later an object replaces the best only if its numeric field is strictly greater. (3) After the loop, `best == null` means there were no objects: `null` or the documented exception. (4) Return the `String` field of the best object.

**Typical mistakes:**

* Using `>=` (or `<=`) instead of `>`: on ties the last object would win, but the documentation asks for the first one.
* Keeping only the greatest number and losing the object: the answer is another field of the object.
* Not reading the end of the documentation: if nothing matches, the method returns `null` or throws the given (checked) exception, which the method declares with `throws`.

| Sheet | Method |
| --- | --- |
| arcade | `String getNameOfGameWithMaximumReleased() throws NotFoundException` |
| campus | `String getCodeOfCourseWithMaximumCredits()` |
| cinema | `String getTitleOfMovieWithMaximumYear()` |
| clinic | `String getRoomOfDoctorWithMaximumExperience()` |
| conference | `String getSpeakerOfTalkWithMaximumRating()` |

### arcade

```java
/**
 * Returns the name of the game that has the greatest release year.
 * If more games have the greatest value, the first one is considered.
 *
 * @return the result
 * @throws NotFoundException if there are no <i>games</i>
 */
@Override
public String getNameOfGameWithMaximumReleased() throws NotFoundException {
    Game best = null;
    for (Game t : getAll()) {
        if (best == null || t.getReleased() > best.getReleased()) {
            best = t;
        }
    }
    if (best == null) {
        throw new NotFoundException();
    }
    return best.getName();
}
```

### campus

```java
/**
 * Returns the course code of the course that has the greatest credits.
 * If more courses have the greatest value, the first one is considered.
 * Returns <code>null</code> if there are no <i>courses</i>.
 *
 * @return the result
 */
@Override
public String getCodeOfCourseWithMaximumCredits() {
    Course best = null;
    for (Course t : getAll()) {
        if (best == null || t.getCredits() > best.getCredits()) {
            best = t;
        }
    }
    if (best == null) {
        return null;
    }
    return best.getCode();
}
```

### cinema

```java
/**
 * Returns the title of the movie that has the greatest release year.
 * If more movies have the greatest value, the first one is considered.
 * Returns <code>null</code> if there are no <i>movies</i>.
 *
 * @return the result
 */
@Override
public String getTitleOfMovieWithMaximumYear() {
    Movie best = null;
    for (Movie t : getAll()) {
        if (best == null || t.getYear() > best.getYear()) {
            best = t;
        }
    }
    if (best == null) {
        return null;
    }
    return best.getTitle();
}
```

### clinic

```java
/**
 * Returns the room number of the doctor that has the greatest years of experience.
 * If more doctors have the greatest value, the first one is considered.
 * Returns <code>null</code> if there are no <i>doctors</i>.
 *
 * @return the result
 */
@Override
public String getRoomOfDoctorWithMaximumExperience() {
    Doctor best = null;
    for (Doctor t : getAll()) {
        if (best == null || t.getExperience() > best.getExperience()) {
            best = t;
        }
    }
    if (best == null) {
        return null;
    }
    return best.getRoom();
}
```

### conference

```java
/**
 * Returns the speaker of the talk that has the greatest average rating.
 * If more talks have the greatest value, the first one is considered.
 * Returns <code>null</code> if there are no <i>talks</i>.
 *
 * @return the result
 */
@Override
public String getSpeakerOfTalkWithMaximumRating() {
    Talk best = null;
    for (Talk t : getAll()) {
        if (best == null || t.getRating() > best.getRating()) {
            best = t;
        }
    }
    if (best == null) {
        return null;
    }
    return best.getSpeaker();
}
```

---

<a id="A-exists-value"></a>

## 21. decides whether any T has the given value of a numeric field

Template `A-exists-value`, algorithm: decision.

**The algorithm.** Decision: is there an object with the given value? A flag starts as `false` and becomes `true` at a match; after the loop it is the answer. (Returning `true` right at the match is also correct.)

**Typical mistakes:**

* Returning `false` inside the loop for the first non-matching object: the answer is `false` only if *no* object matches, which is known only after the loop.

| Sheet | Method |
| --- | --- |
| arcade | `boolean isReleasedPresent(int released)` |
| campus | `boolean isSemesterPresent(int semester)` |
| cinema | `boolean isYearPresent(int year)` |
| clinic | `boolean isExperiencePresent(int experience)` |
| conference | `boolean isRatingPresent(double rating)` |

### arcade

```java
/**
 * Returns whether any game has the given release year.
 *
 * @param released the release year of the game
 * @return the result
 */
@Override
public boolean isReleasedPresent(int released) {
    boolean found = false;
    for (Game t : getAll()) {
        if (t.getReleased() == released) {
            found = true;
        }
    }
    return found;
}
```

### campus

```java
/**
 * Returns whether any course has the given recommended semester.
 *
 * @param semester the recommended semester of the course
 * @return the result
 */
@Override
public boolean isSemesterPresent(int semester) {
    boolean found = false;
    for (Course t : getAll()) {
        if (t.getSemester() == semester) {
            found = true;
        }
    }
    return found;
}
```

### cinema

```java
/**
 * Returns whether any movie has the given release year.
 *
 * @param year the release year of the movie
 * @return the result
 */
@Override
public boolean isYearPresent(int year) {
    boolean found = false;
    for (Movie t : getAll()) {
        if (t.getYear() == year) {
            found = true;
        }
    }
    return found;
}
```

### clinic

```java
/**
 * Returns whether any doctor has the given years of experience.
 *
 * @param experience the years of experience of the doctor
 * @return the result
 */
@Override
public boolean isExperiencePresent(int experience) {
    boolean found = false;
    for (Doctor t : getAll()) {
        if (t.getExperience() == experience) {
            found = true;
        }
    }
    return found;
}
```

### conference

```java
/**
 * Returns whether any talk has the given average rating.
 *
 * @param rating the average rating of the talk
 * @return the result
 */
@Override
public boolean isRatingPresent(double rating) {
    boolean found = false;
    for (Talk t : getAll()) {
        if (t.getRating() == rating) {
            found = true;
        }
    }
    return found;
}
```

---

<a id="A-exists-nested-field"></a>

## 22. decides whether any U has the given field value

Template `A-exists-nested-field`, algorithm: decision.

**The algorithm.** Decision over the nested objects: two loops and a flag set at the first nested object with the given value.

**Typical mistakes:**

* Comparing strings with `==`: it compares the references, not the texts, so a matching string may be missed. Use `equals()`.
* Returning `false` inside the loop at the first non-matching object: decide only after every nested object was checked.

| Sheet | Method |
| --- | --- |
| arcade | `boolean isCharacterNamePresent(String name)` |
| campus | `boolean isStudentNeptunPresent(String neptun)` |
| cinema | `boolean isActorNationalityPresent(String nationality)` |
| clinic | `boolean isPatientNamePresent(String name)` |
| conference | `boolean isAttendeeCompanyPresent(String company)` |

### arcade

```java
/**
 * Returns whether any game has any character with the given name.
 *
 * @param name the name of the character
 * @return the result
 */
@Override
public boolean isCharacterNamePresent(@NonNull String name) {
    boolean found = false;
    for (Game t : getAll()) {
        for (Game.Character u : t.getCharacters()) {
            if (u.getName().equals(name)) {
                found = true;
            }
        }
    }
    return found;
}
```

### campus

```java
/**
 * Returns whether any course has any student with the given Neptun code.
 *
 * @param neptun the Neptun code of the student
 * @return the result
 */
@Override
public boolean isStudentNeptunPresent(@NonNull String neptun) {
    boolean found = false;
    for (Course t : getAll()) {
        for (Course.Student u : t.getStudents()) {
            if (u.getNeptun().equals(neptun)) {
                found = true;
            }
        }
    }
    return found;
}
```

### cinema

```java
/**
 * Returns whether any movie has any actor with the given nationality.
 *
 * @param nationality the nationality of the actor
 * @return the result
 */
@Override
public boolean isActorNationalityPresent(@NonNull String nationality) {
    boolean found = false;
    for (Movie t : getAll()) {
        for (Movie.Actor u : t.getActors()) {
            if (u.getNationality().equals(nationality)) {
                found = true;
            }
        }
    }
    return found;
}
```

### clinic

```java
/**
 * Returns whether any doctor has any patient with the given name.
 *
 * @param name the name of the patient
 * @return the result
 */
@Override
public boolean isPatientNamePresent(@NonNull String name) {
    boolean found = false;
    for (Doctor t : getAll()) {
        for (Doctor.Patient u : t.getPatients()) {
            if (u.getName().equals(name)) {
                found = true;
            }
        }
    }
    return found;
}
```

### conference

```java
/**
 * Returns whether any talk has any attendee with the given company.
 *
 * @param company the company of the attendee
 * @return the result
 */
@Override
public boolean isAttendeeCompanyPresent(@NonNull String company) {
    boolean found = false;
    for (Talk t : getAll()) {
        for (Talk.Attendee u : t.getAttendees()) {
            if (u.getCompany().equals(company)) {
                found = true;
            }
        }
    }
    return found;
}
```

---

<a id="A-all-have-nested"></a>

## 23. decides whether every T has at least the given number of U

Template `A-all-have-nested`, algorithm: decision (for all).

**The algorithm.** Decision for all: does every object meet the condition? The flag starts as `true` and becomes `false` at an object that does not meet it. With no objects the answer is `true`: no object breaks the rule.

**Typical mistakes:**

* Starting the flag as `false` and setting it to `true` at a matching object: that decides *any*, not *every*.
* Using `>` instead of `>=`: *at least* the given number includes the number itself.

| Sheet | Method |
| --- | --- |
| arcade | `boolean doAllGamesHaveAtLeastCharacters(int count)` |
| campus | `boolean doAllCoursesHaveAtLeastStudents(int count)` |
| cinema | `boolean doAllMoviesHaveAtLeastActors(int count)` |
| clinic | `boolean doAllDoctorsHaveAtLeastPatients(int count)` |
| conference | `boolean doAllTalksHaveAtLeastAttendees(int count)` |

### arcade

```java
/**
 * Returns whether every game has at least the given number of characters.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public boolean doAllGamesHaveAtLeastCharacters(int count) {
    boolean result = true;
    for (Game t : getAll()) {
        if (t.getCharacters().length < count) {
            result = false;
        }
    }
    return result;
}
```

### campus

```java
/**
 * Returns whether every course has at least the given number of students.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public boolean doAllCoursesHaveAtLeastStudents(int count) {
    boolean result = true;
    for (Course t : getAll()) {
        if (t.getStudents().length < count) {
            result = false;
        }
    }
    return result;
}
```

### cinema

```java
/**
 * Returns whether every movie has at least the given number of actors.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public boolean doAllMoviesHaveAtLeastActors(int count) {
    boolean result = true;
    for (Movie t : getAll()) {
        if (t.getActors().length < count) {
            result = false;
        }
    }
    return result;
}
```

### clinic

```java
/**
 * Returns whether every doctor has at least the given number of patients.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public boolean doAllDoctorsHaveAtLeastPatients(int count) {
    boolean result = true;
    for (Doctor t : getAll()) {
        if (t.getPatients().size() < count) {
            result = false;
        }
    }
    return result;
}
```

### conference

```java
/**
 * Returns whether every talk has at least the given number of attendees.
 *
 * @param count the minimal count
 * @return the result
 */
@Override
public boolean doAllTalksHaveAtLeastAttendees(int count) {
    boolean result = true;
    for (Talk t : getAll()) {
        if (t.getAttendees().size() < count) {
            result = false;
        }
    }
    return result;
}
```
