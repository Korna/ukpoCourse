package ru.korna.ukpocourse;

import org.junit.Test;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Koma on 06.03.2018.
 */


public class ParserTestWhiteBox {
    @Test
    public void insertValueLong() throws Exception {
        Parser parser = new Parser();
        ArrayList<Integer> list = new ArrayList<>();

        Long firstValue = 2000000000L;


        parser.insertValue(list, firstValue.intValue());


        int autoFirst = list.get(0);


        assertEquals(2000000000, autoFirst);
    }

    @Test
    public void findIndexesNoLoop() throws Exception {
        Parser parser = new Parser();
        Pattern rule = parser.parseRule("12*45");
        ArrayList<Integer>list = parser.findIndexes("Im String 2 Parse.", rule);

        int size = list.size();
        assertEquals(size, 0);

    }
    @Test
    public void findIndexesSingleLoop() throws Exception {
        Parser parser = new Parser();
        Pattern rule = parser.parseRule("universe");
        ArrayList<Integer>list = parser.findIndexes("the universe is expanding.", rule);

        int size = list.size();
        assertEquals(size, 1);

        assertEquals(list.get(0).intValue(), 4);
    }


    @Test
    public void findIndexesMultipleLoop() throws Exception {
        Parser parser = new Parser();
        Pattern rule = parser.parseRule("3");
        ArrayList<Integer>list = parser.findIndexes("3,14159265358979323846", rule);

        int size = list.size();
        assertEquals(size, 4);

        assertEquals(list.get(0).intValue(), 0);

        assertEquals(list.get(1).intValue(), 10);

        assertEquals(list.get(2).intValue(), 16);

        assertEquals(list.get(3).intValue(), 18);
    }

    @Test
    public void parseRule() throws Exception {
        Pattern ruleAuto = Pattern.compile("1*2*x+y.");

        Parser parser = new Parser();
        Pattern rule = parser.parseRule("1*2*x+y.");

        assertEquals(rule.toString(), ruleAuto.toString());
    }

}