/*
 * NOTE: tesseract-ocr must be present in the environment. For Linux this can be done using apt-get install tesseract-ocr 
 * */

package sufyan.imagediscern.tesseract.sample.classes;

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ReferenceCode {

	public static void main(String[] args) {
		File imageFile = new File("src/main/resources/static/images/tesseract_sample.png");
		ITesseract instance = new Tesseract();  // JNA Interface Mapping
		instance.setDatapath("/usr/share/tesseract-ocr/4.00/tessdata"); // path to tessdata directory
		try {
            String result = instance.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
	}

}
