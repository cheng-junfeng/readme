package com.readme.config;

import android.text.TextUtils;

public enum Types {
    text,
    image,
    video,
    audio,
    pdf;

    public static Types value(String type){
        if(TextUtils.isEmpty(type)){
            return text;
        }

        if(type.startsWith(text.name())){
            return text;
        }else if(type.contains(image.name())){
            return image;
        }else if(type.contains(video.name())){
            return video;
        }else if(type.contains(audio.name())){
            return audio;
        }else if(type.contains(pdf.name())){
            return pdf;
        }
        return text;
    }
}
