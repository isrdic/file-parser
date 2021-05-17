package com.control4.fileparser;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import static java.lang.Math.abs;

@Component
public class FileBean {

    // Ako ulazi sa RESTa da se doda naziv fajla
    @Value("classpath:data/resourcedata.txt")
    Resource resourceFile;

    private String[] parseFileAndReturnString() throws IOException {
        File resource = resourceFile.getFile();
        // DA se izbace ENTERI...
        String[] resourceStringArray = new String(
                Files.readAllBytes(resource.toPath())).replaceAll("[^a-zA-Z0-9\\s\\r\\n\\f]", "").split(" ");
        Arrays.sort(resourceStringArray);
        return resourceStringArray;
    }

    // ArrayList
    public void printTop50ArrayList() throws IOException {

        ArrayList<String> words = new ArrayList<>(Arrays.asList(parseFileAndReturnString()));

        List<WordResponse> wordResponseList = new ArrayList<>();

        for (String word : words) {
            int currentWordCounter = 0;
            // Kada jedna rec zavrsi da se izbaci iz niza da se ne bi ponavljale
            for (int i = 0; i < words.size(); i++) {
                if (word.equalsIgnoreCase(words.get(i))) {
                    currentWordCounter++;
                }
            }
            // Samo ubacivanje u listu da proveri CONTAINS?
            WordResponse wordResponse = new WordResponse(currentWordCounter, word);

            if (!wordResponseList.contains(wordResponse)) {
                wordResponseList.add(wordResponse);
            }
        }

        Collections.sort(wordResponseList);
        wordResponseList.subList(0, Math.min(wordResponseList.size(), 50)).forEach(System.out::println);
    }

    // -- FILE FROM REST API --

    // TreeMap
    public void printTop50TreeMap() throws IOException {
        TreeMap<String, Integer> words = new TreeMap<>();

        String[] wordArray = parseFileAndReturnString();

        for (String word : wordArray) {

            word = word.toLowerCase();

            if (words.containsKey(word)) {
                continue;
            }

            for (int i = 0; i < wordArray.length; i++) {
                if (word.equalsIgnoreCase(wordArray[i])) {

                    if (!words.containsKey(word)) {
                        words.put(word.toLowerCase(), 1);
                    }
                    else {
                        words.put(word.toLowerCase(), words.get(word.toLowerCase()) + 1);
                    }
                }
            }


            // Kada jedna rec zavrsi da se izbaci iz niza da se ne bi ponavljale
            // Samo ubacivanje u listu da proveri CONTAINS?


        }

        words.forEach((key, value) -> System.out.println(key + " " + value));
//        wordResponseList.subList(0, Math.min(wordResponseList.size(), 50)).forEach(System.out::println);
    }

    // Array

    // From File


    private void insertIntoArray(String[] words) {

        WordResponse[] wordResponseArray = new WordResponse[50];
        for (String word : words) {
            int currentWordCounter = 0;
            // Kada jedna rec zavrsi da se izbaci iz niza da se ne bi ponavljale
            for (int i = 0; i < words.length; i++) {
                if (word.equalsIgnoreCase(words[i])) {
                    currentWordCounter++;
                }
            }
            WordResponse wordResponse = new WordResponse(currentWordCounter, word);

            //        6-, 5-, 4-, 3-, 2, // 1, n, n, n, n
            for (int i = wordResponseArray.length-1; i >= 0 ; i--) {
                if (wordResponseArray[i] != null && wordResponse.getNumberOfOccur() < wordResponseArray[i].getNumberOfOccur()) {
                    for (int j = wordResponseArray.length-1; j > i; j--) {
                        wordResponseArray[j] = wordResponseArray[j-1];
                    }
                    wordResponseArray[i+1] = wordResponse;
                    break;
                }
                wordResponseArray[abs(i-wordResponseArray.length-1)] = wordResponse;
            }
//            return wordResponseArray;
        }
        for (WordResponse wordResponse : wordResponseArray) {
            System.out.println(wordResponse);
        }

    }

}
