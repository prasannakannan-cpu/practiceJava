import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamsLevel3
{
    public static void main(String args[])
    {
     
     
     String input = "EPAM REFERAL PROGRAM";

     String reversedString = Arrays.stream(input.split(" "))
     .map(word -> new StringBuilder(word).reverse().toString())
                  .collect(Collectors.joining(" "));
     System.out.println(reversedString);
     
     
     
        String[] words = {"eat", "tea", "tan", "ate", "nat", "bat"};

     Map<String, List<String>> groups = Arrays.stream(words)
             .collect(Collectors.groupingBy(word -> {
                 char[] chars = word.toCharArray();
                 Arrays.sort(chars);
                 return new String(chars);
             }), Collectors.counting()).entrySet.stream()
             .filter(entry -> entry.getValue() > 1)
             .collect(Collectors.toMap());

     List<List<String>> result = new ArrayList<>(groups.values());

     System.out.println(result);

     System.out.println(groupAnagramsLoop(words));
    }

    public static List<List<String>> groupAnagramsLoop(String[] words)
    {
        Map<String, List<String>> groups = new HashMap<>();

        for (String word : words)
        {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            List<String> bucket = groups.get(key);
            if (bucket == null)
            {
                bucket = new ArrayList<>();
                groups.put(key, bucket);
            }
            bucket.add(word);
        }

        return new ArrayList<>(groups.values());
    }
}
