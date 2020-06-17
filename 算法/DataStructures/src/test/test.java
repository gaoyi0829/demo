package test;


import java.util.Arrays;

public class test {
   Double a = new Double(123);
   Integer[] b = {1,2,3};
   public void change(Double a, Integer[] b){
      a = 321.0;
      b[0] = 0;
   }

   public static void main(String[] args) {
      test t = new test();
      t.change(t.a,t.b);
      System.out.println(t.a);
      System.out.println(Arrays.toString(t.b));
   }
}
