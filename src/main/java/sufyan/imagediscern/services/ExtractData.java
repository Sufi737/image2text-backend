package sufyan.imagediscern.services;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

public interface ExtractData {
	public Map<String, ArrayList<String>> extractData(Map<String, Boolean> selectedOptions, BufferedImage image);
}
