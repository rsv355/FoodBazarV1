package foodbazar.webmyne.com.foodbazar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import foodbazar.webmyne.com.foodbazar.fragments.CitySelectionFragment;
import foodbazar.webmyne.com.foodbazar.fragments.HotelListFragment;
import it.neokree.googlenavigationdrawer.GAccount;
import it.neokree.googlenavigationdrawer.GAccountListener;
import it.neokree.googlenavigationdrawer.GSection;
import it.neokree.googlenavigationdrawer.GoogleNavigationDrawer;

/**
 * Created by neokree on 17/12/14.
 */
public class MainActivity extends GoogleNavigationDrawer implements GAccountListener{

    GAccount account;
    GSection CitySelection, HotelSelection, ContactUs,History,SignIn,settingsSection;

    @Override
    public void init(Bundle savedInstanceState) {

        account = new GAccount("NeoKree","neokree@gmail.com",new ColorDrawable(Color.parseColor("#9e9e9e")),this.getResources().getDrawable(R.drawable.bamboo));
        this.addAccount(account);

        this.setAccountListener(this);

        // create sections
        CitySelection = this.newSection("Select Location", this.getResources().getDrawable(R.drawable.ic_home), new CitySelectionFragment());
        HotelSelection = this.newSection("Select Hotel",this.getResources().getDrawable(R.drawable.ic_people),new HotelListFragment()).setSectionColor(Color.parseColor("#80ffffff"));
        // recorder section with icon and 10 notifications
        ContactUs = this.newSection("Contact Us",this.getResources().getDrawable(R.drawable.ic_photos),new FragmentIndex());
        // night section with icon, section color and notifications
        History = this.newSection("History Page", this.getResources().getDrawable(R.drawable.ic_pages), new FragmentIndex());
        // night section with section color
        SignIn = this.newSection("Sign In Page",this.getResources().getDrawable(R.drawable.ic_photos), new FragmentIndex()).setSectionColor(Color.parseColor("#80ffffff"));

        Intent i = new Intent(this,Settings.class);
        settingsSection = this.newSection("Settings",this.getResources().getDrawable(R.drawable.ic_settings_black_24dp),i);

        // add your sections to the drawer
        this.addSection(CitySelection);
        this.addSection(HotelSelection);
        this.addSection(ContactUs);
        this.addSection(History);
        this.addSection(SignIn);

        this.addDivisor();
//        this.addSection(recorder);
//        this.addSection(night);
//        this.addDivisor();
//        this.addSection(last);
        this.addBottomSection(settingsSection);

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
                        Toast.makeText(getApplicationContext(), "Loaded 'from web' user image", Toast.LENGTH_SHORT).show();
                    }
                });
                //Log.w("PHOTO","user account photo setted");


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });
}
