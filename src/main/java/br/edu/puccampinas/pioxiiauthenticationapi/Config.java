package br.edu.puccampinas.pioxiiauthenticationapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
public class Config {

    @Bean
    public LdapContextSource ldapContextSource(){
        LdapContextSource lcs = new LdapContextSource();
        lcs.setUrl("ldap://myldap"); 
        lcs.setBase("DC=abc,DC=com,DC=br");
        return lcs;
    }
    @Bean
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(ldapContextSource());
    }
}
