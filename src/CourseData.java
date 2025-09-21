import java.util.*;

public class CourseData {
    // Map: Batch -> (Semester -> Courses list)
    private static final Map<String, Map<String, List<String>>> batchSemesterCourses = new HashMap<>();

    static {
        // Batch 13 courses
        Map<String, List<String>> batch13 = new HashMap<>();
        batch13.put("1", Arrays.asList(
                "CSE 1101 – Structured Programming",
                "CSE 1102 – Discrete Mathematics",
                "STAT 1103 – Probability and Statistics for Engineers I",
                "MATH 1107 – Calculus I",
                "GE 1105 – Introduction to Sociology",
                "SE 1106 – Introduction to Software Engineering",
                "CSE 1101L – Structured Programming Lab"
        ));
        batch13.put("2", Arrays.asList(
                "CSE 1215 – Data Structure",
                "MATH 1216 – Calculus II",
                "CSE 1211 – Computer Organization",
                "STAT 1203 – Probability and Statistics for Engineers II",
                "GE 1212 – Bangladesh Studies",
                "SE 1206 – Object Oriented Concepts",
                "CSE 1215L – Data Structure Lab",
                "CSE 1211L – Computer Organization Lab",
                "SE 1206L – Object Oriented Concepts Lab"
        ));
        batch13.put("3", Arrays.asList(
                "CSE 2115 – Introduction to Algorithm",
                "SE 2112 – Theory of Computing",
                "CSE 2111 – Computer Networking",
                "MATH 2116 – Linear Algebra",
                "SE 2105 – Software Project Lab I",
                "BUS 2117 – Business Communication",
                "CSE 2115L – Introduction to Algorithm Lab",
                "CSE 2111L – Computer Networking Lab"
        ));
        batch13.put("4", Arrays.asList(
                "CSE 2201 – Operating System and System Programming",
                "SE 2215 – Design Patterns",
                "CSE 2204 – Database Management System",
                "BUS 2205 – Business Studies for Engineers",
                "SE 2206 – Software Requirements Specification and Analysis",
                "CSE 2201L – Operating System and System Programming Lab",
                "SE 2215L – Design Patterns Lab",
                "CSE 2204L – Database Management System Lab",
                "SE 2206L – Software Requirements Specification and Analysis Lab"
        ));
        batch13.put("5", Arrays.asList(
                "SE 3115 – Software Testing and Quality Assurance",
                "CSE 3102 – Web Technology",
                "CSE 3116 – Mathematical and Statistical Methods for Data Analysis",
                "CSE 3117 – Artificial Intelligence",
                "SE 3118 – Software Design and Analysis",
                "CSE 3119 – Cryptography and Security Mechanisms",
                "SE 3105 – Software Project Lab II",
                "CSE 3102L – Web Technology Lab",
                "CSE 3119L – Cryptography and Security Mechanisms Lab"
        ));
        batch13.put("6", Arrays.asList("SE 3215 – Internship"));
        batch13.put("7", Arrays.asList(
                "CSE 4115 – Distributed Systems",
                "CSE 4116 – Compiler Design",
                "CSE 4117 - IT Laws and Ethics",
                "CSE/SE 41XX – Elective 1",
                "CSE 4118 – Machine Learning",
                "CSE 4115L – Distributed Systems Lab",
                "SE 4119 – Project"
        ));
        batch13.put("8", Arrays.asList(
                "SE 4215 – Software Security",
                "SE 4203 – Software Project Management",
                "CSE/SE 42XX – Elective 2",
                "CSE/SE 42XX – Elective 3",
                "SE 4201 – Project",
                "SE 4215L – Software Security Lab",
                "SE 4216 – Software Metrics",
                "SE 4216L – Software Metrics Lab"
        ));

        // Add batch 13 to main map
        batchSemesterCourses.put("13", deepCopyBatch(batch13));

        // Copy batch 13 to batches 14–17 with deep copy
        for (int b = 14; b <= 17; b++) {
            batchSemesterCourses.put(String.valueOf(b), deepCopyBatch(batch13));
        }
    }

    // Helper method to create deep copy of a batch
    private static Map<String, List<String>> deepCopyBatch(Map<String, List<String>> original) {
        Map<String, List<String>> copy = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : original.entrySet()) {
            copy.put(entry.getKey(), new ArrayList<>(entry.getValue())); // deep copy list
        }
        return copy;
    }

    // Get courses for a batch & semester
    public static List<String> getCoursesForBatchAndSemester(String batch, String semester) {
        Map<String, List<String>> semesterCourses = batchSemesterCourses.get(batch);
        if (semesterCourses != null) {
            return semesterCourses.getOrDefault(semester, new ArrayList<>());
        }
        return new ArrayList<>();
    }

    // Get all batches
    public static Set<String> getAllBatches() {
        return batchSemesterCourses.keySet();
    }

    // Get semesters for a batch
    public static Set<String> getSemestersForBatch(String batch) {
        Map<String, List<String>> semesterCourses = batchSemesterCourses.get(batch);
        return semesterCourses != null ? semesterCourses.keySet() : new HashSet<>();
    }
}
