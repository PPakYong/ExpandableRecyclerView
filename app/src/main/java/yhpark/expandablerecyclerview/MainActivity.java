package yhpark.expandablerecyclerview;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by YHPark on 2016-04-25.
 */
public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Sample Code
        adapter.setItem(getMainMenu(), 0, 0);

        RecyclerView rcvSettingList = (RecyclerView) findViewById(R.id.rcvSettingList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rcvSettingList.setLayoutManager(linearLayoutManager);
        rcvSettingList.setAdapter(adapter);
    }

    SettingRecyclerAdapter adapter = new SettingRecyclerAdapter(this) {
        @Override
        public ArrayList<SettingMenu> getSubMenu(String menuNm) {
            ArrayList<SettingMenu> list = new ArrayList<SettingMenu>();
            for (int i = 0; i < 5; i++) {
                SettingMenu menu = new SettingMenu();
                menu.setMenuNm(menuNm + " - sub menu " + i);

                switch (i % 3) {
                    case 0:
                        menu.setOptionType(SettingRecyclerAdapter.TYPE_SUB_OPTION_MULTI_CHOICE);
                        break;
                    case 1:
                        menu.setOptionType(SettingRecyclerAdapter.TYPE_SUB_OPTION_SWITCH);
                        break;
                    case 2:
                        menu.setOptionType(SettingRecyclerAdapter.TYPE_SUB_OPTION_LINK);
                        break;
                }
                list.add(menu);
            }
            return list;
        }

        @Override
        public void initHeaderView(View itemView) {
        }

        @Override
        public void initFooterView(View itemView) {
        }

        @Override
        public void setOnClickOptionOnOff(int clickId, String menuNm, SwitchCompat sthOptionValue) {
        }

        @Override
        public void setOnClickOptionValue(int position, String menuNm, TextView tvOptionValue) {
        }
    };

    public ArrayList<SettingMenu> getMainMenu() {
        ArrayList<SettingMenu> list = new ArrayList<SettingMenu>();
        for (int i = 0; i < 30; i++) {
            SettingMenu menu = new SettingMenu();
            if (i % 5 == 0) {
                menu.setTitle("Title View");
            }

            menu.setMenuNm("Test menu " + i);

            switch (i % 3) {
                case 0:
                    menu.setOptionType(SettingRecyclerAdapter.TYPE_MAIN_OPTION_MULTI_CHOICE);
                    break;
                case 1:
                    menu.setOptionType(SettingRecyclerAdapter.TYPE_MAIN_OPTION_SWITCH);
                    break;
                case 2:
                    menu.setOptionType(SettingRecyclerAdapter.TYPE_MAIN_OPTIOIN_EXPANDABLE);
                    break;
            }

            list.add(menu);
        }
        return list;
    }
}
