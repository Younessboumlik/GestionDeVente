/*
 * @(#)DHURL.java	1.3 99/12/06
 *
 * Copyright 1997-1999 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc.  
 * Use is subject to license terms.
 * 
 */

import java.net.*;
import java.io.*;
import javax.activation.*;

public class DHURL {
    URL url = null;
    DataHandler dh = null;
    
    public static void main(String args[]){
        DHURL test = new DHURL();

        if(args.length == 0) {
            System.out.println("usage: DHURL url");
            System.exit(1);
        }

        test.setURL(args[0]);

        test.doit();

    }

    public void setURL(String url) {
	
	try {
	    this.url = new URL(url);
	} catch(MalformedURLException e) {
	    e.printStackTrace();
	    System.out.println("malformed URL!!!");
	    System.exit(1);
	}

    }
    
    public void doit() {
	System.out.print("Creating DataHandler...");
	dh = new DataHandler(url);
	System.out.println("...done.");

	System.out.println("The MimeType of the DH : " +
			   dh.getContentType());
	try {
	InputStream is = dh.getInputStream();
	if(is != null)
	    System.out.println("got an inputstream");
	} catch(Exception e) {
	    e.printStackTrace();
	}

	
    }
	
    
}
