package com.nrifintech.lms.util;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font;

public class PdfGenerateReport {
	public static void pdfGen(List<String[]> a) throws Exception, DocumentException
	{
		int rowCount = 0;
		
		Document document = new Document();
		PdfPTable table= null;
	    try
	    {
	        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D://demo.pdf"));
	        document.open();
	        int cols = ((a.size() == 0) ? 0 : a.get(0).length);
	        
	        table= new PdfPTable(cols); // 9 columns
	        table.setWidthPercentage(100); //Width 100%
	        table.setSpacingBefore(10f); //Space before table
	        table.setSpacingAfter(10f); //Space after table
	        
	      //Set Column widths
	        float[] columnWidths = new float[cols];
	        for (int i = 0; i < cols; ++i) {
	        	columnWidths[i] = 1f;
	        }
	        table.setWidths(columnWidths);
	 
	        for (String[] aBook : a ) 
	        { 
		        for (String field : aBook) {
	        		table.addCell(new PdfPCell(new Phrase(String.valueOf(field))));
	        	}
	        }
	        document.add(table);
	        document.close();
	        writer.close();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    finally
	    {
	    	if(document!=null)
	    	{
	    		document.close();
	    	}
	    }
	}
	
	
	//Passing Header as different string array
	public static void pdfGen(String[] headers, List<String[]> a) throws Exception, DocumentException
	{
		//int rowCount = 0;
		
		Document document = new Document();
//		font = new Font(FontFamily.HELVETICA, 10);
		PdfPTable table= null;
	    try
	    {
	    	String fileLocation = new File("src\\main\\resources\\static\\documents\\").getAbsolutePath() + "\\" + "demo.pdf";
	        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileLocation));
	        document.open();
	        
//	        int rows = a.size();
	        int cols = ((a.size() == 0) ? 0 : a.get(0).length);
	        
	        table= new PdfPTable(cols); // 9 columns
	        table.setWidthPercentage(100); //Width 100%
	        table.setSpacingBefore(10f); //Space before table
	        table.setSpacingAfter(10f); //Space after table
	        
	      //Set Column widths
	        float[] columnWidths = new float[cols];
	        for (int i = 0; i < cols; ++i) {
	        	columnWidths[i] = 1f;
	        }
	        table.setWidths(columnWidths);
	        
	        for (String header : headers) {
        		table.addCell(new PdfPCell(new Phrase(String.valueOf(header))));
        	}
	        
	        for (String[] aBook : a ) 
	        { 
		        for (String field : aBook) {
	        		table.addCell(new PdfPCell(new Phrase(String.valueOf(field))));
	        	}
	        }
	        document.add(table);
	        document.close();
	        writer.close();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    finally
	    {
	    	if(document!=null)
	    	{
	    		document.close();
	    	}
	    }
	}
}
