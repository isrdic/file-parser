package com.control4.fileparser.unit;

import com.control4.fileparser.dto.Type;
import com.control4.fileparser.dto.WordResponse;
import com.control4.fileparser.util.FileParserUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FileParserUtilUnitTest {

    @Value("classpath:data/resourcedata.txt")
    Resource file;

    @Autowired
    private FileParserUtil fileParserUtil;

    @Test
    public void engineTest() throws IOException {
        List<WordResponse> response = fileParserUtil.engine(new String(Files.readAllBytes(file.getFile().toPath())), Type.ArrayList);
        assertEngineResponse(response);

        response = fileParserUtil.engine(new String(Files.readAllBytes(file.getFile().toPath())), Type.TreeSet);
        assertEngineResponse(response);

        response = fileParserUtil.engine(null, Type.ArrayList);
        assertEngineResponse(response);

        String text = "The initial private residence of billionaire philanthropist — and everyone’s favorite superhero — Tony Stark, is a massive, futuristic mansion set on a sea-side cliff along the coast of Malibu.\n" +
                "The high tech house comes with an integrated artificial intelligence system initially created to help out around the house (among other, more famous purposes) that Tony Stark named “Just a Rather Very Intelligent System”, shortened J.A.R.V.I.S., after his childhood butler Edwin Jarvis.\n" +
                "And while the choice in tech setup is all Tony, in terms of decor, the Stark mansion bears the signature of on-screen love interest Pepper Potts.\n" +
                "“Who do you think decorated in the first place? And now that I live here, I can make sure he doesn’t ruin the Feng Shui by sticking a suit of armor in the middle of the living room.”";

        response = fileParserUtil.engine(text, Type.ArrayList);
        assertEngineResponse(response);
    }

    private void assertEngineResponse(List<WordResponse> response) {
        assertThat(response).isNotNull();
        assertEquals(50, response.size());
        assertEquals("WordResponse {numberOfOccur=11, word='The'}", response.get(0).toString());
        assertEquals("WordResponse {numberOfOccur=6, word='of'}", response.get(1).toString());
        assertEquals("WordResponse {numberOfOccur=4, word='a'}", response.get(2).toString());
        assertEquals("WordResponse {numberOfOccur=4, word='in'}", response.get(3).toString());
        assertEquals("WordResponse {numberOfOccur=3, word='And'}", response.get(4).toString());
        assertEquals("WordResponse {numberOfOccur=3, word='Stark'}", response.get(5).toString());
        assertEquals("WordResponse {numberOfOccur=3, word='Tony'}", response.get(6).toString());
        assertEquals("WordResponse {numberOfOccur=2, word='I'}", response.get(7).toString());
        assertEquals("WordResponse {numberOfOccur=2, word='JARVIS'}", response.get(8).toString());
        assertEquals("WordResponse {numberOfOccur=2, word='System'}", response.get(9).toString());
        assertEquals("WordResponse {numberOfOccur=2, word='house'}", response.get(10).toString());
        assertEquals("WordResponse {numberOfOccur=2, word='is'}", response.get(11).toString());
        assertEquals("WordResponse {numberOfOccur=2, word='mansion'}", response.get(12).toString());
        assertEquals("WordResponse {numberOfOccur=2, word='tech'}", response.get(13).toString());
        assertEquals("WordResponse {numberOfOccur=2, word='that'}", response.get(14).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='Edwin'}", response.get(15).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='Feng'}", response.get(16).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='Intelligent'}", response.get(17).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='Just'}", response.get(18).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='Malibu'}", response.get(19).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='Pepper'}", response.get(20).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='Potts'}", response.get(21).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='Rather'}", response.get(22).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='Shui'}", response.get(23).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='Very'}", response.get(24).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='Who'}", response.get(25).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='after'}", response.get(26).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='all'}", response.get(27).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='along'}", response.get(28).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='among'}", response.get(29).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='an'}", response.get(30).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='armor'}", response.get(31).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='around'}", response.get(32).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='artificial'}", response.get(33).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='bears'}", response.get(34).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='billionaire'}", response.get(35).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='butler'}", response.get(36).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='by'}", response.get(37).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='can'}", response.get(38).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='childhood'}", response.get(39).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='choice'}", response.get(40).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='cliff'}", response.get(41).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='coast'}", response.get(42).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='comes'}", response.get(43).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='created'}", response.get(44).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='decor'}", response.get(45).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='decorated'}", response.get(46).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='do'}", response.get(47).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='doesnt'}", response.get(48).toString());
        assertEquals("WordResponse {numberOfOccur=1, word='everyones'}", response.get(49).toString());
    }
}
