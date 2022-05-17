import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;

import javax.swing.*;

import static java.lang.Thread.sleep;

public class NBC extends JFrame {


    /*field*/          final int J_TEXT_FIELD_COL = 30;
    /*window*/         final int WINDOW_WIDTH = 500; final int WINDOW_HEIGHT = 500;
    /*time*/           final int TEN_MINUTES = 600000;
    /*text*/           final int J_TEXT_X = 30; final int J_TEXT_Y = 20; final int J_TEXT_WIDTH = 200; final int J_TEXT_HEIGHT = 100;
    /*button up*/      final int UPPER_BUTTON_X = 50; final int UPPER_BUTTON_Y = 150; final int UPPER_BUTTON_WIDTH = 300; final int UPPER_BUTTON_HEIGHT = 50;
    /*button down*/    final int LOWER_BUTTON_X = 150; final int LOWER_BUTTON_Y = 300; final int LOWER_BUTTON_WIDTH = 150; final int LOWER_BUTTON_HEIGHT = 50;
    /*Label text*/     final int J_Label_TEXT_X = 30; final int J_Label_TEXT_Y = 200; final int J_Label_TEXT_WIDTH = 500; final int J_Label_TEXT_HEIGHT = 100;
    /*Label text*/     final int J_Label_INTRO_TEXT_X = 30; final int J_Label_INTRO_TEXT_Y = 170; final int J_Label_INTRO_TEXT_WIDTH = 500; final int J_Label_INTRO_TEXT_HEIGHT = 100;

    private JLabel intro;
    private JLabel theResultMap;


    JPanel jP = new JPanel();
    JLabel jL = new JLabel();
    JTextField wordsToBeIgnored = new JTextField(J_TEXT_FIELD_COL);
    String wordToBeIgnored;


    public static void main(String[] args) {
        NBC myPilot = new NBC();
    }

    public void init() {
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        repaint();

    }

    public NBC() {
        this.init();
        this.drawOneWindow();
    }

    public void articleScanner() {
        new Thread(() -> {
            while (true) {
                try {
                    String url = "https://abcnews.go.com/";
                    Document website = Jsoup
                            .connect(url)
                            .get();
                    String title = website.title();
                    //  System.out.println("The title is:  " + title);


                    List<Element> elementList = website.getElementsByClass( "AnchorLink News News--xl");
                    for (int i = 0; i < elementList.size(); i++) {
                        Element currentElement = elementList.get(i);
                        Element linkElement = currentElement.child(0);
                        String linkToArticle = linkElement.attr("href");
                        //      System.out.println(linkToArticle);


                        String articleUrl = linkToArticle;
                        Document article = Jsoup
                                .connect(articleUrl)
                                .get();

                        List<Element> articleBody = article.getElementsByClass("Article__Content story");
//                System.out.println(articleBody.size());
//                System.out.println(articleBody.get(0).text());
                        String body = articleBody.get(0).text();
                        String[] words = body.split(" ");


                        Map<String, Integer> wordsMap = new HashMap<>();

                        for (int j = 0; j < words.length; j++) {
                            String currentWord = words[j];
                            Integer count = wordsMap.get(currentWord);

                            if (count == null) {
                                count = 0;
                            }
                            count++;
                            wordsMap.put(currentWord, count);

                        }
                        //    System.out.println(words.length);

                        int mostFrequentlyUsed = 0;
                        String theWord = null;

                        Map<String, Integer> tenPopularWordsMap = new HashMap<>();
//tree map?

                        for (int j = 0; j <= 9; j++) {
                            theWord = "";
                            mostFrequentlyUsed = -1;
                            for (String word : wordsMap.keySet()) {
//                                if (wordToBeIgnored.equals(theWord)){
//                                    wordsMap.remove(theWord);
//                                }

                                Integer theVal = wordsMap.get(word);
                                if (theVal > mostFrequentlyUsed) {
                                    mostFrequentlyUsed = theVal;
                                    theWord = word;
                                }
                                if (wordToBeIgnored.equals(theWord) ){
                                    wordsMap.remove(theWord);
                                    tenPopularWordsMap.remove(theWord);

                                }

                            }
                            tenPopularWordsMap.put(theWord, mostFrequentlyUsed);

                            wordsMap.remove(theWord);


                        }

                        this.intro = addLabel("The 10 most popular words in this article are: ",J_Label_INTRO_TEXT_X,J_Label_INTRO_TEXT_Y, J_Label_INTRO_TEXT_WIDTH, J_Label_INTRO_TEXT_HEIGHT);
                        this.theResultMap = addLabel(String.valueOf((tenPopularWordsMap.keySet())),J_Label_TEXT_X,J_Label_TEXT_Y, J_Label_TEXT_WIDTH, J_Label_TEXT_HEIGHT);

                        System.out.println(tenPopularWordsMap.keySet());


//                System.out.printf("The most frequently used word is '%s', %d times",
//                        theWord,mostFrequentlyUsed);
//
//                System.out.println();
//                System.out.println(words.length);


                        break;

                    }

                    sleep(TEN_MINUTES);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
    public JLabel addLabel(String labelText, int x, int y, int width, int height) {
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.black);
        label.setBounds(x, y, width, height);
        this.add(label);
        return label;
    }
    private void drawOneWindow() {
        JLabel title = new JLabel();
        title.setText(" Hello there  :) ");
        title.setBounds(J_TEXT_X, J_TEXT_Y, J_TEXT_WIDTH, J_TEXT_HEIGHT);
        this.add(title);

        jP.add(wordsToBeIgnored);
        add(jP);


        //buttons start here
        JButton addWordBtn = new JButton("Add here a word to be ignored :) ");
        addWordBtn.setBounds(UPPER_BUTTON_X, UPPER_BUTTON_Y, UPPER_BUTTON_WIDTH, UPPER_BUTTON_HEIGHT);
        this.add(addWordBtn);
        addWordBtn.addActionListener((event) -> {
            System.out.println("button click");
            JTextField wordsToBeIgnored = new JTextField();
            this.add(wordsToBeIgnored);
            wordsToBeIgnored.setBounds(50,100, 200,30);
            repaint();
            wordsToBeIgnored.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String input =  wordsToBeIgnored.getText();
                    jL.setText(input);
                    System.out.println(input);
                    wordToBeIgnored = input;
                }
            });



        });

        JButton button = new JButton("Start scan");
        button.setBounds(LOWER_BUTTON_X, LOWER_BUTTON_Y, LOWER_BUTTON_WIDTH, LOWER_BUTTON_HEIGHT);
        this.add(button);
        button.addActionListener((event) -> {
            System.out.println("button click");
            articleScanner();

            repaint();



        });


    }

}


