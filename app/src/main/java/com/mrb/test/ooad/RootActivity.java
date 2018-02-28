package com.mrb.test.ooad;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RootActivity extends AppCompatActivity {
    private DrawerLayout DL;
    private LinearLayout LL;
    protected NavigationView NV;
    protected int CurrentMenuItem = 0;
    //final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    //setSupportActionBar(toolbar);

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        DL = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_root, null);
        LL = (LinearLayout) DL.findViewById(R.id.root_layout);
        NV = (NavigationView)DL.findViewById(R.id.navigation_view);
        getLayoutInflater().inflate(layoutResID, LL, true);
        super.setContentView(DL);
        //toolbar = (Toolbar) findViewById(R.id.ToolBar);
        setUpNavigation();
    }



    private void setUpNavigation() {
        // Set navigation item selected listener

        SharedPreferences setting;
        setting = getSharedPreferences("user", MODE_PRIVATE);
        if(setting.getBoolean("LogIn", false)){
            NV.getMenu().findItem(R.id.logging).setTitle("登出");
            NV.getMenu().findItem(R.id.logging).setIcon(R.mipmap.logout);
        }
        else{
            NV.getMenu().findItem(R.id.logging).setTitle("登入");
            NV.getMenu().findItem(R.id.logging).setIcon(R.mipmap.login);
        }
        NV.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if(!(menuItem == NV.getMenu().getItem(CurrentMenuItem))) {//判斷使者者是否點擊當前畫面的項目，若不是，根據所按的項目做出分別的動作
                    switch (menuItem.getItemId()) {
                        case R.id.help:
                            Help();
                            break;
                        case R.id.settings:
                            break;
                        case R.id.logging:
                            SharedPreferences setting;
                            setting = getSharedPreferences("user", MODE_PRIVATE);
                            if(setting.getBoolean("LogIn", false)){
                                LogOut();
                            }
                            else{
                                Login();
                            }
                            break;
                        /*
                        case R.id.navItemOne:
                            Intent intent = new Intent();
                            intent.setClass(Navigation_BaseActivity.this, MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                            finish();
                            break;
                        case R.id.navItemAbout:
                            Intent intent2 = new Intent();
                            intent2.setClass(Navigation_BaseActivity.this, About.class);
                            startActivity(intent2);
                            overridePendingTransition(0, 0);
                            finish();
                            break;
                            */
                    }
                }
                else {
                    DL.closeDrawer(GravityCompat.START);
                }
                return false;
            }
        });
    }


    private void Help() {
        Toast.makeText(this, "關掉比較快", Toast.LENGTH_SHORT).show();
    }
    private void Login(){
        LayoutInflater factory = LayoutInflater.from(RootActivity.this);
        final View dialogView = factory.inflate(R.layout.login_alertdialog, null);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setView(dialogView);
        alertDialog.setPositiveButton("登入", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText user = (EditText)dialogView.findViewById(R.id.username);
                EditText passwd = (EditText)dialogView.findViewById(R.id.password);
                String userStr = user.getText().toString();
                String pwStr = passwd.getText().toString();
                boolean valid = true;
                if(valid){
                    SharedPreferences setting;
                    setting = getSharedPreferences("user", MODE_PRIVATE);
                    //Preference have NAME & PW
                    setting.edit()
                            .putString("NAME", String.valueOf(userStr))
                            .putString("PW", String.valueOf(pwStr))
                            .putBoolean("LogIn",true)
                            .apply();
                    Toast.makeText(RootActivity.this, "登入成功", Toast.LENGTH_LONG).show();
                    NV.getMenu().findItem(R.id.logging).setTitle("登出");
                    NV.getMenu().findItem(R.id.logging).setIcon(R.mipmap.logout);
                }else{
                    Toast.makeText(RootActivity.this, "登入失敗", Toast.LENGTH_LONG).show();
                }
            }
        });
        alertDialog.setNegativeButton("註冊", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                // TODO Intent to Register Page
            }
        });
        alertDialog.create().show();
    }
    private void LogOut(){
        final AlertDialog.Builder alertDialog =new AlertDialog.Builder(RootActivity.this);
        alertDialog.setTitle("Logout");
        alertDialog.setMessage("Are you sure you want to Logout?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences setting;
                setting = getSharedPreferences("user", MODE_PRIVATE);
                setting.edit()
                        .putString("NAME", null)
                        .putString("PW", null)
                        .putBoolean("LogIn",false)
                        .apply();
                Toast.makeText(RootActivity.this, "登出成功", Toast.LENGTH_LONG).show();
                NV.getMenu().findItem(R.id.logging).setTitle("登入");
                NV.getMenu().findItem(R.id.logging).setIcon(R.mipmap.login);
            }
        });
        alertDialog.setNegativeButton("No", null);
        alertDialog.create().show();

    }
}
