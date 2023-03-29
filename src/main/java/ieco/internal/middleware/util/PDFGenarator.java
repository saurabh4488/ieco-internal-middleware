package ieco.internal.middleware.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
@RestController

public class PDFGenarator   {

	/** Initial size for the temporary output byte array (if any). */
	private static final int OUTPUT_BYTE_ARRAY_INITIAL_SIZE = 4096;
	

	//@Override
	@PostMapping("/genPDF")
	public String renderMergedOutputModel(@RequestBody Map<String, String> model
	) throws DocumentException, IOException {
	ByteArrayOutputStream baos = createTemporaryOutputStream();

	        // Apply preferences and build metadata.
	        Document document = new Document(PageSize.A4,36,36,36,50);
	        PdfWriter writer = PdfWriter.getInstance(document, baos); 
	        prepareWriter(writer);
	        buildPdfMetadata(model, document);
	       
	        PdfPageEvent footer_event = new PdfPageEvent();
	    writer.setPageEvent(footer_event);
	        // Build PDF document.
	        document.open();
	        buildPdfDocument(model, document);
	       // document.
	        document.close();
	 
	        byte[] pdfBytes = baos.toByteArray();

	       return Base64.getEncoder().encodeToString(pdfBytes);
	}

	//@Override
	protected void buildPdfDocument(Map<String, String> model, Document doc
	) throws DocumentException, IOException {

	Font font_blue = FontFactory.getFont(FontFactory.TIMES_ROMAN, 13.0f, Font.BOLD);
	       font_blue.setColor(BaseColor.BLUE);
	       
	      
	       
	       Font font_black = FontFactory.getFont(FontFactory.TIMES_ROMAN);
	       font_black.setColor(BaseColor.BLACK);
	       
	       Font font_black_bold = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11.0f,Font.BOLD);
	       font_black_bold.setColor(BaseColor.BLACK);
	       
	       Font font_white = FontFactory.getFont(FontFactory.TIMES_ROMAN);
	       font_white.setColor(BaseColor.WHITE);
	       
	       Font fontcurious_blue = FontFactory.getFont(FontFactory.TIMES_ROMAN);
	       fontcurious_blue.setColor(PciColors.CURIOUS_BLUE);
	       
	       Font fontcurious_blue_bold = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11.0f, Font.BOLD);
	       fontcurious_blue_bold.setColor(PciColors.CURIOUS_BLUE);

	            PdfPTable table_courses = new PdfPTable(2);
	            table_courses.setWidths(new int[]{1, 2});
	           
	            Image image = Image.getInstance(Base64.getDecoder().decode("/9j/4QAYRXhpZgAASUkqAAgAAAAAAAAAAAAAAP/sABFEdWNreQABAAQAAABQAAD/4QNzaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLwA8P3hwYWNrZXQgYmVnaW49Iu+7vyIgaWQ9Ilc1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCI/PiA8eDp4bXBtZXRhIHhtbG5zOng9ImFkb2JlOm5zOm1ldGEvIiB4OnhtcHRrPSJBZG9iZSBYTVAgQ29yZSA1LjYtYzA2NyA3OS4xNTc3NDcsIDIwMTUvMDMvMzAtMjM6NDA6NDIgICAgICAgICI+IDxyZGY6UkRGIHhtbG5zOnJkZj0iaHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyI+IDxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PSIiIHhtbG5zOnhtcE1NPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvbW0vIiB4bWxuczpzdFJlZj0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL3NUeXBlL1Jlc291cmNlUmVmIyIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bXBNTTpPcmlnaW5hbERvY3VtZW50SUQ9InhtcC5kaWQ6NUU4NUVEREIwQ0Q0RTYxMUI0MDlFNzkxNjIzRDExRTUiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6MzVFOTA3Rjc1OUZBMTFFOUE1NThGRUVFOEI1MzcwQTIiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6MzVFOTA3RjY1OUZBMTFFOUE1NThGRUVFOEI1MzcwQTIiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTUgKFdpbmRvd3MpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6NEYwRThFRkIzMDMyMTFFOUE3RjFDRjZDNzhBNEFBNkMiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6NEYwRThFRkMzMDMyMTFFOUE3RjFDRjZDNzhBNEFBNkMiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4g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"));
	            image.setAlignment(Image.MIDDLE);
	            image.scaleToFit(20, 20);
	            table_courses.addCell(image);
	            table_courses.addCell(getCell1("OFFLINE XML BASED KYC DATA FORM", PdfPCell.ALIGN_CENTER, font_black_bold));
	           
	            
	            Phrase firstLine = new Phrase("This is the document generated by ", font_black );
	            firstLine.add(new Chunk("Kotak Investment Advisors Limited ", font_black_bold ));
	            firstLine.add(new Chunk("with resident's consent and on the basis of offline XML based KYC data shared by the resident with Kotak Investment Advisors Limited for the purpose of opening Investment Account. This document will be used as ID and/or Address proof for record purposes.", font_black ));
	            
	            PdfPCell c1 = new PdfPCell(firstLine);
	           
	            c1.setBorderColor(PciColors.CURIOUS_BLUE);
	            c1.setColspan(3);
	            table_courses.addCell(c1);
	           
	            table_courses.addCell(getCell1("Reference Id:", PdfPCell.ALIGN_LEFT, font_black));
	            table_courses.addCell(getCell1(model.get("Reference Id:"), PdfPCell.ALIGN_LEFT, font_black));
	            
	            table_courses.addCell(getImgCell("Photo", PdfPCell.ALIGN_LEFT, font_black));
	            Image custImage = Image.getInstance(Base64.getDecoder().decode(model.get("Photo")));
	            custImage.setAlignment(Image.LEFT);
	            custImage.scaleToFit(80, 80);
	            

	            PdfPCell cell = new PdfPCell();

	            cell.setFixedHeight(100f);
	           

	            cell.addElement(custImage);

	            table_courses.addCell(cell);

	            table_courses.addCell(getCell1("Aadhaar Number", PdfPCell.ALIGN_LEFT, font_black));
	            table_courses.addCell(getCell1(model.get("Aadhaar Number"), PdfPCell.ALIGN_LEFT, font_black));
	           
	            table_courses.addCell(getCell1("Name", PdfPCell.ALIGN_LEFT, font_black));
	            table_courses.addCell(getCell1(model.get("Name"), PdfPCell.ALIGN_LEFT, font_black));
	            
	            table_courses.addCell(getCell1("DATE OF BIRTH", PdfPCell.ALIGN_LEFT, font_black));
	            table_courses.addCell(getCell1(model.get("DATE OF BIRTH"), PdfPCell.ALIGN_LEFT, font_black));
	           
	            table_courses.addCell(getCell1("Gender", PdfPCell.ALIGN_LEFT, font_black));
	            table_courses.addCell(getCell1(model.get("GENDER"), PdfPCell.ALIGN_LEFT, font_black));
	           
	            table_courses.addCell(getCell1("Address", PdfPCell.ALIGN_LEFT, font_black));
	            table_courses.addCell(getCell1(model.get("ADDRESS"), PdfPCell.ALIGN_LEFT, font_black));
	           
	            table_courses.addCell(getCell1("Generated ON", PdfPCell.ALIGN_LEFT, font_black));
	            table_courses.addCell(getCell1(getDate(model.get("GENERATED ON").split("T")[0]), PdfPCell.ALIGN_LEFT, font_black));
	           
	            doc.add(table_courses);
	    }
String getDate(String input) {
	String destPattern = "MM/dd/yyyy";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(destPattern);

	DateFormat sourceformat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	try {
		Date d = sourceformat.parse(input);
		return simpleDateFormat.format(d);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
	public PdfPCell getCellHead(String text, int alignment, Font font_white) {
	   PdfPCell cell = new PdfPCell(new Phrase(text,font_white));
	   cell.setPaddingTop(1.0f);
	   cell.setBackgroundColor(PciColors.CURIOUS_BLUE);
	   cell.setHorizontalAlignment(alignment);
	   cell.setBorder(PdfPCell.BOX);
	   cell.setBorderColor(PciColors.CURIOUS_BLUE);
	   cell.setFixedHeight(20f);
	   return cell;
	}

	public PdfPCell getCell(String text, int alignment, Font font_black) {
	   PdfPCell cell = new PdfPCell(new Phrase(text,font_black));
	   cell.setPadding(0);
	   cell.setHorizontalAlignment(alignment);
	   cell.setBorder(PdfPCell.NO_BORDER);
	   cell.setBorderColor(BaseColor.WHITE);
	   cell.setFixedHeight(20f);
	   return cell;
	}

	public PdfPCell getCellAddr(String value, int alignment, Font fontcurious_blue) {
	PdfPCell cell = new PdfPCell(new Phrase(value,fontcurious_blue));
	cell.setPadding(0);
	cell.setHorizontalAlignment(alignment);
	cell.setBorder(PdfPCell.NO_BORDER);
	cell.setBorderColor(BaseColor.WHITE);
	cell.setFixedHeight(20f);
	return cell;
	}

	public PdfPCell getCell1(String text, int alignment, Font font_black) {
	PdfPCell cell = new PdfPCell(new Phrase(text,font_black));
	cell.setPadding(3.0f);
	cell.setHorizontalAlignment(alignment);
	cell.setBorder(PdfPCell.BOX);
	cell.setBorderColor(PciColors.CURIOUS_BLUE);
	return cell;
	}
	
	public PdfPCell getImgCell(String text, int alignment, Font font_black) {
		PdfPCell cell = new PdfPCell(new Phrase(text,font_black));
		cell.setPadding(3.0f);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(PdfPCell.BOX);
		cell.setBorderColor(PciColors.CURIOUS_BLUE);
		cell.setFixedHeight(45f);
		return cell;
	}
	
	protected ByteArrayOutputStream createTemporaryOutputStream() {
		return new ByteArrayOutputStream(OUTPUT_BYTE_ARRAY_INITIAL_SIZE);
	}
	
	 protected void prepareWriter(PdfWriter writer)
	            throws DocumentException {
	 
	        writer.setViewerPreferences(getViewerPreferences());
	    }
	     
	    protected int getViewerPreferences() {
	        return PdfWriter.ALLOW_PRINTING | PdfWriter.PageLayoutSinglePage;
	    }
	     
	    protected void buildPdfMetadata(Map<String, String> model, Document document) {
			//content to be added
	    }
	    
	    	    
	    }
