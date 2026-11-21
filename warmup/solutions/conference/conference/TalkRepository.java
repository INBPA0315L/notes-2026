package conference;

import base.*;
import lombok.NonNull;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The reference solution (generated).
 */
public class TalkRepository extends Repository<Talk> implements AggregationQueries<Talk, Talk.Attendee, Talk.Track>,
        SortingQueries<Talk, Talk.Attendee, Talk.Track>,
        SearchQueries<Talk, Talk.Attendee, Talk.Track>,
        FilteringQueries<Talk, Talk.Attendee, Talk.Track>,
        SetQueries<Talk, Talk.Attendee, Talk.Track>,
        BasicMapQueries<Talk, Talk.Attendee, Talk.Track>,
        AggregationMapQueries<Talk, Talk.Attendee, Talk.Track>,
        GroupingMapQueries<Talk, Talk.Attendee, Talk.Track>,
        NestedMapQueries<Talk, Talk.Attendee, Talk.Track> {

    public TalkRepository() throws IOException {
        super(Talk.class);
    }

    public static void main(String[] args) throws Exception {
        TalkRepository repository = new TalkRepository();
        System.out.println(repository);
        System.out.println("getMaximumCountOfAttendees: " + repository.getMaximumCountOfAttendees());
        System.out.println("getMinimumCountOfAttendees: " + repository.getMinimumCountOfAttendees());
        System.out.println("getTotalCountOfAttendeesFilterByTrack: " + repository.getTotalCountOfAttendeesFilterByTrack(Talk.Track.BACKEND));
        System.out.println("getMaximumOfRating: " + repository.getMaximumOfRating());
        System.out.println("getMinimumOfRating: " + repository.getMinimumOfRating());
        System.out.println("getAverageOfRatingFilterByTrack: " + repository.getAverageOfRatingFilterByTrack(Talk.Track.BACKEND));
        System.out.println("getMaximumOfAttendeeAge: " + repository.getMaximumOfAttendeeAge());
        System.out.println("getAverageOfAttendeeAgeFilterByTrack: " + repository.getAverageOfAttendeeAgeFilterByTrack(Talk.Track.BACKEND));
        System.out.println("getCountOfTalksFilterByTrack: " + repository.getCountOfTalksFilterByTrack(Talk.Track.BACKEND));
        System.out.println("getCountOfTalksAboveRatingLimit: " + repository.getCountOfTalksAboveRatingLimit(repository.getAll().getFirst().getRating()));
        System.out.println("getCountOfTalksByTitlePrefix: " + repository.getCountOfTalksByTitlePrefix(repository.getAll().getFirst().getTitle().substring(0, 1)));
        System.out.println("getCountOfTalksByAttendeeCompany: " + repository.getCountOfTalksByAttendeeCompany(repository.getAll().getFirst().getAttendees().iterator().next().getCompany()));
        System.out.println("getCountOfTalksWithoutAttendees: " + repository.getCountOfTalksWithoutAttendees());
        System.out.println("getCountOfAttendeesByCompany: " + repository.getCountOfAttendeesByCompany(repository.getAll().getFirst().getAttendees().iterator().next().getCompany()));
        System.out.println("getSumOfRatingFilterByTrack: " + repository.getSumOfRatingFilterByTrack(Talk.Track.BACKEND));
        System.out.println("getSumOfAttendeeAgeByEmail: " + repository.getSumOfAttendeeAgeByEmail(repository.getAll().getFirst().getAttendees().iterator().next().getEmail()));
        System.out.println("getMinimumOfAttendeeAge: " + repository.getMinimumOfAttendeeAge());
        System.out.println("getAverageCountOfAttendeesFilterByTrack: " + repository.getAverageCountOfAttendeesFilterByTrack(Talk.Track.BACKEND));
        System.out.println("getMaximumLengthOfSpeaker: " + repository.getMaximumLengthOfSpeaker());
        System.out.println("getSpeakerOfTalkWithMaximumRating: " + repository.getSpeakerOfTalkWithMaximumRating());
        System.out.println("isRatingPresent: " + repository.isRatingPresent(repository.getAll().getFirst().getRating()));
        System.out.println("isAttendeeCompanyPresent: " + repository.isAttendeeCompanyPresent(repository.getAll().getFirst().getAttendees().iterator().next().getCompany()));
        System.out.println("doAllTalksHaveAtLeastAttendees: " + repository.doAllTalksHaveAtLeastAttendees(1));
        System.out.println("getTalksOrderBySpeakerThenByRatingDescThenByTitleDesc: " + repository.getTalksOrderBySpeakerThenByRatingDescThenByTitleDesc());
        System.out.println("getTalksOrderByDurationDescThenByRatingThenByTitleDesc: " + repository.getTalksOrderByDurationDescThenByRatingThenByTitleDesc());
        System.out.println("getTalksOrderByRatingThenByDurationDescThenByTitleDesc: " + repository.getTalksOrderByRatingThenByDurationDescThenByTitleDesc());
        System.out.println("getTalksOrderBySpeakerDescThenByRatingThenByTitle: " + repository.getTalksOrderBySpeakerDescThenByRatingThenByTitle());
        System.out.println("getTalksOrderBySpeakerDescThenByRatingDescThenByTitle: " + repository.getTalksOrderBySpeakerDescThenByRatingDescThenByTitle());
        System.out.println("getFirstTalkByTrack: " + repository.getFirstTalkByTrack(Talk.Track.BACKEND));
        System.out.println("getFirstTalkBySpeaker: " + repository.getFirstTalkBySpeaker(repository.getAll().getFirst().getSpeaker()));
        System.out.println("getFirstTalkByAttendeeEmail: " + repository.getFirstTalkByAttendeeEmail(repository.getAll().getFirst().getAttendees().iterator().next().getEmail()));
        System.out.println("getFirstAttendeeByCompany: " + repository.getFirstAttendeeByCompany(repository.getAll().getFirst().getAttendees().iterator().next().getCompany()));
        System.out.println("getTalkWithMaximumRating: " + repository.getTalkWithMaximumRating());
        System.out.println("getTalkWithMostAttendees: " + repository.getTalkWithMostAttendees());
        System.out.println("getAttendeeWithMaximumAge: " + repository.getAttendeeWithMaximumAge());
        System.out.println("getTalksFilterByTrackWithoutAttendee: " + repository.getTalksFilterByTrackWithoutAttendee(Talk.Track.BACKEND, repository.getAll().getFirst().getAttendees().iterator().next()));
        System.out.println("getTalksFilterByTrackWithoutAttendeeCompany: " + repository.getTalksFilterByTrackWithoutAttendeeCompany(Talk.Track.BACKEND, repository.getAll().getFirst().getAttendees().iterator().next().getCompany()));
        System.out.println("getTalksContainingAttendee: " + repository.getTalksContainingAttendee(repository.getAll().getFirst().getAttendees().iterator().next()));
        System.out.println("getTalksAboveDurationLimit: " + repository.getTalksAboveDurationLimit(repository.getAll().getFirst().getDuration()));
        System.out.println("getTalksFilterByCountOfAttendees: " + repository.getTalksFilterByCountOfAttendees(2));
        System.out.println("getDistinctAttendeesInOrderFilterByTrack: " + repository.getDistinctAttendeesInOrderFilterByTrack(Talk.Track.BACKEND));
        System.out.println("getTracksByAttendeeEmail: " + repository.getTracksByAttendeeEmail(repository.getAll().getFirst().getAttendees().iterator().next().getEmail()));
        System.out.println("getTracksContainingAttendee: " + repository.getTracksContainingAttendee(repository.getAll().getFirst().getAttendees().iterator().next()));
        System.out.println("getTitleValuesByAttendeeCompany: " + repository.getTitleValuesByAttendeeCompany(repository.getAll().getFirst().getAttendees().iterator().next().getCompany()));
        System.out.println("getAttendeesFilterByTrack: " + repository.getAttendeesFilterByTrack(Talk.Track.BACKEND));
        System.out.println("getTracksAboveDurationLimit: " + repository.getTracksAboveDurationLimit(repository.getAll().getFirst().getDuration()));
        System.out.println("getAttendeeCompanyValues: " + repository.getAttendeeCompanyValues());
        System.out.println("getCountOfTalksByTracks: " + repository.getCountOfTalksByTracks());
        System.out.println("getCountOfTalksByRating: " + repository.getCountOfTalksByRating());
        System.out.println("getTracksByTalks: " + repository.getTracksByTalks());
        System.out.println("getFirstTalkByTracks: " + repository.getFirstTalkByTracks());
        System.out.println("getLastTalkByTracks: " + repository.getLastTalkByTracks());
        System.out.println("getRatingOfFirstTalkByTracks: " + repository.getRatingOfFirstTalkByTracks());
        System.out.println("getRatingOfLastTalkByTracks: " + repository.getRatingOfLastTalkByTracks());
        System.out.println("getFirstTalkByDuration: " + repository.getFirstTalkByDuration());
        System.out.println("getTrackOfLastTalkBySpeaker: " + repository.getTrackOfLastTalkBySpeaker());
        System.out.println("getSumOfRatingByTracks: " + repository.getSumOfRatingByTracks());
        System.out.println("getCountOfTalksByTracksFilterByCountOfAttendees: " + repository.getCountOfTalksByTracksFilterByCountOfAttendees(3));
        System.out.println("getCountOfTalksBySpeakerValuesFilterByTrack: " + repository.getCountOfTalksBySpeakerValuesFilterByTrack(Talk.Track.BACKEND));
        System.out.println("getAverageOfDurationByTracks: " + repository.getAverageOfDurationByTracks());
        System.out.println("getMaximumOfDurationByTracks: " + repository.getMaximumOfDurationByTracks());
        System.out.println("getSumOfAttendeeAgeByTracks: " + repository.getSumOfAttendeeAgeByTracks());
        System.out.println("getAttendeesByTracks: " + repository.getAttendeesByTracks());
        System.out.println("getTalksByTracksFilterByCountOfAttendees: " + repository.getTalksByTracksFilterByCountOfAttendees(3));
        System.out.println("getSpeakerValuesByTracksFilterByRating: " + repository.getSpeakerValuesByTracksFilterByRating(repository.getAll().getFirst().getRating()));
        System.out.println("getAgeValuesOfAttendeesByDurationFilterByTrack: " + repository.getAgeValuesOfAttendeesByDurationFilterByTrack(Talk.Track.BACKEND));
        System.out.println("getTracksByAttendeeAgeFilterByCountOfAttendees: " + repository.getTracksByAttendeeAgeFilterByCountOfAttendees(3));
        System.out.println("getCountOfTalksByTracksAndRating: " + repository.getCountOfTalksByTracksAndRating());
        System.out.println("getCountOfTalksByDurationAndTracksFilterByCountOfAttendees: " + repository.getCountOfTalksByDurationAndTracksFilterByCountOfAttendees(3));
        System.out.println("getCountOfAttendeesByTracksAndAge: " + repository.getCountOfAttendeesByTracksAndAge());
        System.out.println("getMaximumOfRatingByTracksAndSpeaker: " + repository.getMaximumOfRatingByTracksAndSpeaker());
    }

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

    @Override
    public List<Talk> getTalksOrderBySpeakerThenByRatingDescThenByTitleDesc() {
        List<Talk> result = new ArrayList<>(getAll());
        Collections.sort(result);
        return result;
    }

    @Override
    public List<Talk> getTalksOrderByDurationDescThenByRatingThenByTitleDesc() {
        List<Talk> result = new ArrayList<>(getAll());
        Collections.sort(result, new conference.TalkComparator());
        return result;
    }

    @Override
    public List<Talk> getTalksOrderByRatingThenByDurationDescThenByTitleDesc() {
        List<Talk> result = new ArrayList<>(getAll());
        Collections.sort(result, new Comparator<Talk>() {
            @Override
            public int compare(@NonNull Talk first, @NonNull Talk second) {
                int result = Objects.compare(first.getRating(), second.getRating(), Comparator.naturalOrder());
                if (result != 0) {
                    return result;
                }
                result = Objects.compare(first.getDuration(), second.getDuration(), Comparator.reverseOrder());
                if (result != 0) {
                    return result;
                }
                return Objects.compare(first.getTitle(), second.getTitle(), Comparator.reverseOrder());
            }
        });
        return result;
    }

    @Override
    public List<Talk> getTalksOrderBySpeakerDescThenByRatingThenByTitle() {
        List<Talk> result = new ArrayList<>(getAll());
        Collections.sort(result, new TalkComparator());
        return result;
    }

    @Override
    public List<Talk> getTalksOrderBySpeakerDescThenByRatingDescThenByTitle() {
        List<Talk> result = new ArrayList<>(getAll());
        Collections.sort(result, new Talk.TalkComparator());
        return result;
    }

    @Override
    public Talk getFirstTalkByTrack(@NonNull Talk.Track track) throws EmptyResultException {
        for (Talk t : getAll()) {
            if (t.getTrack() == track) {
                return t;
            }
        }
        throw new EmptyResultException();
    }

    @Override
    public Talk getFirstTalkBySpeaker(@NonNull String speaker) {
        for (Talk t : getAll()) {
            if (t.getSpeaker().equals(speaker)) {
                return t;
            }
        }
        return null;
    }

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

    @Override
    public Map<Talk.Track, Integer> getCountOfTalksByTracks() {
        Map<Talk.Track, Integer> result = new HashMap<>();
        for (Talk t : getAll()) {
            result.put(t.getTrack(), result.getOrDefault(t.getTrack(), 0) + 1);
        }
        return result;
    }

    @Override
    public Map<Double, Integer> getCountOfTalksByRating() {
        Map<Double, Integer> result = new HashMap<>();
        for (Talk t : getAll()) {
            result.put(t.getRating(), result.getOrDefault(t.getRating(), 0) + 1);
        }
        return result;
    }

    @Override
    public Map<Talk, Talk.Track> getTracksByTalks() {
        Map<Talk, Talk.Track> result = new HashMap<>();
        for (Talk t : getAll()) {
            result.put(t, t.getTrack());
        }
        return result;
    }

    @Override
    public Map<Talk.Track, Talk> getFirstTalkByTracks() {
        Map<Talk.Track, Talk> result = new HashMap<>();
        for (Talk t : getAll()) {
            if (!result.containsKey(t.getTrack())) {
                result.put(t.getTrack(), t);
            }
        }
        return result;
    }

    @Override
    public Map<Talk.Track, Talk> getLastTalkByTracks() {
        Map<Talk.Track, Talk> result = new HashMap<>();
        for (Talk t : getAll()) {
            result.put(t.getTrack(), t);
        }
        return result;
    }

    @Override
    public Map<Talk.Track, Double> getRatingOfFirstTalkByTracks() {
        Map<Talk.Track, Double> result = new HashMap<>();
        for (Talk t : getAll()) {
            if (!result.containsKey(t.getTrack())) {
                result.put(t.getTrack(), t.getRating());
            }
        }
        return result;
    }

    @Override
    public Map<Talk.Track, Double> getRatingOfLastTalkByTracks() {
        Map<Talk.Track, Double> result = new HashMap<>();
        for (Talk t : getAll()) {
            result.put(t.getTrack(), t.getRating());
        }
        return result;
    }

    @Override
    public Map<Integer, Talk> getFirstTalkByDuration() {
        Map<Integer, Talk> result = new HashMap<>();
        for (Talk t : getAll()) {
            if (!result.containsKey(t.getDuration())) {
                result.put(t.getDuration(), t);
            }
        }
        return result;
    }

    @Override
    public Map<String, Talk.Track> getTrackOfLastTalkBySpeaker() {
        Map<String, Talk.Track> result = new HashMap<>();
        for (Talk t : getAll()) {
            result.put(t.getSpeaker(), t.getTrack());
        }
        return result;
    }

    @Override
    public Map<Talk.Track, Double> getSumOfRatingByTracks() {
        Map<Talk.Track, Double> result = new HashMap<>();
        for (Talk t : getAll()) {
            result.put(t.getTrack(), result.getOrDefault(t.getTrack(), 0.0) + t.getRating());
        }
        return result;
    }

    @Override
    public Map<Talk.Track, Integer> getCountOfTalksByTracksFilterByCountOfAttendees(int count) {
        Map<Talk.Track, Integer> result = new HashMap<>();
        for (Talk t : getAll()) {
            if (t.getAttendees().size() >= count) {
                result.put(t.getTrack(), result.getOrDefault(t.getTrack(), 0) + 1);
            }
        }
        return result;
    }

    @Override
    public Map<String, Integer> getCountOfTalksBySpeakerValuesFilterByTrack(@NonNull Talk.Track track) {
        Map<String, Integer> result = new HashMap<>();
        for (Talk t : getAll()) {
            if (t.getTrack() == track) {
                result.put(t.getSpeaker(), result.getOrDefault(t.getSpeaker(), 0) + 1);
            }
        }
        return result;
    }

    @Override
    public Map<Talk.Track, Double> getAverageOfDurationByTracks() {
        Map<Talk.Track, Double> sums = new HashMap<>();
        Map<Talk.Track, Integer> counts = new HashMap<>();
        for (Talk t : getAll()) {
            sums.put(t.getTrack(), sums.getOrDefault(t.getTrack(), 0.0) + t.getDuration());
            counts.put(t.getTrack(), counts.getOrDefault(t.getTrack(), 0) + 1);
        }
        Map<Talk.Track, Double> result = new HashMap<>();
        for (Talk.Track key : sums.keySet()) {
            result.put(key, sums.get(key) / counts.get(key));
        }
        return result;
    }

    @Override
    public Map<Talk.Track, Integer> getMaximumOfDurationByTracks() {
        Map<Talk.Track, Integer> result = new HashMap<>();
        for (Talk t : getAll()) {
            if (!result.containsKey(t.getTrack()) || t.getDuration() > result.get(t.getTrack())) {
                result.put(t.getTrack(), t.getDuration());
            }
        }
        return result;
    }

    @Override
    public Map<Talk.Track, Integer> getSumOfAttendeeAgeByTracks() {
        Map<Talk.Track, Integer> result = new HashMap<>();
        for (Talk t : getAll()) {
            int sum = 0;
            for (Talk.Attendee u : t.getAttendees()) {
                sum += u.getAge();
            }
            result.put(t.getTrack(), result.getOrDefault(t.getTrack(), 0) + sum);
        }
        return result;
    }

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

    @Override
    public Map<Talk.Track, Map<Double, Integer>> getCountOfTalksByTracksAndRating() {
        Map<Talk.Track, Map<Double, Integer>> result = new HashMap<>();
        for (Talk t : getAll()) {
            if (!result.containsKey(t.getTrack())) {
                result.put(t.getTrack(), new HashMap<>());
            }
            Map<Double, Integer> inner = result.get(t.getTrack());
            inner.put(t.getRating(), inner.getOrDefault(t.getRating(), 0) + 1);
        }
        return result;
    }

    @Override
    public Map<Integer, Map<Talk.Track, Integer>> getCountOfTalksByDurationAndTracksFilterByCountOfAttendees(int count) {
        Map<Integer, Map<Talk.Track, Integer>> result = new HashMap<>();
        for (Talk t : getAll()) {
            if (t.getAttendees().size() >= count) {
                if (!result.containsKey(t.getDuration())) {
                    result.put(t.getDuration(), new HashMap<>());
                }
                Map<Talk.Track, Integer> inner = result.get(t.getDuration());
                inner.put(t.getTrack(), inner.getOrDefault(t.getTrack(), 0) + 1);
            }
        }
        return result;
    }

    @Override
    public Map<Talk.Track, Map<Integer, Integer>> getCountOfAttendeesByTracksAndAge() {
        Map<Talk.Track, Map<Integer, Integer>> result = new HashMap<>();
        for (Talk t : getAll()) {
            if (!result.containsKey(t.getTrack())) {
                result.put(t.getTrack(), new HashMap<>());
            }
            Map<Integer, Integer> inner = result.get(t.getTrack());
            for (Talk.Attendee u : t.getAttendees()) {
                inner.put(u.getAge(), inner.getOrDefault(u.getAge(), 0) + 1);
            }
        }
        return result;
    }

    @Override
    public Map<Talk.Track, Map<String, Double>> getMaximumOfRatingByTracksAndSpeaker() {
        Map<Talk.Track, Map<String, Double>> result = new HashMap<>();
        for (Talk t : getAll()) {
            if (!result.containsKey(t.getTrack())) {
                result.put(t.getTrack(), new HashMap<>());
            }
            Map<String, Double> inner = result.get(t.getTrack());
            if (!inner.containsKey(t.getSpeaker()) || t.getRating() > inner.get(t.getSpeaker())) {
                inner.put(t.getSpeaker(), t.getRating());
            }
        }
        return result;
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
            result = Objects.compare(first.getRating(), second.getRating(), Comparator.naturalOrder());
            if (result != 0) {
                return result;
            }
            return Objects.compare(first.getTitle(), second.getTitle(), Comparator.naturalOrder());
        }
    }
}
