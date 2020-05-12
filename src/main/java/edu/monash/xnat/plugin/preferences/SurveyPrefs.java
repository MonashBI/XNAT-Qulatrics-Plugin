package edu.monash.xnat.plugin.preferences;

import org.nrg.framework.constants.Scope;
import org.nrg.prefs.annotations.NrgPreference;
import org.nrg.prefs.annotations.NrgPreferenceBean;
import org.nrg.prefs.entities.Preference;
import org.nrg.prefs.beans.AbstractPreferenceBean;
import org.nrg.prefs.exceptions.InvalidPreferenceName;
import org.nrg.prefs.services.NrgPreferenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NrgPreferenceBean(toolId = "surveyPlugin", toolName = "Survey Plugin Preferences")
public class SurveyPrefs extends AbstractPreferenceBean {
    @Autowired
    protected SurveyPrefs(final NrgPreferenceService preferenceService) {
        super(preferenceService);
    }

    public static final Scope SCOPE = Scope.Project;
    public static final String SURVEY_ENABLED = "surveyEnabled";
    public static final String SURVEY_URL = "surveyUrl";
    public static final String SURVEY_QUERY_PARAMS = "surveyQueryParams";
    public static final String SURVEY_QUERY_TYPE = "surveyQueryType";
    @NrgPreference
    public Boolean getSurveyEnabled() {
        return null;
    }

    public Boolean getSurveyEnabled(final String entityId) {
        return this.getBooleanValue(SCOPE, entityId, SURVEY_ENABLED);
    }
    public void setSurveyEnabled(final String entityId, final Boolean surveyEnabled) {
        try {
            removeSiteLevelPreferenceIfExists(SURVEY_ENABLED);
            this.setBooleanValue(SCOPE, entityId, surveyEnabled, SURVEY_ENABLED);
        } catch (InvalidPreferenceName invalidPreferenceName) {
            System.out.println("Invalid Survey preference name");
        }
    }

    @NrgPreference
    public static  String getSurveyQueryParams() {
        return null;
    }

    public String getSurveyQueryParams(final String entityId) {
        return this.getValue(SCOPE, entityId, SURVEY_QUERY_PARAMS);
    }
    public void setSurveyQueryParams(final String entityId, final String surveyQueryParams) {
        try {
            removeSiteLevelPreferenceIfExists(SURVEY_QUERY_PARAMS);
            this.set(SCOPE, entityId, surveyQueryParams, SURVEY_QUERY_PARAMS);
        } catch (InvalidPreferenceName invalidPreferenceName) {
            System.out.println("Invalid Query Params preference name");
        }
    }

    @NrgPreference
    public static String getSurveyUrl() {
        return null;
    }

    public String getSurveyUrl(final String entityId) {
        return this.getValue(SCOPE, entityId, SURVEY_URL);
    }

    public void setSurveyUrl(final String entityId, final String url) {
        try {
            removeSiteLevelPreferenceIfExists(SURVEY_URL);
            this.set(SCOPE, entityId, url, SURVEY_URL);
        } catch (InvalidPreferenceName invalidPreferenceName) {
            System.out.println("Invalid Survey URL preference name");
        }
    }

    @NrgPreference
    public static String getSurveyQueryType() {
        return null;
    }

    public String getSurveyQueryType(final String entityId){
        return this.getValue(SCOPE,entityId,SURVEY_QUERY_TYPE);
    }

    public void setSurveyQueryType(final String entityId, final String queryType){
        try {
            removeSiteLevelPreferenceIfExists(SURVEY_QUERY_TYPE);
            this.set(SCOPE, entityId, queryType, SURVEY_QUERY_TYPE);
        } catch (InvalidPreferenceName invalidPreferenceName) {
            System.out.println("Invalid Query Type preference name");
        }
    }
    // TODO:  Workaround method (XNAT-5134)
    private void removeSiteLevelPreferenceIfExists(String key) {
        try {
            if (sitePreferenceExists(key) != null) {
                this.delete(Scope.Site,"",key);
            }
        } catch (InvalidPreferenceName e) {
            // Do nothing.
        }
    }
    // TODO:  Workaround method (XNAT-5134)
    private Preference sitePreferenceExists(String key) {
        Preference p = this.getPreference(Scope.Site, "", key);
        if (p != null) {
            _logger.error("ERROR:  Found site level preference where only project preferences should exist (KEY=" + key + ").");

        }
        return p;
    }
    private static final Logger _logger = LoggerFactory.getLogger(SurveyPrefs.class);
}