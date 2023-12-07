package Day4.Part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(Part1.class.getResource("Test.txt").getFile());
        Scanner scanner = new Scanner(file);

        int totalPoints = 0;
        int points = 1;
        boolean isFirstMatch = true;
        while (scanner.hasNextLine()) {
            String card = scanner.nextLine();
            String[] parts = card.split("\\|");
            if (parts.length < 2) {
                continue;  // Skip this iteration of the loop if there are not enough parts
            }
            String[] winningNumbers = parts[0].trim().split(" ");
            String[] yourNumbers = parts[1].trim().split(" ");

            Set<Integer> yourSet = new HashSet<>();
            for (String number : yourNumbers) {
                if (!number.isEmpty() && isNumeric(number)) {
                    yourSet.add(Integer.parseInt(number));
                }
            }

            for (String number : winningNumbers) {
                if (!number.isEmpty() && isNumeric(number) && yourSet.contains(Integer.parseInt(number))) {
                    if (isFirstMatch) {
                        totalPoints += 1;
                        isFirstMatch = false;
                    } else {
                        totalPoints += points;
                        points *= 2;
                    }
                }
            }
            points = 1;
            isFirstMatch = true;
        }
        System.out.println("Total points: " + totalPoints);
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}