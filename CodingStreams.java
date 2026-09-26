import java.util.List;
import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.stream.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class CodingStreams
{
  public static void main(String args[])
  {
     // find out all the even numbers that exist in the list using Stream
     List<Integer> numbers = Arrays.asList(10,15,8,49,25,98,32);
     List<Integer> evenNumbers = numbers.stream()
                                        .filter(n -> n%2 == 0)
                                        .collect(Collectors.toList());
    System.out.println("Even numbers: " + evenNumbers);

    // find out all the numbers starting with 1 using Stream

     numbers.stream()
            .map( s -> s + "")
            .filter(s -> s.startsWith("1"))
            .forEach(System.out::println);

    
    // find duplicate elements
    List<Integer> duplicateList = List.of(10,25,8,78,32,10,25,6);

    HashSet<Integer> seen  = new HashSet<>();
    duplicateList.stream()
                 .filter(s -> !seen.add(s))
                 .collect(Collectors.toSet());

    // find out the first element of the list using Stream
    List<Integer> firstElementList = List.of(10,25,8,78,32,10,25,6);
    Integer firstElement = firstElementList.stream()
                                            .findFirst()
                                            .orElse(null);
    System.out.println("First element: " + firstElement);

    // count the number of elements in the list using Stream

    long count = numbers.stream()
           .count();
    System.out.println("Count: " + count);
    
    // find out the maximum number in the list
    
    int maxValue = numbers.stream()
           .max(Integer::compare)
           .get();
    System.out.println("Maximum number in the list: " + maxValue);    
  
    //

    String input = "Java articles are Awesome";

    Character firstNonRepeated = input.chars()                             // Step 1: IntStream of char values
                                      .mapToObj(c -> (char) c)             // Step 2: Convert to Character objects
                                      .collect(Collectors.groupingBy(      // Step 3: Group by character, count occurrences
                                          Function.identity(),             //   key = the character itself
                                          LinkedHashMap::new,              //   LinkedHashMap preserves insertion order
                                          Collectors.counting()))          //   value = count of each character
                                      .entrySet().stream()                 // Step 4: Stream over the map entries
                                      .filter(entry -> entry.getValue() == 1L) // Step 5: Keep only characters with count 1
                                      //.filter(entry -> entry.getValue() > 1L)
                                      .map(entry -> entry.getKey())        // Step 6: Extract the character
                                      .findFirst()                         // Step 7: Get the first non-repeated character
                                      .orElse(null);
   // System.out.println("First unique character: " + firstNonRepeated);

    // sort the list using Stream
   List<Integer> myList = Arrays.asList(10,15,8,49,25,98,98,32,15);
   /* myList.stream()
         .sorted()
         .forEach(System.out::println); */

    // sort the list in reverse order using Stream

  //  myList.stream().sorted(Collections.reverseOrder()).forEach(System.out::println);

    // return true if any value appears more than once in the list using Stream
   // Set<Integer> setData = new HashSet<>();
 /*    boolean result = Arrays.stream(MyList))
                           .anyMatch(num -> !setData.add(num)); */

    // get current date and time using Stream
    String currentDateTime = Stream.of(java.time.LocalDateTime.now())
                                   .map(java.time.LocalDateTime::toString)
                                   .findFirst()
                                   .orElse(null);
    System.out.println("Current date and time: " + currentDateTime);
  }
}
    
