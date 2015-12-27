package homework5.rssreader.Channels;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import homework5.rssreader.R;

/**
 * Created by Anstanasia on 27.12.2015.
 */
public class ChannelDialog extends DialogFragment {
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_URL = "url";

    private OnCompleteListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = "";
        String url = "";

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();

        if (getArguments() != null) {
            title = getArguments().getString(EXTRA_TITLE);
            url = getArguments().getString(EXTRA_URL);
            builder.setTitle("Change channel");
        } else {
            builder.setTitle("New channel");
        }

        final View v = inflater.inflate(R.layout.channel_dialog, null);

        if (getArguments() != null) {
            ((EditText)v.findViewById(R.id.channel_dialog_title)).setText(title);
            ((EditText)v.findViewById(R.id.channel_dialog_url)).setText(url);
        }

        builder.setView(v).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    mListener.onComplete(((EditText) v.findViewById(R.id.channel_dialog_title)).getText().toString(),
                                         ((EditText) v.findViewById(R.id.channel_dialog_url)).getText().toString());

                    /*sendResult(((EditText) v.findViewById(R.id.channel_dialog_title)).getText().toString(),
                            ((EditText) v.findViewById(R.id.channel_dialog_url)).getText().toString());
                */
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    ChannelDialog.this.getDialog().cancel();
                }
            }).setNeutralButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                mListener.onComplete(null, null);
            }
        });

        return builder.create();
    }

    public interface OnCompleteListener {
        void onComplete(String title, String url);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnCompleteListener)activity;
        } catch (final ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnCompleteListener");
        }
    }

}
