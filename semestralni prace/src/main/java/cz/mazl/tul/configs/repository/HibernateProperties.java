package cz.mazl.tul.configs.repository;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@ConfigurationProperties(prefix = "cz.mazl.tul.hibernate")
public class HibernateProperties {
    @NotNull
    private String dialect;
    private String showSql;
    private String hdm2ddl;
    private String packageScan;
    @NotNull
    private String driverClass;
    @NotNull
    private String url;
    @NotNull
    private String username;
    @NotNull
    private String password;

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getShowSql() {
        return showSql;
    }

    public void setShowSql(String showSql) {
        this.showSql = showSql;
    }

    public String getHdm2ddl() {
        return hdm2ddl;
    }

    public void setHdm2ddl(String hdm2ddl) {
        this.hdm2ddl = hdm2ddl;
    }

    public String getPackageScan() {
        return packageScan;
    }

    public void setPackageScan(String packageScan) {
        this.packageScan = packageScan;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "HibernateProperties{" +
                "dialect='" + dialect + '\'' +
                ", showSql='" + showSql + '\'' +
                ", hdm2ddl='" + hdm2ddl + '\'' +
                ", packageScan='" + packageScan + '\'' +
                ", driverClass='" + driverClass + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + "****" + '\'' +
                '}';
    }
}
