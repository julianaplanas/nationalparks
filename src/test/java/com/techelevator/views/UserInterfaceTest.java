package com.techelevator.views;

import com.techelevator.models.dto.Park;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class UserInterfaceTest
{
    InputStream inStream;
    private final PrintStream originalOut = System.out;

    @Before
    public void setup() {
        inStream = null;
    }

    @After
    public void tearDown()
    {
        //set the UserInterface streams back to default
        UserInterface.setIn(System.in);
        System.setOut(originalOut);
    }

    //@Test
    ////public void getHomeScreenSelection_shouldReturn_list_withValidSelection()
    ////{
    ////    // arrange
    ////    String input = "1\n";
    ////    inStream = new ByteArrayInputStream(input.getBytes());
//    //    UserInterface.setIn(inStream);
////
    ////    String expected = "list";
//    //    String message = "Because 'list' should be returned when the user selects option 1.";
////
    ////    // act
//    //    //String actual = UserInterface.getHomeScreenSelection();
////
    ////    // assert
//    //    //assertEquals(message, expected, actual);
////
    //}

    //@Test
    //public void getHomeScreenSelection_shouldReturn_invalid_withInvalidSelection()
    //{
    //    // arrange
    //    String input = "wrong\n";
    //    inStream = new ByteArrayInputStream(input.getBytes());
    //    UserInterface.setIn(inStream);
//
    //    String expected = "invalid";
    //    String message = "Because 'invlid' should be returned when the user enters an unrecognized option.";
//
    //    // act
    //    String actual = UserInterface.getHomeScreenSelection();
//
    //    // assert
    //    assertEquals(message, expected, actual);
//
    //}

    //@Test
    //public void test()
    //{
    //    // arrange
    //    ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
    //    PrintStream outStream = new PrintStream(outBytes);
    //    System.setOut(outStream);
//
    //    String expected = "Acadia\r\n" +
    //            "--------------------\r\n" +
    //            "Area: 47389\r\n";
//
    //    Park park = new Park(1,"Acadia","Maine", LocalDate.parse("1919-02-26"),47389, 2563129,"Covering most of Mount Desert Island and other coastal islands, Acadia features the tallest mountain on the Atlantic coast of the United States, granite peaks, ocean shoreline, woodlands, and lakes. There are freshwater, estuary, forest, and intertidal habitats.");
//
//
    //    // act
    //    UserInterface.displayParkDetail(park);
    //    outStream.flush();
//
    //    // assert
    //    String actual = outBytes.toString();//.replace("\r", "");
    //    assertEquals("Because the acadia park details should have displayed correctly.", expected, actual);
//
    //}

}
