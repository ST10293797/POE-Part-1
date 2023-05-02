/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.userauthentication2;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {
    
    
    public LoginTest ()
    {}
    //Step 1 - create an object from the class in question
    Login login = new Login(); 
    
    //check to see if the username entered is correct for the criteria that's outlined in the POE document
    @Test
    public void testCheckUserNameCorrectlyFormatted() {
        boolean actual = login.checkUserName("kyl_1");
        assertTrue(actual);
    }
    
    //check to see if the username entered is incorrect for the criteria that's outlined in the POE document
    @Test
    public void testCheckUserNamePoorlyFormatted() {
        boolean actual = login.checkUserName("kyle!!!!!");
        assertFalse( actual);     
    }
//check to see if the password entered meets the criteria that's outlined in the POE document
    @Test
    public void testCheckPasswordComplexitySuccess() {
        boolean actual = login.checkPasswordComplexity("Ch&&sec@ke9");
        assertTrue( actual);
    }
    
    //check to see if the password entered does not meet the criteria that's outlined in the POE document
    @Test
    public void testCheckPasswordComplexityFailure() {
        boolean actual = login.checkPasswordComplexity("password");
        assertFalse( actual);
    }
}
