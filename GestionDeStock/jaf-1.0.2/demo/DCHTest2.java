/*
 * @(#)DCHTest2.java	1.5 99/12/06
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

public class DCHTest2 {
    private FileDataSource fds = null;
    private MailcapCommandMap cmdMap = null;
    private DataHandler dh = null;
    private DataContentHandlerFactory dchf = null;
    /**
     * main function
     */
    public static void main(String args[]) {
	DCHTest2 test = new DCHTest2();
	
	if(args.length < 2) {
	    System.out.println("usage: DCHTest2 file.txt mailcap");
	    System.exit(1);
	}
	
	// first let's get a DataSource

	test.setFile(args[0]);
	
	test.setMailcap(args[1]);
	test.doit();
    }

    private void doit() {
	DataFlavor xfer_flavors[];
	Object content = null;

	// now let's create a DataHandler
	dh = new DataHandler(fds);
        dh.setCommandMap(cmdMap);
	System.out.println("DCHTest2: DataHandler created");

	// get the dataflavors
	xfer_flavors = dh.getTransferDataFlavors();
	System.out.println("DCHTest2: dh.getTransferDF returned " +
			   xfer_flavors.length + " data flavors.");

	// get the content:
	try {
	    content = dh.getContent();
        } catch (Exception e) { e.printStackTrace(); }
	if(content == null)
	    System.out.println("DCHTest2: no content to be had!!!");
	else
	    System.out.println("DCHTest2: got content of the following type: " +
			       content.getClass().getName());
	
    }

    /**
     * set the file
     */
    public void setFile(String filename) {
	fds = new FileDataSource(filename);
	System.out.println("DCHTest2: FileDataSource created");
	if(!fds.getContentType().equals("text/plain")) {
	    System.out.println("Type must be text/plain");
	    System.exit(1);
	}
    }

   /**
    * set the mailcap file in the CMD Map
    */
   public void setMailcap(String mailcap) {

       try {
	   cmdMap = new MailcapCommandMap(mailcap);
       } catch(Exception e){
	   e.printStackTrace();
	   System.exit(1);
       }
   }

	    
}


