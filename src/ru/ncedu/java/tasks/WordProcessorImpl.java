package ru.ncedu.java.tasks;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Templerock on 21.10.2017.
 */
public class WordProcessorImpl implements WordProcessor {
    private String text;

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public void setSource(String src) {
        if(src == null){
            throw new IllegalArgumentException();
        }   else {
            this.text = src;
        }
    }

    @Override
    public void setSourceFile(String srcFile) throws IOException {
        if(srcFile == null){
            throw new IllegalArgumentException();
        }   else {
            Path path = Paths.get(srcFile);
            Scanner scanner = new Scanner(path);
            String line = "";
            while(scanner.hasNextLine()){
                line = line + scanner.nextLine();
            }
            this.text = line;
        }
    }

    @Override
    public void setSource(FileInputStream fis) throws IOException {
        if (fis == null){
            throw new IllegalArgumentException();
        }   else {
            Scanner scanner = new Scanner(fis);
            String line = "";
            while(scanner.hasNextLine()){
                line = line + scanner.nextLine();
            }
            this.text = line;
        }
    }

    @Override
    public Set<String> wordsStartWith(String begin) {
        if(begin == null || begin.startsWith(" ")) {
            begin = "";
        }
        this.text = this.text.toLowerCase();
        begin = begin.toLowerCase();
        Set<String> set = new HashSet<String>();
        Pattern pattern;
        pattern = Pattern.compile("[ \\f\\n\\r\\t\\v]" + begin + "[^ \\f\\n\\r\\t\\v]*");
        Matcher matcher = pattern.matcher(this.text);
        while (matcher.find())
        {
            set.add(matcher.group(0).trim());
        }
        set.remove("");
        pattern = Pattern.compile("^" + begin + "[^ \\f\\n\\r\\t\\v]*");
        matcher = pattern.matcher(this.text);
        while (matcher.find())
        {
            set.add(matcher.group(0).trim());
        }
        set.remove("");
        return set;
    }
}
