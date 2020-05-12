package edu.monash.xnat.plugin.xapi;

import edu.monash.xnat.plugin.preferences.SurveyPrefs;
import edu.monash.xnat.plugin.preferences.SurveyPrefsInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.nrg.framework.annotations.XapiRestController;
import org.nrg.framework.exceptions.NrgServiceException;
import org.nrg.xapi.rest.AbstractXapiRestController;
import io.swagger.annotations.Api;
import org.nrg.xapi.rest.XapiRequestMapping;
import org.nrg.xdat.security.helpers.AccessLevel;
import org.nrg.xdat.security.services.RoleHolder;
import org.nrg.xdat.security.services.UserManagementServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@XapiRestController
@Api(description = "Survey Preferences API")
@RequestMapping("/survey")
public class SurveyPreferencesController extends AbstractXapiRestController {
    @Autowired
    public SurveyPreferencesController(final SurveyPrefs surveyPrefs, final UserManagementServiceI userManagementService, final RoleHolder roleHolder) {
        super(userManagementService, roleHolder);
        _surveyPrefs = surveyPrefs;
    }
    /**
     *
     * sets the preferneces
     * @param jsonbody
     *          the jsonbody
     * @return the response entity
     *
     * */
    @XapiRequestMapping(value = "/projects/{projectId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, restrictTo = AccessLevel.Owner)
    @ApiOperation(value = "Sets the survey preferences")
    @ApiResponses({ @ApiResponse(code = 200, message = "Survey preferences set."),
            @ApiResponse(code = 500, message = "Unexpected error") })
    public ResponseEntity<String> setSurveyProjectPreferences(@PathVariable("projectId") final String projectId,
                                                              @RequestBody SurveyPrefsInfo surveyPrefs) {
        try {
            _surveyPrefs.setSurveyEnabled(projectId, surveyPrefs.getSurveyEnabled());
            _surveyPrefs.setSurveyUrl(projectId, surveyPrefs.getSurveyURL());
            _surveyPrefs.setSurveyQueryParams(projectId, surveyPrefs.getSurveyQueryParams());
            _surveyPrefs.setSurveyQueryType(projectId, surveyPrefs.getSurveyQueryType());

        } catch (Exception exception) {
            System.out.println("ERROR:  Error setting preferences:  ");
            return new ResponseEntity<>("survey preferences assignment failed ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Survey preferences set", HttpStatus.OK);
    }
    /**
     *
     * Gets the preferneces
     * @param jsonbody
     *          the jsonbody
     * @return the response entity
     *
     * */
    @XapiRequestMapping(value = "/projects/{projectId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, restrictTo = AccessLevel.Read)
    @ApiOperation(value = "Gets the survey preferences")
    @ApiResponses({ @ApiResponse(code = 200, message = "Survey preferences retrieved."),
            @ApiResponse(code = 500, message = "Unexpected error") })
    public ResponseEntity<SurveyPrefsInfo> getSurveyProjectPreferences(@PathVariable("projectId") final String projectId) throws NrgServiceException {

        final SurveyPrefsInfo prefsInfo = new SurveyPrefsInfo(_surveyPrefs, projectId);

        return new ResponseEntity<>(prefsInfo, HttpStatus.OK);
    }
    private final SurveyPrefs _surveyPrefs;

}