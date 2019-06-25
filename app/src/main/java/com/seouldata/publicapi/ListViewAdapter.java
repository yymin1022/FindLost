package com.seouldata.publicapi;
import android.widget.*;
import java.util.*;
import android.view.*;
import android.content.*;

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;

    public ListViewAdapter() {

    }

    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
		if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }
		TextView nameTextView = (TextView) convertView.findViewById(R.id.item_name);
        TextView placeTextView = (TextView) convertView.findViewById(R.id.item_place);
		TextView fromTextView = (TextView) convertView.findViewById(R.id.item_from) ;
		TextView dateTextView = (TextView) convertView.findViewById(R.id.item_date) ;
		TextView idTextView = (TextView) convertView.findViewById(R.id.item_id) ;
		ListViewItem listViewItem = listViewItemList.get(position);
		nameTextView.setText(listViewItem.getName());
        placeTextView.setText(listViewItem.getPlace());
		fromTextView.setText(listViewItem.getFrom());
		dateTextView.setText(listViewItem.getDate());
		idTextView.setText(listViewItem.getId());
        return convertView;
    }
	@Override
    public long getItemId(int position) {
        return position ;
    }
	@Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    public void addItem(String name, String place, String from, String date, String id) {
        ListViewItem item = new ListViewItem();
        item.setName(name);
        item.setPlace(place);
		item.setFrom(from);
		item.setDate(date);
		item.setId(id);
        listViewItemList.add(item);
    }
}
