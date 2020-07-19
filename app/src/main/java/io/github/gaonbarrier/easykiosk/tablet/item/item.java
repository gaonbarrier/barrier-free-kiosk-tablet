package io.github.gaonbarrier.easykiosk.tablet.item;

import android.media.Image;

public class item {
    private String name;
    private Image image;
    private int hotPrice;
    private int coldPrice;

    public String getItemName(){ return name; }
    public void setItemName(String name){
        this.name = name;
    }
    public Image getImage(){return image;}
    public void setImage(Image image){this.image = image;}
    public int getHotPrice(){ return hotPrice;}
    public void setHotPrice(int hotPrice){ this.hotPrice = hotPrice;}
    public int getColdPrice(){ return coldPrice;}
    public void setColdPrice(int hotPrice){ this.coldPrice = coldPrice;}
}
