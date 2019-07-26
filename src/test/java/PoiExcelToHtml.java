

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;


/**
 * <p>
 * @ClassName: FilePreview
 * </p>
 * <p>
 * Description: Excel文件转为html元素
 * </p>
 * 
 * @author 周宣
 * @date 2015年12月21日
 */
public class PoiExcelToHtml {
   
    
    public static void convertExceltoHtml(InputStream is,OutputStream os) throws IOException,ParserConfigurationException, TransformerException,InvalidFormatException {
        HSSFWorkbook workBook = new HSSFWorkbook(is);
        String content = null;
        StringWriter  writer = null;
       
        try {
            ExcelToHtmlConverter converter = new ExcelToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
            converter.setOutputColumnHeaders(false);
            converter.setOutputRowNumbers(false);
            converter.processWorkbook(workBook);
             writer = new StringWriter();
            Transformer serializer = TransformerFactory.newInstance().newTransformer();
            serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty(OutputKeys.METHOD, "html");
            serializer.transform(new DOMSource(converter.getDocument()),
                    new StreamResult(writer));

            content = writer.toString();
            writer.close();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        os.write(content.getBytes());
        os.flush();
        os.close();
    }


    public static void main(String[] args) throws InvalidFormatException, IOException, ParserConfigurationException, TransformerException {
    	convertExceltoHtml(new FileInputStream(new File("D:/zhaojunfei.xls")),new FileOutputStream(new File("d:/aa.html")));
    	
    }

}