package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class HelloService {
    final static private Logger LOG = LogManager.getLogger();

    @Autowired
    private SampleProperties sampleProperties;

    public void printProperties() {
        LOG.debug(sampleProperties.getHost());
        LOG.debug(sampleProperties.getPort());
    }
}
