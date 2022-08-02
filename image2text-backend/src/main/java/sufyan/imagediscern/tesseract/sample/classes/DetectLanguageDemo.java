package sufyan.imagediscern.tesseract.sample.classes;

import java.util.List;

import com.detectlanguage.DetectLanguage;
import com.detectlanguage.Result;
import com.detectlanguage.errors.APIError;

public class DetectLanguageDemo {

	public static void main(String[] args) {
		List<Result> results;
		try {
			DetectLanguage.apiKey = "1b34521dd471a0b45b7a751a6a428eb2";
			results = DetectLanguage.detect("Hello world");
			Result result = results.get(0);
			System.out.println("Language: " + result.language);
			System.out.println("Is reliable: " + result.isReliable);
			System.out.println("Confidence: " + result.confidence);
		} catch (APIError e) {
			e.printStackTrace();
		}
	}

}
