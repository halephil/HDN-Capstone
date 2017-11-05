package phillips.com.medminder;

/**
 * Created by halenphillips on 11/5/17.
 */

import android.os.Parcel;
import android.os.Parcelable;

public class DrugPO implements Parcelable{

    private String mName;
    private String mDescription;
    private String mPillCount;

    public DrugPO(){};

    // Constructor
    public DrugPO(String name, String description, String pillCount){
        this.mName = name;
        this.mDescription = description;
        this.mPillCount = pillCount;
    }
    // Getter and setter methods
    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmPillCount() {
        return mPillCount;
    }

    public void setmPillCount(String mPillCount) {
        this.mPillCount = mPillCount;
    }

    // Parcelling part
    public DrugPO(Parcel in){
        String[] data = new String[3];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method
        this.mName = data[0];
        this.mDescription = data[1];
        this.mPillCount = data[2];
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {
                this.mName,
                this.mDescription,
                this.mPillCount});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public DrugPO createFromParcel(Parcel in) {
            return new DrugPO(in);
        }

        public DrugPO[] newArray(int size) {
            return new DrugPO[size];
        }
    };
}
