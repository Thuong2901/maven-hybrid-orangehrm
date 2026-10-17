
package utilities;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

public interface EnvironmentConfig extends Config {

    @Key("app.url")
    String getAppUrl();
}
