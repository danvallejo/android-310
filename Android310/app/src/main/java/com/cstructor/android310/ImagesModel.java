package com.cstructor.android310;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImagesModel {

    @SerializedName("result_count")
    public int resultCount;

    public List<Image> images;

    public class Image {
        public String id;

        public String title;

        @SerializedName("display_sizes")
        public DisplaySize[] displaySizes;
    }

    public class DisplaySize {
        @SerializedName("is_watermarked")
        public Boolean isWatermarked;

        public String name;
        public String uri;
    }
}
