package test.coding.fuse.data.entity;


import com.google.gson.annotations.SerializedName;

public class CompanyEntity {

    @SerializedName("name")
    private String name;

    @SerializedName("logo")
    private String logo;


    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
