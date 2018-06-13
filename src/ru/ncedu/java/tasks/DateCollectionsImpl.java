package ru.ncedu.java.tasks;

import java.text.ParseException;
import java.util.*;
import java.text.DateFormat;

/**
 * Created by Templerock on 19.09.2017.
 */
public class DateCollectionsImpl implements DateCollections {
    private int dateFormatStyle = DateFormat.MEDIUM;
    private Map<String, Element> map;
    //= new TreeMap<String,Element>();

    @Override
    public void setDateStyle(int dateStyle) {
        dateFormatStyle = dateStyle;
    }

    @Override
    public Calendar toCalendar(String dateString) throws ParseException {
        DateFormat dateFormat = DateFormat.getDateInstance(dateFormatStyle);
        Date date = dateFormat.parse(dateString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    @Override
    public String toString(Calendar date) {
        DateFormat dateFormat = DateFormat.getDateInstance(dateFormatStyle);
        return dateFormat.format(date.getTime());
    }

    @Override
    public void initMainMap(int elementsNumber, Calendar firstDate) {
        this.map = new TreeMap<String, Element>(new Comparator<String>(){
            private DateFormat dateFormat = DateFormat.getDateInstance(dateFormatStyle);
            @Override
            public int compare(String o1, String o2) {
                try {
                    Calendar c1 = toCalendar(o1);
                    Calendar c2 = toCalendar(o2);
                    return c1.compareTo(c2);
                } catch (ParseException e) {
                    return 0;
                }
            }
        });
        Random rand = new Random();
        for(int i = 0; i < elementsNumber; i++){
            Calendar dateToAdd = Calendar.getInstance();
            dateToAdd.setTime(firstDate.getTime());
            dateToAdd.add(Calendar.DAY_OF_MONTH, 110*i);
            this.map.put(toString(dateToAdd), new Element(dateToAdd, rand.nextInt(2000)));
        }
    }

    @Override
    public void setMainMap(Map<String, Element> map) {
        this.map = new HashMap<>();
    this.map.putAll(map);
    }

    @Override
    public Map<String, Element> getMainMap() {
        return map;
    }

    @Override
    public SortedMap<String, Element> getSortedSubMap() {
        Date cur = Calendar.getInstance().getTime();
        SortedMap<String, Element> ret = new TreeMap<String, Element>(new Comparator<String>(){
            DateFormat dateFormat = DateFormat.getDateInstance(dateFormatStyle);
            @Override
            public int compare(String o1, String o2) {
                try {
                    Calendar c1 = toCalendar(o1);
                    Calendar c2 = toCalendar(o2);
                    return c1.compareTo(c2);
                } catch (ParseException e) {
                    return 0;
                }
            }
        });
        Set<String> keys = getMainMap().keySet();
        Element tmp;
        for(String key: keys){
            tmp = getMainMap().get(key);
            if(tmp.getBirthDate().getTime().compareTo(cur) > 0)
                ret.put(key, tmp);
        }
        return ret;
    }

    @Override
    public List<Element> getMainList() {
        List<Element> ret = new LinkedList<>(getMainMap().values());
        Collections.sort(ret, new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
                Calendar c1 = o1.getBirthDate();
                Calendar c2 = o2.getBirthDate();
                return c1.compareTo(c2);
            }
        });
        return ret;
    }

    @Override
    public void sortList(List<Element> list) {
        Collections.sort(list, new Comparator<Element>(){
            private DateFormat dateFormat = DateFormat.getDateInstance(dateFormatStyle);
            @Override
            public int compare(Element o1, Element o2) {
                Calendar c1 = o1.getDeathDate();
                Calendar c2 = o2.getDeathDate();
                return c1.compareTo(c2);
            }
        });
    }

    @Override
    public void removeFromList(List<Element> list) {
        Iterator<Element> listiterator = list.iterator();
        List<Element> toDel = new ArrayList<Element>();
        Element temp;
        while(listiterator.hasNext()){
            temp = listiterator.next();
            int month = temp.getBirthDate().get(Calendar.MONTH);
            if( month == Calendar.DECEMBER || month == Calendar.JANUARY || month == Calendar.FEBRUARY){
                toDel.add(temp);
            }
        }
        for(Element elem: toDel){
            list.remove(elem);
        }
    }
}
