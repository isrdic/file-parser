package com.control4.fileparser.util;

import com.control4.fileparser.dto.Type;
import com.control4.fileparser.dto.WordResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.util.*;

@Component
public class FileParserUtil {

    @Value("classpath:data/resourcedata.txt")
    Resource file;

    public List<WordResponse> engine(String resourceString, Type type) throws IOException {
        String[] resourceStringArray = (resourceString == null) ?
                getStringArrayFromFile(new String(Files.readAllBytes(file.getFile().toPath()))) :
                getStringArrayFromFile(resourceString);
        if (type.equals(Type.ArrayList)) {
            return printAndReturnTop50ArrayList(resourceStringArray);
        } else if (type.equals(Type.TreeSet)) {
            return printAndReturnTop50TreeSet(resourceStringArray);
        }
        throw new DirectoryNotEmptyException("");
    }

    public String[] getStringArrayFromFile(String resourceString) {
        String[] resourceStringArray = Arrays.stream(
                resourceString
                        .replaceAll("[^a-zA-Z0-9\\s+]", "")
                        .replaceAll("[\\n\\r]", " ")
                        .split(" "))
                .filter(e -> e.trim().length() > 0)
                .toArray(String[]::new);

        Arrays.sort(resourceStringArray);
        return resourceStringArray;
    }

    private List<WordResponse> printAndReturnTop50ArrayList(String[] resourceStringArray) {
        List<WordResponse> wordResponseList = new ArrayList<>();
        for (String word : resourceStringArray) {
            Long counter = Arrays.stream(resourceStringArray)
                    .filter(w -> w.equalsIgnoreCase(word))
                    .count();
            WordResponse wordResponse = new WordResponse(counter, word);
            if (!wordResponseList.contains(wordResponse)) {
                wordResponseList.add(wordResponse);
            }
        }
        Collections.sort(wordResponseList);
        wordResponseList = wordResponseList.subList(0, Math.min(wordResponseList.size(), 50));
        System.out.println("------ BEGIN ------");
        wordResponseList.forEach(System.out::println);
        System.out.println("------  END  ------");
        return wordResponseList;
    }

    private List<WordResponse> printAndReturnTop50TreeSet(String[] resourceStringArray) {
        SortedSet<WordResponse> results = new TreeSet<>((e1, e2) -> {
            int res = Long.compare(e2.getNumberOfOccur(), e1.getNumberOfOccur());
            return e1.getWord().equalsIgnoreCase(e2.getWord()) ? res : (res != 0 ? res : 1);
        });
        for (String word : resourceStringArray) {
            Long counter = Arrays.stream(resourceStringArray)
                    .filter(w -> w.equalsIgnoreCase(word))
                    .count();
            results.add(new WordResponse(counter, word));
        }
        System.out.println("------ BEGIN ------");
        results.stream().limit(Math.min(results.size(), 50)).forEach(System.out::println);
        System.out.println("------  END  ------");
        return new ArrayList<>(results).subList(0, Math.min(results.size(), 50));
    }

}
