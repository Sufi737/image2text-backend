package sufyan.imagediscern.services;

import java.awt.image.BufferedImage;

public interface TesseractService {
	public String getTextFromImage(BufferedImage image);
}
