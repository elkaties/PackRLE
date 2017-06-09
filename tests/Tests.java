import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class Tests {
    private void assertFileContent(File file, String expectedContent) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String content = bufferedReader.lines().collect(Collectors.joining("\n"));
        assertEquals(expectedContent, content);
    }

    private final String
            inputOne = "input/inputEncode1.txt",
            inputTwo = "input/inputEncode2.txt",
            inputThree = "input/inputDecode1.txt",
            inputFour = "input/inputDecode2.txt",
            inputFive = "input/inputDecode3.txt";

    private final String
            outputOfEncode1 = "out/outputOfEncode1.txt",
            outputOfEncode2 = "outinputEncode2.txt",
            outputOfDecode1 = "out/outputDecode2.txt",
            outputOfDecode2 = "outinputDecode2.txt",
            outputOfDecode3 = "out/outputDecode3.txt";

    @Test
    public void testEncode() throws IOException {
        FileHandle fileHandle = new FileEncoder();
        fileHandle.pack(inputOne, outputOfEncode1);
        assertFileContent(new File(outputOfEncode1),
                "asndkjndsa87jabd\n" +
                        "asdlfmksadnfknJJHJFJFHJK\n" +
                        "K[7]alkdlkasld\n" +
                        "IOHEWIUNRJKjJJJJRRREEW[28]");
    }

    @Test
    public void testEncode2() throws IOException {
        FileHandle fileHandle = new FileEncoder();
        fileHandle.pack(inputTwo, "");
        assertFileContent(new File(outputOfEncode2),
                "weqweqwe[5]\n" +
                        "2[5]3333[[8]\n" +
                        "][5]\n" +
                        "qwwqwqwqksdsdfff\n" +
                        "3[8]");
    }

    @Test
    public void testDecode() throws IOException {
        FileHandle fileHandle = new FileDecoder();
        fileHandle.pack(inputThree, outputOfDecode1);
        assertFileContent(new File(outputOfDecode1),
                "Y9D5daw\n" +
                        "s2222222222\n" +
                        "sd{[[sd]\n" +
                        "asd[\n" +
                        "]sad[324[s]");
    }

    @Test
    public void testDecode2() throws IOException {
        FileHandle fileHandle = new FileDecoder();
        fileHandle.pack(inputFour, "");
        assertFileContent(new File(outputOfDecode2),
                "[][][][][[[]\n" +
                        "[]sd[]dsf[]]\n" +
                        "39irow[[p]2rp2[3\n" +
                        "[[[[[");
    }

    /*
    В ЭТОМ ТЕСТЕ ПРОВЕРЯЕМ РАЗМНОЖАЕТ ЛИ ОН СИМВОЛ, ЕСЛИ В
    СКОБКАХ УКАЗАНО ЧИСЛО, МЕНЬШЕ ИЛИ РАВНОЕ 4 (MIN_SYMBOLS)
     */

    @Test
    public void testDecode3() throws IOException {
        FileHandle fileHandle = new FileDecoder();
        fileHandle.pack(inputFive, outputOfDecode3);
        assertFileContent(new File(outputOfDecode3),
                "[][]][][\n" +
                        "pp[[3]\n" +
                        "[]][4]4[4[]\n" +
                        "[]]3]][4[]\n" +
                        "adweooooo\n" +
                        "weew[4]");
    }
}
