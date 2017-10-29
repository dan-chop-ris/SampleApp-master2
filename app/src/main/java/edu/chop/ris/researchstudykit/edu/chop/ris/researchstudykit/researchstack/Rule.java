package edu.chop.ris.researchstudykit.edu.chop.ris.researchstudykit.researchstack;

/**
 * Created by danie on 10/26/2017.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Rule {

    @SerializedName("operator")
    @Expose
    public String operator;
    @SerializedName("skipTo")
    @Expose
    public String skipTo;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("value")
    @Expose
    public Integer value;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("operator", operator).append("skipTo", skipTo).append("type", type).append("value", value).toString();
    }

}

