package com.example.pcbuilder.common.log;

import org.apache.logging.log4j.LogManager;
import org.springframework.security.core.context.SecurityContextHolder;

public class Log {

    private static final StackWalker stackWalker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);

    public static void d(String message) {
        LogManager.getLogger(stackWalker.getCallerClass()).debug(message);
//        System.out.println(specialInfo(stackWalker.getCallerClass(), "DEBUG") + message);
    }

    public static void d(String message, Object... args) {
        LogManager.getLogger(stackWalker.getCallerClass()).debug(String.format(message, args));
//        System.out.println(specialInfo(stackWalker.getCallerClass(), "DEBUG") + String.format(message, args));
    }

    public static void v(String message) {
        LogManager.getLogger(stackWalker.getCallerClass()).trace(message);
    }

    public static void v(String message, Object... args) {
        LogManager.getLogger(stackWalker.getCallerClass()).trace(String.format(message, args));
    }

    public static void i(String message) {
        LogManager.getLogger(stackWalker.getCallerClass()).info(specialInfo(message));
    }

    public static void i(String message, Object... args) {
        LogManager.getLogger(stackWalker.getCallerClass()).info(specialInfo(String.format(message, args)));
    }

    public static void e(String message) {
        LogManager.getLogger(stackWalker.getCallerClass()).error(message);
//        System.out.println(specialInfo(stackWalker.getCallerClass(), "ERROR") + message);
    }

    public static void e(Throwable throwable) {
        LogManager.getLogger(stackWalker.getCallerClass()).error(throwable);
//        System.out.println(specialInfo(stackWalker.getCallerClass(), "ERROR") + throwable.getMessage());
//        Arrays.stream(throwable.getStackTrace()).forEach(System.out::println);
    }

//    private static String specialInfo(Class<?> callerClass, String mode) {
//        return ZonedDateTime.now() + "\t[" + mode + "]\t" + callerClass.getCanonicalName() + ":\t";
//    }

    private static String specialInfo(String message) {
        return message + "\tUsername: " + getCurrentUsername();
    }

    private static String getCurrentUsername() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
    }
}
