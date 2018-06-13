package ru.ncedu.java.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Templerock on 22.10.2017.
 */
public class CheckerImpl implements Checker {
    @Override
    public Pattern getPLSQLNamesPattern() {
        Pattern pattern = Pattern.compile("[A-Za-z][A-Za-z0-9_$]{0,29}");
        return pattern;
    }

    @Override
    public Pattern getHrefURLPattern() {
        Pattern pattern = Pattern.compile("<([ \\t\\n\\r\\f])*[Aa][ ([ \\t\\n\\r\\f])*]*[hH][Rr][Ee][Ff]([ \\t\\n\\r\\f])*=([ \\t\\n\\r\\f])*(([^\\\\s>\\\"]+)|(\\\"([^\\\"])*\\\"))([ \\t\\n\\r\\f])*/?>");
        return pattern;
    }

    @Override
    public Pattern getEMailPattern() {
        Pattern pattern = Pattern.compile("[A-Za-z0-9][A-Za-z0-9_.-]{0,19}[A-Za-z0-9]{1,1}@" +
                "([A-Za-z0-9][A-Za-z0-9-]+[A-Za-z0-9]\\.)+(ru|com|net|org)$");
        return pattern;
    }

    @Override
    public boolean checkAccordance(String inputString, Pattern pattern) throws IllegalArgumentException {
        if(inputString == null && pattern == null){
            return true;
        }
        if(pattern == null){
            throw new IllegalArgumentException();
        }
        if(inputString == null){
            throw new IllegalArgumentException();
        }
        Matcher matcher = pattern.matcher(inputString);
        return matcher.matches();
    }

    @Override
    public List<String> fetchAllTemplates(StringBuffer inputString, Pattern pattern) throws IllegalArgumentException {
        if(pattern == null || inputString == null)
            throw new IllegalArgumentException();

        List<String> list = new ArrayList<String>();
        Matcher matcher = pattern.matcher(inputString);

        while(matcher.find())
            list.add(matcher.group());

        return list;
    }
}
