package conference;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Represents a talk.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Talk implements Comparable<Talk> {

    @EqualsAndHashCode.Include
    private String title;

    private String speaker;

    private Track track;

    private int duration;

    private double rating;

    @ToString.Exclude
    private List<Attendee> attendees;

    @Override
    public int compareTo(@NonNull Talk other) {
        int result = Objects.compare(this.getSpeaker(), other.getSpeaker(), Comparator.naturalOrder());
        if (result != 0) {
            return result;
        }
        result = Objects.compare(this.getRating(), other.getRating(), Comparator.reverseOrder());
        if (result != 0) {
            return result;
        }
        return Objects.compare(this.getTitle(), other.getTitle(), Comparator.reverseOrder());
    }

    /**
     * Compares talks.
     */
    public static class TalkComparator implements Comparator<Talk> {

        @Override
        public int compare(@NonNull Talk first, @NonNull Talk second) {
            int result = Objects.compare(first.getSpeaker(), second.getSpeaker(), Comparator.reverseOrder());
            if (result != 0) {
                return result;
            }
            result = Objects.compare(first.getRating(), second.getRating(), Comparator.reverseOrder());
            if (result != 0) {
                return result;
            }
            return Objects.compare(first.getTitle(), second.getTitle(), Comparator.naturalOrder());
        }
    }

    /**
     * Represents an attendee.
     */
    @Getter
    @Setter(AccessLevel.PROTECTED)
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @ToString
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class Attendee {

        @EqualsAndHashCode.Include
        private String email;

        private String company;

        private int age;
    }

    /**
     * Represents the possible tracks.
     */
    public enum Track {
        @JsonProperty("Backend") BACKEND,
        @JsonProperty("Frontend") FRONTEND,
        @JsonProperty("Data") DATA,
        @JsonProperty("Security") SECURITY,
        @JsonProperty("Cloud") CLOUD
    }
}
