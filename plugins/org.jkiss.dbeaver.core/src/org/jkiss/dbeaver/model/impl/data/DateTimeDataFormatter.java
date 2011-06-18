/*
 * Copyright (c) 2011, Serge Rieder and others. All Rights Reserved.
 */

package org.jkiss.dbeaver.model.impl.data;

import org.jkiss.utils.CommonUtils;
import org.jkiss.dbeaver.model.data.DBDDataFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;

public class DateTimeDataFormatter implements DBDDataFormatter {

    public static final String PROP_PATTERN = "pattern";

    private DateFormat dateFormat;

    public void init(Locale locale, Map<Object, Object> properties)
    {
        dateFormat = new SimpleDateFormat(
            CommonUtils.toString(properties.get(PROP_PATTERN)),
            locale);
    }

    public String formatValue(Object value)
    {
        return value == null ? null : dateFormat.format(value);
    }

    public Object parseValue(String value) throws ParseException
    {
        return dateFormat.parse(value);
    }

}
