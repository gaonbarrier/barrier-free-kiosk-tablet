package io.github.gaonbarrier.easykiosk.tablet.Data;

public class Element {
    private String Name;
    private String image;
    //ImageView를 쓸지 안쓸지는 모른다. 이건 전적으로 프론트의 판단에 달려있다.

    public Element(String Name, String image){
        this.Name = Name;
        this.image = image;
    }

    public String getName() { return Name; }
    public void setName(String name) { Name = name; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}
