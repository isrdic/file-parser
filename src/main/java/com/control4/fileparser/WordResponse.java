package com.control4.fileparser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WordResponse implements Comparable<WordResponse> {

    private int numberOfOccur;
    private String word;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WordResponse)) return false;
        WordResponse that = (WordResponse) o;
        return getWord().equalsIgnoreCase(that.getWord()); //getNumberOfOccur() == that.getNumberOfOccur() &&
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWord().toLowerCase());
    }

    @Override
    public String toString() {
        return "WordResponse {" +
                "numberOfOccur=" + numberOfOccur +
                ", word='" + word + '\'' +
                '}';
    }

    @Override
    public int compareTo(WordResponse o) {
        return Integer.compare(o.numberOfOccur, this.numberOfOccur);
    }
}
