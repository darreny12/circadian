package com.example.hshack.circadian;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hshack.circadian.R;
import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {
    private ArrayList<Message> messageArrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        Button button = (Button) v.findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditText tv = (EditText) view.findViewById(R.id.editText2);
                final ConversationService service = new ConversationService("2017-03-26");

                service.setUsernameAndPassword("3a0d0775-006f-45af-a971-eac23a4577fe", "DCpBzpq76lPV");


                String text = " ";
                if(tv != null)
                    text = tv.getText().toString();
                final MessageRequest newMessage = new MessageRequest.Builder()
                        .inputText(text)
                        .build();

                GridLayout l = (GridLayout) v.findViewById(R.id.grid);
                TextView temp = new TextView(getContext());
                temp.setText(text);
                l.addView(temp);


                Thread thread = new Thread(new Runnable() {
                    public void run() {
                        try {
                            MessageResponse response = service.message("25dfa8a0-0263-471b-8980-317e68c30488", newMessage).execute();
                            System.out.println("test");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
        return v;
    }



}