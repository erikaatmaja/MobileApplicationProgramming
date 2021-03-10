package umn.ac.id.week03_27299;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//===LANGKAH 1===
//1. adapter punya RecyclerView, makanya di extends
//2. adapter butuh ViewHolder = untuk sambungin adapter dengan dummy_item (data)
//DummyViewHolder adalah bagian dari DummyAdapter
public class DummyAdapter extends RecyclerView.Adapter<DummyAdapter.DummyViewHolder> {
    //===LANGKAH 2===
    // butuh 1. context : dari yang manggil si adapter(MainActivity.java)
    // -> ngirim dari MainActivity.java, nerimanya gimana ???
    // 2. data

    Context ctx;
    List<String> lists;

    DummyAdapter(Context context, List<String> data){
        this.ctx = context;
        this.lists = data;
    } //===LANGKAH 3===
      // adapter udah BISA terima context dan data
      // + bisa konek in adapter dengan dummy item = konekinnya di onCreateViewHolder

    @NonNull
    @Override
    //onCreateViewHolder untuk mempersiapkan DummyViewHolder
    public DummyAdapter.DummyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //===LANGKAH 4===
        // disini konekinnya
        //dia minta konteks .from() = si main activity, kt ud pnya (Di constructor = ctx)
        //inflate pnya 4 method, biasanya dipake yang ke 3
        //int resource = konekin layout dummy_item,
        // ViewGroup root = diliat dari atas itu ada ViewGroup parent,
        // boolean attachroot = biasanya false
        View view = LayoutInflater.from(ctx).inflate(R.layout.dummy_item, parent, false);

        //===LANGKAH 4===
        //konekin dummy_item ke DummyAdapter itu dari oncreate dan DummyViewHolder
        //DummyViewHolder harus terima view nya dummy_item dapet dari View view = balablaa
        //mangkanya itu diisi view di dlm itu DummyViewHolder(view)
        return new DummyViewHolder(view);
    } //===LANGKAH 5===
      //ini adapter ud bisa ke konek sama dummy_item tp blm konek ke recycler view
      //dia return ke adapter

    @Override
    //onBindViewHolder nampilin data ke dlm per itemnya
    public void onBindViewHolder(@NonNull DummyAdapter.DummyViewHolder holder, int position) {
        //===LANGKAH 8===
        //ganti value tvDummyItem dengan lists -> List<String> lists (data yang akan kita terima)
        //idx nya dapet dari .get(position)
        holder.tvDummyItem.setText(lists.get(position));

        //ini buat yang linearlayout
        holder.lldummyitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String elemnent = lists.get(position); ////////////////CEK!!!!!
                lists.set(position, elemnent + ", already clicked!");
                Toast.makeText(ctx, lists.get(position) + " clicked", Toast.LENGTH_SHORT).show();
            }
        });
    } //===LANGKAH 9===
      //adapter data dari MainActivity
      // adapter ud konek sama dummy_item + isi text nya
      //SISA nyambungin adapter dengan recyclerview (di halaman MainActivity.java)

    @Override
    //untuk kasih tau ke adapter ada berapa banyak item yang diterima
    public int getItemCount() {
        //===LANGKAH10===, kl return 0 ga ada data nnt
        return lists.size();
    }

    //DummyViewHolder punya RecyclerView JUGA
    //DummyViewHolder butuh constructor
    public static class DummyViewHolder extends RecyclerView.ViewHolder{
        //===LANGKAH 6===
        //cara pake id di textview yang pnya id = tvDummyItem
        TextView tvDummyItem;

        //interaksi dengan linearlayoutnya
        LinearLayout lldummyitem;

        public DummyViewHolder(@NonNull View itemView) {
            super(itemView);
            //===LANGKAH 7===
            //cari idnya.. findViewById punya si View yang pny itemView bukan pny DummyViewHolder
            tvDummyItem = itemView.findViewById(R.id.tvDummyItem);
            lldummyitem = itemView.findViewById(R.id.lldummyitem);
        }
    }
}
