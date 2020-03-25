package ak.javaprogramming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class JavaBasics extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;

    private static final String TAG = "JavaBasics";
    private ImageButton button;

    private Context mContext;

    ArrayList<String> titleArrayList;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_javabasics);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-8991826004356667/1049570944");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); //Fullscreen

        button = (ImageButton) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          if (mInterstitialAd.isLoaded()) {
                                              mInterstitialAd.show();
                                          } else {
                                              Log.d("TAG", "The interstitial wasn't loaded yet.");
                                          }
                                          openDialog();
                                      }
                                  });


                mContext = JavaBasics.this;

        titleArrayList = new ArrayList<String>();
        titleArrayList.add(Constants.OVERVIEW);
        titleArrayList.add(Constants.ENVIRONMENT_SETUP);
        titleArrayList.add(Constants.BASICS_SYNTAX);
        titleArrayList.add(Constants.OBJECT_AND_CLASSES);
        titleArrayList.add(Constants.CONSTRUCTORS);
        titleArrayList.add(Constants.BASICS_DATATYPES);
        titleArrayList.add(Constants.VARIABLE_TYPES);
        titleArrayList.add(Constants.MODIFIER_TYPES);
        titleArrayList.add(Constants.BASICS_OPERATORS);
        titleArrayList.add(Constants.LOOP_CONTROL);
        titleArrayList.add(Constants.DECISION_MAKING);
        titleArrayList.add(Constants.NUMBERS_CLASS);
        titleArrayList.add(Constants.CHARACTER_CLASS);
        titleArrayList.add(Constants.STRINGS_CLASS);
        titleArrayList.add(Constants.ARRAYS);
        titleArrayList.add(Constants.DATE_AND_TIME);
        titleArrayList.add(Constants.REGULAR_EXPRESSIONS);
        titleArrayList.add(Constants.METHODS);
        titleArrayList.add(Constants.FILES_AND_IO);






        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        TitleAdapter adapter = new TitleAdapter(mContext, titleArrayList, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

                Intent desIntent = new Intent(mContext,DescriptionActivity.class);
                desIntent.putExtra("titles",titleArrayList.get(position));
                startActivity(desIntent);

                Toast.makeText(mContext,"Clicked "+titleArrayList.get(position),Toast.LENGTH_SHORT).show();

            }
        });

        mRecyclerView.setAdapter(adapter);

        }

    public void openDialog() {
        DialogBox dialogBox = new DialogBox();
        dialogBox.show(getSupportFragmentManager(),"dialog box");


    }
}
