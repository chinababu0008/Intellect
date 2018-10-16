package com.chinna.wordToPdf.Test1;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MoveFileExample
{
    public static void main(String[] args)
    {

    	InputStream inStream = null;
	OutputStream outStream = null;

    	try{

    	    File afile =new File("D:\\resume.doc");
    	    File bfile =new File("D:\\IBM\\resume.docx");

    	    inStream = new FileInputStream(afile);
    	    outStream = new FileOutputStream(bfile);

    	    byte[] buffer = new byte[1024];

    	    int length;
    	    //copy the file content in bytes
    	    while ((length = inStream.read(buffer)) > 0){

    	    	outStream.write(buffer, 0, length);

    	    }

    	    inStream.close();
    	    outStream.close();

    	    //delete the original file
    	   // afile.delete();

    	    System.out.println("File is copied successful!");

    	}catch(IOException e){
    	    e.printStackTrace();
    	}
    }
}
