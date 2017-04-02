package example.com.contactlist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import example.com.contactlist.R;
import example.com.contactlist.model.Contact;

/**
 * Created by user on 4/3/2017.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactsViewHolder> {

private Context mContext;
private ArrayList<Contact> contactArrayList;

public ContactAdapter(Context mContext, ArrayList<Contact> contactArrayList) {
        this.mContext = mContext;
        this.contactArrayList = contactArrayList;
        }


class ContactsViewHolder extends RecyclerView.ViewHolder {
    TextView txtUserName;
    TextView txtUserNo;


    public ContactsViewHolder(View view) {
        super(view);
        this.txtUserName = (TextView) itemView.findViewById(R.id.txtUserName);
        this.txtUserNo = (TextView) itemView.findViewById(R.id.txtUserNo);


    }

}



    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_adapter, parent, false);

        // Set the view to the ViewHolder
        ContactsViewHolder holder = new ContactsViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {

        ContactsViewHolder contactsViewHolder =  holder;
        contactsViewHolder.txtUserName.setText(contactArrayList.get(position).cNo);
        contactsViewHolder.txtUserNo.setText(contactArrayList.get(position).cName);

        //Contact contact = contactArrayList.get(holder.getAdapterPosition());

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return contactArrayList.size();
    }


}
