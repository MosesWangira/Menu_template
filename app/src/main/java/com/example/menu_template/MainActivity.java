package com.example.menu_template;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    Dialog custom_pop_up_dialogue;

    ActionMode mActionMode;

    private ActionMode.Callback mActionModeCallBack = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.action_mode_contexual_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.action_context_menu_one:
                    Toast.makeText(MainActivity.this, "action context one clicked", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                case R.id.action_context_menu_two:
                    Toast.makeText(MainActivity.this, "action context two clicked", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                case R.id.action_context_menu_three:
                    Toast.makeText(MainActivity.this, "action context three clicked", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                    default:
                        return  false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView tv = findViewById(R.id.floating_context_text);
        this.registerForContextMenu(tv);

        custom_pop_up_dialogue = new Dialog(this);

        /**
         * Button for action mode contexual menu
         * */
        Button cm = findViewById(R.id.action_mode_contexual);
        cm.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(mActionMode != null){
                    return false;
                }
                mActionMode = startActionMode(mActionModeCallBack);
                return true;
            }
        });
    }

    /**
     * inflate option menu on this activity
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Handle click events for each option menu created
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_one:
                //what is to be done when item one is clicked
                Toast.makeText(this, "option menu item one", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item_two:
                ////what is to be done when item one is clicked
                Toast.makeText(this, "option menu item two", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * override onCreateContextMenu for floating context menu
     * on long press its menu items are displayed
     * */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.floating_context_menu, menu);
    }

    /**
     * onContextItemSelected enables handling of clicks of floating context that pops up on long press in floating context menu
     * */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.copy:
                Toast.makeText(this, "copying item", Toast.LENGTH_SHORT).show();
                break;
            case R.id.download:
                Toast.makeText(this, "Downloading", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share_link:
                Toast.makeText(this, "sharing link", Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

    public void showPopUpMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.pop_up_menu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.pop_up_one:
                Toast.makeText(this, "pop up one", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.pop_up_two:
                Toast.makeText(this, "pop up two", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.pop_up_three:
                Toast.makeText(this, "pop up three", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }

    public void showPopUpDialog(View view) {
        custom_pop_up_dialogue.setContentView(R.layout.custom_pop_up_dialogue);
        Button pay = custom_pop_up_dialogue.findViewById(R.id.pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                custom_pop_up_dialogue.dismiss();
            }
        });
        custom_pop_up_dialogue.show();
    }
}
