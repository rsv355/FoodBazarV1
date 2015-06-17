package foodbazar.webmyne.com.foodbazar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SplashActivity extends Activity {



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);


		Typeface fonts = Typeface.createFromAsset(getAssets(), "fonts/otttirial.ttf");

		Shimmer.ShimmerTextView txtFoodBazar = (Shimmer.ShimmerTextView)findViewById(R.id.txt_splash);

		txtFoodBazar.setTypeface(fonts);
		//txtFoodBazar = (TextView)findViewById(R.id.txt_splash);
		txtFoodBazar.setText("FOOD BAAZAR");
		Shimmer.Shimmer shimmer = new Shimmer.Shimmer();
		shimmer.start(txtFoodBazar);


		Thread background = new Thread() {
			public void run() {

				try {
					// Thread will sleep for 5 seconds
					sleep(5*1000);

					// After 5 seconds redirect to another intent
					Intent i=new Intent(getBaseContext(),HomeScreen.class);
					startActivity(i);

					//Remove activity
					finish();

				} catch (Exception e) {

				}
			}
		};

		// start thread
		background.start();




//		txtFoodBazar.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//
//				Intent it = new Intent(getApplicationContext(), MainActivity.class);
//				startActivity(it);
//
//			}
//		});

	}

	@Override
	protected void onDestroy() {

		super.onDestroy();

	}

}
