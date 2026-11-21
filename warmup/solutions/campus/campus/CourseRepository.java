package campus;

import base.*;
import lombok.NonNull;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The reference solution (generated).
 */
public class CourseRepository extends Repository<Course> implements AggregationQueries<Course, Course.Student, Course.Faculty>,
        SortingQueries<Course, Course.Student, Course.Faculty>,
        SearchQueries<Course, Course.Student, Course.Faculty>,
        FilteringQueries<Course, Course.Student, Course.Faculty>,
        SetQueries<Course, Course.Student, Course.Faculty>,
        BasicMapQueries<Course, Course.Student, Course.Faculty>,
        AggregationMapQueries<Course, Course.Student, Course.Faculty>,
        GroupingMapQueries<Course, Course.Student, Course.Faculty>,
        NestedMapQueries<Course, Course.Student, Course.Faculty> {

    public CourseRepository() throws IOException {
        super(Course.class);
    }

    public static void main(String[] args) throws Exception {
        CourseRepository repository = new CourseRepository();
        System.out.println(repository);
        System.out.println("getMaximumCountOfStudents: " + repository.getMaximumCountOfStudents());
        System.out.println("getMinimumCountOfStudents: " + repository.getMinimumCountOfStudents());
        System.out.println("getTotalCountOfStudentsFilterByFaculty: " + repository.getTotalCountOfStudentsFilterByFaculty(Course.Faculty.INFORMATICS));
        System.out.println("getMaximumOfSemester: " + repository.getMaximumOfSemester());
        System.out.println("getMinimumOfSemester: " + repository.getMinimumOfSemester());
        System.out.println("getAverageOfCreditsFilterByFaculty: " + repository.getAverageOfCreditsFilterByFaculty(Course.Faculty.INFORMATICS));
        System.out.println("getMaximumOfStudentGrade: " + repository.getMaximumOfStudentGrade());
        System.out.println("getAverageOfStudentGradeFilterByFaculty: " + repository.getAverageOfStudentGradeFilterByFaculty(Course.Faculty.INFORMATICS));
        System.out.println("getCountOfCoursesFilterByFaculty: " + repository.getCountOfCoursesFilterByFaculty(Course.Faculty.INFORMATICS));
        System.out.println("getCountOfCoursesAboveCreditsLimit: " + repository.getCountOfCoursesAboveCreditsLimit(repository.getAll().getFirst().getCredits()));
        System.out.println("getCountOfCoursesByTitlePrefix: " + repository.getCountOfCoursesByTitlePrefix(repository.getAll().getFirst().getTitle().substring(0, 1)));
        System.out.println("getCountOfCoursesByStudentName: " + repository.getCountOfCoursesByStudentName(repository.getAll().getFirst().getStudents()[0].getName()));
        System.out.println("getCountOfCoursesWithoutStudents: " + repository.getCountOfCoursesWithoutStudents());
        System.out.println("getCountOfStudentsByNeptun: " + repository.getCountOfStudentsByNeptun(repository.getAll().getFirst().getStudents()[0].getNeptun()));
        System.out.println("getSumOfSemesterFilterByFaculty: " + repository.getSumOfSemesterFilterByFaculty(Course.Faculty.INFORMATICS));
        System.out.println("getSumOfStudentGradeByNeptun: " + repository.getSumOfStudentGradeByNeptun(repository.getAll().getFirst().getStudents()[0].getNeptun()));
        System.out.println("getMinimumOfStudentGrade: " + repository.getMinimumOfStudentGrade());
        System.out.println("getAverageCountOfStudentsFilterByFaculty: " + repository.getAverageCountOfStudentsFilterByFaculty(Course.Faculty.INFORMATICS));
        System.out.println("getMaximumLengthOfCode: " + repository.getMaximumLengthOfCode());
        System.out.println("getCodeOfCourseWithMaximumCredits: " + repository.getCodeOfCourseWithMaximumCredits());
        System.out.println("isSemesterPresent: " + repository.isSemesterPresent(repository.getAll().getFirst().getSemester()));
        System.out.println("isStudentNeptunPresent: " + repository.isStudentNeptunPresent(repository.getAll().getFirst().getStudents()[0].getNeptun()));
        System.out.println("doAllCoursesHaveAtLeastStudents: " + repository.doAllCoursesHaveAtLeastStudents(1));
        System.out.println("getCoursesOrderBySemesterDescThenByTitleDescThenByCode: " + repository.getCoursesOrderBySemesterDescThenByTitleDescThenByCode());
        System.out.println("getCoursesOrderByTitleThenBySemesterThenByCodeDesc: " + repository.getCoursesOrderByTitleThenBySemesterThenByCodeDesc());
        System.out.println("getCoursesOrderBySemesterThenByTitleDescThenByCodeDesc: " + repository.getCoursesOrderBySemesterThenByTitleDescThenByCodeDesc());
        System.out.println("getCoursesOrderByCreditsThenByTitleDescThenByCodeDesc: " + repository.getCoursesOrderByCreditsThenByTitleDescThenByCodeDesc());
        System.out.println("getCoursesOrderByCreditsThenByTitleDescThenByCode: " + repository.getCoursesOrderByCreditsThenByTitleDescThenByCode());
        System.out.println("getFirstCourseByFaculty: " + repository.getFirstCourseByFaculty(Course.Faculty.INFORMATICS));
        System.out.println("getFirstCourseByCode: " + repository.getFirstCourseByCode(repository.getAll().getFirst().getCode()));
        System.out.println("getFirstCourseByStudentName: " + repository.getFirstCourseByStudentName(repository.getAll().getFirst().getStudents()[0].getName()));
        System.out.println("getFirstStudentByName: " + repository.getFirstStudentByName(repository.getAll().getFirst().getStudents()[0].getName()));
        System.out.println("getCourseWithMaximumCredits: " + repository.getCourseWithMaximumCredits());
        System.out.println("getCourseWithMostStudents: " + repository.getCourseWithMostStudents());
        System.out.println("getStudentWithMaximumGrade: " + repository.getStudentWithMaximumGrade());
        System.out.println("getCoursesFilterByFacultyWithoutStudent: " + repository.getCoursesFilterByFacultyWithoutStudent(Course.Faculty.INFORMATICS, repository.getAll().getFirst().getStudents()[0]));
        System.out.println("getCoursesFilterByFacultyWithoutStudentName: " + repository.getCoursesFilterByFacultyWithoutStudentName(Course.Faculty.INFORMATICS, repository.getAll().getFirst().getStudents()[0].getName()));
        System.out.println("getCoursesContainingStudent: " + repository.getCoursesContainingStudent(repository.getAll().getFirst().getStudents()[0]));
        System.out.println("getCoursesAboveCreditsLimit: " + repository.getCoursesAboveCreditsLimit(repository.getAll().getFirst().getCredits()));
        System.out.println("getCoursesFilterByCountOfStudents: " + repository.getCoursesFilterByCountOfStudents(2));
        System.out.println("getDistinctStudentsInOrderFilterByFaculty: " + repository.getDistinctStudentsInOrderFilterByFaculty(Course.Faculty.INFORMATICS));
        System.out.println("getFacultiesByStudentNeptun: " + repository.getFacultiesByStudentNeptun(repository.getAll().getFirst().getStudents()[0].getNeptun()));
        System.out.println("getFacultiesContainingStudent: " + repository.getFacultiesContainingStudent(repository.getAll().getFirst().getStudents()[0]));
        System.out.println("getTitleValuesByStudentName: " + repository.getTitleValuesByStudentName(repository.getAll().getFirst().getStudents()[0].getName()));
        System.out.println("getStudentsFilterByFaculty: " + repository.getStudentsFilterByFaculty(Course.Faculty.INFORMATICS));
        System.out.println("getFacultiesAboveCreditsLimit: " + repository.getFacultiesAboveCreditsLimit(repository.getAll().getFirst().getCredits()));
        System.out.println("getStudentGradeValues: " + repository.getStudentGradeValues());
        System.out.println("getCountOfCoursesByFaculties: " + repository.getCountOfCoursesByFaculties());
        System.out.println("getCountOfCoursesByTitle: " + repository.getCountOfCoursesByTitle());
        System.out.println("getFacultiesByCourses: " + repository.getFacultiesByCourses());
        System.out.println("getFirstCourseByFaculties: " + repository.getFirstCourseByFaculties());
        System.out.println("getLastCourseByFaculties: " + repository.getLastCourseByFaculties());
        System.out.println("getCodeOfFirstCourseByFaculties: " + repository.getCodeOfFirstCourseByFaculties());
        System.out.println("getTitleOfLastCourseByFaculties: " + repository.getTitleOfLastCourseByFaculties());
        System.out.println("getFirstCourseBySemester: " + repository.getFirstCourseBySemester());
        System.out.println("getFacultyOfLastCourseByTitle: " + repository.getFacultyOfLastCourseByTitle());
        System.out.println("getSumOfSemesterByFaculties: " + repository.getSumOfSemesterByFaculties());
        System.out.println("getCountOfCoursesByFacultiesFilterByCountOfStudents: " + repository.getCountOfCoursesByFacultiesFilterByCountOfStudents(3));
        System.out.println("getCountOfCoursesByCreditsValuesFilterByFaculty: " + repository.getCountOfCoursesByCreditsValuesFilterByFaculty(Course.Faculty.INFORMATICS));
        System.out.println("getAverageOfCreditsByFaculties: " + repository.getAverageOfCreditsByFaculties());
        System.out.println("getMaximumOfCreditsByFaculties: " + repository.getMaximumOfCreditsByFaculties());
        System.out.println("getSumOfStudentGradeByFaculties: " + repository.getSumOfStudentGradeByFaculties());
        System.out.println("getStudentsByFaculties: " + repository.getStudentsByFaculties());
        System.out.println("getCoursesByFacultiesFilterByCountOfStudents: " + repository.getCoursesByFacultiesFilterByCountOfStudents(3));
        System.out.println("getSemesterValuesByFacultiesFilterByCredits: " + repository.getSemesterValuesByFacultiesFilterByCredits(repository.getAll().getFirst().getCredits()));
        System.out.println("getGradeValuesOfStudentsByTitleFilterByFaculty: " + repository.getGradeValuesOfStudentsByTitleFilterByFaculty(Course.Faculty.INFORMATICS));
        System.out.println("getFacultiesByStudentGradeFilterByCountOfStudents: " + repository.getFacultiesByStudentGradeFilterByCountOfStudents(3));
        System.out.println("getCountOfCoursesByFacultiesAndSemester: " + repository.getCountOfCoursesByFacultiesAndSemester());
        System.out.println("getCountOfCoursesByCreditsAndFacultiesFilterByCountOfStudents: " + repository.getCountOfCoursesByCreditsAndFacultiesFilterByCountOfStudents(3));
        System.out.println("getCountOfStudentsByFacultiesAndName: " + repository.getCountOfStudentsByFacultiesAndName());
        System.out.println("getMaximumOfCreditsByFacultiesAndSemester: " + repository.getMaximumOfCreditsByFacultiesAndSemester());
    }

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

    @Override
    public List<Course> getCoursesOrderBySemesterDescThenByTitleDescThenByCode() {
        List<Course> result = new ArrayList<>(getAll());
        Collections.sort(result);
        return result;
    }

    @Override
    public List<Course> getCoursesOrderByTitleThenBySemesterThenByCodeDesc() {
        List<Course> result = new ArrayList<>(getAll());
        Collections.sort(result, new campus.CourseComparator());
        return result;
    }

    @Override
    public List<Course> getCoursesOrderBySemesterThenByTitleDescThenByCodeDesc() {
        List<Course> result = new ArrayList<>(getAll());
        Collections.sort(result, new Comparator<Course>() {
            @Override
            public int compare(@NonNull Course first, @NonNull Course second) {
                int result = Objects.compare(first.getSemester(), second.getSemester(), Comparator.naturalOrder());
                if (result != 0) {
                    return result;
                }
                result = Objects.compare(first.getTitle(), second.getTitle(), Comparator.reverseOrder());
                if (result != 0) {
                    return result;
                }
                return Objects.compare(first.getCode(), second.getCode(), Comparator.reverseOrder());
            }
        });
        return result;
    }

    @Override
    public List<Course> getCoursesOrderByCreditsThenByTitleDescThenByCodeDesc() {
        List<Course> result = new ArrayList<>(getAll());
        Collections.sort(result, new CourseComparator());
        return result;
    }

    @Override
    public List<Course> getCoursesOrderByCreditsThenByTitleDescThenByCode() {
        List<Course> result = new ArrayList<>(getAll());
        Collections.sort(result, new Course.CourseComparator());
        return result;
    }

    @Override
    public Course getFirstCourseByFaculty(@NonNull Course.Faculty faculty) throws NotFoundException {
        for (Course t : getAll()) {
            if (t.getFaculty() == faculty) {
                return t;
            }
        }
        throw new NotFoundException();
    }

    @Override
    public Course getFirstCourseByCode(@NonNull String code) {
        for (Course t : getAll()) {
            if (t.getCode().equals(code)) {
                return t;
            }
        }
        return null;
    }

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

    @Override
    public List<Course> getCoursesFilterByFacultyWithoutStudent(@NonNull Course.Faculty faculty, @NonNull Course.Student student) {
        List<Course> result = new ArrayList<>();
        for (Course t : getAll()) {
            if (t.getFaculty() == faculty) {
                boolean contains = false;
                for (Course.Student u : t.getStudents()) {
                    if (u.equals(student)) {
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
    public List<Course> getCoursesFilterByFacultyWithoutStudentName(@NonNull Course.Faculty faculty, @NonNull String name) {
        List<Course> result = new ArrayList<>();
        for (Course t : getAll()) {
            if (t.getFaculty() == faculty) {
                boolean contains = false;
                for (Course.Student u : t.getStudents()) {
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
    public List<Course> getCoursesContainingStudent(@NonNull Course.Student student) {
        List<Course> result = new ArrayList<>();
        for (Course t : getAll()) {
            boolean contains = false;
            for (Course.Student u : t.getStudents()) {
                if (u.equals(student)) {
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
    public List<Course> getCoursesAboveCreditsLimit(int credits) {
        List<Course> result = new ArrayList<>();
        for (Course t : getAll()) {
            if (t.getCredits() > credits) {
                result.add(t);
            }
        }
        return result;
    }

    @Override
    public List<Course> getCoursesFilterByCountOfStudents(int count) {
        List<Course> result = new ArrayList<>();
        for (Course t : getAll()) {
            if (t.getStudents().length >= count) {
                result.add(t);
            }
        }
        return result;
    }

    @Override
    public List<Course.Student> getDistinctStudentsInOrderFilterByFaculty(@NonNull Course.Faculty faculty) {
        List<Course.Student> result = new ArrayList<>();
        for (Course t : getAll()) {
            if (t.getFaculty() == faculty) {
                for (Course.Student u : t.getStudents()) {
                    if (!result.contains(u)) {
                        result.add(u);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Set<Course.Faculty> getFacultiesByStudentNeptun(@NonNull String neptun) {
        Set<Course.Faculty> result = new HashSet<>();
        for (Course t : getAll()) {
            boolean contains = false;
            for (Course.Student u : t.getStudents()) {
                if (u.getNeptun().equals(neptun)) {
                    contains = true;
                }
            }
            if (contains) {
                result.add(t.getFaculty());
            }
        }
        return result;
    }

    @Override
    public Set<Course.Faculty> getFacultiesContainingStudent(@NonNull Course.Student student) {
        Set<Course.Faculty> result = new HashSet<>();
        for (Course t : getAll()) {
            boolean contains = false;
            for (Course.Student u : t.getStudents()) {
                if (u.equals(student)) {
                    contains = true;
                }
            }
            if (contains) {
                result.add(t.getFaculty());
            }
        }
        return result;
    }

    @Override
    public Set<String> getTitleValuesByStudentName(@NonNull String name) {
        Set<String> result = new HashSet<>();
        for (Course t : getAll()) {
            boolean contains = false;
            for (Course.Student u : t.getStudents()) {
                if (u.getName().equals(name)) {
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
    public Set<Course.Student> getStudentsFilterByFaculty(@NonNull Course.Faculty faculty) {
        Set<Course.Student> result = new HashSet<>();
        for (Course t : getAll()) {
            if (t.getFaculty() == faculty) {
                for (Course.Student u : t.getStudents()) {
                    result.add(u);
                }
            }
        }
        return result;
    }

    @Override
    public Set<Course.Faculty> getFacultiesAboveCreditsLimit(int credits) {
        Set<Course.Faculty> result = new HashSet<>();
        for (Course t : getAll()) {
            if (t.getCredits() > credits) {
                result.add(t.getFaculty());
            }
        }
        return result;
    }

    @Override
    public Set<Integer> getStudentGradeValues() {
        Set<Integer> result = new HashSet<>();
        for (Course t : getAll()) {
            for (Course.Student u : t.getStudents()) {
                result.add(u.getGrade());
            }
        }
        return result;
    }

    @Override
    public Map<Course.Faculty, Integer> getCountOfCoursesByFaculties() {
        Map<Course.Faculty, Integer> result = new HashMap<>();
        for (Course t : getAll()) {
            result.put(t.getFaculty(), result.getOrDefault(t.getFaculty(), 0) + 1);
        }
        return result;
    }

    @Override
    public Map<String, Integer> getCountOfCoursesByTitle() {
        Map<String, Integer> result = new HashMap<>();
        for (Course t : getAll()) {
            result.put(t.getTitle(), result.getOrDefault(t.getTitle(), 0) + 1);
        }
        return result;
    }

    @Override
    public Map<Course, Course.Faculty> getFacultiesByCourses() {
        Map<Course, Course.Faculty> result = new HashMap<>();
        for (Course t : getAll()) {
            result.put(t, t.getFaculty());
        }
        return result;
    }

    @Override
    public Map<Course.Faculty, Course> getFirstCourseByFaculties() {
        Map<Course.Faculty, Course> result = new HashMap<>();
        for (Course t : getAll()) {
            if (!result.containsKey(t.getFaculty())) {
                result.put(t.getFaculty(), t);
            }
        }
        return result;
    }

    @Override
    public Map<Course.Faculty, Course> getLastCourseByFaculties() {
        Map<Course.Faculty, Course> result = new HashMap<>();
        for (Course t : getAll()) {
            result.put(t.getFaculty(), t);
        }
        return result;
    }

    @Override
    public Map<Course.Faculty, String> getCodeOfFirstCourseByFaculties() {
        Map<Course.Faculty, String> result = new HashMap<>();
        for (Course t : getAll()) {
            if (!result.containsKey(t.getFaculty())) {
                result.put(t.getFaculty(), t.getCode());
            }
        }
        return result;
    }

    @Override
    public Map<Course.Faculty, String> getTitleOfLastCourseByFaculties() {
        Map<Course.Faculty, String> result = new HashMap<>();
        for (Course t : getAll()) {
            result.put(t.getFaculty(), t.getTitle());
        }
        return result;
    }

    @Override
    public Map<Integer, Course> getFirstCourseBySemester() {
        Map<Integer, Course> result = new HashMap<>();
        for (Course t : getAll()) {
            if (!result.containsKey(t.getSemester())) {
                result.put(t.getSemester(), t);
            }
        }
        return result;
    }

    @Override
    public Map<String, Course.Faculty> getFacultyOfLastCourseByTitle() {
        Map<String, Course.Faculty> result = new HashMap<>();
        for (Course t : getAll()) {
            result.put(t.getTitle(), t.getFaculty());
        }
        return result;
    }

    @Override
    public Map<Course.Faculty, Integer> getSumOfSemesterByFaculties() {
        Map<Course.Faculty, Integer> result = new HashMap<>();
        for (Course t : getAll()) {
            result.put(t.getFaculty(), result.getOrDefault(t.getFaculty(), 0) + t.getSemester());
        }
        return result;
    }

    @Override
    public Map<Course.Faculty, Integer> getCountOfCoursesByFacultiesFilterByCountOfStudents(int count) {
        Map<Course.Faculty, Integer> result = new HashMap<>();
        for (Course t : getAll()) {
            if (t.getStudents().length >= count) {
                result.put(t.getFaculty(), result.getOrDefault(t.getFaculty(), 0) + 1);
            }
        }
        return result;
    }

    @Override
    public Map<Integer, Integer> getCountOfCoursesByCreditsValuesFilterByFaculty(@NonNull Course.Faculty faculty) {
        Map<Integer, Integer> result = new HashMap<>();
        for (Course t : getAll()) {
            if (t.getFaculty() == faculty) {
                result.put(t.getCredits(), result.getOrDefault(t.getCredits(), 0) + 1);
            }
        }
        return result;
    }

    @Override
    public Map<Course.Faculty, Double> getAverageOfCreditsByFaculties() {
        Map<Course.Faculty, Double> sums = new HashMap<>();
        Map<Course.Faculty, Integer> counts = new HashMap<>();
        for (Course t : getAll()) {
            sums.put(t.getFaculty(), sums.getOrDefault(t.getFaculty(), 0.0) + t.getCredits());
            counts.put(t.getFaculty(), counts.getOrDefault(t.getFaculty(), 0) + 1);
        }
        Map<Course.Faculty, Double> result = new HashMap<>();
        for (Course.Faculty key : sums.keySet()) {
            result.put(key, sums.get(key) / counts.get(key));
        }
        return result;
    }

    @Override
    public Map<Course.Faculty, Integer> getMaximumOfCreditsByFaculties() {
        Map<Course.Faculty, Integer> result = new HashMap<>();
        for (Course t : getAll()) {
            if (!result.containsKey(t.getFaculty()) || t.getCredits() > result.get(t.getFaculty())) {
                result.put(t.getFaculty(), t.getCredits());
            }
        }
        return result;
    }

    @Override
    public Map<Course.Faculty, Integer> getSumOfStudentGradeByFaculties() {
        Map<Course.Faculty, Integer> result = new HashMap<>();
        for (Course t : getAll()) {
            int sum = 0;
            for (Course.Student u : t.getStudents()) {
                sum += u.getGrade();
            }
            result.put(t.getFaculty(), result.getOrDefault(t.getFaculty(), 0) + sum);
        }
        return result;
    }

    @Override
    public Map<Course.Faculty, Set<Course.Student>> getStudentsByFaculties() {
        Map<Course.Faculty, Set<Course.Student>> result = new HashMap<>();
        for (Course t : getAll()) {
            if (!result.containsKey(t.getFaculty())) {
                result.put(t.getFaculty(), new HashSet<>());
            }
            for (Course.Student u : t.getStudents()) {
                result.get(t.getFaculty()).add(u);
            }
        }
        return result;
    }

    @Override
    public Map<Course.Faculty, List<Course>> getCoursesByFacultiesFilterByCountOfStudents(int count) {
        Map<Course.Faculty, List<Course>> result = new HashMap<>();
        for (Course t : getAll()) {
            if (t.getStudents().length >= count) {
                if (!result.containsKey(t.getFaculty())) {
                    result.put(t.getFaculty(), new ArrayList<>());
                }
                result.get(t.getFaculty()).add(t);
            }
        }
        return result;
    }

    @Override
    public Map<Course.Faculty, Set<Integer>> getSemesterValuesByFacultiesFilterByCredits(int credits) {
        Map<Course.Faculty, Set<Integer>> result = new HashMap<>();
        for (Course t : getAll()) {
            if (t.getCredits() > credits) {
                if (!result.containsKey(t.getFaculty())) {
                    result.put(t.getFaculty(), new HashSet<>());
                }
                result.get(t.getFaculty()).add(t.getSemester());
            }
        }
        return result;
    }

    @Override
    public Map<String, Set<Integer>> getGradeValuesOfStudentsByTitleFilterByFaculty(@NonNull Course.Faculty faculty) {
        Map<String, Set<Integer>> result = new HashMap<>();
        for (Course t : getAll()) {
            if (t.getFaculty() == faculty) {
                if (!result.containsKey(t.getTitle())) {
                    result.put(t.getTitle(), new HashSet<>());
                }
                for (Course.Student u : t.getStudents()) {
                    result.get(t.getTitle()).add(u.getGrade());
                }
            }
        }
        return result;
    }

    @Override
    public Map<Integer, Set<Course.Faculty>> getFacultiesByStudentGradeFilterByCountOfStudents(int count) {
        Map<Integer, Set<Course.Faculty>> result = new HashMap<>();
        for (Course t : getAll()) {
            if (t.getStudents().length >= count) {
                for (Course.Student u : t.getStudents()) {
                    if (!result.containsKey(u.getGrade())) {
                        result.put(u.getGrade(), new HashSet<>());
                    }
                    result.get(u.getGrade()).add(t.getFaculty());
                }
            }
        }
        return result;
    }

    @Override
    public Map<Course.Faculty, Map<Integer, Integer>> getCountOfCoursesByFacultiesAndSemester() {
        Map<Course.Faculty, Map<Integer, Integer>> result = new HashMap<>();
        for (Course t : getAll()) {
            if (!result.containsKey(t.getFaculty())) {
                result.put(t.getFaculty(), new HashMap<>());
            }
            Map<Integer, Integer> inner = result.get(t.getFaculty());
            inner.put(t.getSemester(), inner.getOrDefault(t.getSemester(), 0) + 1);
        }
        return result;
    }

    @Override
    public Map<Integer, Map<Course.Faculty, Integer>> getCountOfCoursesByCreditsAndFacultiesFilterByCountOfStudents(int count) {
        Map<Integer, Map<Course.Faculty, Integer>> result = new HashMap<>();
        for (Course t : getAll()) {
            if (t.getStudents().length >= count) {
                if (!result.containsKey(t.getCredits())) {
                    result.put(t.getCredits(), new HashMap<>());
                }
                Map<Course.Faculty, Integer> inner = result.get(t.getCredits());
                inner.put(t.getFaculty(), inner.getOrDefault(t.getFaculty(), 0) + 1);
            }
        }
        return result;
    }

    @Override
    public Map<Course.Faculty, Map<String, Integer>> getCountOfStudentsByFacultiesAndName() {
        Map<Course.Faculty, Map<String, Integer>> result = new HashMap<>();
        for (Course t : getAll()) {
            if (!result.containsKey(t.getFaculty())) {
                result.put(t.getFaculty(), new HashMap<>());
            }
            Map<String, Integer> inner = result.get(t.getFaculty());
            for (Course.Student u : t.getStudents()) {
                inner.put(u.getName(), inner.getOrDefault(u.getName(), 0) + 1);
            }
        }
        return result;
    }

    @Override
    public Map<Course.Faculty, Map<Integer, Integer>> getMaximumOfCreditsByFacultiesAndSemester() {
        Map<Course.Faculty, Map<Integer, Integer>> result = new HashMap<>();
        for (Course t : getAll()) {
            if (!result.containsKey(t.getFaculty())) {
                result.put(t.getFaculty(), new HashMap<>());
            }
            Map<Integer, Integer> inner = result.get(t.getFaculty());
            if (!inner.containsKey(t.getSemester()) || t.getCredits() > inner.get(t.getSemester())) {
                inner.put(t.getSemester(), t.getCredits());
            }
        }
        return result;
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
            return Objects.compare(first.getCode(), second.getCode(), Comparator.reverseOrder());
        }
    }
}
