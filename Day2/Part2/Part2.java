package Day2.Part2;

import java.io.*;
import java.util.*;

public class Part2 {
    static String colours[] = {"red", "green", "blue"};

    public Part2() {
    }

    public static void main(String[] args) throws FileNotFoundException{
        File file = new File(Part2.class.getResource("Test.txt").getFile());
        Scanner scanner = new Scanner(file);
        int sumOfPowers = 0;

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] parts = line.split(":")[1].split(";");

            Map<String, Integer> minCubes = new HashMap<>();
            for(String colour : colours) {
                minCubes.put(colour, 0);
            }

            for(String part : parts){
                String[] cubes = part.split(",");
                Map<String, Integer> colourCounts = new HashMap<>();
                
                for(String cube : cubes){
                    String[] cubeInfo = cube.trim().split(" ");
                    int count = cubeInfo.length > 1 ? Integer.parseInt(cubeInfo[0]) : 1;
                    String colour = cubeInfo[cubeInfo.length > 1 ? 1 : 0];

                    colourCounts.put(colour, colourCounts.getOrDefault(colour, 0) + count);
                }

                for(String colour : colours) {
                    minCubes.put(colour, Math.max(minCubes.get(colour), colourCounts.getOrDefault(colour, 0)));
                }
            }

            int power = 1;
            for(String colour : colours) {
                power *= minCubes.get(colour);
            }
            sumOfPowers += power;
        }
        System.out.println("Sum of powers: " + sumOfPowers);
    }
}
