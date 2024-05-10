/*
 * @(#)SimpleDCF.java	1.2 99/12/06
 *
 * Copyright 1997-1999 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc.  
 * Use is subject to license terms.
 * 
 */

import javax.activation.*;
import java.util.StringTokenizer;
import java.util.Hashtable;

public class SimpleDCF implements DataContentHandlerFactory {
    Hashtable entry_hash = new Hashtable();
    /**
     * the constructor, takes a list of classes as an argument in the
     * form:
     * <mimetype>:<class name>\n
     *
     * For Example:
     *
     * application/x-wombat:com.womco.WombatDCH
     * text/plain:com.textco.TextDCH
     *
     */
    public SimpleDCF(String entries) {
	StringTokenizer tok = new StringTokenizer(entries);

	String entry;
	System.out.println("SimpleDCH: new SimpleDCF being created");

	// parse the string
	while(tok.hasMoreTokens()) {
	    int colon;

	    entry = tok.nextToken();
	    System.out.println("full entry = " + entry);

	    // parse out the fields
	    colon = entry.indexOf(':');
	    VectorEntry ve = new VectorEntry(entry.substring(0,colon),
					     entry.substring(colon + 1, 
							     entry.length()));
	    System.out.println("adding element = " + ve);
	    entry_hash.put(ve.getMimeType(),ve);
	}
    }

    /**
     * implement the factor interface
     */
    public DataContentHandler createDataContentHandler(String mimeType){
	DataContentHandler dch = null;

	System.out.print("SimpleDCF: trying to create a DCH");

	VectorEntry ve = (VectorEntry)entry_hash.get(mimeType);
	if(ve != null) {
	    System.out.print("...found token");
	    try { 
		
		dch = (DataContentHandler)Class.forName(
					ve.getClassName()).newInstance();
		if(dch == null)
		    System.out.println("...FAILED!!!");
		else
		    System.out.println("...SUCCESS!!!");

	    } catch(Exception e) {
		System.out.println(e);
	    }
	}
	return dch;
    }
}

class VectorEntry {
    private String mimeType;
    private String className;

    public VectorEntry(String mimeType, String className) {
	this.mimeType = mimeType;
	this.className = className;
    }
    
    public String getMimeType() { return mimeType; }
    public String getClassName() { return className; }
    public String toString() { 
	return new String("type: " + mimeType + " class name: " + className);
    }

}
