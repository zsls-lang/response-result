/**
 * Company
 * Copyright (C) 2004-2020 All Rights Reserved.
 */
package com.zsls.common.utils;

import java.net.InetAddress;

/**
 * @author zsls
 * @version $Id InetUtils.java, v 0.1 2020-12-29 16:58  Exp $$
 */
public class InetUtils {

    private static final String DEFAULT_HOST = "localhost";
    public static String getHost() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            return addr.getHostAddress();
        } catch (Exception var2) {
            return DEFAULT_HOST;
        }
    }
}
