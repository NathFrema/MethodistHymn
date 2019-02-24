package com.admin.methodisthymn.fontAdapter;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

public class FontCache {

    private static HashMap<String, Typeface> fontCache = new HashMap<>();

    public static Typeface getTypeFace(String fontName, Context context){
        Typeface typeface = fontCache.get(fontName);

        if(typeface == null){
            try
            {
                typeface = Typeface.createFromAsset(context.getAssets(),fontName);
            }catch (Exception e){
                e.printStackTrace();
            }
            fontCache.put(fontName,typeface);

        }
        return typeface;
    }
}
