package foodbazar.webmyne.com.foodbazar.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Android on 28-04-2015.
 */
public class History {

    @SerializedName("ResponseCode")
    public String ResponseCode;

    @SerializedName("ResponseMsg")
    public String ResponseMsg;

    @SerializedName("lstUSerOrdered")
    public ArrayList<UserOrder> UserOrderArrayList;




}
