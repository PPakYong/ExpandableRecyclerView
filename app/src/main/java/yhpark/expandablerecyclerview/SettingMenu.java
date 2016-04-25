package yhpark.expandablerecyclerview;

/**
 * Created by YHPark on 2016-04-18.
 */
public class SettingMenu {
    private String menuNm;
    private String optionValue;
    private String title;
    private boolean isListExpanded;
    private boolean isEnableOption;
    private boolean isActive = true;

    int optionType = 0;

    public int getClickId() {
        return clickId;
    }

    public void setClickId(int clickId) {
        this.clickId = clickId;
    }

    int clickId = 0;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    public boolean isListExpanded() {
        return isListExpanded;
    }

    public void setListExpanded(boolean listExpanded) {
        this.isListExpanded = listExpanded;
    }

    public boolean isEnableOption() {
        return isEnableOption;
    }

    public void setEnableOption(boolean enableOption) {
        this.isEnableOption = enableOption;
    }

    public String getMenuNm() {
        return menuNm;
    }

    public void setMenuNm(String menuNm) {
        this.menuNm = menuNm;
    }

    public int getOptionType() {
        return optionType;
    }

    public void setOptionType(int optionType) {
        this.optionType = optionType;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
