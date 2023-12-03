package Day2.Part1;

import java.io.*;
import java.util.*;

public class Part1 {
    static String colours[] = {"red", "green", "blue"};

    public Part1() {
    }

    public static void main(String[] args) throws FileNotFoundException{
        File file = new File(Part1.class.getResource("Test.txt").getFile());
        Scanner scanner = new Scanner(file);
        int playable = 0;
        int gameNumber = 1;
        int sumOfPlayableGameNumbers = 0;

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] parts = line.split(":")[1].split(";");

            boolean isPlayable = true; 
            for(String part : parts){
                String[] cubes = part.split(",");
                Map<String, Integer> colourCounts = new HashMap<>();
                
                for(String cube : cubes){
                    String[] cubeInfo = cube.trim().split(" ");
                    int count = cubeInfo.length > 1 ? Integer.parseInt(cubeInfo[0]) : 1;
                    String colour = cubeInfo[cubeInfo.length > 1 ? 1 : 0];

                    colourCounts.put(colour, colourCounts.getOrDefault(colour, 0) + count);
                }

                if(colourCounts.getOrDefault("red", 0) > 12 || colourCounts.getOrDefault("green", 0) > 13 || colourCounts.getOrDefault("blue", 0) > 14){
                    isPlayable = false;
                    break;
                }
            }
            if(isPlayable){
                playable++;
                sumOfPlayableGameNumbers += gameNumber;
            }
            gameNumber++;
        }
        System.out.println("Number of playable games: " + playable);
        System.out.println("Sum of playable game numbers: " + sumOfPlayableGameNumbers);
    }
}
