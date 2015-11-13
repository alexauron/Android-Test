package test.coding.fuse.presentation.model;


import android.os.Parcel;
import android.os.Parcelable;

public class CompanyModel implements Parcelable {
    private String name;
    private String logo;

    public CompanyModel(String name, String logo) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.logo);
    }

    protected CompanyModel(Parcel in) {
        this.name = in.readString();
        this.logo = in.readString();
    }

    public static final Parcelable.Creator<CompanyModel> CREATOR = new Parcelable.Creator<CompanyModel>() {
        public CompanyModel createFromParcel(Parcel source) {
            return new CompanyModel(source);
        }

        public CompanyModel[] newArray(int size) {
            return new CompanyModel[size];
        }
    };
}
