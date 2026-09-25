public class CodingStreams
{
  public static void main(String args[])
  {
     // find out all the even numbers that exist in the list using Stream
     List<Integer> numbers = Array.asList(10,15,8,49,25,98,32);
     List<Integer> evenNumbers = numbers.stream()
                                        .filter(n -> n%2 == 0)
                                        .collect(Collectors.toList());
    System.out.println("Even numbers: " + evenNUmbers);
  }
}
    
