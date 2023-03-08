import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Employee[] employees = new Employee[10];
        Employee employee1 = new Employee("Иванов Иван Петрович", 5, 154000);
        Employee employee2 = new Employee("Жамнов Сергей Юрьевич", 5, 110000);
        Employee employee3 = new Employee("Петрова Ольга Ивановна", 3, 120800);
        Employee employee4 = new Employee("Трофимов Илья Владимирович", 3, 134000);
        Employee employee5 = new Employee("Реева Марина Михайловна", 3, 70000);
        Employee employee6 = new Employee("Сидорова Александра Сергеевна", 9, 170000);
        Employee employee7 = new Employee("Маринина Татьяна Владимировна", 9, 95000);
        employees[0] = employee1;
        employees[2] = employee2;
        employees[3] = employee3;
        employees[5] = employee4;
        employees[6] = employee5;
        employees[8] = employee6;
        employees[9] = employee7;
        System.out.println(getEmployees(employees));
        System.out.println("Сумма зарплат в месяц: " + sumSalary(employees) + "рублей");
        System.out.println("Сотрудник с минимальной зарплатой: " + getEmployeeMinSalary(employees).substring(0, getEmployeeMinSalary(employees).indexOf(",")));
        System.out.println("Сотрудник с максимальной зарплатой: " + getEmployeeMaxSalary(employees).substring(0, getEmployeeMaxSalary(employees).indexOf(",")));
        System.out.printf("Среднее значение зарплат %.2f рублей", avgSalary(employees));
        System.out.println();
        System.out.println(getNames(employees));
//        Повышенная сложность
        System.out.println("======================");
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите процент индексации зарплаты:");
        int index = sc.nextInt();
        indexSalary(employees, index);
        int n = (int) Math.floor(Math.random() * employees.length);
        if (employees[n] != null) {
            System.out.println("Сотрудник с минимальной зарплатой в отделе " + employees[n].getDepartment() + ": "
                    + getEmployeeMinSalaryDepart(employees, employees[n].getDepartment()));
            System.out.println("Сотрудник с максимальной зарплатой в отделе " + employees[n].getDepartment() + ": "
                    + getEmployeeMaxSalaryDepart(employees, employees[n].getDepartment()));
            System.out.println("Сумму затрат на зарплату по отделу " + employees[n].getDepartment() + ": "
                    + sumSalaryDepart(employees, employees[n].getDepartment()));
            System.out.printf("Средняя зарплата по отделу " + employees[n].getDepartment()
                    + ": %.2f", avgSalaryDepart(employees, employees[n].getDepartment()));
            System.out.println();
            indexSalaryDepart(employees, employees[n].getDepartment(), index);
            System.out.println("Сотрудники отдела " + employees[n].getDepartment() + ": "
                    + getEmployeesDepart(employees, employees[n].getDepartment()));
        } else {
            System.out.println("Отдел не найден");
        }
        double minSalary = Double.parseDouble(getEmployeeMinSalary(employees).substring(getEmployeeMinSalary(employees).indexOf(",") + 1, getEmployeeMinSalary(employees).length()));
        double maxSalary = Double.parseDouble(getEmployeeMaxSalary(employees).substring(getEmployeeMaxSalary(employees).indexOf(",") + 1, getEmployeeMaxSalary(employees).length()));
        maxSalary -= minSalary;
        int randomSalary = (int) ((int) (Math.random() * ++maxSalary) + minSalary);
        System.out.println(getEmployeesLessSalary(employees, randomSalary));
        System.out.println(getEmployeesMoreSalary(employees, randomSalary));
    }
    public static String getEmployeesLessSalary(Employee[] employees, int randomSalary) {
        String employee = new String();
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getSalary() < randomSalary) {
                employee = employee + ", " + employees[i].getId() + " " + employees[i].getName() +
                        " " + employees[i].getSalary() + " рублей";
            }
        }
        return "Сотрудники c зарплатой менее " + randomSalary + " рублей:" + employee.replaceAll(", ", "\n");
    }
    public static String getEmployeesMoreSalary(Employee[] employees, int randomSalary) {
        String employee = new String();
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getSalary() > randomSalary) {
                employee = employee + ", " + employees[i].getId() + " " + employees[i].getName() +
                        " " + employees[i].getSalary() + " рублей";
            }
        }
        return "Сотрудники  c зарплатой более " + randomSalary + " рублей:" + employee.replaceAll(", ", "\n");
    }
    public static String getEmployeeMinSalary(Employee[] employees) {
        String employee = new String();
        double minSalary = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                if (minSalary == 0 || employees[i].getSalary() < minSalary) {
                    employee = employees[i].getName();
                    minSalary = employees[i].getSalary();
                }
            }
        }
        return employee + "," + minSalary;
    }

    public static String getEmployeeMaxSalary(Employee[] employees) {
        String employee = new String();
        double maxSalary = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                if (maxSalary == 0 || employees[i].getSalary() > maxSalary) {
                    employee = employees[i].getName();
                    maxSalary = employees[i].getSalary();
                }
            }
        }
        return employee + "," + maxSalary;
    }

    public static String getEmployeeMinSalaryDepart(Employee[] employees, int department) {
        String employee = new String();
        double minSalary = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getDepartment() == department) {
                if (minSalary == 0 || employees[i].getSalary() < minSalary) {
                    employee = employees[i].getName();
                    minSalary = employees[i].getSalary();
                }
            }
        }
        return employee;
    }

    public static String getEmployeesDepart(Employee[] employees, int department) {
        String employee = new String();
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getDepartment() == department) {
                employee = employee + ", " + employees[i].getId() + " " + employees[i].getName() +
                        " " + employees[i].getSalary() + " рублей";
            }
        }
        return employee.replaceAll(", ", "\n");
    }

    public static void indexSalaryDepart(Employee[] employees, int department, int index) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getDepartment() == department) {
                employees[i].setSalary(Math.round(employees[i].getSalary() * (1 + ((double) index / 100))));
            }
        }
    }

    public static double avgSalaryDepart(Employee[] employees, int department) {
        double avgSalary = 0;
        int counter = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getDepartment() == department) {
                counter++;
            }
        }
        avgSalary = (double) sumSalaryDepart(employees, department) / counter;
        return avgSalary;
    }

    public static double sumSalaryDepart(Employee[] employees, int department) {
        double sumSalary = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getDepartment() == department) {
                sumSalary = sumSalary + employees[i].getSalary();
            }
        }
        return sumSalary;
    }

    public static String getEmployeeMaxSalaryDepart(Employee[] employees, int department) {
        String employee = new String();
        double maxSalary = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getDepartment() == department) {
                if (maxSalary == 0 || employees[i].getSalary() > maxSalary) {
                    employee = employees[i].getName();
                    maxSalary = employees[i].getSalary();
                }
            }
        }
        return employee;
    }

    public static void indexSalary(Employee[] employees, int index) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                employees[i].setSalary(Math.round(employees[i].getSalary() * (1 + ((double) index / 100))));
            }
        }
    }

    public static String getEmployees(Employee[] employees) {
        String employee = new String();
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                employee = employee + ", " + employees[i].getId() + " " + employees[i].getName() +
                        " отдел:" + employees[i].getDepartment() + " " + employees[i].getSalary() + " рублей";
            }
        }
        return "Сотрудники:" + employee.replaceAll(", ", "\n");
    }

    public static String getNames(Employee[] employees) {
        String names = new String();
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                names = names + ", " + employees[i].getName();
            }
        }
        return "ФИО сотрудников:" + names.replaceAll(", ", "\n");
    }


    public static double sumSalary(Employee[] employees) {
        double sumSalary = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                sumSalary = sumSalary + employees[i].getSalary();
            }
        }
        return sumSalary;
    }

    public static double avgSalary(Employee[] employees) {
        double avgSalary = 0;
        int counter = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                counter++;
            }
        }
        avgSalary = (double) sumSalary(employees) / counter;
        return avgSalary;
    }
}