import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VOX {
    public static void main(String[] args) {


        try {


            String url = "https://www.vox.com/";
            Document website = Jsoup
                    .connect(url)
                    .get();
            String title = website.title();
            System.out.println("The title is:  " + title);

            String currentWord = " ";
            List<Element> elementList= website.getElementsByClass("c-entry-box--compact__title");
            for (int i = 0; i < elementList.size(); i++) {
                Element currentElement = elementList.get(i);
                Element linkElement = currentElement.child(0);
                String linkToArticle = linkElement.attr("href");
                System.out.println(linkToArticle);


                String articleUrl = linkToArticle;
                Document article = Jsoup
                        .connect(articleUrl)
                        .get();

                List<Element> articleBody =  article.getElementsByClass("c-entry-content" );
                System.out.println(articleBody.size());
                System.out.println(articleBody.get(0).text());
                String body = articleBody.get(0).text();
                String [] words = body.split(" ");


                Map <String, Integer> wordsMap = new HashMap<>();
                for (int j = 0; j < words.length; j++) {
                     currentWord =  words[j];
                    Integer count = wordsMap.get(currentWord);

                    if (count== null){
                        count = 0;
                    }
                    count++;
                    wordsMap.put(currentWord,count);

                }
                System.out.println(words.length);

                break;

            }

            System.out.println(currentWord);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}



       