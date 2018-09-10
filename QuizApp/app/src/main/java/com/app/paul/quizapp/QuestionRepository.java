package com.app.paul.quizapp;

import java.util.ArrayList;
import java.util.List;

public class QuestionRepository {

    public List<Question> createRepo(){
        List<Question> repo = new ArrayList<>();
        repo.add(new Question("Who was the Supreme Allied Commander in World War II ?",
                new String[]{"Dwight D. Eisenhower"},"George Patton","Winston Churchill","Erwin Rommel","Dwight D. Eisenhower"));
        repo.add(new Question("The wars between the Romans and Carthaginians were called:",
                new String[]{"Punic Wars"},"The Sessanid Wars","The Judean Wars","Punic Wars","The Peloponnesian War"));
        repo.add(new Question("Which of these wars cast a shadow over the entire 19th century ?",
                new String[]{"The Napoleonic Wars"},"World War I","The Franco-Prussian War","The Napoleonic Wars","The War of 1812"));
        repo.add(new Question("Which of these 18th century events created the political spectrum as we know it today ?",
                new String[]{"The French Revolution"},"The French Revolution","The Assassination of Franz Ferdinand","The English Civil War","The Russian Revolution"));
        repo.add(new Question("Select the countries the fought for the Allies in World War II",
                new String[]{"United States","France","United Kingdom"},"United States","Italy","France","United Kingdom"));
        repo.add(new Question("Select the characters of World War II",
                new String[]{"Benito Mussolini","Franklin Roosevelt"},"Woodrow Wilson","Benito Mussolini","Franklin Roosevelt","Georges Clemenceau"));


        return  repo;
    }
}
