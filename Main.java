package Calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        System.out.println("Введите выражение");
        String input = scn.nextLine();
        System.out.println(calc(input));
        scn.close();
    }
    public static String calc(String input) throws Exception {
        int num1;
        int num2;
        boolean isRoman;
        String[] strSplit = input.split(" ");
        int len = strSplit.length;
        if (len > 3) throw new Exception("Неверный формат чисел");
        if (len < 3) throw new Exception("Строка не является математической операцией");
        String n1 = strSplit[0];
        String action = strSplit[1];
        String n2 = strSplit[2];
        if ((action.equals("+")) || (action.equals("-")) || (action.equals("*")) || (action.equals("/"))) {
            if (Roman.isRoman(strSplit[0]) && Roman.isRoman(strSplit[2])) {
                num1 = Roman.convertToArabian(strSplit[0]);
                num2 = Roman.convertToArabian(strSplit[2]);
                isRoman = true;
            } else if ((n1.equals("0")) || (n2.equals("0"))) throw new Exception("Введите число от 1 до 10");
            else if (!Roman.isRoman(strSplit[0]) && !Roman.isRoman(strSplit[2])) {
                num1 = Integer.parseInt(strSplit[0]);
                num2 = Integer.parseInt(strSplit[2]);
                isRoman = false;
            } else throw new Exception("Используются разные системы счисления");
            if (num1>10 || num2>10 || num1<1 || num2<1)
                throw new Exception("Числа должны быть от 1 до 10");
            int variant = expression(num1, num2, action);
            if (isRoman) {
                if (variant<0) throw new Exception("В римской системе нет отрицательных чисел");
                input = Roman.convertToRoman(variant);
            } else
                input = String.valueOf(variant);
            return input;
        }
        throw new Exception("Неверный формат выражения");
    }
    static int expression(int a, int b, String example) throws Exception {
        if (example.equals("+")) return a + b;
            else if (example.equals("-")) return a - b;
            else if (example.equals("*")) return a * b;
            else if (example.equals("/")) return a / b;
        return a / b;
    }
    class Roman {
        static String[] roman = new String[] {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
        public static boolean isRoman(String val) {
            for (int i = 0; i < roman.length; i++) {
                if (val.equals(roman[i])) {
                    return true;
                }
            }
            return false;
        }
        public static int convertToArabian(String romanTo) {
            for (int i = 0; i < roman.length; i++) {
                if (romanTo.equals(roman[i])) {
                    return i;
                }
            }
            return -1;
        }
        public static String convertToRoman(int arabian) { return roman[arabian]; }
    }
}
