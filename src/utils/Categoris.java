package utils;

import java.util.ArrayList;

public class Categoris {
    public String aClass;
    public ArrayList<String> subClasses;

    public Categoris(String aClass, ArrayList<String> subClasses) {
        this.aClass = aClass;
        this.subClasses = subClasses;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(aClass).append(": [");
        for (String subClass : subClasses) {
            stringBuilder.append(subClass + ",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
