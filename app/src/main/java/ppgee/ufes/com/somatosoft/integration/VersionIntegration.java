package ppgee.ufes.com.somatosoft.integration;

import ppgee.ufes.com.somatosoft.integration.payload.VersionPayload;

public class VersionIntegration {
    public void check(Integer actualVersion) {
        VersionPayload payload = new VersionPayload();

        if (payload.getVersion() > actualVersion) {
            // show alert
        }

        return;
    }
}