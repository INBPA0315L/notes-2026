package arcade;

import base.*;
import lombok.NonNull;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The reference solution (generated).
 */
public class GameRepository extends Repository<Game> implements AggregationQueries<Game, Game.Character, Game.Platform>,
        SortingQueries<Game, Game.Character, Game.Platform>,
        SearchQueries<Game, Game.Character, Game.Platform>,
        FilteringQueries<Game, Game.Character, Game.Platform>,
        SetQueries<Game, Game.Character, Game.Platform>,
        BasicMapQueries<Game, Game.Character, Game.Platform>,
        AggregationMapQueries<Game, Game.Character, Game.Platform>,
        GroupingMapQueries<Game, Game.Character, Game.Platform>,
        NestedMapQueries<Game, Game.Character, Game.Platform> {

    public GameRepository() throws IOException {
        super(Game.class);
    }

    public static void main(String[] args) throws Exception {
        GameRepository repository = new GameRepository();
        System.out.println(repository);
        System.out.println("getMaximumCountOfCharacters: " + repository.getMaximumCountOfCharacters());
        System.out.println("getMinimumCountOfCharacters: " + repository.getMinimumCountOfCharacters());
        System.out.println("getTotalCountOfCharactersFilterByPlatform: " + repository.getTotalCountOfCharactersFilterByPlatform(Game.Platform.NEO_GEO));
        System.out.println("getMaximumOfReleased: " + repository.getMaximumOfReleased());
        System.out.println("getMinimumOfReleased: " + repository.getMinimumOfReleased());
        System.out.println("getAverageOfReleasedFilterByPlatform: " + repository.getAverageOfReleasedFilterByPlatform(Game.Platform.NEO_GEO));
        System.out.println("getMaximumOfCharacterGames: " + repository.getMaximumOfCharacterGames());
        System.out.println("getAverageOfCharacterGamesFilterByPlatform: " + repository.getAverageOfCharacterGamesFilterByPlatform(Game.Platform.NEO_GEO));
        System.out.println("getCountOfGamesFilterByPlatform: " + repository.getCountOfGamesFilterByPlatform(Game.Platform.NEO_GEO));
        System.out.println("getCountOfGamesAboveReleasedLimit: " + repository.getCountOfGamesAboveReleasedLimit(repository.getAll().getFirst().getReleased()));
        System.out.println("getCountOfGamesByNamePrefix: " + repository.getCountOfGamesByNamePrefix(repository.getAll().getFirst().getName().substring(0, 1)));
        System.out.println("getCountOfGamesByCharacterName: " + repository.getCountOfGamesByCharacterName(repository.getAll().getFirst().getCharacters()[0].getName()));
        System.out.println("getCountOfGamesWithoutCharacters: " + repository.getCountOfGamesWithoutCharacters());
        System.out.println("getCountOfCharactersByName: " + repository.getCountOfCharactersByName(repository.getAll().getFirst().getCharacters()[0].getName()));
        System.out.println("getSumOfReleasedFilterByPlatform: " + repository.getSumOfReleasedFilterByPlatform(Game.Platform.NEO_GEO));
        System.out.println("getSumOfCharacterGamesByName: " + repository.getSumOfCharacterGamesByName(repository.getAll().getFirst().getCharacters()[0].getName()));
        System.out.println("getMinimumOfCharacterGames: " + repository.getMinimumOfCharacterGames());
        System.out.println("getAverageCountOfCharactersFilterByPlatform: " + repository.getAverageCountOfCharactersFilterByPlatform(Game.Platform.NEO_GEO));
        System.out.println("getMaximumLengthOfName: " + repository.getMaximumLengthOfName());
        System.out.println("getNameOfGameWithMaximumReleased: " + repository.getNameOfGameWithMaximumReleased());
        System.out.println("isReleasedPresent: " + repository.isReleasedPresent(repository.getAll().getFirst().getReleased()));
        System.out.println("isCharacterNamePresent: " + repository.isCharacterNamePresent(repository.getAll().getFirst().getCharacters()[0].getName()));
        System.out.println("doAllGamesHaveAtLeastCharacters: " + repository.doAllGamesHaveAtLeastCharacters(1));
        System.out.println("getGamesOrderByReleasedDescThenByDescriptionDescThenByNameDesc: " + repository.getGamesOrderByReleasedDescThenByDescriptionDescThenByNameDesc());
        System.out.println("getGamesOrderByReleasedThenByDescriptionThenByName: " + repository.getGamesOrderByReleasedThenByDescriptionThenByName());
        System.out.println("getGamesOrderByReleasedThenByDescriptionDescThenByNameDesc: " + repository.getGamesOrderByReleasedThenByDescriptionDescThenByNameDesc());
        System.out.println("getGamesOrderByDescriptionDescThenByReleasedDescThenByNameDesc: " + repository.getGamesOrderByDescriptionDescThenByReleasedDescThenByNameDesc());
        System.out.println("getGamesOrderByDescriptionDescThenByReleasedThenByNameDesc: " + repository.getGamesOrderByDescriptionDescThenByReleasedThenByNameDesc());
        System.out.println("getFirstGameByPlatform: " + repository.getFirstGameByPlatform(Game.Platform.NEO_GEO));
        System.out.println("getFirstGameByName: " + repository.getFirstGameByName(repository.getAll().getFirst().getName()));
        System.out.println("getFirstGameByCharacterName: " + repository.getFirstGameByCharacterName(repository.getAll().getFirst().getCharacters()[0].getName()));
        System.out.println("getFirstCharacterByName: " + repository.getFirstCharacterByName(repository.getAll().getFirst().getCharacters()[0].getName()));
        System.out.println("getGameWithMaximumReleased: " + repository.getGameWithMaximumReleased());
        System.out.println("getGameWithMostCharacters: " + repository.getGameWithMostCharacters());
        System.out.println("getCharacterWithMaximumGames: " + repository.getCharacterWithMaximumGames());
        System.out.println("getGamesFilterByPlatformWithoutCharacter: " + repository.getGamesFilterByPlatformWithoutCharacter(Game.Platform.NEO_GEO, repository.getAll().getFirst().getCharacters()[0]));
        System.out.println("getGamesFilterByPlatformWithoutCharacterName: " + repository.getGamesFilterByPlatformWithoutCharacterName(Game.Platform.NEO_GEO, repository.getAll().getFirst().getCharacters()[0].getName()));
        System.out.println("getGamesContainingCharacter: " + repository.getGamesContainingCharacter(repository.getAll().getFirst().getCharacters()[0]));
        System.out.println("getGamesAboveReleasedLimit: " + repository.getGamesAboveReleasedLimit(repository.getAll().getFirst().getReleased()));
        System.out.println("getGamesFilterByCountOfCharacters: " + repository.getGamesFilterByCountOfCharacters(2));
        System.out.println("getDistinctCharactersInOrderFilterByPlatform: " + repository.getDistinctCharactersInOrderFilterByPlatform(Game.Platform.NEO_GEO));
        System.out.println("getPlatformsByCharacterName: " + repository.getPlatformsByCharacterName(repository.getAll().getFirst().getCharacters()[0].getName()));
        System.out.println("getPlatformsContainingCharacter: " + repository.getPlatformsContainingCharacter(repository.getAll().getFirst().getCharacters()[0]));
        System.out.println("getNameValuesByCharacterName: " + repository.getNameValuesByCharacterName(repository.getAll().getFirst().getCharacters()[0].getName()));
        System.out.println("getCharactersFilterByPlatform: " + repository.getCharactersFilterByPlatform(Game.Platform.NEO_GEO));
        System.out.println("getPlatformsAboveReleasedLimit: " + repository.getPlatformsAboveReleasedLimit(repository.getAll().getFirst().getReleased()));
        System.out.println("getCharacterGamesValues: " + repository.getCharacterGamesValues());
        System.out.println("getCountOfGamesByPlatforms: " + repository.getCountOfGamesByPlatforms());
        System.out.println("getCountOfGamesByReleased: " + repository.getCountOfGamesByReleased());
        System.out.println("getPlatformsByGames: " + repository.getPlatformsByGames());
        System.out.println("getFirstGameByPlatforms: " + repository.getFirstGameByPlatforms());
        System.out.println("getLastGameByPlatforms: " + repository.getLastGameByPlatforms());
        System.out.println("getReleasedOfFirstGameByPlatforms: " + repository.getReleasedOfFirstGameByPlatforms());
        System.out.println("getNameOfLastGameByPlatforms: " + repository.getNameOfLastGameByPlatforms());
        System.out.println("getFirstGameByReleased: " + repository.getFirstGameByReleased());
        System.out.println("getPlatformOfLastGameByReleased: " + repository.getPlatformOfLastGameByReleased());
        System.out.println("getSumOfReleasedByPlatforms: " + repository.getSumOfReleasedByPlatforms());
        System.out.println("getCountOfGamesByPlatformsFilterByCountOfCharacters: " + repository.getCountOfGamesByPlatformsFilterByCountOfCharacters(3));
        System.out.println("getCountOfGamesByReleasedValuesFilterByPlatform: " + repository.getCountOfGamesByReleasedValuesFilterByPlatform(Game.Platform.NEO_GEO));
        System.out.println("getAverageOfReleasedByPlatforms: " + repository.getAverageOfReleasedByPlatforms());
        System.out.println("getMaximumOfReleasedByPlatforms: " + repository.getMaximumOfReleasedByPlatforms());
        System.out.println("getSumOfCharacterGamesByPlatforms: " + repository.getSumOfCharacterGamesByPlatforms());
        System.out.println("getCharactersByPlatforms: " + repository.getCharactersByPlatforms());
        System.out.println("getGamesByPlatformsFilterByCountOfCharacters: " + repository.getGamesByPlatformsFilterByCountOfCharacters(3));
        System.out.println("getGamesValuesOfCharactersByReleasedFilterByPlatform: " + repository.getGamesValuesOfCharactersByReleasedFilterByPlatform(Game.Platform.NEO_GEO));
        System.out.println("getPlatformsByCharacterGamesFilterByCountOfCharacters: " + repository.getPlatformsByCharacterGamesFilterByCountOfCharacters(3));
        System.out.println("getCountOfGamesByPlatformsAndReleased: " + repository.getCountOfGamesByPlatformsAndReleased());
        System.out.println("getCountOfGamesByReleasedAndPlatformsFilterByCountOfCharacters: " + repository.getCountOfGamesByReleasedAndPlatformsFilterByCountOfCharacters(3));
        System.out.println("getCountOfCharactersByPlatformsAndGames: " + repository.getCountOfCharactersByPlatformsAndGames());
    }

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

    @Override
    public List<Game> getGamesOrderByReleasedDescThenByDescriptionDescThenByNameDesc() {
        List<Game> result = new ArrayList<>(getAll());
        Collections.sort(result);
        return result;
    }

    @Override
    public List<Game> getGamesOrderByReleasedThenByDescriptionThenByName() {
        List<Game> result = new ArrayList<>(getAll());
        Collections.sort(result, new arcade.GameComparator());
        return result;
    }

    @Override
    public List<Game> getGamesOrderByReleasedThenByDescriptionDescThenByNameDesc() {
        List<Game> result = new ArrayList<>(getAll());
        Collections.sort(result, new Comparator<Game>() {
            @Override
            public int compare(@NonNull Game first, @NonNull Game second) {
                int result = Objects.compare(first.getReleased(), second.getReleased(), Comparator.naturalOrder());
                if (result != 0) {
                    return result;
                }
                result = Objects.compare(first.getDescription(), second.getDescription(), Comparator.reverseOrder());
                if (result != 0) {
                    return result;
                }
                return Objects.compare(first.getName(), second.getName(), Comparator.reverseOrder());
            }
        });
        return result;
    }

    @Override
    public List<Game> getGamesOrderByDescriptionDescThenByReleasedDescThenByNameDesc() {
        List<Game> result = new ArrayList<>(getAll());
        Collections.sort(result, new GameComparator());
        return result;
    }

    @Override
    public List<Game> getGamesOrderByDescriptionDescThenByReleasedThenByNameDesc() {
        List<Game> result = new ArrayList<>(getAll());
        Collections.sort(result, new Game.GameComparator());
        return result;
    }

    @Override
    public Game getFirstGameByPlatform(@NonNull Game.Platform platform) {
        for (Game t : getAll()) {
            if (t.getPlatform() == platform) {
                return t;
            }
        }
        return null;
    }

    @Override
    public Game getFirstGameByName(@NonNull String name) throws NotFoundException {
        for (Game t : getAll()) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        throw new NotFoundException();
    }

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

    @Override
    public List<Game> getGamesFilterByPlatformWithoutCharacter(@NonNull Game.Platform platform, @NonNull Game.Character character) {
        List<Game> result = new ArrayList<>();
        for (Game t : getAll()) {
            if (t.getPlatform() == platform) {
                boolean contains = false;
                for (Game.Character u : t.getCharacters()) {
                    if (u.equals(character)) {
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
    public List<Game> getGamesFilterByPlatformWithoutCharacterName(@NonNull Game.Platform platform, @NonNull String name) {
        List<Game> result = new ArrayList<>();
        for (Game t : getAll()) {
            if (t.getPlatform() == platform) {
                boolean contains = false;
                for (Game.Character u : t.getCharacters()) {
                    if (u.getName().equals(name)) {
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
    public List<Game> getGamesContainingCharacter(@NonNull Game.Character character) {
        List<Game> result = new ArrayList<>();
        for (Game t : getAll()) {
            boolean contains = false;
            for (Game.Character u : t.getCharacters()) {
                if (u.equals(character)) {
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
    public List<Game> getGamesAboveReleasedLimit(int released) {
        List<Game> result = new ArrayList<>();
        for (Game t : getAll()) {
            if (t.getReleased() > released) {
                result.add(t);
            }
        }
        return result;
    }

    @Override
    public List<Game> getGamesFilterByCountOfCharacters(int count) {
        List<Game> result = new ArrayList<>();
        for (Game t : getAll()) {
            if (t.getCharacters().length >= count) {
                result.add(t);
            }
        }
        return result;
    }

    @Override
    public List<Game.Character> getDistinctCharactersInOrderFilterByPlatform(@NonNull Game.Platform platform) {
        List<Game.Character> result = new ArrayList<>();
        for (Game t : getAll()) {
            if (t.getPlatform() == platform) {
                for (Game.Character u : t.getCharacters()) {
                    if (!result.contains(u)) {
                        result.add(u);
                    }
                }
            }
        }
        return result;
    }

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

    @Override
    public Map<Game.Platform, Integer> getCountOfGamesByPlatforms() {
        Map<Game.Platform, Integer> result = new HashMap<>();
        for (Game t : getAll()) {
            result.put(t.getPlatform(), result.getOrDefault(t.getPlatform(), 0) + 1);
        }
        return result;
    }

    @Override
    public Map<Integer, Integer> getCountOfGamesByReleased() {
        Map<Integer, Integer> result = new HashMap<>();
        for (Game t : getAll()) {
            result.put(t.getReleased(), result.getOrDefault(t.getReleased(), 0) + 1);
        }
        return result;
    }

    @Override
    public Map<Game, Game.Platform> getPlatformsByGames() {
        Map<Game, Game.Platform> result = new HashMap<>();
        for (Game t : getAll()) {
            result.put(t, t.getPlatform());
        }
        return result;
    }

    @Override
    public Map<Game.Platform, Game> getFirstGameByPlatforms() {
        Map<Game.Platform, Game> result = new HashMap<>();
        for (Game t : getAll()) {
            if (!result.containsKey(t.getPlatform())) {
                result.put(t.getPlatform(), t);
            }
        }
        return result;
    }

    @Override
    public Map<Game.Platform, Game> getLastGameByPlatforms() {
        Map<Game.Platform, Game> result = new HashMap<>();
        for (Game t : getAll()) {
            result.put(t.getPlatform(), t);
        }
        return result;
    }

    @Override
    public Map<Game.Platform, Integer> getReleasedOfFirstGameByPlatforms() {
        Map<Game.Platform, Integer> result = new HashMap<>();
        for (Game t : getAll()) {
            if (!result.containsKey(t.getPlatform())) {
                result.put(t.getPlatform(), t.getReleased());
            }
        }
        return result;
    }

    @Override
    public Map<Game.Platform, String> getNameOfLastGameByPlatforms() {
        Map<Game.Platform, String> result = new HashMap<>();
        for (Game t : getAll()) {
            result.put(t.getPlatform(), t.getName());
        }
        return result;
    }

    @Override
    public Map<Integer, Game> getFirstGameByReleased() {
        Map<Integer, Game> result = new HashMap<>();
        for (Game t : getAll()) {
            if (!result.containsKey(t.getReleased())) {
                result.put(t.getReleased(), t);
            }
        }
        return result;
    }

    @Override
    public Map<Integer, Game.Platform> getPlatformOfLastGameByReleased() {
        Map<Integer, Game.Platform> result = new HashMap<>();
        for (Game t : getAll()) {
            result.put(t.getReleased(), t.getPlatform());
        }
        return result;
    }

    @Override
    public Map<Game.Platform, Integer> getSumOfReleasedByPlatforms() {
        Map<Game.Platform, Integer> result = new HashMap<>();
        for (Game t : getAll()) {
            result.put(t.getPlatform(), result.getOrDefault(t.getPlatform(), 0) + t.getReleased());
        }
        return result;
    }

    @Override
    public Map<Game.Platform, Integer> getCountOfGamesByPlatformsFilterByCountOfCharacters(int count) {
        Map<Game.Platform, Integer> result = new HashMap<>();
        for (Game t : getAll()) {
            if (t.getCharacters().length >= count) {
                result.put(t.getPlatform(), result.getOrDefault(t.getPlatform(), 0) + 1);
            }
        }
        return result;
    }

    @Override
    public Map<Integer, Integer> getCountOfGamesByReleasedValuesFilterByPlatform(@NonNull Game.Platform platform) {
        Map<Integer, Integer> result = new HashMap<>();
        for (Game t : getAll()) {
            if (t.getPlatform() == platform) {
                result.put(t.getReleased(), result.getOrDefault(t.getReleased(), 0) + 1);
            }
        }
        return result;
    }

    @Override
    public Map<Game.Platform, Double> getAverageOfReleasedByPlatforms() {
        Map<Game.Platform, Double> sums = new HashMap<>();
        Map<Game.Platform, Integer> counts = new HashMap<>();
        for (Game t : getAll()) {
            sums.put(t.getPlatform(), sums.getOrDefault(t.getPlatform(), 0.0) + t.getReleased());
            counts.put(t.getPlatform(), counts.getOrDefault(t.getPlatform(), 0) + 1);
        }
        Map<Game.Platform, Double> result = new HashMap<>();
        for (Game.Platform key : sums.keySet()) {
            result.put(key, sums.get(key) / counts.get(key));
        }
        return result;
    }

    @Override
    public Map<Game.Platform, Integer> getMaximumOfReleasedByPlatforms() {
        Map<Game.Platform, Integer> result = new HashMap<>();
        for (Game t : getAll()) {
            if (!result.containsKey(t.getPlatform()) || t.getReleased() > result.get(t.getPlatform())) {
                result.put(t.getPlatform(), t.getReleased());
            }
        }
        return result;
    }

    @Override
    public Map<Game.Platform, Integer> getSumOfCharacterGamesByPlatforms() {
        Map<Game.Platform, Integer> result = new HashMap<>();
        for (Game t : getAll()) {
            int sum = 0;
            for (Game.Character u : t.getCharacters()) {
                sum += u.getGames();
            }
            result.put(t.getPlatform(), result.getOrDefault(t.getPlatform(), 0) + sum);
        }
        return result;
    }

    @Override
    public Map<Game.Platform, Set<Game.Character>> getCharactersByPlatforms() {
        Map<Game.Platform, Set<Game.Character>> result = new HashMap<>();
        for (Game t : getAll()) {
            if (!result.containsKey(t.getPlatform())) {
                result.put(t.getPlatform(), new HashSet<>());
            }
            for (Game.Character u : t.getCharacters()) {
                result.get(t.getPlatform()).add(u);
            }
        }
        return result;
    }

    @Override
    public Map<Game.Platform, List<Game>> getGamesByPlatformsFilterByCountOfCharacters(int count) {
        Map<Game.Platform, List<Game>> result = new HashMap<>();
        for (Game t : getAll()) {
            if (t.getCharacters().length >= count) {
                if (!result.containsKey(t.getPlatform())) {
                    result.put(t.getPlatform(), new ArrayList<>());
                }
                result.get(t.getPlatform()).add(t);
            }
        }
        return result;
    }

    @Override
    public Map<Integer, Set<Integer>> getGamesValuesOfCharactersByReleasedFilterByPlatform(@NonNull Game.Platform platform) {
        Map<Integer, Set<Integer>> result = new HashMap<>();
        for (Game t : getAll()) {
            if (t.getPlatform() == platform) {
                if (!result.containsKey(t.getReleased())) {
                    result.put(t.getReleased(), new HashSet<>());
                }
                for (Game.Character u : t.getCharacters()) {
                    result.get(t.getReleased()).add(u.getGames());
                }
            }
        }
        return result;
    }

    @Override
    public Map<Integer, Set<Game.Platform>> getPlatformsByCharacterGamesFilterByCountOfCharacters(int count) {
        Map<Integer, Set<Game.Platform>> result = new HashMap<>();
        for (Game t : getAll()) {
            if (t.getCharacters().length >= count) {
                for (Game.Character u : t.getCharacters()) {
                    if (!result.containsKey(u.getGames())) {
                        result.put(u.getGames(), new HashSet<>());
                    }
                    result.get(u.getGames()).add(t.getPlatform());
                }
            }
        }
        return result;
    }

    @Override
    public Map<Game.Platform, Map<Integer, Integer>> getCountOfGamesByPlatformsAndReleased() {
        Map<Game.Platform, Map<Integer, Integer>> result = new HashMap<>();
        for (Game t : getAll()) {
            if (!result.containsKey(t.getPlatform())) {
                result.put(t.getPlatform(), new HashMap<>());
            }
            Map<Integer, Integer> inner = result.get(t.getPlatform());
            inner.put(t.getReleased(), inner.getOrDefault(t.getReleased(), 0) + 1);
        }
        return result;
    }

    @Override
    public Map<Integer, Map<Game.Platform, Integer>> getCountOfGamesByReleasedAndPlatformsFilterByCountOfCharacters(int count) {
        Map<Integer, Map<Game.Platform, Integer>> result = new HashMap<>();
        for (Game t : getAll()) {
            if (t.getCharacters().length >= count) {
                if (!result.containsKey(t.getReleased())) {
                    result.put(t.getReleased(), new HashMap<>());
                }
                Map<Game.Platform, Integer> inner = result.get(t.getReleased());
                inner.put(t.getPlatform(), inner.getOrDefault(t.getPlatform(), 0) + 1);
            }
        }
        return result;
    }

    @Override
    public Map<Game.Platform, Map<Integer, Integer>> getCountOfCharactersByPlatformsAndGames() {
        Map<Game.Platform, Map<Integer, Integer>> result = new HashMap<>();
        for (Game t : getAll()) {
            if (!result.containsKey(t.getPlatform())) {
                result.put(t.getPlatform(), new HashMap<>());
            }
            Map<Integer, Integer> inner = result.get(t.getPlatform());
            for (Game.Character u : t.getCharacters()) {
                inner.put(u.getGames(), inner.getOrDefault(u.getGames(), 0) + 1);
            }
        }
        return result;
    }

    /**
     * Compares games.
     */
    public static class GameComparator implements Comparator<Game> {

        @Override
        public int compare(@NonNull Game first, @NonNull Game second) {
            int result = Objects.compare(first.getDescription(), second.getDescription(), Comparator.reverseOrder());
            if (result != 0) {
                return result;
            }
            result = Objects.compare(first.getReleased(), second.getReleased(), Comparator.reverseOrder());
            if (result != 0) {
                return result;
            }
            return Objects.compare(first.getName(), second.getName(), Comparator.reverseOrder());
        }
    }
}
