package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder(
                "Report for period " + dateFrom + " - " + dateTo
                        + System.lineSeparator()
        );

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate startDate = LocalDate.parse(dateFrom.trim(), formatter);
        LocalDate endDate = LocalDate.parse(dateTo.trim(), formatter);

        boolean firstEmployee = true;

        for (String employee : names) {
            int totalSalary = 0;

            for (String record : data) {
                String[] parts = record.split(" ");

                if (parts[1].equals(employee)) {
                    LocalDate workDate = LocalDate.parse(parts[0], formatter);

                    if (!workDate.isBefore(startDate)
                            && !workDate.isAfter(endDate)) {
                        int hours = Integer.parseInt(parts[2]);
                        int rate = Integer.parseInt(parts[3]);

                        totalSalary += hours * rate;
                    }
                }
            }

            if (!firstEmployee) {
                report.append(System.lineSeparator());
            }
            firstEmployee = false;

            report.append(employee)
                    .append(" - ")
                    .append(totalSalary);
        }
        return report.toString();
    }
}
