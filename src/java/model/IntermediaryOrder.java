package model;

public class IntermediaryOrder {
    private int id;
    private String description;
    private double price;
    private boolean fee_type;
    private String contact;
    private String hide_description;
    private boolean public_status;
    private String created_at;
    private String updated_at;
    private int account_sold_id;
    private String order_status;
    private double intermediary_fee;
    private String link_to;
    private String title;
    private String account_sold_name;

    public IntermediaryOrder() {
    }

    public IntermediaryOrder(int id, String description, double price, boolean fee_type, String contact, String hide_description, boolean public_status, String created_at, String updated_at, int account_sold_id, String order_status, double intermediary_fee, String link_to, String title, String account_sold_name) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.fee_type = fee_type;
        this.contact = contact;
        this.hide_description = hide_description;
        this.public_status = public_status;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.account_sold_id = account_sold_id;
        this.order_status = order_status;
        this.intermediary_fee = intermediary_fee;
        this.link_to = link_to;
        this.title = title;
        this.account_sold_name = account_sold_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean getFee_type() {
        return fee_type;
    }

    public void setFee_type(boolean fee_type) {
        this.fee_type = fee_type;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getHide_description() {
        return hide_description;
    }

    public void setHide_description(String hide_description) {
        this.hide_description = hide_description;
    }

    public boolean getPublic_status() {
        return public_status;
    }

    public void setPublic_status(boolean public_status) {
        this.public_status = public_status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getAccount_sold_id() {
        return account_sold_id;
    }

    public void setAccount_sold_id(int account_sold_id) {
        this.account_sold_id = account_sold_id;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public double getIntermediary_fee() {
        return intermediary_fee;
    }

    public void setIntermediary_fee(double intermediary_fee) {
        this.intermediary_fee = intermediary_fee;
    }

    public String getLink_to() {
        return link_to;
    }

    public void setLink_to(String link_to) {
        this.link_to = link_to;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAccount_sold_name() {
        return account_sold_name;
    }

    public void setAccount_sold_name(String account_sold_name) {
        this.account_sold_name = account_sold_name;
    }
}
