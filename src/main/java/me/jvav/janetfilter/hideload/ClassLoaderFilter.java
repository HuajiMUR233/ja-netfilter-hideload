package me.jvav.janetfilter.hideload;

public class ClassLoaderFilter {
    public static void testClass(String name) throws ClassNotFoundException {
        if (name == null) {
            return;
        }

        if (name.toLowerCase().startsWith("com.janetfilter.")) {
            ClassNotFoundException e = new ClassNotFoundException(name);

            StackTraceElement[] elements = e.getStackTrace();
            if (elements.length > 0) {  // fix stacktrace
                StackTraceElement[] newElements = new StackTraceElement[elements.length - 1];
                System.arraycopy(elements, 1, newElements, 0, newElements.length);

                e.setStackTrace(newElements);
            }

            throw e;
        }

    }
}
