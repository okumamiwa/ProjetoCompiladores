import java.util.Scanner;
public class MainClass{ 
  public static void main(String args[]){
       Scanner _key = new Scanner(System.in);
double  a;
double  b;
double  c;
double  d;
String  t1;
String  t2;
a= _key.nextDouble();
b= _key.nextDouble();
a = 1+2*3/b;
if (a<b) {
System.out.println(a);}else {
System.out.println(b);}

c = 0;
t1 = "testing";
while (c<5) {
System.out.println(c);c = c+1;}
do {
System.out.println(c);c = c-1;} 
 while (c>0);
if (c==a) {System.out.println(a);} else { System.out.println(t1);}

System.out.println(t1);
}}