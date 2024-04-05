package com.example.QRCode;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.google.zxing.WriterException;

import static org.junit.jupiter.api.Assertions.*;

class QRCodeServiceTest {

    private QRCodeService qrCodeService;

    @BeforeEach
    void setUp() {
        qrCodeService = new QRCodeService();
    }

    @Test
    void generateQRCodeImage_WithValidInput() {
        assertDoesNotThrow(() -> {
            byte[] result = qrCodeService.generateQRCodeImage("Valid Input", 200, 200, null);
            assertNotNull(result);
            assertTrue(result.length > 0);
        });
    }

    @Test
    void generateQRCodeImage_WithEmptyString() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            qrCodeService.generateQRCodeImage("", 200, 200, null);
        });

        assertEquals("Der Inhalt für den QR-Code darf nicht null oder leer sein.", thrown.getMessage());
    }

    @Test
    void generateQRCodeImage_WithNullInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            qrCodeService.generateQRCodeImage(null, 200, 200, null);
        });
    }

    @Test
    void generateQRCodeImage_WithInvalidDimensions() {
        assertThrows(IllegalArgumentException.class, () -> {
            qrCodeService.generateQRCodeImage("Valid Input", -1, -1, null);
        });
    }
}
