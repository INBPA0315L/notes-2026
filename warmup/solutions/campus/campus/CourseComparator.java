package campus;

import lombok.NonNull;

import java.util.Comparator;
import java.util.Objects;

/**
 * Compares courses.
 */
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