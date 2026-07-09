package com.cloudcart.backend.service;

import com.cloudcart.backend.entity.Order;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;

@Service
public class InvoiceService {

    public ByteArrayInputStream generateInvoice(Order order) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);

            document.open();

            Font titleFont =
                    FontFactory.getFont(
                            FontFactory.HELVETICA_BOLD,
                            28);

            Font subTitle =
                    FontFactory.getFont(
                            FontFactory.HELVETICA,
                            14);

            Font normal =
                    FontFactory.getFont(
                            FontFactory.HELVETICA,
                            12);

            Font bold =
                    FontFactory.getFont(
                            FontFactory.HELVETICA_BOLD,
                            14);

            // Heading

            Paragraph heading =
                    new Paragraph(
                            "CLOUDCART",
                            titleFont);

            heading.setAlignment(Element.ALIGN_CENTER);

            document.add(heading);

            Paragraph shop =
                    new Paragraph(
                            "ONLINE SHOPPING",
                            subTitle);

            shop.setAlignment(Element.ALIGN_CENTER);

            document.add(shop);

            document.add(new Paragraph(" "));

            document.add(new Paragraph(
                    "Invoice Date : "
                            + LocalDate.now(),
                    normal));

            document.add(new Paragraph(" "));

            PdfPTable table =
                    new PdfPTable(2);

            table.setWidthPercentage(100);

            table.addCell("Invoice No");
            table.addCell("#" + order.getId());

            table.addCell("Customer");
            table.addCell(order.getUserEmail());

            table.addCell("Payment");
            table.addCell(order.getPaymentMethod());

            table.addCell("Status");
            table.addCell(order.getStatus());

            table.addCell("Amount");
            table.addCell("₹ " + order.getTotalAmount());

            table.addCell("Shipping Address");
            table.addCell(order.getAddress());

            document.add(table);

            document.add(new Paragraph(" "));

            Paragraph total =
                    new Paragraph(
                            "Total Amount : ₹ "
                                    + order.getTotalAmount(),
                            bold);

            total.setAlignment(Element.ALIGN_RIGHT);

            document.add(total);

            document.add(new Paragraph(" "));

            Paragraph footer =
                    new Paragraph(
                            "Thank you for shopping with CloudCart ❤️",
                            bold);

            footer.setAlignment(Element.ALIGN_CENTER);

            document.add(footer);

            document.close();

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return new ByteArrayInputStream(
                out.toByteArray());

    }

}