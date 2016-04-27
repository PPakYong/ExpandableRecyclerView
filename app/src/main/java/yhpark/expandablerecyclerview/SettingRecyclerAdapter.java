package yhpark.expandablerecyclerview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by YHPark on 2016-04-19.
 */
public abstract class SettingRecyclerAdapter extends RecyclerView.Adapter<ListViewHolder> {
    public Context context;

    public static final int TYPE_MAIN_OPTION_MULTI_CHOICE = 0x0000;
    public static final int TYPE_MAIN_OPTION_SWITCH = 0x0001;
    public static final int TYPE_MAIN_OPTIOIN_EXPANDABLE = 0x0002;

    public static final int TYPE_SUB_OPTION_MULTI_CHOICE = 0x1000;
    public static final int TYPE_SUB_OPTION_SWITCH = 0x1001;
    public static final int TYPE_SUB_OPTION_LINK = 0x1002;

    public static final int TYPE_HEADER = 0x9999;
    public static final int TYPE_FOOTER = 0x8888;

    ArrayList<SettingMenu> item;

    private int LAYOUT_HEADER;
    private int LAYOUT_FOOTER;

    SettingRecyclerAdapter(Context context) {
        if (context == null) {
            throw new NullPointerException("context is null!");
        }

        this.context = context;
    }

    public void setItem(String[] optionArray, int header, int footer) {
        if (optionArray == null) {
            throw new NullPointerException("optionArray is null!");
        }
        item = new ArrayList<SettingMenu>();

        if (header != 0) {
            LAYOUT_HEADER = header;
            SettingMenu headerView = new SettingMenu();
            headerView.setOptionType(TYPE_HEADER);
            item.add(headerView);
        }

        int option = 0;
        for (int i = 0; i < optionArray.length; i++) {
            SettingMenu menu = new SettingMenu();
            String[] optionItem = optionArray[i].split("--");
            menu.setTitle(optionItem[0]);
            menu.setMenuNm(optionItem[1]);
            menu.setOptionType(Integer.parseInt(optionItem[2]));
            item.add(menu);
        }

        if (footer != 0) {
            LAYOUT_FOOTER = footer;
            SettingMenu footerView = new SettingMenu();
            footerView.setOptionType(TYPE_FOOTER);
            item.add(footerView);
        }
    }

    public void setItem(ArrayList<SettingMenu> optionArray, int header, int footer) {
        if (optionArray == null) {
            throw new NullPointerException("optionArray is null!");
        }
        item = optionArray;

        if (header != 0) {
            LAYOUT_HEADER = header;
            SettingMenu headerView = new SettingMenu();
            headerView.setOptionType(TYPE_HEADER);
            item.add(0, headerView);
        }

        if (footer != 0) {
            LAYOUT_FOOTER = footer;
            SettingMenu footerView = new SettingMenu();
            footerView.setOptionType(TYPE_FOOTER);
            item.add(footerView);
        }
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;
        ListViewHolder vh = null;
        switch (viewType) {
            case TYPE_MAIN_OPTION_MULTI_CHOICE:
            case TYPE_MAIN_OPTION_SWITCH:
            case TYPE_MAIN_OPTIOIN_EXPANDABLE:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_setting_list_main, parent, false);
                vh = new ListViewHolder(v, TYPE_MAIN_OPTIOIN_EXPANDABLE);
                break;
            case TYPE_SUB_OPTION_MULTI_CHOICE:
            case TYPE_SUB_OPTION_SWITCH:
            case TYPE_SUB_OPTION_LINK:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_setting_list_expanded, parent, false);
                vh = new ListViewHolder(v, TYPE_MAIN_OPTIOIN_EXPANDABLE);
                break;
            case TYPE_HEADER:
                v = LayoutInflater.from(parent.getContext()).inflate(getHeaderLayout(), parent, false);
                vh = new ListViewHolder(v, TYPE_HEADER);
                break;
            case TYPE_FOOTER:
                v = LayoutInflater.from(parent.getContext()).inflate(getFooterLayout(), parent, false);
                vh = new ListViewHolder(v, TYPE_FOOTER);
                break;
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(final ListViewHolder holder, final int position) {
        if (item == null) {
            throw new NullPointerException("item is null! don't forget call 'setItem()' before call setAdapter().");
        }

        final SettingMenu menu = (SettingMenu) item.get(position);
        switch (menu.getOptionType()) {
            case TYPE_MAIN_OPTION_MULTI_CHOICE:
                holder.tvOptionValue.setVisibility(View.VISIBLE);
                holder.sthOptionValue.setVisibility(View.GONE);
                holder.tbExpandable.setVisibility(View.GONE);
                break;
            case TYPE_MAIN_OPTION_SWITCH:
                holder.tvOptionValue.setVisibility(View.GONE);
                holder.sthOptionValue.setVisibility(View.VISIBLE);
                holder.tbExpandable.setVisibility(View.GONE);
                break;
            case TYPE_MAIN_OPTIOIN_EXPANDABLE:
                holder.tvOptionValue.setVisibility(View.GONE);
                holder.sthOptionValue.setVisibility(View.GONE);
                holder.tbExpandable.setVisibility(View.VISIBLE);
                break;
            case TYPE_SUB_OPTION_MULTI_CHOICE:
                holder.tvOptionValue.setVisibility(View.VISIBLE);
                holder.sthOptionValue.setVisibility(View.GONE);
                holder.tbExpandable.setVisibility(View.GONE);
                break;
            case TYPE_SUB_OPTION_SWITCH:
                holder.tvOptionValue.setVisibility(View.GONE);
                holder.sthOptionValue.setVisibility(View.VISIBLE);
                holder.tbExpandable.setVisibility(View.GONE);
                break;
            case TYPE_SUB_OPTION_LINK:
                holder.tvOptionValue.setVisibility(View.GONE);
                holder.sthOptionValue.setVisibility(View.GONE);
                holder.tbExpandable.setVisibility(View.GONE);
                break;
            case TYPE_HEADER:
                break;
            case TYPE_FOOTER:
                break;
        }

        if (menu.getOptionType() != TYPE_HEADER && menu.getOptionType() != TYPE_FOOTER) {
            if (!FormatUtil.isNullorEmpty(menu.getTitle()) && menu.getTitle().equals("untitle")) {
                holder.llCategory.setVisibility(View.VISIBLE);
                holder.tvCategory.setVisibility(View.GONE);
                holder.tvCategory.setText(menu.getTitle());
            } else if (!FormatUtil.isNullorEmpty(menu.getTitle())) {
                holder.llCategory.setVisibility(View.VISIBLE);
                holder.tvCategory.setVisibility(View.VISIBLE);
                holder.tvCategory.setText(menu.getTitle());
            } else {
                holder.llCategory.setVisibility(View.GONE);
            }

            holder.tvOptionNm.setText(menu.getMenuNm());
            holder.tvOptionValue.setText(menu.getOptionValue());

            if (!FormatUtil.isNullorEmpty(menu.getOptionValue()) && isOptionDisable(menu.getOptionValue())) {
                holder.tvOptionValue.setTextColor(Color.GRAY);
            } else {
                holder.tvOptionValue.setTextColor(Color.BLACK);
            }

            holder.tbExpandable.setChecked(menu.isListExpanded());
            holder.sthOptionValue.setChecked(menu.isEnableOption());

            if (menu.isActive()) {
                holder.getItemView().setBackgroundColor(Color.WHITE);

                holder.getItemView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.sthOptionValue.isShown()) {
                            holder.sthOptionValue.setChecked(!holder.sthOptionValue.isChecked());
                            setOnClickOptionOnOff(menu.getClickId(), menu.getMenuNm(), holder.sthOptionValue);
                        } else if (holder.tbExpandable.isShown()) {
                            holder.tbExpandable.setChecked(!holder.tbExpandable.isChecked());
                            menu.setListExpanded(holder.tbExpandable.isChecked());
                            setOnClickSubmenuView(menu.getMenuNm(), holder.tbExpandable.isChecked());
                        } else {
                            setOnClickOptionValue(holder.getLayoutPosition(), menu.getMenuNm(), holder.tvOptionValue);
                        }
                    }
                });
            } else {
                holder.getItemView().setOnClickListener(null);
                holder.getItemView().setBackgroundColor(Color.GRAY);
            }
        } else if (menu.getOptionType() == TYPE_HEADER) {
            initHeaderView(holder.getItemView());
        } else if (menu.getOptionType() == TYPE_FOOTER) {
            initFooterView(holder.getItemView());
        }
    }

    public abstract void initHeaderView(View itemView);

    public abstract void initFooterView(View itemView);

    public abstract void setOnClickOptionOnOff(int clickId, String menuNm, final SwitchCompat sthOptionValue);

    public void setOnClickSubmenuView(String mainMenuTitle, boolean isViewOpen) {
        if (getSubMenu(mainMenuTitle) == null) {
            throw new NullPointerException("getSubMenu(" + mainMenuTitle + ") is null! if you want to use expandable menu, you should override getSubMenu(String menuNm)");
        }

        //This is default function. do not work here! work what you want by override!
        int menuPosition = getItemPosition(mainMenuTitle) + 1;

        if (isViewOpen) {
            for (int i = 0; i < getSubMenu(mainMenuTitle).size(); i++) {
                item.add(menuPosition + i, getSubMenu(mainMenuTitle).get(i));
            }
            notifyItemRangeInserted(menuPosition, getSubMenu(mainMenuTitle).size());
        } else {
            for (int i = 0; i < getSubMenu(mainMenuTitle).size(); i++) {
                item.remove(menuPosition);
            }
            notifyItemRangeRemoved(menuPosition, getSubMenu(mainMenuTitle).size());
        }
    }

    ;

    public abstract void setOnClickOptionValue(int position, String menuNm, TextView tvOptionValue);

    public ArrayList<SettingMenu> getSubMenu(String menuNm) {
        //Do not work here! work what you want by override!
        return null;
    }


    public int getItemPosition(String menuNm) {
        for (int i = 0; i < item.size(); i++) {
            if (menuNm.equals(item.get(i).getMenuNm())) {
                return i;
            }
        }

        return -1;
    }

    public int getItemPosition(int clickId) {
        for (int i = 0; i < item.size(); i++) {
            if (clickId == item.get(i).getClickId()) {
                return i;
            }
        }

        return -1;
    }

    public boolean isOptionDisable(String optionValue) {
        //TODO if your list item is inactive and you want something of action, input terms and return that
        return false;
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    @Override
    public int getItemViewType(int position) {
        return ((SettingMenu) item.get(position)).getOptionType();
    }

    public SettingMenu getItemFromMenuNm(String menuNm) {
        return item.get(getItemPosition(menuNm));
    }

    public SettingMenu getItemFromPosition(int position) {
        return item.get(position);
    }

    public SettingMenu getItemFromClickId(int clickId) {
        return item.get(getItemPosition(clickId));
    }

    public ArrayList<SettingMenu> getAllItem() {
        return item;
    }

    public void remove(SettingMenu menu) {
        item.remove(menu);
        notifyDataSetChanged();
    }

    public int getHeaderLayout() {
        return LAYOUT_HEADER;
    }

    public int getFooterLayout() {
        return LAYOUT_FOOTER;
    }
}