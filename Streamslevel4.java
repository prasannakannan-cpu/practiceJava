import java.util.stream.*;
import java.util.Arrays;
import java.util.List;

public class Streamslevel4
{
    public static void main(String args[])
    {
        List<Integer> nums = Arrays.asList(10, 45, 22, 90, 33);

        int secondLargest = nums.stream()
                           .sorted(Comparator.reversedOrder())
                           .skip(1)
                           .findFirst()
                           .orElse(0);

         System.out.println("Second largest number: " + secondLargest);

         // the other way to find the second largest number is to use distinct() method to remove duplicates 
         // and then sort the stream in reverse order and skip the first element to get the second largest number.
         int max = nums.stream()
                .max(Comparator.naturalOrder())
                .orElseThrow(() -> new IllegalArgumentException("List must not be empty"));

         secondLargest = nums.stream()
                .filter(n -> n != max)
                .max(Comparator.naturalOrder())
                .orElseThrow(() -> new IllegalArgumentException("No second largest element"));

         System.out.println("Second largest number: " + secondLargest);

         // fibonacci series using streams
            int n = 10; // number of terms
            List<Integer> fibonacciSeries = Stream.iterate(new int[]{0, 1}, f -> new int[]{f[1], f[0] + f[1]})
                    .limit(n)
                    .map(f -> f[0])
                    .collect(Collectors.toList());

            System.out.println("Fibonacci series: " + fibonacciSeries);

        // summary statistics using streams
            List<Integer> nums1 = Arrays.asList(4, 8, 15, 16, 23, 42);

        IntSummaryStatistics stats = nums1.stream().mapToInt(Integer::intValue).summaryStatistics();

        System.out.println("Count: " + stats.getCount());
        System.out.println("Sum: " + stats.getSum());
        System.out.println("Min: " + stats.getMin());
        System.out.println("Max: " + stats.getMax());


    }
}