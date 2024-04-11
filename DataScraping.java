

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
public class DataScraping {
	public static void main(String[] args) throws IOException  {
		int j =1;
		
		String baseUrl = "http://blockchainmagazine.net";
		String userAgent = "Mozilla/5,0(Window NT 10.0; Win64; x64) AppleWebKit/537.36(KHTML,like Gecko) Chrome/107.0.0.0 Safari/537.36";
		Document docs = Jsoup.connect(baseUrl).userAgent(userAgent).get();
		
		for(int i =2; i<=10; i++) {
			String nextURL = docs.getElementsByTag("a").get(i).attr("href");
			docs = Jsoup.connect(nextURL).userAgent(userAgent).get();

			Elements nextElements= docs.select(".mtc.stc_h");
			
				List<Article> articles = new ArrayList<>();
				for(Element nextElement: nextElements) {
					nextURL = nextElement.attr("href");
					//System.out.println(nextURL);
					Document doc = Jsoup.connect(nextURL).userAgent(userAgent).get();
					String link = nextURL;
					String source = baseUrl;
					String type = "News Articles";
					String summary = null;
					String title=doc.select(".stm_post_view__title").text();;
					String content= doc.select(".stm_post_view__content").text();
					String[] date_author = doc.selectFirst("div.date").text().split("\\s*by\\s*");
					
					String date = date_author[0];
					String author =date_author[1];
					String tags = "";
					String keyword = "conclusion,";
					int index = content.lastIndexOf(keyword);

					    if (index != -1) {
					            // Find the index of the last occurrence of "conclusion"
					    	
					            int startIndex = index + keyword.length()+1;
					            summary = content.substring(startIndex);}
					String category=doc.select(".sbc.mtc.wtc_h.mbc_h.no_deco").text();	
					System.out.println(j);
					System.out.println(summary);
					Article article = new Article();
					article.setArticleLink(link);
					article.setArticleSummary(summary);
					article.setArticleTitle(title);
					article.setArticleType(type);
					article.setAuthor(author);
					article.setCategory(category);
					article.setDate(date);
					article.setDetailedContent(content);
					article.setTags(tags);
					article.setWebsiteSource(source);
					articles.add(article);
					
					j++;
					
				}
				nextURL = docs.select("a.next.page-numbers").attr("href");
				if(nextURL.isEmpty()) {break;}
				docs = Jsoup.connect(nextURL).userAgent(userAgent).get();
				nextElements = docs.select(".mtc.stc_h");
				

				
				
			
		}
	}
}