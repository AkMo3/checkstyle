////////////////////////////////////////////////////////////////////////////////
// Test case file for checkstyle.
// Created: 2001
////////////////////////////////////////////////////////////////////////////////
package com.puppycrawl.tools.checkstyle.checks.javadoc.javadocmethod;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;

/**
 * Config: default
 **/
public class InputJavadocMethodScopeAnonInner
{
    /**
       button.
    */
    private JButton mButton = new JButton(); // ok

    /**
       anon inner in member variable initialization.
    */
    private Runnable mRunnable = new Runnable() { // ok
        public void run() // should not have to be documented, class is anon.
        {
            System.identityHashCode("running");
        }
    };

    /**
       anon inner in constructor.
    */
    InputJavadocMethodScopeAnonInner() // ok
    {
        mButton.addMouseListener( new MouseAdapter()
            {
                public void mouseClicked( MouseEvent aEv )
                {
                    System.identityHashCode("click");
                }
            } );
    }

    /**
       anon inner in method
    */
    public void addInputAnonInner() // ok
    {
        mButton.addMouseListener( new MouseAdapter()
            {
                public void mouseClicked( MouseEvent aEv )
                {
                    System.identityHashCode("click");
                }
            } );
    }
}
