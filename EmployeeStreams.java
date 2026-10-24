import java.util.stream.Collectors;

record Employee(String name, String dept, int salary, List<String> projects) {}

public class EmployeeStreams {
    public static void main(String[] args) {

    List<Employee> employees = List.of(
      new Employee("Alice",  "IT", 90000, List.of("Alpha", "Beta")),
      new Employee("Bob",    "IT", 60000, List.of("Beta", "Gamma")),
      new Employee("Carol",  "HR", 50000, List.of("Delta")),
      new Employee("Dave",   "HR", 75000, List.of("Alpha", "Delta")),
      new Employee("Eve",    "Finance", 85000, List.of("Gamma"))
   );
    
   List<String> itEmployees = employees.stream()
                             .filter(e -> e.dept().equals("IT"))
                             .map(Employee::name)
                             .toList();

    Map<String, Integer> employeesCountByDept = employees.stream()
                                                .collect(Collectors.groupingBy(Employee::dept, Collectors.counting()));

    System.out.println("Department-wise employee count: " + employeesCountByDept);


    }
}


