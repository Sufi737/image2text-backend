package sufyan.imagediscern.rest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import net.sourceforge.tess4j.TesseractException;
import sufyan.imagediscern.services.ExtractData;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RequestMapping("/image")
public class ImageUpload {
	
	@Autowired
	private ExtractData extractData;
	
	@Autowired
	private Gson gson;

	@PostMapping("/extract")
	public Map<String, ArrayList<String>> upload(
			@RequestParam(value = "image") MultipartFile image,
			@RequestParam(value = "selectedOptions") String selectedOptions
	) throws IOException, TesseractException {
		Map<String, Boolean> selectedOptionsMap = gson.fromJson(selectedOptions, Map.class);
		byte[] imageData = image.getBytes();
		ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
	    try {
	        BufferedImage bufferedImage =  ImageIO.read(bais);
	        Map<String, ArrayList<String>> resultSet = new HashMap<String, ArrayList<String>>();
	        resultSet = extractData.extractData(selectedOptionsMap, bufferedImage);
	        return resultSet;
	    } catch (IOException e) {
	        throw new RuntimeException(e);
	    }
	}
}
