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
//            //??????body????????????
//            body = readAsChars(httpServletRequest);
//        }
//        //?????????????????????????????????????????????
//        log.info("????????????===??????:" + url);
//        log.info("????????????===??????:" + method);
//        log.info("????????????===???????????????:" + queryString);
//        log.info("????????????===body??????:" + body);
//        filterChain.doFilter(servletRequest, responseWrapper);
//        // ?????????????????????
//        String outParam = new String(responseWrapper.toByteArray(), responseWrapper.getCharacterEncoding());
//        log.info("???????????????" + outParam);
//
//    }
//
//
//
//
//    public static Map<String, Object> getKeyAndValue(Object obj) {
//        Map<String, Object> map = new HashMap<>();
//        // ???????????????
//        Class userCla = (Class) obj.getClass();
//        /* ????????????????????????????????? */
//        Field[] fs = userCla.getDeclaredFields();
//        for (int i = 0; i < fs.length; i++) {
//            Field f = fs[i];
//            f.setAccessible(true); // ?????????????????????????????????
//            Object val = new Object();
//            try {
//                val = f.get(obj);
//                // ?????????????????????
//                map.put(f.getName(), val);// ????????????
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
//            // ???????????????
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
////        // ??????html?????????js???????????????
////        if (httpServletRequest.getRequestURI().matches(ignoreUrlRegex)) {
////            ThreadContext.clearAll();
////            filterChain.doFilter(servletRequest, responseWrapper);
////            return;
////        }
////
////        Map params;
////        // ????????????
////        log.info("?????????URL???" + httpServletRequest.getRequestURI());
////
////        filterChain.doFilter(requestWrapper, responseWrapper);
////
////        // ??????from?????????????????????
////        params = servletRequest.getParameterMap();
////        if (null != params && params.size() != 0) {
////            log.info("?????????" + JSON.toJSONString(params));
////        } else {
////            // ??????json?????????????????????
////            String charEncoding = requestWrapper.getCharacterEncoding() != null ? requestWrapper.getCharacterEncoding() : "UTF-8";
////            log.info("??????" + new String(requestWrapper.toByteArray(), charEncoding));
////        }
////
////        // ????????????
////        String outParam = new String();
////        // ?????????????????????
////        params = new HashMap();
////        // ?????????????????????errorCode??????????????????????????????????????????
////        try {
////            params.put("errorCode", ((ResponseFacade) servletResponse).getHeader("errorCode"));
////            params.put("errorMsg", (URLDecoder.decode(((ResponseFacade) servletResponse).getHeader("errorMsg"), "UTF-8")));
////            outParam = JSON.toJSONString(params);
////        } catch (Exception e) {
////        }
////
////        // ?????????????????????
////        if (params.size() < 2) {
////            outParam = outParam + new String(responseWrapper.toByteArray(), responseWrapper.getCharacterEncoding());
////        }
////
////        log.info("?????????" + outParam);
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