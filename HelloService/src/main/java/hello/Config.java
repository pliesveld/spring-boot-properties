package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({SampleProperties.class})
public class Config {

    @Autowired
    private SampleProperties sampleProperties;

    public SampleProperties getSampleProperties() {
        return sampleProperties;
    }

    public void setSampleProperties(SampleProperties sampleProperties) {
        this.sampleProperties = sampleProperties;
    }
}
