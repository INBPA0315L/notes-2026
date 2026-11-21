package cinema;

import base.*;
import lombok.NonNull;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The reference solution (generated).
 */
public class MovieRepository extends Repository<Movie> implements AggregationQueries<Movie, Movie.Actor, Movie.Studio>,
        SortingQueries<Movie, Movie.Actor, Movie.Studio>,
        SearchQueries<Movie, Movie.Actor, Movie.Studio>,
        FilteringQueries<Movie, Movie.Actor, Movie.Studio>,
        SetQueries<Movie, Movie.Actor, Movie.Studio>,
        BasicMapQueries<Movie, Movie.Actor, Movie.Studio>,
        AggregationMapQueries<Movie, Movie.Actor, Movie.Studio>,
        GroupingMapQueries<Movie, Movie.Actor, Movie.Studio>,
        NestedMapQueries<Movie, Movie.Actor, Movie.Studio> {

    public MovieRepository() throws IOException {
        super(Movie.class);
    }

    public static void main(String[] args) throws Exception {
        MovieRepository repository = new MovieRepository();
        System.out.println(repository);
        System.out.println("getMaximumCountOfActors: " + repository.getMaximumCountOfActors());
        System.out.println("getMinimumCountOfActors: " + repository.getMinimumCountOfActors());
        System.out.println("getTotalCountOfActorsFilterByStudio: " + repository.getTotalCountOfActorsFilterByStudio(Movie.Studio.PARAMOUNT_PICTURES));
        System.out.println("getMaximumOfYear: " + repository.getMaximumOfYear());
        System.out.println("getMinimumOfYear: " + repository.getMinimumOfYear());
        System.out.println("getAverageOfYearFilterByStudio: " + repository.getAverageOfYearFilterByStudio(Movie.Studio.PARAMOUNT_PICTURES));
        System.out.println("getMaximumOfActorAge: " + repository.getMaximumOfActorAge());
        System.out.println("getAverageOfActorAgeFilterByStudio: " + repository.getAverageOfActorAgeFilterByStudio(Movie.Studio.PARAMOUNT_PICTURES));
        System.out.println("getCountOfMoviesFilterByStudio: " + repository.getCountOfMoviesFilterByStudio(Movie.Studio.PARAMOUNT_PICTURES));
        System.out.println("getCountOfMoviesAboveYearLimit: " + repository.getCountOfMoviesAboveYearLimit(repository.getAll().getFirst().getYear()));
        System.out.println("getCountOfMoviesByGenrePrefix: " + repository.getCountOfMoviesByGenrePrefix(repository.getAll().getFirst().getGenre().substring(0, 1)));
        System.out.println("getCountOfMoviesByActorName: " + repository.getCountOfMoviesByActorName(repository.getAll().getFirst().getActors()[0].getName()));
        System.out.println("getCountOfMoviesWithoutActors: " + repository.getCountOfMoviesWithoutActors());
        System.out.println("getCountOfActorsByName: " + repository.getCountOfActorsByName(repository.getAll().getFirst().getActors()[0].getName()));
        System.out.println("getSumOfYearFilterByStudio: " + repository.getSumOfYearFilterByStudio(Movie.Studio.PARAMOUNT_PICTURES));
        System.out.println("getSumOfActorAgeByName: " + repository.getSumOfActorAgeByName(repository.getAll().getFirst().getActors()[0].getName()));
        System.out.println("getMinimumOfActorAge: " + repository.getMinimumOfActorAge());
        System.out.println("getAverageCountOfActorsFilterByStudio: " + repository.getAverageCountOfActorsFilterByStudio(Movie.Studio.PARAMOUNT_PICTURES));
        System.out.println("getMaximumLengthOfTitle: " + repository.getMaximumLengthOfTitle());
        System.out.println("getTitleOfMovieWithMaximumYear: " + repository.getTitleOfMovieWithMaximumYear());
        System.out.println("isYearPresent: " + repository.isYearPresent(repository.getAll().getFirst().getYear()));
        System.out.println("isActorNationalityPresent: " + repository.isActorNationalityPresent(repository.getAll().getFirst().getActors()[0].getNationality()));
        System.out.println("doAllMoviesHaveAtLeastActors: " + repository.doAllMoviesHaveAtLeastActors(1));
        System.out.println("getMoviesOrderByGenreDescThenByYearDesc: " + repository.getMoviesOrderByGenreDescThenByYearDesc());
        System.out.println("getMoviesOrderByGenreDescThenByYearDesc2: " + repository.getMoviesOrderByGenreDescThenByYearDesc2());
        System.out.println("getMoviesOrderByGenreThenByYearDesc: " + repository.getMoviesOrderByGenreThenByYearDesc());
        System.out.println("getMoviesOrderByGenreDescThenByYear: " + repository.getMoviesOrderByGenreDescThenByYear());
        System.out.println("getMoviesOrderByGenreThenByTitle: " + repository.getMoviesOrderByGenreThenByTitle());
        System.out.println("getFirstMovieByStudio: " + repository.getFirstMovieByStudio(Movie.Studio.PARAMOUNT_PICTURES));
        System.out.println("getFirstMovieByTitle: " + repository.getFirstMovieByTitle(repository.getAll().getFirst().getTitle()));
        System.out.println("getFirstMovieByActorName: " + repository.getFirstMovieByActorName(repository.getAll().getFirst().getActors()[0].getName()));
        System.out.println("getFirstActorByName: " + repository.getFirstActorByName(repository.getAll().getFirst().getActors()[0].getName()));
        System.out.println("getMovieWithMaximumYear: " + repository.getMovieWithMaximumYear());
        System.out.println("getMovieWithMostActors: " + repository.getMovieWithMostActors());
        System.out.println("getActorWithMaximumAge: " + repository.getActorWithMaximumAge());
        System.out.println("getMoviesFilterByStudioWithoutActor: " + repository.getMoviesFilterByStudioWithoutActor(Movie.Studio.PARAMOUNT_PICTURES, repository.getAll().getFirst().getActors()[0]));
        System.out.println("getMoviesFilterByStudioWithoutActorNationality: " + repository.getMoviesFilterByStudioWithoutActorNationality(Movie.Studio.PARAMOUNT_PICTURES, repository.getAll().getFirst().getActors()[0].getNationality()));
        System.out.println("getMoviesContainingActor: " + repository.getMoviesContainingActor(repository.getAll().getFirst().getActors()[0]));
        System.out.println("getMoviesAboveYearLimit: " + repository.getMoviesAboveYearLimit(repository.getAll().getFirst().getYear()));
        System.out.println("getMoviesFilterByCountOfActors: " + repository.getMoviesFilterByCountOfActors(2));
        System.out.println("getDistinctActorsInOrderFilterByStudio: " + repository.getDistinctActorsInOrderFilterByStudio(Movie.Studio.PARAMOUNT_PICTURES));
        System.out.println("getStudiosByActorNationality: " + repository.getStudiosByActorNationality(repository.getAll().getFirst().getActors()[0].getNationality()));
        System.out.println("getStudiosContainingActor: " + repository.getStudiosContainingActor(repository.getAll().getFirst().getActors()[0]));
        System.out.println("getGenreValuesByActorNationality: " + repository.getGenreValuesByActorNationality(repository.getAll().getFirst().getActors()[0].getNationality()));
        System.out.println("getActorsFilterByStudio: " + repository.getActorsFilterByStudio(Movie.Studio.PARAMOUNT_PICTURES));
        System.out.println("getStudiosAboveYearLimit: " + repository.getStudiosAboveYearLimit(repository.getAll().getFirst().getYear()));
        System.out.println("getActorNationalityValues: " + repository.getActorNationalityValues());
        System.out.println("getCountOfMoviesByStudios: " + repository.getCountOfMoviesByStudios());
        System.out.println("getCountOfMoviesByGenre: " + repository.getCountOfMoviesByGenre());
        System.out.println("getStudiosByMovies: " + repository.getStudiosByMovies());
        System.out.println("getFirstMovieByStudios: " + repository.getFirstMovieByStudios());
        System.out.println("getLastMovieByStudios: " + repository.getLastMovieByStudios());
        System.out.println("getGenreOfFirstMovieByStudios: " + repository.getGenreOfFirstMovieByStudios());
        System.out.println("getYearOfLastMovieByStudios: " + repository.getYearOfLastMovieByStudios());
        System.out.println("getFirstMovieByGenre: " + repository.getFirstMovieByGenre());
        System.out.println("getStudioOfLastMovieByGenre: " + repository.getStudioOfLastMovieByGenre());
        System.out.println("getSumOfYearByStudios: " + repository.getSumOfYearByStudios());
        System.out.println("getCountOfMoviesByStudiosFilterByCountOfActors: " + repository.getCountOfMoviesByStudiosFilterByCountOfActors(3));
        System.out.println("getCountOfMoviesByGenreValuesFilterByStudio: " + repository.getCountOfMoviesByGenreValuesFilterByStudio(Movie.Studio.PARAMOUNT_PICTURES));
        System.out.println("getAverageOfYearByStudios: " + repository.getAverageOfYearByStudios());
        System.out.println("getMaximumOfYearByStudios: " + repository.getMaximumOfYearByStudios());
        System.out.println("getSumOfActorAgeByStudios: " + repository.getSumOfActorAgeByStudios());
        System.out.println("getActorsByStudios: " + repository.getActorsByStudios());
        System.out.println("getMoviesByStudiosFilterByCountOfActors: " + repository.getMoviesByStudiosFilterByCountOfActors(3));
        System.out.println("getGenreValuesByStudiosFilterByYear: " + repository.getGenreValuesByStudiosFilterByYear(repository.getAll().getFirst().getYear()));
        System.out.println("getNationalityValuesOfActorsByGenreFilterByStudio: " + repository.getNationalityValuesOfActorsByGenreFilterByStudio(Movie.Studio.PARAMOUNT_PICTURES));
        System.out.println("getStudiosByActorNationalityFilterByCountOfActors: " + repository.getStudiosByActorNationalityFilterByCountOfActors(3));
        System.out.println("getCountOfMoviesByStudiosAndGenre: " + repository.getCountOfMoviesByStudiosAndGenre());
        System.out.println("getCountOfMoviesByGenreAndStudiosFilterByCountOfActors: " + repository.getCountOfMoviesByGenreAndStudiosFilterByCountOfActors(3));
        System.out.println("getCountOfActorsByStudiosAndNationality: " + repository.getCountOfActorsByStudiosAndNationality());
        System.out.println("getMaximumOfYearByStudiosAndGenre: " + repository.getMaximumOfYearByStudiosAndGenre());
    }

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

    @Override
    public List<Movie> getMoviesOrderByGenreDescThenByYearDesc() {
        List<Movie> result = new ArrayList<>(getAll());
        Collections.sort(result);
        return result;
    }

    @Override
    public List<Movie> getMoviesOrderByGenreDescThenByYearDesc2() {
        List<Movie> result = new ArrayList<>(getAll());
        Collections.sort(result, new cinema.MovieComparator());
        return result;
    }

    @Override
    public List<Movie> getMoviesOrderByGenreThenByYearDesc() {
        List<Movie> result = new ArrayList<>(getAll());
        Collections.sort(result, new Comparator<Movie>() {
            @Override
            public int compare(@NonNull Movie first, @NonNull Movie second) {
                int result = Objects.compare(first.getGenre(), second.getGenre(), Comparator.naturalOrder());
                if (result != 0) {
                    return result;
                }
                return Objects.compare(first.getYear(), second.getYear(), Comparator.reverseOrder());
            }
        });
        return result;
    }

    @Override
    public List<Movie> getMoviesOrderByGenreDescThenByYear() {
        List<Movie> result = new ArrayList<>(getAll());
        Collections.sort(result, new MovieComparator());
        return result;
    }

    @Override
    public List<Movie> getMoviesOrderByGenreThenByTitle() {
        List<Movie> result = new ArrayList<>(getAll());
        Collections.sort(result, new Movie.MovieComparator());
        return result;
    }

    @Override
    public Movie getFirstMovieByStudio(@NonNull Movie.Studio studio) throws NoResultException {
        for (Movie t : getAll()) {
            if (t.getStudio() == studio) {
                return t;
            }
        }
        throw new NoResultException();
    }

    @Override
    public Movie getFirstMovieByTitle(@NonNull String title) throws EmptyResultException {
        for (Movie t : getAll()) {
            if (t.getTitle().equals(title)) {
                return t;
            }
        }
        throw new EmptyResultException();
    }

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

    @Override
    public Map<Movie.Studio, Integer> getCountOfMoviesByStudios() {
        Map<Movie.Studio, Integer> result = new HashMap<>();
        for (Movie t : getAll()) {
            result.put(t.getStudio(), result.getOrDefault(t.getStudio(), 0) + 1);
        }
        return result;
    }

    @Override
    public Map<String, Integer> getCountOfMoviesByGenre() {
        Map<String, Integer> result = new HashMap<>();
        for (Movie t : getAll()) {
            result.put(t.getGenre(), result.getOrDefault(t.getGenre(), 0) + 1);
        }
        return result;
    }

    @Override
    public Map<Movie, Movie.Studio> getStudiosByMovies() {
        Map<Movie, Movie.Studio> result = new HashMap<>();
        for (Movie t : getAll()) {
            result.put(t, t.getStudio());
        }
        return result;
    }

    @Override
    public Map<Movie.Studio, Movie> getFirstMovieByStudios() {
        Map<Movie.Studio, Movie> result = new HashMap<>();
        for (Movie t : getAll()) {
            if (!result.containsKey(t.getStudio())) {
                result.put(t.getStudio(), t);
            }
        }
        return result;
    }

    @Override
    public Map<Movie.Studio, Movie> getLastMovieByStudios() {
        Map<Movie.Studio, Movie> result = new HashMap<>();
        for (Movie t : getAll()) {
            result.put(t.getStudio(), t);
        }
        return result;
    }

    @Override
    public Map<Movie.Studio, String> getGenreOfFirstMovieByStudios() {
        Map<Movie.Studio, String> result = new HashMap<>();
        for (Movie t : getAll()) {
            if (!result.containsKey(t.getStudio())) {
                result.put(t.getStudio(), t.getGenre());
            }
        }
        return result;
    }

    @Override
    public Map<Movie.Studio, Integer> getYearOfLastMovieByStudios() {
        Map<Movie.Studio, Integer> result = new HashMap<>();
        for (Movie t : getAll()) {
            result.put(t.getStudio(), t.getYear());
        }
        return result;
    }

    @Override
    public Map<String, Movie> getFirstMovieByGenre() {
        Map<String, Movie> result = new HashMap<>();
        for (Movie t : getAll()) {
            if (!result.containsKey(t.getGenre())) {
                result.put(t.getGenre(), t);
            }
        }
        return result;
    }

    @Override
    public Map<String, Movie.Studio> getStudioOfLastMovieByGenre() {
        Map<String, Movie.Studio> result = new HashMap<>();
        for (Movie t : getAll()) {
            result.put(t.getGenre(), t.getStudio());
        }
        return result;
    }

    @Override
    public Map<Movie.Studio, Integer> getSumOfYearByStudios() {
        Map<Movie.Studio, Integer> result = new HashMap<>();
        for (Movie t : getAll()) {
            result.put(t.getStudio(), result.getOrDefault(t.getStudio(), 0) + t.getYear());
        }
        return result;
    }

    @Override
    public Map<Movie.Studio, Integer> getCountOfMoviesByStudiosFilterByCountOfActors(int count) {
        Map<Movie.Studio, Integer> result = new HashMap<>();
        for (Movie t : getAll()) {
            if (t.getActors().length >= count) {
                result.put(t.getStudio(), result.getOrDefault(t.getStudio(), 0) + 1);
            }
        }
        return result;
    }

    @Override
    public Map<String, Integer> getCountOfMoviesByGenreValuesFilterByStudio(@NonNull Movie.Studio studio) {
        Map<String, Integer> result = new HashMap<>();
        for (Movie t : getAll()) {
            if (t.getStudio() == studio) {
                result.put(t.getGenre(), result.getOrDefault(t.getGenre(), 0) + 1);
            }
        }
        return result;
    }

    @Override
    public Map<Movie.Studio, Double> getAverageOfYearByStudios() {
        Map<Movie.Studio, Double> sums = new HashMap<>();
        Map<Movie.Studio, Integer> counts = new HashMap<>();
        for (Movie t : getAll()) {
            sums.put(t.getStudio(), sums.getOrDefault(t.getStudio(), 0.0) + t.getYear());
            counts.put(t.getStudio(), counts.getOrDefault(t.getStudio(), 0) + 1);
        }
        Map<Movie.Studio, Double> result = new HashMap<>();
        for (Movie.Studio key : sums.keySet()) {
            result.put(key, sums.get(key) / counts.get(key));
        }
        return result;
    }

    @Override
    public Map<Movie.Studio, Integer> getMaximumOfYearByStudios() {
        Map<Movie.Studio, Integer> result = new HashMap<>();
        for (Movie t : getAll()) {
            if (!result.containsKey(t.getStudio()) || t.getYear() > result.get(t.getStudio())) {
                result.put(t.getStudio(), t.getYear());
            }
        }
        return result;
    }

    @Override
    public Map<Movie.Studio, Integer> getSumOfActorAgeByStudios() {
        Map<Movie.Studio, Integer> result = new HashMap<>();
        for (Movie t : getAll()) {
            int sum = 0;
            for (Movie.Actor u : t.getActors()) {
                sum += u.getAge();
            }
            result.put(t.getStudio(), result.getOrDefault(t.getStudio(), 0) + sum);
        }
        return result;
    }

    @Override
    public Map<Movie.Studio, Set<Movie.Actor>> getActorsByStudios() {
        Map<Movie.Studio, Set<Movie.Actor>> result = new HashMap<>();
        for (Movie t : getAll()) {
            if (!result.containsKey(t.getStudio())) {
                result.put(t.getStudio(), new HashSet<>());
            }
            for (Movie.Actor u : t.getActors()) {
                result.get(t.getStudio()).add(u);
            }
        }
        return result;
    }

    @Override
    public Map<Movie.Studio, List<Movie>> getMoviesByStudiosFilterByCountOfActors(int count) {
        Map<Movie.Studio, List<Movie>> result = new HashMap<>();
        for (Movie t : getAll()) {
            if (t.getActors().length >= count) {
                if (!result.containsKey(t.getStudio())) {
                    result.put(t.getStudio(), new ArrayList<>());
                }
                result.get(t.getStudio()).add(t);
            }
        }
        return result;
    }

    @Override
    public Map<Movie.Studio, Set<String>> getGenreValuesByStudiosFilterByYear(int year) {
        Map<Movie.Studio, Set<String>> result = new HashMap<>();
        for (Movie t : getAll()) {
            if (t.getYear() > year) {
                if (!result.containsKey(t.getStudio())) {
                    result.put(t.getStudio(), new HashSet<>());
                }
                result.get(t.getStudio()).add(t.getGenre());
            }
        }
        return result;
    }

    @Override
    public Map<String, Set<String>> getNationalityValuesOfActorsByGenreFilterByStudio(@NonNull Movie.Studio studio) {
        Map<String, Set<String>> result = new HashMap<>();
        for (Movie t : getAll()) {
            if (t.getStudio() == studio) {
                if (!result.containsKey(t.getGenre())) {
                    result.put(t.getGenre(), new HashSet<>());
                }
                for (Movie.Actor u : t.getActors()) {
                    result.get(t.getGenre()).add(u.getNationality());
                }
            }
        }
        return result;
    }

    @Override
    public Map<String, Set<Movie.Studio>> getStudiosByActorNationalityFilterByCountOfActors(int count) {
        Map<String, Set<Movie.Studio>> result = new HashMap<>();
        for (Movie t : getAll()) {
            if (t.getActors().length >= count) {
                for (Movie.Actor u : t.getActors()) {
                    if (!result.containsKey(u.getNationality())) {
                        result.put(u.getNationality(), new HashSet<>());
                    }
                    result.get(u.getNationality()).add(t.getStudio());
                }
            }
        }
        return result;
    }

    @Override
    public Map<Movie.Studio, Map<String, Integer>> getCountOfMoviesByStudiosAndGenre() {
        Map<Movie.Studio, Map<String, Integer>> result = new HashMap<>();
        for (Movie t : getAll()) {
            if (!result.containsKey(t.getStudio())) {
                result.put(t.getStudio(), new HashMap<>());
            }
            Map<String, Integer> inner = result.get(t.getStudio());
            inner.put(t.getGenre(), inner.getOrDefault(t.getGenre(), 0) + 1);
        }
        return result;
    }

    @Override
    public Map<String, Map<Movie.Studio, Integer>> getCountOfMoviesByGenreAndStudiosFilterByCountOfActors(int count) {
        Map<String, Map<Movie.Studio, Integer>> result = new HashMap<>();
        for (Movie t : getAll()) {
            if (t.getActors().length >= count) {
                if (!result.containsKey(t.getGenre())) {
                    result.put(t.getGenre(), new HashMap<>());
                }
                Map<Movie.Studio, Integer> inner = result.get(t.getGenre());
                inner.put(t.getStudio(), inner.getOrDefault(t.getStudio(), 0) + 1);
            }
        }
        return result;
    }

    @Override
    public Map<Movie.Studio, Map<String, Integer>> getCountOfActorsByStudiosAndNationality() {
        Map<Movie.Studio, Map<String, Integer>> result = new HashMap<>();
        for (Movie t : getAll()) {
            if (!result.containsKey(t.getStudio())) {
                result.put(t.getStudio(), new HashMap<>());
            }
            Map<String, Integer> inner = result.get(t.getStudio());
            for (Movie.Actor u : t.getActors()) {
                inner.put(u.getNationality(), inner.getOrDefault(u.getNationality(), 0) + 1);
            }
        }
        return result;
    }

    @Override
    public Map<Movie.Studio, Map<String, Integer>> getMaximumOfYearByStudiosAndGenre() {
        Map<Movie.Studio, Map<String, Integer>> result = new HashMap<>();
        for (Movie t : getAll()) {
            if (!result.containsKey(t.getStudio())) {
                result.put(t.getStudio(), new HashMap<>());
            }
            Map<String, Integer> inner = result.get(t.getStudio());
            if (!inner.containsKey(t.getGenre()) || t.getYear() > inner.get(t.getGenre())) {
                inner.put(t.getGenre(), t.getYear());
            }
        }
        return result;
    }

    /**
     * Compares movies.
     */
    public static class MovieComparator implements Comparator<Movie> {

        @Override
        public int compare(@NonNull Movie first, @NonNull Movie second) {
            int result = Objects.compare(first.getGenre(), second.getGenre(), Comparator.reverseOrder());
            if (result != 0) {
                return result;
            }
            return Objects.compare(first.getYear(), second.getYear(), Comparator.naturalOrder());
        }
    }
}
