package ru.korna.ukpocourse;

import java.util.regex.Pattern;

/**
 * Created by Koma on 06.03.2018.
 */

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        for(int i = 0; i < 100000; ++i) {
            Parser parser = new Parser();
            Pattern pattern = parser.parseRule("10");
            String line = "011010";
            parser.findIndexes(line, pattern);
        }

    }
}
