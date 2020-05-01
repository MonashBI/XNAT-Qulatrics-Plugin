package edu.monash.xnat.plugin;

import org.nrg.framework.annotations.XnatPlugin;
import org.springframework.context.annotation.Bean;

@XnatPlugin(value = "XNATQualtricsPlugin", name = "XNAT Qualtrics Plugin")

public class XnatQulatricsPlugin {
    @Bean
    public String xnatQulatricsPluginMessage(){
        return "hello from xnat qualtrics plugin";
    }
}

