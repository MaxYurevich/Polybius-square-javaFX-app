package ru.maxsoft;

import java.util.HashMap;
import java.util.Map;

public class PolybiusSquare implements Cipherable {

    private Map<Character, String> square;

    private char[][] alphabet = {
                    {'А', 'Б', 'В', 'Г', 'Д', 'Е'},
                    {'Ё', 'Ж', 'З', 'И', 'Й', 'К'},
                    {'Л', 'М', 'Н', 'О', 'П', 'Р'},
                    {'С', 'Т', 'У', 'Ф', 'Х', 'Ц'},
                    {'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь'},
                    {'Э', 'Ю', 'Я', ' ', ',', '.'}};
//    private char[][] alphabet = {
//        {'A','B','C'},
//        {'D', 'E', 'I'},
//        {'F', 'J', 'K'}};

    private int length = alphabet.length * alphabet.length;

    public PolybiusSquare() {
        this.square = new HashMap<>(this.length);

        for (int i = 0; i < alphabet.length; i++) {
            for (int j = 0; j < alphabet.length; j++) {
                square.put(alphabet[i][j], "" + (i+1) + (j+1));
            }
        }
    }

    @Override
    public String encode(String str) {
        if (str.isEmpty()) {
            return null;
        }

        StringBuilder cipher = new StringBuilder(this.length * 2);

        str = str.toUpperCase();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            String num = square.get(c);
            if (num == null) {
                throw new IllegalArgumentException("Таких символов нет в алфавите");
            }
            cipher.append(num);
        }

        return cipher.toString();
    }

    @Override
    public String decode(String cipher) {
        if (cipher.isEmpty()) {
            return null;
        }
        if (cipher.length() % 2 == 1) {
            throw new IllegalArgumentException("Количество символов не четное");
        }

        StringBuilder str = new StringBuilder(cipher.length() / 2);

        for (int i = 0; i < cipher.length(); i = i + 2) {
            int j = i + 1;

            char c1 = cipher.charAt(i);
            char c2 = cipher.charAt(j);

            int firstNum = Character.getNumericValue(c1) - 1;
            int secondNum = Character.getNumericValue(c2) - 1;

            if (firstNum > alphabet.length - 1 || firstNum < 0 || secondNum > alphabet.length - 1 || secondNum < 0) {
                throw new IllegalArgumentException();
            }

            str.append(alphabet[firstNum][secondNum]);
        }

        return str.toString();
    }
}
