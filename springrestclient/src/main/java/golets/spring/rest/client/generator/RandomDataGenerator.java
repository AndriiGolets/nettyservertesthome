package golets.spring.rest.client.generator;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class RandomDataGenerator {

    public static Date randomDate() {
        Random r = new Random();
        int month, year, day;
        month = r.nextInt(Calendar.DECEMBER) + Calendar.JANUARY;
        year = r.nextInt(8) + 2010;
        day = r.nextInt(30);
        return new GregorianCalendar(year, month, day).getTime();
    }

    public static int randomPositiveInteger() {
        return randomPositiveInteger(Integer.MAX_VALUE);
    }

    public static int randomPositiveInteger(int range) {
        Random r = new Random();
        return (int) (r.nextDouble() * range);
    }

    public static long randomPositiveLong() {
        return randomPositiveLong(Long.MAX_VALUE);
    }

    public static double randomDouble() {
        Random r = new Random();
        return r.nextDouble();
    }

    public static long randomPositiveLong(long range) {
        Random r = new Random();
        return (long) (r.nextDouble() * range);
    }

    public static String randomHexString() {
        return randomHexString(10);
    }

    public static String randomHexString(int size) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < size) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, size);
    }
}
