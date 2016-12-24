package sample.kojimamasahiro.mydialogfragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

public class MyDialogFragment extends DialogFragment {

    private static final String DEFAULT_POSITIVE = "OK";

    private static final String BUNDLE_TAG = "tag";
    private static final String BUNDLE_TITLE = "title";
    private static final String BUNDLE_MESSAGE = "message";
    private static final String BUNDLE_POSITIVE = "positive";
    private static final String BUNDLE_NEGATIVE = "negative";
    private static final String BUNDLE_NEUTRAL = "neutral";

    protected MyDialogFragmentListener mListener;
    public interface MyDialogFragmentListener {
        void onPositive(String tag);
        void onNegative(String tag);
        void onNeutral(String tag);
    }

    /**
     *  引数にあわせて、ダイアログの形をかえる
     */
    public static MyDialogFragment newInstance(String tag, String title, String message) {
        return newInstance(tag, title, message, DEFAULT_POSITIVE, null, null);
    }

    public static MyDialogFragment newInstance(String tag, String title, String message, String positive) {
        return newInstance(tag, title, message, positive, null, null);
    }

    public static MyDialogFragment newInstance(String tag, String title, String message, String positive, String negative) {
        return newInstance(tag, title, message, positive, negative, null);
    }

    public static MyDialogFragment newInstance(String tag, String title, String message, String positive, String negative, String neutral) {
        Bundle args = new Bundle();
        args.putString(BUNDLE_TAG, tag);
        args.putString(BUNDLE_TITLE, title);
        args.putString(BUNDLE_MESSAGE, message);
        args.putString(BUNDLE_POSITIVE, positive);
        args.putString(BUNDLE_NEGATIVE, negative);
        args.putString(BUNDLE_NEUTRAL, neutral);

        MyDialogFragment dialogFragment = new MyDialogFragment();
        dialogFragment.setArguments(args);

        return dialogFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(getArguments().getString(BUNDLE_TITLE))
                .setMessage(getArguments().getString(BUNDLE_MESSAGE))
                .setPositiveButton(getArguments().getString(BUNDLE_POSITIVE),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (mListener == null) {
                                    return;
                                }

                                mListener.onPositive(getArguments().getString(BUNDLE_TAG));
                            }
                        })
                .setNegativeButton(getArguments().getString(BUNDLE_NEGATIVE),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (mListener == null) {
                                    return;
                                }

                                mListener.onNegative(getArguments().getString(BUNDLE_TAG));
                            }
                        })
                .setNeutralButton(getArguments().getString(BUNDLE_NEUTRAL),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (mListener == null) {
                                    return;
                                }

                                mListener.onNeutral(getArguments().getString(BUNDLE_TAG));
                            }
                        })
                .create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (MyDialogFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        dismiss();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
