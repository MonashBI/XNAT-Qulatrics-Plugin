package edu.monash.xnat.plugin;

import org.nrg.framework.annotations.XnatPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@XnatPlugin(value = "surveyPlugin", name = "XNAT Survey Plugin")
@ComponentScan({
        "edu.monash.xnat.plugin.preferences",
        "edu.monash.xnat.plugin.xapi"
})
public class SurveyPlugin {
    @Bean
    public String surveyPluginMessage(){
        return "hello from xnat qualtrics plugin";
    }
}

