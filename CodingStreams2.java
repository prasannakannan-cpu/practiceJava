import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.*;
import java.util.Objects;

class Notes
{
    private final int id;
    private final String tagName;
    private final long tagId;

    Notes(int id, String tagName, long tagId)
    {
        this.id = id;
        this.tagName = tagName;
        this.tagId = tagId;
    }

    public int getId() { return id; }
    public String getTagName() { return tagName; }
    public long getTagId() { return tagId; }
}

public class CodingStreams2
{
    public static void main(String args[])
    {
       List<String> list1 = Arrays.asList("Java", "8", "Stream", "API", "is", "awesome");
       List<String> list2 = Arrays.asList("Learning", "by", "Coding", "Streams", "is", "fun");

       Stream<String> combinedStream = Stream.concat(list1.stream(), list2.stream());

      // combinedStream.forEach(System.out::println); // final output of concatenated stream

       // find numbers whose cube value is greater than 50
       List<Integer> cubes = Arrays.asList(4,5,6,7,1,2,3);
       cubes.stream()
            .map(n -> n * n * n)
            .filter(n -> n > 50)
            .forEach(System.out::println); // final output of cubes greater than 50

    int[] arr = {45, 23, 67, 12, 89, 34};
    Arrays.parallelSort(arr); // parallel sort of array
    Arrays.stream(arr).forEach(System.out::println); // final output of sorted array

    // convert a List of Strings into a List of uppercase Strings
    List<String> wordsList = list1.stream()
                                  .map(String::toUpperCase)
                                  .toList(); // final output of words in uppercase
   // wordsList.forEach(System.out::println);

    // convert a List of objects into a Map, handling duplicate keys and keeping sorted order
    List<Notes> noteLst = new ArrayList<>();
    noteLst.add(new Notes(1, "note1", 11));
    noteLst.add(new Notes(2, "note2", 22));
    noteLst.add(new Notes(3, "note3", 33));
    noteLst.add(new Notes(4, "note4", 44));
    noteLst.add(new Notes(5, "note5", 55));
    noteLst.add(new Notes(6, "note4", 66));

    Map<String, Long> notesRecords = noteLst.stream()
            .sorted(Comparator.comparingLong(Notes::getTagId).reversed()) // sort by tagId: 66,55,44,33,22,11
            .collect(Collectors.toMap(
                    Notes::getTagName,
                    Notes::getTagId,
                    (oldValue, newValue) -> oldValue, // duplicate key keeps first (highest tagId) value: 66
                    LinkedHashMap::new));             // preserves the sorted order
    System.out.println("Notes : " + notesRecords);

    // count the number of occurrences of each name in a List of Strings
    List<String> names = Arrays.asList("John", "Jane", "John", "Jill", "Jane");
    Map<String, Long> nameCount = names.stream()
                                       .filter(name -> Collections.frequency(names, name) > 1) // filter names that occur more than once
                                       .collect(Collectors.groupingBy(
                                        Function.identity(), Collectors.counting()
                                       ));
    System.out.println("Name Count : " + nameCount);

    // check if the list is empty in Java 8 using Optional, if not itreates over the list and prints the elements
     Optional.ofNullable(noteLst)
             .orElseGet(Collections::emptyList)
             .stream()
             .filter(Objects::nonNull)
             .map(Notes::getTagName)
             .forEach(System.out::println); // final output of tag names in noteLst

    
    String str = "Java 8 Stream API is awesome";
    
    Map<String, Long> countLetters = 

    } 
}