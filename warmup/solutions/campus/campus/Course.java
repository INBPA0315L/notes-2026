package campus;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Comparator;
import java.util.Objects;

/**
 * Represents a course.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public final class Course implements Comparable<Course> {

    @EqualsAndHashCode.Include
    final String code;

    final String title;

    final Faculty faculty;

    final int credits;

    final int semester;

    @ToString.Exclude
    final Student[] students;

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

    /**
     * Compares courses.
     */
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

    /**
     * Represents a student.
     */
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
    @ToString
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static final class Student {

        @EqualsAndHashCode.Include
        private final String neptun;

        private final String name;

        private final int grade;
    }

    /**
     * Represents the possible faculties.
     */
    public enum Faculty {
        @JsonProperty("Informatics") INFORMATICS,
        @JsonProperty("Science") SCIENCE,
        @JsonProperty("Engineering") ENGINEERING,
        @JsonProperty("Economics") ECONOMICS,
        @JsonProperty("Humanities") HUMANITIES
    }
}
