package Day4.Part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(Part2.class.getResource("Test.txt").getFile());
        Scanner scanner = new Scanner(file);

        Map<Integer, Integer> cardCounts = new HashMap<>();
        Queue<Integer> cardQueue = new LinkedList<>();
        List<String> cards = new ArrayList<>();
        int cardIndex = 0;

        while (scanner.hasNextLine()) {
            cards.add(scanner.nextLine());
            cardQueue.add(cardIndex);
            cardCounts.put(cardIndex, cardCounts.getOrDefault(cardIndex, 0) + 1);
            cardIndex++;
        }

        while (!cardQueue.isEmpty()) {
            int currentCardIndex = cardQueue.poll();
            String card = cards.get(currentCardIndex);
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

            int matches = 0;
            for (String number : winningNumbers) {
                if (!number.isEmpty() && isNumeric(number) && yourSet.contains(Integer.parseInt(number))) {
                    matches++;
                }
            }

            for (int i = 1; i <= matches && currentCardIndex + i < cards.size(); i++) {
                int newCardIndex = currentCardIndex + i;
                cardQueue.add(newCardIndex);
                cardCounts.put(newCardIndex, cardCounts.getOrDefault(newCardIndex, 0) + 1);
            }
        }

        int totalCards = cardCounts.values().stream().mapToInt(Integer::intValue).sum();
        System.out.println("Total cards: " + totalCards);
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