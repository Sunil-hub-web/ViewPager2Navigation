package com.example.viewpager2navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    OnBoardingAdapter onBoardingAdapter;
    LinearLayout linearLayout_indactor;
    ViewPager2 viewPager_OnBoarding;
    MaterialButton materialButton;
    ArrayList<OnBoardingItem> onBoarding = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout_indactor = findViewById(R.id.linearLayout_indactor);
        viewPager_OnBoarding = findViewById(R.id.viewPager_OnBorading);
        materialButton = findViewById(R.id.materialButton);

        setupOnboardingItems();

        onBoardingAdapter = new OnBoardingAdapter(onBoarding);
        viewPager_OnBoarding.setAdapter(onBoardingAdapter);

        setupOnboardingIndicators();
        setCurrentOnboardingIndicators(0);

        viewPager_OnBoarding.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicators(position);
            }
        });

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(viewPager_OnBoarding.getCurrentItem() + 1 < onBoardingAdapter.getItemCount()){

                    viewPager_OnBoarding.setCurrentItem(viewPager_OnBoarding.getCurrentItem() + 1);

                }else{

                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    finish();
                }
            }
        });
    }

    private void setupOnboardingItems(){

        onBoarding.add(new OnBoardingItem(R.drawable.hero_img,"Pay Your Bill Online","your Delivery Ride On The Way"));
        onBoarding.add(new OnBoardingItem(R.drawable.cardboard_boxes,"Pay Your Bill Online","your Delivery Ride On The Way"));
        onBoarding.add(new OnBoardingItem(R.drawable.eait_together,"Pay Your Bill Online","your Delivery Ride On The Way"));
        onBoarding.add(new OnBoardingItem(R.drawable.food_delivery,"Pay Your Bill Online","your Delivery Ride On The Way"));
        onBoarding.add(new OnBoardingItem(R.drawable.online_payment,"Pay Your Bill Online","your Delivery is Done Payayment"));
        onBoarding.add(new OnBoardingItem(R.drawable.pay_online,"Pay Your Bill Online","your Delivery is Done Payayment"));
        onBoarding.add(new OnBoardingItem(R.drawable.payonline,"Pay Your Bill Online","your Delivery is Done Payayment"));
    }

    private void setupOnboardingIndicators(){

        ImageView[] indicators = new ImageView[onBoardingAdapter.getItemCount()];

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(8,0,8,0);

        for(int i=0;i<indicators.length;i++){

            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onborading_indicator_inactive));

            indicators[i].setLayoutParams(layoutParams);
            linearLayout_indactor.addView(indicators[i]);

        }
    }

    private void setCurrentOnboardingIndicators(int index){

        int childCount = linearLayout_indactor.getChildCount();

        for(int i=0;i<childCount;i++){

            ImageView imageView = (ImageView) linearLayout_indactor.getChildAt(i);

            if(i == index){

                imageView.setImageDrawable(ContextCompat.getDrawable(
                        getApplicationContext(),
                        R.drawable.onborading_indicator_active));
            }else{

                imageView.setImageDrawable(ContextCompat.getDrawable(
                        getApplicationContext(),
                        R.drawable.onborading_indicator_inactive));
            }
        }

        if(index == onBoardingAdapter.getItemCount() - 1){
            materialButton.setText("Start");
        }else{
            materialButton.setText("Next");
        }
    }
}