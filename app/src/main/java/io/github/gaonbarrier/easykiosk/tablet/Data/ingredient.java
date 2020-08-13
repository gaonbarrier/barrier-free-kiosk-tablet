package io.github.gaonbarrier.easykiosk.tablet.Data;

import android.media.Image;

public class ingredient {
    private String name;
    private Image image;

    public ingredient(String name, Image image){
        this.name = name;
        this.image = image;
    }

    public String getItemName(){
        return name;
    }
    public void setItemName(String name){
        this.name = name;
    }
    public Image getImage(){return image;}
    public void setImage(Image image){this.image = image;}
}
