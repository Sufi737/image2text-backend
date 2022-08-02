package sufyan.imagediscern.tesseract.sample.classes;

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ArabicText {

	public static void main(String[] args) {
		File imageFile = new File("src/main/resources/static/images/arabic_board.jpg");
		ITesseract instance = new Tesseract();  // JNA Interface Mapping
		instance.setDatapath("/usr/share/tesseract-ocr/4.00/tessdata"); // path to tessdata directory
		try {
			instance.setLanguage("ara");
            String result = instance.doOCR(imageFile);
            instance.setLanguage("ara");
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
	}

}
