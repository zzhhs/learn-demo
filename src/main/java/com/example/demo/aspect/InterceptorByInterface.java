//package com.example.demo.aspect;
//
//import cn.hutool.http.HttpResponse;
//import cn.hutool.json.JSONUtil;
//import com.alibaba.fastjson.JSON;
//import com.github.isrsal.logging.ResponseWrapper;
//import org.apache.catalina.connector.ResponseFacade;
//import org.apache.commons.codec.Charsets;
//import org.springframework.lang.Nullable;
//import org.springframework.util.ReflectionUtils;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.xml.ws.RequestWrapper;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.OutputStream;
//import java.io.PrintWriter;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.net.URLDecoder;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Set;
//
////通过实现HandlerInterceptor接口实现拦截,这个类需要注册
//public class InterceptorByInterface implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        System.out.println("此路是我开，此树是我栽，要想从此过，留下买路财");
//        //输出请求路径
//        System.out.println(request.getRequestURI());
//        //true表示请求放行，false表示拦截请求，不再向下寻找处理器
//
//
//        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
//
//        String url = request.getRequestURL().toString();
//        String method = request.getMethod();
//        String uri = request.getRequestURI();
//        String queryString = request.getQueryString();
//        //Object[] args = pjp.getArgs();
//        String params = "";
//        //获取请求参数集合并进行遍历拼接
////        if(args.length>0){
////            if("POST".equals(method)){
////                Object object = args[0];
////                Map map = getKeyAndValue(object);
////                params = JSONUtil.toJsonPrettyStr(map);
////            }else if("GET".equals(method)){
//                params = queryString;
////            }
////        }
//
//
//        System.out.println("请求开始===地址:"+url);
//        System.out.println("请求开始===类型:"+method);
//        System.out.println("请求开始===参数:"+params);
//
//        return true;
//    }
//
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
//        //System.out.println(JSONUtil.toJsonStr(modelAndView.getModel()));
//        //System.out.println(JSONUtil.toJsonStr(modelAndView.getView()));
//        //System.out.println(JSONUtil.toJsonStr(modelAndView.getModelMap()));
//        System.out.println("22222222222");
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
//        ResponseWrapper responseWrapper = new ResponseWrapper (Thread.currentThread().getId(), response);
//        // 记录出参
//        String outParam = new String();
//        // 记录出参响应头
//        Map params = new HashMap();
//        // 如果响应头存在errorCode则打印，除文件下载外均不存在
////        try {
////            params.put("errorCode", ((ResponseFacade) response).getHeader("errorCode"));
////            params.put("errorMsg", (URLDecoder.decode(((ResponseFacade) response).getHeader("errorMsg"), "UTF-8")));
////            outParam = JSON.toJSONString(params);
////        } catch (Exception e) {
////        }
//        outParam = new String(responseWrapper.toByteArray(), responseWrapper.getCharacterEncoding());
//        System.out.println("33333333333333333333"+outParam);
//    }
//}