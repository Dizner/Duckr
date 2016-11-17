package cn.duckr.duckr;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by PF on 2016/11/9.
 */

public class Me_information_photo_dialoginface extends Dialog implements View.OnClickListener{
    private Context context;
    private Button me_information_photo_dialog1,me_information_photo_dialog2;
    private me_information_photo_Listener listener;

    public interface me_information_photo_Listener{
        void onClick(View view);
    }

    public Me_information_photo_dialoginface(Context context) {
        super(context);
        this.context = context;
    }

    public Me_information_photo_dialoginface(Context context,int theme,me_information_photo_Listener listener){
        super(context);

        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.me_information_photo_dialog);
        me_information_photo_dialog1 = (Button) findViewById(R.id.me_information_photo_dialog1);
        me_information_photo_dialog2 = (Button) findViewById(R.id.me_information_photo_dialog2);
        me_information_photo_dialog1.setOnClickListener(this);
        me_information_photo_dialog2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        listener.onClick(view);
    }


}







