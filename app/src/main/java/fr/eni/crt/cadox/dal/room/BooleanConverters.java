package fr.eni.crt.cadox.dal.room;

import androidx.room.TypeConverter;

import java.time.LocalDate;

public class BooleanConverters
{
    @TypeConverter
    public static Boolean strToBool(String value)
    {
        return value == null ? null : Boolean.valueOf(value);
    }

    @TypeConverter
    public static String boolToStr(Boolean value)
    {
        return value == null ? null : value.toString();
    }
}
