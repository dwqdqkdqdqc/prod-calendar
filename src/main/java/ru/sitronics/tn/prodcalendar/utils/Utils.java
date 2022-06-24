package ru.sitronics.tn.prodcalendar.utils;

import ru.sitronics.tn.prodcalendar.model.Calendar;
import ru.sitronics.tn.prodcalendar.model.MonthOfYear;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.toMap;

public class Utils {

    /**
     * @param calendar объект типа Календарь.
     * @param inputDate строка с датой начала отсчета.
     * @param count число производственных дней.
     * @return дату типа LocalDate.
     */
    public static LocalDate getProdDate(Calendar calendar, String inputDate, int count) {
        LocalDate start = LocalDate.parse(inputDate);

        Map<Integer, List<Integer>> daysOff = getDaysOff(calendar);
        List<LocalDate> workDays = LocalDate
                .of(start.getYear(), 1, 1)
                .datesUntil(LocalDate.of(2023, 1, 1))
                .filter(l -> {
                    List<Integer> integers = daysOff.get(l.getMonthValue());
                    return !integers.contains(l.getDayOfMonth());
                })
                .toList();
        for (int i = 0; i < workDays.size(); i++) {
            if (workDays.get(i).equals(start)) {
                int k = count;
                while (k != 0) {
                    i++;
                    k--;
                }
                return workDays.get(i);
            }
        }
        return null;
    }

    /**
     * @param calendar из Интернета за определенный год.
     * @return мапу, которая содержит по ключу номер месяца, а по значению - список праздничных и выходных дней.
     */
    private static Map<Integer, List<Integer>> getDaysOff(Calendar calendar) {
        return calendar.getMonths()
                .stream()
                .collect(toMap(
                        MonthOfYear::getMonth,
                        m -> Arrays
                                .stream(m.getDays().split(","))
                                .filter(d -> !d.contains("*")) //* - это сокращенный рабочий день.
                                .map(d -> {
                                    if (d.contains("+")) {//+ - это перенос праздничного дня на рабочий день.
                                        return Integer.parseInt(d.substring(0, d.indexOf("+")));
                                    }
                                    return Integer.parseInt(d);
                                })
                                .toList()
                        )
                );
    }
}