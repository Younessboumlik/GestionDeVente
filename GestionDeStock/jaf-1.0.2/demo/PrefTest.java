/*
 * @(#)PrefTest.java	1.4 02/03/10
 *
 * Copyright 1997-2002 Sun Microsystems, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of Sun Microsystems, Inc.
 * Use is subject to license terms.
 *
 */
import java.io.*;
import java.beans.*;
import javax.activation.*;

public class PrefTest {

    public static void main(String args[]) {
	MailcapCommandMap mcf = null;

	if (args.length > 0) {
	    try {
		mcf = new MailcapCommandMap(args[0]);
	    } catch (Exception e) {
		e.printStackTrace();
		System.exit(1);
	    }
	} else
	    mcf = new MailcapCommandMap();

	CommandInfo cmdinfo[] = mcf.getAllCommands("text/plain");
	
	if (cmdinfo != null) {
	    System.out.println("ALL Commands for text/plain:");
	    for (int i = 0; i < cmdinfo.length; i++) {
		System.out.println("Verb: " + cmdinfo[i].getCommandName() +
				  " Class: " + cmdinfo[i].getCommandClass());
	    }
	    System.out.println("done");
	} else {
	    System.out.println("no commands");
	}
	System.out.println();

	cmdinfo = mcf.getPreferredCommands("text/plain");
	if (cmdinfo != null) {
	    System.out.println("PREFERRED Commands for text/plain:");
	    for (int i = 0; i < cmdinfo.length; i++) {
		System.out.println("Verb: " + cmdinfo[i].getCommandName() +
				  " Class: " + cmdinfo[i].getCommandClass());
	    }
	    System.out.println("done");
	} else {
	    System.out.println("no commands");
	}
	System.out.println();
    }
}
