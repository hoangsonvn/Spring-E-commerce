package com.demo6.shop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
@PropertySource(value = "classpath:application.properties")

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static List<String> clients = Arrays.asList("google", "facebook");
    private static String CLIENT_PROPERTY_KEY
            = "spring.security.oauth2.client.registration.";
    @Autowired
    private Environment env;

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        List<ClientRegistration> registrations = clients.stream()
                .map(c -> getRegistration(c))
                .filter(registration -> registration != null)
                .collect(Collectors.toList());

        return new InMemoryClientRegistrationRepository(registrations);
    }

    private ClientRegistration getRegistration(String client) {
        String clientId = env.getProperty(
                CLIENT_PROPERTY_KEY + client + ".client-id");

        if (clientId == null) {
            return null;
        }

        String clientSecret = env.getProperty(
                CLIENT_PROPERTY_KEY + client + ".client-secret");

        if (client.equals("google")) {
            return CommonOAuth2Provider.GOOGLE.getBuilder(client)
                    .clientId(clientId).clientSecret(clientSecret).build();
        }
        if (client.equals("facebook")) {
            return CommonOAuth2Provider.FACEBOOK.getBuilder(client)
                    .clientId(clientId).clientSecret(clientSecret).build();
        }
        return null;
    }


    @Autowired
    UserDetailsService userDetailsService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
		/*http.csrf().disable().cors().disable().authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll().and().exceptionHandling().accessDeniedPage("/logout");*/
        http.csrf().disable().cors().disable()
                .authorizeRequests()
             //   .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/client/profile/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/client/my-order/**","/client/pay/**","/client/order-details/**").hasAnyRole("USER","ADMIN")
                .anyRequest().permitAll().and().exceptionHandling().accessDeniedPage("/403");
        http.rememberMe().key("uniqueAndSecret").tokenValiditySeconds(129600);

        http.formLogin().loginPage("/login").loginProcessingUrl("/j_spring_security_check").usernameParameter("account")
                .passwordParameter("password").defaultSuccessUrl("/home")
                .failureUrl("/login?err=Invalid. Please try again?");
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/client/home");
        http.authorizeRequests()
//                .anyRequest().permitAll()
                .and()
                .oauth2Login()
                .loginPage("/oauth_login")

                .defaultSuccessUrl("/loginSuccess")
                .clientRegistrationRepository(clientRegistrationRepository())
                .authorizedClientService(authorizedClientService());


    }
    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {

        return new InMemoryOAuth2AuthorizedClientService(
                clientRegistrationRepository());
    }


}
