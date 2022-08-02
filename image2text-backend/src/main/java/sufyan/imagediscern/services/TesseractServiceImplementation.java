package sufyan.imagediscern.services;

import org.springframework.beans.factory.annotation.Autowired;
import java.awt.image.BufferedImage;
import org.springframework.stereotype.Component;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.TesseractException;

@Component
public class TesseractServiceImplementation implements TesseractService {
	@Autowired
	private ITesseract instance;

	@Override
	public String getTextFromImage(BufferedImage image) {
		instance.setDatapath("/usr/share/tesseract-ocr/4.00/tessdata");
		String result = "";
		try {
			result = instance.doOCR(image);
		} catch (TesseractException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
