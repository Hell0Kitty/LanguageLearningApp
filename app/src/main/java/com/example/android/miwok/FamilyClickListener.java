package com.example.android.miwok;

import android.view.View;
import android.widget.Toast;

/**
 * Created by Thinkpad on 4/22/2017.
 */

public class FamilyClickListener implements View.OnClickListener {

  @Override
    public void onClick(View view)
  {
      Toast.makeText(view.getContext(),"Open",Toast.LENGTH_SHORT).show();


  }
}
