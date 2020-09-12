package com.example.demo.lesson.jvm.construction;

public class Master {

    final static Master master1 = null;
    final Master master2 = null;
    static Master master3 = null;
    Master master4 = null;

    final static int int1 = 9;
    final int int2 = 10;
    static int int3 = 11;
    int int4 = 12;

    final static Integer integerMax1 = 1014;
    final Integer integerMax2 = 1015;
    static Integer integerMax3 = 1016;
    Integer integerMax4 = 1017;

    final static Integer integerMin1 = 19;
    final Integer integerMin2 = 20;
    static Integer integerMin3 = 21;
    Integer integerMin4 = 22;

    final static String str1 = "str24";
    final String str2 = "str25";
    static String str3 = "str26";
    String str4 = "str27";

    final static String strN1 = new String("str29");
    final String strN2 = new String("str30");
    static String strN3 = new String("str31");
    String strN4 = new String("str32");

    final static String strI1 = "str34".intern();
    final String strI2 = "str35".intern();
    static String strI3 = "str36".intern();
    String strI4 = "str37".intern();

    public static void main(String[] args) {
        final Master master5 = null;
        Master master6 = null;

        final int int5 = 44;
        int int6 = 45;

        final Integer integerMax5 = 1047;
        Integer integerMax6 = 1048;

        final Integer integerMin5 = 50;
        Integer integerMin6 = 51;

        final String str5 = "str53";
        String str6 = "str54";

        final String strN5 = new String("str56");
        String strN6 = new String("str57");

        final String strI5 = "str59".intern();
        String strI6 = "str60".intern();

        Integer integerMin7 = 19;
        Integer integerMax7 = 1014;
        Integer integerMax8 = 1048;
        System.out.println(integerMin7 == integerMin1);
        System.out.println(integerMax1 == integerMax7);
        System.out.println(integerMax6 == integerMax8);
        System.out.println(Integer.valueOf(1046) == Integer.valueOf(1046));
        System.out.println(Integer.valueOf(100) == Integer.valueOf(100));

        System.out.println(strN1 == "str29");
        System.out.println(strI5 == "str56");
        System.out.println(strN5 == "str56");
        String abc = new String("abc").intern();
        System.out.println("abc".hashCode());
        System.out.println(abc.hashCode());
        System.out.println(new String("abc").hashCode());
        System.out.println("bcd".hashCode());

    }
}
