package com.mayday.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm1 implements Realm {
    @Override
    public String getName() {
        return "myRealm1";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {

        //仅支持UsernamePasswordToken类型
        return authenticationToken  instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //得到用户名
        String userName=(String)authenticationToken.getPrincipal();
        //得到密码
        String password=new String((char[])authenticationToken.getCredentials());
        if(!"zhang".equals(userName)){  //如果用户名错误,抛出未知用户异常
            throw  new UnknownAccountException();
        }
        if(!"1234".equals(password)){  //如果密码错误，抛出IncorrectCredentialsException异常
            throw  new IncorrectCredentialsException();
        }

        //如果用户名密码校验成功，返回一个AuthenticationInfo实现
        return new SimpleAuthenticationInfo(userName,password,getName());
    }
}
