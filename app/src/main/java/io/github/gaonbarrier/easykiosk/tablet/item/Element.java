package io.github.gaonbarrier.easykiosk.tablet.item;
import android.media.Image;
import android.widget.ImageView;

import java.util.*;

public class Element {
    private String Name;
    private ImageView image;

    public String getName() { return Name; }
    public void setName(String name) { Name = name; }
    public ImageView getImage() { return image; }
    public void setImage(ImageView image) { this.image = image; }
}
