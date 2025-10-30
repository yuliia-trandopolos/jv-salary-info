package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom.trim(), DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo.trim(), DATE_FORMATTER);

        int[] salaries = new int[names.length];

        for (String record : data) {
            String[] parts = record.split("\\s+");
            LocalDate workDate = LocalDate.parse(parts[0], DATE_FORMATTER);
            String employee = parts[1];
            int hoursWorked = Integer.parseInt(parts[2]);
            int incomePerHour = Integer.parseInt(parts[3]);

            if (! workDate.isBefore(fromDate) && ! workDate.isAfter(toDate)) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(employee)) {
                        salaries[i] += hoursWorked * incomePerHour;
                        break;
                    }
                }
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            report.append(names[i]).append(" - ").append(salaries[i]);
            if (i != names.length - 1) {
                report.append(System.lineSeparator());
            }
        }
        return report.toString();
    }
}
