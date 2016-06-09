package com.fc.mydemo.activity.widget.actionbar;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import com.fc.mydemo.R;

public class ActionBarBaseActivity extends Activity implements View.OnClickListener {

    private ActionBar actionBar;
    private Button showActionbarButton;
    private Button hideActionbarButton;
    private Button toSubActionBarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar_base);

        showActionbarButton = (Button) findViewById(R.id.show_actionbar_btn);
        hideActionbarButton = (Button) findViewById(R.id.hide_actionbar_btn);
        toSubActionBarButton = (Button) findViewById(R.id.to_sub_action_bar);
        showActionbarButton.setOnClickListener(this);
        hideActionbarButton.setOnClickListener(this);
        toSubActionBarButton.setOnClickListener(this);
        actionBar = getActionBar();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.base_actionbar_menu, menu);
        return true;
    }

    /**
     * Actionbar的显示和隐藏
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show_actionbar_btn:
                actionBar.show();
                break;
            case R.id.hide_actionbar_btn:
                actionBar.hide();
                break;
            case R.id.to_sub_action_bar:
                startActivity(new Intent(this,IconActionBarActivity.class));
        }
    }
}
