package edu.chop.ris.researchstudykit.edu.chop.ris.researchstudykit.redcap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by danie on 10/16/2017.
 */

public class RedcapQuestion {
    @SerializedName("field_name")
    @Expose
    public String fieldName;
    @SerializedName("form_name")
    @Expose
    public String formName;
    @SerializedName("section_header")
    @Expose
    public String sectionHeader;
    @SerializedName("field_type")
    @Expose
    public String fieldType;
    @SerializedName("field_label")
    @Expose
    public String fieldLabel;
    @SerializedName("select_choices_or_calculations")
    @Expose
    public String selectChoicesOrCalculations;
    @SerializedName("field_note")
    @Expose
    public String fieldNote;
    @SerializedName("text_validation_type_or_show_slider_number")
    @Expose
    public String textValidationTypeOrShowSliderNumber;
    @SerializedName("text_validation_min")
    @Expose
    public String textValidationMin;
    @SerializedName("text_validation_max")
    @Expose
    public String textValidationMax;
    @SerializedName("identifier")
    @Expose
    public String identifier;
    @SerializedName("branching_logic")
    @Expose
    public String branchingLogic;
    @SerializedName("required_field")
    @Expose
    public String requiredField;
    @SerializedName("custom_alignment")
    @Expose
    public String customAlignment;
    @SerializedName("question_number")
    @Expose
    public String questionNumber;
    @SerializedName("matrix_group_name")
    @Expose
    public String matrixGroupName;
    @SerializedName("matrix_ranking")
    @Expose
    public String matrixRanking;
    @SerializedName("field_annotation")
    @Expose
    public String fieldAnnotation;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("fieldName", fieldName).append("formName", formName).append("sectionHeader", sectionHeader).append("fieldType", fieldType).append("fieldLabel", fieldLabel).append("selectChoicesOrCalculations", selectChoicesOrCalculations).append("fieldNote", fieldNote).append("textValidationTypeOrShowSliderNumber", textValidationTypeOrShowSliderNumber).append("textValidationMin", textValidationMin).append("textValidationMax", textValidationMax).append("identifier", identifier).append("branchingLogic", branchingLogic).append("requiredField", requiredField).append("customAlignment", customAlignment).append("questionNumber", questionNumber).append("matrixGroupName", matrixGroupName).append("matrixRanking", matrixRanking).append("fieldAnnotation", fieldAnnotation).toString();
    }


}
