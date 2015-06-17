package foodbazar.webmyne.com.foodbazar.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Android on 01-05-2015.
 */
public class UserOrder {

    @SerializedName("CustomerFirstName")
    public String CustomerFirstName;

    @SerializedName("CustomerLastName")
    public String CustomerLastName;

    @SerializedName("DeliveryArea")
    public String DeliveryArea;

    @SerializedName("DeliveryCity")
    public String DeliveryCity;

    @SerializedName("DeliveryCityName")
    public String DeliveryCityName;

    @SerializedName("DeliveryCountry")
    public String DeliveryCountry;

    @SerializedName("DeliveryCountryName")
    public String DeliveryCountryName;

    @SerializedName("DeliveryState")
    public String DeliveryState;

    @SerializedName("DeliveryStateName")
    public String DeliveryStateName;

    @SerializedName("Userid")
    public String Userid;

    @SerializedName("CreatedOn")
    public String CreatedOn;

    @SerializedName("DiscountPercent")
    public String DiscountPercent;

    @SerializedName("DiscountPrice")
    public String DiscountPrice;

    @SerializedName("HotelId")
    public String HotelId;

    @SerializedName("HotelName")
    public String HotelName;

    @SerializedName("OrderBy")
    public String OrderBy;

    @SerializedName("OrderDesc")
    public String OrderDesc;
    @SerializedName("OrderId")
    public String OrderId;
    @SerializedName("OrderStatus")
    public String OrderStatus;
    @SerializedName("PaymentTypeId")
    public String PaymentTypeId;
    @SerializedName("PriceToPay")
    public String PriceToPay;
    @SerializedName("Tax")
    public String Tax;
    @SerializedName("TotalPrice")
    public String TotalPrice;

    @SerializedName("TotalQty")
    public String TotalQty;

    @SerializedName("UpdatedOn")
    public String UpdatedOn;

//    @SerializedName("lstMenuItem")
//    public ArrayList<lstMenuItem> lstMenuItem;
//
//    @SerializedName("lstOrderItem")
//    public ArrayList<lstOrderItem> lstOrderItem;





}
