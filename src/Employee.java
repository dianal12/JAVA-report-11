import java.util.Comparator;

public class Employee {
    private String bunho;
    private String name;
    private char hobong;
    private int sudang;
    private int baseSalary;
    private int totalPay;
    private int tax;
    private int netPay;

    public Employee(String bunho, String name, char hobong, int sudang) {
        this.bunho = bunho;
        this.name = name;
        this.hobong = hobong;
        this.sudang = sudang;
        calculateSalary();
    }

    private void calculateSalary() {
        switch (hobong) {
            case '1':
                baseSalary = 1650000;
                break;
            case '2':
                baseSalary = 1680000;
                break;
            case '3':
                baseSalary = 1700000;
                break;
            case '4':
                baseSalary = 1720000;
                break;
            case '5':
                baseSalary = 1750000;
                break;
            default:
                baseSalary = 0;
        }

        totalPay = baseSalary + sudang;

        if (totalPay < 1700000) {
            tax = (int) (totalPay * 0.05);
        } else if (totalPay < 2300000) {
            tax = (int) (totalPay * 0.08);
        } else {
            tax = (int) (totalPay * 0.10);
        }

        netPay = totalPay - tax;
    }

    public String getName() {
        return name;
    }

    public int getSudang() {
        return sudang;
    }

    public int getTotalPay() {
        return totalPay;
    }

    public int getTax() {
        return tax;
    }

    public int getNetPay() {
        return netPay;
    }

    public static Comparator<Employee> byNetPayDescending() {
        return (e1, e2) -> Integer.compare(e2.getNetPay(), e1.getNetPay());
    }

    @Override
    public String toString() {
        return String.format("%-4s %-4s %2s %7s %,7d %,7d %,7d %,7d",
                bunho, name, hobong, "", sudang, baseSalary, totalPay, tax, netPay);
    }
}

