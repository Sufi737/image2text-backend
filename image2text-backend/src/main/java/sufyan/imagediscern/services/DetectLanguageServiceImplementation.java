package sufyan.imagediscern.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.detectlanguage.DetectLanguage;
import com.detectlanguage.Result;
import com.detectlanguage.errors.APIError;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

@Component
public class DetectLanguageServiceImplementation implements DetectLanguageService {
	
	@Value("${DetectLanguage.API_KEY}")
	private String api_key;
	
	private Gson gson;
	
	@Autowired
	DetectLanguageServiceImplementation(Gson gson) {
		this.gson = gson;
	}
	
	public String sanitizeLanguage(String name) {
		String sanitizedName = name.substring(0,1) + name.substring(1).toLowerCase();
		return sanitizedName;
	}
	
	public ArrayList<String> getLanguageNamesFromCodes(
			List<String> languageCodes, 
			ArrayList<LinkedTreeMap<String, String>> languagesList
	) {
		ArrayList<String> languages = new ArrayList<String>();
		for (String languageCode: languageCodes) {
	    	LinkedTreeMap<String, String> languageName = languagesList
		    		.stream()
		    		.filter((language) -> language.get("code").equals(languageCode))
		    		.findFirst()
		    		.orElse(null);
	    	languages.add(languageName.get("name"));
	    }
		return languages;
	}

	@Override
	public ArrayList<String> detectLanguage(String text) {
		List<Result> results;
		ArrayList<String> resultList = new ArrayList<String>();
		try {
			DetectLanguage.apiKey = api_key;
			results = DetectLanguage.detect(text);
			List<String> languageCodes = results
					.stream()
					.map(result -> result.language).collect(Collectors.toList());
		    BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/languages_list.json"));
		    ArrayList<LinkedTreeMap<String, String>> languagesList = gson.fromJson(bufferedReader, ArrayList.class);
		    ArrayList<String> languages = new ArrayList<String>();
		    languages = getLanguageNamesFromCodes(languageCodes, languagesList);
		    languages = (ArrayList<String>) languages.stream()
		    .map(language -> sanitizeLanguage(language))
		    .collect(Collectors.toList());
		    return languages;
		} catch (APIError e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return resultList;
	}

}
