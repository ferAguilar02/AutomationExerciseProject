package ui;

import ui.components.LeftSidebar;
import ui.components.PageHeader;
import ui.section.AddedPopUp;

public abstract class MainPageObject extends BasePageObject {
    public PageHeader pageHeader;
    public LeftSidebar leftSidebar;
    public AddedPopUp addedPopUp;

    public MainPageObject() {
        pageHeader = new PageHeader();
        leftSidebar = new LeftSidebar();
        addedPopUp = new AddedPopUp();
    }
}
