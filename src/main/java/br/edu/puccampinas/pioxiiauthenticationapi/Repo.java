package br.edu.puccampinas.pioxiiauthenticationapi;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AuthenticatedLdapEntryContextMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapEntryIdentification;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.stereotype.Repository;

@Repository
public class Repo {
	
    @Autowired
    private LdapTemplate LdapTemplate;
    
    public boolean auth(String uid, String password){
        boolean authenticated = false;
        try{
            AuthenticatedLdapEntryContextMapper<DirContextOperations> mapper = authenticatedLdapEntryContextMapper();
            DirContextOperations dco = LdapTemplate.authenticate(LdapQueryBuilder.query().where("uid").is(uid), password, mapper);
            authenticated = ((dco!=null) && (dco.getStringAttribute(uid).equals(uid)));
        }
        catch(Exception e) {
            System.out.println("login failed " + e.getMessage());
        }
        return authenticated;
    }

    private AuthenticatedLdapEntryContextMapper<DirContextOperations> authenticatedLdapEntryContextMapper(){
       return (DirContext ctx, LdapEntryIdentification ldapEntryIdentification) -> {
            try{
                return (DirContextOperations) ctx.lookup(ldapEntryIdentification.getRelativeName());
            }
            catch(NamingException e) {
                throw new RuntimeException("Lookup failed for: " + ldapEntryIdentification.getRelativeName(), e);
            }
        };
    }
}

