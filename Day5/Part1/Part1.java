package Day5.Part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(Part1.class.getResource("Test.txt").getFile());
        Scanner scanner = new Scanner(file);

        List<Long> seeds = new ArrayList<>();
        Map<String, List<long[]>> maps = new HashMap<>();
        String currentMap = "";

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("seeds:")) {
                String[] parts = line.split(":")[1].trim().split(" ");
                for (String part : parts) {
                    seeds.add(Long.parseLong(part));
                }
            } else if (line.endsWith("map:")) {
                currentMap = line.split(" ")[0];
                maps.put(currentMap, new ArrayList<>());
            } else if (!line.isEmpty()) {
                String[] parts = line.split(" ");
                long[] range = {Long.parseLong(parts[0]), Long.parseLong(parts[1]), Long.parseLong(parts[2])};
                maps.get(currentMap).add(range);
            }
        }

        long minLocation = Long.MAX_VALUE;
        for (long seed : seeds) {
            long soil = processMap(maps.get("seed-to-soil"), seed);
            long fertilizer = processMap(maps.get("soil-to-fertilizer"), soil);
            long water = processMap(maps.get("fertilizer-to-water"), fertilizer);
            long light = processMap(maps.get("water-to-light"), water);
            long temperature = processMap(maps.get("light-to-temperature"), light);
            long humidity = processMap(maps.get("temperature-to-humidity"), temperature);
            long location = processMap(maps.get("humidity-to-location"), humidity);
            minLocation = Math.min(minLocation, location);
        }

        System.out.println("Lowest location number: " + minLocation);
    }

    private static long processMap(List<long[]> map, long input) {
        for (long[] range : map) {
            long destStart = range[0];
            long srcStart = range[1];
            long length = range[2];
            if (input >= srcStart && input < srcStart + length) {
                return destStart + (input - srcStart);
            }
        }
        return input;
    }
}