package yc.android.yourchallenger;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class CreateFragment extends Fragment {
    public CreateFragment() {
    }
 
    Button button;  

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create, container, false);
        button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity();

                if (activity != null) {
                    Toast.makeText(activity,
                            "toast_you_just_clicked_a_fragment",
                            Toast.LENGTH_LONG).show();
                }
            }

        });

        return view;
    }

    public void setText(String text ){
        button.setText(text);
    }
}