package clinic;

import base.*;
import lombok.NonNull;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The reference solution (generated).
 */
public class DoctorRepository extends Repository<Doctor> implements AggregationQueries<Doctor, Doctor.Patient, Doctor.Department>,
        SortingQueries<Doctor, Doctor.Patient, Doctor.Department>,
        SearchQueries<Doctor, Doctor.Patient, Doctor.Department>,
        FilteringQueries<Doctor, Doctor.Patient, Doctor.Department>,
        SetQueries<Doctor, Doctor.Patient, Doctor.Department>,
        BasicMapQueries<Doctor, Doctor.Patient, Doctor.Department>,
        AggregationMapQueries<Doctor, Doctor.Patient, Doctor.Department>,
        GroupingMapQueries<Doctor, Doctor.Patient, Doctor.Department>,
        NestedMapQueries<Doctor, Doctor.Patient, Doctor.Department> {

    public DoctorRepository() throws IOException {
        super(Doctor.class);
    }

    public static void main(String[] args) throws Exception {
        DoctorRepository repository = new DoctorRepository();
        System.out.println(repository);
        System.out.println("getMaximumCountOfPatients: " + repository.getMaximumCountOfPatients());
        System.out.println("getMinimumCountOfPatients: " + repository.getMinimumCountOfPatients());
        System.out.println("getTotalCountOfPatientsFilterByDepartment: " + repository.getTotalCountOfPatientsFilterByDepartment(Doctor.Department.CARDIOLOGY));
        System.out.println("getMaximumOfExperience: " + repository.getMaximumOfExperience());
        System.out.println("getMinimumOfExperience: " + repository.getMinimumOfExperience());
        System.out.println("getAverageOfExperienceFilterByDepartment: " + repository.getAverageOfExperienceFilterByDepartment(Doctor.Department.CARDIOLOGY));
        System.out.println("getMaximumOfPatientInsuranceNumber: " + repository.getMaximumOfPatientInsuranceNumber());
        System.out.println("getAverageOfPatientInsuranceNumberFilterByDepartment: " + repository.getAverageOfPatientInsuranceNumberFilterByDepartment(Doctor.Department.CARDIOLOGY));
        System.out.println("getCountOfDoctorsFilterByDepartment: " + repository.getCountOfDoctorsFilterByDepartment(Doctor.Department.CARDIOLOGY));
        System.out.println("getCountOfDoctorsAboveExperienceLimit: " + repository.getCountOfDoctorsAboveExperienceLimit(repository.getAll().getFirst().getExperience()));
        System.out.println("getCountOfDoctorsByNamePrefix: " + repository.getCountOfDoctorsByNamePrefix(repository.getAll().getFirst().getName().substring(0, 1)));
        System.out.println("getCountOfDoctorsByPatientName: " + repository.getCountOfDoctorsByPatientName(repository.getAll().getFirst().getPatients().iterator().next().getName()));
        System.out.println("getCountOfDoctorsWithoutPatients: " + repository.getCountOfDoctorsWithoutPatients());
        System.out.println("getCountOfPatientsByName: " + repository.getCountOfPatientsByName(repository.getAll().getFirst().getPatients().iterator().next().getName()));
        System.out.println("getSumOfExperienceFilterByDepartment: " + repository.getSumOfExperienceFilterByDepartment(Doctor.Department.CARDIOLOGY));
        System.out.println("getSumOfPatientAgeByName: " + repository.getSumOfPatientAgeByName(repository.getAll().getFirst().getPatients().iterator().next().getName()));
        System.out.println("getMinimumOfPatientInsuranceNumber: " + repository.getMinimumOfPatientInsuranceNumber());
        System.out.println("getAverageCountOfPatientsFilterByDepartment: " + repository.getAverageCountOfPatientsFilterByDepartment(Doctor.Department.CARDIOLOGY));
        System.out.println("getMaximumLengthOfRoom: " + repository.getMaximumLengthOfRoom());
        System.out.println("getRoomOfDoctorWithMaximumExperience: " + repository.getRoomOfDoctorWithMaximumExperience());
        System.out.println("isExperiencePresent: " + repository.isExperiencePresent(repository.getAll().getFirst().getExperience()));
        System.out.println("isPatientNamePresent: " + repository.isPatientNamePresent(repository.getAll().getFirst().getPatients().iterator().next().getName()));
        System.out.println("doAllDoctorsHaveAtLeastPatients: " + repository.doAllDoctorsHaveAtLeastPatients(1));
        System.out.println("getDoctorsOrderByExperienceDescThenByRoomDescThenByName: " + repository.getDoctorsOrderByExperienceDescThenByRoomDescThenByName());
        System.out.println("getDoctorsOrderByExperienceThenByRoomDescThenByName: " + repository.getDoctorsOrderByExperienceThenByRoomDescThenByName());
        System.out.println("getDoctorsOrderByRoomThenByExperienceThenByNameDesc: " + repository.getDoctorsOrderByRoomThenByExperienceThenByNameDesc());
        System.out.println("getDoctorsOrderByOnCallDescThenByRoomThenByNameDesc: " + repository.getDoctorsOrderByOnCallDescThenByRoomThenByNameDesc());
        System.out.println("getDoctorsOrderByRoomThenByOnCallThenByName: " + repository.getDoctorsOrderByRoomThenByOnCallThenByName());
        System.out.println("getFirstDoctorByDepartment: " + repository.getFirstDoctorByDepartment(Doctor.Department.CARDIOLOGY));
        System.out.println("getFirstDoctorByName: " + repository.getFirstDoctorByName(repository.getAll().getFirst().getName()));
        System.out.println("getFirstDoctorByPatientName: " + repository.getFirstDoctorByPatientName(repository.getAll().getFirst().getPatients().iterator().next().getName()));
        System.out.println("getFirstPatientByName: " + repository.getFirstPatientByName(repository.getAll().getFirst().getPatients().iterator().next().getName()));
        System.out.println("getDoctorWithMaximumExperience: " + repository.getDoctorWithMaximumExperience());
        System.out.println("getDoctorWithMostPatients: " + repository.getDoctorWithMostPatients());
        System.out.println("getPatientWithMaximumInsuranceNumber: " + repository.getPatientWithMaximumInsuranceNumber());
        System.out.println("getDoctorsFilterByDepartmentWithoutPatient: " + repository.getDoctorsFilterByDepartmentWithoutPatient(Doctor.Department.CARDIOLOGY, repository.getAll().getFirst().getPatients().iterator().next()));
        System.out.println("getDoctorsFilterByDepartmentWithoutPatientName: " + repository.getDoctorsFilterByDepartmentWithoutPatientName(Doctor.Department.CARDIOLOGY, repository.getAll().getFirst().getPatients().iterator().next().getName()));
        System.out.println("getDoctorsContainingPatient: " + repository.getDoctorsContainingPatient(repository.getAll().getFirst().getPatients().iterator().next()));
        System.out.println("getDoctorsAboveExperienceLimit: " + repository.getDoctorsAboveExperienceLimit(repository.getAll().getFirst().getExperience()));
        System.out.println("getDoctorsFilterByCountOfPatients: " + repository.getDoctorsFilterByCountOfPatients(2));
        System.out.println("getDistinctPatientsInOrderFilterByDepartment: " + repository.getDistinctPatientsInOrderFilterByDepartment(Doctor.Department.CARDIOLOGY));
        System.out.println("getDepartmentsByPatientName: " + repository.getDepartmentsByPatientName(repository.getAll().getFirst().getPatients().iterator().next().getName()));
        System.out.println("getDepartmentsContainingPatient: " + repository.getDepartmentsContainingPatient(repository.getAll().getFirst().getPatients().iterator().next()));
        System.out.println("getRoomValuesByPatientName: " + repository.getRoomValuesByPatientName(repository.getAll().getFirst().getPatients().iterator().next().getName()));
        System.out.println("getPatientsFilterByDepartment: " + repository.getPatientsFilterByDepartment(Doctor.Department.CARDIOLOGY));
        System.out.println("getDepartmentsAboveExperienceLimit: " + repository.getDepartmentsAboveExperienceLimit(repository.getAll().getFirst().getExperience()));
        System.out.println("getPatientAgeValues: " + repository.getPatientAgeValues());
        System.out.println("getCountOfDoctorsByDepartments: " + repository.getCountOfDoctorsByDepartments());
        System.out.println("getCountOfDoctorsByRoom: " + repository.getCountOfDoctorsByRoom());
        System.out.println("getDepartmentsByDoctors: " + repository.getDepartmentsByDoctors());
        System.out.println("getFirstDoctorByDepartments: " + repository.getFirstDoctorByDepartments());
        System.out.println("getLastDoctorByDepartments: " + repository.getLastDoctorByDepartments());
        System.out.println("getExperienceOfFirstDoctorByDepartments: " + repository.getExperienceOfFirstDoctorByDepartments());
        System.out.println("getNameOfLastDoctorByDepartments: " + repository.getNameOfLastDoctorByDepartments());
        System.out.println("getFirstDoctorByRoom: " + repository.getFirstDoctorByRoom());
        System.out.println("getDepartmentOfLastDoctorByExperience: " + repository.getDepartmentOfLastDoctorByExperience());
        System.out.println("getSumOfExperienceByDepartments: " + repository.getSumOfExperienceByDepartments());
        System.out.println("getCountOfDoctorsByDepartmentsFilterByCountOfPatients: " + repository.getCountOfDoctorsByDepartmentsFilterByCountOfPatients(3));
        System.out.println("getCountOfDoctorsByExperienceValuesFilterByDepartment: " + repository.getCountOfDoctorsByExperienceValuesFilterByDepartment(Doctor.Department.CARDIOLOGY));
        System.out.println("getAverageOfExperienceByDepartments: " + repository.getAverageOfExperienceByDepartments());
        System.out.println("getMaximumOfExperienceByDepartments: " + repository.getMaximumOfExperienceByDepartments());
        System.out.println("getSumOfPatientInsuranceNumberByDepartments: " + repository.getSumOfPatientInsuranceNumberByDepartments());
        System.out.println("getPatientsByDepartments: " + repository.getPatientsByDepartments());
        System.out.println("getDoctorsByDepartmentsFilterByCountOfPatients: " + repository.getDoctorsByDepartmentsFilterByCountOfPatients(3));
        System.out.println("getRoomValuesByDepartmentsFilterByExperience: " + repository.getRoomValuesByDepartmentsFilterByExperience(repository.getAll().getFirst().getExperience()));
        System.out.println("getNameValuesOfPatientsByExperienceFilterByDepartment: " + repository.getNameValuesOfPatientsByExperienceFilterByDepartment(Doctor.Department.CARDIOLOGY));
        System.out.println("getDepartmentsByPatientNameFilterByCountOfPatients: " + repository.getDepartmentsByPatientNameFilterByCountOfPatients(3));
        System.out.println("getCountOfDoctorsByDepartmentsAndOnCall: " + repository.getCountOfDoctorsByDepartmentsAndOnCall());
        System.out.println("getCountOfDoctorsByRoomAndDepartmentsFilterByCountOfPatients: " + repository.getCountOfDoctorsByRoomAndDepartmentsFilterByCountOfPatients(3));
        System.out.println("getCountOfPatientsByDepartmentsAndAge: " + repository.getCountOfPatientsByDepartmentsAndAge());
        System.out.println("getMaximumOfExperienceByDepartmentsAndOnCall: " + repository.getMaximumOfExperienceByDepartmentsAndOnCall());
    }

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

    @Override
    public List<Doctor> getDoctorsOrderByExperienceDescThenByRoomDescThenByName() {
        List<Doctor> result = new ArrayList<>(getAll());
        Collections.sort(result);
        return result;
    }

    @Override
    public List<Doctor> getDoctorsOrderByExperienceThenByRoomDescThenByName() {
        List<Doctor> result = new ArrayList<>(getAll());
        Collections.sort(result, new clinic.DoctorComparator());
        return result;
    }

    @Override
    public List<Doctor> getDoctorsOrderByRoomThenByExperienceThenByNameDesc() {
        List<Doctor> result = new ArrayList<>(getAll());
        Collections.sort(result, new Comparator<Doctor>() {
            @Override
            public int compare(@NonNull Doctor first, @NonNull Doctor second) {
                int result = Objects.compare(first.getRoom(), second.getRoom(), Comparator.naturalOrder());
                if (result != 0) {
                    return result;
                }
                result = Objects.compare(first.getExperience(), second.getExperience(), Comparator.naturalOrder());
                if (result != 0) {
                    return result;
                }
                return Objects.compare(first.getName(), second.getName(), Comparator.reverseOrder());
            }
        });
        return result;
    }

    @Override
    public List<Doctor> getDoctorsOrderByOnCallDescThenByRoomThenByNameDesc() {
        List<Doctor> result = new ArrayList<>(getAll());
        Collections.sort(result, new DoctorComparator());
        return result;
    }

    @Override
    public List<Doctor> getDoctorsOrderByRoomThenByOnCallThenByName() {
        List<Doctor> result = new ArrayList<>(getAll());
        Collections.sort(result, new Doctor.DoctorComparator());
        return result;
    }

    @Override
    public Doctor getFirstDoctorByDepartment(@NonNull Doctor.Department department) {
        for (Doctor t : getAll()) {
            if (t.getDepartment() == department) {
                return t;
            }
        }
        return null;
    }

    @Override
    public Doctor getFirstDoctorByName(@NonNull String name) throws EmptyResultException {
        for (Doctor t : getAll()) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        throw new EmptyResultException();
    }

    @Override
    public Doctor getFirstDoctorByPatientName(@NonNull String name) {
        for (Doctor t : getAll()) {
            for (Doctor.Patient u : t.getPatients()) {
                if (u.getName().equals(name)) {
                    return t;
                }
            }
        }
        return null;
    }

    @Override
    public Doctor.Patient getFirstPatientByName(@NonNull String name) {
        for (Doctor t : getAll()) {
            for (Doctor.Patient u : t.getPatients()) {
                if (u.getName().equals(name)) {
                    return u;
                }
            }
        }
        return null;
    }

    @Override
    public Doctor getDoctorWithMaximumExperience() {
        Doctor best = null;
        for (Doctor t : getAll()) {
            if (best == null || t.getExperience() > best.getExperience()) {
                best = t;
            }
        }
        if (best == null) {
            return null;
        }
        return best;
    }

    @Override
    public Doctor getDoctorWithMostPatients() {
        Doctor best = null;
        for (Doctor t : getAll()) {
            if (best == null || t.getPatients().size() > best.getPatients().size()) {
                best = t;
            }
        }
        if (best == null) {
            return null;
        }
        return best;
    }

    @Override
    public Doctor.Patient getPatientWithMaximumInsuranceNumber() {
        Doctor.Patient best = null;
        for (Doctor t : getAll()) {
            for (Doctor.Patient u : t.getPatients()) {
                if (best == null || u.getInsuranceNumber() > best.getInsuranceNumber()) {
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
    public List<Doctor> getDoctorsFilterByDepartmentWithoutPatient(@NonNull Doctor.Department department, @NonNull Doctor.Patient patient) {
        List<Doctor> result = new ArrayList<>();
        for (Doctor t : getAll()) {
            if (t.getDepartment() == department) {
                boolean contains = false;
                for (Doctor.Patient u : t.getPatients()) {
                    if (u.equals(patient)) {
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
    public List<Doctor> getDoctorsFilterByDepartmentWithoutPatientName(@NonNull Doctor.Department department, @NonNull String name) {
        List<Doctor> result = new ArrayList<>();
        for (Doctor t : getAll()) {
            if (t.getDepartment() == department) {
                boolean contains = false;
                for (Doctor.Patient u : t.getPatients()) {
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
    public List<Doctor> getDoctorsContainingPatient(@NonNull Doctor.Patient patient) {
        List<Doctor> result = new ArrayList<>();
        for (Doctor t : getAll()) {
            boolean contains = false;
            for (Doctor.Patient u : t.getPatients()) {
                if (u.equals(patient)) {
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
    public List<Doctor> getDoctorsAboveExperienceLimit(int experience) {
        List<Doctor> result = new ArrayList<>();
        for (Doctor t : getAll()) {
            if (t.getExperience() > experience) {
                result.add(t);
            }
        }
        return result;
    }

    @Override
    public List<Doctor> getDoctorsFilterByCountOfPatients(int count) {
        List<Doctor> result = new ArrayList<>();
        for (Doctor t : getAll()) {
            if (t.getPatients().size() >= count) {
                result.add(t);
            }
        }
        return result;
    }

    @Override
    public List<Doctor.Patient> getDistinctPatientsInOrderFilterByDepartment(@NonNull Doctor.Department department) {
        List<Doctor.Patient> result = new ArrayList<>();
        for (Doctor t : getAll()) {
            if (t.getDepartment() == department) {
                for (Doctor.Patient u : t.getPatients()) {
                    if (!result.contains(u)) {
                        result.add(u);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Set<Doctor.Department> getDepartmentsByPatientName(@NonNull String name) {
        Set<Doctor.Department> result = new HashSet<>();
        for (Doctor t : getAll()) {
            boolean contains = false;
            for (Doctor.Patient u : t.getPatients()) {
                if (u.getName().equals(name)) {
                    contains = true;
                }
            }
            if (contains) {
                result.add(t.getDepartment());
            }
        }
        return result;
    }

    @Override
    public Set<Doctor.Department> getDepartmentsContainingPatient(@NonNull Doctor.Patient patient) {
        Set<Doctor.Department> result = new HashSet<>();
        for (Doctor t : getAll()) {
            boolean contains = false;
            for (Doctor.Patient u : t.getPatients()) {
                if (u.equals(patient)) {
                    contains = true;
                }
            }
            if (contains) {
                result.add(t.getDepartment());
            }
        }
        return result;
    }

    @Override
    public Set<String> getRoomValuesByPatientName(@NonNull String name) {
        Set<String> result = new HashSet<>();
        for (Doctor t : getAll()) {
            boolean contains = false;
            for (Doctor.Patient u : t.getPatients()) {
                if (u.getName().equals(name)) {
                    contains = true;
                }
            }
            if (contains) {
                result.add(t.getRoom());
            }
        }
        return result;
    }

    @Override
    public Set<Doctor.Patient> getPatientsFilterByDepartment(@NonNull Doctor.Department department) {
        Set<Doctor.Patient> result = new HashSet<>();
        for (Doctor t : getAll()) {
            if (t.getDepartment() == department) {
                for (Doctor.Patient u : t.getPatients()) {
                    result.add(u);
                }
            }
        }
        return result;
    }

    @Override
    public Set<Doctor.Department> getDepartmentsAboveExperienceLimit(int experience) {
        Set<Doctor.Department> result = new HashSet<>();
        for (Doctor t : getAll()) {
            if (t.getExperience() > experience) {
                result.add(t.getDepartment());
            }
        }
        return result;
    }

    @Override
    public Set<Integer> getPatientAgeValues() {
        Set<Integer> result = new HashSet<>();
        for (Doctor t : getAll()) {
            for (Doctor.Patient u : t.getPatients()) {
                result.add(u.getAge());
            }
        }
        return result;
    }

    @Override
    public Map<Doctor.Department, Integer> getCountOfDoctorsByDepartments() {
        Map<Doctor.Department, Integer> result = new HashMap<>();
        for (Doctor t : getAll()) {
            result.put(t.getDepartment(), result.getOrDefault(t.getDepartment(), 0) + 1);
        }
        return result;
    }

    @Override
    public Map<String, Integer> getCountOfDoctorsByRoom() {
        Map<String, Integer> result = new HashMap<>();
        for (Doctor t : getAll()) {
            result.put(t.getRoom(), result.getOrDefault(t.getRoom(), 0) + 1);
        }
        return result;
    }

    @Override
    public Map<Doctor, Doctor.Department> getDepartmentsByDoctors() {
        Map<Doctor, Doctor.Department> result = new HashMap<>();
        for (Doctor t : getAll()) {
            result.put(t, t.getDepartment());
        }
        return result;
    }

    @Override
    public Map<Doctor.Department, Doctor> getFirstDoctorByDepartments() {
        Map<Doctor.Department, Doctor> result = new HashMap<>();
        for (Doctor t : getAll()) {
            if (!result.containsKey(t.getDepartment())) {
                result.put(t.getDepartment(), t);
            }
        }
        return result;
    }

    @Override
    public Map<Doctor.Department, Doctor> getLastDoctorByDepartments() {
        Map<Doctor.Department, Doctor> result = new HashMap<>();
        for (Doctor t : getAll()) {
            result.put(t.getDepartment(), t);
        }
        return result;
    }

    @Override
    public Map<Doctor.Department, Integer> getExperienceOfFirstDoctorByDepartments() {
        Map<Doctor.Department, Integer> result = new HashMap<>();
        for (Doctor t : getAll()) {
            if (!result.containsKey(t.getDepartment())) {
                result.put(t.getDepartment(), t.getExperience());
            }
        }
        return result;
    }

    @Override
    public Map<Doctor.Department, String> getNameOfLastDoctorByDepartments() {
        Map<Doctor.Department, String> result = new HashMap<>();
        for (Doctor t : getAll()) {
            result.put(t.getDepartment(), t.getName());
        }
        return result;
    }

    @Override
    public Map<String, Doctor> getFirstDoctorByRoom() {
        Map<String, Doctor> result = new HashMap<>();
        for (Doctor t : getAll()) {
            if (!result.containsKey(t.getRoom())) {
                result.put(t.getRoom(), t);
            }
        }
        return result;
    }

    @Override
    public Map<Integer, Doctor.Department> getDepartmentOfLastDoctorByExperience() {
        Map<Integer, Doctor.Department> result = new HashMap<>();
        for (Doctor t : getAll()) {
            result.put(t.getExperience(), t.getDepartment());
        }
        return result;
    }

    @Override
    public Map<Doctor.Department, Integer> getSumOfExperienceByDepartments() {
        Map<Doctor.Department, Integer> result = new HashMap<>();
        for (Doctor t : getAll()) {
            result.put(t.getDepartment(), result.getOrDefault(t.getDepartment(), 0) + t.getExperience());
        }
        return result;
    }

    @Override
    public Map<Doctor.Department, Integer> getCountOfDoctorsByDepartmentsFilterByCountOfPatients(int count) {
        Map<Doctor.Department, Integer> result = new HashMap<>();
        for (Doctor t : getAll()) {
            if (t.getPatients().size() >= count) {
                result.put(t.getDepartment(), result.getOrDefault(t.getDepartment(), 0) + 1);
            }
        }
        return result;
    }

    @Override
    public Map<Integer, Integer> getCountOfDoctorsByExperienceValuesFilterByDepartment(@NonNull Doctor.Department department) {
        Map<Integer, Integer> result = new HashMap<>();
        for (Doctor t : getAll()) {
            if (t.getDepartment() == department) {
                result.put(t.getExperience(), result.getOrDefault(t.getExperience(), 0) + 1);
            }
        }
        return result;
    }

    @Override
    public Map<Doctor.Department, Double> getAverageOfExperienceByDepartments() {
        Map<Doctor.Department, Double> sums = new HashMap<>();
        Map<Doctor.Department, Integer> counts = new HashMap<>();
        for (Doctor t : getAll()) {
            sums.put(t.getDepartment(), sums.getOrDefault(t.getDepartment(), 0.0) + t.getExperience());
            counts.put(t.getDepartment(), counts.getOrDefault(t.getDepartment(), 0) + 1);
        }
        Map<Doctor.Department, Double> result = new HashMap<>();
        for (Doctor.Department key : sums.keySet()) {
            result.put(key, sums.get(key) / counts.get(key));
        }
        return result;
    }

    @Override
    public Map<Doctor.Department, Integer> getMaximumOfExperienceByDepartments() {
        Map<Doctor.Department, Integer> result = new HashMap<>();
        for (Doctor t : getAll()) {
            if (!result.containsKey(t.getDepartment()) || t.getExperience() > result.get(t.getDepartment())) {
                result.put(t.getDepartment(), t.getExperience());
            }
        }
        return result;
    }

    @Override
    public Map<Doctor.Department, Long> getSumOfPatientInsuranceNumberByDepartments() {
        Map<Doctor.Department, Long> result = new HashMap<>();
        for (Doctor t : getAll()) {
            long sum = 0L;
            for (Doctor.Patient u : t.getPatients()) {
                sum += u.getInsuranceNumber();
            }
            result.put(t.getDepartment(), result.getOrDefault(t.getDepartment(), 0L) + sum);
        }
        return result;
    }

    @Override
    public Map<Doctor.Department, Set<Doctor.Patient>> getPatientsByDepartments() {
        Map<Doctor.Department, Set<Doctor.Patient>> result = new HashMap<>();
        for (Doctor t : getAll()) {
            if (!result.containsKey(t.getDepartment())) {
                result.put(t.getDepartment(), new HashSet<>());
            }
            for (Doctor.Patient u : t.getPatients()) {
                result.get(t.getDepartment()).add(u);
            }
        }
        return result;
    }

    @Override
    public Map<Doctor.Department, List<Doctor>> getDoctorsByDepartmentsFilterByCountOfPatients(int count) {
        Map<Doctor.Department, List<Doctor>> result = new HashMap<>();
        for (Doctor t : getAll()) {
            if (t.getPatients().size() >= count) {
                if (!result.containsKey(t.getDepartment())) {
                    result.put(t.getDepartment(), new ArrayList<>());
                }
                result.get(t.getDepartment()).add(t);
            }
        }
        return result;
    }

    @Override
    public Map<Doctor.Department, Set<String>> getRoomValuesByDepartmentsFilterByExperience(int experience) {
        Map<Doctor.Department, Set<String>> result = new HashMap<>();
        for (Doctor t : getAll()) {
            if (t.getExperience() > experience) {
                if (!result.containsKey(t.getDepartment())) {
                    result.put(t.getDepartment(), new HashSet<>());
                }
                result.get(t.getDepartment()).add(t.getRoom());
            }
        }
        return result;
    }

    @Override
    public Map<Integer, Set<String>> getNameValuesOfPatientsByExperienceFilterByDepartment(@NonNull Doctor.Department department) {
        Map<Integer, Set<String>> result = new HashMap<>();
        for (Doctor t : getAll()) {
            if (t.getDepartment() == department) {
                if (!result.containsKey(t.getExperience())) {
                    result.put(t.getExperience(), new HashSet<>());
                }
                for (Doctor.Patient u : t.getPatients()) {
                    result.get(t.getExperience()).add(u.getName());
                }
            }
        }
        return result;
    }

    @Override
    public Map<String, Set<Doctor.Department>> getDepartmentsByPatientNameFilterByCountOfPatients(int count) {
        Map<String, Set<Doctor.Department>> result = new HashMap<>();
        for (Doctor t : getAll()) {
            if (t.getPatients().size() >= count) {
                for (Doctor.Patient u : t.getPatients()) {
                    if (!result.containsKey(u.getName())) {
                        result.put(u.getName(), new HashSet<>());
                    }
                    result.get(u.getName()).add(t.getDepartment());
                }
            }
        }
        return result;
    }

    @Override
    public Map<Doctor.Department, Map<Boolean, Integer>> getCountOfDoctorsByDepartmentsAndOnCall() {
        Map<Doctor.Department, Map<Boolean, Integer>> result = new HashMap<>();
        for (Doctor t : getAll()) {
            if (!result.containsKey(t.getDepartment())) {
                result.put(t.getDepartment(), new HashMap<>());
            }
            Map<Boolean, Integer> inner = result.get(t.getDepartment());
            inner.put(t.isOnCall(), inner.getOrDefault(t.isOnCall(), 0) + 1);
        }
        return result;
    }

    @Override
    public Map<String, Map<Doctor.Department, Integer>> getCountOfDoctorsByRoomAndDepartmentsFilterByCountOfPatients(int count) {
        Map<String, Map<Doctor.Department, Integer>> result = new HashMap<>();
        for (Doctor t : getAll()) {
            if (t.getPatients().size() >= count) {
                if (!result.containsKey(t.getRoom())) {
                    result.put(t.getRoom(), new HashMap<>());
                }
                Map<Doctor.Department, Integer> inner = result.get(t.getRoom());
                inner.put(t.getDepartment(), inner.getOrDefault(t.getDepartment(), 0) + 1);
            }
        }
        return result;
    }

    @Override
    public Map<Doctor.Department, Map<Integer, Integer>> getCountOfPatientsByDepartmentsAndAge() {
        Map<Doctor.Department, Map<Integer, Integer>> result = new HashMap<>();
        for (Doctor t : getAll()) {
            if (!result.containsKey(t.getDepartment())) {
                result.put(t.getDepartment(), new HashMap<>());
            }
            Map<Integer, Integer> inner = result.get(t.getDepartment());
            for (Doctor.Patient u : t.getPatients()) {
                inner.put(u.getAge(), inner.getOrDefault(u.getAge(), 0) + 1);
            }
        }
        return result;
    }

    @Override
    public Map<Doctor.Department, Map<Boolean, Integer>> getMaximumOfExperienceByDepartmentsAndOnCall() {
        Map<Doctor.Department, Map<Boolean, Integer>> result = new HashMap<>();
        for (Doctor t : getAll()) {
            if (!result.containsKey(t.getDepartment())) {
                result.put(t.getDepartment(), new HashMap<>());
            }
            Map<Boolean, Integer> inner = result.get(t.getDepartment());
            if (!inner.containsKey(t.isOnCall()) || t.getExperience() > inner.get(t.isOnCall())) {
                inner.put(t.isOnCall(), t.getExperience());
            }
        }
        return result;
    }

    /**
     * Compares doctors.
     */
    public static class DoctorComparator implements Comparator<Doctor> {

        @Override
        public int compare(@NonNull Doctor first, @NonNull Doctor second) {
            int result = Objects.compare(first.isOnCall(), second.isOnCall(), Comparator.reverseOrder());
            if (result != 0) {
                return result;
            }
            result = Objects.compare(first.getRoom(), second.getRoom(), Comparator.naturalOrder());
            if (result != 0) {
                return result;
            }
            return Objects.compare(first.getName(), second.getName(), Comparator.reverseOrder());
        }
    }
}
