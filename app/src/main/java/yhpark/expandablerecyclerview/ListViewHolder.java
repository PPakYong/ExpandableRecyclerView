package yhpark.expandablerecyclerview;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by YHPark on 2016-04-19.
 */
public class ListViewHolder extends RecyclerView.ViewHolder {
    private static final int TYPE_MAIN_OPTION_MULTI_CHOICE = 0;
    private static final int TYPE_MAIN_OPTION_SWITCH = 1;
    private static final int TYPE_MAIN_OPTIOIN_EXPANDABLE = 2;

    private static final int TYPE_SUB_OPTION_MULTI_CHOICE = 3;
    private static final int TYPE_SUB_OPTION_SWITCH = 4;
    private static final int TYPE_SUB_OPTION_LINK = 5;

    private static final int TYPE_HEADER = 6;
    private static final int TYPE_FOOTER = 7;

    public View itemView;

    public LinearLayout llCategory;
    public TextView tvCategory;

    public TextView tvOptionNm;
    public TextView tvOptionValue;
    public SwitchCompat sthOptionValue;
    public ToggleButton tbExpandable;

    public ListViewHolder(View itemView, int viewType) {
        super(itemView);
        this.itemView = itemView;

        if (viewType == TYPE_HEADER) {

        } else if (viewType == TYPE_FOOTER) {

        } else {
            llCategory = (LinearLayout) itemView.findViewById(R.id.llCategory);
            tvCategory = (TextView) itemView.findViewById(R.id.tvCategory);

            tvOptionNm = (TextView) itemView.findViewById(R.id.tvOptionNm);
            tvOptionValue = (TextView) itemView.findViewById(R.id.tvOptionValue);
            sthOptionValue = (SwitchCompat) itemView.findViewById(R.id.sthOptionValue);
            tbExpandable = (ToggleButton) itemView.findViewById(R.id.tbExpandable);
        }
    }

    public View getItemView() {
        return itemView;
    }
}
