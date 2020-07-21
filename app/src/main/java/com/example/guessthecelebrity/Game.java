package com.example.guessthecelebrity;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {
    private ArrayList<String> names;
    private ArrayList<String> https;
    static int numOfQuestions = -1;
    int randomNumber;


    public ArrayList<String> getNames() {
        return names;
    }

    public ArrayList<String> getHttps() {
        return https;
    }


    public Game(){
       gameStart();

    }

    //initialize game map
    public void gameStart() {

        try{
            ImageHttpNameDownloader task = new ImageHttpNameDownloader();

            String htmlCode = " ";
            try {
                htmlCode = (String) task.execute("https://www.imdb.com/list/ls052283250/").get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(htmlCode);

            actorsNamesWithPicturesHttps(htmlCode);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public GameView nextQuestion(){
        numOfQuestions++;

        if(numOfQuestions < names.size()){
            GameView gameView = new GameView();

            ImageDownloader imageDownloader = new ImageDownloader();

            Random random = new Random();

            randomNumber = random.nextInt(names.size());
            //setting image
            try {
                gameView.setImageOfActor((Bitmap) imageDownloader.execute(https.get(randomNumber)).get());

            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //setting correct name
            gameView.setCorrectActorName(names.get(randomNumber));
            //setting others actors
            gameView.setOtherActorsNames(randomNames());

            return gameView;
        }

        return null;
    }


    private String[] randomNames(){
        String[] namess = {"","",""};
        ArrayList<Integer> tab = new ArrayList<>();
        tab.add(randomNumber);
        Random random = new Random();
        int mapLength = names.size();
        int rand;
        for(int i=0;i<3;i++){
            rand = random.nextInt(mapLength);
            while(tab.contains(rand)) rand = random.nextInt(mapLength);
            tab.add(rand);
            namess[i] = names.get(rand);
        }
        return  namess;

    }

    private void actorsNamesWithPicturesHttps(String html){
        names = new ArrayList<>();
        https = new ArrayList<>();

        Pattern namePattern = Pattern.compile("<img alt=\"(.*?)\"");
        Pattern htmlPattern = Pattern.compile("src=\"https://m.media-amazon.com/images/(.*?)\"");
        Matcher htmlMatcher = htmlPattern.matcher(html);
        Matcher nameMatcher = namePattern.matcher(html);

        String htmlSubstring = "";
        String nameSubstring = "";

        while(nameMatcher.find()){
            htmlMatcher.find();
            htmlSubstring = (htmlMatcher.group()).substring(5);
            htmlSubstring = htmlSubstring.substring(0,htmlSubstring.length()-1);
            nameSubstring = (nameMatcher.group()).substring(10);
            nameSubstring = nameSubstring.substring(0,nameSubstring.length()-1);

            System.out.println(nameSubstring + " >> " + htmlSubstring );
            names.add(nameSubstring);
            https.add(htmlSubstring);
        }
    }

}
