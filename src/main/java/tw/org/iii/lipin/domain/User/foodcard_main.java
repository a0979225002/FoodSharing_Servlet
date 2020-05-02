package tw.org.iii.lipin.domain.User;


import java.sql.Date;

public class foodcard_main {
    private int id;
    private String name;
    private String category;
    private String city;
    private String dist;
    private String address;
    private Date due_date;
    private String tag;
    private int qty;
    private boolean split;
    private String detail;
    private boolean status;
    private Date createtime;
    private Date modified;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public boolean isSplit() {
        return split;
    }

    public void setSplit(boolean split) {
        this.split = split;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "foodcard_main{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", city='" + city + '\'' +
                ", dist='" + dist + '\'' +
                ", address='" + address + '\'' +
                ", due_date=" + due_date +
                ", tag='" + tag + '\'' +
                ", qty=" + qty +
                ", split=" + split +
                ", detail='" + detail + '\'' +
                ", status=" + status +
                ", createtime=" + createtime +
                ", modified=" + modified +
                '}';
    }
}

