package phillips.com.medminder;

/**
 * Created by halenphillips on 11/5/17.
 */

import android.os.Parcel;
import android.os.Parcelable;

public class DrugPO implements Parcelable{


    private String mAlarmTime;
    private String mName;
    private String mDescription;
    private int mDosesPerDay;
    private int mQuantityPerDose;
    private int mRefillAt;
    private int mPillCount;
    private int indexPos;
    private boolean Monday;
    private boolean Tuesday;
    private boolean Wednesday;
    private boolean Thursday;
    private boolean Friday;



    private boolean Saturday;
    private boolean Sunday;

    public DrugPO(){
        this.mAlarmTime = "N/A";
        this.mName = "";
        this.mDescription = "";
        this.mDosesPerDay = 0;
        this.mQuantityPerDose = 0;
        this.mRefillAt = 0;
        this.mPillCount = 0;
        this.Monday = false;
        this.Tuesday = false;
        this.Wednesday = false;
        this.Thursday = false;
        this.Friday = false;
        this.Saturday = false;
        this.Sunday = false;
    };

    // Constructor
    public DrugPO(String name, String description, int pillCount){
        this.mAlarmTime = "N/A";
        this.mName = "";
        this.mDescription = "";
        this.mDosesPerDay = 0;
        this.mQuantityPerDose = 0;
        this.mRefillAt = 0;
        this.mPillCount = 0;
        this.Monday = true;
        this.Tuesday = false;
        this.Wednesday = false;
        this.Thursday = false;
        this.Friday = false;
        this.Saturday = false;
        this.Sunday = false;

        this.mName = name;
        this.mDescription = description;
        this.mPillCount = pillCount;
    }

    public void decrementPillCount(){
        if(mPillCount > 0){
            mPillCount = mPillCount - 1;
        }
    }


    // Getter and setter methods
    public String getmName()
    {
        if(mName == null){
            return "";
        }
        else{
            return mName;
        }

    }
    public String getmAlarmTime() {return mAlarmTime;}

    public void setmAlarmTime(String mAlarmTime) {this.mAlarmTime = mAlarmTime;}

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {return mDescription;}

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public int getmDosesPerDay() {return mDosesPerDay;}

    public void setmDosesPerDay(int mDosesPerDay) {this.mDosesPerDay = mDosesPerDay;}

    public int getmQuantityPerDose() {return mQuantityPerDose;}

    public void setmQuantityPerDose(int mQuantityPerDose) {this.mQuantityPerDose = mQuantityPerDose;}

    public int getmRefillAt() {return mRefillAt;}

    public void setmRefillAt(int mRefillAt) {this.mRefillAt = mRefillAt;}

    public int getmPillCount() {
        return mPillCount;
    }

    public void setmPillCount(int mPillCount) {
        this.mPillCount = mPillCount;
    }

    public int getIndexPos() {return indexPos;}

    public void setIndexPos(int indexPos) {this.indexPos = indexPos;}

    public boolean isMonday() {return Monday;}

    public void setMonday(boolean monday) {Monday = monday;}

    public boolean isTuesday() {return Tuesday;}

    public void setTuesday(boolean tuesday) {Tuesday = tuesday;}

    public boolean isWednesday() {return Wednesday;}

    public void setWednesday(boolean wednesday) {Wednesday = wednesday;}

    public boolean isThursday() {return Thursday;}

    public void setThursday(boolean thursday) {Thursday = thursday;}

    public boolean isFriday() {return Friday;}

    public void setFriday(boolean friday) {Friday = friday;}

    public boolean isSaturday() {return Saturday;}

    public void setSaturday(boolean saturday) {Saturday = saturday;}

    public boolean isSunday() {return Sunday;}

    public void setSunday(boolean sunday) {Sunday = sunday;}



    // Parcelling part
    public DrugPO(Parcel in){
        String[] data = new String[15];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method
        this.mName = data[0];
        this.mDescription = data[1];
        this.mDosesPerDay = Integer.parseInt(data[2]);
        this.mQuantityPerDose = Integer.parseInt(data[3]);
        this.mRefillAt = Integer.parseInt(data[4]);
        this.mPillCount = Integer.parseInt(data[5]);
        this.indexPos = Integer.parseInt(data[6]);
        this.Monday = Boolean.parseBoolean(data[7]);
        this.Tuesday = Boolean.parseBoolean(data[8]);
        this.Wednesday = Boolean.parseBoolean(data[9]);
        this.Thursday = Boolean.parseBoolean(data[10]);
        this.Friday = Boolean.parseBoolean(data[11]);
        this.Saturday = Boolean.parseBoolean(data[12]);
        this.Sunday = Boolean.parseBoolean(data[13]);
        this.mAlarmTime = data[14];
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
                Integer.toString(this.mDosesPerDay),
                Integer.toString(this.mQuantityPerDose),
                Integer.toString(this.mRefillAt),
                Integer.toString(this.mPillCount),
                Integer.toString(this.indexPos),
                Boolean.toString(this.Monday),
                Boolean.toString(this.Tuesday),
                Boolean.toString(this.Wednesday),
                Boolean.toString(this.Thursday),
                Boolean.toString(this.Friday),
                Boolean.toString(this.Saturday),
                Boolean.toString(this.Sunday),
                this.mAlarmTime
        });
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
