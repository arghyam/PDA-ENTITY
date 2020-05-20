package com.socion.entity.utils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;

public class PdfUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(PdfUtils.class);

    public static String cropPdf(String src, String dest, int w, int h, int x, int y) {

        float width = 8.5f * w;
        float height = 6f * h;
        float tolerance = 1f;

        PdfReader reader = null;
        try {
            reader = new PdfReader(src);
        } catch (IOException e) {
            LOGGER.info("EXCEPTION: {}", e.getMessage());
        }

        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            Rectangle rectangle = reader.getCropBox(i);
            Rectangle cropBox = new Rectangle(x, y, rectangle.getWidth(), rectangle.getHeight());

            float widthToAdd = width - cropBox.getWidth();
            float heightToAdd = height - cropBox.getHeight();
            if (Math.abs(widthToAdd) > tolerance || Math.abs(heightToAdd) > tolerance) {
                float[] newBoxValues = new float[]{
                        cropBox.getLeft() - widthToAdd / 2,
                        cropBox.getBottom() - heightToAdd / 2,
                        cropBox.getRight() + widthToAdd / 2,
                        cropBox.getTop() + heightToAdd / 2
                };
                PdfArray newBox = new PdfArray(newBoxValues);

                PdfDictionary pageDict = reader.getPageN(i);
                pageDict.put(PdfName.CROPBOX, newBox);
                pageDict.put(PdfName.MEDIABOX, newBox);
            }
        }

        PdfStamper stamper = null;
        try {
            stamper = new PdfStamper(reader, new FileOutputStream(dest));
        } catch (DocumentException | IOException e) {
            LOGGER.info("EXCEPTION: {}", e.getMessage());
        }
        try {
             if(stamper != null) {
                 stamper.close();
             }
        } catch (DocumentException | IOException e) {
            LOGGER.info("EXCEPTION: {}", e.getMessage());
        }

        return dest;

    }
}
