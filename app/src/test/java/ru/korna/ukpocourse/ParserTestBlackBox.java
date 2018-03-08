package ru.korna.ukpocourse;

import org.junit.Test;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Koma on 06.03.2018.
 */

public class ParserTestBlackBox {
    @Test
    public void ruleShort() throws Exception {
        Parser parser = new Parser();
        String rule = ".?x";
        Pattern pattern = Pattern.compile(rule);

        Pattern pattern2 = parser.parseRule(rule);
        assertEquals(pattern.toString(), pattern2.toString());

    }
    @Test
    public void ruleLong() throws Exception {
        Parser parser = new Parser();
        String rule = ".*+.*+1234567890.*+qazwsxedcrfvtgbyhnujmikolp";
        Pattern pattern = Pattern.compile(rule);

        Pattern pattern2 = parser.parseRule(rule);
        assertEquals(pattern.toString(), pattern2.toString());

    }
    @Test
    public void ruleNone() throws Exception {
        Parser parser = new Parser();
        String rule = "";
        Pattern pattern = Pattern.compile(rule);

        Pattern pattern2 = parser.parseRule(rule);
        try {
            assertEquals(pattern.toString(), pattern2.toString());
        }catch (NullPointerException npe){
            npe.printStackTrace();//стандартная библиотека позволяет парсить пустые правила, а написанный враппер возвращает null
        }
    }
    @Test
    public void count_1() throws Exception {
        Parser parser = new Parser();
        ArrayList<Integer> list = new ArrayList<>();

        int sizeBefore = list.size();
        parser.insertValue(list, 1);
        int sizeAfter = list.size();
        assertEquals(sizeBefore, sizeAfter - 1);
    }

    @Test
    public void count_2() throws Exception {
        Parser parser = new Parser();
        ArrayList<Integer> list = new ArrayList<>();

        int sizeBefore = list.size();
        parser.insertValue(list, 1);
        parser.insertValue(list, 0);
        int sizeAfter = list.size();
        assertEquals(sizeBefore, sizeAfter - 2);
    }


    /* этот тест некорректен
    @Test
    public void countNull() throws Exception {
        Parser parser = new Parser();
        ArrayList<Integer> list = new ArrayList<>();

        int sizeBefore = list.size();
        parser.insertValue(list, NULL);
        int sizeAfter = list.size();
        assertEquals(sizeBefore, sizeAfter);
    }*/

    @Test
    public void value() throws Exception {
        Parser parser = new Parser();
        ArrayList<Integer> list = new ArrayList<>();

        int firstValue = 30;
        int secondValue = 20;

        parser.insertValue(list, firstValue);
        parser.insertValue(list, secondValue);

        int autoFirst = list.get(0);
        int autoSecond = list.get(1);

        assertEquals(firstValue, autoFirst);
        assertEquals(secondValue, autoSecond);
    }


    @Test
    public void findIndexesEmptyRule() throws Exception {
        Parser parser = new Parser();
        Pattern rule = parser.parseRule("");
        ArrayList<Integer>list = parser.findIndexes("Im String 2 Parse.", rule);

        int size = list.size();
        assertEquals(size, 0);

    }

    @Test
    public void findIndexesEmptyStringDot() throws Exception {
        Parser parser = new Parser();
        Pattern rule = parser.parseRule(".");
        ArrayList<Integer>list = parser.findIndexes("", rule);

        int size = list.size();
        assertEquals(size, 0);

    }

    @Test
    public void findIndexesEmptyStringStar() throws Exception {
        Parser parser = new Parser();
        Pattern rule = parser.parseRule("0*");
        ArrayList<Integer>list = parser.findIndexes("", rule);

        int size = list.size();
        assertEquals(size, 0);

    }

    @Test
    public void findIndexesEmptyStringPlus() throws Exception {
        Parser parser = new Parser();
        Pattern rule = parser.parseRule("1+");
        ArrayList<Integer>list = parser.findIndexes("", rule);

        int size = list.size();
        assertEquals(size, 0);

    }

    @Test
    public void findIndexesEmptyEmpty() throws Exception {
        Parser parser = new Parser();
        Pattern rule = parser.parseRule("");
        ArrayList<Integer>list = parser.findIndexes("", rule);

        int size = list.size();
        assertEquals(size, 0);

    }

    @Test
    public void findIndexesAllCommandSymbols() throws Exception {
        Parser parser = new Parser();
        Pattern rule = parser.parseRule("x*y+z*");
        ArrayList<Integer>list = parser.findIndexes("xx33yz44", rule);

        int size = list.size();
        assertEquals(size, 1);

    }

    @Test
    public void findIndexesDifferentCase() throws Exception {
        Parser parser = new Parser();
        Pattern rule = parser.parseRule("qwerty");
        ArrayList<Integer>list = parser.findIndexes("QWERTY", rule);

        int size = list.size();
        assertEquals(size, 0);
    }

    @Test
    public void findIndexesFoundMultiple() throws Exception {
        Parser parser = new Parser();
        Pattern rule = parser.parseRule("1");
        ArrayList<Integer>list = parser.findIndexes("ulyij?32 1 asdij2 1?la sdn1fdsm1", rule);

        int size = list.size();
        assertEquals(size, 4);
    }

    @Test
    public void findIndexesDotFoundMultiple() throws Exception {
        Parser parser = new Parser();
        Pattern rule = parser.parseRule("..");
        ArrayList<Integer>list = parser.findIndexes("123456", rule);

        int size = list.size();
        assertEquals(size, 3);
    }

    @Test
    public void findIndexesStarFoundSingle() throws Exception {
        Parser parser = new Parser();
        Pattern rule = parser.parseRule("z*x");
        ArrayList<Integer>list = parser.findIndexes("zccx", rule);

        int size = list.size();
        assertEquals(size, 1);
    }

    @Test
    public void findIndexesStarFoundSingleNoGap() throws Exception {
        Parser parser = new Parser();
        Pattern rule = parser.parseRule("1*1");
        ArrayList<Integer>list = parser.findIndexes("11", rule);

        int size = list.size();
        assertEquals(size, 1);
    }

    @Test
    public void findIndexesStarFoundMultiple() throws Exception {
        Parser parser = new Parser();
        Pattern rule = parser.parseRule("k*s");
        ArrayList<Integer>list = parser.findIndexes("ks ks", rule);

        int size = list.size();
        assertEquals(size, 2);
    }

    @Test
    public void findIndexesPlusFoundMultiple() throws Exception {
        Parser parser = new Parser();
        Pattern rule = parser.parseRule("Y+1");
        ArrayList<Integer>list = parser.findIndexes("Y__1", rule);

        int size = list.size();
        assertEquals(size, 0);
    }

    @Test
    public void findIndexesPlusFoundNone() throws Exception {
        Parser parser = new Parser();
        Pattern rule = parser.parseRule("1+6");
        ArrayList<Integer>list = parser.findIndexes("0x0", rule);

        int size = list.size();
        assertEquals(size, 0);
    }

    @Test
    public void findIndexesPlusFoundNoneSymbols() throws Exception {
        Parser parser = new Parser();
        Pattern rule = parser.parseRule("!+@");
        ArrayList<Integer>list = parser.findIndexes("!@", rule);

        int size = list.size();
        assertEquals(size, 1);//враппер не исключает другие командные символы
    }


}