/*
 * @(#)TextInternalizer.java	1.2 99/12/06
 *
 * Copyright 1997-1999 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc.  
 * Use is subject to license terms.
 * 
 */

import java.awt.*;
import java.io.*;
import java.beans.*;
import javax.activation.*;

public class TextInternalizer extends Panel implements Externalizable {
    // UI Vars...
    private TextArea text_area = null;
    
    // File Vars
    private File text_file = null;
    private String text_buffer = null;
    
    private DataHandler _dh = null;
    private boolean DEBUG = false;
    /**
     * Constructor
     */
    public TextInternalizer() {
	System.out.println("TextInternalizer!!!!!");

	setLayout( new GridLayout(1,1));
	// create the text area
	text_area = new TextArea("", 24, 80, 
				 TextArea.SCROLLBARS_VERTICAL_ONLY );
	text_area.setEditable( false );
	
	add(text_area);
    }
    
    public void writeExternal(ObjectOutput out) throws IOException{

    }

    public void readExternal(ObjectInput in) throws IOException, 
	ClassNotFoundException {

	
	this.setObjectInput(in);
    }



    //--------------------------------------------------------------------
//     public void setCommandContext(String verb, DataHandler dh) throws IOException {
// 	_dh = dh;
// 	this.setInputStream( _dh.getInputStream() );
//     }
  //--------------------------------------------------------------------

  /**
   * set the data stream, component to assume it is ready to
   * be read.
   */
  public void setObjectInput(ObjectInput ins) throws IOException {
      try {

      text_buffer = (String)ins.readObject();
      } catch(Exception e){ e.printStackTrace(); }
      // place in the text area
      text_area.setText(text_buffer);

    }
  //--------------------------------------------------------------------
    public void addNotify() {
	super.addNotify();
	invalidate();
    }
  //--------------------------------------------------------------------
    public Dimension getPreferredSize()	{
	return text_area.getMinimumSize(24, 80);
    }

}






