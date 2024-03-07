package pages;

import models.Product;

public interface SearchPage {

    void SearchProduct(String searchTerm);

    void clickOnProduct(String productName);

    Product getProductDetails();
    


}
