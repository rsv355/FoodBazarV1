package foodbazar.webmyne.com.foodbazar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import foodbazar.webmyne.com.foodbazar.fragments.CitySelectionFragment;
import foodbazar.webmyne.com.foodbazar.fragments.HotelListFragment;
import foodbazar.webmyne.com.foodbazar.model.LoginClass;
import foodbazar.webmyne.com.foodbazar.utils.PrefUtils;
import it.neokree.googlenavigationdrawer.GAccount;
import it.neokree.googlenavigationdrawer.GAccountListener;
import it.neokree.googlenavigationdrawer.GSection;
import it.neokree.googlenavigationdrawer.GoogleNavigationDrawer;

/**
 * Created by neokree on 17/12/14.
 */
public class MainActivity extends GoogleNavigationDrawer implements GAccountListener{

    GAccount account;
    GSection CitySelection, HotelSelection, ContactUs,History,SignIn,History1,Contact,settingsSection;

    LoginClass loginClass;




    @Override
    public void init(Bundle savedInstanceState) {




       

                loginClass = PrefUtils.getLogin(MainActivity.this);

        account = new GAccount("Your Name","Please Login Account",new ColorDrawable(Color.parseColor("#9e9e9e")),this.getResources().getDrawable(R.drawable.bamboo));
            this.addAccount(account);


//        android.app.ActionBar bar = getActionBar();
//        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DA4D4D")));

    //    String namee = loginClass.FName;



//        if (loginClass != null)
//        {
//            account = new GAccount(loginClass.FName,loginClass.LName+"@gmail.com",new ColorDrawable(Color.parseColor("#9e9e9e")),this.getResources().getDrawable(R.drawable.bamboo));
//            this.addAccount(account);
//        }
//
//        else {
//            account = new GAccount("Your Name","Please Login Account",new ColorDrawable(Color.parseColor("#9e9e9e")),this.getResources().getDrawable(R.drawable.bamboo));
//            this.addAccount(account);
//        }





        this.setAccountListener(this);

        // create sections
        CitySelection = this.newSection("Select Location", this.getResources().getDrawable(R.drawable.ic_home), new CitySelectionFragment()).setSectionColor(Color.parseColor("#DA4D4D"));
        HotelSelection = this.newSection("Select Hotel",this.getResources().getDrawable(R.drawable.ic_people),new HotelListFragment()).setSectionColor(Color.parseColor("#DA4D4D"));
        // recorder section with icon and 10 notifications
        ContactUs = this.newSection("Give Me Rate",this.getResources().getDrawable(R.drawable.ic_photos),new Intent(this, RateActivity.class)).setSectionColor(Color.parseColor("#DA4D4D"));
        // night section with icon, section color and notifications
        History = this.newSection("History Page", this.getResources().getDrawable(R.drawable.ic_pages), new Intent(this, HomeScreen.class)).setSectionColor(Color.parseColor("#DA4D4D"));
        // night section with section color
        SignIn = this.newSection("Sign In Page",this.getResources().getDrawable(R.drawable.ic_photos), new Intent(this, SignUpActivity.class)).setSectionColor(Color.parseColor("#DA4D4D"));

        History1 = this.newSection("User Wallet", this.getResources().getDrawable(R.drawable.ic_pages), new Intent(this, WalletActivity.class)).setSectionColor(Color.parseColor("#DA4D4D"));

        Contact = this.newSection("Contact Us", this.getResources().getDrawable(R.drawable.ic_pages),new Intent(this, ContactActivity.class)).setSectionColor(Color.parseColor("#DA4D4D"));

        Intent i = new Intent(this,Settings.class);
        settingsSection = this.newSection("Settings",this.getResources().getDrawable(R.drawable.ic_settings_black_24dp),i);

        // add your sections to the drawer
        this.addSection(CitySelection);
        this.addSection(HotelSelection);
        this.addSection(ContactUs);
        this.addSection(History);
        this.addSection(SignIn);
        this.addSection(History1);
        this.addSection(Contact);




     //   this.addDivisor();
//        this.addSection(recorder);
//        this.addSection(night);
//        this.addDivisor();
//        this.addSection(last);
    //    this.addBottomSection(settingsSection);

        // start thread
        t.start();

    }


    @Override
    public void onAccountOpening(GAccount account) {
        // open account activity or do what you want
    }

    // after 5 second (async task loading photo from website) change user photo
    Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
                account.setPhoto(getResources().getDrawable(R.drawable.photo));

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        notifyAccountDataChanged();
                      //  Toast.makeText(getApplicationContext(), "Loaded 'from web' user image", Toast.LENGTH_SHORT).show();
                    }
                });
                //Log.w("PHOTO","user account photo setted");


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });
}
