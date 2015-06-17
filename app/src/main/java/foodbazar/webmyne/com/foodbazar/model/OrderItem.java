package foodbazar.webmyne.com.foodbazar.model;

/**
 * Created by Android on 28-04-2015.
 */
public class OrderItem {

//    @SerializedName("FoodDietId")
    public String FoodDietId;
//    @SerializedName("MenuItem")
    public String MenuItem;

    public String MenuItemName;
    public String MenuItemTaste;

//    @SerializedName("MenuItemQuantity")
    public String MenuItemQuantity;

    public String ItemPrice;

    public OrderItem(String foodDietId, String menuItem, String menuItemName, String menuItemTaste, String menuItemQuantity, String itemPrice) {
        FoodDietId = foodDietId;
        MenuItem = menuItem;
        MenuItemName = menuItemName;
        MenuItemTaste = menuItemTaste;
        MenuItemQuantity = menuItemQuantity;
        ItemPrice = itemPrice;
    }


    //    public OrderItem(String foodDietId, String menuItem, String menuItemName, String menuItemQuantity, String itemPrice) {
//        FoodDietId = foodDietId;
//        MenuItem = menuItem;
//        MenuItemName = menuItemName;
//        MenuItemQuantity = menuItemQuantity;
//        ItemPrice = itemPrice;
//    }
}
