package com.kondie.pm_admin;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by kondie on 2018/02/17.
 */

public class MechanicItemHolder extends RecyclerView.ViewHolder {

    TextView shopName, hiddenMechanicEmail;
    Button shopStatusButton;
    public static Button clickedMechanicButton;
    ImageView shopDp;
    Dialog optionsDialog;

    public MechanicItemHolder(View likeItemView){
        super(likeItemView);

        shopName = likeItemView.findViewById(R.id.mechanic_name);
        hiddenMechanicEmail = likeItemView.findViewById(R.id.hidden_mechanic_email);
        shopStatusButton = likeItemView.findViewById(R.id.mechanic_status);
        shopDp = likeItemView.findViewById(R.id.mechanic_dp);

        shopStatusButton.setOnClickListener(changeMechanicStatus);
    }

    private View.OnClickListener changeMechanicStatus = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            clickedMechanicButton = shopStatusButton;
            String act = (shopStatusButton.getText().toString().equalsIgnoreCase("active")) ? "deactivate" : "activate";
            new ChangeMechanicStatus().execute(act, hiddenMechanicEmail.getText().toString());
        }
    };

    /*private String getStringDP(){
        BitmapDrawable bitmapDrawable = (BitmapDrawable) userDp.getDrawable();
        Bitmap shopIconBitmap = bitmapDrawable.getBitmap();

        ByteArrayOutputStream BAOutStream = new ByteArrayOutputStream();
        userDpBitmap.compress(Bitmap.CompressFormat.JPEG, 100, BAOutStream);
        byte[] byteArray = BAOutStream.toByteArray();
        String stringDP = Base64.encodeToString(byteArray, Base64.DEFAULT);

        return (stringDP);
    }

    private View.OnClickListener goto_chat = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent gotoChatIntent = new Intent(UserListAdapter.activity, InsideDm.class);
            gotoChatIntent.putExtra("username", username.getText().toString());
            gotoChatIntent.putExtra("stringDP", getStringDP());
            UserListAdapter.activity.startActivity(gotoChatIntent);
        }
    };*/
}

