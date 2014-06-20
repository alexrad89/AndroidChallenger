package yc.android.yourchallenger;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ReadFragment extends Fragment {
    public ReadFragment() {
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_create, container, false);
        TextView text = (TextView) rootView.findViewById(R.id.action_settings);
        text.setText("string");
        return rootView;
    }
 

}
