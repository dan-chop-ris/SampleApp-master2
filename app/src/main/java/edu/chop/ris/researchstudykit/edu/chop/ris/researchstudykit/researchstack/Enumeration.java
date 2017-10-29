package edu.chop.ris.researchstudykit.edu.chop.ris.researchstudykit.researchstack;

/**
 * Created by danie on 10/26/2017.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Enumeration {

    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("value")
    @Expose
    public Integer value;
    @SerializedName("label")
    @Expose
    public String label;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("type", type).append("value", value).append("label", label).toString();
    }

}
