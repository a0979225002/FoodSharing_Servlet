package tw.org.iii.lipin.domain;

import java.util.Date;

public class foodcard_has_takers {
    private int foodcard_id;
    private int user_id;
    private int qty;
    private int inline;
    private int giveraccept;
    private Date create;
    private Date modified;
    private int status;

    public int getFoodcard_id() {
        return foodcard_id;
    }

    public void setFoodcard_id(int foodcard_id) {
        this.foodcard_id = foodcard_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getInline() {
        return inline;
    }

    public void setInline(int inline) {
        this.inline = inline;
    }

    public int getGiveraccept() {
        return giveraccept;
    }

    public void setGiveraccept(int giveraccept) {
        this.giveraccept = giveraccept;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
