package com.chinna.wordToPdf.Test1;

import java.io.File;

import ooo.connector.BootstrapSocketConnector;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.uno.XComponentContext;


	public class JodConvertot  {
		
		public static XComponentContext mxRemoteContext = null;
		public static XMultiComponentFactory mxRemoteServiceManager = null;
		public static String oooExeFolder = "D:/OpenOffice.org 3/program/";
		public static String outputFilePath = "D:/OpenOffice.org 3/program/";
		
		public static void main(String[] args) 		{
			String METHOD_NAME = "convertToPdfFile";
			String inputFileNameWithPath, outputFileName;
	        try {
	        	inputFileNameWithPath = "inputFileNameWithPath";
	        	outputFileName = "outputFileName";
	        	
	            File inputFile = new File(inputFileNameWithPath);
	            File outputFile = new File(outputFilePath+outputFileName);
	             
	            if (mxRemoteContext == null && mxRemoteServiceManager == null) {
	      	         // get the remote office context. If necessary a new office
	      	         // process is started
	      	         mxRemoteContext = BootstrapSocketConnector.bootstrap(oooExeFolder);
	      	         mxRemoteServiceManager = mxRemoteContext.getServiceManager();
	      	          
	      	         String available = (mxRemoteServiceManager != null ? "available" : "not available");
	      	     }

	             //connect to an OpenOffice.org instance running on port 8100
	             OpenOfficeConnection connection = new SocketOpenOfficeConnection();
	             connection.connect();

	             //convert
	             DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
	             converter.convert(inputFile, outputFile);

	             //close the connection
	             connection.disconnect();

	               
	         } catch (Exception e) {  
	             e.printStackTrace();
	         }
	}


}
