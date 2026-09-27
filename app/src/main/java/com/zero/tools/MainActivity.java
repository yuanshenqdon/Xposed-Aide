package com.zero.tools;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    private LinearLayout cardContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setSubtitle(isModuleActivated() ? R.string.xposed_activated : R.string.xposed_unactivated);
        }

        cardContainer = findViewById(R.id.card_container);

        addCard("com.cosmos.tools", "宇宙工具箱", "解锁VIP 需要登录才可以使用高级功能");
    }

    public static boolean isModuleActivated() {
        return false;
    }

    private void addCard(String pkg, String title, String content) {
        float density = getResources().getDisplayMetrics().density;
        int pad = (int) (16 * density);
        int margin = (int) (12 * density);

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        android.graphics.drawable.GradientDrawable bg = new android.graphics.drawable.GradientDrawable();
        bg.setColor(0xFFFFFFFF);
        bg.setCornerRadius(16 * density);
        card.setBackground(bg);
        card.setPadding(pad, pad, pad, pad);

        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        cardParams.bottomMargin = margin;
        card.setLayoutParams(cardParams);

        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);

        ImageView icon = new ImageView(this);
        LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(
                (int) (40 * density), (int) (40 * density));
        icon.setLayoutParams(iconParams);
        icon.setImageDrawable(getAppIconOrDefault(pkg));
        row.addView(icon);

        TextView tvTitle = new TextView(this);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        titleParams.leftMargin = margin;
        tvTitle.setLayoutParams(titleParams);
        tvTitle.setText(title);
        tvTitle.setTextSize(18);
        tvTitle.setTypeface(null, Typeface.BOLD);
        row.addView(tvTitle);

        card.addView(row);

        TextView tvContent = new TextView(this);
        LinearLayout.LayoutParams contentParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        contentParams.topMargin = margin;
        tvContent.setLayoutParams(contentParams);
        tvContent.setText(content);
        tvContent.setTextSize(14);
        card.addView(tvContent);

        cardContainer.addView(card);
    }

    private Drawable getAppIconOrDefault(String pkg) {
        try {
            return getPackageManager().getApplicationIcon(pkg);
        } catch (Throwable t) {
            return getDrawable(R.drawable.defaultlogo);
        }
    }
}