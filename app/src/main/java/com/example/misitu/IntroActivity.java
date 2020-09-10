package com.example.misitu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.chyrta.onboarder.OnboarderActivity;
import com.chyrta.onboarder.OnboarderPage;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends OnboarderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OnboarderPage onboarderPage1 = new OnboarderPage("Mchanga Kigajani Mwako", "Furahia Huduma bora kutoka Idara Ya misitu", R.drawable.diging);
        OnboarderPage onboarderPage2 = new OnboarderPage("Taarifa Za Mchanga", "Pata Taarfia za Manunuzi ya Mchanga Kupitia simu Yako", R.drawable.mypictwo);
        OnboarderPage onboarderPage3 = new OnboarderPage("Furahia Huduma zetu", "Fanya Manunuzi Ya Mchanga Popote Ulipo", R.drawable.formapps);

        onboarderPage1.setBackgroundColor(R.color.green);
        onboarderPage2.setBackgroundColor(R.color.green);
        onboarderPage3.setBackgroundColor(R.color.green);

        List<OnboarderPage> pages = new ArrayList<>();

        pages.add(onboarderPage1);
        pages.add(onboarderPage2);
        pages.add(onboarderPage3);

        for (OnboarderPage page : pages) {
            page.setTitleColor(R.color.black);
            page.setDescriptionColor(R.color.primary_text);
            //page.setMultilineDescriptionCentered(true);
        }

        setSkipButtonTitle("Skip");
        setFinishButtonTitle("Finish");

        setOnboardPagesReady(pages);

    }

    @Override
    public void onSkipButtonPressed() {
        super.onSkipButtonPressed();
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFinishButtonPressed() {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
        finish();
    }

}

