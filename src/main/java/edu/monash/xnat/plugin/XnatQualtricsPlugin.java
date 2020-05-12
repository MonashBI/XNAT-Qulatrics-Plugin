package edu.monash.xnat.plugin;

import org.nrg.framework.annotations.XnatPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@XnatPlugin(value = "XNATQualtricsPlugin", name = "XNAT Qualtrics Plugin")
@ComponentScan({
        "edu.monash.xnat.plugin.preferences",
        "edu.monash.xnat.plugin.xapi"
})
public class XnatQualtricsPlugin {
    @Bean
    public String xnatQualtricsPluginMessage(){
        return "hello from xnat qualtrics plugin";
    }
}

