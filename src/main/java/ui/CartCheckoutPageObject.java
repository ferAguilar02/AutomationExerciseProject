package ui;

import ui.components.Breadcrumbs;
import ui.components.PageHeader;
import ui.section.CheckoutPopUp;


public abstract class CartCheckoutPageObject extends BasePageObject{
    public PageHeader pageHeader;
    public Breadcrumbs breadcrumbs;
    public CheckoutPopUp checkoutPopUp;


    public CartCheckoutPageObject() {
        pageHeader = new PageHeader();
        breadcrumbs = new Breadcrumbs();
        checkoutPopUp = new CheckoutPopUp();
    }
}
