package Day5.Part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Part2 {
    static class Range {
        long destination;
        long source;
        long range;

        public Range(long des, long src, long r) {
            destination = des;
            source = src;
            range = r;
        }

        public Range(String line) {
            String[] pieces = line.split(" ");
            destination = Long.parseLong(pieces[0]);
            source = Long.parseLong(pieces[1]);
            range = Long.parseLong(pieces[2]);
        }
    }

    static class RangeMap {
        List<Long> starts;
        List<Long> ends;
        List<Long> betweens;

        public RangeMap(List<Range> ranges) {
            starts = new ArrayList<>();
            ends = new ArrayList<>();
            betweens = new ArrayList<>();
            for (Range range : ranges) {
                starts.add(range.source);
                ends.add(range.destination);
                betweens.add(range.range);
            }
        }

        public long convert(long val) {
            for (int i = 0; i < starts.size(); i++) {
                if (starts.get(i) <= val && starts.get(i) + betweens.get(i) > val) {
                    return ends.get(i) + (val - starts.get(i));
                }
            }
            return val;
        }

        public long[] convert2(long val) {
            long nextStart = 10000000000L;
            for (int i = 0; i < starts.size(); i++) {
                if (starts.get(i) > val) {
                    nextStart = Math.min(nextStart, starts.get(i) - val - 1);
                }
                if (starts.get(i) <= val && starts.get(i) + betweens.get(i) > val) {
                    return new long[]{ends.get(i) + (val - starts.get(i)), betweens.get(i) - (val - starts.get(i)) - 1};
                }
            }
            return new long[]{val, nextStart == 10000000000L ? 0 : nextStart};
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(Part2.class.getResource("Test.txt").getFile());
        Scanner scanner = new Scanner(file);

        List<RangeMap> rangeMaps = new ArrayList<>();
        List<Range> tmp = new ArrayList<>();
        long answer = 10000000000L;
        long[] seeds = null;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("")) {
                continue;
            }
            if (line.contains("map")) {
                if (tmp.size() > 0) {
                    rangeMaps.add(new RangeMap(tmp));
                }
                tmp = new ArrayList<>();
            } else if (line.startsWith("seeds:")) { // Process the "seeds:" line
                String[] stringSeeds = line.split(" ");
                seeds = new long[stringSeeds.length - 1];
                for (int i = 1; i < stringSeeds.length; i++) {
                    seeds[i - 1] = Long.parseLong(stringSeeds[i]);
                }
            } else {
                tmp.add(new Range(line));
            }
        }
        rangeMaps.add(new RangeMap(tmp));

        for (int i = 0; i < seeds.length; i += 2) {
            for (long j = seeds[i]; j < seeds[i] + seeds[i + 1]; j++) {
                long[] ret = returnValAndBound(j, rangeMaps);
                if (ret[0] < answer) {
                    answer = ret[0];
                }
                j += ret[1];
            }
        }

        System.out.println("Lowest location number: " + answer);
    }

    private static long[] returnValAndBound(long val, List<RangeMap> rangeMaps) {
        long bound = 10000000000L;
        for (RangeMap rangeMap : rangeMaps) {
            bound = Math.min(bound, rangeMap.convert2(val)[1]);
            val = rangeMap.convert2(val)[0];
        }
        return new long[]{val, bound};
    }
}