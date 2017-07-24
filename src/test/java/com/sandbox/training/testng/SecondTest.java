package com.sandbox.training.testng;

import org.testng.annotations.Test;

/**
 * Created by dchicu on 7/23/2017.
 */
public class SecondTest {

    @Test
    public void shouldTriggerTestViaTestNG() {
        assert  1 > 0;
        System.out.println("First TestNG assertion passed");
    }

    @Test
    public void shouldFailedTheTest() {
        assert  1 > 23;
        System.out.println("Second TestNG assertion failed");
    }
}
