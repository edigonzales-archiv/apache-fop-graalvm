package apache.fop.graalvm;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.apache.fop.render.intermediate.IFContext;
import org.apache.fop.render.intermediate.IFDocumentHandler;
import org.apache.fop.render.pdf.PDFDocumentHandler;

public class Converter {    
    public void runXml2Pdf_V1(String xmlFileName, String outputDirectory) throws Exception {
        System.out.println("FOP ExampleXML2PDF\n");
        System.out.println("Preparing...");

        File xmlfile = new File(xmlFileName);
        File pdffile = new File(outputDirectory, "ResultXML2PDF.pdf");
        
        Path outputPath = Paths.get(outputDirectory);
        File xsltfile = new File(Paths.get(outputPath.toFile().getAbsolutePath(), "projectteam2fo.xsl").toFile().getAbsolutePath());
        InputStream xsltFileInputStream = Converter.class.getResourceAsStream("/"+"projectteam2fo.xsl"); 
        Files.copy(xsltFileInputStream, xsltfile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        xsltFileInputStream.close();

        System.out.println("Input: XML (" + xmlfile + ")");
        System.out.println("Stylesheet: " + xsltfile);
        System.out.println("Output: PDF (" + pdffile + ")");
        System.out.println();
        System.out.println("Transforming...");

        final FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();

        OutputStream out = new java.io.FileOutputStream(pdffile);
        out = new java.io.BufferedOutputStream(out);
        
        Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(xsltfile));

        transformer.setParameter("versionParam", "2.0");
        
        Source src = new StreamSource(xmlfile);
        Result res = new SAXResult(fop.getDefaultHandler());
        
        transformer.transform(src, res);
        out.close();
    }
    
    public void runXml2Pdf_V2(String xmlFileName, String outputDirectory) throws Exception {
        System.out.println("FOP ExampleXML2PDF\n");
        System.out.println("Preparing...");

        File xmlfile = new File(xmlFileName);
        File pdffile = new File(outputDirectory, "ResultXML2PDF.pdf");
        
        Path outputPath = Paths.get(outputDirectory);
        File xsltfile = new File(Paths.get(outputPath.toFile().getAbsolutePath(), "projectteam2fo.xsl").toFile().getAbsolutePath());
        InputStream xsltFileInputStream = Converter.class.getResourceAsStream("/"+"projectteam2fo.xsl"); 
        Files.copy(xsltFileInputStream, xsltfile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        xsltFileInputStream.close();

        System.out.println("Input: XML (" + xmlfile + ")");
        System.out.println("Stylesheet: " + xsltfile);
        System.out.println("Output: PDF (" + pdffile + ")");
        System.out.println();
        System.out.println("Transforming...");
        
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        
        IFDocumentHandler handler = new PDFDocumentHandler(new IFContext(foUserAgent));
        StreamResult streamResult =  new StreamResult();
        streamResult.setSystemId(pdffile.getAbsolutePath());
        handler.setResult(streamResult);
        foUserAgent.setDocumentHandlerOverride(handler);
        Fop fop = fopFactory.newFop(foUserAgent);
       
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(xsltfile));

        Source src = new StreamSource(xmlfile);
        Result res = new SAXResult(fop.getDefaultHandler());
        
        transformer.transform(src, res);
    }


}
