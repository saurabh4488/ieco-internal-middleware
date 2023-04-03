package ieco.internal.middleware.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class PdfPageEvent extends PdfPageEventHelper {
	
	private PdfTemplate t;
	private Image total;

    public void onOpenDocument(PdfWriter writer) {
        t = writer.getDirectContent().createTemplate(30, 16);
        try {
            total = Image.getInstance(t);
            total.setRole(PdfName.ARTIFACT);
            total.setBorderColor(PciColors.CURIOUS_BLUE);
            total.setAlignment(Image.LEFT);
            total.scaleToFit(10, 10);
        } catch (DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }
    @Override
    public void onStartPage(PdfWriter writer, Document document) {
    	Rectangle layout = new Rectangle(PageSize.A4);
        //layout.setBackgroundColor(PciColors.AQUA); //Background color
        layout.setBorderColor(PciColors.AQUA);  //Border color
        layout.setBorderWidth(4.0f);      //Border width  
        layout.setBorder(Rectangle.BOX);
        try {
			document.add(layout);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
    	super.onStartPage(writer, document);
    }
    
    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        addFooter(writer);
    }

    private void addFooter(PdfWriter writer){
        PdfPTable footer = new PdfPTable(3);
        try {
            footer.setWidths(new int[]{24, 2, 1});
            footer.setTotalWidth(527);
            footer.setLockedWidth(true);
            footer.getDefaultCell().setFixedHeight(40);
            footer.getDefaultCell().setBorder(Rectangle.TOP);
            footer.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

            footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            footer.addCell(new Phrase(String.format("Page %d of", writer.getPageNumber()), new Font(Font.FontFamily.TIMES_ROMAN, 8)));
            PdfPCell totalPageCount = new PdfPCell(total);
            totalPageCount.setBorder(Rectangle.TOP);
            totalPageCount.setBorderColor(BaseColor.LIGHT_GRAY);
            footer.addCell(totalPageCount);

            PdfContentByte canvas = writer.getDirectContent();
            canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
            footer.writeSelectedRows(0, -1, 34, 50, canvas);
            canvas.endMarkedContentSequence();
        } catch(DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }
    

}