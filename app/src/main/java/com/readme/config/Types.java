package com.readme.config;

import android.text.TextUtils;

public enum Types {
    text,
    image,
    video,
    audio,
    pdf,
    msword;

    public static Types value(String type){
        if(TextUtils.isEmpty(type)){
            return text;
        }

        if(type.startsWith(text.name())){
            return text;
        }else if(type.startsWith(image.name())){
            return image;
        }else if(type.startsWith(video.name())){
            return video;
        }else if(type.startsWith(audio.name())){
            return audio;
        }else if(type.startsWith("application/pdf")){
            return pdf;
        }else if(type.startsWith("application/msword")){
            return msword;
        }
        return text;
    }
}
