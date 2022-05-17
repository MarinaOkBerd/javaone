import java.awt.desktop.SystemSleepEvent;
import java.util.Scanner;
import java.util.SplittableRandom;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = null;
        try {
            System.out.println("Введите математическое выражение.");
            scanner = new Scanner(System.in);
            String task = scanner.nextLine();
            calc (task);
        } catch (Exception e) {
            System.out.println("Ошибка в ходе выполнения программы: " + e.getMessage());
        } finally {
            if (scanner != null){
              scanner.close();
            }
        }
    }
    public static void calc(String task) throws Exception {
        if (task==null || task.isEmpty()){
            throw new Exception("Строка пустая.");
        }

        if (task.trim().startsWith("-")){
            throw new Exception("Числа не могут быть отрицательные.");
        }

        String sign = "";
        if (task.contains("+")) {
            sign = "+";
        } else if (task.contains("-")) {
            sign = "-";
        } else if (task.contains("*")) {
            sign = "*";
        } else if (task.contains("/")) {
            sign = "/";
        } else {
            throw new Exception("Строка не является математической операцией.");
        }

        int signPosition = task.indexOf(sign);
        String sX = task.substring(0, signPosition).trim();
        String sY = task.substring(signPosition + 1).trim();
        if ((sX.contains(".") || sX.contains(",")) || (sY.contains(".") || sY.contains(","))){
            throw new Exception("Введено дробное число. Введите целое число.");
        }
        if (sY.contains("+") || sY.contains("-") || sY.contains("*") || sY.contains("/")){
            throw new Exception("Формат математической операции не удовлетворяет условию задения.");
        }

        if ((isRoman(sX) && !isRoman(sY)) || (!isRoman(sX) && isRoman(sY))) {
            throw new Exception("Используются одновременно разные системы счисления.");
        }

        int x = number(sX);
        int y = number(sY);
        int z = 0;
        if ("+".equals(sign)) {
            z = x + y;
        } else if ("-".equals(sign)){
            z = x - y;
        } else if ("*".equals(sign)){
            z = x * y;
        } else if ("/".equals(sign)){
            if (y==0) {
                throw new Exception("На 0 делить нельзя.");
            }
            z = x / y;
        }
        if (isRoman(sX)){
            if (z<0){
                throw new Exception("В римской сисмеме нет отрицательных чисел.");
            }
            System.out.println(toRoman(z));
        } else {
            System.out.println(z);
        }
    }
    public static boolean isRoman(String roman){
        if (    "I".equalsIgnoreCase(roman) ||
                "II".equalsIgnoreCase(roman)||
                "III".equalsIgnoreCase(roman) ||
                "IV".equalsIgnoreCase(roman) ||
                "V".equalsIgnoreCase(roman)||
                "VI".equalsIgnoreCase(roman) ||
                "VII".equalsIgnoreCase(roman) ||
                "VIII".equalsIgnoreCase(roman) ||
                "IX".equalsIgnoreCase(roman) ||
                "X".equalsIgnoreCase(roman) ){
            return true;
        } else {
            return false;
        }
    }
    public static String toRoman(int z){
        if (z==0){
            return "";
        } else if (z==1){
            return "I";
        } else if (z==2){
            return "II";
        } else if (z==3){
            return "III";
        } else if (z==4){
            return "IV";
        }else if (z==5){
            return "V";
        } else if (z==6){
            return "VI";
        } else if (z==7){
            return "VII";
        } else if (z==8){
            return "VIII";
        } else if (z==9){
            return "IX";
        } else if (z==10){
            return "X";
        } else if (z<40){
            return "X" + toRoman(z-10);
        } else if (z<50){
            return "XL" + toRoman(z-40);
        } else if (z<90){
            return "L" + toRoman(z-50);
        } else if (z<100){
            return "XC" + toRoman(z-90);
        } else if (z==100){
            return "C";
        }
        return "";
    }
    public static int number(String roman) {
        if ("I".equalsIgnoreCase(roman)) {
            return 1;
        } else if ("II".equalsIgnoreCase(roman)) {
            return 2;
        } else if ("III".equalsIgnoreCase(roman)) {
            return 3;
        } else if ("IV".equalsIgnoreCase(roman)) {
            return 4;
        } else if ("V".equalsIgnoreCase(roman)) {
            return 5;
        } else if ("VI".equalsIgnoreCase(roman)) {
            return 6;
        } else if ("VII".equalsIgnoreCase(roman)) {
            return 7;
        } else if ("VIII".equalsIgnoreCase(roman)) {
            return 8;
        } else if ("IX".equalsIgnoreCase(roman)) {
            return 9;
        } else if ("X".equalsIgnoreCase(roman)) {
            return 10;
        } else {
            int n = Integer.parseInt(roman);
            return n;
        }
    }
}