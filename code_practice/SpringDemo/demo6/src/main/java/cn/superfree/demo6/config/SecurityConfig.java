package cn.superfree.demo6.config;

import cn.superfree.demo6.constant.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {



//  admin: admin 123
//  user: user 123
  @Override
  protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder.inMemoryAuthentication()
            .withUser("admin").roles("admin").password("$2a$10$/XULsr78PmrUy7yZZftJeOAbQmGrBWnE6uNCpRMLY3Tdm0YiAQbTK")
            .and()
            .withUser("user").roles("user").password("$2a$10$/XULsr78PmrUy7yZZftJeOAbQmGrBWnE6uNCpRMLY3Tdm0YiAQbTK");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().anyRequest().authenticated()
            .and()
            .formLogin()
            .and().csrf().disable();
    http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
    CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter();
    customAuthenticationFilter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
      @Override
      public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        Response respBean = Response.ok("login success!");
        out.write(new ObjectMapper().writeValueAsString(respBean));
        out.flush();
        out.close();
      }
    });
    customAuthenticationFilter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
      @Override
      public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        Response respBean = Response.error("login fail!");
        out.write(new ObjectMapper().writeValueAsString(respBean));
        out.flush();
        out.close();
      }
    });
    customAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
    return customAuthenticationFilter;
  }

//  @Override
//  public void configure(WebSecurity web) throws Exception {
//    web.ignoring().antMatchers("/vercode");
//  }

  @Bean
  PasswordEncoder passwordEncoder() {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    String p1 = bCryptPasswordEncoder.encode("123");
    System.out.println(p1);
    return bCryptPasswordEncoder;
//    return new BCryptPasswordEncoder();
  }
}
