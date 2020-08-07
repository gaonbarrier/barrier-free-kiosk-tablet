package io.github.gaonbarrier.easykiosk.tablet.cart;

public class Select{
    private String Name;
    private int price;
    private boolean isHot;
    private int amount;

    public Select(String Name, int price, boolean isHot, int amount){
        this.Name = Name;
        this.price = price;
        this.isHot = isHot;
        this.amount = amount;
    }
    public String getName() { return Name; }
    public void setName(String name) { Name = name; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public boolean getisHot() { return isHot; }
    public void setisHot(boolean hot) { isHot = hot; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
    public void printAll(){
        System.out.println(getName() + " " + getAmount() + " " + getisHot() + " " + getPrice());
    }
}
