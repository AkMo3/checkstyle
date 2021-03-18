package com.puppycrawl.tools.checkstyle.checks.coding.illegalthrows;

/*
 * Config:
 * ignoreOverriddenMethods = false
 * Message = MSG_KEY
 *
 */
public class InputIllegalThrowsIgnoreOverriddenMethods
             extends InputIllegalThrows
{
    @Override
    public void methodTwo() throws RuntimeException {                 // violation at 13:36:

    }

    @java.lang.Override
    public java.lang.Throwable methodOne() throws RuntimeException {  // violation at 18:51:
        return null;
    }
}
