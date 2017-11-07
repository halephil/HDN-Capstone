package phillips.com.medminder;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by halenphillips on 10/10/17.
 */

public class Drug{

    private String name;
    private String description;
    private int pillCount;


    //Constructor
    public Drug(String name, String description, int pillCount){
        this.name = name;
        this.description = description;
        this.pillCount = pillCount;
    }

    // Default constructor
    public Drug(){}

    //
    public int decrementPillCount(){
        if(pillCount <= 0)
            return 0;

        pillCount = pillCount - 1;
        return pillCount;
    }


    //GETTERS SETTERS//


    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public int getPillCount(){ return this.pillCount;}

}
