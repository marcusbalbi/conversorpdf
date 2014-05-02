/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package conversorpdf.Conversor;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Normalizer;

/**
 *
 * @author marcus
 */
public class Conversor {
      
    /**
     * Parses a PDF to a plain text file.
     * @param pdf the original PDF
     * @param txt the resulting text
     * @throws IOException
     */
    public  boolean parsePdf(String pdf, String txt,boolean removerAcento) throws IOException {
        PdfReader reader = new PdfReader(pdf);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        PrintWriter out = new PrintWriter(new FileOutputStream(txt));
        TextExtractionStrategy strategy;
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            
            strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
            
            if(removerAcento == false)
            {       
                out.println(strategy.getResultantText());
            }
            else
            {
                 out.println(this.removeAcentos(strategy.getResultantText()));
            }
            
        }
        out.flush();
        out.close();
        
        return true;
    }
    
    private String removeAcentos(String str) {
        
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = str.replaceAll("[^\\p{ASCII}]", "");
        return str;

    }
}
