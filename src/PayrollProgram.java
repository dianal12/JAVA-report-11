import java.io.*;
import java.util.*;

public class PayrollProgram {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        String fileName = "employees.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 4) {
                    System.err.println("파일 형식 오류: " + line);
                    continue;
                }
                String bunho = parts[0].trim();
                String name = parts[1].trim();
                char hobong = parts[2].trim().charAt(0);
                int sudang = Integer.parseInt(parts[3].trim());

                Employee employee = new Employee(bunho, name, hobong, sudang);
                employees.add(employee);
            }
        } catch (FileNotFoundException e) {
            System.err.println("파일을 찾을 수 없습니다: " + fileName);
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (NumberFormatException e) {
            System.err.println("수당을 숫자로 변환할 수 없습니다.");
            return;
        }

        employees.sort(Employee.byNetPayDescending());

        System.out.println("***************************** 급 여 처 리 *****************************");
        System.out.println("사번   이름   호봉   수당       기본급      급여액      세금      지급액");
        System.out.println("**********************************************************************");

        int totalSudang = 0;
        int totalPay = 0;
        int totalTax = 0;
        int totalNetPay = 0;
        for (Employee employee : employees) {
            System.out.println(employee);
            totalSudang += employee.getSudang();
            totalPay += employee.getTotalPay();
            totalTax += employee.getTax();
            totalNetPay += employee.getNetPay();
        }

        System.out.println("**********************************************************************");
        System.out.printf("합계 %15s %,7d %,7d %,7d %,7d\n",
                "", totalSudang, totalPay, totalTax, totalNetPay);
    }
}

