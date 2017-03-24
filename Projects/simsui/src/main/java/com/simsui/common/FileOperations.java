/**
 * @ (#) FileOperations.java
 * Project     : SIMS
 * File        : FileOperations.java
 * Author      : Ninganna.c
 * Company     : EVRY
 * Date Created: 23/Mar/2017
 *
 * ========================================================================================================================
 *  No | Modified date |      Modified by     |  Reason
 * ========================================================================================================================
 *  1.   
 * ========================================================================================================================
 */

package com.simsui.common;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
public class FileOperations {
	
	private FileOperations() {
		/* private constructor */
	}
	private static final Logger LOGGER = Logger.getLogger(FileOperations.class);
	
	public static String getServicePath(String endPointName) throws SimsException{
		LOGGER.info("In FileOperations : getServicePath");
		FileOperations fileOperations=new FileOperations();
		String serviceEndPoint = null;
		Boolean status=false;
	      try {	
	    	  	ClassLoader cl = fileOperations.getClass().getClassLoader();
	    	  	File inputFile = new File(cl.getResource("./service_end_point.xml").getFile());
	    	  	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	  	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	  	Document doc = dBuilder.parse(inputFile);
	    	  	doc.getDocumentElement().normalize();
	    	  	
	    	  	System.out.println("Root element :"   + doc.getDocumentElement().getNodeName());
	    	  	NodeList nList = doc.getElementsByTagName("service");
	    	  	for (int temp = 0; temp < nList.getLength(); temp++) {
	    	  		Node nNode = nList.item(temp);
	    	  		System.out.println("\nCurrent Element :"  + nNode.getNodeName());
	    	  		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	    	  			Element eElement = (Element) nNode;
	    	  			String serviceName=eElement.getAttribute("name");
	    	  			if(serviceName.equals(endPointName)){
	    	  				status=true;
	    	  				serviceEndPoint=eElement.getAttribute("endpoint");
	    	  			}
	    	  		}
	    	  	}
	      } catch (Exception e) {
	    	  e.printStackTrace();
	      }
	      if(status){
	  	  		return serviceEndPoint;
	  	  	}
	  	  	else{
	  	  		return "";
	  	  	}
   }
	
}
