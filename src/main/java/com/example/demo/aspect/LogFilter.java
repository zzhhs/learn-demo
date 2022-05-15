//package com.example.demo.aspect;
//
//
//import cn.hutool.json.JSONUtil;
//import com.alibaba.fastjson.JSON;
//import org.apache.catalina.connector.ResponseFacade;
//import org.apache.commons.io.IOUtils;
//import org.apache.logging.log4j.ThreadContext;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import com.alibaba.fastjson.JSON;
//import com.github.isrsal.logging.RequestWrapper;
//import com.github.isrsal.logging.ResponseWrapper;
//import org.apache.catalina.connector.RequestFacade;
//import org.apache.catalina.connector.ResponseFacade;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.logging.log4j.ThreadContext;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpServletResponseWrapper;
//import java.io.*;
//import java.lang.reflect.Field;
//import java.net.URLDecoder;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//import static com.sun.deploy.net.protocol.ProtocolType.HTTP;
//
///**
// * @author m.feng
// * @create 2019-01-03-14:31
// */
//@Component
//@WebFilter(filterName="logFilter",urlPatterns={"/wyw", "/wyw2", "/wyw3"})
//public class LogFilter implements Filter {
//    private static final Logger log = LoggerFactory.getLogger(LogFilter.class);
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        ThreadContext.put("TId", UUID.randomUUID().toString());
//        ResponseWrapper  responseWrapper = new ResponseWrapper (Thread.currentThread().getId(), (HttpServletResponse) servletResponse);
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        RequestWrapper requestWrapper = new RequestWrapper(Thread.currentThread().getId(), httpServletRequest);
//
//        String url = httpServletRequest.getRequestURL().toString();
//        String method = httpServletRequest.getMethod();
//        String uri = httpServletRequest.getRequestURI();
//        String queryString = httpServletRequest.getQueryString();
//        String body = null;
//        if(method.equals("POST")){
//            //读取body中的参数
//            body = readAsChars(httpServletRequest);
//        }
//        //获取请求参数集合并进行遍历拼接
//        log.info("请求开始===地址:" + url);
//        log.info("请求开始===类型:" + method);
//        log.info("请求开始===地址栏参数:" + queryString);
//        log.info("请求开始===body参数:" + body);
//        filterChain.doFilter(servletRequest, responseWrapper);
//        // 记录出参响应体
//        String outParam = new String(responseWrapper.toByteArray(), responseWrapper.getCharacterEncoding());
//        log.info("请求结束：" + outParam);
//
//    }
//
//
//
//
//    public static Map<String, Object> getKeyAndValue(Object obj) {
//        Map<String, Object> map = new HashMap<>();
//        // 得到类对象
//        Class userCla = (Class) obj.getClass();
//        /* 得到类中的所有属性集合 */
//        Field[] fs = userCla.getDeclaredFields();
//        for (int i = 0; i < fs.length; i++) {
//            Field f = fs[i];
//            f.setAccessible(true); // 设置些属性是可以访问的
//            Object val = new Object();
//            try {
//                val = f.get(obj);
//                // 得到此属性的值
//                map.put(f.getName(), val);// 设置键值
//            } catch (IllegalArgumentException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//
//        }
//        return map;
//    }
//
//
//    public static String readAsChars(HttpServletRequest request) throws IOException {
//        BufferedReader br = null;
//        try {
//            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
//            String line = null;
//            StringBuilder sb = new StringBuilder();
//            while((line = br.readLine())!=null){
//                sb.append(line);
//            }
//
//            // 将资料解码
//            String reqBody = sb.toString();
//            return URLDecoder.decode(reqBody, "utf-8");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            //br.close();
//        }
//        return null;
//    }
//
////    @Override
////    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
////        ThreadContext.put("TId", UUID.randomUUID().toString());
////        ResponseWrapper  responseWrapper = new ResponseWrapper (Thread.currentThread().getId(), (HttpServletResponse) servletResponse);
////        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
////        RequestWrapper requestWrapper = new RequestWrapper(Thread.currentThread().getId(), httpServletRequest);
////
////        // 请求html页面、js不打印日志
////        if (httpServletRequest.getRequestURI().matches(ignoreUrlRegex)) {
////            ThreadContext.clearAll();
////            filterChain.doFilter(servletRequest, responseWrapper);
////            return;
////        }
////
////        Map params;
////        // 记录入参
////        log.info("请求的URL：" + httpServletRequest.getRequestURI());
////
////        filterChain.doFilter(requestWrapper, responseWrapper);
////
////        // 打印from格式的入参信息
////        params = servletRequest.getParameterMap();
////        if (null != params && params.size() != 0) {
////            log.info("入参：" + JSON.toJSONString(params));
////        } else {
////            // 打印json格式的入参信息
////            String charEncoding = requestWrapper.getCharacterEncoding() != null ? requestWrapper.getCharacterEncoding() : "UTF-8";
////            log.info("入参" + new String(requestWrapper.toByteArray(), charEncoding));
////        }
////
////        // 记录出参
////        String outParam = new String();
////        // 记录出参响应头
////        params = new HashMap();
////        // 如果响应头存在errorCode则打印，除文件下载外均不存在
////        try {
////            params.put("errorCode", ((ResponseFacade) servletResponse).getHeader("errorCode"));
////            params.put("errorMsg", (URLDecoder.decode(((ResponseFacade) servletResponse).getHeader("errorMsg"), "UTF-8")));
////            outParam = JSON.toJSONString(params);
////        } catch (Exception e) {
////        }
////
////        // 记录出参响应体
////        if (params.size() < 2) {
////            outParam = outParam + new String(responseWrapper.toByteArray(), responseWrapper.getCharacterEncoding());
////        }
////
////        log.info("出参：" + outParam);
////
////        ThreadContext.clearAll();
////    }
//
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//    }
//
//    @Override
//    public void destroy() {
//    }
//}