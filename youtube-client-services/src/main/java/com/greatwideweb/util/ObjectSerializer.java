package com.greatwideweb.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectSerializer {

	public ObjectSerializer(String fullyPathedFile, Object o) throws IOException {
		FileOutputStream fos =null;
		ObjectOutputStream oos = null;
		try{
	         fos= new FileOutputStream(fullyPathedFile);
	         oos= new ObjectOutputStream(fos);
	         oos.writeObject(o);
	     } finally {
			try {
				if(oos != null) { oos.close(); }
				if(fos != null) { fos.close(); }
			} catch (IOException e) { e.printStackTrace(); }
		}
	}

}
