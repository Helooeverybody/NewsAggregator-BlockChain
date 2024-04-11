
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import org.jsoup.nodes.Element;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.regex.Pattern;

public class DataScraping2 {
	public static void main(String[] args)  throws IOException{
		String baseUrl = "http://blockchain.news";
		Pattern pattern = Pattern.compile("\\d+$");
		String userAgent = "Mozilla/5,0(Window NT 10.0; Win64; x64) AppleWebKit/537.36(KHTML,like Gecko) Chrome/107.0.0.0 Safari/537.36";
		Document docss = Jsoup.connect(baseUrl).userAgent(userAgent).get();
		Elements topics = docss.select("li").select("a");
		int idx =1;
		List<Article> articles = new ArrayList<>();
		for(int i =5;i<=21;i++) {
			String relativeUrl = topics.get(i).attr("href"); 
			String completeUrl = baseUrl+relativeUrl;
			
			while(!completeUrl.equals(baseUrl)) {
				
				Document docs = Jsoup.connect(completeUrl).userAgent(userAgent).get();		
				Elements news = docs.getElementById("main").select("a");
				LinkedHashSet<String> uniqueUrls = new LinkedHashSet<>();
				for(Element n: news) {
					
					String completeUrlIn = baseUrl+n.attr("href");
			
					uniqueUrls.add(completeUrlIn);
				}
				for(String url : uniqueUrls) {
					if (!pattern.matcher(url).find()) {
						try {		
						Document doc  = Jsoup.connect(url).userAgent(userAgent).get();
						
						System.out.println(idx);
						System.out.println(url);
						idx++;


						
						String link = completeUrl;//
						String source = baseUrl;//
						String type = "News Articles";//
				
						
						String title=doc.selectFirst("b").text();//
						
						String content= doc.select("div.textbody").text();	//	
						String summary = null;
						String keyword = "conclusion,";
					    int index = content.lastIndexOf(keyword);

					    if (index != -1) {
					            // Find the index of the last occurrence of "conclusion"
					    	
					            int startIndex = index + keyword.length()+1;
					            summary = content.substring(startIndex);}
						String date = doc.selectFirst(".entry-date").text();	
				
						String author = doc.selectFirst(".entry-cat").text();
					
					
						String tags = "";
						String category=relativeUrl.replace("/tag/","")+" Article";	
						System.out.println(summary);
		
						Article article = new Article();
						article.setArticleLink(link);
						article.setArticleSummary(summary);
						article.setArticleTitle(title);
						article.setArticleType(type);
			
						article.setAuthor(author);
						article.setCategory(category);
						article.setDate(date);
						
						article.setTags(tags);
						article.setWebsiteSource(source);
						articles.add(article);
						System.out.println(title);
							}catch(Exception e) {
								 System.out.println("Error occurred: " + e.getMessage());
					             continue;
							}
						}
					}
				
						
					  	completeUrl = baseUrl + docs.select("a:contains(Next >)").attr("href");
					  	
					  	if (completeUrl.equals("http://blockchain.news/tag/bitcoin/127")){
					  			completeUrl ="http://blockchain.news/tag/bitcoin/128";}
					  	else if (completeUrl.equals("http://blockchain.news/tag/bitcoin/405")){
				  			completeUrl ="http://blockchain.news/tag/bitcoin/406";}
					  	
					
		    }
		}	
	}
}	




