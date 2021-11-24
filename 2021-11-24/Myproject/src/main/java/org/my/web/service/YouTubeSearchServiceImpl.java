package org.my.web.service;


import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class YouTubeSearchServiceImpl implements YouTubeSearchService {
	private static String PROPERTIES_FILENAME = "youtube.properties";
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private static final long NUMBER_OF_VIDEOS_RETURNED = 5;
	private static final long NUMBER_OF_TOPICS_RETURNED = 5;
	private static final String YOUTUBE_API_KEY = "Your api key";
	private static YouTube youtube;
	
	
	public void searchWithYouTube() throws Exception{
		System.out.println("this is searchWithYouTube Method");
		Properties properties = new Properties();
		
		try {
			String topicsId = getTopicId();
			
			if(topicsId.length() < 1) {
				System.out.println("No topic id will be applied to your search.");
			}
			
			// 키워드를 입력받는 함수
			// String queryTerm = getInputQuery("search");
			String queryTerm = "food"; 
			
			youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
		        public void initialize(HttpRequest request) throws IOException {}})
		      .setApplicationName("MyProject")
		      .build();
			 
			YouTube.Search.List search = youtube.search().list("id, snippet");
			String apiKey = properties.getProperty(YOUTUBE_API_KEY);
			search.setKey(apiKey);
			search.setQ(queryTerm);
			 
			if(topicsId.length() > 0) {
				search.setTopicId(topicsId);
			}
			 
			search.setType("video");
			search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
			search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
			SearchListResponse searchResponse = search.execute();

			List<SearchResult> searchResultList = searchResponse.getItems();
		     
			if(searchResultList != null) {
				prettyPrint(searchResultList.iterator(), queryTerm, topicsId);
			} else {
				System.out.println("There were no results for your query.");
			}
		} catch (GoogleJsonResponseException e) {
		    System.err.println("There was a service error: " + e.getDetails().getCode() +
		        " : " + e.getDetails().getMessage());
		    e.printStackTrace();
		} catch (IOException e) {
		    System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
		    e.printStackTrace();
		}
	}
	

	private static String getInputQuery(String searchCategory) throws IOException {
		System.out.println("this is getInputQuery Method");
		/*
	    String inputQuery = "";
	
	    BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
	
	    do {
	      System.out.print("Please enter a " + searchCategory + " term: ");
	      inputQuery = bReader.readLine();
	    } while(inputQuery.length() < 1);
		*/
		String inputQuery="food";
	    return inputQuery;
	  }
	
	private static String getTopicId() throws IOException {
		System.out.println("this is getTopicId Method");
	    String topicsId = "";
	    String topicQuery = getInputQuery("topics");

	    HttpClient httpclient = new DefaultHttpClient();
	    List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("query", topicQuery));
	    params.add(new BasicNameValuePair("limit", Long.toString(NUMBER_OF_TOPICS_RETURNED)));

	    String serviceURL = "https://www.googleapis.com/freebase/v1/search";
	    String url = serviceURL + "?" + URLEncodedUtils.format(params, "UTF-8");

	    HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
	    HttpEntity entity = httpResponse.getEntity();

	    if (entity != null) {
	        InputStream instream = entity.getContent();
	        try {
	          ObjectMapper mapper = new ObjectMapper();
	          JsonNode rootNode = mapper.readValue(instream, JsonNode.class);

	          // Check that the response is valid.
	          if(rootNode.get("status").asText().equals("200 OK")) {
	            // I know the "result" field contains the list of results I need.
	            ArrayNode arrayNodeResults = (ArrayNode) rootNode.get("result");
	            // Only place we set the topicsId for a valid selection in this function.
	            topicsId = getUserChoice(arrayNodeResults);
	          }
	        } finally {
	          instream.close();
	        }
	    }
	    return topicsId;
	}
	
	private static String getUserChoice(ArrayNode freebaseResults) throws IOException {
		System.out.println("this is getUserChoice Method");
	    String freebaseId = "";

	    if(freebaseResults.size() < 1) {
	      return freebaseId;
	    }

	    for(int i = 0; i < freebaseResults.size(); i++) {
	      JsonNode node = freebaseResults.get(i);
	      System.out.print(" " + i + " = " + node.get("name").asText());
	      if(node.get("notable") != null) {
	        System.out.print(" (" + node.get("notable").get("name").asText() + ")");
	      }
	      System.out.println("");
	    }

	    //BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
	    String inputChoice = "2";
	    /*
	    do {
	      System.out.print("Choose the number of the Freebase Node: ");
	      inputChoice = bReader.readLine();
	    } while (!isValidIntegerSelection(inputChoice, freebaseResults.size()));
		*/
	    
	    // Returns Topic id needed for YouTube Search.
	    JsonNode node = freebaseResults.get(Integer.parseInt(inputChoice));
	    freebaseId = node.get("mid").asText();
	    return freebaseId;
	}
	
	public static boolean isValidIntegerSelection(String input, int max) {
		System.out.println("this is isValidIntegerSelection Method");
	    if (input.length() > 9)
	      return false;

	    boolean validNumber = false;
	    // Only accepts positive numbers of up to 9 numbers.
	    Pattern intsOnly = Pattern.compile("^\\d{1,9}$");
	    Matcher makeMatch = intsOnly.matcher(input);

	    if(makeMatch.find()){
	      int number = Integer.parseInt(makeMatch.group());
	      if((number >= 0) && (number < max)) {
	        validNumber = true;
	      }
	    }
	    return validNumber;
	}
	 private static void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query, String topicsId) {
		 System.out.println("this is prettyPrint Method");
		    System.out.println("\n=============================================================");
		    System.out.println("   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\" with Topics id: " + topicsId + ".");
		    System.out.println("=============================================================\n");

		    if(!iteratorSearchResults.hasNext()) {
		      System.out.println(" There aren't any results for your query.");
		    }

		    while(iteratorSearchResults.hasNext()) {

		      SearchResult singleVideo = iteratorSearchResults.next();
		      ResourceId rId = singleVideo.getId();

		      // Double checks the kind is video.
		      if(rId.getKind().equals("youtube#video")) {
		        Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().get("default");

		        System.out.println(" Video Id" + rId.getVideoId());
		        System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
		        System.out.println(" Thumbnail: " + thumbnail.getUrl());
		        System.out.println("\n-------------------------------------------------------------\n");
		      }
		   }
	 }
	 

	 /*출처 : https://lasdri.tistory.com/794 */
	@Override
	public void searchTest(String keyword) throws Exception {
		String apiUrl = "https://www.googleapis.com/youtube/v3/search?key="+YOUTUBE_API_KEY;
		apiUrl += "&part=snippet&type=video&maxResults=20&videoEmbeddable=true";
		apiUrl += "&q="+URLEncoder.encode(keyword, "UTF-8");
		
		URL url = new URL(apiUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while((inputLine = br.readLine()) != null) {
			response.append(inputLine);
		}
		br.close();
		
		System.out.println(response.toString());
	}
}
