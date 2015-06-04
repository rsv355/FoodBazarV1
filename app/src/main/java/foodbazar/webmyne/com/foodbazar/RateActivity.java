package foodbazar.webmyne.com.foodbazar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

public class RateActivity extends Activity {



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_rate_this);


		Typeface fonts = Typeface.createFromAsset(getAssets(), "fonts/otttirial.ttf");

		Shimmer.ShimmerTextView txtFoodBazar = (Shimmer.ShimmerTextView)findViewById(R.id.txt_splash);

		txtFoodBazar.setTypeface(fonts);
		//txtFoodBazar = (TextView)findViewById(R.id.txt_splash);
		txtFoodBazar.setText("Thank You");
		Shimmer.Shimmer shimmer = new Shimmer.Shimmer();
		shimmer.start(txtFoodBazar);







		txtFoodBazar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent it = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(it);
			}
		});

	}

	@Override
	protected void onDestroy() {

		super.onDestroy();

	}

}
