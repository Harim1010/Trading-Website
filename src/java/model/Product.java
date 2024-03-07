package model;

public class Product {
    int id;
    String name;
    int quantity;
    int cateId;
    String code;
    double price;
    String hide_info;
    String description;
    String linkShare;
    String image;
    String cateName;

    public Product() {
    }

    public Product(int id, String name, int quantity, int cateId, String code, double price, String hide_info, String description, String linkShare, String image, String cateName) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.cateId = cateId;
        this.code = code;
        this.price = price;
        this.hide_info = hide_info;
        this.description = description;
        this.linkShare = linkShare;
        this.image = image;
        this.cateName = cateName;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getHide_info() {
        return hide_info;
    }

    public void setHide_info(String hide_info) {
        this.hide_info = hide_info;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinkShare() {
        return linkShare;
    }

    public void setLinkShare(String linkShare) {
        this.linkShare = linkShare;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCateName() {
        return cateName;
    }
    
    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
}
