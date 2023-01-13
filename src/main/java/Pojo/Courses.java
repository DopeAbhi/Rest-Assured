package Pojo;

import java.security.PrivateKey;
import java.util.List;

public class Courses
{
    private List<WebAutomation> webAutomation;
    private  List<Api> api;
    private List<Mobile> mobile;

    public List<WebAutomation> getWebAutomation() {
        return webAutomation;
    }

    public void setWebAutomation(List<WebAutomation> webAutomation) {
        this.webAutomation = webAutomation;
    }

    public void setApi(List<Api> api) {
        this.api = api;
    }

    public List<Api> getApi() {
        return api;
    }

    public void setMobile(List<Mobile> mobile) {
        this.mobile = mobile;
    }

    public List<Mobile> getMobile() {
        return mobile;
    }
}

