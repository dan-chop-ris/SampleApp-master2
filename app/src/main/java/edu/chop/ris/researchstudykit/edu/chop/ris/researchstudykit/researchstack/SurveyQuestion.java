package edu.chop.ris.researchstudykit.edu.chop.ris.researchstudykit.researchstack;

/**
 * Created by danie on 10/26/2017.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class SurveyQuestion {

    @SerializedName("identifier")
    @Expose
    public String identifier;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("elements")
    @Expose
    public List<Element> elements = null;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("identifier", identifier).append("type", type).append("name", name).append("elements", elements).toString();
    }

}