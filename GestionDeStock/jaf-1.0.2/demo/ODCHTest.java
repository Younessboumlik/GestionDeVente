/*
 * @(#)ODCHTest.java	1.2 99/12/06
 *
 * Copyright 1997-1999 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc.  
 * Use is subject to license terms.
 * 
 */

import java.io.*;
import javax.activation.*;
import java.awt.datatransfer.*;

public class ODCHTest {
    private FileDataSource fds = null;
    private DataHandler dh = null;
    private DataContentHandlerFactory dchf = null;
    private String str;
    /**
     * main function
     */
    public static void main(String args[]) {
	ODCHTest test = new ODCHTest();
	
	if(args.length != 0) {
	    System.out.println("usage: ODCHTest");
	    System.exit(1);
	}
	
	// first let's get a DataSource

	
	test.doit();
    }

    private void doit() {
	DataFlavor xfer_flavors[];
	Object content = null;

	str = new String("This is a test");

	// now let's create a DataHandler
	dh = new DataHandler(str, "text/plain");
	System.out.println("ODCHTest: DataHandler created with str & text/plain");

	// now lets set a DataContentHandlerFactory
	dchf = new SimpleDCF("text/plain:PlainDCH\n");
	System.out.println("ODCHTest: Simple dchf created");
	
	// now let's set the dchf in the dh
	dh.setDataContentHandlerFactory(dchf);
	System.out.println("ODCHTest: DataContentHandlerFactory set in DataHandler");
	
	// get the dataflavors
	xfer_flavors = dh.getTransferDataFlavors();
	System.out.println("ODCHTest: dh.getTransferDF returned " +
			   xfer_flavors.length + " data flavors.");

	// get the content:
        try {
	   content = dh.getContent();
        } catch (Exception e) { e.printStackTrace(); }

	if(content == null)
	    System.out.println("ODCHTest: no content to be had!!!");
	else {
	    System.out.println("ODCHTest: got content of the following type: " +
			       content.getClass().getName());
	    if(content == str)
		System.out.println("get content works");
	    
	}
    }
	    
}
