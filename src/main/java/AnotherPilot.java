import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnotherPilot {
    public static void main(String[] args) {


        try {


            String url = "https://www.nbcnews.com/";
            Document website = Jsoup
                    .connect(url)
                    .get();
            String title = website.title();
            System.out.println("The title is:  " + title);


            List<Element> elementList= website.getElementsByClass("tease-card__headline tease-card__title tease-card__title--news relative");
            for (int i = 0; i < elementList.size(); i++) {
                Element currentElement = elementList.get(i);
                Element linkElement = currentElement.child(0);
                String linkToArticle = linkElement.attr("href");
          //      System.out.println(linkToArticle);


                String articleUrl = linkToArticle;
                Document article = Jsoup
                        .connect(articleUrl)
                        .get();

                List<Element> articleBody =  article.getElementsByClass("article-body__content" );
//                System.out.println(articleBody.size());
//                System.out.println(articleBody.get(0).text());
                String body = articleBody.get(0).text();
                String [] words = body.split(" ");


                Map <String, Integer> wordsMap = new HashMap<>();

                for (int j = 0; j < words.length; j++) {
                    String currentWord =  words[j];
                    Integer count = wordsMap.get(currentWord);

                    if (count== null){
                        count = 0;
                    }
                    count++;
                    wordsMap.put(currentWord,count);

                }
                System.out.println(words.length);

                int mostFrequentlyUsed =0;
                String theWord = null;

                Map <String, Integer> tenPopularWordsMap = new HashMap<>();

                for (int j = 0; j < words.length; j++) {

                    for (String word: wordsMap.keySet() ){
                        Integer theVal = wordsMap.get(word);
                        if (theVal>mostFrequentlyUsed){
                            mostFrequentlyUsed = theVal;
                            theWord = word;
                        }
                    }
                    if ( tenPopularWordsMap.get(theWord)==null) {
                        tenPopularWordsMap.put(theWord, mostFrequentlyUsed);
                    }

                    else {
                        wordsMap.remove(theWord);

                    }

                }
                System.out.println(tenPopularWordsMap.keySet());
//                System.out.printf("The most frequently used word is '%s', %d times",
//                        theWord,mostFrequentlyUsed);
//
//                System.out.println();
//                System.out.println(words.length);


                break;

            }



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}



