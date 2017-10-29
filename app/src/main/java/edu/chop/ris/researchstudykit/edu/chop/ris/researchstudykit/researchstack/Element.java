package edu.chop.ris.researchstudykit.edu.chop.ris.researchstudykit.researchstack;

/**
 * Created by danie on 10/26/2017.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Element {

    @SerializedName("identifier")
    @Expose
    public String identifier;
    @SerializedName("prompt")
    @Expose
    public String prompt;
    @SerializedName("uiHint")
    @Expose
    public String uiHint;
    @SerializedName("guid")
    @Expose
    public String guid;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("constraints")
    @Expose
    public Constraints constraints;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("identifier", identifier).append("prompt", prompt).append("uiHint", uiHint).append("guid", guid).append("type", type).append("constraints", constraints).toString();
    }

}