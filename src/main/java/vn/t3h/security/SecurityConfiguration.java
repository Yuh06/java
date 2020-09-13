
package vn.t3h.security;

import vn.t3h.dao.UserPermisionDao;
import vn.t3h.model.UserPermision;
import vn.t3h.services.UserDetailsServiceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 
    Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);
    
    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;
    
    @Autowired
    private UserPermisionDao userPermisionDao;
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Autowired
    private PersistentTokenRepository tokenRepository;
 
    @Autowired // Authen Login
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	// Tim User
        auth.userDetailsService(userDetailsService);
        // So sanh password
        auth.authenticationProvider(authenticationProvider());
    }
 
    @Override // Author phan quyen nguoi dung.
    protected void configure(HttpSecurity http) throws Exception {
        
        String[] antPatterns = {
            "/api/**", "/api-config/**"
        };
        String[] resources = new String[]{
    		"/", "/css/**", "/fonts/**", "/icons/**", "/img/**",
    		"/favicon.ico", "/js/**", "/pages/**", "/api/**"
        };
        
        http.authorizeRequests()
        .antMatchers(resources).permitAll()
        .antMatchers("/about").permitAll()
        .antMatchers("/*-id*").permitAll()
        .antMatchers("/uploads/**").permitAll()
        .antMatchers("/api/auth/**").permitAll()
        .antMatchers("/auth/login").permitAll()
        .antMatchers("/access-deny").permitAll();
        
        List<UserPermision> userPermisions = userPermisionDao.getAllPermision();
        for(UserPermision userP : userPermisions) {
            logger.info("userPermision action:{} ,roles:{}", userP.getAction(), userP.getRoles());
            if("any".equals(userP.getRoles())){
                http.authorizeRequests().antMatchers(userP.getAction()).permitAll();
            } else {
            	http.authorizeRequests().antMatchers(userP.getAction()).access(userP.getRoles());
            }
        }
        
        http.authorizeRequests()
        .anyRequest().authenticated()
        .and().formLogin().loginPage("/auth/login").loginProcessingUrl("/auth/login")
        .usernameParameter("ssoId").passwordParameter("password").defaultSuccessUrl("/auth/auto-redirect", true)
        .and().rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository).tokenValiditySeconds(86400)
        .and().csrf().ignoringAntMatchers(antPatterns)
        .and().exceptionHandling().accessDeniedPage("/auth/access-deny");
    }
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
 
    @Bean
    public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
        PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
                "remember-me", userDetailsService, tokenRepository);
        return tokenBasedservice;
    }
 
    @Bean
    public AuthenticationTrustResolver getAuthenticationTrustResolver() {
        return new AuthenticationTrustResolverImpl();
    }
}
