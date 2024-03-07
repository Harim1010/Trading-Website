package model;

public class PurchaseOrder {
    int id;
    String status;
    int productId;
    String productName;
    String hide_info;
    int quantity;
    double price;
    double totalPrice;
    String createDate;
    String updateDate;
    int userId;

    public PurchaseOrder() {
    }

    public PurchaseOrder(int id, String status, int productId, String productName, String hide_info, int quantity, double price, double totalPrice, String createDate, String updateDate, int userId) {
        this.id = id;
        this.status = status;
        this.productId = productId;
        this.productName = productName;
        this.hide_info = hide_info;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getHide_info() {
        return hide_info;
    }

    public void setHide_info(String hide_info) {
        this.hide_info = hide_info;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
