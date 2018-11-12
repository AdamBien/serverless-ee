
package com.airhacks.serverless.ee.boundary;

/**
 *
 * @author airhacks.com
 */
public interface Logger {

    static void log(Object clazz, String message) {
        System.out.println("ee " + clazz.getClass().getSimpleName() + " " + message);
    }
}
