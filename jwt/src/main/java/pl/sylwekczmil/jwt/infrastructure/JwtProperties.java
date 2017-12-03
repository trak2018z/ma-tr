package pl.sylwekczmil.jwt.infrastructure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sczmil on 6/6/2017. *
 */
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {


    private String secret;
    private String authLink;
    private Long expiration = 604800L;
    private String header = "Authorization";
    private List<String> notAuthenticatedLinks = new ArrayList<>();
    private List<String> notCheckedLinks = new ArrayList<>();
    private boolean downloadUserDetailsFromDatabaseForEveryRequestActive = true;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getAuthLink() {
        return authLink;
    }

    public void setAuthLink(String authLink) {
        this.authLink = authLink;
    }

    public List<String> getNotAuthenticatedLinks() {
        return notAuthenticatedLinks;
    }

    public void setNotAuthenticatedLinks(List<String> notAuthenticatedLinks) {
        this.notAuthenticatedLinks = notAuthenticatedLinks;
    }

    public List<String> getNotCheckedLinks() {
        return notCheckedLinks;
    }

    public void setNotCheckedLinks(List<String> notCheckedLinks) {
        this.notCheckedLinks = notCheckedLinks;
    }

    public boolean isDownloadUserDetailsFromDatabaseForEveryRequestActive() {
        return downloadUserDetailsFromDatabaseForEveryRequestActive;
    }

    public void setDownloadUserDetailsFromDatabaseForEveryRequestActive(boolean downloadUserDetailsFromDatabaseForEveryRequestActive) {
        this.downloadUserDetailsFromDatabaseForEveryRequestActive = downloadUserDetailsFromDatabaseForEveryRequestActive;
    }

    @Override
    public String toString() {
        return "JwtProperties {" +
                " authLink='" + authLink + '\'' +
                ", expiration=" + expiration +
                ", header='" + header + '\'' +
                ", notAuthenticatedLinks=" + notAuthenticatedLinks +
                ", notCheckedLinks=" + notCheckedLinks +
                " }";
    }
}
