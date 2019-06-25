package com.seouldata.publicapi;

import android.os.*;
import android.view.*;
import android.widget.*;
import android.graphics.drawable.*;

public class WhatFragment extends android.support.v4.app.Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		LinearLayout linearLayout=(LinearLayout)inflater.inflate(R.layout.input_what,container,false);
		return linearLayout;
	}
}
