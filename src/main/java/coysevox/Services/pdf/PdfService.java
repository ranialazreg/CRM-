package coysevox.Services.pdf;


import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import coysevox.Entities.Devi;
import coysevox.Entities.Signature;
import coysevox.Repository.DeviRepository;
import coysevox.Repository.SignatureRepository;
import coysevox.dto.Devidto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;


@Service
public class PdfService {

    @Autowired
    DeviRepository deviRepository;
    @Autowired
    SignatureRepository signatureRepository;
    public Devidto addDevi(Devidto devidto) throws IOException {
        Devi devi = new Devi();
        devi.setId(devidto.getId());
        devi.setDescription(devidto.getDescription());
        devi.setQuantity(devidto.getQuantity());
        devi.setPrice(devidto.getPrice());
        return deviRepository.save(devi).getDto();
    }

    public List<Devidto> getAllDevi(){
        List<Devi> devis = deviRepository.findAll();
        return devis.stream().map(Devi::getDto).collect(Collectors.toList());
    }

    public boolean deletedevi(Long id){
        Optional<Devi> optionalItems= deviRepository.findById(id);
        if(optionalItems.isPresent()){
            deviRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }




    public ByteArrayInputStream devisToPdf(List<Devi> devisList) {
        double totalAmount = 0;
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // Add logo to the document
            Image logo = Image.getInstance("C:\\Users\\asus\\Downloads\\groupe_coysevox_logo.jfif"); // Replace with your logo path
            logo.setAbsolutePosition(36, 750); // Adjust position
            logo.scalePercent(15); // Adjust scaling to fit your needs
            document.add(logo);

            // Add "Facture" title aligned center
            Paragraph title = new Paragraph("Facture", new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Add the current system date on the right
            Paragraph dateParagraph = new Paragraph(new Date().toString(), new Font(Font.FontFamily.TIMES_ROMAN, 12));
            dateParagraph.setAlignment(Element.ALIGN_RIGHT);
            document.add(dateParagraph);

            document.add(Chunk.NEWLINE);
            document.add(new Paragraph(" ", new Font(Font.FontFamily.TIMES_ROMAN, 100)));

            PdfPTable table = new PdfPTable(4); // Adjust the number of columns based on your Devi object's fields
            table.setWidthPercentage(100);
            table.addCell("Description");
            table.addCell("Quantity");
            table.addCell("Price");
            table.addCell("Total");

            for (Devi devis : devisList) {
                table.addCell(devis.getDescription());
                table.addCell(String.valueOf(devis.getQuantity()));
                table.addCell(String.valueOf(devis.getPrice()));
                double lineTotal = devis.getPrice() * devis.getQuantity();
                table.addCell(String.format("%.2f", lineTotal));

                totalAmount += lineTotal;
            }

            table.addCell("Total Amount");
            table.addCell("");
            table.addCell("");
            table.addCell(String.format("%.2f", totalAmount));

            document.add(table);

            Paragraph footer = new Paragraph("La Direction Administrative et Financière", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.ITALIC));
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);

            // Add a signature area
            PdfPTable signatureTable = new PdfPTable(1);
            signatureTable.setWidthPercentage(50);
            signatureTable.setHorizontalAlignment(Element.ALIGN_RIGHT);

            PdfPCell cell = new PdfPCell(new Phrase("Signature:", new Font(Font.FontFamily.TIMES_ROMAN, 12)));
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setBorderWidthBottom(1);
            cell.setPaddingBottom(5);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            signatureTable.addCell(cell);

            // Add some spacing before the signature table
            document.add(new Paragraph(" "));
            document.add(signatureTable);

            // Fetch the signature image from the database
            byte[] signatureData = getSignatureImage(); // Method to retrieve signature

            // Add the signature image if it exists
            if (signatureData != null && signatureData.length > 0) {
                try {
                    Image signatureImage = Image.getInstance(signatureData);
                    signatureImage.scaleToFit(150, 50); // Adjust size as needed
                    signatureImage.setAlignment(Element.ALIGN_RIGHT);
                    document.add(signatureImage);
                } catch (Exception e) {
                    // Handle potential exceptions for invalid image formats
                    System.err.println("Failed to add signature image: " + e.getMessage());
                }
            }

            document.close();
        } catch (DocumentException | IOException ex) {
            throw new RuntimeException(ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    // Method to retrieve the signature image from the database
    private byte[] getSignatureImage() {
        Signature signature = signatureRepository.f(); // Adjust based on your entity structure
        if (signature != null) {
            String base64Image = signature.getSignatureData(); // Get the Base64 encoded string

            // Check if the string starts with the metadata
            String base64Data;
            if (base64Image.startsWith("data:image/png;base64,")) {
                base64Data = base64Image.substring("data:image/png;base64,".length());
            } else {
                base64Data = base64Image; // Assume it's already a valid Base64 string
            }

            return Base64.getDecoder().decode(base64Data); // Decode to byte array
        }
        return null; // Return null if no signature is found
    }



}
