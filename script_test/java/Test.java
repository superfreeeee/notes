// public class Test {
//   public static void main(String[] args) {
//     // System.out.println("Test start: ");
//     // System.out.println(ClassA.propertyString);
//     // System.out.println(new ClassA());
//     int i = test();
//     System.out.println(i);
//   }

//   static int test() {
//     return 1;
//   }

// }

// class ClassA {
//   static String propertyString = "propertyString";
//   {
//     System.out.println("instance code block of ClassA");
//   }

//   static {
//     System.out.println("static code block of ClassA");
//   }
  

//   public ClassA() {
//     System.out.println("constructor of ClassA");    
//   }
// }
import java.util.ArrayList;

class Solution {
  public int lengthOfLongestSubstring(String s) {
    StringBuilder temp = new StringBuilder();
    int front;
    int result = 0;
    for(char c : s.toCharArray()) {
      if((front = temp.indexOf(c+"")) >= 0) {
        if(temp.length() > result) 
          result = temp.length();
        temp = new StringBuilder(temp.substring(front + 1));
      }
      temp.append(c);
      System.out.println("\""+ temp +"\"");
    }
    return temp.length() > result ? temp.length() : result;
  }

  public static void main(String[] args) {
    String s = " ";
    int result = new Solution().lengthOfLongestSubstring(s);
    System.out.println(result);
  }
}