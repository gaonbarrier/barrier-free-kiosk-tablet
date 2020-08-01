package io.github.gaonbarrier.easykiosk.tablet.menu;
import android.widget.ImageView;

public class Element {
    private String Name;
    private ImageView image;
    //ImageView를 쓸지 안쓸지는 모른다. 이건 전적으로 프론트의 판단에 달려있다.

    public String getName() { return Name; }
    public void setName(String name) { Name = name; }
    public ImageView getImage() { return image; }
    public void setImage(ImageView image) { this.image = image; }
}
