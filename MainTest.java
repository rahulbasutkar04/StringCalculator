package org.example;

import org.junit.jupiter.api.Test;

import static org.example.Main.customOperator;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {


    @Test
    void checkandReturntrueIfStrignisEmpty() {

        //Arrange
        Main m=new Main();
        //Act
        boolean result=m.empty("");
        //Assert
        assertEquals(true,result);

    }

    @Test
    void shouldCheckandReturnIfCommas()
    {
        //Arrange
        Main m=new Main();

        //Act
        int result=m.commasPresent("2,3");

        //Assert

        assertEquals(5,result);
    }


    @Test
    void shouldReturnSumifCommaswithdoubleValuesPresent()
    {
        //Arrange
        Main m=new Main();
        //Act
        double result=m.commasPresentWithDouble("1.1,2.2");
        //Assert

        double tolerance = 0.0001;
        assertEquals(3.3, result, tolerance);
    }

    @Test
    void shouldReturnSumifnewLineAndCommaPresent()
    {
        Main m=new Main();

            int result = m.newlineAndcommasPresent("12\n,3");
            assertEquals(6, result);

    }

    @Test
    void shouldreturnEOFError(){
        Main m=new Main();
        assertThrows(IllegalArgumentException.class, () -> {
            m.missingLastNumber("1,2,");
        }, "Number expected but EOF found.");
    }



    @Test
    void shouldreturnNegativeException()
    {
        Main m = new Main();

        // Testing the case where negative numbers are present
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            m.NegativeNumber("-1,2");
        });

        // Verifying the error message
        assertEquals("Negative not allowed: -1", exception.getMessage());

    }

    @Test
    void shouldReturnErrorMessages() {
        Main m = new Main();

        String input1 = "-1,,2";
        String expectedErrorMessage1 = "Negative not allowed: -1\nNumber expected but ',' found at position 2.\n";
        assertEquals(expectedErrorMessage1, m.isMultiError(input1));

        String input2 = "-1,,-2";
        String expectedErrorMessage2 = "Negative not allowed: -1\nNumber expected but ',' found at position 2.\nNegative not allowed: -2\n";
        assertEquals(expectedErrorMessage2, m.isMultiError(input2));
    }

    @Test
    void shouldReturnNullIfNoErrors() {
        Main m=new Main();

        String input = "1,2,3";
        assertEquals(null, m.isMultiError(input));
    }

    @Test
    void shouldHandleCustomSeparator() {

        String input1 = "//;\n1;2";
        assertTrue(customOperator(input1), "Expected customOperator('" + input1 + "') to return true");


        String input2 = "//|\n1|2|3";
        assertTrue(customOperator(input2), "Expected customOperator('" + input2 + "') to return true");

        String input3 = "//sep\n2sep3";
        assertTrue(customOperator(input3), "Expected customOperator('" + input3 + "') to return true");

        String input4 = "//|\n1|2,3";
        assertFalse(customOperator(input4), "Expected customOperator('" + input4 + "') to return false");
    }


}