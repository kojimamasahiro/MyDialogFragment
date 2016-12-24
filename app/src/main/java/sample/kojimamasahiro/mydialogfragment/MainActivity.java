package sample.kojimamasahiro.mydialogfragment;

import android.os.Bundle;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDialogFragment myDialogFragment = MyDialogFragment.newInstance("main", "hello dialog", "");
        myDialogFragment.show(getFragmentManager(), "dialog");
    }

    @Override
    public void onPositive(String tag) {

    }

    @Override
    public void onNegative(String tag) {

    }

    @Override
    public void onNeutral(String tag) {

    }
}
