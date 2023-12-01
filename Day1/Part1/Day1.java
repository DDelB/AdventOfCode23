package Day1.Part1;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1 {
    public static void main(String[] args) {
        try {
            List<Integer> integers = new ArrayList<>();
            File file = new File(Day1.class.getResource("Test.txt").getFile());
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                Matcher m = Pattern.compile("(\\d+)").matcher(line);
                List<String> numbers = new ArrayList<>();
                while (m.find()) {
                    numbers.add(m.group());
                }
                if (!numbers.isEmpty()) {
                    String first = numbers.get(0);
                    String last = numbers.get(numbers.size() - 1);
                    String firstDigit = String.valueOf(first.charAt(0));
                    String lastDigit = String.valueOf(last.charAt(last.length() - 1));
                    String twoDigitNumber = firstDigit + lastDigit;
                    integers.add(Integer.parseInt(twoDigitNumber));
                }
            }
            int totalSum = 0;
            for (int num : integers) {
                totalSum += num;
            }
            System.out.println(totalSum);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
