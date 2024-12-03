package com.example.pcbuilder.common.log;

import java.time.ZonedDateTime;
import java.util.Arrays;

public class Log {

    private static final StackWalker stackWalker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);

    public static void d(String message) {
        System.out.println(specialInfo(stackWalker.getCallerClass(), "DEBUG") + message);
    }

    public static void d(String message, Object... args) {
        System.out.println(specialInfo(stackWalker.getCallerClass(), "DEBUG") + String.format(message, args));
    }

    public static void e(String message) {
        System.out.println(specialInfo(stackWalker.getCallerClass(), "ERROR") + message);
    }

    public static void e(Throwable throwable) {
        System.out.println(specialInfo(stackWalker.getCallerClass(), "ERROR") + throwable.getMessage());
        Arrays.stream(throwable.getStackTrace()).forEach(System.out::println);
    }

    private static String specialInfo(Class<?> callerClass, String mode) {
        return ZonedDateTime.now() + "\t[" + mode + "]\t" + callerClass.getCanonicalName() + ":\t";
    }
}
