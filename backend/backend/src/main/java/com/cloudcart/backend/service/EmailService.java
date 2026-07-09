package com.cloudcart.backend.service;

import com.cloudcart.backend.entity.Order;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOrderConfirmation(Order order)
            throws MessagingException {

        MimeMessage message =
                mailSender.createMimeMessage();

        MimeMessageHelper helper =
                new MimeMessageHelper(message, true);

        helper.setTo(order.getUserEmail());

        helper.setSubject("🛒 CloudCart - Order Confirmation");

        String html = """
                <html>

                <body style="font-family:Arial;background:#f5f5f5;padding:20px;">

                <div style="
                max-width:700px;
                margin:auto;
                background:white;
                padding:30px;
                border-radius:10px;
                box-shadow:0 0 10px #ddd;">

                <h1 style="color:#0d6efd;">
                🛒 CloudCart
                </h1>

                <hr>

                <h2 style="color:green;">
                ✅ Order Placed Successfully
                </h2>

                <p>
                Thank you for shopping with
                <b>CloudCart</b>.
                </p>

                <table
                style="width:100%%;
                border-collapse:collapse;">

                <tr>
                <td><b>Status</b></td>
                <td>%s</td>
                </tr>

                <tr>
                <td><b>Payment</b></td>
                <td>%s</td>
                </tr>

                <tr>
                <td><b>Total Amount</b></td>
                <td>₹ %.2f</td>
                </tr>

                <tr>
                <td><b>Address</b></td>
                <td>%s</td>
                </tr>

                </table>

                <br>

                <div
                style="
                background:#0d6efd;
                color:white;
                padding:15px;
                border-radius:8px;
                text-align:center;">

                Estimated Delivery
                <br>

                <b>3-5 Business Days</b>

                </div>

                <br>

                <center>

                <a
                href="http://localhost:3000/orders"

                style="
                background:green;
                color:white;
                padding:12px 25px;
                text-decoration:none;
                border-radius:5px;">

                View My Orders

                </a>

                </center>

                <hr>

                <center>

                ❤️ Thank you for shopping with us.

                </center>

                </div>

                </body>

                </html>
                """
                .formatted(

                order.getStatus(),
                order.getPaymentMethod(),
                order.getTotalAmount(),
                order.getAddress()

        );

        helper.setText(html, true);

        mailSender.send(message);
    }

}