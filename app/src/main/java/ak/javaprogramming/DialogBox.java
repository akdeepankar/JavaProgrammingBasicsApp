package ak.javaprogramming;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogBox extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("JAVA PROGRAMMING")
                .setMessage("Version : 1.0 \nCreated : 25th March 2020 \nLast Updated : 25th March 2020 \nDeveloped By : AK DEEPAK \nContact : akdeepaknyc@gmail.com \nInstagram : @aknyss")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        return builder.create();
    }
}
