package com.admin.methodisthymn.fontAdapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewCompat;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.DialogInterface.OnClickListener;
import android.app.AlertDialog.Builder;

import com.admin.methodisthymn.R;

public class App_Utils {

    private Activity activity;

    public App_Utils(Activity activity) {
        this.activity = activity;
    }

    class C04009 implements OnClickListener {
        public void onClick(DialogInterface dialogInterface, int i) {
        }

        C04009() {
        }
    }

    class C03943 implements OnClickListener {
        C03943() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    }

    class C03954 implements OnClickListener {
        C03954() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    }

    public void about() {
        View textView = new TextView(this.activity);
        ((TextView) textView).setText(R.string.about_body);
        textView.setBackgroundColor(-1);
        ((TextView) textView).setLineSpacing(0.0f, 1.0f);
        textView.setPadding(20, 20, 20, 20);
        //font(textView);
        ((TextView) textView).setTextColor(ViewCompat.MEASURED_STATE_MASK);
        ((TextView) textView).setMovementMethod(LinkMovementMethod.getInstance());
        Builder builder = new Builder(this.activity);
        builder.setTitle(R.string.about_title);
        builder.setView(textView);
        builder.create();
        builder.setPositiveButton(R.string.about_dismiss_button, new C03954());
        builder.show();
    }

    public void contact() {
        View textView = new TextView(this.activity);
        ((TextView) textView).setText(R.string.cabout_body);
        textView.setPadding(20, 20, 20, 20);
        textView.setBackgroundColor(0x55FF0000);
        //font(textView);
        ((TextView) textView).setTextColor(ViewCompat.MEASURED_STATE_MASK);
        ((TextView) textView).setMovementMethod(LinkMovementMethod.getInstance());
        Builder builder = new Builder(this.activity);
        builder.setTitle(R.string.about_title);
        builder.setView(textView);
        builder.create();
        builder.setPositiveButton(R.string.about_dismiss_button, new C03943());
        builder.show();
    }

    public void apr() {
        Builder builder = new Builder(activity);
        builder.setTitle(R.string.about_title);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.about_dismiss_button, new C04009());
        builder.setMessage(R.string.aprabout_body);
        builder.create().show();
    }

    public void exit() {
        final Dialog dialog = new Dialog(this.activity);
        dialog.requestWindowFeature(1);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.exit_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        TextView textView = (TextView) dialog.findViewById(R.id.txt_file_path);
        //font(textView);
        textView.setText(R.string.about_title);
        textView = (TextView) dialog.findViewById(R.id.ask);
        //font(textView);
        textView.setText(this.activity.getString(R.string.ask_exit));
        ((Button) dialog.findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        ((Button) dialog.findViewById(R.id.okay)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                App_Utils.this.activity.finish();
                //view = App_Utils.this.activity.getSharedPreferences("MyPref", 0).edit();
                //view.clear();
                //view.apply();
            }
        });
        dialog.show();
    }

}
