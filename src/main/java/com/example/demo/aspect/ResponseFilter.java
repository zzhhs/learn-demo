//package com.example.demo.aspect;
//
//
//
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.WriteListener;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpServletResponseWrapper;
//
//
//
//
//@Order(1)
//@Component
//@WebFilter(urlPatterns="/**" ,filterName="responseFilter")
//public class ResponseFilter implements Filter{
//
//    @Override
//    public void destroy() {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
//            throws IOException, ServletException {
//        // TODO Auto-generated method stub
//        ServletResponseWrapper responseWrapper=new ServletResponseWrapper((HttpServletResponse)arg1);
//        arg2.doFilter(arg0, responseWrapper);
//        System.out.println(((HttpServletRequest)arg0).getServletPath());
//        System.out.println(responseWrapper.getResponseBody());
//    }
//
//    @Override
//    public void init(FilterConfig arg0) throws ServletException {
//        // TODO Auto-generated method stub
//
//    }
//
//    public static class MonitorOutoutStream extends ServletOutputStream{
//
//        private ServletOutputStream output;
//        private ByteArrayOutputStream copy=new ByteArrayOutputStream();
//
//
//
//        public MonitorOutoutStream(ServletOutputStream output) {
//            super();
//            this.output = output;
//        }
//
//        @Override
//        public boolean isReady() {
//            // TODO Auto-generated method stub
//            return output.isReady();
//        }
//
//        @Override
//        public void setWriteListener(WriteListener arg0) {
//            // TODO Auto-generated method stub
//            output.setWriteListener(arg0);
//        }
//
//        @Override
//        public void write(int b) throws IOException {
//            // TODO Auto-generated method stub
//            output.write(b);
//            copy.write(b);
//        }
//
//        @Override
//        public void write(byte[] b) throws IOException {
//            // TODO Auto-generated method stub
//            output.write(b);
//            copy.write(b);
//        }
//
//        @Override
//        public void write(byte[] b, int off, int len) throws IOException {
//            // TODO Auto-generated method stub
//            output.write(b,off,len);
//            copy.write(b,off,len);
//        }
//
//        public byte[] getWroteInfo() {
//            return copy.toByteArray();
//        }
//
//        @Override
//        public void flush() throws IOException {
//            // TODO Auto-generated method stub
//            output.flush();
//            copy.close();
//        }
//
//        @Override
//        public void close() throws IOException {
//            // TODO Auto-generated method stub
//            output.close();
//            copy.close();
//        }
//
//
//    }
//
//    public static class ServletResponseWrapper extends HttpServletResponseWrapper{
//
//        private  volatile MonitorOutoutStream mos;
//
//        public ServletResponseWrapper(HttpServletResponse response) {
//            super(response);
//            // TODO Auto-generated constructor stub
//        }
//
//
//        @Override
//        public ServletOutputStream getOutputStream() throws IOException {
//            // TODO Auto-generated method stub
//            if(mos==null){
//                synchronized (this) {
//                    if(mos==null){
//                        mos = new  MonitorOutoutStream(super.getOutputStream());
//                    }
//                }
//            }
//            return mos;
//        }
//
//        public String getResponseBody(){
//
//            return new String(mos.getWroteInfo());
//
//
//        }
//    }
//}