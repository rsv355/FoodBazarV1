package foodbazar.webmyne.com.foodbazar.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android on 27-04-2015.
 */
public class Hotel {

    @SerializedName("AreaName")
    public String AreaName;
    @SerializedName("City")
    public int City;
    @SerializedName("CityName")
    public String CityName;
    @SerializedName("DeliveryFee")
    public String DeliveryFee;
    @SerializedName("DeliveryTime")
    public String DeliveryTime;
    @SerializedName("Description")
    public String Description;
    @SerializedName("EmailId")
    public String EmailId;
    @SerializedName("HotelId")
    public int HotelId;
    @SerializedName("HotelName")
    public String HotelName;
    @SerializedName("Logo")
    public String Logo;
    @SerializedName("LogoPath")
    public String LogoPath;
    @SerializedName("MinDeliveryAmount")
    public String MinDeliveryAmount;
    @SerializedName("PhoneNumber")
    public String PhoneNumber;
    @SerializedName("PickUpTime")
    public String PickUpTime;

    @SerializedName("State")
    public String State;
    @SerializedName("StreetAddress")
    public String StreetAddress;
    @SerializedName("VatTax")
    public String VatTax;
}
