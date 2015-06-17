package foodbazar.webmyne.com.foodbazar.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import foodbazar.webmyne.com.foodbazar.model.Balance;
import foodbazar.webmyne.com.foodbazar.model.History;
import foodbazar.webmyne.com.foodbazar.model.HotelMenuItem;
import foodbazar.webmyne.com.foodbazar.model.HotelsList;
import foodbazar.webmyne.com.foodbazar.model.HotelsMenu;
import foodbazar.webmyne.com.foodbazar.model.HotelsMenuList;
import foodbazar.webmyne.com.foodbazar.model.LoginClass;
import foodbazar.webmyne.com.foodbazar.model.SubmitOrder;
import foodbazar.webmyne.com.foodbazar.model.UserOrder;


public class PrefUtils {

    public static void setArea(String area, Context ctx){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("selectedArea", area);
        editor.commit();
    }

    public static String getArea( Context ctx){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);

        String area=preferences.getString("selectedArea", "");
        return  area;
    }


    public static void setPosition(int position, Context ctx){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("selectedPosition", position);
        editor.commit();
    }

    public static int getPosition( Context ctx){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);

        int pos=preferences.getInt("selectedPosition", 0);
        return  pos;
    }


    public static void setHotelsList(HotelsList currentUser, Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "hotel_list_pref", 0);
        complexPreferences.putObject("hotel_list_pref_value", currentUser);
        complexPreferences.commit();
    }

    public static HotelsList getHotelsList(Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "hotel_list_pref", 0);
        HotelsList currentUser = complexPreferences.getObject("hotel_list_pref_value", HotelsList.class);
        return currentUser;
    }









    public static void setHotelsMenu(HotelsMenuList currentUser, Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "hotel_menu_pref", 0);
        complexPreferences.putObject("hotel_menu_pref_value", currentUser);
        complexPreferences.commit();
    }

    public static void clearCurrentUser( Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "user_prefs", 0);
        complexPreferences.clearObject();
        complexPreferences.commit();
    }


    public static HotelsMenuList getHotelsMenu(Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "hotel_menu_pref", 0);
        HotelsMenuList currentUser = complexPreferences.getObject("hotel_menu_pref_value", HotelsMenuList.class);
        return currentUser;
    }





    public static void setHotelsMenuItems(HotelsMenu currentUser, Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "hotel_menu_items_pref", 0);
        complexPreferences.putObject("hotel_menu_items_pref_value", currentUser);
        complexPreferences.commit();
    }

    public static HotelsMenu getHotelsMenuItems(Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "hotel_menu_items_pref", 0);
        HotelsMenu currentUser = complexPreferences.getObject("hotel_menu_items_pref_value", HotelsMenu.class);
        return currentUser;
    }



    public static void setMenuItemDetail(HotelMenuItem currentUser, Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "hotel_menu_items_pref", 0);
        complexPreferences.putObject("hotel_menu_items_pref_value", currentUser);
        complexPreferences.commit();
    }

    public static HotelMenuItem getMenuItemDetail(Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "hotel_menu_items_pref", 0);
        HotelMenuItem currentUser = complexPreferences.getObject("hotel_menu_items_pref_value", HotelMenuItem.class);
        return currentUser;
    }


    public static void AddItemToCart(SubmitOrder currentUser, Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "cart_pref", 0);
        complexPreferences.putObject("cart_pref_value", currentUser);
        complexPreferences.commit();
    }

    public static SubmitOrder getCartItems(Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "cart_pref", 0);
        SubmitOrder currentUser = complexPreferences.getObject("cart_pref_value", SubmitOrder.class);
        return currentUser;
    }

    public static void clearCart( Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "cart_pref", 0);
        complexPreferences.clearObject();
        complexPreferences.commit();
    }

    public static void clearLogin( Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "login_pref", 0);
        complexPreferences.clearObject();
        complexPreferences.commit();
    }

    public static void setLogin(LoginClass currentUser, Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "login_pref", 0);
        complexPreferences.putObject("login_value", currentUser);
        complexPreferences.commit();
    }

    public static LoginClass getLogin(Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "login_pref", 0);
        LoginClass currentUser = complexPreferences.getObject("login_value", LoginClass.class);
        return currentUser;
    }

    public static void setBalance(Balance currentUser, Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "balance_pref", 0);
        complexPreferences.putObject("balance_value", currentUser);
        complexPreferences.commit();
    }

    public static Balance getBalance(Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "balance_pref", 0);
        Balance Balance = complexPreferences.getObject("balance_value", Balance.class);
        return Balance;
    }


    public static void setHistory(History currentUser, Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "order_pref", 0);
        complexPreferences.putObject("order_value", currentUser);
        complexPreferences.commit();
    }

    public static History getHistory(Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "order_pref", 0);
        History order = complexPreferences.getObject("order_value", History.class);
        return order;
    }

}
