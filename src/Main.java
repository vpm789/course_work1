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

        for (Employee employee : employees) {
            if (employee != null) {
                System.out.println(employee);
            }
        }
        System.out.println("Сумма зарплат в месяц: " + sumSalary(employees) + "рублей");
        System.out.println("Сотрудник с минимальной зарплатой: " + getEmployeeMinSalary(employees).substring(0, getEmployeeMinSalary(employees).indexOf(",")));
        System.out.println("Сотрудник с максимальной зарплатой: " + getEmployeeMaxSalary(employees).substring(0, getEmployeeMaxSalary(employees).indexOf(",")));
        System.out.printf("Среднее значение зарплат %.2f рублей", avgSalary(employees));
        System.out.println();
        for (Employee employee : employees) {
            if (employee != null) {
                System.out.println(employee.getName());
            }
        }

        //Повышенная сложность
        System.out.println("======================");
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите процент индексации зарплаты:");
        int index = sc.nextInt();
        indexSalary(employees, index);
        System.out.println("Введите номер отдела:");
        int department = sc.nextInt();
        for (Employee employee : employees) {
            if (employee != null && employee.getDepartment() == department) {
                System.out.println("Сотрудник с минимальной зарплатой в отделе " + department + ": "
                        + getEmployeeMinSalaryDepart(employees, department));
                System.out.println("Сотрудник с максимальной зарплатой в отделе " + department + ": "
                        + getEmployeeMaxSalaryDepart(employees, department));
                System.out.println("Сумму затрат на зарплату по отделу " + department + ": "
                        + sumSalaryDepart(employees, department));
                System.out.printf("Средняя зарплата по отделу " + department
                        + ": %.2f", avgSalaryDepart(employees, department));
                System.out.println();
                indexSalaryDepart(employees, department, index);
                System.out.println("Сотрудники отдела " + department + ": "
                        + getEmployeesDepart(employees, department));
                break;
            } else {
                System.out.println("Отдел не найден");
                break;
            }
        }
        sc.close();
        double minSalary = Double.parseDouble(getEmployeeMinSalary(employees).substring(getEmployeeMinSalary(employees).indexOf(",") + 1, getEmployeeMinSalary(employees).length()));
        double maxSalary = Double.parseDouble(getEmployeeMaxSalary(employees).substring(getEmployeeMaxSalary(employees).indexOf(",") + 1, getEmployeeMaxSalary(employees).length()));
        maxSalary -= minSalary;
        int randomSalary = (int) ((int) (Math.random() * ++maxSalary) + minSalary);
        System.out.println(getEmployeesLessSalary(employees, randomSalary));
        System.out.println(getEmployeesMoreSalary(employees, randomSalary));
    }
    public static String getEmployeesLessSalary(Employee[] employees, int salaryLevel) {
        String employeeName = "";
        for (Employee employee : employees) {
            if (employee != null && employee.getSalary() < salaryLevel) {
                employeeName = employeeName + ", " + employee.getId() + " " + employee.getName() +
                        " " + employee.getSalary() + " рублей";
            }
        }
        return "Сотрудники c зарплатой менее " + salaryLevel + " рублей:" + employeeName.replace(", ", "\n");
    }
    public static String getEmployeesMoreSalary(Employee[] employees, int salaryLevel) {
        String employeeName = "";
        for (Employee employee : employees) {
            if (employee != null && employee.getSalary() > salaryLevel) {
                employeeName = employeeName + ", " + employee.getId() + " " + employee.getName() +
                        " " + employee.getSalary() + " рублей";
            }
        }
        return "Сотрудники  c зарплатой более " + salaryLevel + " рублей:" + employeeName.replace(", ", "\n");
    }
    public static String getEmployeeMinSalary(Employee[] employees) {
        String employeeName = "";
        double minSalary = 0;
        for (Employee employee : employees) {
            if (employee != null) {
                if (minSalary == 0 || employee.getSalary() < minSalary) {
                    employeeName = employee.getName();
                    minSalary = employee.getSalary();
                }
            }
        }
        return employeeName + "," + minSalary;
    }

    public static String getEmployeeMaxSalary(Employee[] employees) {
        String employeeName = "";
        double maxSalary = 0;
        for (Employee employee : employees) {
            if (employee != null) {
                if (maxSalary == 0 || employee.getSalary() > maxSalary) {
                    employeeName = employee.getName();
                    maxSalary = employee.getSalary();
                }
            }
        }
        return employeeName + "," + maxSalary;
    }

    public static String getEmployeeMinSalaryDepart(Employee[] employees, int department) {
        String employeeName = "";
        double minSalary = 0;
        for (Employee employee : employees) {
            if (employee != null && employee.getDepartment() == department) {
                if (minSalary == 0 || employee.getSalary() < minSalary) {
                    employeeName = employee.getName();
                    minSalary = employee.getSalary();
                }
            }
        }
        return employeeName;
    }

    public static String getEmployeesDepart(Employee[] employees, int department) {
        String employeeName = "";
        for (Employee employee : employees) {
            if (employee != null && employee.getDepartment() == department) {
                employeeName = employeeName + ", " + employee.getId() + " " + employee.getName() +
                        " " + employee.getSalary() + " рублей";
            }
        }
        return employeeName.replace(", ", "\n");
    }

    public static void indexSalaryDepart(Employee[] employees, int department, int index) {
        for (Employee employee : employees) {
            if (employee != null && employee.getDepartment() == department) {
                employee.setSalary(Math.round(employee.getSalary() * (1 + ((double) index / 100))));
            }
        }
    }

    public static double avgSalaryDepart(Employee[] employees, int department) {
        double avgSalary = 0;
        int counter = 0;
        for (Employee employee : employees) {
            if (employee != null && employee.getDepartment() == department) {
                counter++;
            }
        }
        avgSalary = (double) sumSalaryDepart(employees, department) / counter;
        return avgSalary;
    }

    public static double sumSalaryDepart(Employee[] employees, int department) {
        double sumSalary = 0;
        for (Employee employee : employees) {
            if (employee != null && employee.getDepartment() == department) {
                sumSalary = sumSalary + employee.getSalary();
            }
        }
        return sumSalary;
    }

    public static String getEmployeeMaxSalaryDepart(Employee[] employees, int department) {
        String employeeName = "";
        double maxSalary = 0;
        for (Employee employee : employees) {
            if (employee != null && employee.getDepartment() == department) {
                if (maxSalary == 0 || employee.getSalary() > maxSalary) {
                    employeeName = employee.getName();
                    maxSalary = employee.getSalary();
                }
            }
        }
        return employeeName;
    }

    public static void indexSalary(Employee[] employees, int index) {
        for (Employee employee : employees) {
            if (employee != null) {
                employee.setSalary(Math.round(employee.getSalary() * (1 + ((double) index / 100))));
            }
        }
    }

    public static double sumSalary(Employee[] employees) {
        double sumSalary = 0;
        for (Employee employee : employees) {
            if (employee != null) {
                sumSalary = sumSalary + employee.getSalary();
            }
        }
        return sumSalary;
    }

    public static double avgSalary(Employee[] employees) {
        double avgSalary = 0;
        int counter = 0;
        for (Employee employee : employees) {
            if (employee != null) {
                counter++;
            }
        }
        avgSalary = (double) sumSalary(employees) / counter;
        return avgSalary;
    }
}