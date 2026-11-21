package clinic;

import lombok.NonNull;

import java.util.Comparator;
import java.util.Objects;

/**
 * Compares doctors.
 */
public class DoctorComparator implements Comparator<Doctor> {

    @Override
    public int compare(@NonNull Doctor first, @NonNull Doctor second) {
        int result = Objects.compare(first.getExperience(), second.getExperience(), Comparator.naturalOrder());
        if (result != 0) {
            return result;
        }
        result = Objects.compare(first.getRoom(), second.getRoom(), Comparator.reverseOrder());
        if (result != 0) {
            return result;
        }
        return Objects.compare(first.getName(), second.getName(), Comparator.naturalOrder());
    }
}