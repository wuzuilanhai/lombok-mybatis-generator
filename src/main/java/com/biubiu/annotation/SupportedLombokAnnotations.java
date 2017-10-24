package com.biubiu.annotation;

/**
 * Created by zhanghaibiao on 2017/10/24.
 */
public enum SupportedLombokAnnotations {
    DATA("data", "@Data", "lombok.Data"), BUILDER("builder", "@Builder", "lombok.Builder"),
    ALL_ARGS_CONSTRUCTOR("allArgsConstructor", "@AllArgsConstructor", "lombok.AllArgsConstructor"),
    NO_ARGS_CONSTRUCTOR("noArgsConstructor", "@NoArgsConstructor", "lombok.NoArgsConstructor"),
    TO_STRING("toString", "@ToString", "lombok.ToString");

    SupportedLombokAnnotations(String alias, String lombokAnnotation, String packageName) {
        this.alias = alias;
        this.lombokAnnotation = lombokAnnotation;
        this.packageName = packageName;
    }

    private final String alias;

    private final String lombokAnnotation;

    private final String packageName;

    public String getAlias() {
        return alias;
    }

    public String getLombokAnnotation() {
        return lombokAnnotation;
    }

    public String getPackageName() {
        return packageName;
    }

    public static SupportedLombokAnnotations getValueOf(String alias) {
        for (SupportedLombokAnnotations annotation : SupportedLombokAnnotations.values()) {
            if (String.CASE_INSENSITIVE_ORDER.compare(alias, annotation.getAlias()) == 0)
                return annotation;
        }
        return null;
    }

}
