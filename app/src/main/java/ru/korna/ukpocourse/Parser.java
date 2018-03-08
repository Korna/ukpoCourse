package ru.korna.ukpocourse;

/**
 * Created by Koma on 06.03.2018.
 */
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public Parser() {
    }

    public ArrayList<Integer> insertValue(ArrayList<Integer> list, int value) {
        list.add(value);
        return list;
    }

    public ArrayList<Integer> findIndexes(String line, Pattern rule) {
        ArrayList<Integer> list = new ArrayList<>();

        if(line.equals("") || rule == null)
            return list;

        Matcher matcher = rule.matcher(line);

        while(matcher.find()) {
            int start = matcher.start();
            this.insertValue(list, start);
        }

        return list;
    }

    public Pattern parseRule(String line) {
        if(!line.equals(""))
            return Pattern.compile(line);
        else
            return null;
    }
}