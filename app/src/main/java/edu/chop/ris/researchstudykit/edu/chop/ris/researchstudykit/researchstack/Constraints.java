package edu.chop.ris.researchstudykit.edu.chop.ris.researchstudykit.researchstack;

/**
 * Created by danie on 10/26/2017.
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Constraints {

    @SerializedName("dataType")
    @Expose
    public String dataType;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("rules")
    @Expose
    public List<Rule> rules = null;
    @SerializedName("step")
    @Expose
    public Object step;
    @SerializedName("maxValue")
    @Expose
    public Integer maxValue;
    @SerializedName("minValue")
    @Expose
    public Integer minValue;
    @SerializedName("allowMultiple")
    @Expose
    public Boolean allowMultiple;
    @SerializedName("enumeration")
    @Expose
    public List<Enumeration> enumeration = null;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("dataType", dataType).append("type", type).append("rules", rules).append("step", step).append("maxValue", maxValue).append("minValue", minValue).append("allowMultiple", allowMultiple).append("enumeration", enumeration).toString();
    }

}