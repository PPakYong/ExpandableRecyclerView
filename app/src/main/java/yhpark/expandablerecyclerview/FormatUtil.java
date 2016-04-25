package yhpark.expandablerecyclerview;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by YHPark on 2016-04-25.
 */
public class FormatUtil {
    public static boolean isNullorEmpty(String targetString)
    {
        if(targetString == null || targetString.trim().equals("") || targetString.trim().equals("null"))
            return true;

        return false;
    }

    public static boolean isNullorEmpty(String targetString, boolean enableNull)
    {
        if(enableNull) {
            if(targetString == null || targetString.trim().equals(""))
                return true;
        } else {
            if(targetString == null || targetString.trim().equals("") || targetString.trim().equals("null"))
                return true;
        }


        return false;
    }

    public static boolean isNullorEmpty(JSONObject jsonObject)
    {
        if(jsonObject == null || jsonObject.length() == 0)
            return true;

        return false;
    }

    public static boolean isNullorEmpty(JSONArray jsonArray)
    {
        if(jsonArray == null || jsonArray.length() == 0)
            return true;

        return false;
    }
}
