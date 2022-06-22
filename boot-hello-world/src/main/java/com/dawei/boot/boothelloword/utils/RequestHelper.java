package com.dawei.boot.boothelloword.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/**
 * @author by Dawei on 2018/8/22. Request参数处理
 */
public class RequestHelper {

    private static final Logger logger = LoggerFactory.getLogger(RequestHelper.class);

    /* ###################################### Attribute 与 Reuqest ################################# */

    /* Request 域中的获取的基本方法 */
    private static String getStringParm(HttpServletRequest request, String parmName) {
        if (request != null && !"".equals(parmName)) {
            return request.getParameter(parmName);
        }
        return null;
    }

    public static String getStringParm(HttpServletRequest request, String parmName,
                                       String defaultValue) {
        return getStringParm(request, parmName) == null ? defaultValue
                : getStringParm(request, parmName);

    }

    public static Integer getIntParm(HttpServletRequest request, String parmName, Integer defaultValue) {
        Integer result = defaultValue;
        String stringParm = getStringParm(request, parmName);
        if (stringParm != null && !"".equals(stringParm)) {
            try {
                result = Integer.parseInt(stringParm);
            } catch (Exception e) {
                logger.warn("getIntParm 类型转化异常， parmName={}， e=", parmName, e);
            }
        }
        return result;
    }

    public static Double getDoubleParm(HttpServletRequest request, String parmName, Double defaultValue) {
        Double result = defaultValue;
        String stringParm = getStringParm(request, parmName);
        if (stringParm != null && !"".equals(stringParm)) {
            try {
                result = Double.parseDouble(stringParm);
            } catch (Exception e) {
                logger.warn("getDoubleParm 类型转化异常， parmName={}， e=", parmName, e);
            }

        }
        return result;
    }

    public static Long getLongParm(HttpServletRequest request, String parmName, Long defaultValue) {
        Long result = defaultValue;
        String stringParm = getStringParm(request, parmName);
        if (stringParm != null && !"".equals(stringParm)) {
            try {
                result = Long.parseLong(stringParm);
            } catch (Exception e) {
                logger.warn("getLongParm 类型转化异常， parmName={}， e=", parmName, e);
            }
        }
        return result;
    }

    /* ###################################### Header ################################# */

    /* Header 域中的获取的基本方法 */
    private static String getHeaderStringPara(HttpServletRequest request, String headerName) {
        String result = null;
        if (request != null && !"".equals(headerName)) {
            result = request.getHeader(headerName);
        }
        return result;
    }

    public static String getHeaderStringParm(HttpServletRequest request, String parmName,
                                             String defaultValue) {
        return getHeaderStringPara(request, parmName) == null ? defaultValue
                : getHeaderStringPara(request, parmName);

    }

    public static Integer getHeaderIntParm(HttpServletRequest request, String parmName,
                                           Integer defaultValue) {
        Integer result = defaultValue;
        String stringParm = getHeaderStringPara(request, parmName);
        if (stringParm != null && !"".equals(stringParm)) {
            try {
                result = Integer.parseInt(stringParm);
            } catch (Exception e) {
                logger.warn("类型转化异常， parmName={}， e=", parmName, e);
            }
        }
        return result;
    }

    public static Double getHeaderDoubleParm(HttpServletRequest request, String parmName,
                                             Double defaultValue) {
        Double result = defaultValue;
        String stringParm = getHeaderStringPara(request, parmName);
        if (stringParm != null && !"".equals(stringParm)) {
            try {
                result = Double.parseDouble(stringParm);
            } catch (Exception e) {
                logger.warn("getHeaderDoubleParm 类型转化异常， parmName={}， e=", parmName, e);
            }
        }
        return result;
    }

    public static Long getHeaderLongParm(HttpServletRequest request, String parmName, Long defaultValue) {
        Long result = defaultValue;
        String stringParm = getHeaderStringPara(request, parmName);
        if (stringParm != null && !"".equals(stringParm)) {
            try {
                result = Long.parseLong(stringParm);
            } catch (Exception e) {
                logger.warn("getHeaderLongParm 类型转化异常， parmName={}， e=", parmName, e);
            }
        }
        return result;
    }


    /* ###################################### Cookie ################################# */

    /* Cookie 域中的获取的基本方法 */
    private static String getCookieStringPara(HttpServletRequest request, String cookieName) {
        if (request != null && !"".equals(cookieName)) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equalsIgnoreCase(cookieName)) {
                        return cookie.getValue();
                    }
                }
            }
        }
        return null;
    }

    public static String getCookieStringParm(HttpServletRequest request, String parmName,
                                             String defaultValue) {
        return getCookieStringPara(request, parmName) == null ? defaultValue
                : getCookieStringPara(request, parmName);

    }

    public static Integer getCookieIntParm(HttpServletRequest request, String parmName,
                                           Integer defaultValue) {
        Integer result = defaultValue;
        String stringParm = getCookieStringPara(request, parmName);
        if (stringParm != null && !"".equals(stringParm)) {
            try {
                result = Integer.parseInt(stringParm);
            } catch (Exception e) {
                logger.warn("getCookieIntParm 类型转化异常， parmName={}， e=", parmName, e);
            }
        }
        return result;
    }

    public static Double getCookieDoubleParm(HttpServletRequest request, String parmName,
                                             Double defaultValue) {
        Double result = defaultValue;
        String stringParm = getCookieStringPara(request, parmName);
        if (stringParm != null && !"".equals(stringParm)) {
            try {
                result = Double.parseDouble(stringParm);
            } catch (Exception e) {
                logger.warn("getCookieDoubleParm 类型转化异常， parmName={}， e=", parmName, e);
            }
        }
        return result;
    }

    public static Long getCookieLongParm(HttpServletRequest request, String parmName, Long defaultValue) {
        Long result = defaultValue;
        String stringParm = getCookieStringPara(request, parmName);
        if (stringParm != null && !"".equals(stringParm)) {
            try {
                result = Long.parseLong(stringParm);
            } catch (Exception e) {
                logger.warn("getCookieLongParm 类型转化异常， parmName={}， e=", parmName, e);
            }
        }
        return result;
    }


}
