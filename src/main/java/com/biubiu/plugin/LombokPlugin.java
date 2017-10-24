package com.biubiu.plugin;

import com.biubiu.annotation.SupportedLombokAnnotations;
import com.google.common.collect.Sets;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by zhanghaibiao on 2017/10/24.
 */
public class LombokPlugin extends PluginAdapter {

    private final Set<SupportedLombokAnnotations> annotations = Sets.newLinkedHashSet();

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        annotations.add(SupportedLombokAnnotations.DATA);
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            boolean enable = Boolean.parseBoolean(entry.getValue().toString());
            if (enable) {
                String alias = entry.getKey().toString().trim();
                SupportedLombokAnnotations annotation = SupportedLombokAnnotations.getValueOf(alias);
                if (annotation != null) {
                    annotations.add(annotation);
                }
            }
        }
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        this.addAnnotation(topLevelClass);
        return true;
    }

    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        this.addAnnotation(topLevelClass);
        return true;
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        this.addAnnotation(topLevelClass);
        return true;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    private void addAnnotation(TopLevelClass topLevelClass) {
        for (SupportedLombokAnnotations annotation : annotations) {
            topLevelClass.addImportedType(annotation.getPackageName());
            topLevelClass.addAnnotation(annotation.getLombokAnnotation());
        }
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

}
