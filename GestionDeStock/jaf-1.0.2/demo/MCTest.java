/*
 * @(#)MCTest.java	1.6 99/12/06
 *
 * Copyright 1997-1999 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc.  
 * Use is subject to license terms.
 * 
 */
import java.io.*;
import java.beans.*;
import com.sun.activation.registries.*;
import javax.activation.*;

public class MCTest {
    MailcapCommandMap mcf = null;
    String file_name = null;

    public static void main(String args[]){
	MCTest test = new MCTest();
	
	if(args.length == 0) {
            System.out.println("usage: MCTest mailcap");
            System.exit(1);
        }

	test.setFile(args[0]);

	test.doit();

    }

    private void setFile(String fname) {
       file_name = fname;
    }

    private void doit() {

	try {
	    mcf = new MailcapCommandMap(file_name);
	} catch (Exception e){ 
	    e.printStackTrace();
	    System.exit(1);
	}

	CommandInfo cmdinfo[] = mcf.getAllCommands("text/plain");
        System.out.print("Are there any commands for text/plain?");
	
	if(cmdinfo != null) {
	    System.out.println("number of cmds = " + cmdinfo.length);
	    System.out.println("now try an individual cmd");
	    CommandInfo info = mcf.getCommand("text/plain", "view");
	    if(info != null) {
		System.out.println("Got command...");
	    }
	    else
		System.out.println("no cmds");
	    //	    System.out.print("Add another command");
	    
	    mcf.addMailcap("text/plain;; x-java-flobotz=com.sun.activation.flobotz\n");	
	    //	    System.out.println("...dome");
	    if(cmdinfo != null) {
		cmdinfo = mcf.getAllCommands("text/plain");
		System.out.println("now we have cmds = " + cmdinfo.length);
		
	    }	

        } else {
	    System.out.println("NO CMDS AT ALL!");
	}
	
    }
    
}
