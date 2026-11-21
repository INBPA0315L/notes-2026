package clinic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Represents a doctor.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public final class Doctor implements Comparable<Doctor> {

    @EqualsAndHashCode.Include
    final String name;

    final Department department;

    final int experience;

    final String room;

    final boolean onCall;

    @ToString.Exclude
    final List<Patient> patients;

    @Override
    public int compareTo(@NonNull Doctor other) {
        int result = Objects.compare(this.getExperience(), other.getExperience(), Comparator.reverseOrder());
        if (result != 0) {
            return result;
        }
        result = Objects.compare(this.getRoom(), other.getRoom(), Comparator.reverseOrder());
        if (result != 0) {
            return result;
        }
        return Objects.compare(this.getName(), other.getName(), Comparator.naturalOrder());
    }

    /**
     * Compares doctors.
     */
    public static class DoctorComparator implements Comparator<Doctor> {

        @Override
        public int compare(@NonNull Doctor first, @NonNull Doctor second) {
            int result = Objects.compare(first.getRoom(), second.getRoom(), Comparator.naturalOrder());
            if (result != 0) {
                return result;
            }
            result = Objects.compare(first.isOnCall(), second.isOnCall(), Comparator.naturalOrder());
            if (result != 0) {
                return result;
            }
            return Objects.compare(first.getName(), second.getName(), Comparator.naturalOrder());
        }
    }

    /**
     * Represents a patient.
     */
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
    @ToString
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static final class Patient {

        @EqualsAndHashCode.Include
        final long insuranceNumber;

        final String name;

        final int age;
    }

    /**
     * Represents the possible departments.
     */
    public enum Department {
        @JsonProperty("Cardiology") CARDIOLOGY,
        @JsonProperty("Neurology") NEUROLOGY,
        @JsonProperty("Pediatrics") PEDIATRICS,
        @JsonProperty("Orthopedics") ORTHOPEDICS,
        @JsonProperty("Dermatology") DERMATOLOGY
    }
}
