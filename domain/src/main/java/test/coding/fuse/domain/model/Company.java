package test.coding.fuse.domain.model;


public class Company {
    private String name;
    private String logo;

    public Company(String name, String logo) {
        this.setName(name);
        this.setLogo(logo);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
