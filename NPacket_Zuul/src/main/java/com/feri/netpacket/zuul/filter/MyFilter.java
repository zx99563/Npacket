package com.feri.netpacket.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: Npacket_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-06-05 17:37
 */
@Component
public class MyFilter extends ZuulFilter {
    /**
     * 过滤器的类型：
     * 1.pre 前置
     * 2.routing 过滤中
     * 3.post 后置*/
    @Override
    public String filterType() {
        return "pre";
    }
    /**
    * 优先级 排序 值
     * 决定了执行顺序 越小越先执行*/
    @Override
    public int filterOrder() {
        return 1;
    }
    /**
     * 是否启用过滤器
     * */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤方法
     * 每次需要传递版本号 参数：version*/
    @Override
    public Object run() throws ZuulException {
        RequestContext context=RequestContext.getCurrentContext();
        HttpServletRequest request=context.getRequest();
        if(request.getRequestURI().endsWith(".do")) {
            if (request.getParameter("version") != null) {
                //放行
            } else {
                //拦截
                context.setSendZuulResponse(false);
                HttpServletResponse response = context.getResponse();
                response.setContentType("text/html;charset=UTF-8");
                try {
                    response.getWriter().println("请传递Version 版本号");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
