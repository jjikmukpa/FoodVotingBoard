package com.jjikmukpa.project.post.model;

public class Option {

    private String image1Path;
    private String image2Path;

    public Option(String image1Path, String image2Path) {
        this.image1Path = image1Path;
        this.image2Path = image2Path;
    }

    public String getImage1() {
        return image1Path;
    }

    public String getImage2() {
        return image2Path;
    }


}
