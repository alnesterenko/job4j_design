package ru.job4j.ood.srp.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class XmlCalendarAdapter extends XmlAdapter<String, Calendar> {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    @Override
    public Calendar unmarshal(String stringDate) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DATE_FORMAT.parse(stringDate));
        return cal;
    }

    @Override
    public String marshal(Calendar calendar) throws Exception {
        return DATE_FORMAT.format(calendar.getTime());
    }
}
