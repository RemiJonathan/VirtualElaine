package com.remijonathan.virtualelaine;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void convertingDateIsGood(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DD/MM/YYYY HH24:MI", Locale.CANADA);
        try {
            System.out.println(simpleDateFormat.parse("1/01/1970 00:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}