package edu.fcps.sehuebsch.quiz80stv;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LeaderAdapter extends RecyclerView.Adapter<LeaderAdapter.ViewHolder> {


        private List<LeaderEntry> leaderList;

        public static class ViewHolder extends RecyclerView.ViewHolder
        {
            public TextView mTextViewName;
            //public TextView mTextViewScore;
            public ViewHolder(TextView v1)
            {
                super(v1);
                mTextViewName = v1;
                //mTextViewScore = v2;
            }
        }

        public LeaderAdapter(ArrayList<LeaderEntry> list)
        {
            //super( context, 0, list);
            super();
            leaderList = list;
        }

        public void updateList(ArrayList<LeaderEntry> list)
        {
            leaderList = list;
        }
    // Create new views (invoked by the layout manager)
    @Override
    public LeaderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                          int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.leader_view, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        // Individually handles each Contact in the contactList
        LeaderEntry currentEntry = leaderList.get(position);

        // Extracts the name of the Contact
        TextView name  = holder.mTextViewName.findViewById(R.id.textView_Name);
        name.setText(currentEntry.getLeaderName());

        // Extracts the phone number of the Contact
        TextView number = holder.mTextViewName.findViewById(R.id.textView_Score);
        number.setText(currentEntry.getHighScore());

    }

/*
       public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View listItem = convertView;


            // Associates the list with the XML Layout file "contact_view"
            if(listItem == null)
                listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.leader_view,parent,false);


            // Individually handles each Contact in the contactList
            LeaderEntry currentEntry = leaderList.get(position);


            // Extracts the name of the Contact
            TextView name  = (TextView) listItem.findViewById(R.id.textView_Name);
            name.setText(currentEntry.getLeaderName());

            // Extracts the phone number of the Contact
            TextView number = (TextView) listItem.findViewById(R.id.textView_Score);
            number.setText(currentEntry.getHighScore());


            return listItem;
        }

*/
        @Override
    public int getItemCount()
        {
            return leaderList.size();
        }
}
