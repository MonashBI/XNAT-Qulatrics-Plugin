package edu.monash.xnat.plugin.preferences;

public class SurveyPrefsInfo{
    private Boolean surveyEnabled;
    private String surveyURL;
    private String surveyQueryParams;
    private String surveyQueryType;

    public SurveyPrefsInfo(SurveyPrefs prefs, String entityId) {
        this.surveyEnabled = prefs.getSurveyEnabled(entityId);
        this.surveyURL = prefs.getSurveyUrl(entityId);
        this.surveyQueryParams = prefs.getSurveyQueryParams(entityId);
        this.surveyQueryType = prefs.getSurveyQueryType(entityId);
    }

    public SurveyPrefsInfo() {
    }

    public Boolean getSurveyEnabled() {
        return surveyEnabled;
    }

    public void setSurveyEnabled(Boolean surveyEnabled) {
        this.surveyEnabled = surveyEnabled;
    }

    public String getSurveyURL() {
        return surveyURL;
    }

    public void setSurveyURL(String surveyURL) {
        this.surveyURL = surveyURL;
    }

    public String getSurveyQueryParams() {
        return surveyQueryParams;
    }

    public void setSurveyQueryParams(String surveyQueryParams) {
        this.surveyQueryParams = surveyQueryParams;
    }

    public String getSurveyQueryType() {
        return surveyQueryType;
    }

    public void setSurveyQueryType(String surveyQueryType) {
        this.surveyQueryType = surveyQueryType;
    }
}